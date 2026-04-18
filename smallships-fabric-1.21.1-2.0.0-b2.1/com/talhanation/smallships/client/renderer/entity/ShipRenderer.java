package com.talhanation.smallships.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.talhanation.smallships.client.model.CannonModel;
import com.talhanation.smallships.client.model.ShipModel;
import com.talhanation.smallships.client.model.sail.BriggSailModel;
import com.talhanation.smallships.client.model.sail.CogSailModel;
import com.talhanation.smallships.client.model.sail.DrakkarSailModel;
import com.talhanation.smallships.client.model.sail.GalleySailModel;
import com.talhanation.smallships.client.model.sail.SailModel;
import com.talhanation.smallships.world.entity.projectile.ShipCannon;
import com.talhanation.smallships.world.entity.ship.Attributes;
import com.talhanation.smallships.world.entity.ship.BriggEntity;
import com.talhanation.smallships.world.entity.ship.CogEntity;
import com.talhanation.smallships.world.entity.ship.DrakkarEntity;
import com.talhanation.smallships.world.entity.ship.GalleyEntity;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.entity.ship.abilities.Bannerable;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.Paddleable;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import com.talhanation.smallships.world.entity.ship.abilities.Shieldable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_1088;
import net.minecraft.class_1746;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_2398;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_4730;
import net.minecraft.class_5602;
import net.minecraft.class_600;
import net.minecraft.class_630;
import net.minecraft.class_7833;
import net.minecraft.class_823;
import net.minecraft.class_897;
import net.minecraft.class_918;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

public abstract class ShipRenderer<T extends Ship> extends class_897<T> {
   protected final Map<class_1692, Pair<class_2960, ShipModel<T>>> boatResources;
   private static final class_630 cannonModel = CannonModel.createBodyLayer().method_32109();
   private static final class_630 bannerModel;
   private static final class_600 shieldModel = new class_600(class_310.method_1551().method_31974().method_32072(class_5602.field_27595));
   private static final Map<Class<? extends Ship>, SailModel> sailModels = new HashMap<>();

   public ShipRenderer(class_5618 context) {
      super(context);
      this.field_4673 = 0.8F;
      this.boatResources = Stream.of(class_1692.values())
         .collect(ImmutableMap.toImmutableMap(type -> type, type -> Pair.of(this.getTextureLocation(type), this.createBoatModel(context, type))));
   }

   protected abstract ShipModel<T> createBoatModel(class_5618 var1, class_1692 var2);

   protected class_2960 getTextureLocation(class_1692 type) {
      return class_2960.method_60655("smallships", "textures/entity/ship/" + getNameFromType(type) + ".png");
   }

   @NotNull
   public class_2960 getTextureLocation(@NotNull T shipEntity) {
      return (class_2960)this.boatResources.get(shipEntity.method_47885()).getFirst();
   }

