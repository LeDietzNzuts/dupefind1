package net.caffeinemc.mods.sodium.mixin.features.options.weather;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.class_310;
import net.minecraft.class_5365;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_761.class)
public class LevelRendererMixin {
   @Redirect(method = "method_22714(Lnet/minecraft/class_765;FDDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_310;method_1517()Z"))
   private boolean redirectGetFancyWeather() {
      return SodiumClientMod.options().quality.weatherQuality.isFancy((class_5365)class_310.method_1551().field_1690.method_42534().method_41753());
   }
}
