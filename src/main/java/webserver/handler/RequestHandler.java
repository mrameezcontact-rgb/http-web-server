package webserver.handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static webserver.handler.getrequests.GetReqHandler.getReqResponse;
import static webserver.handler.postrequets.PostReqHandler.postReqResponse;

public class RequestHandler extends Thread {
    private final Socket sock;

    public RequestHandler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try {
            handle(sock.getInputStream(), sock.getOutputStream());
            sock.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void handle(InputStream in, OutputStream out) throws Exception {

        byte[] buffer = new byte[1024];
        int byteReads = in.read(buffer);

        if (byteReads == -1) {
            return;
        }

        String clientRequestInformation =
                new String(buffer, 0, byteReads);

        identifyAndProcessRequest(out, clientRequestInformation);
        out.flush();
    }

    private static void identifyAndProcessRequest(OutputStream out, String clientRequestInformation) {
        String requestLine = clientRequestInformation.split("\r\n")[0];

        // Parsing first Header
        String[] parts = requestLine.split(" ");
        String method = parts[0];
        String path = parts[1];

        System.out.println(method);
        System.out.println(path);

        switch (method) {
            case "GET":
                getReqResponse(out, path);
                break;
            case "POST":
                postReqResponse(out, clientRequestInformation);
                break;
        }

    }
}
