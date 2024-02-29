package io.github.yuko1101.digitalmineradjust.mixin;

import io.github.yuko1101.digitalmineradjust.DigitalMinerAdjust;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityDigitalMiner.class, remap = false)
public class MixinTileEntityDigitalMiner {
    @Inject(method = "canMine", at = @At("HEAD"), cancellable = true)
    private void canMine(BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (DigitalMinerAdjust.disallowedBlocks.stream().anyMatch(state::is)) {
            cir.setReturnValue(false);
        }
    }
}
