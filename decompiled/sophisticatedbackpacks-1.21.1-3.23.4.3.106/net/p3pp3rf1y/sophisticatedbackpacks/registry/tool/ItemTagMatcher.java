package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import java.util.function.Predicate;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_6862;

class ItemTagMatcher implements Predicate<class_1799> {
   private final class_6862<class_1792> itemTag;

   public ItemTagMatcher(class_6862<class_1792> itemTag) {
      this.itemTag = itemTag;
   }

   public boolean test(class_1799 stack) {
      return stack.method_31573(this.itemTag);
   }
}
