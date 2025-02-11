package io.github.yuko1101.mekanismadjust.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import io.github.yuko1101.mekanismadjust.MekanismAdjust;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ResourceModifier {
    private static boolean initialized = false;
    private static final ArrayList<JsonModifier> jsonModifiers = new ArrayList<>();

    public static void init() {
        if (initialized) return;
        initialized = true;
        MekanismAdjust.registerJsonModifiers(jsonModifiers);
    }

    @Nullable
    public static InputStream replaceWithFile(Path path) {
        return ResourceModifier.class.getClassLoader().getResourceAsStream("modified/" + path.toString().replaceFirst("^/", ""));
    }

    @Nullable
    public static JsonElement modifyJson(Path path) throws IOException {
        boolean isModified = false;
        JsonElement jsonElement;
        try (var inputStream = Files.newInputStream(path)) {
            jsonElement = JsonParser.parseReader(new InputStreamReader(inputStream));
        } catch (JsonParseException ignored) {
            return null;
        }
        for (var modifier : jsonModifiers) {
            var modifiedJson = modifier.modify(path, jsonElement);
            if (modifiedJson != null) {
                isModified = true;
                jsonElement = modifiedJson;
            }
        }

        return isModified ? jsonElement : null;
    }
}
