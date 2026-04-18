package org.embeddedt.modernfix.common.mixin.perf.compact_mojang_registries;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.class_7887;
import net.minecraft.class_7225.class_7874;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_7887.class)
public class VanillaRegistriesMixin {
   private static class_7874 STATIC_PROVIDER;

   @WrapMethod(method = "createLookup")
   private static class_7874 modernfix$memoizeLookup(Operation<class_7874> original) {
      synchronized (class_7887.class) {
         if (STATIC_PROVIDER == null) {
            STATIC_PROVIDER = (class_7874)original.call(new Object[0]);
         }

         return STATIC_PROVIDER;
      }
   }
}
