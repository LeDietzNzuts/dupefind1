package net.caffeinemc.mods.lithium.mixin.block_pattern_matching;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.lithium.common.world.block_pattern_matching.BlockPatternExtended;
import net.caffeinemc.mods.lithium.common.world.block_pattern_matching.BlockSearch;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2700;
import net.minecraft.class_4538;
import net.minecraft.class_9380;
import net.minecraft.class_2700.class_2702;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2700.class)
public class BlockPatternMixin implements BlockPatternExtended {
   @Unique
   private class_2248 requiredBlock;
   @Unique
   private int requiredBlockCount;

   @Inject(
      method = "method_11708(Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;)Lnet/minecraft/class_2700$class_2702;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2338;method_10069(III)Lnet/minecraft/class_2338;"),
      cancellable = true
   )
   private void countRequiredBlocksBeforeExpensiveSearch(class_4538 levelReader, class_2338 blockPos, CallbackInfoReturnable<class_2702> cir, @Local int size) {
      if (this.requiredBlock != null) {
         class_2338 maxCorner = blockPos.method_10069(2 * size - 1, 2 * size - 1, 2 * size - 1);
         class_2338 minCorner = blockPos.method_10069(-size, -size, -size);
         class_9380 searchBox = class_9380.method_58237(minCorner, maxCorner);
         if (!BlockSearch.hasAtLeast(levelReader, searchBox, this.requiredBlock, this.requiredBlockCount)) {
            cir.setReturnValue(null);
         }
      }
   }

   @Override
   public void lithium$setRequiredBlock(class_2248 block, int count) {
      this.requiredBlock = block;
      this.requiredBlockCount = count;
   }
}
