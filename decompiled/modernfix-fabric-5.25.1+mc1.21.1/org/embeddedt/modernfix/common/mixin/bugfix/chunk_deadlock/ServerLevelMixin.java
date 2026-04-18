package org.embeddedt.modernfix.common.mixin.bugfix.chunk_deadlock;

import net.minecraft.class_3218;
import org.embeddedt.modernfix.chunk.SafeBlockGetter;
import org.embeddedt.modernfix.duck.ISafeBlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_3218.class)
public class ServerLevelMixin implements ISafeBlockGetter {
   @Unique
   private final SafeBlockGetter mfix$safeBlockGetter = new SafeBlockGetter((class_3218)this);

   @Override
   public SafeBlockGetter mfix$getSafeBlockGetter() {
      return this.mfix$safeBlockGetter;
   }
}
