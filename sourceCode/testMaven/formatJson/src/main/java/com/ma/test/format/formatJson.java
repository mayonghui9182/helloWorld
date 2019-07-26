package com.ma.test.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class formatJson {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URI resource = formatJson.class.getClassLoader().getResource("json.txt").toURI();
        String sourceJson = Files.lines(Paths.get(resource)).collect(Collectors.joining(" "));
        String formatJson = toPrettyFormat(sourceJson);
        File file = new File("C:\\Users\\myh\\Desktop\\formatJson.txt");
        file.createNewFile();
        Files.write(file.toPath(),formatJson.getBytes());
    }

    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }
}
