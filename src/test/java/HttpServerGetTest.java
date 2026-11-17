import org.junit.jupiter.api.Test;
import webserver.handler.RequestHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpServerGetTest {

    @Test
    void shouldReturn200ForRootGetRequest() throws Exception {
        String request = "GET / HTTP/1.1\r\n" + "Host: localhost\r\n" + "\r\n";

        InputStream in = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        RequestHandler handler = new RequestHandler(null);
        handler.handle(in, out);

        String response = out.toString();

        assertTrue(response.startsWith("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Content-Type: application/json"));
    }

    @Test
    void shouldReturn404ForUnknownRoutes() throws Exception {
        String request = "GET /invalid HTTP/1.1\r\n" + "Host: localhost\r\n" + "\r\n";

        InputStream in = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        RequestHandler handler = new RequestHandler(null);
        handler.handle(in, out);

        String response = out.toString();

        assertTrue(response.startsWith("HTTP/1.1 404 Not Found"));

//        text/html because sending back client a custom 404 page
        assertTrue(response.contains("Content-Type: text/html"));
    }
}