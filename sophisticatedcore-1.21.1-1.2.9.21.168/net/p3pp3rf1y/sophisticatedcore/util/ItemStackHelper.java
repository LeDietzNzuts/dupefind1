package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_9323;
import net.minecraft.class_9334;
import net.minecraft.class_9336;

public class ItemStackHelper {
   private ItemStackHelper() {
   }

   public static boolean areItemStackComponentsEqualIgnoreDurability(class_1799 stackA, class_1799 stackB) {
      if (stackA.method_7960() && stackB.method_7960()) {
         return true;
      } else if (stackA.method_7960() || stackB.method_7960()) {
         return false;
      } else {
         return stackA.method_57353().method_57837() && !stackB.method_57353().method_57837()
            ? false
            : stackA.method_57353().method_57837() || areComponentsEqualIgnoreDurability(stackA.method_57353(), stackB.method_57353());
      }
   }

   public static boolean areComponentsEqualIgnoreDurability(class_9323 componentsA, @Nullable class_9323 componentsB) {
      if (componentsA == componentsB) {
         return true;
      } else if (componentsB != null && componentsA.method_57835() == componentsB.method_57835()) {
         for (class_9336<?> typedDataComponent : componentsA) {
            if (!componentsB.method_57832(typedDataComponent.comp_2443())) {
               return false;
            }

            if (!typedDataComponent.comp_2443().equals(class_9334.field_49629)
               && !Objects.equals(typedDataComponent.comp_2444(), componentsB.method_57829(typedDataComponent.comp_2443()))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
