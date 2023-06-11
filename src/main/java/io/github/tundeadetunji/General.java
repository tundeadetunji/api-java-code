package io.github.tundeadetunji;

import java.text.SimpleDateFormat;
import java.util.*;

public class General {

    enum TextCase {
        Capitalize, UpperCase, LowerCase, None
    }

    enum IDPattern {
        Short, Short_DateOnly, Short_DateTime, Long, Long_DateTime
    }

    private enum AppendWith {
        Date, DateTime, Nothing
    }

    public enum FormatFor{
        Web, JavaScript, Custom, Nothing
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh-mm-ss-ms");

    public static String NewGUID(String prefix, AppendWith append_with_date_string, boolean truncate) {
        StringBuilder result = new StringBuilder();
        result.append(prefix.length() > 0 ? prefix : "");
        result.append(result.toString().length() > 0 ? "-" : "");
        if (append_with_date_string == AppendWith.DateTime) {
            result.append(DATE_FORMAT.format(new Date(System.currentTimeMillis())))
                    .append(TIME_FORMAT.format(new Date(System.currentTimeMillis()))).append("-");
        } else if (append_with_date_string == AppendWith.Date) {
            result.append(DATE_FORMAT.format(new Date(System.currentTimeMillis()))).append("-");
        }

        String id = UUID.randomUUID().toString();
        return result.append(truncate ? id.substring(0, 13) : id).toString();
    }

    public static String NewGUID(IDPattern pattern, String prefix) {
        String result = "";
        switch (pattern) {
            case Long -> {
                result = NewGUID(prefix, AppendWith.Nothing, false);
            }
            case Long_DateTime -> {
                result = NewGUID(prefix, AppendWith.DateTime, false);
            }
            case Short -> {
                result = NewGUID(prefix, AppendWith.Nothing, true);
            }
            case Short_DateOnly -> {
                result = NewGUID(prefix, AppendWith.Date, true);
            }
            case Short_DateTime -> {
                result = NewGUID(prefix, AppendWith.DateTime, true);
            }
        }
        return result;
    }

    public static String NewGUID(IDPattern pattern) {
        String result = "";
        switch (pattern) {
            case Long -> {
                result = NewGUID("", AppendWith.Nothing, false);
            }
            case Long_DateTime -> {
                result = NewGUID("", AppendWith.DateTime, false);
            }
            case Short -> {
                result = NewGUID("", AppendWith.Nothing, true);
            }
            case Short_DateOnly -> {
                result = NewGUID("", AppendWith.Date, true);
            }
            case Short_DateTime -> {
                result = NewGUID("", AppendWith.DateTime, true);
            }
        }
        return result;
    }

    public static String WriteHTML(String content, String title){
        return previewHTML(content, title);
    }

    public static String WriteHTML(String content){
        return previewHTML(content, "Preview");
    }

