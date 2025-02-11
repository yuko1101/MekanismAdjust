package io.github.yuko1101.mekanismadjust.fabric;

import io.github.yuko1101.mekanismadjust.MekanismAdjust;
import net.fabricmc.api.ModInitializer;

public final class MekanismAdjustFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MekanismAdjust.init();
    }
}
