package com.magistuarmory.client.render.tileentity;

import com.magistuarmory.client.render.PatternLayer;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4730;

public interface ShieldPatternLayer extends PatternLayer {
   class_4730 getBaseMaterial(boolean var1);

   class_4730 getBasePatternMaterial();

   class_4730 getPatternMaterial(class_2960 var1);

   @Override
   default class_4588 baseVertexConsumer(class_4597 buffer, boolean withPattern, boolean hasfoil) {
      return this.getBaseMaterial(withPattern).method_30001(buffer, class_1921::method_23576, hasfoil);
   }

   @Override
   default class_4588 basePatternVertexConsumer(class_4597 buffer, boolean hasfoil) {
      return this.getBasePatternMaterial().method_30001(buffer, class_1921::method_23588, hasfoil);
   }

   @Override
   default class_4588 patternVertexConsumer(class_4597 buffer, class_2960 patternlocation, boolean hasfoil) {
      return this.getPatternMaterial(patternlocation).method_30001(buffer, class_1921::method_23588, hasfoil);
   }
}
