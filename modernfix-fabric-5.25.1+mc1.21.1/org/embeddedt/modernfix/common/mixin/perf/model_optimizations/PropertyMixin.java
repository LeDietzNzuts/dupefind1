package org.embeddedt.modernfix.common.mixin.perf.model_optimizations;

import net.minecraft.class_2769;
import org.embeddedt.modernfix.dedup.IdentifierCaches;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2769.class)
public class PropertyMixin {
   @Shadow
   @Mutable
   @Final
   private String field_24743;
   @Shadow
   private Integer field_24744;
   @Shadow
   @Final
   private Class field_24742;

   @Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/state/properties/Property;name:Ljava/lang/String;"))
   private void internName(class_2769 instance, String name) {
      this.field_24743 = IdentifierCaches.PROPERTY.deduplicate(name);
   }

   @Overwrite(remap = false)
   @Override
   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         return !(p_equals_1_ instanceof class_2769<?> property)
            ? false
            : this.field_24742 == property.method_11902() && this.field_24743 == property.method_11899();
      }
   }
}
