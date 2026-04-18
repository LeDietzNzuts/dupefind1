package vectorwing.farmersdelight.common.loot.modifier;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.refabricated.LootModifier;

public class AddItemModifier extends LootModifier {
   private final class_1792 addedItem;
   private final int count;

   public AddItemModifier(class_5341[] conditionsIn, class_1792 addedItemIn, int count) {
      super(conditionsIn);
      this.addedItem = addedItemIn;
      this.count = count;
   }

   @NotNull
   @Override
   protected ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> generatedLoot, class_47 context) {
      class_1799 addedStack = new class_1799(this.addedItem, this.count);
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
