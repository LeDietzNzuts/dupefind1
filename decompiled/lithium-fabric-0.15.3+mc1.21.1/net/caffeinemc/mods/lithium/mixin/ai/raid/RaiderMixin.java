package net.caffeinemc.mods.lithium.mixin.ai.raid;

import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2582;
import net.minecraft.class_3763;
import net.minecraft.class_3765;
import net.minecraft.class_7871;
import net.minecraft.class_7924;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3763.class)
public abstract class RaiderMixin extends class_1297 {
   @Mutable
   @Shadow
   @Final
   static Predicate<class_1542> field_16600 = itemEntity -> {
      class_1799 ominousBanner = ((LithiumData)itemEntity.method_37908()).lithium$getData().ominousBanner();
      if (ominousBanner == null) {
         ominousBanner = class_3765.method_16515(itemEntity.method_56673().method_46762(class_7924.field_41252));
      }

      return !itemEntity.method_6977() && itemEntity.method_5805() && class_1799.method_7973(itemEntity.method_6983(), ominousBanner);
   };

   public RaiderMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Redirect(
      method = {"method_5949(Lnet/minecraft/class_1542;)V", "method_58646()Z"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3765;method_16515(Lnet/minecraft/class_7871;)Lnet/minecraft/class_1799;")
   )
   private class_1799 getOminousBanner(class_7871<class_2582> bannerPatternLookup) {
      class_1799 ominousBanner = ((LithiumData)this.method_37908()).lithium$getData().ominousBanner();
      if (ominousBanner == null) {
         ominousBanner = class_3765.method_16515(bannerPatternLookup);
      }

      return ominousBanner;
   }
}
