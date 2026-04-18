package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.class_1058;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_4603;
import net.minecraft.class_773;
import net.minecraft.class_2338.class_2339;
import net.p3pp3rf1y.sophisticatedcore.client.render.CustomParticleIcon;
import net.p3pp3rf1y.sophisticatedcore.util.model.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4603.class)
public class ScreenEffectRendererMixin {
   @Redirect(
      method = "renderScreenEffect",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/block/BlockModelShaper;getParticleIcon(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"
      )
   )
   private static class_1058 sophisticatedcore$renderEffectScreen$getParticleIcon(class_773 instance, class_2680 state, @Share("pos") LocalRef<class_2338> pos) {
      if (pos.get() != null && instance.method_3335(state) instanceof CustomParticleIcon model) {
         ModelData data = model.getModelData(class_310.method_1551().field_1687, (class_2338)pos.get(), state, ModelData.EMPTY);
         return model.getParticleIcon(data);
      } else {
         return instance.method_3339(state);
      }
   }

   @Inject(method = "getViewBlockingState", at = @At(value = "RETURN", ordinal = 0))
   private static void sophisticatedcore$getViewBlockingState(
      class_1657 player, CallbackInfoReturnable<class_2680> cir, @Local class_2339 mutableBlockPos, @Share("pos") LocalRef<class_2338> pos
   ) {
      pos.set(mutableBlockPos);
   }
}
