package noobanidus.mods.lootr.common.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class ConfigManagerBase {
   protected static Set<String> validateStringList(Collection<? extends String> incomingList, String listKey) {
      Set<String> validatedList = new HashSet<>();

      for (String entry : incomingList) {
         if (entry != null && !entry.isEmpty()) {
            validatedList.add(entry);
         } else {
            LootrAPI.LOG
               .error(
                  "Error found when validating a configuration list for '{}'. One of the entries is null or empty and cannot be converted to a String.",
                  listKey
               );
         }
      }

      return validatedList;
   }

   protected static Set<class_5321<class_1937>> validateDimensions(Collection<? extends String> incomingList, String listKey) {
      return validateResourceKeyList(incomingList, listKey, o -> class_5321.method_29179(class_7924.field_41223, o));
   }

   protected static <T> Set<class_5321<T>> validateResourceKeyList(
      Collection<? extends String> incomingList, String listKey, Function<class_2960, class_5321<T>> builder
   ) {
      Set<class_5321<T>> validatedList = new HashSet<>();

      for (String entry : incomingList) {
         if (entry == null || entry.isEmpty()) {
            throw new RuntimeException(
               "Error found when validating a configuration list for '"
                  + listKey
                  + "'. One of the entries is null or empty and cannot be converted to a ResourceLocation."
            );
         }

         class_2960 location;
         try {
            location = class_2960.method_60654(entry);
         } catch (Exception var9) {
            throw new RuntimeException(
               "Error found when validating a configuration list for '"
                  + listKey
                  + "'. The value found in the list, '"
                  + entry
                  + "', is not a valid ResourceLocation.",
               var9
            );
         }

         try {
            validatedList.add(builder.apply(location));
         } catch (Exception var8) {
            throw new RuntimeException(
               "Error found when validating a configuration list for '"
                  + listKey
                  + "'. The value found in the list, '"
                  + entry
                  + "', is not valid to create a ResourceKey.",
               var8
            );
         }
      }

      return validatedList;
   }
}
