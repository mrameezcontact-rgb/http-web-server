package webserver.api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class PostEndPoints {

    public static String submitForm(HashMap<String, String> map) {
        System.out.println(map);

        String fileName = "post_data.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (HashMap.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + " = " + entry.getValue());
                writer.newLine();
            }
            writer.write("----");
            writer.newLine();
        } catch (IOException e) {
            return "{\"status\": \"Error writing to file.\"}";
        }

        return "{\"status\": \"Data saved successfully!\"}";
    }
}