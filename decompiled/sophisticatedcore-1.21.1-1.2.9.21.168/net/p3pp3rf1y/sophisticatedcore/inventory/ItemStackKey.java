package net.p3pp3rf1y.sophisticatedcore.inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1799;

public final class ItemStackKey {
   private final class_1799 stack;
   private static final Map<class_1799, ItemStackKey> CACHE = new ConcurrentHashMap<>();
   private boolean hashInitialized = false;
   private int hash;

   public static ItemStackKey of(class_1799 stack) {
      return CACHE.computeIfAbsent(stack, ItemStackKey::new);
   }

   private ItemStackKey(class_1799 stack) {
      this.stack = stack.method_7972();
      this.stack.method_7939(1);
   }

   public static void clearCacheOnTickEnd() {
      CACHE.clear();
   }

   public class_1799 getStack() {
      return this.stack;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ItemStackKey that = (ItemStackKey)o;
         return class_1799.method_31577(this.stack, that.stack);
      } else {
         return false;
      }
   }

   public boolean hashCodeNotEquals(class_1799 otherStack) {
      return this.hashCode() != class_1799.method_57355(otherStack);
   }

   @Override
   public int hashCode() {
      if (!this.hashInitialized) {
         this.hashInitialized = true;
         this.hash = class_1799.method_57355(this.stack);
      }

      return this.hash;
   }

   public boolean matches(ItemVariant resource) {
      return this.hashCode() == class_1799.method_57355(resource.toStack());
   }

   public boolean matches(class_1799 stack) {
      return this.hashCode() == class_1799.method_57355(stack);
   }

   public class_1799 stack() {
      return this.stack;
   }

   @Override
   public String toString() {
      return "ItemStackKey[stack=" + this.stack + "]";
   }
}
