package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpStatusChecker {

    private HttpStatusChecker() {
    }

    private static final String BASIC_URL = "https://http.cat";
    private static OkHttpClient client = new OkHttpClient();
    private static final Logger logger = Logger.getLogger(HttpStatusChecker.class.getName());

    public static String getStatusImage(int code) {

        String url = BASIC_URL + "/" + code + ".jpg";

        Request request = new Request.Builder().url(url).head().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.log(Level.WARNING,"There is not image for HTTP status " + code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}