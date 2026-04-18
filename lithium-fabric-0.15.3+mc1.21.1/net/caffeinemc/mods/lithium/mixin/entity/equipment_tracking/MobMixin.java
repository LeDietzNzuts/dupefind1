package net.caffeinemc.mods.lithium.mixin.entity.equipment_tracking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.caffeinemc.mods.lithium.common.entity.EquipmentEntity;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1308.class)
public abstract class MobMixin extends class_1297 implements EquipmentEntity {
   @Shadow
   private class_1799 field_48829;

   public MobMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @WrapOperation(
      method = "method_5749(Lnet/minecraft/class_2487;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2371;set(ILjava/lang/Object;)Ljava/lang/Object;"),
      require = 2
   )
   private <E> E trackEquipChange(class_2371<E> list, int index, E element, Operation<E> original) {
      E prevElement = (E)original.call(new Object[]{list, index, element});
      this.trackEquipChange(prevElement, element);
      return prevElement;
   }

   @Inject(method = "method_5749(Lnet/minecraft/class_2487;)V", at = {@At("HEAD"), @At("RETURN")})
   private void trackBodyArmor(class_2487 nbt, CallbackInfo ci, @Share("prevBodyArmor") LocalRef<class_1799> prevBodyArmorRef) {
      class_1799 prevBodyArmor = (class_1799)prevBodyArmorRef.get();
      if (prevBodyArmor == null) {
         prevBodyArmorRef.set(this.field_48829);
      } else if (prevBodyArmor != this.field_48829) {
         this.trackEquipChange(prevBodyArmor, this.field_48829);
      }
   }

   @Unique
   private <E> void trackEquipChange(E prevElement, E element) {
      if (!this.method_37908().method_8608() && element instanceof class_1799 newStack && prevElement instanceof class_1799 prevStack) {
         this.lithium$onEquipmentReplaced(prevStack, newStack);
      }
   }
}
