package org.embeddedt.modernfix.common.mixin.bugfix.chunk_deadlock;

import net.minecraft.class_1922;
import net.minecraft.class_4970.class_4971;
import org.embeddedt.modernfix.chunk.SafeBlockGetter;
import org.embeddedt.modernfix.duck.ISafeBlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = class_4971.class, priority = 100)
public class BlockStateBaseMixin {
   @ModifyVariable(method = "getOffset", at = @At("HEAD"), argsOnly = true, index = 1)
   private class_1922 useSafeGetter(class_1922 g) {
      if (g instanceof ISafeBlockGetter) {
         SafeBlockGetter replacement = ((ISafeBlockGetter)g).mfix$getSafeBlockGetter();
         if (replacement.shouldUse()) {
            return replacement;
         }
      }

      return g;
   }
}
