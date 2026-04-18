package net.caffeinemc.mods.lithium.mixin.util.chunk_access;

import net.caffeinemc.mods.lithium.common.world.ChunkView;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_4538;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4538.class)
public interface LevelReaderMixin extends ChunkView {
   @Shadow
   @Nullable
   class_2791 method_8402(int var1, int var2, class_2806 var3, boolean var4);

   @Nullable
   @Override
   default class_2791 lithium$getLoadedChunk(int chunkX, int chunkZ) {
      return this.method_8402(chunkX, chunkZ, class_2806.field_12803, false);
   }
}
