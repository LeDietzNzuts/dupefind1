package net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.bsp_tree;

import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TQuad;
import net.minecraft.class_4076;

class BSPWorkspace {
   final TQuad[] quads;
   final class_4076 sectionPos;
   final BSPResult result = new BSPResult();
   final boolean prepareNodeReuse;

   BSPWorkspace(TQuad[] quads, class_4076 sectionPos, boolean prepareNodeReuse) {
      this.quads = quads;
      this.sectionPos = sectionPos;
      this.prepareNodeReuse = prepareNodeReuse;
   }

   void addAlignedPartitionPlane(int axis, float distance) {
      this.result.addDoubleSidedPlane(this.sectionPos, axis, distance);
   }
}
