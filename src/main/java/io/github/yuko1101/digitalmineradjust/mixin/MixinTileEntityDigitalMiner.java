package io.github.yuko1101.digitalmineradjust.mixin;

import io.github.yuko1101.digitalmineradjust.DigitalMinerAdjust;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import mekanism.common.util.MekanismUtils;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.BitSet;

@Mixin(value = TileEntityDigitalMiner.class, remap = false)
public abstract class MixinTileEntityDigitalMiner {

    @Shadow public abstract int getMaxY();

    @Shadow public abstract int getMinY();

    @Shadow public abstract int getRadius();

    @Shadow private int delay;

    @Shadow public abstract int getDelay();

    @Unique
    private int digitalMinerAdjust$initialExpectedMineCount = 0;

    @Inject(method = "getDelay", at = @At("HEAD"), cancellable = true)
    private void getDelay(CallbackInfoReturnable<Integer> cir) {
        if (digitalMinerAdjust$initialExpectedMineCount == 0) {
            throw new IllegalStateException("You can't call this method with initialExpectedMineCount 0.");
        }
        final TileEntityDigitalMiner digitalMiner = ((TileEntityDigitalMiner)(Object) this);
        final int delay = MekanismUtils.getTicks(digitalMiner, DigitalMinerAdjust.BASE_TICKS_PER_MINE * digitalMinerAdjust$rangeBlockCount() / digitalMinerAdjust$initialExpectedMineCount);
        cir.setReturnValue(delay);
    }

    @Inject(method = "updateFromSearch", at = @At("HEAD"))
    private void updateFromSearch(Long2ObjectMap<BitSet> oresToMine, int found, CallbackInfo ci) {
        digitalMinerAdjust$initialExpectedMineCount = found;
        delay = getDelay();
    }

    @Inject(method = "reset", at = @At("HEAD"))
    private void reset(CallbackInfo ci) {
        digitalMinerAdjust$initialExpectedMineCount = 0;
    }

    @Unique
    private int digitalMinerAdjust$rangeBlockCount() {
        return (int) ((getMaxY() - getMinY() + 1) * Math.pow(getRadius() * 2.0 + 1, 2.0));
    }


    @Inject(method = "onBoundingBlockPowerChange", at = @At("HEAD"))
    private void onBoundingBlockPowerChange(BlockPos boundingPos, int oldLevel, int newLevel, CallbackInfo ci) {
        final TileEntityDigitalMiner digitalMiner = ((TileEntityDigitalMiner)(Object) this);
        if (oldLevel > 0 && newLevel == 0) {
            digitalMiner.reset();
        } else if (newLevel > oldLevel) {
            if (!digitalMiner.isRunning()) digitalMiner.start();
        } else if (newLevel < oldLevel) {
            if (digitalMiner.isRunning()) digitalMiner.stop();
        }
    }
}
