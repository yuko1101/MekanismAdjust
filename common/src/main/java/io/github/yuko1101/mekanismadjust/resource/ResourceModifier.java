package io.github.yuko1101.mekanismadjust.resource;

import io.github.yuko1101.mekanismadjust.MekanismAdjust;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class ResourceModifier {
    private static boolean initialized = false;
    private static final HashMap<String, QuickResourceModifier> quickResourceModifiers = new HashMap<>();

    public static void init() {
        if (initialized) return;
        initialized = true;
        MekanismAdjust.registerModifiers();
    }

    public static byte @Nullable [] modifyResource(Path path) throws IOException {
        String pathString = path.toString().replaceFirst("^/", "");
        var modifier = quickResourceModifiers.get(pathString);
        if (modifier == null) return null;

        byte[] data;
        try (var inputStream = Files.newInputStream(path)) {
            data = inputStream.readAllBytes();
        }

        return modifier.modify(data);
    }

    public static void registerQuickModifier(String path, QuickResourceModifier modifier) {
        quickResourceModifiers.put(path, modifier);
    }
}
