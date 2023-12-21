package io.github.tundeadetunji;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Methods to construct charts (HTML/CSS/JS snippet) dynamically. Pass in appropriate parameters,
 * or combine with MySQL.count() for example, to generate a pie chart, then
 * put the resulting string in HTML div at runtime.
 * <p>
 * The methods assume you are using Shoppy's template described in General.previewHtml().
 * For it to work, the CSS and JS and other related assets must be in place. You can pass
 * the result of desired method as content (parameter) to General.previewHTML(), for instance.
 */
public final class Charts {

    public static <T> String pie(List<T> values) {
        return Chart_Pie_String(values, null, 470, 300);
    }

    public static <K, V> String pie(Map<K, V> values) {
        return Chart_Pie_String(General.mapToList(values, General.SideToReturn.Right), null, 470, 300);
    }

    public static <T> String pie(List<T> values, List<String> colors) {
        return Chart_Pie_String(values, colors, 470, 300);
    }

    public static <K, V> String pie(Map<K, V> values, List<String> colors) {
        return Chart_Pie_String(General.mapToList(values, General.SideToReturn.Right), colors, 470, 300);
    }

    public static <T> String pie(List<T> values, List<String> colors, int width, int height) {
        return Chart_Pie_String(values, colors, width, height);
    }

    public static <K, V> String pie(Map<K, V> values, List<String> colors, int width, int height) {
        return Chart_Pie_String(General.mapToList(values, General.SideToReturn.Right), colors, width, height);
    }

    public static <T> String pie(List<T> values, int width, int height) {
        return Chart_Pie_String(values, null, width, height);
    }

    public static <K, V> String pie(Map<K, V> values, int width, int height) {
        return Chart_Pie_String(General.mapToList(values, General.SideToReturn.Right), null, width, height);
    }

    public static <T> String doughnut(List<T> values) {
        return Chart_Doughnut_String(values, null, 470, 300);
    }

    public static <K, V> String doughnut(Map<K, V> values) {
        return Chart_Doughnut_String(General.mapToList(values, General.SideToReturn.Right), null, 470, 300);
    }

    public static <T> String doughnut(List<T> values, List<String> colors) {
        return Chart_Doughnut_String(values, colors, 470, 300);
    }

    public static <K, V> String doughnut(Map<K, V> values, List<String> colors) {
        return Chart_Doughnut_String(General.mapToList(values, General.SideToReturn.Right), colors, 470, 300);
    }

    public static <T> String doughnut(List<T> values, List<String> colors, int width, int height) {
        return Chart_Doughnut_String(values, colors, width, height);
    }

    public static <K, V> String doughnut(Map<K, V> values, List<String> colors, int width, int height) {
        return Chart_Doughnut_String(General.mapToList(values, General.SideToReturn.Right), colors, width, height);
    }

    public static <T> String doughnut(List<T> values, int width, int height) {
        return Chart_Doughnut_String(values, null, width, height);
    }

    public static <K, V> String doughnut(Map<K, V> values, int width, int height) {
        return Chart_Doughnut_String(General.mapToList(values, General.SideToReturn.Right), null, width, height);
    }



    public static <T> String line(List<String> labels, List<T> values)
    {
        return Chart_Line_String(labels, values, 400, 300);
    }

    public static <K, V> String line(Map<K, V> labelsValues)
    {
        return Chart_Line_String(General.mapToList(labelsValues, General.SideToReturn.Left), General.mapToList(labelsValues, General.SideToReturn.Right), 400, 300);
    }

    public static <T> String line(List<String> labels, List<T> values, int width, int height)
    {
        return Chart_Line_String(labels, values, width, height);
    }

    public static <K, V> String line(Map<K, V> labelsValues, int width, int height)
    {
        return Chart_Line_String(General.mapToList(labelsValues, General.SideToReturn.Left), General.mapToList(labelsValues, General.SideToReturn.Right), width, height);
    }


    public static <T> String bar(List<String> labels, List<T> values)
    {
        return BarChart_String(labels, values, 400, 300);
    }

    public static <K, V> String bar(Map<K, V> labelsValues)
    {
        return BarChart_String(General.mapToList(labelsValues, General.SideToReturn.Left) , General.mapToList(labelsValues, General.SideToReturn.Right), 400, 300);
    }

    public static <T> String bar(List<String> labels, List<T> values, int width, int height)
    {
        return BarChart_String(labels, values, width, height);
    }

