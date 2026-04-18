package vectorwing.farmersdelight.common.loot.modifier;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.refabricated.LootModifier;

public class ReplaceItemModifier extends LootModifier {
   private final class_1792 removedItem;
   private final class_1792 addedItem;
   private final int addedCount;

   public ReplaceItemModifier(class_5341[] conditions, class_1792 removedItem, class_1792 addedItem, int addedCount) {
      super(conditions);
      this.removedItem = removedItem;
      this.addedItem = addedItem;
      this.addedCount = addedCount;
   }

   @NotNull
   @Override
   protected ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> generatedLoot, class_47 lootContext) {
      class_1799 addedStack = new class_1799(this.addedItem, this.addedCount);
      generatedLoot.forEach(item -> {
         if (item.method_31574(this.removedItem)) {
            generatedLoot.remove(item);
         }
      });
      if (addedStack.method_7947() < addedStack.method_7914()) {
         generatedLoot.add(addedStack);
      } else {
         int i = addedStack.method_7947();

         while (i > 0) {
            class_1799 subStack = addedStack.method_7972();
            subStack.method_7939(Math.min(addedStack.method_7914(), i));
            i -= subStack.method_7947();
            generatedLoot.add(subStack);
         }
      }

      return generatedLoot;
   }
}
