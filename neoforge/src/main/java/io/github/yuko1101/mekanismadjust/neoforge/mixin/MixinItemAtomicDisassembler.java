package io.github.yuko1101.mekanismadjust.neoforge.mixin;

import mekanism.common.item.gear.ItemAtomicDisassembler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemAtomicDisassembler.class)
public class MixinItemAtomicDisassembler {
    @ModifyVariable(method = "adjustAttributes", at = @At("STORE"), ordinal = 0)
    private long adjustAttributes(long energy) {
        return 0;
    }

    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    private void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