    public static <K, V> String bar(Map<K, V> labelsValues, int width, int height)
    {
        return BarChart_String(General.mapToList(labelsValues, General.SideToReturn.Left) , General.mapToList(labelsValues, General.SideToReturn.Right), width, height);
    }




    private static <T> String BarChart_String(List<String> labels, List<T> values, int width, int height)
    {
        String id = General.newGUID(General.IDPattern.Short);
        return new StringBuilder().append("<canvas id=\"" + id + "\" width=\"" + width + "\" height=\"" + height + "\"" + "\r\n" +
                "            style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "        <script>" + "\r\n" +
                "            var barChartData = {" + "\r\n" +
                "                labels: [" + bar_labels_string(labels) + "]," + "\r\n" +
                "                datasets: [{ fillColor: \"" + randomColor(new ArrayList<Integer>()) + "\", data: [" + bar_dataset_string(values) + "] }]" + "\r\n" +
                "            };" + "\r\n" +
                "            new Chart(document.getElementById(\"" + id + "\").getContext(\"2d\")).Bar(barChartData);" + "\r\n" +
                "        </script>").toString();
    }

    private static String bar_labels_string(List<String> labels)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < labels.size(); i++)
        {
            result.append("\"" + labels.get(i) + "\"" + ((i < labels.size() - 1) ? ", " : ""));
        }
        return result.toString();
    }

    private static <T> String bar_dataset_string(List<T> dataset)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dataset.size(); i++)
        {
            result.append(dataset.get(i)).append(((i < dataset.size() - 1) ? ", " : ""));
        }
        return result.toString();
    }

    private static <T> String Chart_Line_String(List<String> labels, List<T> values, int width, int height) {
        var id = io.github.tundeadetunji.General.newGUID(General.IDPattern.Short);
        return new StringBuilder().append("<canvas id=\"" + id.toString() + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "			<script>" + "\r\n" +
                "				var lineChartData = {")
                .append(Chart_Line_Variable(labels, values))
                .append("new Chart(document.getElementById(\"" + id.toString() + "\").getContext(\"2d\")).Line(lineChartData);" + "\r\n" +
                "			  </script>").toString();
    }

    private static <T> String Chart_Line_Variable(List<String> labels, List<T> values) {
        StringBuilder l_string = new StringBuilder().append("labels : [");
        for (var i = 0; i < labels.size(); i++) {
            l_string.append("\"" + labels.get(i) + "\"")
                    .append(i != labels.size() - 1 ? "," : "");
        }
        String fc = randomColor(new ArrayList<Integer>());
        String c = randomColor(new ArrayList<Integer>());
        l_string.append("],");

        StringBuilder v_string = new StringBuilder().append("datasets: [" + "\r\n" +
                "										{" + "\r\n" +
                "											fillColor: \"" + fc.toString() + "\"," + "\r\n" +
                "											strokeColor: \"" + c.toString() + "\"," + "\r\n" +
                "											pointColor: \"" + c.toString() + "\"," + "\r\n" +
                "											pointStrokeColor: \"#fff\"," + "\r\n" +
                "											data: [");
        for (var j = 0; j < values.size(); j++) {
            v_string.append(values.get(j))
                    .append(j != values.size() - 1 ? "," : "");
        }
        v_string.append("]}]};");
        return l_string.append(v_string).toString();
    }

    private static <T> String Chart_Pie_String(List<T> values, List<String> colors, int width, int height) {
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

    private static <T> String Chart_Pie_Variable(List<T> values, List<String> colorsList) {

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

    private static <T> String Chart_Doughnut_String(List<T> values, List<String> colors, int width, int height) {
        String id = io.github.tundeadetunji.General.newGUID(General.IDPattern.Short);
        return new StringBuilder().append("<canvas id=\"" + id.toString() + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                        "			<script>" + "\r\n" +
                        "				var doughnutData = [")
                .append(Chart_Doughnut_Variable(values, colors))
                .append("];" + "\r\n" +
                        "					new Chart(document.getElementById(\"" + id.toString() + "\").getContext(\"2d\")).Doughnut(doughnutData);" + "\r\n" +
                        "			  </script>").toString();
    }

    private static <T> String Chart_Doughnut_Variable(List<T> values, List<String> colorsList) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            String colors = colorsList != null ? colorsList.get(i) : randomColor(new ArrayList<>());
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
