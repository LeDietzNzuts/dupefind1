package vectorwing.farmersdelight.common.block.entity;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import net.minecraft.class_1263;
import net.minecraft.class_1301;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_265;

public interface Basket extends class_1263 {
   class_265[] COLLECTION_AREA_SHAPES = new class_265[]{
      class_2248.method_9541(0.0, -16.0, 0.0, 16.0, 16.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 32.0, 16.0),
      class_2248.method_9541(0.0, 0.0, -16.0, 16.0, 16.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 32.0),
      class_2248.method_9541(-16.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 32.0, 16.0, 16.0)
   };

   default class_265 getFacingCollectionArea(int facingIndex) {
      return COLLECTION_AREA_SHAPES[facingIndex];
   }

   double getLevelX();

   double getLevelY();

   double getLevelZ();

   void setCooldown(int var1);

   boolean isOnCooldown();

   boolean isOnCustomCooldown();

   void tryTransfer(BooleanSupplier var1);

   default boolean collectItems(class_1937 level, int facingIndex) {
      for (class_1542 itementity : this.getItemsToCollect(level, facingIndex)) {
         if (this.collectItem(itementity)) {
            return true;
         }
      }

      return false;
   }

   default List<class_1542> getItemsToCollect(class_1937 level, int facingIndex) {
      return this.getFacingCollectionArea(facingIndex)
         .method_1090()
         .stream()
         .flatMap(
            aabb -> level.method_8390(
                  class_1542.class, aabb.method_989(this.getLevelX() - 0.5, this.getLevelY() - 0.5, this.getLevelZ() - 0.5), class_1301.field_6154
               )
               .stream()
         )
         .collect(Collectors.toList());
   }

   default boolean collectItem(class_1542 itemEntity) {
      boolean flag = false;
      class_1799 entityItemStack = itemEntity.method_6983().method_7972();
      class_1799 remainderStack = this.insert(entityItemStack);
      if (remainderStack.method_7960()) {
         flag = true;
         itemEntity.method_31472();
      } else {
         itemEntity.method_6979(remainderStack);
      }

      return flag;
   }

   default class_1799 insert(class_1799 stack) {
      int size = this.method_5439();

      for (int slot = 0; slot < size && !stack.method_7960(); slot++) {
         stack = this.insert(slot, stack);
      }

      return stack;
   }

   default class_1799 insert(int slot, class_1799 stack) {
      class_1799 slotStack = this.method_5438(slot);
      if (this.method_5437(slot, stack)) {
         boolean inserted = false;
         if (slotStack.method_7960()) {
            this.method_5447(slot, stack);
            stack = class_1799.field_8037;
            inserted = true;
         } else if (canMergeItems(slotStack, stack)) {
            int insertCount = stack.method_7914() - slotStack.method_7947();
            insertCount = Math.min(stack.method_7947(), insertCount);
            stack.method_7934(insertCount);
            slotStack.method_7933(insertCount);
            inserted = insertCount > 0;
         }

         if (inserted) {
            if (this.method_5442() && !this.isOnCustomCooldown()) {
               this.setCooldown(8);
            }

            this.method_5431();
         }
      }

      return stack;
   }

   static boolean canMergeItems(class_1799 stack1, class_1799 stack2) {
      return stack1.method_7947() <= stack1.method_7914() && class_1799.method_31577(stack1, stack2);
   }
}
