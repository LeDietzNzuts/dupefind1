package net.caffeinemc.mods.lithium.mixin.entity.equipment_tracking.enchantment_ticking;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.caffeinemc.mods.lithium.common.entity.EquipmentEntity;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_3218;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import net.minecraft.class_9701;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin implements EquipmentEntity.TickableEnchantmentTrackingEntity {
   @Unique
   private boolean maybeHasTickableEnchantments = this instanceof class_1657;

   @WrapWithCondition(
      method = "method_5670()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1890;method_60154(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)V")
   )
   private boolean maybeHasAnyTickableEnchantments(class_3218 world, class_1309 user) {
      return this.maybeHasTickableEnchantments;
   }

   @Override
   public void lithium$updateHasTickableEnchantments(class_1799 oldStack, class_1799 newStack) {
      if (!this.maybeHasTickableEnchantments) {
         this.maybeHasTickableEnchantments = stackHasTickableEnchantment(newStack);
      }
   }

   public void lithium$notifyAfterEnchantmentChange(class_1799 publisher, int subscriberData) {
      if (!this.maybeHasTickableEnchantments) {
         this.maybeHasTickableEnchantments = stackHasTickableEnchantment(publisher);
      }
   }

   @Unique
   private static boolean stackHasTickableEnchantment(class_1799 stack) {
      if (!stack.method_7960()) {
         class_9304 enchantments = (class_9304)stack.method_57824(class_9334.field_49633);
         if (enchantments != null && !enchantments.method_57543()) {
            for (class_6880<class_1887> enchantmentEntry : enchantments.method_57534()) {
               if (!((class_1887)enchantmentEntry.comp_349()).method_60034(class_9701.field_51671).isEmpty()) {
                  return true;
               }
            }

            return false;
         }
      }

      return false;
   }
}
