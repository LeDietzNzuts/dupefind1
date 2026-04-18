package io.github.suel_ki.beautify.mixin;

import io.github.suel_ki.beautify.core.init.BlockInit;
import net.minecraft.class_1937;
import net.minecraft.class_2331;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2331.class)
public class EnchantingTableBlockMixin {
   @Inject(at = @At("HEAD"), method = "method_40445", cancellable = true)
   private static void isValidBookShelf(class_1937 level, class_2338 tablePos, class_2338 blockPos, CallbackInfoReturnable<Boolean> cir) {
      if (level.method_8320(tablePos.method_10081(blockPos)).method_27852(BlockInit.BOOKSTACK)
         && level.method_22347(tablePos.method_10069(blockPos.method_10263() / 2, blockPos.method_10264(), blockPos.method_10260() / 2))) {
         cir.setReturnValue(true);
      }
   }
}
