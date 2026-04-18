package net.p3pp3rf1y.sophisticatedcore.upgrades;

import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.util.ItemStackHelper;

public interface IOverflowResponseUpgrade {
   default boolean stackMatchesFilterStack(class_1799 stack, class_1799 filterStack) {
      if (stack.method_7909() != filterStack.method_7909()) {
         return false;
      } else if (this.getFilterLogic().getPrimaryMatch() == PrimaryMatch.TAGS) {
         return true;
      } else {
         return this.getFilterLogic().shouldMatchDurability() && stack.method_7919() != filterStack.method_7919()
            ? false
            : !this.getFilterLogic().shouldMatchComponents() || ItemStackHelper.areItemStackComponentsEqualIgnoreDurability(stack, filterStack);
      }
   }

   FilterLogic getFilterLogic();

   boolean worksInGui();

   class_1799 onOverflow(class_1799 var1);

   boolean stackMatchesFilter(class_1799 var1);
}
