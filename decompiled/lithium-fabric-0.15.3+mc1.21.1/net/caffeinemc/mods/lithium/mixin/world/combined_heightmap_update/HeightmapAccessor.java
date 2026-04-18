package net.caffeinemc.mods.lithium.mixin.world.combined_heightmap_update;

import java.util.function.Predicate;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_2902.class)
public interface HeightmapAccessor {
   @Invoker("method_12602")
   void callSet(int var1, int var2, int var3);

   @Accessor("field_13193")
   Predicate<class_2680> getBlockPredicate();
}
