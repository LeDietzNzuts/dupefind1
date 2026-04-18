package net.caffeinemc.mods.lithium.mixin.math.fast_util;

import net.minecraft.class_238;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_238.class)
public class AABBMixin {
   @Shadow
   @Final
   public double field_1323;
   @Shadow
   @Final
   public double field_1322;
   @Shadow
   @Final
   public double field_1321;
   @Shadow
   @Final
   public double field_1320;
   @Shadow
   @Final
   public double field_1325;
   @Shadow
   @Final
   public double field_1324;

   // $VF: Unable to simplify switch-on-enum, as the enum class was not able to be found.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Overwrite
   public double method_1001(class_2351 axis) {
      switch (axis.ordinal()) {
         case 0:
            return this.field_1323;
         case 1:
            return this.field_1322;
         case 2:
            return this.field_1321;
         default:
            throw new IllegalArgumentException();
      }
   }

   // $VF: Unable to simplify switch-on-enum, as the enum class was not able to be found.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Overwrite
   public double method_990(class_2351 axis) {
      switch (axis.ordinal()) {
         case 0:
            return this.field_1320;
         case 1:
            return this.field_1325;
         case 2:
            return this.field_1324;
         default:
            throw new IllegalArgumentException();
      }
   }

   static {
      assert class_2351.field_11048.ordinal() == 0;

      assert class_2351.field_11052.ordinal() == 1;

      assert class_2351.field_11051.ordinal() == 2;

      assert class_2351.values().length == 3;
   }
}
