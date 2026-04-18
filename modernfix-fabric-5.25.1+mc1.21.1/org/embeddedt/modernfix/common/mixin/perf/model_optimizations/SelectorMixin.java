package org.embeddedt.modernfix.common.mixin.perf.model_optimizations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_819;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_819.class)
@ClientOnlyMixin
public class SelectorMixin {
   private ConcurrentHashMap<class_2689<class_2248, class_2680>, Predicate<class_2680>> predicateCache = new ConcurrentHashMap<>();

   @Inject(method = "getPredicate", at = @At("HEAD"), cancellable = true)
   private void useCachedPredicate(class_2689<class_2248, class_2680> pState, CallbackInfoReturnable<Predicate<class_2680>> cir) {
      Predicate<class_2680> cached = this.predicateCache.get(pState);
      if (cached != null) {
         cir.setReturnValue(cached);
      }
   }

   @Inject(method = "getPredicate", at = @At("RETURN"))
   private void storeCachedPredicate(class_2689<class_2248, class_2680> pState, CallbackInfoReturnable<Predicate<class_2680>> cir) {
      this.predicateCache.put(pState, (Predicate<class_2680>)cir.getReturnValue());
   }
}
