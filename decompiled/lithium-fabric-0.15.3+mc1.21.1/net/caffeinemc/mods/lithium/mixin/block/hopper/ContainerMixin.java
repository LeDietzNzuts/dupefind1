package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumCooldownReceivingInventory;
import net.caffeinemc.mods.lithium.api.inventory.LithiumTransferConditionInventory;
import net.minecraft.class_1263;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1263.class)
public interface ContainerMixin extends LithiumCooldownReceivingInventory, LithiumTransferConditionInventory {
}
