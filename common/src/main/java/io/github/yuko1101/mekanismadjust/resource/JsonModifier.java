package io.github.yuko1101.mekanismadjust.resource;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

@FunctionalInterface
public interface JsonModifier {
    @Nullable
    JsonElement modify(Path path, JsonElement json);
}
