package com.magistuarmory.client.render.entity.layer;

import com.magistuarmory.client.render.PatternLayer;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_918;

public interface ArmorPatternLayer extends PatternLayer {
   class_2960 getBaseTexture(boolean var1);

   class_2960 getBasePatternTexture();

   class_2960 getPatternTexture(class_2960 var1);

   @Override
   default class_4588 baseVertexConsumer(class_4597 buffer, boolean withPattern, boolean hasfoil) {
      return class_918.method_27952(buffer, class_1921.method_23576(this.getBaseTexture(withPattern)), hasfoil);
   }

   @Override
   default class_4588 basePatternVertexConsumer(class_4597 buffer, boolean hasfoil) {
      return class_918.method_27952(buffer, class_1921.method_23576(this.getBasePatternTexture()), hasfoil);
   }

   @Override
   default class_4588 patternVertexConsumer(class_4597 buffer, class_2960 patternlocation, boolean hasfoil) {
      return class_918.method_27952(buffer, class_1921.method_23588(this.getPatternTexture(patternlocation)), hasfoil);
   }
}
