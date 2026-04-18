package net.caffeinemc.mods.lithium.mixin.util.block_tracking;

import net.caffeinemc.mods.lithium.common.block.BlockStateFlagHolder;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.block.TrackedBlockStatePredicate;
import net.minecraft.class_2680;
import net.minecraft.class_4970.class_4971;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = class_4971.class, priority = 1010)
public class BlockStateBaseMixin implements BlockStateFlagHolder {
   @Unique
   private int flags;

   @Override
   public void lithium$initializeFlags() {
      TrackedBlockStatePredicate.FULLY_INITIALIZED.set(true);
      int flags = 0;

      for (int i = 0; i < BlockStateFlags.FLAGS.length; i++) {
         if (BlockStateFlags.FLAGS[i].test((class_2680)this)) {
            flags |= 1 << i;
         }
      }

      this.flags = flags;
   }

   @Override
   public int lithium$getAllFlags() {
      return this.flags;
   }
}
