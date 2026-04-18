package net.caffeinemc.mods.lithium.mixin.collections.attributes;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Set;
import net.minecraft.class_1324;
import net.minecraft.class_5131;
import net.minecraft.class_5132;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_5131.class)
public class AttributeMapMixin {
   @Mutable
   @Shadow
   @Final
   private Set<class_1324> field_51890;
   @Mutable
   @Shadow
   @Final
   private Set<class_1324> field_51889;

   @Inject(method = "<init>(Lnet/minecraft/class_5132;)V", at = @At("RETURN"))
   private void initCollections(class_5132 defaultAttributes, CallbackInfo ci) {
      this.field_51890 = new ReferenceOpenHashSet(0);
      this.field_51889 = new ReferenceOpenHashSet(0);
   }
}
