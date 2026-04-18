package net.caffeinemc.mods.lithium.mixin.shapes.lazy_shape_context;

import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_3610;
import net.minecraft.class_3727;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3727.class)
public class EntityCollisionContextMixin {
   @Mutable
   @Shadow
   @Final
   private class_1799 field_17594;
   @Mutable
   @Shadow
   @Final
   private Predicate<class_3610> field_24425;
   @Shadow
   @Final
   @Nullable
   private class_1297 field_27935;

   @ModifyConstant(method = "<init>(Lnet/minecraft/class_1297;)V", constant = @Constant(classValue = class_1309.class, ordinal = 0))
   private static boolean redirectInstanceOf(Object obj, Class<?> clazz) {
      return false;
   }

   @ModifyConstant(method = "<init>(Lnet/minecraft/class_1297;)V", constant = @Constant(classValue = class_1309.class, ordinal = 2))
   private static boolean redirectInstanceOf2(Object obj, Class<?> clazz) {
      return false;
   }

   @Inject(
      method = "<init>(Lnet/minecraft/class_1297;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3727;<init>(ZDLnet/minecraft/class_1799;Ljava/util/function/Predicate;Lnet/minecraft/class_1297;)V",
         shift = Shift.AFTER
      )
   )
   private void initFields(class_1297 entity, CallbackInfo ci) {
      this.field_17594 = null;
      this.field_24425 = null;
   }

   @Inject(method = "method_17785(Lnet/minecraft/class_1792;)Z", at = @At("HEAD"))
   public void isHolding(class_1792 item, CallbackInfoReturnable<Boolean> cir) {
      this.initHeldItem();
   }

   @Intrinsic
   public class_1799 getHeldItem() {
      return this.field_17594;
   }

   @Inject(method = "getHeldItem", remap = false, at = @At("HEAD"))
   private void initHeldItem(CallbackInfoReturnable<class_1799> callbackInfoReturnable) {
      this.initHeldItem();
   }

   @Unique
   private void initHeldItem() {
      if (this.field_17594 == null) {
         this.field_17594 = this.field_27935 instanceof class_1309 ? ((class_1309)this.field_27935).method_6047() : class_1799.field_8037;
      }
   }

   @Inject(method = "method_27866(Lnet/minecraft/class_3610;Lnet/minecraft/class_3610;)Z", at = @At("HEAD"))
   public void canWalkOnFluid(class_3610 state, class_3610 fluidState, CallbackInfoReturnable<Boolean> cir) {
      if (this.field_24425 == null) {
         if (this.field_27935 instanceof class_1309 livingEntity) {
            this.field_24425 = livingEntity::method_26319;
         } else {
            this.field_24425 = liquid -> false;
         }
      }
   }
}
