package net.caffeinemc.mods.lithium.fabric.mixin.util.inventory_change_listening;

import net.caffeinemc.mods.lithium.common.block.entity.SetBlockStateHandlingBlockEntity;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeEmitter;
import net.minecraft.class_2338;
import net.minecraft.class_2591;
import net.minecraft.class_2595;
import net.minecraft.class_2621;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = class_2595.class, priority = 999)
public abstract class ChestBlockEntityMixin extends class_2621 implements InventoryChangeEmitter, SetBlockStateHandlingBlockEntity {
   protected ChestBlockEntityMixin(class_2591<?> blockEntityType, class_2338 blockPos, class_2680 blockState) {
      super(blockEntityType, blockPos, blockState);
   }

   @Override
   public void lithium$handleSetBlockState() {
      this.lithium$emitRemoved();
   }
}
