package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_3542;
import net.minecraft.class_2561.class_2562;
import net.minecraft.class_7225.class_7874;

public class NBTHelper {
   private NBTHelper() {
   }

   public static Optional<Integer> getInt(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10550);
   }

   public static Optional<int[]> getIntArray(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10561);
   }

   public static Optional<Boolean> getBoolean(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10577);
   }

   public static Optional<class_2487> getCompound(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10562);
   }

   public static <T> Optional<T> getTagValue(class_2487 tag, String key, BiFunction<class_2487, String, T> getValue) {
      return !tag.method_10545(key) ? Optional.empty() : Optional.of(getValue.apply(tag, key));
   }

   public static <E, C extends Collection<E>> Optional<C> getCollection(
      class_2487 tag, String key, byte listType, Function<class_2520, Optional<E>> getElement, Supplier<C> initCollection
   ) {
      return getTagValue(tag, key, (c, n) -> c.method_10554(n, listType)).map(listNbt -> {
         C ret = initCollection.get();
         listNbt.forEach(elementNbt -> getElement.apply(elementNbt).ifPresent(ret::add));
         return ret;
      });
   }

   public static <T extends Enum<T>> Optional<T> getEnumConstant(class_2487 tag, String key, Function<String, T> deserialize) {
      return getTagValue(tag, key, (t, k) -> deserialize.apply(t.method_10558(k)));
   }

   public static Optional<Long> getLong(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10537);
   }

   public static class_2487 putBoolean(class_2487 tag, String key, boolean value) {
      tag.method_10556(key, value);
      return tag;
   }

   public static class_2487 putInt(class_2487 tag, String key, int value) {
      tag.method_10569(key, value);
      return tag;
   }

   public static class_2487 putString(class_2487 tag, String key, String value) {
      tag.method_10582(key, value);
      return tag;
   }

   public static <T extends Enum<T> & class_3542> class_2487 putEnumConstant(class_2487 tag, String key, T enumConstant) {
      tag.method_10582(key, enumConstant.method_15434());
      return tag;
   }

   public static Optional<class_2561> getComponent(class_2487 tag, String key, class_7874 registries) {
      return getTagValue(tag, key, (t, k) -> class_2562.method_10877(t.method_10558(k), registries));
   }

   public static Optional<String> getString(class_2487 tag, String key) {
      return getTagValue(tag, key, class_2487::method_10558);
   }

   public static <K, V> Optional<Map<K, V>> getMap(class_2487 tag, String key, Function<String, K> getKey, BiFunction<String, class_2520, Optional<V>> getValue) {
      return getMap(tag, key, getKey, getValue, HashMap::new);
   }

   public static <K, V> Optional<Map<K, V>> getMap(
      class_2487 tag, String key, Function<String, K> getKey, BiFunction<String, class_2520, Optional<V>> getValue, Supplier<Map<K, V>> initMap
   ) {
      class_2487 mapNbt = tag.method_10562(key);
      Map<K, V> map = initMap.get();

      for (String tagName : mapNbt.method_10541()) {
         getValue.apply(tagName, mapNbt.method_10580(tagName)).ifPresent(value -> map.put(getKey.apply(tagName), (V)value));
      }

      return Optional.of(map);
   }

   public static <K, V> class_2487 putMap(class_2487 tag, String key, Map<K, V> map, Function<K, String> getStringKey, Function<V, class_2520> getNbtValue) {
      class_2487 mapNbt = new class_2487();

      for (Entry<K, V> entry : map.entrySet()) {
         mapNbt.method_10566(getStringKey.apply(entry.getKey()), getNbtValue.apply(entry.getValue()));
      }

      tag.method_10566(key, mapNbt);
      return tag;
   }

   public static <T> void putList(class_2487 tag, String key, Collection<T> values, Function<T, class_2520> getNbtValue) {
      class_2499 list = new class_2499();
      values.forEach(v -> list.add(getNbtValue.apply((T)v)));
      tag.method_10566(key, list);
   }
}
