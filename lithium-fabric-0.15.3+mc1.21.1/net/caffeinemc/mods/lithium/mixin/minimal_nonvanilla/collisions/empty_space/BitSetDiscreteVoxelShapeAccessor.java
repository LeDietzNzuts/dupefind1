package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.collisions.empty_space;

import java.util.BitSet;
import net.minecraft.class_244;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_244.class)
public interface BitSetDiscreteVoxelShapeAccessor {
   @Accessor
   BitSet getField_1359();
}
