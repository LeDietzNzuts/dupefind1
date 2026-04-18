package net.caffeinemc.mods.lithium.mixin.world.raycast;

import net.minecraft.class_3959;
import net.minecraft.class_3959.class_242;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_3959.class)
public interface ClipContextAccessor {
   @Accessor("field_17556")
   class_242 getFluidHandling();
}
