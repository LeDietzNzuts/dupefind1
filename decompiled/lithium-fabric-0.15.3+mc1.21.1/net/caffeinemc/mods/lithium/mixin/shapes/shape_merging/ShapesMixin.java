package net.caffeinemc.mods.lithium.mixin.shapes.shape_merging;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.caffeinemc.mods.lithium.common.shapes.pairs.LithiumDoublePairList;
import net.minecraft.class_255;
import net.minecraft.class_259;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_259.class)
public class ShapesMixin {
   @Inject(
      method = "method_1069(ILit/unimi/dsi/fastutil/doubles/DoubleList;Lit/unimi/dsi/fastutil/doubles/DoubleList;ZZ)Lnet/minecraft/class_255;",
      at = @At(
         shift = Shift.BEFORE,
         value = "NEW",
         target = "(Lit/unimi/dsi/fastutil/doubles/DoubleList;Lit/unimi/dsi/fastutil/doubles/DoubleList;ZZ)Lnet/minecraft/class_254;"
      ),
      cancellable = true
   )
   private static void injectCustomListPair(int size, DoubleList a, DoubleList b, boolean flag1, boolean flag2, CallbackInfoReturnable<class_255> cir) {
      cir.setReturnValue(new LithiumDoublePairList(a, b, flag1, flag2));
   }
}
