package io.github.tundeadetunji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.tundeadetunji.General.newGUID;
import static io.github.tundeadetunji.General.*;

/**
 * This class contains methods to construct charts HTML snippets.
 * The CSS/Java are based on Shoppy's resources, but defaults to Bootstrap in syntax.
 * As such, Shoppy's files has to be present.
 * Check the output of io.github.tundeadetunji.General.previewHTML() to note the paths.
 * To use any of these methods, call io.github.tundeadetunji.General.previewHTML() and
 * pass any of the public methods as argument, e.g. previewHTML(PieChart(...)).
 */
public class Charts {

    /*
    Pie
     */


    public static String PieChart(List<String> l_values, List<String> l_colors) {
        return Chart_Pie_String(l_values, l_colors, 470, 300);
    }

    public static String PieChart(List<String> l_values) {
        return Chart_Pie_String(l_values, null, 470, 300);
    }

    public static String PieChart(List<String> l_values, List<String> l_colors, int width, int height) {
        return Chart_Pie_String(l_values, l_colors, width, height);
    }

    public static String PieChart(List<String> l_values, int width, int height) {
        return Chart_Pie_String(l_values, null, width, height);
    }

    private static String Chart_Pie_String(List<String> l_values, List<String> COLORS, int width, int height) {
        var id = newGUID(IDPattern.Long);
        String s = "<canvas id=\"" + id + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "			<script>" + "\r\n" +
                "				var pieData = [";
        s += Chart_Pie_Variable(l_values, COLORS);
        s += "];" + "\r\n" +
                "					new Chart(document.getElementById(\"" + id + "\").getContext(\"2d\")).Pie(pieData);" + "\r\n" +
                "			  </script>";
        return s;
    }

    private static String Chart_Pie_Variable(List<String> l_values, List<String> COLORS) {
        List<String> l = l_values;
        String s = "";
        String color;

        for (var i = 0; i <= l.size() - 1; i++) {
            if (COLORS != null) {
                color = COLORS.get(i);
            } else {
                color = Support.RandomColor(new ArrayList<Integer>());
            }
            s += "{" + "\r\n" +
                    "						value: " + l.get(i) + "," + "\r\n" +
                    "						color: \"" + color + "\"" + "\r\n" +
                    "				  }";
            if (i != l.size() - 1) {
                s += ",";
            }
        }
        return s;
    }




    /*
    Doughnut
     */

    public static String DoughnutChart(List<String> l_values) {
        return Chart_Doughnut_String(l_values, null, 470, 300);
    }

    public static String DoughnutChart(List<String> l_values, List<String> COLORS) {
        return Chart_Doughnut_String(l_values, COLORS, 470, 300);
    }

    public static String DoughnutChart(List<String> l_values, int width, int height) {
        return Chart_Doughnut_String(l_values, null, width, height);
    }

    public static String DoughnutChart(List<String> l_values, List<String> COLORS, int width, int height) {
        return Chart_Doughnut_String(l_values, COLORS, width, height);
    }

    private static String Chart_Doughnut_String(List<String> l_values, List<String> COLORS, int width, int height) {
        var id = newGUID(IDPattern.Long);
        StringBuilder s = new StringBuilder();
        s.append("<canvas id=\"" + id + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "			<script>" + "\r\n" +
                "				var doughnutData = [");
        s.append(Chart_Doughnut_Variable(l_values, COLORS));
        s.append("];" + "\r\n" +
                "					new Chart(document.getElementById(\"" + id + "\").getContext(\"2d\")).Doughnut(doughnutData);" + "\r\n" +
                "			  </script>");
        return s.toString();
    }

    private static String Chart_Doughnut_Variable(List<String> l_values, List<String> COLORS) {
        List<String> l = l_values;
        String s = "";
        String color;

        for (var i = 0; i <= l.size() - 1; i++) {
            if (COLORS != null) {
                color = COLORS.get(i);
            } else {
                color = Support.RandomColor(new ArrayList<Integer>());
            }
            s += "{" + "\r\n" +
                    "						value: " + l.get(i) + "," + "\r\n" +
                    "						color: \"" + color + "\"" + "\r\n" +
                    "				  }";
            if (i != l.size() - 1) {
                s += ",";
            }
        }
        return s;
    }


    /*
    Line
     */


    public static String LineChart(List<String> l_values, List<String> l_labels, int width, int height) {
        return Chart_Line_String(l_values, l_labels, width, height);
    }

    public static String LineChart(List<String> l_values, List<String> l_labels) {
        return Chart_Line_String(l_values, l_labels, 400, 300);
    }

    private static String Chart_Line_String(List<String> l_values, List<String> l_labels, int width, int height)
    {
        var id = newGUID(IDPattern.Long);
        String s = "<canvas id=\"" + id.toString() + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "			<script>" + "\r\n" +
                "				var lineChartData = {";
        s += Chart_Line_Variable(l_values, l_labels);
        s += "new Chart(document.getElementById(\"" + id.toString() + "\").getContext(\"2d\")).Line(lineChartData);" + "\r\n" +
                "			  </script>";
        return s;
    }

