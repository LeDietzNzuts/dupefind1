package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.minecraft.class_9316;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_9316.class)
public interface PathfindingContextAccessor {
   @Accessor("field_49424")
   class_2339 getLastNodePos();
}
