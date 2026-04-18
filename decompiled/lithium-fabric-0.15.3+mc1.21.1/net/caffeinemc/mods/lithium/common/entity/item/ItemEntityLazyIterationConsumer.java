package net.caffeinemc.mods.lithium.common.entity.item;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.mixin.util.accessors.ItemEntityAccessor;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_7927;
import net.minecraft.class_7927.class_7928;

public class ItemEntityLazyIterationConsumer implements class_7927<class_1542> {
   private final class_1799 stack;
   private final class_238 box;
   private final Predicate<class_1542> predicate;
   private final ArrayList<class_1542> mergeEntities;
   private final class_1542 searchingEntity;
   private int adjustedStackCount;

   public ItemEntityLazyIterationConsumer(class_1542 searchingEntity, class_238 box, Predicate<class_1542> predicate) {
      this.searchingEntity = searchingEntity;
      this.box = box;
      this.predicate = predicate;
      this.mergeEntities = new ArrayList<>();
      this.stack = this.searchingEntity.method_6983();
      this.adjustedStackCount = this.stack.method_7947();
   }

   public ArrayList<class_1542> getMergeEntities() {
      return this.mergeEntities;
   }

   public class_7928 accept(class_1542 otherItemEntity) {
      if (this.box.method_994(otherItemEntity.method_5829()) && this.predicate.test(otherItemEntity)) {
         int receivedItemCount = predictReceivedItemCount(this.searchingEntity, this.stack, this.adjustedStackCount, otherItemEntity);
         if (receivedItemCount != 0) {
            this.mergeEntities.add(otherItemEntity);
            this.adjustedStackCount += receivedItemCount;
            if (this.adjustedStackCount <= 0 || this.adjustedStackCount >= this.stack.method_7914()) {
               return class_7928.field_41284;
            }
         }

         return class_7928.field_41283;
      } else {
         return class_7928.field_41283;
      }
   }

   private static int predictReceivedItemCount(class_1542 thisEntity, class_1799 thisStack, int adjustedStackCount, class_1542 otherEntity) {
      class_1799 otherStack;
      if (!Objects.equals(((ItemEntityAccessor)thisEntity).lithium$getOwner(), ((ItemEntityAccessor)otherEntity).lithium$getOwner())
         || !class_1542.method_24017(thisStack, otherStack = otherEntity.method_6983())) {
         return 0;
      } else {
         return otherStack.method_7947() < adjustedStackCount
            ? getTransferAmount(thisStack.method_7914(), adjustedStackCount, otherStack.method_7947())
            : -getTransferAmount(otherStack.method_7914(), otherStack.method_7947(), adjustedStackCount);
      }
   }

   private static int getTransferAmount(int maxCount, int targetStackCount, int sourceStackCount) {
      return Math.min(Math.min(maxCount, 64) - targetStackCount, sourceStackCount);
   }
}
