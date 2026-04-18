package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.spawning;

import net.minecraft.class_5568;
import net.minecraft.class_5573;
import net.minecraft.class_5579;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5579.class)
public interface PersistentEntitySectionManagerAccessor<T extends class_5568> {
   @Accessor("field_27265")
   class_5573<T> getCache();
}
