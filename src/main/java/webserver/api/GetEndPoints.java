package webserver.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetEndPoints {

    public static String Default() {
        return "{\"status\": \"I am ready to entertain your all requests.\"}";
    }

    public static String sayHello() {
        return "{\"status\": \"hello\"}";
    }

    public static String sayPakistan() {
        return "{\"status\": \"Pakistan\"}";
    }

    public static String tellTime() {
        String currentTime = String.valueOf(LocalTime.now());
        return "{\"status\": \"" + currentTime + "\"}";
    }

    public static String allFormEntries() {

        String fileName = "post_data.txt";
        List<Map<String,String>> entries = new ArrayList<>();
        Map<String, String> currentEntry = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.equals("----")) {
                    // end of one submission
                    entries.add(new HashMap<>(currentEntry));
                    currentEntry.clear();
                } else {
                    String[] parts = line.split(" = ", 2);
                    if (parts.length == 2) {
                        currentEntry.put(parts[0], parts[1]);
                    }
                }
            }

        } catch (IOException e) {
            return "{\"status\":\"error\",\"message\":\"Unable to read file\"}";
        }

        // Build JSON manually
        StringBuilder json = new StringBuilder();
        json.append("{\"entries\":[");

        for (int i = 0; i < entries.size(); i++) {
            Map<String, String> entry = entries.get(i);
            json.append("{");

            int j = 0;
            for (Map.Entry<String, String> field : entry.entrySet()) {
                json.append("\"")
                        .append(field.getKey())
                        .append("\":\"")
                        .append(field.getValue())
                        .append("\"");

                if (j < entry.size() - 1) json.append(",");
                j++;
            }

            json.append("}");
            if (i < entries.size() - 1) json.append(",");
        }

        json.append("]}");
        return json.toString();
    }
}
