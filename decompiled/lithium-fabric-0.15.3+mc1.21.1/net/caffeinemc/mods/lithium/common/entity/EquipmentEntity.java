package net.caffeinemc.mods.lithium.common.entity;

import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_1799;

public interface EquipmentEntity {
   void lithium$onEquipmentReplaced(class_1799 var1, class_1799 var2);

   public interface EquipmentTrackingEntity {
      void lithium$onEquipmentChanged();
   }

   public interface TickableEnchantmentTrackingEntity extends ChangeSubscriber.EnchantmentSubscriber<class_1799> {
      void lithium$updateHasTickableEnchantments(class_1799 var1, class_1799 var2);
   }
}
