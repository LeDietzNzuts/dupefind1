package net.caffeinemc.mods.sodium.mixin.features.render.frapi;

import net.caffeinemc.mods.sodium.client.render.frapi.render.NonTerrainBlockRenderContext;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_324;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5819;
import net.minecraft.class_778;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_778.class)
public abstract class ModelBlockRendererMixin {
   @Shadow
   @Final
   private class_324 field_4178;
   @Unique
   private final ThreadLocal<NonTerrainBlockRenderContext> contexts = ThreadLocal.withInitial(() -> new NonTerrainBlockRenderContext(this.field_4178));

   @Inject(
      method = "method_3374(Lnet/minecraft/class_1920;Lnet/minecraft/class_1087;Lnet/minecraft/class_2680;Lnet/minecraft/class_2338;Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;ZLnet/minecraft/class_5819;JI)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void onRender(
      class_1920 blockView,
      class_1087 model,
      class_2680 state,
      class_2338 pos,
      class_4587 matrix,
      class_4588 buffer,
      boolean cull,
      class_5819 rand,
      long seed,
      int overlay,
      CallbackInfo ci
   ) {
      if (!((FabricBakedModel)model).isVanillaAdapter()) {
         this.contexts.get().renderModel(blockView, model, state, pos, matrix, buffer, cull, rand, seed, overlay);
         ci.cancel();
      }
   }
}
