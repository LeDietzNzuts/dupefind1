package net.caffeinemc.mods.lithium.mixin.util.accessors;

import net.minecraft.class_5568;
import net.minecraft.class_5573;
import net.minecraft.class_5582;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5582.class)
public interface TransientEntitySectionManagerAccessor<T extends class_5568> {
   @Accessor("field_27282")
   class_5573<T> getCache();
}
