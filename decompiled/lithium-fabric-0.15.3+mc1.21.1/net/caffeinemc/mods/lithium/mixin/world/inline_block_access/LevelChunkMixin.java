package net.caffeinemc.mods.lithium.mixin.world.inline_block_access;

import net.minecraft.class_1923;
import net.minecraft.class_1959;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = class_2818.class, priority = 500)
public abstract class LevelChunkMixin extends class_2791 {
   private static final class_2680 DEFAULT_BLOCK_STATE = class_2246.field_10124.method_9564();
   private static final class_3610 DEFAULT_FLUID_STATE = class_3612.field_15906.method_15785();

   public LevelChunkMixin(
      class_1923 pos,
      class_2843 upgradeData,
      class_5539 heightLimitView,
      class_2378<class_1959> biome,
      long inhabitedTime,
      @Nullable class_2826[] sectionArrayInitializer,
      @Nullable class_6749 blendingData
   ) {
      super(pos, upgradeData, heightLimitView, biome, inhabitedTime, sectionArrayInitializer, blendingData);
   }

   @Overwrite
   public class_2680 method_8320(class_2338 pos) {
      int x = pos.method_10263();
      int y = pos.method_10264();
      int z = pos.method_10260();
      int chunkY = this.method_31602(y);
      class_2826[] sectionArray = this.method_12006();
      if (chunkY >= 0 && chunkY < sectionArray.length) {
         class_2826 section = sectionArray[chunkY];
         if (!section.method_38292()) {
            return section.method_12254(x & 15, y & 15, z & 15);
         }
      }

      return DEFAULT_BLOCK_STATE;
   }

   @Overwrite
   public class_3610 method_12234(int x, int y, int z) {
      int chunkY = this.method_31602(y);
      class_2826[] sectionArray = this.method_12006();
      if (chunkY >= 0 && chunkY < sectionArray.length) {
         class_2826 section = sectionArray[chunkY];
         return section.method_12255(x & 15, y & 15, z & 15);
      } else {
         return DEFAULT_FLUID_STATE;
      }
   }
}
