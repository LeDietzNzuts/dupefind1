package org.embeddedt.modernfix.common.mixin.perf.faster_item_rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1087;
import net.minecraft.class_1093;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_804;
import net.minecraft.class_811;
import net.minecraft.class_918;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.render.FastItemRenderType;
import org.embeddedt.modernfix.render.RenderState;
import org.embeddedt.modernfix.render.SimpleItemModelView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_918.class, priority = 600)
@ClientOnlyMixin
public abstract class ItemRendererMixin {
   private class_811 transformType;
   private final SimpleItemModelView modelView = new SimpleItemModelView();

   @Inject(method = "render", at = @At("HEAD"))
   private void markRenderingType(
      class_1799 itemStack,
      class_811 transformType,
      boolean leftHand,
      class_4587 matrixStack,
      class_4597 buffer,
      int combinedLight,
      int combinedOverlay,
      class_1087 model,
      CallbackInfo ci
   ) {
      this.transformType = transformType;
   }

   @ModifyArg(
      method = "render",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"
      ),
      index = 0
   )
   private class_1087 useSimpleWrappedItemModel(
      class_1087 model,
      class_1799 stack,
      int combinedLight,
      int combinedOverlay,
      class_4587 matrixStack,
      class_4588 buffer,
      @Local(ordinal = 0) class_1087 originalModel
   ) {
      if (originalModel != null && originalModel.getClass() != class_1093.class) {
         return model;
      } else if (!RenderState.IS_RENDERING_LEVEL && !stack.method_7960() && model.getClass() == class_1093.class && this.transformType == class_811.field_4317) {
         class_804 transform = model.method_4709().field_4300;
         FastItemRenderType type;
         if (transform == class_804.field_4284) {
            type = FastItemRenderType.SIMPLE_ITEM;
         } else {
            if (!(stack.method_7909() instanceof class_1747) || !this.isBlockTransforms(transform)) {
               return model;
            }

            type = FastItemRenderType.SIMPLE_BLOCK;
         }

         this.modelView.setItem(model);
         this.modelView.setType(type);
         return this.modelView;
      } else {
         return model;
      }
   }

   private boolean isBlockTransforms(class_804 transform) {
      return transform.field_4287.x() == 30.0F && transform.field_4287.y() == 225.0F && transform.field_4287.z() == 0.0F;
   }
}
