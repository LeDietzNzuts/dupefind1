package net.caffeinemc.mods.sodium.client.world.cloned;

import java.util.List;
import net.minecraft.class_3341;
import net.minecraft.class_4076;

public class ChunkRenderContext {
   private final class_4076 origin;
   private final ClonedChunkSection[] sections;
   private final class_3341 volume;
   private final List<?> renderers;

   public ChunkRenderContext(class_4076 origin, ClonedChunkSection[] sections, class_3341 volume, List<?> renderers) {
      this.origin = origin;
      this.sections = sections;
      this.volume = volume;
      this.renderers = renderers;
   }

   public ClonedChunkSection[] getSections() {
      return this.sections;
   }

   public class_4076 getOrigin() {
      return this.origin;
   }

   public class_3341 getVolume() {
      return this.volume;
   }

   public List<?> getRenderers() {
      return this.renderers;
   }
}
