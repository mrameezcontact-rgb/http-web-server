package webserver.handler.getrequests;

import java.io.OutputStream;

public class GetReqHandler {

    public static void getReqResponse(OutputStream out, String path) {
        boolean isResourceReq = path.contains(".");

        if (isResourceReq) {
            StaticFileHandler handler = new StaticFileHandler(out, path);
            handler.handle();
        } else {
            APIHandler handler = new APIHandler(out, path);
            handler.handle();
        }

    }
}
