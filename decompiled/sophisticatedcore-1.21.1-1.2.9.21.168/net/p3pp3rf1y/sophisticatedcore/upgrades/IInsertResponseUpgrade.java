package net.p3pp3rf1y.sophisticatedcore.upgrades;

import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;

public interface IInsertResponseUpgrade {
   class_1799 onBeforeInsert(IItemHandlerSimpleInserter var1, int var2, class_1799 var3, boolean var4);

   void onAfterInsert(IItemHandlerSimpleInserter var1, int var2);
}
