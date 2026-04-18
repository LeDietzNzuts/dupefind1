package net.raphimc.immediatelyfast.feature.core;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import java.util.Arrays;
import java.util.Map;
import java.util.SequencedMap;
import java.util.Set;
import net.minecraft.class_1006;
import net.minecraft.class_1921;
import net.minecraft.class_287;
import net.minecraft.class_2960;
import net.minecraft.class_4588;
import net.minecraft.class_4722;
import net.minecraft.class_9799;
import net.minecraft.class_1921.class_4687;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.compat.IrisCompat;

public class BatchableBufferSource extends class_4598 implements AutoCloseable {
   private static final class_9799 FALLBACK_BUFFER = new class_9799(0);
   protected final Map<class_1921, ReferenceSet<class_287>> pendingBuffers = (Map<class_1921, ReferenceSet<class_287>>)(IrisCompat.IRIS_LOADED
      ? new Object2ObjectLinkedOpenHashMap()
      : new Reference2ObjectLinkedOpenHashMap());
   protected final Set<class_1921> activeLayers = (Set<class_1921>)(IrisCompat.IRIS_LOADED ? new ObjectLinkedOpenHashSet() : new ReferenceLinkedOpenHashSet());
   protected boolean drawFallbackLayersFirst = false;

   public BatchableBufferSource() {
      this(Object2ObjectSortedMaps.emptyMap());
   }

   public BatchableBufferSource(SequencedMap<class_1921, class_9799> layerBuffers) {
      this(FALLBACK_BUFFER, layerBuffers);
   }

   public BatchableBufferSource(class_9799 fallbackBuffer, SequencedMap<class_1921, class_9799> layerBuffers) {
      super(fallbackBuffer, layerBuffers);
   }

   public class_4588 getBuffer(class_1921 layer) {
      if (!this.drawFallbackLayersFirst && this.field_52158 != null && this.field_52158 != layer && !this.field_20953.containsKey(this.field_52158)) {
         this.drawFallbackLayersFirst = true;
      }

      if (IrisCompat.IRIS_LOADED) {
         IrisCompat.skipExtension.set(!IrisCompat.isRenderingLevel.getAsBoolean());
      }

      boolean hasBufferForRenderLayer = layer.method_43332() && this.pendingBuffers.containsKey(layer);
      class_287 bufferBuilder;
      if (!layer.method_43332()) {
         bufferBuilder = new class_287(this.getNextBufferAllocator(), layer.method_23033(), layer.method_23031());
         this.field_52158 = layer;
      } else if (hasBufferForRenderLayer) {
         bufferBuilder = (class_287)this.pendingBuffers.get(layer).iterator().next();
      } else if (this.field_20953.containsKey(layer)) {
         bufferBuilder = new class_287((class_9799)this.field_20953.get(layer), layer.method_23033(), layer.method_23031());
      } else {
         bufferBuilder = new class_287(this.getNextBufferAllocator(), layer.method_23033(), layer.method_23031());
         this.field_52158 = layer;
      }

      if (IrisCompat.IRIS_LOADED) {
         IrisCompat.skipExtension.set(false);
      }

      if (!hasBufferForRenderLayer) {
         this.pendingBuffers.computeIfAbsent(layer, k -> new ReferenceLinkedOpenHashSet()).add(bufferBuilder);
      }

      if (hasBufferForRenderLayer) {
         if ((ImmediatelyFast.config.debug_only_use_last_usage_for_batch_ordering || layer.field_21363.contains("immediatelyfast:renderlast"))
            && this.activeLayers.contains(layer)) {
            this.activeLayers.remove(layer);
            this.activeLayers.add(layer);
         }
      } else {
         this.activeLayers.add(layer);
      }

      return bufferBuilder;
   }

   public void method_37104() {
      this.field_52158 = null;
      this.drawFallbackLayersFirst = false;
      int sortedLayersLength = 0;
      class_1921[] sortedLayers = new class_1921[this.activeLayers.size()];

      for (class_1921 layer : this.activeLayers) {
         if (!this.field_20953.containsKey(layer)) {
            sortedLayers[sortedLayersLength++] = layer;
         }
      }

      if (sortedLayersLength != 0) {
         Arrays.sort(sortedLayers, (l1, l2) -> Integer.compare(this.getLayerOrder(l1), this.getLayerOrder(l2)));

         for (int i = 0; i < sortedLayersLength; i++) {
            this.method_22994(sortedLayers[i]);
         }
      }
   }

