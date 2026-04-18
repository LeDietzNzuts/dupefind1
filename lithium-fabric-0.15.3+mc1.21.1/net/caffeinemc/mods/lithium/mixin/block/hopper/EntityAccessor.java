package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.minecraft.class_1297;
import net.minecraft.class_5569;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1297.class)
public interface EntityAccessor {
   @Accessor("field_26996")
   class_5569 getChangeListener();
}
