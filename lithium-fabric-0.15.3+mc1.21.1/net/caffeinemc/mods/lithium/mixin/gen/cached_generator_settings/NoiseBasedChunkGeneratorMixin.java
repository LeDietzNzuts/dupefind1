package net.caffeinemc.mods.lithium.mixin.gen.cached_generator_settings;

import net.minecraft.class_1966;
import net.minecraft.class_3754;
import net.minecraft.class_5284;
import net.minecraft.class_6880;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3754.class)
public class NoiseBasedChunkGeneratorMixin {
   @Shadow
   @Final
   private class_6880<class_5284> field_24774;
   private int cachedSeaLevel;

   @Overwrite
   public int method_16398() {
      return this.cachedSeaLevel;
   }

   @Inject(
      method = "<init>(Lnet/minecraft/class_1966;Lnet/minecraft/class_6880;)V",
      at = @At(
         value = "INVOKE",
         target = "Lcom/google/common/base/Suppliers;memoize(Lcom/google/common/base/Supplier;)Lcom/google/common/base/Supplier;",
         remap = false,
         shift = Shift.BEFORE
      )
   )
   private void hookConstructor(class_1966 biomeSource, class_6880<class_5284> settings, CallbackInfo ci) {
      this.cachedSeaLevel = ((class_5284)this.field_24774.comp_349()).comp_479();
   }
}