   public void method_22993() {
      if (this.activeLayers.isEmpty()) {
         this.close();
      } else {
         this.method_37104();

         for (class_1921 layer : this.field_20953.keySet()) {
            this.method_22994(layer);
         }
      }
   }

   public void method_22994(class_1921 layer) {
      if (this.drawFallbackLayersFirst) {
         this.method_37104();
      }

      this.drawDirect(layer);
   }

   @Override
   public void close() {
      this.field_52158 = null;
      this.drawFallbackLayersFirst = false;

      for (Set<class_287> buffers : this.pendingBuffers.values()) {
         for (class_287 bufferBuilder : buffers) {
            bufferBuilder.method_60794().close();
            BufferAllocatorPool.returnBufferAllocatorSafe(bufferBuilder.field_52071);
         }
      }

      this.activeLayers.clear();
      this.pendingBuffers.clear();
   }

   public void drawDirect(class_1921 layer) {
      if (IrisCompat.IRIS_LOADED && !IrisCompat.isRenderingLevel.getAsBoolean()) {
         IrisCompat.renderWithExtendedVertexFormat.accept(false);
      }

      this.activeLayers.remove(layer);
      Set<class_287> buffers = (Set<class_287>)this.pendingBuffers.remove(layer);
      if (buffers != null) {
         for (class_287 bufferBuilder : buffers) {
            class_9799 prevBufferAllocator = this.field_52156;
            this.field_52156 = bufferBuilder.field_52071;
            this.method_60893(layer, bufferBuilder);
            this.field_52156 = prevBufferAllocator;
            BufferAllocatorPool.returnBufferAllocatorSafe(bufferBuilder.field_52071);
         }
      }

      if (this.field_52158 == layer) {
         this.field_52158 = null;
      }

      if (IrisCompat.IRIS_LOADED && !IrisCompat.isRenderingLevel.getAsBoolean()) {
         IrisCompat.renderWithExtendedVertexFormat.accept(true);
      }
   }

   public boolean hasActiveLayers() {
      return !this.activeLayers.isEmpty();
   }

   protected int getLayerOrder(class_1921 layer) {
      if (layer == null) {
         return Integer.MAX_VALUE;
      } else {
         int order = 0;
         if (layer instanceof class_4687 multiPhase) {
            class_2960 textureId = (class_2960)multiPhase.method_35784().field_21406.method_23564().orElse(null);
            if (textureId != null) {
               if (textureId.method_12832().startsWith("textures/entity/horse/")) {
                  String horseTexturePath = textureId.method_12832().substring("textures/entity/horse/".length());
                  if (horseTexturePath.startsWith("horse_markings")) {
                     return 2;
                  }

                  if (horseTexturePath.startsWith("armor/")) {
                     return 3;
                  }

                  return 1;
               }

               if (textureId.toString().startsWith("minecraft:textures/entity/wolf/")) {
                  if (textureId.equals(class_1006.field_4913)) {
                     return 2;
                  }

                  return 1;
               }

               if (textureId.method_12832().startsWith("textures/entity/villager/")) {
                  String villagerTexturePath = textureId.method_12832().substring("textures/entity/villager/".length());
                  if (villagerTexturePath.startsWith("type/")) {
                     return 2;
                  }

                  if (villagerTexturePath.startsWith("profession/")) {
                     return 3;
                  }

                  return 1;
               }

               if (textureId.equals(class_4722.field_42071)) {
                  order = 1;
               } else if (!layer.field_21363.startsWith("text")
                  && !layer.field_21363.startsWith("neoforge_text")
                  && !layer.field_21363.startsWith("forge_text")) {
                  if (textureId.method_12836().equals("cataclysm")) {
                     if (textureId.method_12832().equals("textures/entity/maledictus/phantom_halberd.png")) {
                        return 2;
                     }

                     if (textureId.method_12832().equals("textures/entity/maledictus/phantom_halberd_discard.png")) {
                        return 1;
                     }
                  }
               } else if (textureId.method_12836().equals("minecraft")) {
                  order = 2;
               } else {
                  order = 1;
               }
            }
         }

         return !layer.method_60894() ? order : 100000000 + order;
      }
   }

   private class_9799 getNextBufferAllocator() {
      return this.field_52156 != FALLBACK_BUFFER && this.field_52158 == null && this.field_52156.field_52082 != 0L
         ? this.field_52156
         : BufferAllocatorPool.borrowBufferAllocator();
   }
}
