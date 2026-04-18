package org.embeddedt.modernfix.common.mixin.feature.measure_time;

import net.minecraft.class_310;
import net.minecraft.class_4071;
import org.embeddedt.modernfix.ModernFixClient;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
@ClientOnlyMixin
public class MinecraftMixin {
   @Shadow
   @Nullable
   public class_4071 field_18175;

   @Inject(method = "tick", at = @At("HEAD"))
   private void onClientTick(CallbackInfo ci) {
      if (this.field_18175 == null && ModernFixClient.INSTANCE != null) {
         ModernFixClient.INSTANCE.onGameLaunchFinish();
      }
   }

   @Inject(method = "doWorldLoad", at = @At("HEAD"))
   private void recordWorldLoadStart(CallbackInfo ci) {
      ModernFixClient.worldLoadStartTime = System.nanoTime();
   }
}
