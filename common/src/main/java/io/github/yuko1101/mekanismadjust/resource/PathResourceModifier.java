package io.github.yuko1101.mekanismadjust.resource;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@FunctionalInterface
public interface PathResourceModifier {
    byte @Nullable [] modify(String path, byte[] data) throws IOException;
}