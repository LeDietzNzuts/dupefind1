package com.magistuarmory.client.render.entity.layer;

import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.decoration.HorseArmorDecorationModel;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1498;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4059;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_549;
import net.minecraft.class_6880;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class HorseArmorDecorationLayer extends class_3887<class_1498, class_549<class_1498>> implements ArmorPatternLayer {
   private static final String BASE_DIR = "textures/entity/horse/armor/";
   private final HorseArmorDecorationModel<class_1498> model;
   private final class_2960 baseTexture;
   private final class_2960 basePatternTexture;
   private final String name;
   private final String dirprefix;

   public HorseArmorDecorationLayer(class_3883<class_1498, class_549<class_1498>> parent, class_5618 context, class_2960 texture, String name) {
      super(parent);
      this.name = name;
      this.dirprefix = "textures/entity/horse/armor/" + name + "/";
      this.baseTexture = texture;
      this.model = new HorseArmorDecorationModel(
         context.method_32167(ModModels.createDecorationLocation(class_2960.method_60655(texture.method_12836(), name)))
      );
      this.basePatternTexture = class_2960.method_60655("magistuarmory", this.dirprefix + "base.png");
   }

   public void render(class_4587 pose, class_4597 buffer, int p, class_1498 entity, float f, float f2, float f3, float f4, float f5, float f6) {
      class_1799 stack = entity.method_6118(class_1304.field_48824);
      class_9307 patterns = (class_9307)stack.method_57824(class_9334.field_49619);
      if (stack.method_7909() instanceof class_4059 && patterns != null) {
         class_1767 basecolor = (class_1767)stack.method_57824(class_9334.field_49620);
         ((class_549)this.method_17165()).method_17081(this.model);
         this.model.method_17084(entity, f, f2, f3);
         this.model.method_17085(entity, f, f2, f4, f5, f6);
         List<Pair<class_6880<class_2582>, class_1767>> list = patterns.comp_2428()
            .stream()
            .map(l -> Pair.of(l.comp_2429(), l.comp_2430()))
            .collect(Collectors.toList());
         this.renderPatterns(pose, buffer, p, class_4608.field_21444, list, false, this.model.parts(), basecolor);
      }
   }

   @Override
   public class_2960 getBaseTexture(boolean withPattern) {
      return this.baseTexture;
   }

   @Override
   public class_2960 getBasePatternTexture() {
      return this.basePatternTexture;
   }

   @Override
   public class_2960 getPatternTexture(class_2960 patternlocation) {
      return class_2960.method_60655(this.baseTexture.method_12836(), this.dirprefix + patternlocation.method_12832() + ".png");
   }
}
