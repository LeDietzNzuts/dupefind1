package net.caffeinemc.mods.lithium.mixin.debug.palette;

import net.minecraft.class_2540;
import net.minecraft.class_2837;
import net.minecraft.class_2841;
import net.minecraft.class_6490;
import net.minecraft.class_6558;
import net.minecraft.class_2841.class_6561;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2841.class)
public class PalettedContainerMixin<T> {
   @Shadow
   private volatile class_6561<T> field_34560;

   @Inject(method = "method_12326(Lnet/minecraft/class_2540;)V", at = @At("RETURN"))
   private void checkConsistency(class_2540 friendlyByteBuf, CallbackInfo ci) {
      class_6490 storage = this.field_34560.comp_118();
      class_2837<T> palette = this.field_34560.comp_119();
      int i = -1;
      int index = -1;

      try {
         for (i = 0; i < storage.method_15215(); i++) {
            index = storage.method_15211(i);
            T t = (T)palette.method_12288(index);
            if (t == null) {
               throw new class_6558(index);
            }
         }
      } catch (Exception var9) {
         String builder = "Received invalid paletted container data!\nEntry at index "
            + i
            + " has palette index "
            + index
            + ".\nPalette: "
            + palette
            + " Size: "
            + palette.method_12197()
            + "\n";
         throw new IllegalStateException(builder, var9);
      }
   }
}
