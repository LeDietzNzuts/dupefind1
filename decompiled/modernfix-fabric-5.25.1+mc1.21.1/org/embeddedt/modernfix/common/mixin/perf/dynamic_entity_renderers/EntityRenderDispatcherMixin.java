package org.embeddedt.modernfix.common.mixin.perf.dynamic_entity_renderers;

import java.util.Map;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_897;
import net.minecraft.class_898;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.entity.EntityRendererMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_898.class)
@ClientOnlyMixin
public class EntityRenderDispatcherMixin {
   @Shadow
   private Map<class_1299<?>, class_897<?>> field_4696;
   private EntityRendererMap mfix$dynamicRenderers;

   @Inject(method = "getRenderer", at = @At("RETURN"), cancellable = true)
   private <T extends class_1297> void checkNullness(T entity, CallbackInfoReturnable<class_897<? super T>> cir) {
      if (cir.getReturnValue() == null) {
         cir.setReturnValue(this.mfix$dynamicRenderers.get(entity.method_5864()));
      }
   }

   @Redirect(
      method = "onResourceManagerReload",
      at = @At(value = "FIELD", opcode = 181, target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;renderers:Ljava/util/Map;")
   )
   private void setRendererField(class_898 instance, Map<class_1299<?>, class_897<?>> incomingMap) {
      this.field_4696 = incomingMap;
      this.mfix$dynamicRenderers = (EntityRendererMap)incomingMap;
   }
}
