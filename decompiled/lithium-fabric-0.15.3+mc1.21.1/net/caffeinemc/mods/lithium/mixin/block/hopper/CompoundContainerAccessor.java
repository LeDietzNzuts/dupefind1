package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.minecraft.class_1258;
import net.minecraft.class_1263;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1258.class)
public interface CompoundContainerAccessor {
   @Accessor("field_5769")
   class_1263 getFirst();

   @Accessor("field_5771")
   class_1263 getSecond();
}
