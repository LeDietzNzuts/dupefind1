package ewewukek.musketmod.mixin;

import ewewukek.musketmod.GunItem;
import net.minecraft.class_1306;
import net.minecraft.class_1308;
import net.minecraft.class_606;
import net.minecraft.class_572.class_573;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_606.class)
abstract class SkeletonModelMixin {
   private class_1308 mob;

   @Inject(method = "prepareMobModel", at = @At("HEAD"))
   private void prepareMobModelHead(class_1308 mob, float f, float g, float h, CallbackInfo ci) {
      this.mob = mob;
   }

   @Inject(method = "prepareMobModel", at = @At("TAIL"))
   private void prepareMobModelTail(CallbackInfo ci) {
      if (GunItem.isHoldingGun(this.mob) && this.mob.method_6115()) {
         class_606<?> model = (class_606<?>)this;
         if (this.mob.method_6068() == class_1306.field_6183) {
            model.field_3395 = class_573.field_3405;
         } else {
            model.field_3399 = class_573.field_3405;
         }
      }
   }

   @Inject(
      method = "setupAnim",
      cancellable = true,
      at = @At(value = "INVOKE", shift = Shift.BEFORE, target = "Lnet/minecraft/world/entity/Mob;getMainHandItem()Lnet/minecraft/world/item/ItemStack;")
   )
   private void setupAnim(CallbackInfo ci) {
      if (this.mob != null && GunItem.isHoldingGun(this.mob)) {
         ci.cancel();
      }
   }
}
