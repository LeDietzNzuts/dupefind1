package org.embeddedt.modernfix.common.mixin.feature.blockentity_incorrect_thread;

import java.util.Map;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2586;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import org.embeddedt.modernfix.util.ConcurrencySanitizingMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2791.class)
public class ChunkAccessMixin {
   @Shadow
   @Final
   @Mutable
   protected Map<class_2338, class_2586> field_34543;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void wrapInConcurrencyDetector(
      class_1923 chunkPos,
      class_2843 upgradeData,
      class_5539 levelHeightAccessor,
      class_2378 biomeRegistry,
      long inhabitedTime,
      class_2826[] sections,
      class_6749 blendingData,
      CallbackInfo ci
   ) {
      if (levelHeightAccessor instanceof class_1937 level) {
         this.field_34543 = new ConcurrencySanitizingMap<>(this.field_34543, ((LevelThreadAccessor)level).getThread());
      }
   }
}
