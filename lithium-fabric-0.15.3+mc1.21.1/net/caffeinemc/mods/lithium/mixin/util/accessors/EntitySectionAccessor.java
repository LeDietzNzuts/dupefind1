package net.caffeinemc.mods.lithium.mixin.util.accessors;

import net.minecraft.class_3509;
import net.minecraft.class_5572;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5572.class)
public interface EntitySectionAccessor<T> {
   @Accessor("field_27248")
   class_3509<T> getCollection();
}
