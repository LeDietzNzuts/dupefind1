package net.caffeinemc.mods.sodium.mixin.features.options.overlays;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.class_329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_329.class)
public class GuiMixin {
   @Redirect(
      method = "method_55798(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_310;method_1517()Z")
   )
   private boolean redirectFancyGraphicsVignette() {
      return SodiumClientMod.options().quality.enableVignette;
   }
}
