package net.caffeinemc.mods.sodium.mixin.core.gui;

import net.minecraft.class_2338;
import net.minecraft.class_746;
import net.minecraft.class_8819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_8819.class)
public class LevelLoadStatusManagerMixin {
   @Redirect(method = "method_54135()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_746;method_24515()Lnet/minecraft/class_2338;"))
   private class_2338 redirect$getPlayerBlockPosition(class_746 instance) {
      return class_2338.method_49637(instance.method_23317(), instance.method_23320(), instance.method_23321());
   }
}
