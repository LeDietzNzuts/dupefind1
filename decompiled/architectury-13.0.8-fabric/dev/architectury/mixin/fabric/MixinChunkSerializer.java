package dev.architectury.mixin.fabric;

import com.mojang.serialization.Codec;
import dev.architectury.event.events.common.ChunkEvent;
import net.minecraft.class_1923;
import net.minecraft.class_1959;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2791;
import net.minecraft.class_2802;
import net.minecraft.class_2808;
import net.minecraft.class_2826;
import net.minecraft.class_2839;
import net.minecraft.class_2841;
import net.minecraft.class_2843;
import net.minecraft.class_2852;
import net.minecraft.class_3218;
import net.minecraft.class_3568;
import net.minecraft.class_4153;
import net.minecraft.class_6749;
import net.minecraft.class_6880;
import net.minecraft.class_9240;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2852.class)
public class MixinChunkSerializer {
   @Inject(method = "read", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
   private static void load(
      class_3218 serverLevel,
      class_4153 poiManager,
      class_9240 regionStorageInfo,
      class_1923 chunkPos,
      class_2487 compoundTag,
      CallbackInfoReturnable<class_2839> cir,
      class_1923 chunkPos2,
      class_2843 upgradeData,
      boolean bl,
      class_2499 listTag,
      int i,
      class_2826[] levelChunkSections,
      boolean bl2,
      class_2802 chunkSource,
      class_3568 levelLightEngine,
      class_2378<class_1959> registry,
      Codec<class_2841<class_6880<class_1959>>> codec,
      boolean bl3,
      long m,
      class_2808 chunkType,
      class_6749 blendingData,
      class_2791 chunkAccess
   ) {
      ChunkEvent.LOAD_DATA.invoker().load(chunkAccess, serverLevel, compoundTag);
   }
}
