package net.raphimc.immediatelyfast.feature.batching;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceObjectImmutablePair;
import it.unimi.dsi.fastutil.objects.ReferenceObjectPair;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import java.util.SequencedMap;
import java.util.Set;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import net.minecraft.class_9799;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;

public class HudBatchingBufferSource extends BatchableBufferSource {
   private final Object2ObjectMap<ReferenceObjectPair<class_1921, LightingState>, class_1921> lightingRenderLayers = new Object2ObjectOpenHashMap();
   private final Reference2ObjectMap<class_1921, ReferenceSet<class_1921>> renderLayerMap = new Reference2ObjectOpenHashMap();
   private boolean renderingItem = false;
   private boolean currentlyDrawing = false;

   public HudBatchingBufferSource(class_9799 fallbackBuffer, SequencedMap<class_1921, class_9799> layerBuffers) {
      super(fallbackBuffer, layerBuffers);
   }

   public void setRenderingItem(boolean renderingItem) {
      this.renderingItem = renderingItem;
   }

   public boolean isCurrentlyDrawing() {
      return this.currentlyDrawing;
   }

   @Override
   public class_4588 getBuffer(class_1921 layer) {
      if (this.renderingItem && !layer.field_21363.contains("glint")) {
         LightingState lightingState = LightingState.current();
         class_1921 newLayer = (class_1921)this.lightingRenderLayers
            .computeIfAbsent(
               new ReferenceObjectImmutablePair(layer, lightingState),
               key -> new BatchingBuffers.WrappedRenderLayer(layer, lightingState::saveAndApply, lightingState::revert)
            );
         ((ReferenceSet)this.renderLayerMap.computeIfAbsent(layer, key -> new ReferenceOpenHashSet())).add(newLayer);
         return super.getBuffer(newLayer);
      } else {
         return super.getBuffer(layer);
      }
   }

   @Override
   public void drawDirect(class_1921 layer) {
      this.currentlyDrawing = true;

      try {
         Set<class_1921> renderLayers = (Set<class_1921>)this.renderLayerMap.remove(layer);
         if (renderLayers != null) {
            for (class_1921 renderLayer : renderLayers) {
               super.drawDirect(renderLayer);
            }
         } else {
            super.drawDirect(layer);
         }
      } finally {
         this.currentlyDrawing = false;
      }
   }

   @Override
   public void method_22993() {
      super.method_22993();
      this.lightingRenderLayers.clear();
      this.renderLayerMap.clear();
   }

   @Override
   public void close() {
      super.close();
      this.lightingRenderLayers.clear();
      this.renderLayerMap.clear();
   }
}
