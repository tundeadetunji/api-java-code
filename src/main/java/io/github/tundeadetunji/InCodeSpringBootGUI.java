package io.github.tundeadetunji;

import java.util.List;

import static io.github.tundeadetunji.InCodeSpringBootAPI.*;

public class InCodeSpringBootGUI {
    public static String pasteInsideHTTPCallFromAppLikePostman(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines);

        String json = createJSONObject(fields_with_types);
        return json;
    }
    public static String pasteInsideRepositoryFile(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines);

        String data_type_of_id = getDataTypeOfId(fields_with_types);
        String class_ = getClassFromString(source);

        String result = createRepositoryString(class_, data_type_of_id);
        return result;
    }
    private static String pasteInsideDtoFile(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines, true);
        String class_ = getClassFromString(source);

        String result = getDtoString(class_, fields_with_types);
        return result;

    }

}
