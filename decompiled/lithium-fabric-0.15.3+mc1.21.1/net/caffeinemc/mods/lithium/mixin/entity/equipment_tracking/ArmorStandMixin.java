package net.caffeinemc.mods.lithium.mixin.entity.equipment_tracking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.lithium.common.entity.EquipmentEntity;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1531;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1531.class)
public abstract class ArmorStandMixin extends class_1297 implements EquipmentEntity {
   public ArmorStandMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @WrapOperation(
      method = {"method_5749(Lnet/minecraft/class_2487;)V", "method_6908(Lnet/minecraft/class_3218;Lnet/minecraft/class_1282;)V"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2371;set(ILjava/lang/Object;)Ljava/lang/Object;"),
      require = 4
   )
   private <E> E trackEquipChange(class_2371<E> list, int index, E element, Operation<E> original) {
      E prevElement = (E)original.call(new Object[]{list, index, element});
      this.trackEquipChange(prevElement, element);
      return prevElement;
   }

   @Unique
   private <E> void trackEquipChange(E prevElement, E element) {
      if (!this.method_37908().method_8608() && element instanceof class_1799 newStack && prevElement instanceof class_1799 prevStack) {
         this.lithium$onEquipmentReplaced(prevStack, newStack);
      }
   }
}
