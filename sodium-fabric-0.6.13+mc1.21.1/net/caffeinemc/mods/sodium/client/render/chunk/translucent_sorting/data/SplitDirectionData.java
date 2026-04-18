package net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data;

import net.minecraft.class_4076;

public abstract class SplitDirectionData extends PresentTranslucentData {
   private final int[] vertexCounts;

   public SplitDirectionData(class_4076 sectionPos, int[] vertexCounts, int quadCount) {
      super(sectionPos, quadCount);
      this.vertexCounts = vertexCounts;
   }

   @Override
   public int[] getVertexCounts() {
      return this.vertexCounts;
   }
}
