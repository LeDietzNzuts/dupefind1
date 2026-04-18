package net.caffeinemc.mods.lithium.mixin.entity.equipment_tracking.equipment_changes;

import java.util.Map;
import net.caffeinemc.mods.lithium.common.entity.EquipmentEntity;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 implements EquipmentEntity.EquipmentTrackingEntity {
   @Unique
   private boolean equipmentChanged = true;

   public LivingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Override
   public void lithium$onEquipmentChanged() {
      this.equipmentChanged = true;
   }

   @Inject(method = "method_30129()Ljava/util/Map;", at = @At("HEAD"), cancellable = true)
   private void skipSentEquipmentComparison(CallbackInfoReturnable<@Nullable Map<class_1304, class_1799>> cir) {
      if (!this.equipmentChanged) {
         cir.setReturnValue(null);
      }
   }

   @Inject(method = "method_30128()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1309;method_30121(Ljava/util/Map;)V"))
   private void resetEquipmentChanged(CallbackInfo ci) {
      if (!(this instanceof class_1657)) {
         this.equipmentChanged = false;
      }
   }
}
