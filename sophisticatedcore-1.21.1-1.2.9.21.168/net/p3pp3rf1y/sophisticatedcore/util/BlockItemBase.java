package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.function.Consumer;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_1792.class_1793;
import net.p3pp3rf1y.sophisticatedcore.Config;

public class BlockItemBase extends class_1747 {
   public BlockItemBase(class_2248 block, class_1793 properties) {
      super(block, properties);
   }

   public void addCreativeTabItems(Consumer<class_1799> itemConsumer) {
      if (Config.COMMON.enabledItems.isItemEnabled(this) && this.method_7711() instanceof BlockBase blockBase) {
         blockBase.addCreativeTabItems(itemConsumer);
      }
   }
}
