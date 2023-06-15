package io.github.tundeadetunji;

import java.util.List;

import static io.github.tundeadetunji.InCodeSpringBootApi.*;

/**
 * The methods in this class can most likely increase productivity by
 * generating code snippets that can be pasted directly into the file.
 */
public class InCodeSpringBootGui {
    /**
     * Creates a JSON string that you can paste directly in the body of the request.
     *
     * @param source The text copied from the class file
     * @return
     */
    public static String pasteInsideRequestBody(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines);

        String json = createJSONObject(fields_with_types);
        return json;
    }

    /**
     * Creates text that can be placed in the Repository file.
     *
     * @param source The text copied from the class file
     * @return
     */
    public static String pasteInsideRepositoryFile(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines);

        String data_type_of_id = getDataTypeOfId(fields_with_types);
        String class_ = getClassFromString(source);

        String result = createRepositoryString(class_, data_type_of_id);
        return result;
    }

    /**
     * Creates text that can be placed in the Dto file.
     *
     * @param source The text copied from the class file
     * @return
     */
    private static String pasteInsideDtoFile(String source)
    {
        List<String> lines = getLinesFromString(source);
        List<String> fields_with_types = getFieldsWithTypes(lines, true);
        String class_ = getClassFromString(source);

        String result = getDtoString(class_, fields_with_types);
        return result;

    }

}
