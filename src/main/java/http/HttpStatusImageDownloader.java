package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

public class HttpStatusImageDownloader {

    private static Logger logger = Logger.getLogger(HttpStatusImageDownloader.class.getName());
    private static final OkHttpClient client = new OkHttpClient();

    private HttpStatusImageDownloader(){}

    public static void downloadStatusImage(int code) {

        String imageUrl = HttpStatusChecker.getStatusImage(code);
        String imageName = "cat." + code + ".jpg";

        Request request = new Request.Builder().url(imageUrl).build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if(response.isSuccessful()) {
                download(imageName, responseBody);
                logger.info("Image " + code + " downloaded");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void download(String name, ResponseBody responseBody) {
        try (InputStream inputStream = responseBody.byteStream()) {
            Files.copy(inputStream, Paths.get("images",name), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}