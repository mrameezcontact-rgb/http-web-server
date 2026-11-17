package webserver.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class EndPointRegistry {

    public static final Map<String, Supplier<String>> routes = new HashMap<>();
    public static Map<String, Function<HashMap<String,String>, String>> postRoutes = new HashMap<>();


    static {
        routes.put("/", GetEndPoints::Default);
        routes.put("/hello", GetEndPoints::sayHello);
        routes.put("/pakistan", GetEndPoints::sayPakistan);
        routes.put("/time", GetEndPoints::tellTime);
        routes.put("/all", GetEndPoints::allFormEntries);

        postRoutes.put("/submit", PostEndPoints::submitForm);
    }
}
