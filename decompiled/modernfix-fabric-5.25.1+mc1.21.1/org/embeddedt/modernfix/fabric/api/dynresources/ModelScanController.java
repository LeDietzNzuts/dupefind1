package org.embeddedt.modernfix.fabric.api.dynresources;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_2960;

public class ModelScanController {
   public static final List<Predicate<class_2960>> SCAN_PREDICATES = new ArrayList<>();

   public static boolean shouldScanAndTestWrapping(class_2960 location) {
      if (SCAN_PREDICATES.size() > 0) {
         for (Predicate<class_2960> predicate : SCAN_PREDICATES) {
            if (!predicate.test(location)) {
               return false;
            }
         }
      }

      return true;
   }
}
