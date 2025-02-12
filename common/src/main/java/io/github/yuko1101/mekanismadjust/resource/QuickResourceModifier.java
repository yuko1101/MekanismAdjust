package io.github.yuko1101.mekanismadjust.resource;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface QuickResourceModifier {
    byte @Nullable [] modify(byte[] data);
}
