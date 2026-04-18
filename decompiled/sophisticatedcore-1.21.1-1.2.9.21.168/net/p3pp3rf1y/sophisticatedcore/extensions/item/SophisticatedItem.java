package net.p3pp3rf1y.sophisticatedcore.extensions.item;

import javax.annotation.Nullable;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1740;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1887;
import net.minecraft.class_3956;
import net.minecraft.class_4174;
import net.minecraft.class_6880;
import net.minecraft.class_9334;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

public interface SophisticatedItem {
   default float getXpRepairRatio(class_1799 stack) {
      return 1.0F;
   }

   default boolean onDroppedByPlayer(class_1799 stack, class_1657 player) {
      return true;
   }

   @OverrideOnly
   default int getBurnTime(class_1799 stack, @Nullable class_3956<?> recipeType) {
      Integer burnTime = (Integer)FuelRegistry.INSTANCE.get(stack.method_7909());
      return burnTime != null ? burnTime : 0;
   }

   default class_1269 onItemUseFirst(class_1799 stack, class_1838 context) {
      return class_1269.field_5811;
   }

   @Nullable
   default class_4174 getFoodProperties(class_1799 stack, @Nullable class_1309 entity) {
      return (class_4174)stack.method_57824(class_9334.field_50075);
   }

   @OverrideOnly
   default int getEnchantmentLevel(class_1799 stack, class_6880<class_1887> enchantment) {
      return stack.method_58657().method_57536(enchantment);
   }

   default boolean shouldCauseReequipAnimation(class_1799 oldStack, class_1799 newStack, boolean slotChanged) {
      return !oldStack.equals(newStack);
   }

   default boolean makesPiglinsNeutral(class_1799 stack, class_1309 wearer) {
      return stack.method_7909() instanceof class_1738 && ((class_1738)stack.method_7909()).method_7686() == class_1740.field_7895;
   }
}
