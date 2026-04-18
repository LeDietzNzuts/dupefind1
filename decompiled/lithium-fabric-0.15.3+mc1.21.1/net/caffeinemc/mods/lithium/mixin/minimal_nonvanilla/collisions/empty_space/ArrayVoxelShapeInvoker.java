package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.collisions.empty_space;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_245;
import net.minecraft.class_251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_245.class)
public interface ArrayVoxelShapeInvoker {
   @Invoker("<init>")
   static class_245 init(class_251 shape, DoubleList xPoints, DoubleList yPoints, DoubleList zPoints) {
      throw new AssertionError();
   }
}
