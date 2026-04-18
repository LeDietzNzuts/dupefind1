package net.caffeinemc.mods.sodium.mixin.features.render.immediate;

import net.minecraft.class_2350;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_2350.class)
public class DirectionMixin {
   @Overwrite
   public static class_2350 method_10147(float x, float y, float z) {
      if (x == 0.0F && y == 0.0F && z == 0.0F) {
         return class_2350.field_11043;
      } else {
         float yM = Math.abs(y);
         float zM = Math.abs(z);
         float xM = Math.abs(x);
         if (yM >= zM) {
            if (yM >= xM) {
               if (y <= 0.0F) {
                  return class_2350.field_11033;
               }

               return class_2350.field_11036;
            }
         } else if (zM >= xM) {
            if (z <= 0.0F) {
               return class_2350.field_11043;
            }

            return class_2350.field_11035;
         }

         return x <= 0.0F ? class_2350.field_11039 : class_2350.field_11034;
      }
   }
}
