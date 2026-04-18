package cn.enaium.onekeyminer.screen;

import java.util.Collection;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_350;
import net.minecraft.class_6382;
import net.minecraft.class_350.class_351;

public class ListWidget<T extends ListWidget.Entry<T>> extends class_350<T> {
   public ListWidget(class_310 client, int width, int height, int y, int itemHeight) {
      super(client, width, height, y, itemHeight);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      for (int i = 0; i < this.method_25396().size(); i++) {
         if (((ListWidget.Entry)this.method_25396().get(i)).hovered) {
            this.method_25313((ListWidget.Entry)this.method_25396().get(i));
         }
      }

      return super.method_25402(mouseX, mouseY, button);
   }

   protected boolean removeEntry(T entry) {
      return super.method_25330(entry);
   }

   protected void method_47399(class_6382 builder) {
   }

   public int addEntry(T entry) {
      return super.method_25321(entry);
   }

   protected void method_25314(Collection<T> newEntries) {
      super.method_25314(newEntries);
   }

   public static class Entry<E extends class_351<E>> extends class_351<E> {
      public boolean hovered = false;

      public void method_25343(
         class_332 context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta
      ) {
         this.hovered = hovered;
      }
   }
}
