package dev.architectury.hooks.level.biome;

import java.util.List;
import net.minecraft.class_2922;
import net.minecraft.class_5321;
import net.minecraft.class_6796;
import net.minecraft.class_6880;
import net.minecraft.class_2893.class_2894;
import net.minecraft.class_2893.class_2895;
import org.jetbrains.annotations.ApiStatus.Experimental;

public interface GenerationProperties {
   Iterable<class_6880<class_2922<?>>> getCarvers(class_2894 var1);

   Iterable<class_6880<class_6796>> getFeatures(class_2895 var1);

   List<Iterable<class_6880<class_6796>>> getFeatures();

   public interface Mutable extends GenerationProperties {
      GenerationProperties.Mutable addFeature(class_2895 var1, class_6880<class_6796> var2);

      @Experimental
      GenerationProperties.Mutable addFeature(class_2895 var1, class_5321<class_6796> var2);

      GenerationProperties.Mutable addCarver(class_2894 var1, class_6880<class_2922<?>> var2);

      @Experimental
      GenerationProperties.Mutable addCarver(class_2894 var1, class_5321<class_2922<?>> var2);

      GenerationProperties.Mutable removeFeature(class_2895 var1, class_5321<class_6796> var2);

      GenerationProperties.Mutable removeCarver(class_2894 var1, class_5321<class_2922<?>> var2);
   }
}
