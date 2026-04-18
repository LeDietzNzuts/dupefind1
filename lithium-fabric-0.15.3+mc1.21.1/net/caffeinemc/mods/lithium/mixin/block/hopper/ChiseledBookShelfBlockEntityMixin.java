package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumTransferConditionInventory;
import net.minecraft.class_7716;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_7716.class)
public class ChiseledBookShelfBlockEntityMixin implements LithiumTransferConditionInventory {
   @Override
   public boolean lithium$itemInsertionTestRequiresStackSize1() {
      return true;
   }
}