    public static String previewHTML(String content, String title){
        StringBuilder result = new StringBuilder().append(content.length() > 0 ? prepareForIO(content) : "");
        result.append("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>")
                .append(title)
                .append("</title><script type=\"application/x-javascript\">addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false);" +
                        "function hideURLbar(){ window.scrollTo(0,1); }</script><!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->" +
                        "<link href=\"css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
                .append("<!-- Custom Theme files --><link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" /><!--js-->" +
                        "<script src=\"js/jquery-2.1.1.min.js\"></script><!--icons-css--><!--<link href=\"css/font-awesome.css\" rel=\"stylesheet\" />-->" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/font-awesome-4.7.0/css/font-awesome.min.css\" />")
                .append("<!--Google Fonts--><link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css' />" +
                        "<link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css' />" +
                        "<!--//skycons-icons--><!--button css--><link href=\"css/demo-page.css\" rel=\"stylesheet\" media=\"all\" /><link href=\"css/hover.css\" " +
                        "rel=\"stylesheet\" media=\"all\" />")
                .append("<!--static chart--><script src=\"js/Chart.min.js\"></script><!--//charts-->" +
                        "<!-- geo chart<script src=\"//cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js\" type=\"text/javascript\"></script>" +
                        "rem removed--><script src=\"js/modernizr.min.js\"></script><!-- rem added  --><script src=\"lib/modernizr/modernizr-custom.js\">" +
                        "</script><!-- rem added  -->")
                .append("<script>\n" +
                        "        window.modernizr || document.write('<script src=\"lib/modernizr/modernizr-custom.js\"><\\/script>')\n" +
                        "    </script>\n" +
                        "\n" +
                        "    <!--<script src=\"lib/html5shiv/html5shiv.js\"></script>-->\n" +
                        "    <!-- Chartinator  -->\n" +
                        "    <script src=\"js/chartinator.js\"></script>\n" +
                        "    <!--geo chart-->\n" +
                        "\n" +
                        "    <!--skycons-icons-->\n" +
                        "    <script src=\"js/skycons.js\"></script>\n" +
                        "    <!--//skycons-icons-->\n" +
                        "    <!--mapcss-->\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/examples.css\" />\n" +
                        "    <!--js-->\n" +
                        "    <script type=\"text/javascript\" src=\"//maps.google.com/maps/api/js?sensor=true\"></script>\n" +
                        "    <script type=\"text/javascript\" src=\"js/gmaps.js\"></script>\n" +
                        "    <!--map-->\n" +
                        "    <!--pop up strat here-->\n" +
                        "    <script src=\"js/jquery.magnific-popup.js\" type=\"text/javascript\"></script>\n" +
                        "    <script>\n" +
                        "        $(document).ready(function () {\n" +
                        "            $('.popup-with-zoom-anim').magnificPopup({\n" +
                        "                type: 'inline',\n" +
                        "                fixedContentPos: false,\n" +
                        "                fixedBgPos: true,\n" +
                        "                overflowY: 'auto',\n" +
                        "                closeBtnInside: true,\n" +
                        "                preloader: false,\n" +
                        "                midClick: true,\n" +
                        "                removalDelay: 300,\n" +
                        "                mainClass: 'my-mfp-zoom-in'\n" +
                        "            });\n" +
                        "\n" +
                        "        });\n" +
                        "    </script>\n" +
                        "\n" +
                        "    <link rel=\"stylesheet\" href=\"css/inovation/_style.css\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\n" +
                        "    <div class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
                        "        <div class=\"container\">\n" +
                        "            <div class=\"navbar-header\">\n" +
                        "                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"\n" +
                        "                    title=\"more options\">\n" +
                        "                    <span class=\"icon-bar\"></span>\n" +
                        "                    <span class=\"icon-bar\"></span>\n" +
                        "                    <span class=\"icon-bar\"></span>\n" +
                        "                </button>\n" +
                        "                <a class=\"navbar-brand\" runat=\"server\" href=\"#\">\n" +
                        "                    Preview\n" +
                        "                </a>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "\n" +
                        "    <div class=\"container\" style=\"padding-top: 80px\">\n" +
                        "        <div class=\"row\">\n" +
                        "            <div class=\"col col-sm-12 col-md-2\">\n" +
                        "            </div>\n" +
                        "            <div class=\"col col-sm-12 col-md-8\">\"")
                .append(content)
                .append("</div><div class=\"col col-sm-12 col-md-2\"></div></div></div><!--scrolling js--><script src=\"js/jquery.nicescroll.js\"></script>" +
                        "<script src=\"js/scripts.js\"></script><!--//scrolling js--><script src=\"js/bootstrap.js\"></script>" +
                        "<script type=\"text/javascript\">function ScrollToDIV(div_) {div_.scrollIntoView({ behavior: 'smooth', block: 'center' });}</script>" +
                        "</body></html>");
        return result.toString();
    }

    public static String prepareForIO(String str_) {
        return prepareForIO(str_, FormatFor.Web);
    }

    public static String prepareForIO(String str_, FormatFor output_) {

        String trimmed_, CR_less, CRLFless, TABless;
        trimmed_ = str_.trim();
        str_ = trimmed_;

        if (output_ == FormatFor.Web) {
            CRLFless = str_.replace("\n", "<br />");
            str_ = CRLFless;
        } else if (output_ == FormatFor.JavaScript) {
            CRLFless = str_.replace("\n", "\n");
            str_ = CRLFless;
        }

        if (output_ == FormatFor.Web) {
            CR_less = str_.replace("\n", "<br />");
            str_ = CR_less;
        } else if (output_ == FormatFor.JavaScript) {
            CR_less = str_.replace("\n", "\n");
            str_ = CR_less;
        }

        if (output_ == FormatFor.Web) {
            TABless = str_.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
            str_ = TABless;
        } else if (output_ == FormatFor.JavaScript) {
            TABless = str_.replace("\t", "\t");
            str_ = TABless;
        }

        return str_;
    }

    public static String ListToString(List<String> list, String del){
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            r.append(list.get(i)).append(i < list.size() - 1 ? del : "");
        }
        return r.toString();
    }

    public static String ListToString(Object[] list, String del){
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            r.append(list[i]).append(i < list.length - 1 ? del : "");
        }
        return r.toString();
    }

    public static List<String> stringToList(String s, String del){
        List<String> result = new ArrayList<>();
        Arrays.stream(s.split(del)).forEach(i -> result.add(i));
        return result;
    }






}












