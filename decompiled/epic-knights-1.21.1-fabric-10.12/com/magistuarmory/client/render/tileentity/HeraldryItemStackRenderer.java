package com.magistuarmory.client.render.tileentity;

import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.item.MedievalShieldModel;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_6880;
import net.minecraft.class_756;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class HeraldryItemStackRenderer extends class_756 implements ShieldPatternLayer {
   private class_3879 model;
   private final class_2960 location;
   private final HeraldryItemStackRenderer.MaterialContainer materialContainer;

   public HeraldryItemStackRenderer(String id, class_2960 location) {
      super(class_310.method_1551().method_31975(), class_310.method_1551().method_31974());
      this.location = location;
      this.materialContainer = new HeraldryItemStackRenderer.MaterialContainer(id, location, "entity/" + location.method_12832() + "/");
   }

   @Deprecated(forRemoval = true)
   public void loadModel(class_5618 context) {
      this.model = new MedievalShieldModel(context.method_32167(ModModels.createLocation(this.location)));
   }

   public void setModel(class_3879 model) {
      this.model = model;
   }

   public void method_3166(class_1799 stack, class_811 transform, class_4587 pose, class_4597 buffer, int p, int overlay) {
      if (this.model instanceof MedievalShieldModel shieldmodel) {
         pose.method_22903();
         pose.method_22905(1.0F, -1.0F, -1.0F);
         class_1767 baseColor = (class_1767)stack.method_57824(class_9334.field_49620);
         class_4588 vertexconsumer = this.getBaseMaterial(baseColor != null)
            .method_24148()
            .method_24108(
               class_918.method_29711(buffer, this.model.method_23500(this.getBaseMaterial(baseColor != null).method_24144()), true, stack.method_7958())
            );
         shieldmodel.handle().method_22699(pose, vertexconsumer, p, overlay, 16777215);
         class_9307 patterns = (class_9307)stack.method_57824(class_9334.field_49619);
         List<Pair<class_6880<class_2582>, class_1767>> list = (List<Pair<class_6880<class_2582>, class_1767>>)(patterns == null
            ? new ArrayList<>()
            : patterns.comp_2428().stream().map(l -> Pair.of(l.comp_2429(), l.comp_2430())).collect(Collectors.toList()));
         this.renderPatterns(pose, buffer, p, overlay, list, stack.method_7958(), shieldmodel.plate(), baseColor);
         pose.method_22909();
      }
   }

   @Override
   public class_4730 getBaseMaterial(boolean withPattern) {
      return withPattern ? this.materialContainer.getBaseWithPatternMaterial() : this.materialContainer.getBaseWithoutPatternMaterial();
   }

   @Override
   public class_4730 getBasePatternMaterial() {
      return this.materialContainer.getBasePatternMaterial();
   }

   @Override
   public class_4730 getPatternMaterial(class_2960 patternLocation) {
      return this.materialContainer.getPatternMaterial(patternLocation);
   }

   private static final class MaterialContainer {
      private final String id;
      private final class_2960 location;
      private final String patternsDirectory;
      private class_4730 baseWithPatternMaterial = null;
      private class_4730 baseWithoutPatternMaterial = null;
      private class_4730 basePatternMaterial = null;

      private MaterialContainer(String id, class_2960 location, String patternsDirectory) {
         this.id = id;
         this.location = location;
         this.patternsDirectory = patternsDirectory;
      }

      public synchronized class_4730 getBaseWithPatternMaterial() {
         if (this.baseWithPatternMaterial == null) {
            this.baseWithPatternMaterial = new class_4730(
               class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), "entity/" + this.id + "_pattern")
            );
         }

         return this.baseWithPatternMaterial;
      }

      public synchronized class_4730 getBaseWithoutPatternMaterial() {
         if (this.baseWithoutPatternMaterial == null) {
            this.baseWithoutPatternMaterial = new class_4730(
               class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), "entity/" + this.id + "_nopattern")
            );
         }

         return this.baseWithoutPatternMaterial;
      }

      public synchronized class_4730 getBasePatternMaterial() {
         if (this.basePatternMaterial == null) {
            this.basePatternMaterial = new class_4730(
               class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), this.patternsDirectory + "base")
            );
         }

         return this.basePatternMaterial;
      }

      public class_4730 getPatternMaterial(class_2960 patternLocation) {
         return new class_4730(
            class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), this.patternsDirectory + patternLocation.method_12832())
         );
      }
   }
}
