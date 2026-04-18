package net.caffeinemc.mods.lithium.mixin.alloc.composter;

import net.caffeinemc.mods.lithium.common.util.ArrayConstants;
import net.minecraft.class_1278;
import net.minecraft.class_2350;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

public class ComposterMixin {
   @Mixin(targets = "net/minecraft/class_3962$class_3963")
   abstract static class ComposterBlockComposterInventoryMixin implements class_1278 {
      @Overwrite
      public int[] method_5494(class_2350 side) {
         return side == class_2350.field_11036 ? ArrayConstants.ZERO : ArrayConstants.EMPTY;
      }
   }

   @Mixin(targets = "net/minecraft/class_3962$class_3925")
   abstract static class ComposterBlockDummyInventoryMixin implements class_1278 {
      @Overwrite
      public int[] method_5494(class_2350 side) {
         return ArrayConstants.EMPTY;
      }
   }

   @Mixin(targets = "net/minecraft/class_3962$class_3964")
   abstract static class ComposterBlockFullComposterInventoryMixin implements class_1278 {
      @Overwrite
      public int[] method_5494(class_2350 side) {
         return side == class_2350.field_11033 ? ArrayConstants.ZERO : ArrayConstants.EMPTY;
      }
   }
}
