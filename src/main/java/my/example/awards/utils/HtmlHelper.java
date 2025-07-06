package my.example.awards.utils;

public class HtmlHelper {

    public static String generateHtmlLinks(String path, String... endpoints) {
        StringBuilder builder = new StringBuilder();
        builder.append("Try this URL for ").append(path).append(":<br><br>\n");

        for (String e : endpoints) {
            builder.append("<a href=\"").append(path).append("/").append(e).append("\">")
                    .append(path).append("/").append(e).append("</a><br>\n");
        }

        return builder.toString();
    }

    public static String generateHtmlLinksForHello(String... endpoints) {
        StringBuilder builder = new StringBuilder();
        builder.append("Try this URL:<br><br>\n");

        for (String e : endpoints) {
            builder.append("<a href=\"").append(e).append("\">").append(e).append("</a><br>\n");
        }

        return builder.toString();
    }
}
