package org.embeddedt.modernfix.dynamicresources;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import net.minecraft.class_1091;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2769;

public class ModelBakeryHelpers {
   public static final int MAX_BAKED_MODEL_COUNT = 10000;
   public static final int MAX_UNBAKED_MODEL_COUNT = 10000;
   public static final int MAX_MODEL_LIFETIME_SECS = 300;
   private static final Splitter COMMA_SPLITTER = Splitter.on(',');
   private static final Splitter EQUAL_SPLITTER = Splitter.on('=').limit(2);

   public static String[] getExtraTextureFolders() {
      return new String[]{
         "attachment",
         "bettergrass",
         "block",
         "blocks",
         "cape",
         "entity/bed",
         "entity/chest",
         "item",
         "items",
         "model",
         "models",
         "part",
         "pipe",
         "ropebridge",
         "runes",
         "solid_block",
         "spell_effect",
         "spell_projectile"
      };
   }

   private static <T extends Comparable<T>, V extends T> class_2680 setPropertyGeneric(class_2680 state, class_2769<T> prop, Object o) {
      return (class_2680)state.method_11657(prop, (Comparable)o);
   }

   private static <T extends Comparable<T>> T getValueHelper(class_2769<T> property, String value) {
      return (T)property.method_11900(value).orElse(null);
   }

   public static ImmutableList<class_2680> getBlockStatesForMRL(class_2689<class_2248, class_2680> stateDefinition, class_1091 location) {
      if (Objects.equals(location.method_4740(), "inventory")) {
         return ImmutableList.of();
      } else {
         Set<class_2769<?>> fixedProperties = new HashSet<>();
         class_2680 fixedState = (class_2680)stateDefinition.method_11664();

         for (String s : COMMA_SPLITTER.split(location.method_4740())) {
            Iterator<String> iterator = EQUAL_SPLITTER.split(s).iterator();
            if (iterator.hasNext()) {
               String s1 = iterator.next();
               class_2769<?> property = stateDefinition.method_11663(s1);
               if (property != null && iterator.hasNext()) {
                  String s2 = iterator.next();
                  Object value = getValueHelper(property, s2);
                  if (value == null) {
                     throw new RuntimeException("Unknown value: '" + s2 + "' for blockstate property: '" + s1 + "' " + property.method_11898());
                  }

                  fixedState = setPropertyGeneric(fixedState, property, value);
                  fixedProperties.add(property);
               } else if (!s1.isEmpty()) {
                  throw new RuntimeException("Unknown blockstate property: '" + s1 + "'");
               }
            }
         }

         if (fixedProperties.size() == stateDefinition.method_11659().size()) {
            return ImmutableList.of(fixedState);
         } else {
            ArrayList<class_2769<?>> anyProperties = new ArrayList<>(stateDefinition.method_11659());
            anyProperties.removeAll(fixedProperties);
            ArrayList<class_2680> finalList = new ArrayList<>();
            finalList.add(fixedState);

            for (class_2769<?> property : anyProperties) {
               ArrayList<class_2680> newPermutations = new ArrayList<>();

               for (class_2680 state : finalList) {
                  for (Comparable<?> value : property.method_11898()) {
                     newPermutations.add(setPropertyGeneric(state, property, value));
                  }
               }

               finalList = newPermutations;
            }

            return ImmutableList.copyOf(finalList);
         }
      }
   }
}