    private static String Chart_Line_Variable(List<String> l_values, List<String> l_labels) {
        StringBuilder l_string = new StringBuilder();
        l_string.append("labels : [");
        for (var i = 0; i <= l_labels.size() - 1; i++) {
            l_string.append("\"" + l_labels.get(i) + "\"");
            if (i != l_labels.size() - 1) {
                l_string.append(",");
            }
        }
        var fc = Support.RandomColor(new ArrayList<Integer>());
        var c = Support.RandomColor(new ArrayList<Integer>());
        l_string.append("],");

        StringBuilder v_string = new StringBuilder();
        v_string.append("datasets: [" + "\r\n" +
                "										{" + "\r\n" +
                "											fillColor: \"" + fc + "\"," + "\r\n" +
                "											strokeColor: \"" + c + "\"," + "\r\n" +
                "											pointColor: \"" + c + "\"," + "\r\n" +
                "											pointStrokeColor: \"#fff\"," + "\r\n" +
                "											data: [");
        for (var j = 0; j <= l_values.size() - 1; j++) {
            v_string.append(l_values.get(j));
            if (j != l_values.size() - 1) {
                v_string.append(",");
            }
        }
        v_string.append("]}]};");
        return new StringBuilder().append(l_string).append(v_string).toString();
    }

    /*
    Bar
     */

    /**
     * ToDo
     * @param x_labels
     * @param l_dataset
     * @return
     */
    private static String BarChart(List<String> x_labels, List<BarChartDataSet> l_dataset) {
        return Chart_Bar_String(x_labels, l_dataset, 400, 300);
    }

    /**
     * ToDo
     * @param x_labels
     * @param l_dataset
     * @param width
     * @param height
     * @return
     */
    private static String BarChart(List<String> x_labels, List<BarChartDataSet> l_dataset, int width, int height) {
        return Chart_Bar_String(x_labels, l_dataset, width, height);
    }

    private static String Chart_Bar_String(List<String> x_labels, List<BarChartDataSet> l_dataset, int width, int height) {
        var id = newGUID(IDPattern.Long);
        StringBuilder s = new StringBuilder();
        s.append("<canvas id=\"" + id + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "        <script>" + "\r\n" +
                "        	var barChartData = {");
        s.append(Chart_Bar_Variable(x_labels, l_dataset));
        s.append("};" + "\r\n" +
                "        		new Chart(document.getElementById(\"" + id + "\").getContext(\"2d\")).Bar(barChartData);" + "\r\n" +
                "          </script>");

        return s.toString();
    }

    private static String Chart_Bar_Variable(List<String> x_labels, List<BarChartDataSet> datasets) {
        StringBuilder s = new StringBuilder();
        s.append("labels: [");
        for (var i = 0; i <= x_labels.size() - 1; i++) {
            s.append("\"").append(x_labels.get(i)).append("\"");
            if (i != x_labels.size() - 1) {
                s.append(",");
            }
        }
        s.append("]," + "\r\n" +
                "                            datasets: [");

        for (var i = 0; i <= datasets.size() - 1; i++) {
            s.append("{fillColor: \"").append(datasets.get(i).rgb_color_string).append("\", data: [");
            for (var j = 0; j <= datasets.get(i).x_value_for_each_x_label.size() - 1; j++) {
                s.append(datasets.get(i).x_value_for_each_x_label.get(j));
                if (j != datasets.get(i).x_value_for_each_x_label.size() - 1) {
                    s.append(",");
                }
            }
            s.append("]}");
            if (i != datasets.size() - 1) {
                s.append(",");
            }
        }
        s.append("]");
        return s.toString();
    }


    public static class BarChartDataSet {
        public List<String> x_value_for_each_x_label;
        public String rgb_color_string;
        public String legend_value;

        public BarChartDataSet(List<String> x_value_for_each_x_label,String legend_value){
            this.x_value_for_each_x_label = x_value_for_each_x_label;
            this.rgb_color_string = Support.RandomColor(new ArrayList<Integer>());
            this.legend_value = legend_value;
        }
    }
/*
    public final class BarChartDataSet {
        public ArrayList<String> x_values_for_each_x_label;
        public String color_;
        public String legend_value;

        public BarChartDataSet clone() {
            BarChartDataSet varCopy = new BarChartDataSet();

            varCopy.x_values_for_each_x_label = this.x_values_for_each_x_label;
            varCopy.color_ = this.color_;
            varCopy.legend_value = this.legend_value;

            return varCopy;
        }
    }
*/

    public static class Support {
        public static String RandomColor(List<Integer> l) {
            int r = RandomList(0, 256, l);
            int g = RandomList(0, 256, l);
            int b = RandomList(0, 256, l);
            String return_ = "rgb(" + r + ", " + g + ", " + b + ")";
            return return_;
        }


        public static String RandomColor(List<Integer> l, byte alpha_) {
            byte a = alpha_;
            if (a < 0) {
                a = 0;
            }
            if (a > 1) {
                a = 1;
            }

            int r = RandomList(0, 256, l);
            int g = RandomList(0, 256, l);
            int b = RandomList(0, 256, l);
            String return_ = "rgba(" + r + ", " + g + ", " + b + ", " + a + ")";
            return return_;
        }

        public static int RandomList(int random_inclusive_min, int random_exclusive_max, java.util.List<Integer> already_) {
            return RandomList(random_inclusive_min, random_exclusive_max, already_, true);
        }

        public static int RandomList(int random_inclusive_min, int random_exclusive_max, List<Integer> already_, boolean recycle_) {
            var r_val = 0;
            if (already_.size() >= random_exclusive_max) {
                if (recycle_ == true) {
                    already_.clear();
                } else {
                    return already_.get(already_.size() - 1);
                }
            }

// 2:;
            try {
                r_val = Random_(random_inclusive_min, random_exclusive_max);
                // if (!already_.isEmpty() & already_.contains(r_val))
                // {
//ToDo: make unique
                // goto 2;
                //}
                // else
                // {
                already_.add(r_val);
                // 	return r_val;
                // }
            } catch (java.lang.Exception e) {
            }
            return r_val;
        }


        public static int Random_(int inclusive_min, int exclusive_max) {
            Random generator = new Random();
            int randomValue;
            randomValue = generator.nextInt(inclusive_min, exclusive_max);
            return randomValue;
        }

    }

}

