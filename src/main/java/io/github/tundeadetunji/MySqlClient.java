package io.github.tundeadetunji;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * For example
 * MySqlClient client = MySqlClient.initialize(url, "","");
 * client.select("table_name_here", List.of("username", "first_name"), Map.of("id", "2000"));
 */
public final class MySqlClient {

    private static MySqlClient instance;

    private static String mUrl; // e.g. jdbc:mysql://localhost:3306/schema_name
    private static String mUsername; // e.g. root
    private static String mPassword; // e.g. root's password

    private static String mDatabase; //schema_name

    private MySqlClient(String url, String username, String password) {
        mUrl = url;
        mUsername = username;
        mPassword = password;

        String[] tokens = url.split("/");
        mDatabase = tokens[tokens.length - 1];
    }


    public static MySqlClient initialize(String url, String username, String password) {
        instance = new MySqlClient(url, username, password);
        return instance;
    }

    /**
     * Returns rows resulting from Count.
     *
     * @param table
     * @param countField
     * @param whereKeysValues
     * @return List of Objects corresponding to the rows retrieved.
     */
    public <K, V> Map<K, V> countGrouped(String table, String countField, Map<K, V> whereKeysValues) {
        return getRows(Queries.countGrouped(mDatabase, table, countField, whereKeysValues));
    }

    /**
     * Returns rows resulting from Count.
     *
     * @param table
     * @param countField
     * @param intervalField
     * @param intervalFrom
     * @param intervalTo
     * @return List of Objects corresponding intervalTo the rows retrieved.
     */
    public <K, V> Map<K, V> countGroupedBetween(String table, String countField, String intervalField, String intervalFrom, String intervalTo) {
        return getRows(Queries.countGroupedBetween(mDatabase, table, countField, intervalField, intervalFrom, intervalTo));
    }

    /**
     * Returns rows resulting from Sum.
     * @param table
     * @param sumField
     * @param groupField
     * @param whereKeysValues
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> sumGrouped(String table, String sumField, String groupField, Map<String, String> whereKeysValues) {
        return getRows(Queries.sumGrouped(mDatabase, table, sumField, groupField, whereKeysValues));
    }


    /**
     * Returns rows resulting from Sum.
     * @param table
     * @param sumField
     * @param groupField
     * @param intervalField
     * @param intervalFrom
     * @param intervalTo
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> sumGroupedBetween(String table, String sumField, String groupField, String intervalField, String intervalFrom, String intervalTo) {
        return getRows(Queries.sumGroupedBetween(mDatabase, table, sumField, groupField, intervalField, intervalFrom, intervalTo));
    }

    /**
     * Returns rows resulting from Average.
     * @param table
     * @param averageField
     * @param groupField
     * @param whereKeysValues
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> averageGrouped(String table, String averageField, String groupField, Map<K, V> whereKeysValues) {
        return getRows(Queries.averageGrouped(mDatabase, table, averageField, groupField, whereKeysValues));
    }

    /**
     * Returns rows resulting from Average.
     * @param table
     * @param averageField
     * @param groupField
     * @param intervalField
     * @param intervalFrom
     * @param intervalTo
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> averageGroupedBetween(String table, String averageField, String groupField, String intervalField, String intervalFrom, String intervalTo) {
        return getRows(Queries.averageGroupedBetween(mDatabase, table, averageField, groupField, intervalField, intervalFrom, intervalTo));
    }

    /**
     * Returns rows resulting from Select.
     * @param table
     * @param selectKeys
     * @param whereKeysValues
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> select(String table, List<String> selectKeys, Map<K, V> whereKeysValues) {
        return getRows(Queries.select(mDatabase, table, selectKeys, whereKeysValues));
    }

    private <K, V> Map<K, V> getRows(String query) {
        Map<K, V> result = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(mUrl, mUsername, mPassword)) {
            ResultSet rows = connection.createStatement().executeQuery(query);
            while (rows.next()) {
                result.put((K) rows.getObject(1), (V) rows.getObject(2));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public static class Queries {
        /**
         * SELECT countField, COUNT(*) FROM table GROUP BY countField
         *
         * @param table
         * @param countField
         * @param whereKeysValues
         * @return
         */
        public static <K, V> String countGrouped(String database, String table, String countField, Map<K, V> whereKeysValues) {
            //SELECT quarter, COUNT(*) as count FROM database.table WHERE (countField = 'first' and Id = 1) GROUP BY quarter
            StringBuilder result = new StringBuilder("SELECT " + countField + ", COUNT(*) as count FROM " + database + "." + table);

            if (whereKeysValues != null) {
                result.append(" WHERE (");
                List<String> keys = new ArrayList<String>((Collection<? extends String>) whereKeysValues.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    result.append(keys.get(i) + " = '" + whereKeysValues.get(keys.get(i)) + "'").append(i < keys.size() - 1 ? " and " : "");
                }
                result.append(")");
            }

            result.append(" GROUP BY " + countField);

            return result.toString();
        }


