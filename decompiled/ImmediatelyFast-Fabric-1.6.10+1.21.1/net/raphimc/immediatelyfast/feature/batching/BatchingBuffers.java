package net.raphimc.immediatelyfast.feature.batching;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.SequencedMap;
import java.util.Set;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_9799;
import net.minecraft.class_4597.class_4598;

public class BatchingBuffers {
   private static class_4598 nonBatchingEntityVertexConsumers;
   private static HudBatchingBufferSource hudBatchingVertexConsumers;
   private static boolean isHudBatching;

   public static class_4598 getNonBatchingEntityVertexConsumers() {
      if (nonBatchingEntityVertexConsumers == null) {
         SequencedMap<class_1921, class_9799> layerBuffers = createLayerBuffers(class_310.method_1551().method_22940().method_23000().field_20953.keySet());
         nonBatchingEntityVertexConsumers = new class_4598(new class_9799(786432), layerBuffers);
      }

      return nonBatchingEntityVertexConsumers;
   }

   public static class_4598 getHudBatchingVertexConsumers() {
      if (hudBatchingVertexConsumers == null) {
         SequencedMap<class_1921, class_9799> layerBuffers = createLayerBuffers(class_310.method_1551().method_22940().method_23000().field_20953.keySet());
         hudBatchingVertexConsumers = new HudBatchingBufferSource(new class_9799(786432), layerBuffers);
      }

      return hudBatchingVertexConsumers;
   }

   public static void runBatched(class_332 drawContext, Runnable runnable) {
      drawContext.method_51452();
      class_4598 prev = drawContext.field_44658;
      drawContext.field_44658 = getHudBatchingVertexConsumers();
      isHudBatching = true;

      try {
         runnable.run();
         drawContext.method_51452();
      } finally {
         drawContext.field_44658 = prev;
         isHudBatching = false;
      }
   }

   public static class_4598 beginHudBatching(class_332 drawContext) {
      drawContext.method_51452();
      class_4598 prev = drawContext.field_44658;
      drawContext.field_44658 = getHudBatchingVertexConsumers();
      isHudBatching = true;
      return prev;
   }

   public static void endHudBatching(class_332 drawContext, class_4598 prev) {
      drawContext.method_51452();
      drawContext.field_44658 = prev;
      isHudBatching = false;
   }

   public static boolean isHudBatching() {
      return isHudBatching;
   }

   public static void tryForceDrawHudBuffers() {
      if (!hudBatchingVertexConsumers.isCurrentlyDrawing() && hudBatchingVertexConsumers.hasActiveLayers()) {
         RenderSystemState renderSystemState = RenderSystemState.current();

         try {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            hudBatchingVertexConsumers.method_22993();
         } finally {
            renderSystemState.apply();
         }
      }
   }

   private static SequencedMap<class_1921, class_9799> createLayerBuffers(Set<class_1921> layers) {
      SequencedMap<class_1921, class_9799> layerBuffers = new Object2ObjectLinkedOpenHashMap(layers.size());

      for (class_1921 layer : layers) {
         layerBuffers.put(layer, new class_9799(layer.method_22722()));
      }

      return layerBuffers;
   }

   public static class WrappedRenderLayer extends class_1921 {
      public WrappedRenderLayer(class_1921 renderLayer, Runnable additionalStartAction, Runnable additionalEndAction) {
         super(
            renderLayer.field_21363,
            renderLayer.method_23031(),
            renderLayer.method_23033(),
            renderLayer.method_22722(),
            renderLayer.method_23037(),
            renderLayer.method_60894(),
            () -> {
               renderLayer.method_23516();
               additionalStartAction.run();
            },
            () -> {
               renderLayer.method_23518();
               additionalEndAction.run();
            }
         );
      }
   }
}
