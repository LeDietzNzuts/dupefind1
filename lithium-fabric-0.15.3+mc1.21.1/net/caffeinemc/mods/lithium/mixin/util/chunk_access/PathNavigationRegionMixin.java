package net.caffeinemc.mods.lithium.mixin.util.chunk_access;

import net.caffeinemc.mods.lithium.common.world.ChunkView;
import net.minecraft.class_1950;
import net.minecraft.class_2791;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1950.class)
public abstract class PathNavigationRegionMixin implements ChunkView {
   @Shadow
   protected abstract class_2791 method_22353(int var1, int var2);

   @Nullable
   @Override
   public class_2791 lithium$getLoadedChunk(int chunkX, int chunkZ) {
      return this.method_22353(chunkX, chunkZ);
   }
}
