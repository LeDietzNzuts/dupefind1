package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_5819;

public class RandHelper {
   private RandHelper() {
   }

   public static float getRandomMinusOneToOne(class_5819 rand) {
      return rand.method_43057() - rand.method_43057();
   }

   public static <T> List<T> getNRandomElements(List<T> input, int numberOfElements) {
      if (input.size() == numberOfElements) {
         return input;
      } else {
         ArrayList<T> randomizedList = new ArrayList<>(input);
         List<T> ret = new ArrayList<>();
         Collections.shuffle(randomizedList);

         for (int i = 0; i < randomizedList.size() && i < numberOfElements; i++) {
            ret.add(randomizedList.get(i));
         }

         return ret;
      }
   }

   public static <T> Optional<T> getRandomWeightedElement(class_5819 random, List<WeightedElement<T>> weightedElements) {
      int totalWeight = 0;

      for (WeightedElement<T> weightedElement : weightedElements) {
         int weight = weightedElement.getWeight();
         if (weight < 0) {
            throw new IllegalArgumentException("Negative weight element passed in");
         }

         totalWeight += weight;
      }

      if (totalWeight == 0) {
         throw new IllegalArgumentException("Map passed in is either empty or the only element has 0 weight");
      } else {
         int rndValue = random.method_43048(totalWeight + 1);

         for (WeightedElement<T> weightedElement : weightedElements) {
            rndValue -= weightedElement.getWeight();
            if (rndValue <= 0) {
               return Optional.of(weightedElement.getElement());
            }
         }

         return Optional.empty();
      }
   }
}
