package net.caffeinemc.mods.lithium.mixin.block.hopper;

import java.util.List;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_2371.class)
public interface NonNullListAccessor<T> {
   @Accessor("field_11115")
   List<T> getDelegate();
}
