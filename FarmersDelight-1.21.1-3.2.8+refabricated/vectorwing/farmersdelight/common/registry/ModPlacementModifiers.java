package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.minecraft.class_6797;
import net.minecraft.class_6798;
import vectorwing.farmersdelight.common.world.filter.BiomeTagFilter;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModPlacementModifiers {
   public static final Supplier<class_6798<BiomeTagFilter>> BIOME_TAG = RegUtils.regPlacementMod("biome_tag", () -> typeConvert(BiomeTagFilter.CODEC));

   private static <P extends class_6797> class_6798<P> typeConvert(MapCodec<P> codec) {
      return () -> codec;
   }

   public static void touch() {
   }
}
