package io.github.tundeadetunji;

import java.util.ArrayList;
import java.util.Random;

import static io.github.tundeadetunji.General.newGUID;
import static io.github.tundeadetunji.General.*;

public class Charts {

    /*
    Pie
     */


    public static String PieChart(ArrayList<String> l_values, ArrayList<String> l_colors)
    {
        return Chart_Pie_String(l_values, l_colors, 470, 300);
    }
    public static String PieChart(ArrayList<String> l_values)
    {
        return Chart_Pie_String(l_values, null, 470, 300);
    }
    public static String PieChart(ArrayList<String> l_values, ArrayList<String> l_colors, int width, int height)
    {
        return Chart_Pie_String(l_values, l_colors, width, height);
    }
    public static String PieChart(ArrayList<String> l_values, int width, int height)
    {
        return Chart_Pie_String(l_values, null, width, height);
    }

    private static String Chart_Pie_String(ArrayList<String> l_values, ArrayList<String> COLORS, int width, int height)
    {
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

    private static String Chart_Pie_Variable(ArrayList<String> l_values, ArrayList<String> COLORS)
    {
        ArrayList<String> l = l_values;
        String s = "";
        String color;

        for (var i = 0; i <= l.size() - 1; i++)
        {
            if (COLORS != null)
            {
                color = COLORS.get(i);
            }
            else
            {
                color = Support.RandomColor(new ArrayList<Integer>());
            }
            s += "{" + "\r\n" +
                    "						value: " + l.get(i) + "," + "\r\n" +
                    "						color: \"" + color + "\"" + "\r\n" +
                    "				  }";
            if (i != l.size() - 1)
            {
                s += ",";
            }
        }
        return s;
    }




    /*
    Doughnut
     */

    public static String DoughnutChart(ArrayList<String> l_values)
    {
        return Chart_Doughnut_String(l_values, null, 470, 300);
    }

    public static String DoughnutChart(ArrayList<String> l_values, ArrayList<String> COLORS)
    {
        return Chart_Doughnut_String(l_values, COLORS, 470, 300);
    }

    public static String DoughnutChart(ArrayList<String> l_values, int width, int height)
    {
        return Chart_Doughnut_String(l_values, null, width, height);
    }

    public static String DoughnutChart(ArrayList<String> l_values, ArrayList<String> COLORS, int width, int height)
    {
        return Chart_Doughnut_String(l_values, COLORS, width, height);
    }

    private static String Chart_Doughnut_String(ArrayList<String> l_values, ArrayList<String> COLORS, int width, int height)
    {
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
    private static String Chart_Doughnut_Variable(ArrayList<String> l_values, ArrayList<String> COLORS)
    {
        ArrayList<String> l = l_values;
        String s = "";
        String color;

        for (var i = 0; i <= l.size() - 1; i++)
        {
            if (COLORS != null)
            {
                color = COLORS.get(i);
            }
            else
            {
                color = Support.RandomColor(new ArrayList<Integer>());
            }
            s += "{" + "\r\n" +
                    "						value: " + l.get(i) + "," + "\r\n" +
                    "						color: \"" + color + "\"" + "\r\n" +
                    "				  }";
            if (i != l.size() - 1)
            {
                s += ",";
            }
        }
        return s;
    }


    /*
    Line
     */


    public static String LineChart(ArrayList<String> l_values, ArrayList<String> l_labels, int width, int height)
    {
        return Chart_Line_String(l_values, l_labels, width, height);
    }
    public static String LineChart(ArrayList<String> l_values, ArrayList<String> l_labels)
    {
        return Chart_Line_String(l_values, l_labels, 400, 300);
    }
    private static String Chart_Line_String(ArrayList<String> l_values, ArrayList<String> l_labels, int width, int height)
    {
        var id = newGUID(IDPattern.Long);
        StringBuilder s = new StringBuilder();
         s.append(id + "\" width=\"" + width + "\" height=\"" + height + "\" style=\"width: " + width + "px; height: " + height + "px\"></canvas>" + "\r\n" +
                "			<script>" + "\r\n" +
                "				var lineChartData = {");
        s.append(Chart_Line_Variable(l_values, l_labels));
        s.append("new Chart(document.getElementById(\"" + id + "\").getContext(\"2d\")).Line(lineChartData);" + "\r\n" +
                "			  </script>");
        return s.toString();
    }
    private static String Chart_Line_Variable(ArrayList<String> l_values, ArrayList<String> l_labels)
    {
        String l_string = "labels : [";
        for (var i = 0; i <= l_labels.size() - 1; i++)
        {
            l_string += "\"" + l_labels.get(i) + "\"";
            if (i != l_labels.size() - 1)
            {
                l_string += ",";
            }
        }
        var fc = Support.RandomColor(new ArrayList<Integer>());
        var c = Support.RandomColor(new ArrayList<Integer>());
        l_string += "],";

        String v_string = "datasets: [" + "\r\n" +
                "										{" + "\r\n" +
                "											fillColor: \"" + fc + "\"," + "\r\n" +
                "											strokeColor: \"" + c + "\"," + "\r\n" +
                "											pointColor: \"" + c + "\"," + "\r\n" +
                "											pointStrokeColor: \"#fff\"," + "\r\n" +
                "											data: [";
        for (var j = 0; j <= l_values.size() - 1; j++)
        {
            v_string += l_values.get(j);
            if (j != l_values.size() - 1)
            {
                v_string += ",";
            }
        }
        v_string += "]}]};";
        return l_string + v_string;
    }

    /*
    Bar
     */

    public static String BarChart(ArrayList<String> x_labels, ArrayList<BarChartDataSet> l_dataset)
    {
        return Chart_Bar_String(x_labels, l_dataset, 400, 300);
    }

    public static String BarChart(ArrayList<String> x_labels, ArrayList<BarChartDataSet> l_dataset, int width, int height)
    {
        return Chart_Bar_String(x_labels, l_dataset, width, height);
    }

    private static String Chart_Bar_String(ArrayList<String> x_labels, ArrayList<BarChartDataSet> l_dataset, int width, int height)
    {
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
    private static String Chart_Bar_Variable(ArrayList<String> x_labels, ArrayList<BarChartDataSet> datasets)
    {
        StringBuilder s = new StringBuilder();
         s.append("labels: [");
        for (var i = 0; i <= x_labels.size() - 1; i++)
        {
            s.append("\"").append(x_labels.get(i)).append("\"");
            if (i != x_labels.size() - 1)
            {
                s.append(",");
            }
        }
        s.append("]," + "\r\n" +
                "                            datasets: [");

        for (var i = 0; i <= datasets.size() - 1; i++)
        {
            s.append("{fillColor: \"").append(datasets.get(i).color_).append("\", data: [");
            for (var j = 0; j <= datasets.get(i).x_values_for_each_x_label.size() - 1; j++)
            {
                s.append(datasets.get(i).x_values_for_each_x_label.get(j));
                if (j != datasets.get(i).x_values_for_each_x_label.size() - 1)
                {
                    s.append(",");
                }
            }
            s.append("]}");
            if (i != datasets.size() - 1)
            {
                s.append(",");
            }
        }
        s.append("]");
        return s.toString();
    }



    public final class BarChartDataSet
    {
        public ArrayList<String> x_values_for_each_x_label;
        public String color_;
        public String legend_value;

        public BarChartDataSet clone()
        {
            BarChartDataSet varCopy = new BarChartDataSet();

            varCopy.x_values_for_each_x_label = this.x_values_for_each_x_label;
            varCopy.color_ = this.color_;
            varCopy.legend_value = this.legend_value;

            return varCopy;
        }
    }

    public static final class Support {
        public static String RandomColor(ArrayList<Integer> l) {
            int r = RandomList(0, 256, l);
            int g = RandomList(0, 256, l);
            int b = RandomList(0, 256, l);
            String return_ = "rgb(" + r + ", " + g + ", " + b + ")";
            return return_;
        }


        public static String RandomColor(ArrayList<Integer> l, byte alpha_) {
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

        public static int RandomList(int random_inclusive_min, int random_exclusive_max, java.util.ArrayList<Integer> already_) {
            return RandomList(random_inclusive_min, random_exclusive_max, already_, true);
        }

        public static int RandomList(int random_inclusive_min, int random_exclusive_max, ArrayList<Integer> already_, boolean recycle_) {
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


        public static int Random_(int inclusive_min, int exclusive_max)
        {
            Random generator = new Random();
            int randomValue;
            randomValue = generator.nextInt(inclusive_min, exclusive_max);
            return randomValue;
        }

    }

}

