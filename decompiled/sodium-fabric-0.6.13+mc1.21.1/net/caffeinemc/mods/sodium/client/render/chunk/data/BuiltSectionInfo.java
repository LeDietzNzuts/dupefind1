package net.caffeinemc.mods.sodium.client.render.chunk.data;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.client.render.chunk.occlusion.VisibilityEncoding;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
import net.minecraft.class_1058;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_854;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuiltSectionInfo {
   public static final BuiltSectionInfo EMPTY = createEmptyData();
   public final int flags;
   public final long visibilityData;
   public final class_2586 @Nullable [] globalBlockEntities;
   public final class_2586 @Nullable [] culledBlockEntities;
   public final class_1058 @Nullable [] animatedSprites;

   private BuiltSectionInfo(
      @NotNull Collection<TerrainRenderPass> blockRenderPasses,
      @NotNull Collection<class_2586> globalBlockEntities,
      @NotNull Collection<class_2586> culledBlockEntities,
      @NotNull Collection<class_1058> animatedSprites,
      @NotNull class_854 occlusionData
   ) {
      this.globalBlockEntities = toArray(globalBlockEntities, class_2586[]::new);
      this.culledBlockEntities = toArray(culledBlockEntities, class_2586[]::new);
      this.animatedSprites = toArray(animatedSprites, class_1058[]::new);
      int flags = 0;
      if (!blockRenderPasses.isEmpty()) {
         flags |= 1;
      }

      if (!culledBlockEntities.isEmpty()) {
         flags |= 2;
      }

      if (!animatedSprites.isEmpty()) {
         flags |= 4;
      }

      this.flags = flags;
      this.visibilityData = VisibilityEncoding.encode(occlusionData);
   }

   private static BuiltSectionInfo createEmptyData() {
      class_854 occlusionData = new class_854();
      occlusionData.method_3693(EnumSet.allOf(class_2350.class));
      BuiltSectionInfo.Builder meshInfo = new BuiltSectionInfo.Builder();
      meshInfo.setOcclusionData(occlusionData);
      return meshInfo.build();
   }

   private static <T> T[] toArray(Collection<T> collection, IntFunction<T[]> allocator) {
      return collection.isEmpty() ? null : collection.toArray(allocator);
   }

   public static class Builder {
      private final List<TerrainRenderPass> blockRenderPasses = new ArrayList<>();
      private final List<class_2586> globalBlockEntities = new ArrayList<>();
      private final List<class_2586> culledBlockEntities = new ArrayList<>();
      private final Set<class_1058> animatedSprites = new ObjectOpenHashSet();
      private class_854 occlusionData;

      public void addRenderPass(TerrainRenderPass pass) {
         this.blockRenderPasses.add(pass);
      }

      public void setOcclusionData(class_854 data) {
         this.occlusionData = data;
      }

      public void addSprite(@NotNull class_1058 sprite) {
         if (SpriteUtil.INSTANCE.hasAnimation(sprite)) {
            this.animatedSprites.add(sprite);
         }
      }

      public void addBlockEntity(class_2586 entity, boolean cull) {
         (cull ? this.culledBlockEntities : this.globalBlockEntities).add(entity);
      }

      public BuiltSectionInfo build() {
         return new BuiltSectionInfo(this.blockRenderPasses, this.globalBlockEntities, this.culledBlockEntities, this.animatedSprites, this.occlusionData);
      }
   }
}
