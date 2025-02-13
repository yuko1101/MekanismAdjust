package io.github.yuko1101.mekanismadjust.neoforge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import mekanism.common.registries.MekanismCreativeTabs;
import net.minecraft.item.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MekanismCreativeTabs.class)
public class MixinMekanismCreativeTabs {
    @WrapOperation(method = "lambda$static$1(Lnet/minecraft/item/ItemGroup$Builder;)Lnet/minecraft/item/ItemGroup$Builder;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemGroup$Builder;withSearchBar()Lnet/minecraft/item/ItemGroup$Builder;"))
    private static ItemGroup.Builder withoutSearchBar(ItemGroup.Builder instance, Operation<ItemGroup.Builder> original) {
        return instance;
    }
}
