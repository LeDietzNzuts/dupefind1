package net.caffeinemc.mods.sodium.mixin.features.render.immediate.buffer_builder.sorting;

import net.caffeinemc.mods.sodium.client.util.sorting.VertexSorters;
import net.minecraft.class_8251;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_8251.class)
public interface VertexSortingMixin {
   @Overwrite
   static class_8251 method_49906(float x, float y, float z) {
      return VertexSorters.sortByDistance(new Vector3f(x, y, z));
   }
}