        /**
         * SELECT countField, COUNT(countField)  FROM database.table WHERE (intervalField BETWEEN intervalFrom AND intervalTo) GROUP BY countField
         * @param database
         * @param table
         * @param countField
         * @param intervalField
         * @param intervalFrom
         * @param intervalTo
         * @return
         */
        public static String countGroupedBetween(String database, String table, String countField, String intervalField, String intervalFrom, String intervalTo) {
            return new StringBuilder("SELECT " + countField + ", COUNT(" + countField + ") FROM " + database + "." + table)
                    .append(" WHERE (").append(intervalField + " BETWEEN " + intervalFrom + " AND " + intervalTo + ") GROUP BY " + countField)
                    .toString();
        }

        /**
         * SELECT groupField, SUM(sumField)  FROM table WHERE (k1=@k1 AND k2=@k2) GROUP BY groupField
         * @param database
         * @param table
         * @param sumField
         * @param groupField
         * @param whereKeysValues
         * @return
         */
        public static <K, V> String sumGrouped(String database, String table, String sumField, String groupField, Map<K, V> whereKeysValues) {
            StringBuilder result = new StringBuilder("SELECT " + groupField + ", SUM(" + sumField + ") FROM " + database + "." + table);

            if (whereKeysValues != null) {
                result.append(" WHERE (");
                List<String> keys = new ArrayList<String>((Collection<? extends String>) whereKeysValues.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    result.append(keys.get(i) + " = '" + whereKeysValues.get(keys.get(i)) + "'").append(i < keys.size() - 1 ? " and " : "");
                }
                result.append(")");
            }

            result.append(" GROUP BY " + groupField);

            return result.toString();
        }

        /**
         * SELECT groupField, SUM(sumField)  FROM table WHERE (intervalField BETWEEN intervalFrom AND intervalTo) GROUP BY groupField
         * @param database
         * @param table
         * @param sumField
         * @param groupField
         * @param intervalField
         * @param intervalFrom
         * @param intervalTo
         * @return
         */
        public static String sumGroupedBetween(String database, String table, String sumField, String groupField, String intervalField, String intervalFrom, String intervalTo) {
            return new StringBuilder("SELECT " + groupField + ", SUM(" + sumField + ") FROM " + database + "." + table)
                    .append(" WHERE (").append(intervalField + " BETWEEN " + intervalFrom + " AND " + intervalTo + ") GROUP BY " + groupField)
                    .toString();
        }


        /**
         * SELECT groupField, AVG(averageField)  FROM table WHERE (k1=@k1 AND k2=@k2) GROUP BY groupField
         * @param database
         * @param table
         * @param averageField
         * @param groupField
         * @param whereKeysValues
         * @return
         */
        public static <K, V> String averageGrouped(String database, String table, String averageField, String groupField, Map<K, V> whereKeysValues) {
            StringBuilder result = new StringBuilder("SELECT " + groupField + ", AVG(" + averageField + ") FROM " + database + "." + table);

            if (whereKeysValues != null) {
                result.append(" WHERE (");
                List<String> keys = new ArrayList<String>((Collection<? extends String>) whereKeysValues.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    result.append(keys.get(i) + " = '" + whereKeysValues.get(keys.get(i)) + "'").append(i < keys.size() - 1 ? " and " : "");
                }
                result.append(")");
            }

            result.append(" GROUP BY " + groupField);

            return result.toString();
        }


        /**
         * SELECT groupField, AVG(averageField)  FROM table WHERE (interval BETWEEN intervalFrom AND intervalTo) GROUP BY groupField
         * @param database
         * @param table
         * @param averageField
         * @param groupField
         * @param intervalField
         * @param intervalFrom
         * @param intervalTo
         * @return
         */
        public static String averageGroupedBetween(String database, String table, String averageField, String groupField, String intervalField, String intervalFrom, String intervalTo) {
            return new StringBuilder("SELECT " + groupField + ", AVG(" + averageField + ") FROM " + database + "." + table)
                    .append(" WHERE (").append(intervalField + " BETWEEN " + intervalFrom + " AND " + intervalTo + ") GROUP BY " + groupField)
                    .toString();
        }


        /**
         * SELECT f1, f2 FROM table WHERE (w1=@w1 AND w2=@w2)
         * @param database
         * @param table
         * @param selectKeys
         * @param whereKeysValues
         * @return
         */
        public static <K, V> String select(String database, String table, List<String> selectKeys, Map<K, V> whereKeysValues) {
            StringBuilder result = new StringBuilder().append("SELECT");

            if (selectKeys != null) {
                result.append(" ");
                for (int i = 0; i < selectKeys.size(); i++) {
                    result.append(selectKeys.get(i)).append(i < selectKeys.size() - 1 ? ", " : "");
                }
            }

            result.append(selectKeys == null ? " * FROM " + database + "." + table : " FROM " + database + "." + table);

            if (whereKeysValues != null) {
                result.append(" WHERE (");
                List<String> keys = new ArrayList<String>((Collection<? extends String>) whereKeysValues.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    result.append(keys.get(i) + " = '" + whereKeysValues.get(keys.get(i)) + "'").append(i < keys.size() - 1 ? " and " : "");
                }
                result.append(")");
            }

            return result.toString();
        }
    }
}