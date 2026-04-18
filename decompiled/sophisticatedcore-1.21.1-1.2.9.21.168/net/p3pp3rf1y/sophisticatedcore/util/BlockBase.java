package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_4970.class_2251;

public abstract class BlockBase extends class_2248 {
   public BlockBase(class_2251 properties) {
      super(properties);
   }

   public void addCreativeTabItems(Consumer<class_1799> itemConsumer) {
      itemConsumer.accept(new class_1799(this));
   }
}
