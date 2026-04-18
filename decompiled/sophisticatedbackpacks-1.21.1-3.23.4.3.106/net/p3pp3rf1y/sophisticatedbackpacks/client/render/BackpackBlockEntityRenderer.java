package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import javax.annotation.Nullable;
import net.minecraft.class_1937;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade.TankRenderInfo;

public class BackpackBlockEntityRenderer implements class_827<BackpackBlockEntity> {
   public void render(
      BackpackBlockEntity backpackBlockEntity, float partialTicks, class_4587 poseStack, class_4597 buffer, int combinedLight, int combinedOverlay
   ) {
      class_2680 state = backpackBlockEntity.method_11010();
      class_2350 facing = (class_2350)state.method_11654(BackpackBlock.FACING);
      boolean showLeftTank = (Boolean)state.method_11654(BackpackBlock.LEFT_TANK);
      boolean showRightTank = (Boolean)state.method_11654(BackpackBlock.RIGHT_TANK);
      boolean showBattery = (Boolean)state.method_11654(BackpackBlock.BATTERY);
      IBackpackWrapper backpackWrapper = backpackBlockEntity.getBackpackWrapper();
      RenderInfo renderInfo = backpackWrapper.getRenderInfo();
      poseStack.method_22903();
      poseStack.method_22904(0.5, 0.0, 0.5);
      poseStack.method_22907(class_7833.field_40715.rotationDegrees(facing.method_10144()));
      poseStack.method_22903();
      poseStack.method_22905(0.6F, 0.6F, 0.6F);
      poseStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
      poseStack.method_22904(0.0, -2.5, 0.0);
      IBackpackModel model = BackpackModelManager.getBackpackModel(backpackWrapper.getBackpack().method_7909());
      if (showLeftTank) {
         TankRenderInfo tankRenderInfo = (TankRenderInfo)renderInfo.getTankRenderInfos().get(TankPosition.LEFT);
         if (tankRenderInfo != null) {
            poseStack.method_22903();
            poseStack.method_22904(1.45, 0.0, 0.0);
            tankRenderInfo.getFluid().ifPresent(fluid -> model.renderFluid(poseStack, buffer, combinedLight, fluid, tankRenderInfo.getFillRatio(), true));
            poseStack.method_22909();
         }
      }

      if (showRightTank) {
         TankRenderInfo tankRenderInfo = (TankRenderInfo)renderInfo.getTankRenderInfos().get(TankPosition.RIGHT);
         if (tankRenderInfo != null) {
            poseStack.method_22903();
            poseStack.method_22904(-1.45, 0.0, 0.0);
            tankRenderInfo.getFluid().ifPresent(fluid -> model.renderFluid(poseStack, buffer, combinedLight, fluid, tankRenderInfo.getFillRatio(), false));
            poseStack.method_22909();
         }
      }

      poseStack.method_22909();
      if (showBattery) {
         renderInfo.getBatteryRenderInfo().ifPresent(batteryRenderInfo -> {
            if (batteryRenderInfo.getChargeRatio() > 0.1F) {
               poseStack.method_22903();
               poseStack.method_22907(class_7833.field_40713.rotationDegrees(180.0F));
               poseStack.method_22904(0.0, -1.5, 0.0);
               model.renderBatteryCharge(poseStack, buffer, combinedLight, batteryRenderInfo.getChargeRatio());
               poseStack.method_22909();
            }
         });
      }

      this.renderItemDisplay(poseStack, buffer, combinedLight, combinedOverlay, renderInfo, backpackBlockEntity.method_10997());
      poseStack.method_22909();
   }

   private void renderItemDisplay(
      class_4587 poseStack, class_4597 buffer, int combinedLight, int combinedOverlay, RenderInfo renderInfo, @Nullable class_1937 level
   ) {
      renderInfo.getItemDisplayRenderInfo()
         .getDisplayItem()
         .ifPresent(
            displayItem -> {
               poseStack.method_22903();
               poseStack.method_22904(0.0, 0.6, 0.25);
               poseStack.method_22905(0.5F, 0.5F, 0.5F);
               poseStack.method_22907(class_7833.field_40713.rotationDegrees(180.0F));
               poseStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F + displayItem.getRotation()));
               class_310.method_1551()
                  .method_1480()
                  .method_23178(displayItem.getItem(), class_811.field_4319, combinedLight, combinedOverlay, poseStack, buffer, level, 0);
               poseStack.method_22909();
            }
         );
   }
}
