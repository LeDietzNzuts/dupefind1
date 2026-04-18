package net.caffeinemc.mods.lithium.fabric.mixin.entity.collisions.fluid;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.caffeinemc.mods.lithium.common.block.BlockCountingSection;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.block.TrackedBlockStatePredicate;
import net.caffeinemc.mods.lithium.common.entity.FluidCachingEntity;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_3218;
import net.minecraft.class_3486;
import net.minecraft.class_3611;
import net.minecraft.class_638;
import net.minecraft.class_6862;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
public abstract class EntityMixin implements FluidCachingEntity {
   @Shadow
   private class_1937 field_6002;
   @Shadow
   protected Object2DoubleMap<class_6862<class_3611>> field_5964;

   @Inject(
      method = "method_5692(Lnet/minecraft/class_6862;D)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5675()Z"),
      cancellable = true
   )
   public void tryShortcutFluidPushing(
      class_6862<class_3611> tag,
      double speed,
      CallbackInfoReturnable<Boolean> cir,
      @Local(ordinal = 0) int x1,
      @Local(ordinal = 1) int x2,
      @Local(ordinal = 2) int y1,
      @Local(ordinal = 3) int y2,
      @Local(ordinal = 4) int z1,
      @Local(ordinal = 5) int z2
   ) {
      if (this.field_6002.method_8608() && this.field_6002 instanceof class_638 || this.field_6002 instanceof class_3218) {
         TrackedBlockStatePredicate blockStateFlag;
         if (tag == class_3486.field_15517) {
            blockStateFlag = BlockStateFlags.WATER;
         } else {
            if (tag != class_3486.field_15518) {
               return;
            }

            blockStateFlag = BlockStateFlags.LAVA;
         }

         int chunkX1 = x1 >> 4;
         int chunkZ1 = z1 >> 4;
         int chunkX2 = x2 - 1 >> 4;
         int chunkZ2 = z2 - 1 >> 4;
         int chunkYIndex1 = Math.max(Pos.SectionYIndex.fromBlockCoord(this.field_6002, y1), Pos.SectionYIndex.getMinYSectionIndex(this.field_6002));
         int chunkYIndex2 = Math.min(Pos.SectionYIndex.fromBlockCoord(this.field_6002, y2 - 1), Pos.SectionYIndex.getMaxYSectionIndexInclusive(this.field_6002));

         for (int chunkX = chunkX1; chunkX <= chunkX2; chunkX++) {
            for (int chunkZ = chunkZ1; chunkZ <= chunkZ2; chunkZ++) {
               class_2791 chunk = this.field_6002.method_8497(chunkX, chunkZ);

               for (int chunkYIndex = chunkYIndex1; chunkYIndex <= chunkYIndex2; chunkYIndex++) {
                  class_2826 section = chunk.method_12006()[chunkYIndex];
                  if (((BlockCountingSection)section).lithium$mayContainAny(blockStateFlag)) {
                     return;
                  }
               }
            }
         }

         this.field_5964.put(tag, 0.0);
         cir.setReturnValue(false);
      }
   }
}
