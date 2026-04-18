package malte0811.ferritecore.impl;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import malte0811.ferritecore.util.PredicateHelper;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2769;

public class KeyValueConditionImpl {
   private static final Map<KeyValueConditionImpl.KeyValuePair, Predicate<class_2680>> STATE_HAS_PROPERTY_CACHE = new ConcurrentHashMap<>();

   public static Predicate<class_2680> getPredicate(class_2689<class_2248, class_2680> stateContainer, String key, String value, Splitter splitter) {
      class_2769<?> property = stateContainer.method_11663(key);
      if (property == null) {
         throw new RuntimeException(String.format("Unknown property '%s' on '%s'", key, ((class_2248)stateContainer.method_11660()).toString()));
      } else {
         String valueNoInvert = value;
         boolean invert = !value.isEmpty() && value.charAt(0) == '!';
         if (invert) {
            valueNoInvert = value.substring(1);
         }

         List<String> matchedStates = splitter.splitToList(valueNoInvert);
         if (matchedStates.isEmpty()) {
            throw new RuntimeException(
               String.format("Empty value '%s' for property '%s' on '%s'", value, key, ((class_2248)stateContainer.method_11660()).toString())
            );
         } else {
            Predicate<class_2680> isMatchedState;
            if (matchedStates.size() == 1) {
               isMatchedState = getBlockStatePredicate(stateContainer, property, valueNoInvert, key, value);
            } else {
               List<Predicate<class_2680>> subPredicates = matchedStates.stream()
                  .map(subValue -> getBlockStatePredicate(stateContainer, property, subValue, key, value))
                  .collect(Collectors.toCollection(ArrayList::new));
               PredicateHelper.canonize(subPredicates);
               isMatchedState = Deduplicator.or(subPredicates);
            }

            return invert ? isMatchedState.negate() : isMatchedState;
         }
      }
   }

   private static <T extends Comparable<T>> Predicate<class_2680> getBlockStatePredicate(
      class_2689<class_2248, class_2680> container, class_2769<T> property, String subValue, String key, String value
   ) {
      Optional<T> optional = property.method_11900(subValue);
      if (optional.isEmpty()) {
         throw new RuntimeException(
            String.format("Unknown value '%s' for property '%s' on '%s' in '%s'", subValue, key, ((class_2248)container.method_11660()).toString(), value)
         );
      } else {
         T unwrapped = (T)optional.get();
         return STATE_HAS_PROPERTY_CACHE.computeIfAbsent(new KeyValueConditionImpl.KeyValuePair(property, unwrapped), pair -> {
            Comparable<?> valueInt = pair.value();
            class_2769<?> propInt = pair.key();
            return state -> state.method_11654(propInt).equals(valueInt);
         });
      }
   }

   private record KeyValuePair(class_2769<?> key, Comparable<?> value) {
      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            KeyValueConditionImpl.KeyValuePair that = (KeyValueConditionImpl.KeyValuePair)o;
            return this.key == that.key && Objects.equals(this.value, that.value);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return 31 * System.identityHashCode(this.key) + Objects.hashCode(this.value);
      }
   }
}
