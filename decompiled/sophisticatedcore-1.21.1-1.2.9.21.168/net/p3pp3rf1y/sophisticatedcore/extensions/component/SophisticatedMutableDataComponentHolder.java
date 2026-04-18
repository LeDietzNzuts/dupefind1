package net.p3pp3rf1y.sophisticatedcore.extensions.component;

import java.util.function.Supplier;
import net.minecraft.class_9322;
import net.minecraft.class_9331;
import org.jetbrains.annotations.Nullable;

public interface SophisticatedMutableDataComponentHolder extends class_9322 {
   @Nullable
   <T> T sophisticatedCore_set(class_9331<? super T> var1, @Nullable T var2);

   @Nullable
   default <T> T sophisticatedCore_set(Supplier<? extends class_9331<? super T>> componentType, @Nullable T value) {
      return this.sophisticatedCore_set((class_9331<? super T>)componentType.get(), value);
   }

   @Nullable
   <T> T sophisticatedCore_remove(class_9331<? extends T> var1);

   @Nullable
   default <T> T sophisticatedCore_remove(Supplier<? extends class_9331<? extends T>> componentType) {
      return this.sophisticatedCore_remove((class_9331<? extends T>)componentType.get());
   }
}
