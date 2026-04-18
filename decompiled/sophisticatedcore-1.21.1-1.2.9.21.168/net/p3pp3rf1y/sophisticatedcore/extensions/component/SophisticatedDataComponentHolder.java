package net.p3pp3rf1y.sophisticatedcore.extensions.component;

import java.util.function.Supplier;
import net.minecraft.class_9322;
import net.minecraft.class_9331;
import org.jetbrains.annotations.Nullable;

public interface SophisticatedDataComponentHolder {
   private class_9322 self() {
      return (class_9322)this;
   }

   @Nullable
   default <T> T sophisticatedCore_get(Supplier<? extends class_9331<? extends T>> componentType) {
      return (T)this.self().method_57824(componentType.get());
   }

   default <T> T sophisticatedCore_getOrDefault(Supplier<? extends class_9331<? extends T>> type, T defaultValue) {
      return (T)this.self().method_57825(type.get(), defaultValue);
   }

   default <T extends class_9331<?>> boolean sophisticatedCore_has(Supplier<T> type) {
      return this.self().method_57826(type.get());
   }
}
