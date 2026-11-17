package webserver.handler.postrequets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webserver.util.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static webserver.api.EndPointRegistry.postRoutes;

public class PostReqHandler {
    public static void postReqResponse(OutputStream out, String clientRequestInformation) {

        String[] responses = clientRequestInformation.split("\r\n");
        String contentType = "";

        for (String s : responses) {
            if (s.startsWith("Content-Type")) {
                contentType = s.split(" ")[1].trim();
            }
        }

        switch (contentType) {
            case "application/x-www-form-urlencoded":
                URLEncodedHandler urlEncodedHandler = new URLEncodedHandler(out, clientRequestInformation);
                HashMap<String, String> formData = urlEncodedHandler.handle();

                Function<HashMap<String, String>, String> endpoint = postRoutes.get("/submit");

                if (endpoint != null) {
                    String responseJson = endpoint.apply(formData);
                    JsonObject apiResponse = JsonParser.parseString(responseJson).getAsJsonObject();
                    HttpResponse response = new HttpResponse(out, 200, "application/json", responseJson.getBytes(), apiResponse);
                    try {
                        response.sendResponse();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    byte[] body;
                    try {
                        InputStream is = PostReqHandler.class.getResourceAsStream("/errors/404.html");
                        body = is.readAllBytes();

                        HttpResponse response = new HttpResponse(out, 404, "text/html", body);
                        response.sendResponse();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


                break;
            case "application/json":

        }


    }
}
