package io.github.tundeadetunji;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Functions relating to HTTP requests.
 */
public final class ServerSide {

    public static String Post(String resource, String json, String method) throws IOException {
        URL url = new URL(resource);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod(method.toUpperCase());
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = json;
        StringBuilder response = new StringBuilder();
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }

    public static String Post(String resource, String json) throws IOException {
        return Post(resource, json, "POST");
    }

    public static String peek(String url){
        String result = "";
        try {
            URL resource = new URL(url);
            URLConnection conn = resource.openConnection();
            InputStream is = conn.getInputStream();
            result = new String(is.readAllBytes());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
