package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.GoogleUser;


public class GoogleUtils {
    public static String getToken(String code) throws IOException {
        String urlParameters = "code=" + code
                + "&client_id=80253236253-gqpog98kutfk87a0kaq0gh85otek81n3.apps.googleusercontent.com"
                + "&client_secret=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"
                + "&redirect_uri=http://localhost:8080/SWP391_M2_BL5_G3/Login"
                + "&grant_type=authorization_code";

        URL url = new URL("https://oauth2.googleapis.com/token");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(urlParameters.getBytes());
            os.flush();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // Parse JSON -> lấy access_token
        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
        return json.get("access_token").getAsString();
    }

    public static GoogleUser getUserInfo(String accessToken) throws IOException {
        String link = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
        GoogleUser user = new GoogleUser();
        user.setId(json.get("id").getAsString());
        user.setEmail(json.get("email").getAsString());
        user.setName(json.get("name").getAsString());
        user.setPicture(json.get("picture").getAsString());
        return user;
    }
}
