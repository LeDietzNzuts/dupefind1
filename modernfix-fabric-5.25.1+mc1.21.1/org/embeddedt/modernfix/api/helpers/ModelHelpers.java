package org.embeddedt.modernfix.api.helpers;

import com.google.common.collect.ImmutableList;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.class_1086;
import net.minecraft.class_1087;
import net.minecraft.class_1088;
import net.minecraft.class_1091;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2960;
import net.minecraft.class_3665;
import net.minecraft.class_7775;
import net.minecraft.class_7923;
import org.embeddedt.modernfix.dynamicresources.ModelBakeryHelpers;
import org.embeddedt.modernfix.util.DynamicMap;

public final class ModelHelpers {
   public static ImmutableList<class_2680> getBlockStateForLocation(class_1091 location) {
      Optional<class_2248> blockOpt = class_7923.field_41175.method_17966(location.comp_2875());
      return blockOpt.isPresent() ? ModelBakeryHelpers.getBlockStatesForMRL(blockOpt.get().method_9595(), location) : ImmutableList.of();
   }

   public static ImmutableList<class_2680> getBlockStateForLocation(class_2689<class_2248, class_2680> definition, class_1091 location) {
      return ModelBakeryHelpers.getBlockStatesForMRL(definition, location);
   }

   public static Map<class_2960, class_1087> createFakeTopLevelMap(BiFunction<class_2960, class_3665, class_1087> modelGetter) {
      return new DynamicMap<>(class_2960.class, location -> modelGetter.apply(location, class_1086.field_5350));
   }

   public static class_7775 adaptBakery(class_1088 bakery) {
      throw new UnsupportedOperationException("TODO");
   }
}
