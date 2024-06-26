package io.github.tundeadetunji;


import java.util.*;

public class Sql {
    private static final String JOIN_BEFORE_ON_IS_EMPTY = "JOIN seems to be empty. It should come before ON.";
    private static final String JOIN_REQUIRES_BOTH_JOIN_COLUMNS = "JOIN requires both join columns - ON seems to be missing.";
    private final String SELECT_BEFORE_WHERE_IS_EMPTY = "SELECT seems to be empty. It should come before WHERE.";
    private final String SELECT_BEFORE_JOIN_IS_INCOMPLETE = "SELECT seems to be incomplete. It should come before JOIN and contain FROM.";
    private final String SELECT_STATEMENT_IS_EMPTY = "SELECT seems to be empty.";
    private final String EMPTY_STRING = "";

    private final Flavor flavor;
    private String TABLE;

    private String joinTable;
    private String leftTableJoinColumn;
    private String rightTableJoinColumn;

    public Sql(Flavor flavor) {
        this.flavor = flavor;
    }

    public Sql() {
        this.flavor = Flavor.MySql;
    }

    public enum Flavor {
        MySql, Sqlite
    }

    private String result = "";
    //private String selectString = "";

    private final String SELECT = "SELECT";
    private final String SPACE = " ";
    private final String ALL = "*";
    private final String DOT = ".";
    private final String AS = "AS";
    private final String AND = "AND";
    private final String DOUBLE_QUOTE = "\"";
    private final String SINGLE_QUOTE = "'";
    private final String COMMA = ",";
    private final String FROM = "FROM";
    private final String WHERE = "WHERE";
    private final String EQUALS = "=";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";
    private final String QUESTION_MARK = "?";
    private final String JOIN = "JOIN";
    private final String ON = "ON";

    private String[] selectFieldsArray;
    private List<String> selectFieldsList;
    private Map<String, String> selectFieldsMap;

    public Sql select() {
        //selectString = SELECT + SPACE + ALL;
        return this;
    }

    public Sql select(String... fields) {
        this.selectFieldsArray = fields;
        return this;
    }

    public Sql select(List<String> fields) {
        this.selectFieldsList = fields;
        return this;
    }

    public Sql select(Map<String, String> fieldAliasMap) {
        this.selectFieldsMap = fieldAliasMap;
        return this;
    }

    private void constructSelect(String[] selectFieldsArray) {
        result = SELECT;
        Arrays.stream(selectFieldsArray)
                .forEach(field -> result += SPACE + TABLE + DOT + field + COMMA);

        result = result.substring(0, result.length() - COMMA.length());

    }

    private void constructSelect(List<String> selectFieldsList) {
        String[] temp = new String[selectFieldsList.size()];
        for (int i = 0; i < selectFieldsList.size(); i++) {
            temp[i] = selectFieldsList.get(i);
        }
        select(temp);
    }


    private Sql constructSelect(Map<String, String> fieldAliasMap) {

        final TreeMap<String, String> map = new TreeMap<>(fieldAliasMap);

        StringBuilder tokenBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            tokenBuilder.append(SPACE).append(entry.getKey()).append(SPACE).append(AS).append(SPACE).append(DOUBLE_QUOTE).append(entry.getValue()).append(DOUBLE_QUOTE).append(COMMA);
        }
        String scratch = tokenBuilder.toString();
        String tokens = scratch.substring(0, scratch.length() - COMMA.length());

        result = SELECT + tokens;
        return this;
    }

    public Sql from(String table) {
        this.TABLE = table;

        if (result.isEmpty()) {
            if (selectFieldsList != null) constructSelect(selectFieldsList);
            if (selectFieldsArray != null) constructSelect(selectFieldsArray);
            if (selectFieldsMap != null) constructSelect(selectFieldsMap);
            if (selectFieldsList == null && selectFieldsArray == null && selectFieldsMap == null){
                result = SELECT + SPACE + ALL;
            }
        }

        result += SPACE + FROM + SPACE + TABLE;
        return this;
    }

    public Sql where(List<String> whereArguments) {
        if (result.isEmpty()) throw new RuntimeException(SELECT_BEFORE_WHERE_IS_EMPTY);
        if (whereArguments.isEmpty()) return this;

        result += SPACE + WHERE + OPEN_BRACKET;
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < whereArguments.size(); i++) {
            tokenBuilder.append(TABLE).append(DOT).append(whereArguments.get(i)).append(EQUALS)
                    .append(QUESTION_MARK).append(i < whereArguments.size() - 1 ? SPACE + AND + SPACE : "");
        }

        result += tokenBuilder + CLOSE_BRACKET;
        return this;
    }

    public Sql where(String... whereArguments) {
        if (whereArguments.length == 0) return this;
        where(Arrays.asList(whereArguments));
        return this;
    }

    public Sql where(List<String> whereArgumentsOnLeftTable, List<String> whereArgumentsOnRightTable) {
        if (result.isEmpty()) throw new RuntimeException(SELECT_BEFORE_WHERE_IS_EMPTY);
        if (whereArgumentsOnLeftTable.isEmpty() && whereArgumentsOnRightTable.isEmpty()) return this;
        result += SPACE + WHERE + OPEN_BRACKET;
        if (!whereArgumentsOnLeftTable.isEmpty()) {
            result += constructWhereToken(whereArgumentsOnLeftTable, TABLE);
        }
        if (!whereArgumentsOnRightTable.isEmpty()) {
            result += SPACE + AND + SPACE;
            result += constructWhereToken(whereArgumentsOnRightTable, joinTable);
        }

        result += CLOSE_BRACKET;
        return this;
    }

    private String constructWhereToken(List<String> whereArguments, String table) {
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < whereArguments.size(); i++) {
            tokenBuilder.append(table).append(DOT).append(whereArguments.get(i)).append(EQUALS)
                    .append(QUESTION_MARK).append(i < whereArguments.size() - 1 ? SPACE + AND + SPACE : "");
        }
        return tokenBuilder.toString();
    }

    public Sql join(String table) {
        if (result.isEmpty()) throw new RuntimeException(SELECT_BEFORE_JOIN_IS_INCOMPLETE);
        this.joinTable = table;
        result += SPACE + JOIN + SPACE + table;
        return this;
    }

    public Sql on(String leftTableJoinColumn, String rightTableJoinColumn) {
        if (result.isEmpty()) throw new RuntimeException(SELECT_BEFORE_JOIN_IS_INCOMPLETE);
        if (joinTable.isEmpty()) throw new RuntimeException(JOIN_BEFORE_ON_IS_EMPTY);

        this.leftTableJoinColumn = leftTableJoinColumn;
        this.rightTableJoinColumn = rightTableJoinColumn;

        result += SPACE + ON + SPACE + TABLE + DOT + leftTableJoinColumn + SPACE + EQUALS + SPACE
                + joinTable + DOT + rightTableJoinColumn;

        return this;
    }

    public String go() {
        if (result.isEmpty()) throw new RuntimeException(SELECT_STATEMENT_IS_EMPTY);
        //if (!joinTable.isEmpty() && (leftTableJoinColumn.isEmpty() || rightTableJoinColumn.isEmpty()))
        //throw new RuntimeException(JOIN_REQUIRES_BOTH_JOIN_COLUMNS);

        return result;
    }
}
