package net.caffeinemc.mods.lithium.mixin.shapes.blockstate_cache;

import net.caffeinemc.mods.lithium.common.util.collections.Object2BooleanCacheTable;
import net.minecraft.class_2248;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_2248.class)
public class BlockMixin {
   private static final Object2BooleanCacheTable<class_265> FULL_CUBE_CACHE = new Object2BooleanCacheTable<>(
      512, shape -> !class_259.method_1074(class_259.method_1077(), shape, class_247.field_16892)
   );

   @Overwrite
   public static boolean method_9614(class_265 shape) {
      return FULL_CUBE_CACHE.get(shape);
   }
}
