package com.magistuarmory.client.render;

import com.magistuarmory.misc.HeraldryRegistry;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1767;
import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_5321;
import net.minecraft.class_630;
import net.minecraft.class_6880;

@Environment(EnvType.CLIENT)
public interface PatternLayer {
   class_4588 baseVertexConsumer(class_4597 var1, boolean var2, boolean var3);

   class_4588 basePatternVertexConsumer(class_4597 var1, boolean var2);

   class_4588 patternVertexConsumer(class_4597 var1, class_2960 var2, boolean var3);

   default void renderPatterns(
      class_4587 pose,
      class_4597 buffer,
      int p,
      int overlay,
      List<Pair<class_6880<class_2582>, class_1767>> list,
      boolean hasfoil,
      class_630[] modelparts,
      class_1767 basecolor
   ) {
      list = filterFromUnregistered(list);

      for (class_630 part : modelparts) {
         part.method_22699(pose, this.baseVertexConsumer(buffer, basecolor != null, hasfoil), p, overlay, -1);
         if (basecolor != null) {
            part.method_22699(pose, this.basePatternVertexConsumer(buffer, hasfoil), p, overlay, basecolor.method_7787());
         }

         for (int i = 0; i < 17 && i < list.size(); i++) {
            Pair<class_6880<class_2582>, class_1767> pair = list.get(i);
            int color = ((class_1767)pair.getSecond()).method_7787();
            part.method_22699(
               pose,
               this.patternVertexConsumer(buffer, ((class_5321)((class_6880)pair.getFirst()).method_40230().get()).method_29177(), hasfoil),
               p,
               overlay,
               color
            );
         }
      }
   }

   static List<Pair<class_6880<class_2582>, class_1767>> filterFromUnregistered(List<Pair<class_6880<class_2582>, class_1767>> list) {
      return list.stream()
         .filter(
            pair -> ((class_6880)pair.getFirst()).method_40230().isPresent()
               && HeraldryRegistry.isRegistered(((class_5321)((class_6880)pair.getFirst()).method_40230().get()).method_29177().method_12832())
         )
         .collect(Collectors.toList());
   }
}
