package io.github.tundeadetunji;

import java.util.*;

import static io.github.tundeadetunji.General.*;

/**
 * The methods in this class are used in conjunction with (rather, called from) those in InCodeSpringBootGui.
 *
 */
public class InCodeSpringBootApi {

    public static String getClassFromString(String s) {
        List<String> lines = stringToList(s);
        String final_Keyword = "";
        for (var i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("class")) {
                final_Keyword = removeAccessModifierFromFieldWithType(lines.get(i)).replace("class", "").replace("{", "").strip();
            }
        }
        return final_Keyword;
    }

    public static ArrayList<String> getLinesFromString(String s) {
        List<String> temp = stringToList(s);
        ArrayList<String> final_Keyword = new ArrayList<String>();
        for (var i = 0; i < temp.size(); i++) {
            if ((temp.get(i).contains("package") == false) && (temp.get(i).contains("import") == false) && (temp.get(i).contains("/*") == false) && (temp.get(i).contains("*/") == false) && (temp.get(i).contains("@") == false) && (temp.get(i).contains("{") == false) && (temp.get(i).contains("}") == false) && (temp.get(i).strip().length() > 0) && (temp.get(i).contains("class") == false)) {
                final_Keyword.add(temp.get(i).strip());
            }
        }
        return final_Keyword;
    }

    public static List<String> getFieldsWithTypes(List<String> lines, boolean it_is_for_dto) {
        var temp = lines;
        ArrayList<String> final_Keyword = new ArrayList<String>();
        for (var i = 0; i < temp.size(); i++) {
            if (temp.get(i).contains("//")) {
                if (it_is_for_dto) {
                    final_Keyword.add(splitTextInTwo(temp.get(i), ";", SideToReturn.Left));
                } else {
                    final_Keyword.add(removeAccessModifierFromFieldWithType(splitTextInTwo(temp.get(i), ";", SideToReturn.Left)));
                }
            } else {
                if (it_is_for_dto) {
                    final_Keyword.add(temp.get(i).replace(";", ""));
                } else {
                    final_Keyword.add(removeAccessModifierFromFieldWithType(temp.get(i)).replace(";", ""));
                }
            }
        }

        return final_Keyword;
    }

    public static List<String> getFieldsWithTypes(List<String> lines) {
        return getFieldsWithTypes(lines, false);
    }

    public static List<String> getFieldsFromFieldsWithTypes(List<String> fields_with_types) {
        List<String> final_Keyword = new ArrayList<String>();
        List<String> temp = null;
        for (var i = 0; i < fields_with_types.size(); i++) {
            temp = splitTextInSplits(fields_with_types.get(i), " ");
            final_Keyword.add(temp.get(1).strip());
        }
        return final_Keyword;
    }

    public static String getDataTypeOfId(List<String> fields_with_types) {
        List<String> final_Keyword = new ArrayList<String>();
        String data_type_of_id = "Long";
        List<String> temp = null;
        for (var i = 0; i < fields_with_types.size(); i++) {
            temp = splitTextInSplits(fields_with_types.get(i), " ");
            if (temp.get(1).toLowerCase().strip().equals("id")) {
                data_type_of_id = temp.get(0).strip();
                break;
            }
        }
        return data_type_of_id;
    }

    public static String createJSONObject(List<String> s) {
        if (s.size() < 1) {
            return "";
        }
        return "{" + "\r\n" + createObjectTokens(s) + "\r\n" + "}";
    }

    public static String getDtoString(String class_, List<String> fields_with_types) {

        String result = "@Data" + "\r\n";
        //result &= "@Builder" & vbCrLf
        result += "public class " + class_ + "Dto {" + "\r\n";
        for (var i = 0; i < fields_with_types.size(); i++) {
            result += "\t" + fields_with_types.get(i) + ";" + "\r\n";
        }
        result += "}";

        return result;
    }

    public static String createRepositoryString(String class_, String data_type_of_id) {
        String result = "@Repository" + "\r\n";
        result += "public interface " + class_ + "Repository extends JpaRepository<" + class_ + ", " + data_type_of_id + "> {" + "\r\n" + "\r\n";
        result += "}";
        return result;
    }

    private static String createObjectTokens(List<String> s) {
        String result = "\t";
        List<String> temp = null;
        String left = null;
        String right = null;
        for (var i = 0; i < s.size(); i++) {
            temp = splitTextInSplits(s.get(i), " ");
            left = temp.get(0).strip().toLowerCase();
            right = temp.get(1).strip();
            result += "\"" + right + "\":";
            if (left.equals("string") || left.equals("char") || left.equals("Character")) {
                result += "\"" + "\"";
            }
            if (i != s.size() - 1) {
                result += "," + "\r\n" + "\t";
            }
        }
        return result;
    }

    private static String removeAccessModifierFromFieldWithType(String s) {
        return s.replace("private", "").replace("public", "").replace("protected", "").strip();
    }

    /**
     * ToDo
     * @param table
     * @param x_label_field
     * @param y_value_field
     * @param where_keys_values
     * @return
     */
    public static String BuildSelectString(String table, String x_label_field, String y_value_field, Map<String, Object> where_keys_values) {
        /*'Line
        '' x_label_field: quater, y_value_field: drink, x_label_field_caption: what should be denoted as the 'topic/subject' in the title, e.g. the column name of y_value_field (in this case, "drink")
        Dim query As String = BuildSelectString(table, {x_label_field, y_value_field}, keys_)
        SELECT quater, drink FROM pie
        */
        return "";
    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_average
     * @param field_for_interval
     * @param interval_from
     * @param interval_to
     * @return
     */
    public static String BuildAvgStringGroupedBetween(String table, String field_to_group, String field_to_average, String field_for_interval, int interval_from, int interval_to) {
        /*'PieAverageAcrossInterval
        '' field_to_average = drink, field_to_group = quater, field_for_interval = id, interval_from = 2, interval_to = 6 : first(25), second(77), third(32)
        Dim query As String = BuildAVGString_GROUPED_BETWEEN(table, field_to_group, field_to_average, {field_for_interval})
        SELECT quater, AVG(drink)  FROM pie WHERE (id BETWEEN @id_FROM AND @id_TO) GROUP BY quater
        */
        return "";

    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_average
     * @param where_keys_values
     * @return
     */
    public static String BuildAvgStringGrouped(String table, String field_to_group, String field_to_average, Map<String, Object> where_keys_values) {
        /*        'PieAverage
        '' field_to_group = quater, field_to_average = drink: first(37), second(77), third(31)
        Dim query = BuildAVGString_GROUPED(table, field_to_group, field_to_average, keys_)
        SELECT quater, AVG(drink)  FROM pie GROUP BY quater
        */
        return "";

    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_sum
     * @param field_for_interval
     * @param interval_from
     * @param interval_to
     * @return
     */
    public static String BuildSumStringGroupedBetween(String table, String field_to_group, String field_to_sum, String field_for_interval, int interval_from, int interval_to) {
        /*'PieSumAcrossInterval
        '' field_to_sum = drink, field_to_group = quater, field_for_interval = id, interval_from = 2, interval_to = 6 : first(25), second(155), third(65)
        Dim query As String = BuildSumString_GROUPED_BETWEEN(table, field_to_group, field_to_sum, {field_for_interval})
        SELECT quater, SUM(drink)  FROM pie WHERE (id BETWEEN @id_FROM AND @id_TO) GROUP BY quater
        */
        return "";

    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_sum
     * @param where_keys_values
     * @return
     */
    public static String BuildSumStringGrouped(String table, String field_to_group, String field_to_sum, Map<String, Object> where_keys_values) {
        /*'PieSum
        '' field_to_group = quater, field_to_sum = drink: first(75), second(155), third(95)
        Dim query = BuildSumString_GROUPED(table, field_to_group, field_to_sum, keys_)
        SELECT quater, SUM(drink)  FROM pie GROUP BY quater
        */
        return "";

    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_count
     * @param field_for_interval
     * @param interval_from
     * @param interval_to
     * @return
     */
    public static String BuildCountStringGroupedBetween(String table, String field_to_group, String field_to_count, String field_for_interval, int interval_from, int interval_to) {
        /*       'PieCountAcrossInterval
        '' field_to_count = quater, field_for_interval = id, interval_from = 2, interval_to = 6 : first(1), second(2), third(2)

        Dim query = BuildCountString_GROUPED_BETWEEN(table, field_to_group, field_to_count, {field_for_interval})
        SELECT quater, COUNT(quater)  FROM pie WHERE (id BETWEEN @id_FROM AND @id_TO) GROUP BY quater
        */
        return "";

    }

    /**
     * ToDo
     * @param table
     * @param field_to_group
     * @param field_to_count
     * @param where_keys_values
     * @return
     */
    public static String BuildCountStringGrouped(String table, String field_to_group, String field_to_count, Map<String, Object> where_keys_values) {
        /*'PieCount
        '' field_to_count = quater: first(2), second(2), third(3)

        Dim query = BuildCountString_GROUPED(table, field_to_count, field_to_group, keys_)
        SELECT quater, COUNT(*)  FROM pie GROUP BY quater
        */
        return "";
    }







}
