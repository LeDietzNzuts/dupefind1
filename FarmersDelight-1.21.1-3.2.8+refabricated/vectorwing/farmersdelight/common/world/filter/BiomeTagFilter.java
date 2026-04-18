package vectorwing.farmersdelight.common.world.filter;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_5444;
import net.minecraft.class_5819;
import net.minecraft.class_6661;
import net.minecraft.class_6798;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import vectorwing.farmersdelight.common.registry.ModPlacementModifiers;

public class BiomeTagFilter extends class_6661 {
   public static final MapCodec<BiomeTagFilter> CODEC = RecordCodecBuilder.mapCodec(
      builder -> builder.group(class_6862.method_40090(class_7924.field_41236).fieldOf("tag").forGetter(instance -> instance.biomeTag))
         .apply(builder, BiomeTagFilter::new)
   );
   private final class_6862<class_1959> biomeTag;

   private BiomeTagFilter(class_6862<class_1959> biomeTag) {
      this.biomeTag = biomeTag;
   }

   public static BiomeTagFilter biomeIsInTag(class_6862<class_1959> biomeTag) {
      return new BiomeTagFilter(biomeTag);
   }

   protected boolean method_38918(class_5444 context, class_5819 random, class_2338 pos) {
      class_6880<class_1959> biome = context.method_34383().method_23753(pos);
      return biome.method_40220(this.biomeTag);
   }

   public class_6798<?> method_39615() {
      return ModPlacementModifiers.BIOME_TAG.get();
   }
}
