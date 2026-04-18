package malte0811.ferritecore.fastmap;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import net.minecraft.class_2350;
import net.minecraft.class_2746;
import net.minecraft.class_2754;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3542;
import org.jetbrains.annotations.Nullable;

public abstract class PropertyIndexer<T extends Comparable<T>> {
   private static final Map<class_2769<?>, PropertyIndexer<?>> KNOWN_INDEXERS = new Reference2ObjectOpenHashMap();
   private final class_2769<T> property;
   private final int numValues;
   protected final T[] valuesInOrder;

   public static <T extends Comparable<T>> PropertyIndexer<T> makeIndexer(class_2769<T> prop) {
      synchronized (KNOWN_INDEXERS) {
         return (PropertyIndexer<T>)KNOWN_INDEXERS.computeIfAbsent(prop, propInner -> {
            PropertyIndexer<?> result = null;
            if (propInner instanceof class_2746 boolProp) {
               result = new PropertyIndexer.BoolIndexer(boolProp);
            } else if (propInner instanceof class_2758 intProp) {
               result = new PropertyIndexer.IntIndexer(intProp);
            } else if (PropertyIndexer.WeirdVanillaDirectionIndexer.isApplicable((class_2769<?>)propInner)) {
               result = new PropertyIndexer.WeirdVanillaDirectionIndexer((class_2769<class_2350>)propInner);
            } else if (propInner instanceof class_2754<?> enumProp) {
               result = new PropertyIndexer.EnumIndexer(enumProp);
            }

            return (PropertyIndexer<?>)(result != null && result.isValid() ? result : new PropertyIndexer.GenericIndexer((class_2769<T>)propInner));
         });
      }
   }

   protected PropertyIndexer(class_2769<T> property, T[] valuesInOrder) {
      this.property = property;
      this.numValues = property.method_11898().size();
      this.valuesInOrder = valuesInOrder;
   }

   public class_2769<T> getProperty() {
      return this.property;
   }

   public int numValues() {
      return this.numValues;
   }

   @Nullable
   public final T byIndex(int index) {
      return index >= 0 && index < this.valuesInOrder.length ? this.valuesInOrder[index] : null;
   }

   public abstract int toIndex(Comparable<?> var1);

   protected boolean isValid() {
      Collection<T> allowed = this.getProperty().method_11898();
      int index = 0;

      for (T val : allowed) {
         if (this.toIndex(val) != index || !val.equals(this.byIndex(index))) {
            return false;
         }

         index++;
      }

      return true;
   }

   private static class BoolIndexer extends PropertyIndexer<Boolean> {
      private static final Boolean[] VALUES = new Boolean[]{true, false};

      protected BoolIndexer(class_2746 property) {
         super(property, VALUES);
      }

      @Override
      public int toIndex(Comparable<?> value) {
         if (value instanceof Boolean bool) {
            return bool ? 0 : 1;
         } else {
            return -1;
         }
      }
   }

   private static class EnumIndexer<E extends Enum<E> & class_3542> extends PropertyIndexer<E> {
      private final int ordinalOffset;

      protected EnumIndexer(class_2754<E> property) {
         super(property, (E[])property.method_11898().toArray(new Enum[0]));
         this.ordinalOffset = property.method_11898().stream().mapToInt(rec$ -> rec$.ordinal()).min().orElse(0);
      }

      @Override
      public int toIndex(Comparable<?> value) {
         return value instanceof Enum<?> enumValue ? enumValue.ordinal() - this.ordinalOffset : -1;
      }
   }

   private static class GenericIndexer<T extends Comparable<T>> extends PropertyIndexer<T> {
      private final Map<Comparable<?>, Integer> toValueIndex;

      protected GenericIndexer(class_2769<T> property) {
         super(property, (T[])property.method_11898().toArray(new Comparable[0]));
         Builder<Comparable<?>, Integer> toValueIndex = ImmutableMap.builder();

         for (int i = 0; i < this.valuesInOrder.length; i++) {
            toValueIndex.put(this.valuesInOrder[i], i);
         }

         this.toValueIndex = toValueIndex.build();
      }

      @Override
      public int toIndex(Comparable<?> value) {
         return this.toValueIndex.getOrDefault(value, -1);
      }
   }

   private static class IntIndexer extends PropertyIndexer<Integer> {
      private final int min;

      protected IntIndexer(class_2758 property) {
         super(property, property.method_11898().toArray(new Integer[0]));
         this.min = property.method_11898().stream().min(Comparator.naturalOrder()).orElse(0);
      }

      @Override
      public int toIndex(Comparable<?> value) {
         return value instanceof Integer i ? i - this.min : -1;
      }
   }

   private static class WeirdVanillaDirectionIndexer extends PropertyIndexer<class_2350> {
      private static final class_2350[] ORDER = new class_2350[]{
         class_2350.field_11043, class_2350.field_11034, class_2350.field_11035, class_2350.field_11039, class_2350.field_11036, class_2350.field_11033
      };

      public WeirdVanillaDirectionIndexer(class_2769<class_2350> prop) {
         super(prop, ORDER);
         Preconditions.checkState(this.isValid());
      }

      static boolean isApplicable(class_2769<?> prop) {
         Collection<?> values = prop.method_11898();
         return values.size() != ORDER.length ? false : Arrays.equals(ORDER, values.toArray());
      }

      @Override
      public int toIndex(Comparable<?> value) {
         if (value instanceof class_2350 d) {
            return switch (d) {
               case field_11043 -> 0;
               case field_11034 -> 1;
               case field_11035 -> 2;
               case field_11039 -> 3;
               case field_11036 -> 4;
               case field_11033 -> 5;
               default -> throw new MatchException(null, null);
            };
         } else {
            return -1;
         }
      }
   }
}
