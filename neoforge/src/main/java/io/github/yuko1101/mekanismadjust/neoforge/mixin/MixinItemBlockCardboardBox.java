package io.github.yuko1101.mekanismadjust.neoforge.mixin;

import mekanism.common.item.block.ItemBlockCardboardBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlockCardboardBox.class)
public class MixinItemBlockCardboardBox {
    @Inject(method = "canReplace", at = @At("HEAD"), cancellable = true)
    private static void canReplace(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
