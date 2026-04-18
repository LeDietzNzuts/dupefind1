package org.embeddedt.modernfix.common.mixin.perf.model_optimizations;

import java.util.Objects;
import net.minecraft.class_4590;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4590.class)
@ClientOnlyMixin
public class TransformationMatrixMixin {
   @Shadow
   @Final
   private Matrix4f field_20900;
   private Integer cachedHashCode = null;

   @Overwrite(remap = false)
   @Override
   public int hashCode() {
      int hash;
      if (this.cachedHashCode != null) {
         hash = this.cachedHashCode;
      } else {
         hash = Objects.hashCode(this.field_20900);
         this.cachedHashCode = hash;
      }

      return hash;
   }
}
