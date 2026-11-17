package webserver.handler.getrequests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webserver.api.EndPointRegistry;
import webserver.util.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class APIHandler {

    private final OutputStream out;
    private final String path;

    public APIHandler(OutputStream out, String path) {
        this.out = out;
        this.path = path;
    }

    public void handle() {
        Supplier<String> handler = EndPointRegistry.routes.get(path);

        String responseBody = "", contentType = "application/json";
        int statusCode = 200;

        InputStream is = null;

        boolean isError = false;

        if (handler != null) {
            responseBody = handler.get();
        } else {
            isError = true;
            statusCode = 404;
            contentType = "text/html";
            is = getClass().getResourceAsStream("/errors/404.html");
        }

        try {
            if (isError) {
                HttpResponse response = new HttpResponse(out, statusCode, contentType, is.readAllBytes());
                response.sendResponse();
            } else {
                JsonObject apiResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                HttpResponse response = new HttpResponse(out, statusCode, contentType, responseBody.getBytes(), apiResponse);
                response.sendResponse();
            }
        } catch (IOException e) {
            System.out.println("[ERROR]: getHandler.apiHandler Error.");
        }
    }
}
