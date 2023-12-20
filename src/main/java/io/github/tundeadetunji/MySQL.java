package io.github.tundeadetunji;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class MySQL {

    private static MySQL instance;

    private String url; // e.g. jdbc:mysql://localhost:3306/schema_name
    private String username; // e.g. root
    private String password; // e.g. root's password

    private static String database;

    private MySQL(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        String[] tokens = url.split("/");
        this.database = tokens[tokens.length - 1];
    }

    public static MySQL getInstance(String url, String username, String password) {
        if (instance == null) instance = new MySQL(url, username, password);
        return instance;
    }

    /**
     * Returns rows resulting from Count.
     * @param table
     * @param field
     * @param whereKeysValues
     * @return List of Objects corresponding to the rows retrieved.
     */
    public List<Object> count(String table, String field, Map<String, String> whereKeysValues) {
        List<Object> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(instance.url, instance.username, instance.password)) {
            ResultSet rows = connection.createStatement().executeQuery(QueryFactory.buildCountString(table, field, whereKeysValues));
            while(rows.next()){
                result.add(rows.getString("count"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public static class QueryFactory{
        /**
         * Constructs SQL Count e.g. SELECT field, COUNT(*) FROM table GROUP BY field.
         * @param table
         * @param field
         * @param whereKeysValues
         * @return
         */
        public static String buildCountString(String table, String field, Map<String, String> whereKeysValues) {
            //SELECT quarter, COUNT(*) as count FROM radium.pie WHERE (quarter = 'first' and id = 1) GROUP BY quarter
            StringBuilder result = new StringBuilder("SELECT " + field + ", COUNT(*) as count FROM " + database + "." + table);

            if (whereKeysValues != null){
                result.append(" WHERE (");
                List<String> keys = new ArrayList<String>((Collection<? extends String>) whereKeysValues.keySet());
                for(int i = 0; i < keys.size(); i++){
                    result.append(keys.get(i) + " = '" + whereKeysValues.get(keys.get(i)) + "'").append(i < keys.size() - 1 ? " and " : "");
                }
                result.append(")");
            }

            result.append(" GROUP BY " + field);

            return result.toString();
        }
    }
}
