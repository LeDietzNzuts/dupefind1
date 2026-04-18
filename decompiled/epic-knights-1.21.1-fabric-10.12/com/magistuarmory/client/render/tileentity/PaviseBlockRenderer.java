package com.magistuarmory.client.render.tileentity;

import com.magistuarmory.block.PaviseBlockEntity;
import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.block.PaviseBlockModel;
import com.magistuarmory.client.render.model.item.MedievalShieldModel;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1767;
import net.minecraft.class_2215;
import net.minecraft.class_2582;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_6880;
import net.minecraft.class_7833;
import net.minecraft.class_827;
import net.minecraft.class_918;
import net.minecraft.class_9307;
import net.minecraft.class_5614.class_5615;

@Environment(EnvType.CLIENT)
public class PaviseBlockRenderer implements class_827<PaviseBlockEntity>, ShieldPatternLayer {
   private final PaviseBlockModel model;
   private final class_2960 location;
   private final String patternsDirectory;
   private final class_4730 baseWithPatternMaterial;
   private final class_4730 baseWithoutPatternMaterial;
   private final class_4730 basePatternMaterial;

   public PaviseBlockRenderer(class_5615 context, String id, class_2960 location) {
      this.model = new PaviseBlockModel(context.method_32140(ModModels.PAVISE_BLOCK_LOCATION));
      this.location = location;
      this.patternsDirectory = "entity/" + location.method_12832() + "/";
      this.baseWithPatternMaterial = new class_4730(class_4722.field_21707, class_2960.method_60655(location.method_12836(), "entity/" + id + "_pattern"));
      this.baseWithoutPatternMaterial = new class_4730(class_4722.field_21707, class_2960.method_60655(location.method_12836(), "entity/" + id + "_nopattern"));
      this.basePatternMaterial = new class_4730(class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), this.patternsDirectory + "base"));
   }

   public int method_33893() {
      return 128;
   }

   public void render(PaviseBlockEntity pavise, float f, class_4587 pose, class_4597 buffer, int p, int overlay) {
      pose.method_22903();
      class_2680 blockstate = pavise.method_11010();
      pose.method_46416(0.5F, 0.5F, 0.5F);
      float yrot = -(Integer)blockstate.method_11654(class_2215.field_9924) * 360 / 16.0F;
      pose.method_22907(class_7833.field_40716.rotationDegrees(yrot));
      this.renderPatterns(pavise, pose, buffer, p, class_4608.field_21444);
      pose.method_22909();
   }

   public void renderPatterns(PaviseBlockEntity pavise, class_4587 pose, class_4597 buffer, int p, int overlay) {
      PaviseBlockModel basecolor = this.model;
      if (basecolor instanceof MedievalShieldModel) {
         pose.method_22903();
         pose.method_22905(1.0F, -1.0F, -1.0F);
         class_1767 basecolorx = pavise.getBaseColor();
         class_4588 vertexconsumer = this.getBaseMaterial(basecolorx != null)
            .method_24148()
            .method_24108(
               class_918.method_29711(buffer, this.model.method_23500(this.getBaseMaterial(basecolorx != null).method_24144()), true, pavise.hasFoil())
            );
         basecolor.handle().method_22699(pose, vertexconsumer, p, overlay, 16777215);
         class_9307 patterns = pavise.getPatterns();
         List<Pair<class_6880<class_2582>, class_1767>> list = (List<Pair<class_6880<class_2582>, class_1767>>)(patterns == null
            ? new ArrayList<>()
            : patterns.comp_2428().stream().map(l -> Pair.of(l.comp_2429(), l.comp_2430())).collect(Collectors.toList()));
         this.renderPatterns(pose, buffer, p, overlay, list, pavise.hasFoil(), basecolor.plate(), basecolorx);
         pose.method_22909();
      }
   }

   @Override
   public class_4730 getBaseMaterial(boolean withPattern) {
      return withPattern ? this.baseWithPatternMaterial : this.baseWithoutPatternMaterial;
   }

   @Override
   public class_4730 getBasePatternMaterial() {
      return this.basePatternMaterial;
   }

   @Override
   public class_4730 getPatternMaterial(class_2960 patternlocation) {
      return new class_4730(
         class_4722.field_21707, class_2960.method_60655(this.location.method_12836(), this.patternsDirectory + patternlocation.method_12832())
      );
   }
}
