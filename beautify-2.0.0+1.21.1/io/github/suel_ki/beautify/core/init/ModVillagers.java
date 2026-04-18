package io.github.suel_ki.beautify.core.init;

import com.google.common.collect.ImmutableSet;
import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.class_2378;
import net.minecraft.class_2680;
import net.minecraft.class_3417;
import net.minecraft.class_3852;
import net.minecraft.class_4158;
import net.minecraft.class_7923;

public class ModVillagers {
   public static final class_4158 BOTANIST_WORKBENCH_POI = registerPoiType(
      "botanist_workbench_poi", ImmutableSet.copyOf(BlockInit.BOTANIST_WORKBENCH.method_9595().method_11662())
   );
   public static final class_3852 BOTANIST = registerVillagerProfession(
      "botanist",
      new class_3852(
         "botanist",
         x -> x.comp_349() == BOTANIST_WORKBENCH_POI,
         x -> x.comp_349() == BOTANIST_WORKBENCH_POI,
         ImmutableSet.of(),
         ImmutableSet.of(),
         class_3417.field_28573
      )
   );

   private static class_4158 registerPoiType(String name, Iterable<class_2680> blocks) {
      return PointOfInterestHelper.register(Beautify.id(name), 1, 1, blocks);
   }

   private static <T extends class_3852> T registerVillagerProfession(String name, T profession) {
      return (T)class_2378.method_10230(class_7923.field_41195, Beautify.id(name), profession);
   }
}
