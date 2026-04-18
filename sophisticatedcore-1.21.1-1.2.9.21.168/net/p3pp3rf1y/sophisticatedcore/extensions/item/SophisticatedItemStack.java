package net.p3pp3rf1y.sophisticatedcore.extensions.item;

import javax.annotation.Nullable;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1887;
import net.minecraft.class_2338;
import net.minecraft.class_2694;
import net.minecraft.class_3468;
import net.minecraft.class_3956;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

public interface SophisticatedItemStack {
   private class_1799 self() {
      return (class_1799)this;
   }

   default float getXpRepairRatio() {
      return this.self().method_7909().getXpRepairRatio(this.self());
   }

   default boolean onDroppedByPlayer(class_1657 player) {
      return this.self().method_7909().onDroppedByPlayer(this.self(), player);
   }

   default int getBurnTime(@Nullable class_3956<?> recipeType) {
      if (this.self().method_7960()) {
         return 0;
      } else {
         int burnTime = this.self().method_7909().getBurnTime(this.self(), recipeType);
         if (burnTime < 0) {
            String itemId = String.valueOf(class_7923.field_41178.method_10221(this.self().method_7909()));
            throw new IllegalStateException("Stack of item " + itemId + " has a negative burn time");
         } else {
            return burnTime;
         }
      }
   }

   default class_1269 onItemUseFirst(class_1838 context) {
      class_1657 player = context.method_8036();
      class_2338 pos = context.method_8037();
      if (!player.method_31549().field_7476 && !this.self().method_57357(new class_2694(context.method_8045(), pos, false))) {
         return class_1269.field_5811;
      } else {
         class_1792 item = this.self().method_7909();
         class_1269 result = item.onItemUseFirst(this.self(), context);
         if (result == class_1269.field_5812) {
            player.method_7259(class_3468.field_15372.method_14956(item));
         }

         return result;
      }
   }

   @OverrideOnly
   default int getEnchantmentLevel(class_6880<class_1887> enchantment) {
      return this.self().method_7909().getEnchantmentLevel(this.self(), enchantment);
   }

   default boolean makesPiglinsNeutral(class_1309 wearer) {
      return this.self().method_7909().makesPiglinsNeutral(this.self(), wearer);
   }
}
