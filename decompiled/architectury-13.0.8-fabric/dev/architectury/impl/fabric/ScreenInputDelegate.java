package dev.architectury.impl.fabric;

import dev.architectury.event.events.client.ClientScreenInputEvent;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_437;

public interface ScreenInputDelegate {
   class_437 architectury_delegateInputs();

   public static class DelegateScreen extends class_437 {
      private class_437 parent;

      public DelegateScreen(class_437 parent) {
         super(class_2561.method_43473());
         this.parent = parent;
      }

      public boolean method_25400(char c, int i) {
         if (ClientScreenInputEvent.CHAR_TYPED_PRE.invoker().charTyped(class_310.method_1551(), this.parent, c, i).isPresent()) {
            return true;
         } else {
            return this.parent.method_25400(c, i)
               ? true
               : ClientScreenInputEvent.CHAR_TYPED_POST.invoker().charTyped(class_310.method_1551(), this.parent, c, i).isPresent();
         }
      }
   }
}
