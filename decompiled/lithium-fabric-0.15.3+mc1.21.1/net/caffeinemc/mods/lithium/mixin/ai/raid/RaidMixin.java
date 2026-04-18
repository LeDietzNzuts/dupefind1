package net.caffeinemc.mods.lithium.mixin.ai.raid;

import net.minecraft.class_3213;
import net.minecraft.class_3532;
import net.minecraft.class_3765;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3765.class)
public abstract class RaidMixin {
   @Shadow
   @Final
   private class_3213 field_16607;
   @Shadow
   private float field_16620;
   @Unique
   private boolean isBarDirty;

   @Shadow
   public abstract float method_16513();

   @Inject(method = "method_16509()V", at = @At("HEAD"))
   private void onTick(CallbackInfo ci) {
      if (this.isBarDirty) {
         this.field_16607.method_5408(class_3532.method_15363(this.method_16513() / this.field_16620, 0.0F, 1.0F));
         this.isBarDirty = false;
      }
   }

   @Overwrite
   public void method_16523() {
      this.isBarDirty = true;
   }
}
