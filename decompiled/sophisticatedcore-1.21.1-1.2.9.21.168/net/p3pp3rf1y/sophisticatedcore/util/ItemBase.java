package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.function.Consumer;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1792.class_1793;
import net.p3pp3rf1y.sophisticatedcore.Config;

public class ItemBase extends class_1792 {
   public ItemBase(class_1793 properties) {
      super(properties);
   }

   public void addCreativeTabItems(Consumer<class_1799> itemConsumer) {
      if (Config.COMMON.enabledItems.isItemEnabled(this)) {
         itemConsumer.accept(new class_1799(this));
      }
   }
}
