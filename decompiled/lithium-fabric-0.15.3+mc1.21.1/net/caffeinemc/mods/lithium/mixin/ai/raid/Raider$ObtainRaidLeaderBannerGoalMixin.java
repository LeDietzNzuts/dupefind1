package net.caffeinemc.mods.lithium.mixin.ai.raid;

import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.minecraft.class_1799;
import net.minecraft.class_2582;
import net.minecraft.class_3763;
import net.minecraft.class_3765;
import net.minecraft.class_7871;
import net.minecraft.class_3763.class_3764;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3764.class)
public class Raider$ObtainRaidLeaderBannerGoalMixin<T extends class_3763> {
   @Shadow
   @Final
   private T field_16603;

   @Redirect(
      method = "method_6264()Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3765;method_16515(Lnet/minecraft/class_7871;)Lnet/minecraft/class_1799;")
   )
   private class_1799 getOminousBanner(class_7871<class_2582> bannerPatternLookup) {
      class_1799 ominousBanner = ((LithiumData)this.field_16603.method_37908()).lithium$getData().ominousBanner();
      if (ominousBanner == null) {
         ominousBanner = class_3765.method_16515(bannerPatternLookup);
      }

      return ominousBanner;
   }
}
