package malte0811.ferritecore.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_815;

public class PredicateHelper {
   public static List<Predicate<class_2680>> toCanonicalList(Iterable<? extends class_815> conditions, class_2689<class_2248, class_2680> stateContainer) {
      List<Predicate<class_2680>> list = new ArrayList<>();

      for (class_815 cond : conditions) {
         list.add(cond.getPredicate(stateContainer));
      }

      canonize(list);
      return list;
   }

   public static <T> void canonize(List<Predicate<T>> input) {
      input.sort(Comparator.comparingInt(Predicate::hashCode));
      if (input instanceof ArrayList<Predicate<T>> arrayList) {
         arrayList.trimToSize();
      }
   }

   public static <T> Predicate<T> and(List<Predicate<T>> list) {
      return state -> {
         for (Predicate<T> predicate : list) {
            if (!predicate.test(state)) {
               return false;
            }
         }

         return true;
      };
   }

   public static <T> Predicate<T> or(List<Predicate<T>> list) {
      return state -> {
         for (Predicate<T> predicate : list) {
            if (predicate.test(state)) {
               return true;
            }
         }

         return false;
      };
   }
}
