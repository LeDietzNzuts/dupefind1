package org.embeddedt.modernfix.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import net.minecraft.class_1091;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemMesherMap<K> implements Map<K, class_1091> {
   private final Function<K, class_1091> getLocation;

   public ItemMesherMap(Function<K, class_1091> getLocation) {
      this.getLocation = getLocation;
   }

   @Override
   public int size() {
      return class_7923.field_41178.method_10235().size();
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   @Override
   public boolean containsKey(Object key) {
      return true;
   }

   @Override
   public boolean containsValue(Object value) {
      return false;
   }

   public class_1091 get(Object key) {
      return this.getLocation.apply((K)key);
   }

   @Nullable
   public class_1091 put(K key, class_1091 value) {
      throw new UnsupportedOperationException();
   }

   public class_1091 remove(Object key) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void putAll(@NotNull Map<? extends K, ? extends class_1091> m) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   @Override
   public Set<K> keySet() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   @Override
   public Collection<class_1091> values() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   @Override
   public Set<Entry<K, class_1091>> entrySet() {
      throw new UnsupportedOperationException();
   }
}
