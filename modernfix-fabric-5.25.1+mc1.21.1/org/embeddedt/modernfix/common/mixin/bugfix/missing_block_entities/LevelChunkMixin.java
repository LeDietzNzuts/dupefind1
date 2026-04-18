package org.embeddedt.modernfix.common.mixin.bugfix.missing_block_entities;

import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_310;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2818.class_2819;
import net.minecraft.class_4970.class_4971;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2818.class)
@ClientOnlyMixin
public abstract class LevelChunkMixin extends class_2791 {
   @Shadow
   @Final
   private class_1937 field_12858;

   @Shadow
   @Nullable
   public abstract class_2586 method_12201(class_2338 var1, class_2819 var2);

   public LevelChunkMixin(
      class_1923 chunkPos,
      class_2843 upgradeData,
      class_5539 levelHeightAccessor,
      class_2378<class_1959> biomeRegistry,
      long inhabitedTime,
      @Nullable class_2826[] sections,
      @Nullable class_6749 blendingData
   ) {
      super(chunkPos, upgradeData, levelHeightAccessor, biomeRegistry, inhabitedTime, sections, blendingData);
   }

   @Inject(method = "replaceWithPacketData", at = @At("RETURN"))
   private void validateBlockEntitiesInChunk(CallbackInfo ci) {
      if (this.field_12858.field_9236 && !class_310.method_1551().method_1542()) {
         for (int i = 0; i < this.field_34545.length; i++) {
            class_2826 section = this.field_34545[i];

            try {
               if (!section.method_38292() && section.method_19523(class_4971::method_31709)) {
                  this.scanSectionForBlockEntities(section, i);
               }
            } catch (Exception var5) {
               ModernFix.LOGGER.error("Exception validating data in chunk", var5);
               return;
            }
         }
      }
   }

   @Unique
   private void scanSectionForBlockEntities(class_2826 section, int i) {
      int chunkXOff = this.field_34538.field_9181 * 16;
      int chunkZOff = this.field_34538.field_9180 * 16;
      class_2339 cursor = new class_2339();
      int sectionYOff = this.method_31604(i) * 16;

      for (int y = 0; y < 16; y++) {
         for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
               class_2680 state = section.method_12254(x, y, z);
               if (state.method_31709()) {
                  cursor.method_10103(chunkXOff + x, sectionYOff + y, chunkZOff + z);
                  this.makeBlockEntityIfNotExists(state, cursor);
               }
            }
         }
      }
   }

   @Unique
   private void makeBlockEntityIfNotExists(class_2680 state, class_2339 pos) {
      if (!this.field_34543.containsKey(pos) && !this.field_34542.containsKey(pos)) {
         class_2586 blockEntity = this.method_12201(pos.method_10062(), class_2819.field_12860);
         String blockName = state.method_26204().toString();
         if (blockEntity != null) {
            ModernFix.LOGGER.warn("Created missing block entity for {} at {}", blockName, pos.method_23854());
         } else {
            ModernFix.LOGGER.error("Block entity is missing for {} at {}, but could not be created", blockName, pos.method_23854());
         }
      }
   }
}
