package net.caffeinemc.mods.lithium.mixin.entity.equipment_tracking;

import net.caffeinemc.mods.lithium.common.entity.EquipmentEntity;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 implements ChangeSubscriber.CountChangeSubscriber<class_1799>, EquipmentEntity {
   public LivingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Inject(
      method = "method_6116(Lnet/minecraft/class_1304;Lnet/minecraft/class_1799;Lnet/minecraft/class_1799;)V",
      require = 1,
      allow = 1,
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_1309;field_5953:Z")
   )
   private void handleStackEquip(class_1304 slot, class_1799 oldStack, class_1799 newStack, CallbackInfo ci) {
      if (!this.method_37908().method_8608()) {
         this.lithium$onEquipmentReplaced(oldStack, newStack);
      }
   }

   public void lithium$notify(@Nullable class_1799 publisher, int zero) {
      if (this instanceof EquipmentEntity.EquipmentTrackingEntity equipmentTrackingEntity) {
         equipmentTrackingEntity.lithium$onEquipmentChanged();
      }
   }

   public void lithium$notifyCount(class_1799 publisher, int zero, int newCount) {
      if (newCount == 0) {
         ((ChangePublisher)publisher).lithium$unsubscribeWithData(this, zero);
      }

      this.lithium$onEquipmentReplaced(publisher, class_1799.field_8037);
   }

   public void lithium$forceUnsubscribe(class_1799 publisher, int zero) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void lithium$onEquipmentReplaced(class_1799 oldStack, class_1799 newStack) {
      if (this instanceof EquipmentEntity.TickableEnchantmentTrackingEntity enchantmentTrackingEntity) {
         enchantmentTrackingEntity.lithium$updateHasTickableEnchantments(oldStack, newStack);
      }

      if (this instanceof EquipmentEntity.EquipmentTrackingEntity equipmentTrackingEntity) {
         equipmentTrackingEntity.lithium$onEquipmentChanged();
      }

      if (!oldStack.method_7960()) {
         ((ChangePublisher)oldStack).lithium$unsubscribeWithData(this, 0);
      }

      if (!newStack.method_7960()) {
         ((ChangePublisher)newStack).lithium$subscribe(this, 0);
      }
   }
}