   public void render(T shipEntity, float entityYaw, float partialTicks, @NotNull class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight) {
      Attributes shipAttributes = shipEntity.getAttributes();
      float h = (shipEntity.method_54295() - partialTicks) / (shipAttributes.maxHealth * shipEntity.method_17681() / 40.0F);
      float j = shipEntity.method_54294() - partialTicks;
      if (j < 0.0F) {
         j = 0.0F;
      } else if (j > shipAttributes.maxHealth * 0.5F) {
         shipEntity.method_37908()
            .method_8406(class_2398.field_11237, shipEntity.method_23322(0.5), shipEntity.method_23318() + 1.0, shipEntity.method_23325(0.5), 0.0, 0.0, 0.0);
      }

      if (h > 0.0F) {
         poseStack.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_15374(h) * h * j / 10.0F * shipEntity.method_54296()));
      }

      float k = shipEntity.method_7547(partialTicks);
      if (!class_3532.method_15347(k, 0.0F)) {
         poseStack.method_22907(new Quaternionf().rotateX(k * (float) (Math.PI / 180.0)).rotateZ(k * (float) (Math.PI / 180.0)));
      }

      float l = shipEntity.getWaveAngle(partialTicks);
      if (!shipEntity.isSunken() && !class_3532.method_15347(l, 0.0F)) {
         poseStack.method_22907(this.getWaveAngleRotation().rotationDegrees(l));
      }

      Pair<class_2960, ShipModel<T>> pair = this.boatResources.get(shipEntity.method_47885());
      class_2960 resourceLocation = (class_2960)pair.getFirst();
      ShipModel<T> shipModel = (ShipModel<T>)pair.getSecond();
      poseStack.method_22905(-1.3F, -1.3F, 1.3F);
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(270.0F));
      shipModel.method_2819(shipEntity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
      if (shipEntity instanceof Cannonable cannonShipEntity) {
         this.renderCannon(cannonShipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
      }

      if (shipEntity instanceof Bannerable bannerShipEntity) {
         this.renderBanner(bannerShipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
      }

      if (shipEntity instanceof Paddleable paddleShipEntity) {
         this.renderPaddle(paddleShipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
      }

      if (shipEntity instanceof Sailable sailShipEntity) {
         this.renderSail(sailShipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
      }

      if (shipEntity instanceof Shieldable shieldShipEntity) {
         this.renderShields(shieldShipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
      }

      class_4588 vertexConsumer = multiBufferSource.getBuffer(shipModel.method_23500(resourceLocation));
      shipModel.method_2828(poseStack, vertexConsumer, packedLight, class_4608.field_21444, -1);
      poseStack.method_22909();
      super.method_3936(shipEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
   }

   private void renderCannon(
      Cannonable cannonShipEntity, float entityYaw, float partialTicks, class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      for (byte i = 0; i < cannonShipEntity.getCannonCount(); i++) {
         ShipCannon cannon = new ShipCannon(cannonShipEntity.self(), cannonShipEntity.getCannonPosition(i));
         poseStack.method_22903();
         poseStack.method_22907(class_7833.field_40715.rotationDegrees(this.getCannonAngleOffset() + cannon.getAngle()));
         poseStack.method_22904(
            cannon.isRightSided() ? -cannon.getOffsetX() : cannon.getOffsetX(), -cannon.getOffsetY() + this.getCannonHeightOffset(), -cannon.getOffsetZ()
         );
         poseStack.method_22905(0.6F, 0.6F, 0.6F);
         class_4588 vertexConsumer = multiBufferSource.getBuffer(class_1921.method_23572(cannonShipEntity.getTextureLocation()));
         cannonModel.method_22698(poseStack, vertexConsumer, packedLight, class_4608.field_21444);
         poseStack.method_22909();
      }
   }

   protected float getCannonAngleOffset() {
      return 0.0F;
   }

   protected float getCannonHeightOffset() {
      return 0.0F;
   }

   private void renderBanner(
      Bannerable bannerShipEntity, float entityYaw, float partialTicks, class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      class_1799 bannerItemStack = bannerShipEntity.self().getData(Ship.BANNER);
      if (bannerItemStack.method_7909() instanceof class_1746 bannerItem) {
         poseStack.method_22903();
         Bannerable.BannerPosition pos = bannerShipEntity.getBannerPosition();
         poseStack.method_22907(class_7833.field_40716.rotationDegrees(pos.yp));
         poseStack.method_22907(class_7833.field_40718.rotationDegrees(pos.zp));
         poseStack.method_22904(pos.x, pos.y, pos.z);
         poseStack.method_22905(0.5F, 0.5F, 0.5F);
         float bannerWaveAngle = bannerShipEntity.getBannerWaveAngle(partialTicks);
         if (!class_3532.method_15347(bannerWaveAngle, 0.0F)) {
            poseStack.method_22907(class_7833.field_40718.rotationDegrees(bannerWaveAngle * 0.5F));
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(bannerWaveAngle));
         }

         class_9307 bannerPatternLayers = (class_9307)bannerItemStack.method_57825(class_9334.field_49619, class_9307.field_49404);
         class_1767 dyeColor = ((class_1746)bannerItemStack.method_7909()).method_7706();
         class_823.method_29999(
            poseStack, multiBufferSource, packedLight, class_4608.field_21444, bannerModel, class_1088.field_20847, true, dyeColor, bannerPatternLayers
         );
         poseStack.method_22909();
      }
   }

   private void renderShields(
      Shieldable shieldShipEntity, float entityYaw, float partialTicks, class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      for (byte i = 0; i < shieldShipEntity.getShields().size(); i++) {
         class_1799 shieldItemStack = shieldShipEntity.getShields().get(i);
         if (shieldItemStack.method_31574(class_1802.field_8255)) {
            poseStack.method_22903();
            Shieldable.ShieldPosition pos = shieldShipEntity.getShieldPosition(i);
            poseStack.method_22904(pos.x, pos.y, pos.z);
            poseStack.method_22905(0.8F, -0.8F, -0.8F);
            if (pos.isRightSided) {
               poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F));
            }

            poseStack.method_22907(class_7833.field_40714.rotationDegrees(20.0F));
            poseStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
            class_9307 bannerPatternLayers = (class_9307)shieldItemStack.method_57825(class_9334.field_49619, class_9307.field_49404);
            class_1767 dyeColor = (class_1767)shieldItemStack.method_57824(class_9334.field_49620);
            boolean flag = !bannerPatternLayers.comp_2428().isEmpty() || dyeColor != null;
            class_4730 material = flag ? class_1088.field_21557 : class_1088.field_21558;
            class_4588 vertexConsumer = material.method_24148()
               .method_24108(class_918.method_29711(multiBufferSource, shieldModel.method_23500(material.method_24144()), true, shieldItemStack.method_7958()));
            if (flag) {
               class_823.method_23802(
                  poseStack,
                  multiBufferSource,
                  packedLight,
                  class_4608.field_21444,
                  shieldModel.method_23774(),
                  material,
                  false,
                  Objects.requireNonNullElse(dyeColor, class_1767.field_7952),
                  bannerPatternLayers,
                  shieldItemStack.method_7958()
               );
            } else {
               shieldModel.method_23774().method_22699(poseStack, vertexConsumer, packedLight, class_4608.field_21444, -1);
            }

            shieldModel.method_23775().method_22699(poseStack, vertexConsumer, packedLight, class_4608.field_21444, -1);
            poseStack.method_22909();
         }
      }
   }

   public class_7833 getWaveAngleRotation() {
      return class_7833.field_40717;
   }

   private void renderPaddle(
      Paddleable paddleShipEntity, float entityYaw, float partialTicks, class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
   }

   private void renderSail(
      Sailable sailShipEntity, float entityYaw, float partialTicks, class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      SailModel sailModel = sailModels.get(sailShipEntity.getClass());
      sailModel.method_2819((Ship)sailShipEntity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
      class_4588 vertexConsumer = multiBufferSource.getBuffer(
         sailModel.method_23500(SailModel.getSailColor(sailShipEntity.self().getData(Ship.SAIL_COLOR)).location)
      );
      sailModel.method_2828(poseStack, vertexConsumer, packedLight, class_4608.field_21444, -1);
   }

   public static String getNameFromType(class_1692 type) {
      return type.method_7559().replace(":", "/");
   }

   static {
      class_630 model = class_823.method_32135().method_32109();
      model.method_32086("pole").field_3665 = false;
      model.method_32086("bar").field_3665 = false;
      bannerModel = model;
      sailModels.put(CogEntity.class, new CogSailModel());
      sailModels.put(BriggEntity.class, new BriggSailModel());
      sailModels.put(GalleyEntity.class, new GalleySailModel());
      sailModels.put(DrakkarEntity.class, new DrakkarSailModel());
   }
}
