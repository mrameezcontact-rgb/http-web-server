package webserver.handler.postrequets;

import java.io.OutputStream;
import java.util.HashMap;

public class URLEncodedHandler {

    private final OutputStream out;
    private final String clientRequestInformation;

    public URLEncodedHandler(OutputStream out, String clientRequestInformation) {
        this.out = out;
        this.clientRequestInformation = clientRequestInformation;
    }

    public HashMap<String, String> handle() {

        HashMap<String, String> formData = new HashMap<>();

        String[] responses = this.clientRequestInformation.split("\r\n");
        String payload = responses[responses.length - 1];
        String[] body = payload.split("&");

        for (int i = 0; i < body.length; i++) {
            body[i] = body[i].replaceAll("\\+", " ");

            if (body[i].contains("%")) {
                int percentageCount = 0;
                for (Character c : body[i].toCharArray()) {
                    if (c == '%') {
                        percentageCount++;
                    }
                }
                while (percentageCount != 0) {
                    int percentageIndex = body[i].indexOf('%');
                    String code = body[i].substring(percentageIndex + 1, percentageIndex + 3);
                    Character codeReplacer = decoder(code);
                    body[i] = body[i].substring(0, percentageIndex) + codeReplacer + body[i].substring(percentageIndex + 3);

                    percentageCount--;
                }
            }
        }

        for (String entry : body) {
            String[] data = entry.split("=");
            String key = data[0];
            String value = entry.substring(key.length() + 1);
            formData.put(key, value);
        }

        return formData;
    }

    private Character decoder(String code) {
//        code = hex
//        return = ASCII
        return (char) Integer.parseInt(code, 16);
    }

}
