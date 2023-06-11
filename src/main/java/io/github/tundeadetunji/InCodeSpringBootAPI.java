package io.github.tundeadetunji;
import java.util.*;
import static io.github.tundeadetunji.General.*;

public class InCodeSpringBootAPI {

    public static String getClassFromString(String s)
    {
        List<String> lines = stringToList(s);
        String final_Keyword = "";
        for (var i = 0; i < lines.size(); i++)
        {
            if (lines.get(i).contains("class"))
            {
                final_Keyword = removeAccessModifierFromFieldWithType(lines.get(i)).replace("class", "").replace("{", "").strip();
            }
        }
        return final_Keyword;
    }

    public static ArrayList<String> getLinesFromString(String s)
    {
        List<String> temp = stringToList(s);
        ArrayList<String> final_Keyword = new ArrayList<String>();
        for (var i = 0; i < temp.size(); i++)
        {
            if ((temp.get(i).contains("package") == false) && (temp.get(i).contains("import") == false) && (temp.get(i).contains("/*") == false) && (temp.get(i).contains("*/") == false) && (temp.get(i).contains("@") == false) && (temp.get(i).contains("{") == false) && (temp.get(i).contains("}") == false) && (temp.get(i).strip().length() > 0) && (temp.get(i).contains("class") == false))
            {
                final_Keyword.add(temp.get(i).strip());
            }
        }
        return final_Keyword;
    }

    public static List<String> getFieldsWithTypes(List<String> lines, boolean it_is_for_dto)
    {
        var temp = lines;
        ArrayList<String> final_Keyword = new ArrayList<String>();
        for (var i = 0; i < temp.size(); i++)
        {
            if (temp.get(i).contains("//"))
            {
                if (it_is_for_dto)
                {
                    final_Keyword.add(splitTextInTwo(temp.get(i), ";", SideToReturn.Left));
                }
                else
                {
                    final_Keyword.add(removeAccessModifierFromFieldWithType(splitTextInTwo(temp.get(i), ";", SideToReturn.Left)));
                }
            }
            else
            {
                if (it_is_for_dto)
                {
                    final_Keyword.add(temp.get(i).replace(";", ""));
                }
                else
                {
                    final_Keyword.add(removeAccessModifierFromFieldWithType(temp.get(i)).replace(";", ""));
                }
            }
        }

        return final_Keyword;
    }

    public static List<String> getFieldsWithTypes(List<String> lines)
    {
        return getFieldsWithTypes(lines, false);
    }

    public static List<String> getFieldsFromFieldsWithTypes(List<String> fields_with_types)
    {
        List<String> final_Keyword = new ArrayList<String>();
        List<String> temp = null;
        for (var i = 0; i < fields_with_types.size(); i++)
        {
            temp = splitTextInSplits(fields_with_types.get(i), " ");
            final_Keyword.add(temp.get(1).strip());
        }
        return final_Keyword;
    }

    public static String getDataTypeOfId(List<String> fields_with_types)
    {
        List<String> final_Keyword = new ArrayList<String>();
        String data_type_of_id = "Long";
        List<String> temp = null;
        for (var i = 0; i < fields_with_types.size(); i++)
        {
            temp = splitTextInSplits(fields_with_types.get(i), " ");
            if (temp.get(1).toLowerCase().strip().equals("id"))
            {
                data_type_of_id = temp.get(0).strip();
                break;
            }
        }
        return data_type_of_id;
    }

    public static String createJSONObject(List<String> s)
    {
        if (s.size() < 1)
        {
            return "";
        }
        return "{" + "\r\n" + createObjectTokens(s) + "\r\n" + "}";
    }
    public static String getDtoString(String class_, List<String> fields_with_types)
    {

        String result = "@Data" + "\r\n";
        //result &= "@Builder" & vbCrLf
        result += "public class " + class_ + "Dto {" + "\r\n";
        for (var i = 0; i < fields_with_types.size(); i++)
        {
            result += "\t" + fields_with_types.get(i) + ";" + "\r\n";
        }
        result += "}";

        return result;
    }
    public static String createRepositoryString(String class_, String data_type_of_id)
    {
        String result = "@Repository" + "\r\n";
        result += "public interface " + class_ + "Repository extends JpaRepository<" + class_ + ", " + data_type_of_id + "> {" + "\r\n" + "\r\n";
        result += "}";
        return result;
    }
    private static String createObjectTokens(List<String> s)
    {
        String result = "\t";
        List<String> temp = null;
        String left = null;
        String right = null;
        for (var i = 0; i < s.size(); i++)
        {
            temp = splitTextInSplits(s.get(i), " ");
            left = temp.get(0).strip().toLowerCase();
            right = temp.get(1).strip();
            result += "\"" + right + "\":";
            if (left.equals("string") || left.equals("char") || left.equals("Character"))
            {
                result += "\"" + "\"";
            }
            if (i != s.size() - 1)
            {
                result += "," + "\r\n" + "\t";
            }
        }
        return result;
    }

    private static String removeAccessModifierFromFieldWithType(String s)
    {
        return s.replace("private", "").replace("public", "").replace("protected", "").strip();
    }


}
