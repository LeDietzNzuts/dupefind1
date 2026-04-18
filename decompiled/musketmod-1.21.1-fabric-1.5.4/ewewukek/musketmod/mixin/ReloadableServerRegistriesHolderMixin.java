package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ILootTableId;
import net.minecraft.class_2960;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_9383.class_9385;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_9385.class)
abstract class ReloadableServerRegistriesHolderMixin {
   private class_2960 location;

   @Inject(method = "getLootTable", at = @At("HEAD"))
   private void getLootTableHead(class_5321<class_52> key, CallbackInfoReturnable<class_52> ci) {
      this.location = key.method_29177();
   }

   @Inject(method = "getLootTable", at = @At("RETURN"))
   private void getLootTable(CallbackInfoReturnable<class_52> ci) {
      ((ILootTableId)ci.getReturnValue()).setLocation(this.location);
   }
}
