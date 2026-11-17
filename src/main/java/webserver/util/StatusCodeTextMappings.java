package webserver.util;

import java.util.HashMap;
import java.util.Map;

public class StatusCodeTextMappings {
    public static final Map<Integer, String> CODE_TEXT_MAPPINGS = new HashMap<>();

    static {
        CODE_TEXT_MAPPINGS.put(200, "OK");
        CODE_TEXT_MAPPINGS.put(201, "Created");
        CODE_TEXT_MAPPINGS.put(204, "No Content");

        CODE_TEXT_MAPPINGS.put(301, "Moved Permanently");
        CODE_TEXT_MAPPINGS.put(302, "Found");
        CODE_TEXT_MAPPINGS.put(304, "Not Modified");

        CODE_TEXT_MAPPINGS.put(400, "Bad Request");
        CODE_TEXT_MAPPINGS.put(401, "Unauthorized");
        CODE_TEXT_MAPPINGS.put(403, "Forbidden");
        CODE_TEXT_MAPPINGS.put(404, "Not Found");
        CODE_TEXT_MAPPINGS.put(405, "Method Not Allowed");
        CODE_TEXT_MAPPINGS.put(408, "Request Timeout");

        CODE_TEXT_MAPPINGS.put(500, "Internal Server Error");
        CODE_TEXT_MAPPINGS.put(501, "Not Implemented");
        CODE_TEXT_MAPPINGS.put(502, "Bad Gateway");
        CODE_TEXT_MAPPINGS.put(503, "Service Unavailable");
    }
}