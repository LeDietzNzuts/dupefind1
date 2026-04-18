package org.embeddedt.modernfix.common.mixin.perf.cache_model_materials;

import java.util.Collection;
import net.minecraft.class_2960;
import net.minecraft.class_816;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_816.class)
@ClientOnlyMixin
public class MultipartMixin {
   private Collection<class_2960> dependencyCache = null;

   @Inject(method = "getDependencies", at = @At("HEAD"), cancellable = true)
   private void useDependencyCache(CallbackInfoReturnable<Collection<class_2960>> cir) {
      if (this.dependencyCache != null) {
         cir.setReturnValue(this.dependencyCache);
      }
   }

   @Inject(method = "getDependencies", at = @At("RETURN"))
   private void storeDependencyCache(CallbackInfoReturnable<Collection<class_2960>> cir) {
      if (this.dependencyCache == null) {
         this.dependencyCache = (Collection<class_2960>)cir.getReturnValue();
      }
   }
}
