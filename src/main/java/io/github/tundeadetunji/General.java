package io.github.tundeadetunji;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class General {

    public enum IDPattern {
        Short, Short_DateOnly, Short_DateTime, Long, Long_DateTime
    }

    public enum CommitDataTo {
        CSV, Sequel, Access, Excel, Hibernate
    }

    private enum AppendWith {
        Date, DateTime, Nothing
    }

    public enum FormatFor {
        Web, JavaScript, Custom, Nothing
    }

    public enum SideToReturn {
        Left, Right, AsArray, AsListOfString, AsListToString, AsCustomApplicationInfo, AsCustomApplicationInfoDisplayName, AsCustomApplicationInfoProcessName, AsCustomApplicationInfoFilename, AsCustomApplicationInfoInstallLocation
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh-mm-ss-ms");

    /**
     * Constructs a GUID.
     *
     * @param prefix                  what to precede the final string with
     * @param append_with_date_string add a transformed date string to the final string
     * @param truncate                grabs the first 13 characters only
     * @return string of transformed GUID
     */
    public static String newGUID(String prefix, AppendWith append_with_date_string, boolean truncate) {
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

    /**
     * Constructs a GUID with a given pattern.
     *
     * @param pattern what pattern to construct the final string with
     * @param prefix  what to precede the final string with
     * @return string of transformed GUID
     */
    public static String newGUID(IDPattern pattern, String prefix) {
        String result = "";
        switch (pattern) {
            case Long:
                result = newGUID(prefix, AppendWith.Nothing, false);
                break;
            case Long_DateTime:
                result = newGUID(prefix, AppendWith.DateTime, false);
                break;
            case Short:
                result = newGUID(prefix, AppendWith.Nothing, true);
                break;
            case Short_DateOnly:
                result = newGUID(prefix, AppendWith.Date, true);
                break;
            case Short_DateTime:
                result = newGUID(prefix, AppendWith.DateTime, true);
                break;
        }
        return result;
    }

    /**
     * Constructs a GUID with a given pattern.
     *
     * @param pattern what pattern to construct the final string with
     * @return string of transformed GUID
     */
    public static String newGUID(IDPattern pattern) {
        String result = "";
        switch (pattern) {
            case Long:
                result = newGUID("", AppendWith.Nothing, false);
                break;
            case Long_DateTime:
                result = newGUID("", AppendWith.DateTime, false);
                break;
            case Short:
                result = newGUID("", AppendWith.Nothing, true);
                break;
            case Short_DateOnly:
                result = newGUID("", AppendWith.Date, true);
                break;
            case Short_DateTime:
                result = newGUID("", AppendWith.DateTime, true);
                break;
        }
        return result;
    }

    /**
     * Constructs a custom HTML snippet, intended to be for preview only.
     * The CSS and JS are based on Shoppy's resources, but defaults to Bootstrap's in syntax.
     *
     * @param content this will represent the bulk of the body section of the final markup snippet
     * @param title   the title of the page which will be placed in the title tag of the markup snippet
     * @return HTML that can be placed in HTML file directly to create a (default custom) web page
     */
    public static String writeHtml(String content, String title) {
        return previewHtml(content, title);
    }

    /**
     * Constructs a custom HTML snippet, intended to be for preview only.
     * The CSS and JS are based on Shoppy's resources, but defaults to Bootstrap's
     * in the absence of Shoppy's.
     *
     * @param content this will represent the bulk of the body section of the final markup snippet
     * @return HTML that can be placed in HTML file directly to create a (default custom) web page
     */
    public static String writeHtml(String content) {
        return previewHtml(content, "Preview");
    }

    /**
     * Custom HTML snippet, intended to be for preview only.
     * The CSS and JS are based on Shoppy's resources, but defaults to Bootstrap's
     * in the absence of Shoppy's.
     *
     * @param content this will represent the bulk of the body section of the final markup snippet
     * @param title   the title of the page which will be placed in the title tag of the markup snippet
     * @return HTML that can be placed in HTML file directly to create a (default custom) web page
     */
    public static String previewHtml(String content, String title) {

        String result = "<!DOCTYPE html>" + "\r\n" +
                "<html lang=\"en\">" + "\r\n" +
                "\r\n" +
                "<head>" + "\r\n" +
                "    <meta charset=\"UTF-8\">" + "\r\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" + "\r\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" + "\r\n" +
                "    <title>" + title + "</title>" + "\r\n" +
                "\r\n" +
                "    <script type=\"application/x-javascript\">" + "\r\n" +
                "		addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }" + "\r\n" +
                "        </script>" + "\r\n" +
                "    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->" + "\r\n" +
                "    <link href=\"css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />" + "\r\n" +
                "    <!-- Custom Theme files -->" + "\r\n" +
                "    <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />" + "\r\n" +
                "    <!--js-->" + "\r\n" +
                "    <script src=\"js/jquery-2.1.1.min.js\"></script>" + "\r\n" +
                "    <!--icons-css-->" + "\r\n" +
                "    <!--<link href=\"css/font-awesome.css\" rel=\"stylesheet\" />-->" + "\r\n" +
                "\r\n" +
                "\r\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/font-awesome-4.7.0/css/font-awesome.min.css\" />" + "\r\n" +
                "\r\n" +
                "    <!--Google Fonts-->" + "\r\n" +
                "    <link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css' />" + "\r\n" +
                "    <link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css' />" + "\r\n" +
                "    <!--//skycons-icons-->" + "\r\n" +
                "    <!--button css-->" + "\r\n" +
                "    <link href=\"css/demo-page.css\" rel=\"stylesheet\" media=\"all\" />" + "\r\n" +
                "    <link href=\"css/hover.css\" rel=\"stylesheet\" media=\"all\" />" + "\r\n" +
                "    <!--static chart-->" + "\r\n" +
                "    <script src=\"js/Chart.min.js\"></script>" + "\r\n" +
                "    <!--//charts-->" + "\r\n" +
                "    <!-- geo chart" + "\r\n" +
                "	<script src=\"//cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js\" type=\"text/javascript\"></script>" + "\r\n" +
                "	rem removed-->" + "\r\n" +
                "    <script src=\"js/modernizr.min.js\"></script>" + "\r\n" +
                "    <!-- rem added  -->" + "\r\n" +
                "\r\n" +
                "    <script src=\"lib/modernizr/modernizr-custom.js\"></script>" + "\r\n" +
                "    <!-- rem added  -->" + "\r\n" +
                "    " + "\r\n" +
                "    " + "\r\n" +
                "    " + "\r\n" +
                "    " + "\r\n" +
                "    " + "\r\n" +
                "    <script>" + "\r\n" +
                "        window.modernizr || document.write('<script src=\"lib/modernizr/modernizr-custom.js\"><\\/script>')" + "\r\n" +
                "    </script>" + "\r\n" +
                "\r\n" +
                "    <!--<script src=\"lib/html5shiv/html5shiv.js\"></script>-->" + "\r\n" +
                "    <!-- Chartinator  -->" + "\r\n" +
                "    <script src=\"js/chartinator.js\"></script>" + "\r\n" +
                "    <!--geo chart-->" + "\r\n" +
                "\r\n" +
                "    <!--skycons-icons-->" + "\r\n" +
                "    <script src=\"js/skycons.js\"></script>" + "\r\n" +
                "    <!--//skycons-icons-->" + "\r\n" +
                "    <!--mapcss-->" + "\r\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/examples.css\" />" + "\r\n" +
                "    <!--js-->" + "\r\n" +
                "    <script type=\"text/javascript\" src=\"//maps.google.com/maps/api/js?sensor=true\"></script>" + "\r\n" +
                "    <script type=\"text/javascript\" src=\"js/gmaps.js\"></script>" + "\r\n" +
                "    <!--map-->" + "\r\n" +
                "    <!--pop up strat here-->" + "\r\n" +
                "    <script src=\"js/jquery.magnific-popup.js\" type=\"text/javascript\"></script>" + "\r\n" +
                "    <script>" + "\r\n" +
                "        $(document).ready(function () {" + "\r\n" +
                "            $('.popup-with-zoom-anim').magnificPopup({" + "\r\n" +
                "                type: 'inline'," + "\r\n" +
                "                fixedContentPos: false," + "\r\n" +
                "                fixedBgPos: true," + "\r\n" +
                "                overflowY: 'auto'," + "\r\n" +
                "                closeBtnInside: true," + "\r\n" +
                "                preloader: false," + "\r\n" +
                "                midClick: true," + "\r\n" +
                "                removalDelay: 300," + "\r\n" +
                "                mainClass: 'my-mfp-zoom-in'" + "\r\n" +
                "            });" + "\r\n" +
                "\r\n" +
                "        });" + "\r\n" +
                "    </script>" + "\r\n" +
                "\r\n" +
                "</head>" + "\r\n" +
                "\r\n" +
                "<body>" + "\r\n" +
                "\r\n" +
                "    <div class=\"navbar navbar-inverse navbar-fixed-top\">" + "\r\n" +
                "        <div class=\"container\">" + "\r\n" +
                "            <div class=\"navbar-header\">" + "\r\n" +
                "                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"" + "\r\n" +
                "                    title=\"more options\">" + "\r\n" +
                "                    <span class=\"icon-bar\"></span>" + "\r\n" +
                "                    <span class=\"icon-bar\"></span>" + "\r\n" +
                "                    <span class=\"icon-bar\"></span>" + "\r\n" +
                "                </button>" + "\r\n" +
                "                <a class=\"navbar-brand\" runat=\"server\" href=\"#\">" + "\r\n" +
                "                    Preview" + "\r\n" +
                "                </a>" + "\r\n" +
                "            </div>" + "\r\n" +
                "        </div>" + "\r\n" +
                "    </div>" + "\r\n" +
                "\r\n" +
                "    <div class=\"container\" style=\"padding-top: 80px\">" + "\r\n" +
                "        <div class=\"row\">" + "\r\n" +
                "            <div class=\"col col-sm-12 col-md-2\">" + "\r\n" +
                "            </div>" + "\r\n" +
                "            <div class=\"col col-sm-12 col-md-8\">" + content + "</div>" + "\r\n" +
                "            <div class=\"col col-sm-12 col-md-2\">" + "\r\n" +
                "            </div>" + "\r\n" +
                "\r\n" +
                "        </div>" + "\r\n" +
                "    </div>" + "\r\n" +
                "\r\n" +
                "\r\n" +
                "\r\n" +
                "    <!--scrolling js-->" + "\r\n" +
                "    <script src=\"js/jquery.nicescroll.js\"></script>" + "\r\n" +
                "    <script src=\"js/scripts.js\"></script>" + "\r\n" +
                "    <!--//scrolling js-->" + "\r\n" +
                "    <script src=\"js/bootstrap.js\"></script>" + "\r\n" +
                "    <script type=\"text/javascript\">" + "\r\n" +
                "        function ScrollToDIV(div_) {" + "\r\n" +
                "            div_.scrollIntoView({ behavior: 'smooth', block: 'center' });" + "\r\n" +
                "        }" + "\r\n" +
                "    </script>" + "\r\n" +
                "</body>" + "\r\n" +
                "\r\n" +
                "</html>";

        return result;
    }

    /**
     * Custom HTML snippet, intended to be for preview only.
     * The CSS and JS are based on Shoppy's resources, but defaults to Bootstrap's
     * in the absence of Shoppy's.
     *
     * @param content this will represent the bulk of the body section of the final markup snippet
     * @return HTML that can be placed in HTML file directly to create a (default custom) web page
     */
    public static String previewHtml(String content)
    {
        return previewHtml(content, "Preview");
    }

    /*private static String replaceNewLineCharacterWithHtmlEquivalent(String content) {
        return content.replace("\n", "<br />");
    }*/

    /**
     * Transforms a string by replacing escape characters (carriage return, tab, etc.) to
     * the appropriate characters depending on the intended output (defaults to web page).
     *
     * @param str_ the string to transform
     * @return string that is safe for direct use for the intended output
     */
    public static String prepareForIo(String str_) {
        return prepareForIO(str_, FormatFor.Web);
    }

    /**
     * Transforms a string by attempting to replace escape characters (carriage return, tab, etc.) to
     * the appropriate characters depending on the intended output specified by output_.
     *
     * @param str_    the string to transform
     * @param output_ the intended output
     * @return string that is safe for direct use for the intended output
     */
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

    /**
     * Combines a list of strings to form a single string joined with the characters specified by del.
     *
     * @param list the list to join together
     * @param del  what to join the strings with
     * @return a string that combines all the items in the original list
     */
    public static String listToString(List<String> list, String del) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            r.append(list.get(i)).append(i < list.size() - 1 ? del : "");
        }
        return r.toString();
    }

    /**
     * Splits a string into tokens, each of which represents the string split along the characters specified
     * by delimiter.
     *
     * @param string    the string to split into tokens
     * @param delimiter what to split the string along
     * @return a list of strings
     */
    public static List<String> stringToList(String string, String delimiter) {
        return List.of(string.split(delimiter));
    }

    /**
     * Splits a string into tokens, each of which represents the string split along carriage return character.
     *
     * @param string the string to split into tokens
     * @return a list of strings
     */
    public static List<String> stringToList(String string) {
        return List.of(string.split("\n"));
    }

    /**
     * Splits a string into tokens, each of which represents the string split along what is specified from delimiter.
     *
     * @param string    the string to split into tokens
     * @param delimiter what to split the string along
     * @return a list of strings
     */
    public static List<String> splitTextInSplits(String string, String delimiter) {
        return List.of(string.split(delimiter));
    }

    /**
     * Splits a string into tokens, each of which represents the string split along what is specified from delimiter.
     *
     * @param string    the string to split into tokens
     * @param delimiter what to split the string along
     * @param how_many  how many tokens to return
     * @return a list of strings
     */
    public static List<String> splitTextInSplits(String string, String delimiter, int how_many) {
        return List.of(string.split(delimiter, how_many));
    }

    /**
     * Splits a string into 2 tokens, each of which represents the string split along what is specified from delimiter.
     *
     * @param string       the string to split into tokens
     * @param delimiter    what to split the string along
     * @param sideToReturn should it return the first token or the last token
     * @return a string that is the first or last token
     */
    public static String splitTextInTwo(String string, String delimiter, SideToReturn sideToReturn) {
        String[] splits = string.split(delimiter, 2);
        return sideToReturn == SideToReturn.Left ? splits[0] : splits[1];
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    public static boolean isEmail(String email){
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static String removeHtmlFromText(String text){
        return text.replaceAll("(<.*?>)|(&.*?;)|([ ]{2,})", "").replaceAll("(<.*?>)|(&.*?;)", " ").replaceAll("\\s{2,}", " ");
    }
}