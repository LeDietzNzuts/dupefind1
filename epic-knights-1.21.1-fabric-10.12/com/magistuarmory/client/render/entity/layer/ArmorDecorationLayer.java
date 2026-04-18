package com.magistuarmory.client.render.entity.layer;

import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.decoration.ArmorDecorationModel;
import com.magistuarmory.client.render.model.decoration.ArmorDecorationModelSet;
import com.magistuarmory.client.render.model.decoration.SurcoatModel;
import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ArmorDecorationItem;
import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1738;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_572;
import net.minecraft.class_630;
import net.minecraft.class_6880;
import net.minecraft.class_918;
import net.minecraft.class_9307;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class ArmorDecorationLayer<T extends class_1309, M extends class_572<T>> extends class_3887<T, M> implements ArmorPatternLayer {
   private static final String ARMOR_DIR_PREFIX = "textures/models/armor/";
   private final ArmorDecorationModel<T> coatModel;
   private final String coatDirPrefix;
   private final class_2960 coatTexture;
   private final class_2960 basePatternTexture;
   private final ArmorDecorationModelSet<T> decorationModels;

   public ArmorDecorationLayer(ArmorDecorationModelSet<T> decorationModels, class_3883<T, M> parent, class_5618 context, class_2960 location) {
      super(parent);
      this.decorationModels = decorationModels;
      this.coatModel = new SurcoatModel<>(context.method_32167(ModModels.createDecorationLocation(location)));
      this.coatDirPrefix = this.getDirPrefix(location);
      this.coatTexture = this.getTexture(location);
      this.basePatternTexture = class_2960.method_60655("magistuarmory", this.coatDirPrefix + "base.png");
   }

   public class_2960 getTexture(class_2960 location) {
      return this.getTexture(location, false);
   }

   public class_2960 getTexture(class_2960 location, boolean overlay) {
      return class_2960.method_60655(location.method_12836(), "textures/models/armor/" + location.method_12832() + (overlay ? "_overlay.png" : ".png"));
   }

   public String getDirPrefix(class_2960 location) {
      return "textures/models/armor/" + location.method_12832() + "/";
   }

   public void render(class_4587 pose, class_4597 buffer, int p, T entity, float f, float f2, float f3, float f4, float f5, float f6) {
      this.renderPiece(pose, buffer, entity, class_1304.field_6174, p);
      this.renderPiece(pose, buffer, entity, class_1304.field_6172, p);
      this.renderPiece(pose, buffer, entity, class_1304.field_6166, p);
      this.renderPiece(pose, buffer, entity, class_1304.field_6169, p);
   }

   private void renderPiece(class_4587 pose, class_4597 buffer, T entity, class_1304 slot, int p) {
      class_1799 stack = entity.method_6118(slot);
      if (stack.method_7909() instanceof class_1738 armoritem && armoritem.method_7685() == slot) {
         if (stack.method_57824((class_9331)ModDataComponents.ARMOR_DECORATION.get()) != null) {
            for (ArmorDecorationItem.DecorationInfo info : ArmorDecorationItem.createDecorations(ArmorDecorationItem.getDecorationTags(stack))) {
               class_2960 location = info.location();
               ArmorDecorationModel<T> model = this.getArmorDecorationModel(location);
               if (model != null) {
                  ((class_572)this.method_17165()).method_2818(model);
                  if (info.dyeable()) {
                     this.renderDecoration(pose, buffer, p, class_4608.field_21444, info.color(), stack.method_7958(), model.parts(), this.getTexture(location));
                     this.renderDecoration(pose, buffer, p, class_4608.field_21444, stack.method_7958(), model.parts(), this.getTexture(location, true));
                  } else {
                     this.renderDecoration(pose, buffer, p, class_4608.field_21444, stack.method_7958(), model.parts(), this.getTexture(location));
                  }
               }
            }
         }

         class_9307 patterns = (class_9307)stack.method_57824(class_9334.field_49619);
         if (armoritem.method_7685() == class_1304.field_6174 && patterns != null) {
            class_1767 basecolor = (class_1767)stack.method_57824(class_9334.field_49620);
            ((class_572)this.method_17165()).method_2818(this.coatModel);
            this.renderDecoration(pose, buffer, p, class_4608.field_21444, stack.method_7958(), this.coatModel.parts(), this.coatTexture);
            List<Pair<class_6880<class_2582>, class_1767>> list = (List<Pair<class_6880<class_2582>, class_1767>>)(patterns == null
               ? new ArrayList<>()
               : patterns.comp_2428().stream().map(l -> Pair.of(l.comp_2429(), l.comp_2430())).collect(Collectors.toList()));
            this.renderPatterns(pose, buffer, p, class_4608.field_21444, list, stack.method_7958(), this.coatModel.parts(), basecolor);
         }
      }
   }

   public ArmorDecorationModel<T> getCoatModel() {
      return this.coatModel;
   }

   public ArmorDecorationModel<T> getArmorDecorationModel(class_2960 location) {
      return this.decorationModels.get(location);
   }

   public void renderDecoration(class_4587 pose, class_4597 buffer, int p, int overlay, boolean hasfoil, class_630[] modelparts, class_2960 texture) {
      this.renderDecoration(pose, buffer, p, overlay, -1, hasfoil, modelparts, texture);
   }

   public void renderDecoration(class_4587 pose, class_4597 buffer, int p, int overlay, int color, boolean hasfoil, class_630[] modelparts, class_2960 texture) {
      class_4588 vertexconsumer = class_918.method_27952(buffer, class_1921.method_23578(texture), hasfoil);

      for (class_630 part : modelparts) {
         part.method_22699(pose, vertexconsumer, p, overlay, color);
      }
   }

   @Override
   public class_2960 getBaseTexture(boolean withPattern) {
      return this.coatTexture;
   }

   public class_2960 getBaseTexture() {
      return this.coatTexture;
   }

   @Override
   public class_2960 getBasePatternTexture() {
      return this.basePatternTexture;
   }

   @Override
   public class_2960 getPatternTexture(class_2960 patternlocation) {
      return class_2960.method_60655(this.coatTexture.method_12836(), this.coatDirPrefix + patternlocation.method_12832() + ".png");
   }

   public void registerDecorations(List<RegistrySupplier<? extends ArmorDecoration>> armorDecorationItems, class_5618 context) {
      this.decorationModels.registerDecorations(armorDecorationItems, context);
   }
}
