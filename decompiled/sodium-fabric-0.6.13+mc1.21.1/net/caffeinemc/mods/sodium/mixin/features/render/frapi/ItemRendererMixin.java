package net.caffeinemc.mods.sodium.mixin.features.render.frapi;

import net.caffeinemc.mods.sodium.client.render.frapi.render.ItemRenderContext;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_325;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_811;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_918.class)
public abstract class ItemRendererMixin {
   @Final
   @Shadow
   private class_325 field_4733;
   @Unique
   private final ItemRenderContext.VanillaModelBufferer vanillaBufferer = this::method_23182;
   @Unique
   private final ThreadLocal<ItemRenderContext> contexts = ThreadLocal.withInitial(() -> new ItemRenderContext(this.field_4733, this.vanillaBufferer));

   @Shadow
   protected abstract void method_23182(class_1087 var1, class_1799 var2, int var3, int var4, class_4587 var5, class_4588 var6);

   @Inject(
      method = "method_23179(Lnet/minecraft/class_1799;Lnet/minecraft/class_811;ZLnet/minecraft/class_4587;Lnet/minecraft/class_4597;IILnet/minecraft/class_1087;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1087;method_4713()Z"),
      cancellable = true
   )
   private void beforeRenderItem(
      class_1799 stack,
      class_811 transformMode,
      boolean invert,
      class_4587 matrixStack,
      class_4597 vertexConsumerProvider,
      int light,
      int overlay,
      class_1087 model,
      CallbackInfo ci
   ) {
      if (!((FabricBakedModel)model).isVanillaAdapter()) {
         this.contexts.get().renderModel(stack, transformMode, invert, matrixStack, vertexConsumerProvider, light, overlay, model);
         matrixStack.method_22909();
         ci.cancel();
      }
   }
}
