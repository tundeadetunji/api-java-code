package io.github.tundeadetunji;

import java.util.ArrayList;
import java.util.List;

/**
 * Methods to construct charts dynamically. Pass in appropriate parameters,
 * or combine with MySQL.count() for example, to generate a pie chart, then
 * put the resulting string in HTML div at runtime.
 *
 * The methods assume you are using Shoppy's template described in General.previewHtml().
 * For it to work, the CSS and JS and other related assets must be in place. You can pass
 * the result of desired method as content (parameter) to General.previewHTML(), for instance.
 */
public final class Charts {

    public static String pie(List<Object> values) {
        return Chart_Pie_String(values, null, 470, 300);
    }

    public static String pie(List<Object> values, List<String> colors) {
        return Chart_Pie_String(values, colors, 470, 300);
    }

    public static String pie(List<Object> values, List<String> colors, int width, int height) {
        return Chart_Pie_String(values, colors, width, height);
    }

    public static String pie(List<Object> values, int width, int height) {
        return Chart_Pie_String(values, null, width, height);
    }

    private static String Chart_Pie_String(List<Object> values, List<String> colors, int width, int height) {
        String id = io.github.tundeadetunji.General.newGUID(General.IDPattern.Short);
        return new StringBuilder().append("<canvas id=\"" + id.toString() + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                        "			<script>" + "\r\n" +
                        "				var pieData = [")
                .append(Chart_Pie_Variable(values, colors))
                .append("];" + "\r\n" +
                        "					new Chart(document.getElementById(\"" + id.toString() + "\").getContext(\"2d\")).Pie(pieData);" + "\r\n" +
                        "			  </script>")
                .toString();
    }

    private static String Chart_Pie_Variable(List<Object> values, List<String> colorsList) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            String colors = colorsList != null ? colorsList.get(i) : randomColor(new ArrayList<Integer>());
            result.append("{" + "\r\n" +
                    "						value: " + values.get(i) + "," + "\r\n" +
                    "						color: \"" + colors + "\"" + "\r\n" +
                    "				  }").append(i < values.size() - 1 ? "," : "");
        }
        return result.toString();
    }

    private static String randomColor(List<Integer> integers) {
        return "rgb(" + randomList(0, 256, integers) + ", " + randomList(0, 256, integers) + ", " + randomList(0, 256, integers) + ")";
    }

    private static int randomList(int min, int max, List<Integer> already) {
        if (already.size() >= max) already.clear();

        int random = (int) (Math.random() * (max - min + 1) + min);
        while (already.size() > 0 && already.contains(random)) {
            random = (int) Math.random();
        }
        already.add(random);
        return random;
    }

}
