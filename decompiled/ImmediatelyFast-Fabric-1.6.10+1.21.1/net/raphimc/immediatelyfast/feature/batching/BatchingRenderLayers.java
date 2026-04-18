package net.raphimc.immediatelyfast.feature.batching;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.BiFunction;
import net.minecraft.class_156;
import net.minecraft.class_1921;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;

public class BatchingRenderLayers {
   public static final BiFunction<Integer, BlendFuncDepthFuncState, class_1921> TEXTURE = class_156.method_34865(
      (id, blendFuncDepthFunc) -> new BatchingRenderLayers.ImmediatelyFastRenderLayer("texture", class_5596.field_27382, class_290.field_1585, false, () -> {
         blendFuncDepthFunc.saveAndApply();
         RenderSystem.setShaderTexture(0, id);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(class_757::method_34542);
      }, blendFuncDepthFunc::revert)
   );
   public static final BiFunction<Integer, BlendFuncDepthFuncState, class_1921> COLORED_TEXTURE = class_156.method_34865(
      (id, blendFuncDepthFunc) -> new BatchingRenderLayers.ImmediatelyFastRenderLayer(
         "colored_texture", class_5596.field_27382, class_290.field_1575, false, () -> {
            blendFuncDepthFunc.saveAndApply();
            RenderSystem.setShaderTexture(0, id);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(class_757::method_34543);
         }, blendFuncDepthFunc::revert
      )
   );

   private static class ImmediatelyFastRenderLayer extends class_1921 {
      private ImmediatelyFastRenderLayer(
         String name, class_5596 drawMode, class_293 vertexFormat, boolean translucent, Runnable startAction, Runnable endAction
      ) {
         super("immediatelyfast_" + name, vertexFormat, drawMode, 2048, false, translucent, startAction, endAction);
      }
   }
}
