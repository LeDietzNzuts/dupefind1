package com.magistuarmory.fabric.client.render.entity.layer;

import com.magistuarmory.client.render.ModRender;
import com.magistuarmory.item.DyeableItemLike;
import com.magistuarmory.item.armor.MedievalArmorItem;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_3489;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_572;
import net.minecraft.class_918;
import net.minecraft.class_1741.class_9196;
import net.minecraft.class_5253.class_5254;

@Environment(EnvType.CLIENT)
public class MedievalArmorLayer implements ArmorRenderer {
   public void render(class_4587 pose, class_4597 buffer, class_1799 stack, class_1309 entity, class_1304 slot, int i, class_572<class_1309> contextmodel) {
      if (stack.method_7909() instanceof MedievalArmorItem armor && armor.method_48398().method_48399() == slot) {
         class_572<? extends class_1309> model = armor.getArmorModel(slot, this.getVanillaArmorModel(slot));
         contextmodel.method_2818(model);
         this.setPartVisibility(model, slot);
         boolean foil = stack.method_7958();
         int color = -1;
         if (stack.method_7909() instanceof DyeableItemLike dyeableitem && stack.method_31573(class_3489.field_48803)) {
            color = class_5254.method_57174(dyeableitem.getColor(stack));
         }

         this.renderModel(pose, buffer, i, armor.getArmorType().getLayers(), foil, model, this.usesInnerModel(slot), color);
      }
   }

   protected void setPartVisibility(class_572<? extends class_1309> model, class_1304 slot) {
      model.method_2805(false);
      switch (slot) {
         case field_6169:
            model.field_3398.field_3665 = true;
            model.field_3394.field_3665 = true;
            break;
         case field_6174:
            model.field_3391.field_3665 = true;
            model.field_3401.field_3665 = true;
            model.field_27433.field_3665 = true;
            break;
         case field_6172:
            model.field_3391.field_3665 = true;
            model.field_3392.field_3665 = true;
            model.field_3397.field_3665 = true;
            break;
         case field_6166:
            model.field_3392.field_3665 = true;
            model.field_3397.field_3665 = true;
      }
   }

   private void renderModel(
      class_4587 pose, class_4597 buffer, int i, List<class_9196> layers, boolean foil, class_572<? extends class_1309> model, boolean secondLayer, int color
   ) {
      for (class_9196 layer : layers) {
         class_4588 vertexconsumer = class_918.method_27952(buffer, class_1921.method_25448(layer.method_56693(secondLayer)), foil);
         model.method_2828(pose, vertexconsumer, i, class_4608.field_21444, layer.method_56692() ? color : -1);
      }
   }

   private class_572<? extends class_1309> getVanillaArmorModel(class_1304 slot) {
      return this.usesInnerModel(slot) ? ModRender.INNER_ARMOR : ModRender.OUTER_ARMOR;
   }

   private boolean usesInnerModel(class_1304 slot) {
      return slot == class_1304.field_6172;
   }
}
