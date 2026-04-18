package net.p3pp3rf1y.sophisticatedbackpacks.compat.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackLayerRenderer;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackModelManager;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.IBackpackModel;

public class BackpackTrinket implements Trinket, TrinketRenderer {
   public void tick(class_1799 stack, SlotReference slot, class_1309 entity) {
      stack.method_7917(entity.method_37908(), entity, slot.index(), true);
   }

   public void render(
      class_1799 stack,
      SlotReference slotReference,
      class_583<? extends class_1309> entityModel,
      class_4587 poseStack,
      class_4597 multiBufferSource,
      int light,
      class_1309 livingEntity,
      float v,
      float v1,
      float v2,
      float v3,
      float v4,
      float v5
   ) {
      if (!stack.method_7960()) {
         poseStack.method_22903();
         IBackpackModel model = BackpackModelManager.getBackpackModel(stack.method_7909());
         class_1304 equipmentSlot = model.getRenderEquipmentSlot();
         BackpackLayerRenderer.renderBackpack(
            entityModel,
            livingEntity,
            poseStack,
            multiBufferSource,
            light,
            stack,
            !livingEntity.method_6118(equipmentSlot).method_7960(),
            BackpackModelManager.getBackpackModel(stack.method_7909())
         );
         poseStack.method_22909();
      }
   }
}
