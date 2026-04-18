package net.caffeinemc.mods.lithium.mixin.math.fast_util;

import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

public class AxisCycleDirectionMixin {
   static {
      assert class_2351.field_11048.ordinal() == 0;

      assert class_2351.field_11052.ordinal() == 1;

      assert class_2351.field_11051.ordinal() == 2;

      assert class_2351.values().length == 3;
   }

   @Mixin(targets = "net/minecraft/class_2335$3")
   public static class BackwardMixin {
      // $VF: Unable to simplify switch-on-enum, as the enum class was not able to be found.
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      @Overwrite
      public class_2351 method_10058(class_2351 axis) {
         switch (axis.ordinal()) {
            case 0:
               return class_2351.field_11051;
            case 1:
               return class_2351.field_11048;
            case 2:
               return class_2351.field_11052;
            default:
               throw new IllegalArgumentException();
         }
      }
   }

   @Mixin(targets = "net/minecraft/class_2335$2")
   public static class ForwardMixin {
      // $VF: Unable to simplify switch-on-enum, as the enum class was not able to be found.
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      @Overwrite
      public class_2351 method_10058(class_2351 axis) {
         switch (axis.ordinal()) {
            case 0:
               return class_2351.field_11052;
            case 1:
               return class_2351.field_11051;
            case 2:
               return class_2351.field_11048;
            default:
               throw new IllegalArgumentException();
         }
      }
   }
}
