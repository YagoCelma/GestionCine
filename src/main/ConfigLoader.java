package main;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigLoader {
    public static JSONObject getCineConfig() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("config.json")));
            JSONObject root = new JSONObject(content);
            return root.getJSONObject("cine");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

