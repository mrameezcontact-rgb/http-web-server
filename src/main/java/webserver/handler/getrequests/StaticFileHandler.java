package webserver.handler.getrequests;

import webserver.util.HttpResponse;
import webserver.util.MimeTypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticFileHandler {

    private final OutputStream out;
    private final String path;

    public StaticFileHandler(OutputStream out, String path) {
        this.out = out;
        this.path = path;
    }

    public void handle() {

        InputStream isResource = getClass().getResourceAsStream(path);
        byte[] body;

        int statusCode = 200;
        String contentType = "text/plain";

        try {
            body = isResource.readAllBytes();

            String fileName = path.substring(path.lastIndexOf("/") + 1);
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1) {
                String ext = fileName.substring(dotIndex + 1);
                contentType = MimeTypes.MIME_TYPES.get(ext);
            }

        } catch (NullPointerException | IOException e) {
            statusCode = 404;

            try {
                InputStream is = getClass().getResourceAsStream("/errors/404.html");
                body = is.readAllBytes();

                contentType = "text/html";
            } catch (IOException ex) {
                body = "<h1>404 Not Found</h1>".getBytes();
                contentType = "text/html";
            }
        }

        try {
            HttpResponse response = new HttpResponse(out, statusCode, contentType, body);
            response.sendResponse();

        } catch (IOException e) {
            System.out.println("[ERROR]: StaticFileHandler handle() error.");
        }
    }
}
