package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import javax.annotation.Nullable;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_583;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.api.IUpgradeRenderer;
import net.p3pp3rf1y.sophisticatedcore.client.render.UpgradeRenderRegistry;
import net.p3pp3rf1y.sophisticatedcore.renderdata.IUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.UpgradeRenderDataType;
import org.joml.Vector3f;

public class BackpackLayerRenderer<T extends class_1309, M extends class_583<T>> extends class_3887<T, M> {
   public BackpackLayerRenderer(class_3883<T, M> entityRendererIn) {
      super(entityRendererIn);
      BackpackModelManager.initModels();
   }

   public void render(
      class_4587 poseStack,
      class_4597 buffer,
      int packedLight,
      T entity,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch
   ) {
      if (entity instanceof class_742 player) {
         PlayerInventoryProvider.get()
            .getBackpackFromRendered(player)
            .ifPresent(
               backpackRenderInfo -> {
                  poseStack.method_22903();
                  class_1799 backpack = backpackRenderInfo.getBackpack();
                  IBackpackModel model = BackpackModelManager.getBackpackModel(backpack.method_7909());
                  class_1304 equipmentSlot = model.getRenderEquipmentSlot();
                  boolean wearsArmor = (equipmentSlot != class_1304.field_6174 || !backpackRenderInfo.isArmorSlot())
                     && !((class_1799)player.method_31548().field_7548.get(equipmentSlot.method_5927())).method_7960();
                  renderBackpack((M)this.method_17165(), player, poseStack, buffer, packedLight, backpack, wearsArmor, model);
                  poseStack.method_22909();
               }
            );
      } else {
         poseStack.method_22903();
         class_1799 chestStack = entity.method_6118(class_1304.field_6174);
         if (chestStack.method_7909() instanceof BackpackItem) {
            renderBackpack(
               (M)this.method_17165(),
               entity,
               poseStack,
               buffer,
               packedLight,
               chestStack,
               false,
               BackpackModelManager.getBackpackModel(chestStack.method_7909())
            );
         }

         poseStack.method_22909();
      }
   }

   public static <T extends class_1309, M extends class_583<T>> void renderBackpack(
      M parentModel,
      class_1309 livingEntity,
      class_4587 matrixStack,
      class_4597 buffer,
      int packedLight,
      class_1799 backpack,
      boolean wearsArmor,
      IBackpackModel model
   ) {
      model.translateRotateAndScale(parentModel, livingEntity, matrixStack, wearsArmor);
      IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
      int clothColor = wrapper.getMainColor();
      int borderColor = wrapper.getAccentColor();
      model.render(parentModel, livingEntity, matrixStack, buffer, packedLight, clothColor, borderColor, backpack.method_7909(), wrapper.getRenderInfo());
      renderUpgrades(livingEntity, wrapper.getRenderInfo());
      renderItemShown(matrixStack, buffer, packedLight, wrapper.getRenderInfo(), livingEntity.method_37908());
   }

   private static void renderItemShown(class_4587 matrixStack, class_4597 buffer, int packedLight, RenderInfo renderInfo, @Nullable class_1937 level) {
      renderInfo.getItemDisplayRenderInfo()
         .getDisplayItem()
         .ifPresent(
            displayItem -> {
               matrixStack.method_22903();
               matrixStack.method_22904(0.0, 0.9, -0.25);
               matrixStack.method_22905(0.5F, 0.5F, 0.5F);
               matrixStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F + displayItem.getRotation()));
               class_310.method_1551()
                  .method_1480()
                  .method_23178(displayItem.getItem(), class_811.field_4319, packedLight, class_4608.field_21444, matrixStack, buffer, level, 0);
               matrixStack.method_22909();
            }
         );
   }

   private static void renderUpgrades(class_1309 livingEntity, RenderInfo renderInfo) {
      if (!class_310.method_1551().method_1493() && livingEntity.method_37908().field_9229.method_43048(32) == 0) {
         renderInfo.getUpgradeRenderData()
            .forEach(
               (type, data) -> UpgradeRenderRegistry.getUpgradeRenderer(type)
                  .ifPresent(renderer -> renderUpgrade((IUpgradeRenderer<T>)renderer, livingEntity, (UpgradeRenderDataType<?>)type, data))
            );
      }
   }

   private static Vector3f getBackpackMiddleFacePoint(class_1309 livingEntity, Vector3f vector) {
      Vector3f point = new Vector3f(vector);
      point.rotate(class_7833.field_40714.rotationDegrees(livingEntity.method_18276() ? 25.0F : 0.0F));
      point.add(0.0F, 0.8F, livingEntity.method_18276() ? 0.9F : 0.7F);
      point.rotate(class_7833.field_40715.rotationDegrees(livingEntity.field_6283 - 180.0F));
      point.add(livingEntity.method_19538().method_46409());
      return point;
   }

   private static <T extends IUpgradeRenderData> void renderUpgrade(
      IUpgradeRenderer<T> renderer, class_1309 livingEntity, UpgradeRenderDataType<?> type, IUpgradeRenderData data
   ) {
      type.cast(data)
         .ifPresent(
            renderData -> renderer.render(
               livingEntity.method_37908(), livingEntity.method_37908().field_9229, vector3d -> getBackpackMiddleFacePoint(livingEntity, vector3d), renderData
            )
         );
   }
}
