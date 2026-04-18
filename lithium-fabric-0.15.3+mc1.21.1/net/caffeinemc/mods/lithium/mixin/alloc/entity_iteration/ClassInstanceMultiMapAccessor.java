package net.caffeinemc.mods.lithium.mixin.alloc.entity_iteration;

import java.util.List;
import net.minecraft.class_3509;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_3509.class)
public interface ClassInstanceMultiMapAccessor<T> {
   @Accessor("field_15635")
   List<T> getAllInstances();
}
