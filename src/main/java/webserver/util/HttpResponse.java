package webserver.util;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;

import static webserver.util.StatusCodeTextMappings.CODE_TEXT_MAPPINGS;

public class HttpResponse {

    private final OutputStream out;
    private final int statusCode;
    private final String statusText;
    private final String contentType;
    private final byte[] body;
    private JsonObject apiResponse = null;

    public HttpResponse(OutputStream out, int statusCode, String contentType, byte[] body) {
        this.out = out;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
        this.statusText = getStatusTextByStatusCode();
    }

    public HttpResponse(OutputStream out, int statusCode, String contentType, byte[] body, JsonObject apiResponse) {
        this.out = out;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
        this.statusText = getStatusTextByStatusCode();
        this.apiResponse = apiResponse;
    }

    public void sendResponse() throws IOException {
        String headers =
                "HTTP/1.1 " + this.statusCode + " " + this.statusText + "\r\n" +
                        "Content-Type: " + this.contentType + "\r\n" +
                        "Content-Length: " + this.body.length + "\r\n";

        if (apiResponse != null) {
            headers = headers.concat(apiResponse + "\r\n");
        }

        headers = headers.concat("\r\n");

        out.write(headers.getBytes());
        out.write(body);
        out.flush();
    }

    private String getStatusTextByStatusCode() {
        return CODE_TEXT_MAPPINGS.get(this.statusCode);
    }

}