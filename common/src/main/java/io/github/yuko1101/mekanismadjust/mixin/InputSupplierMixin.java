package io.github.yuko1101.mekanismadjust.mixin;

import io.github.yuko1101.mekanismadjust.resource.ResourceModifier;
import net.minecraft.resource.InputSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Mixin(InputSupplier.class)
public interface InputSupplierMixin {
    @Inject(method = "create(Ljava/nio/file/Path;)Lnet/minecraft/resource/InputSupplier;", at = @At("HEAD"), cancellable = true)
    private static void create(Path path, CallbackInfoReturnable<InputSupplier<InputStream>> cir) throws IOException {
        ResourceModifier.init();
        var inputStream = ResourceModifier.replaceWithFile(path);
        if (inputStream != null) {
            cir.setReturnValue(() -> inputStream);
        }
        if (path.toString().endsWith(".json")) {
            var modifiedJson = ResourceModifier.modifyJson(path);
            if (modifiedJson != null) {
                cir.setReturnValue(() -> new ByteArrayInputStream(modifiedJson.toString().getBytes()));
            }
        }
    }
}
