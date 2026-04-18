package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.class_1058;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1723;
import net.minecraft.class_1792;
import net.minecraft.class_1921;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4592;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_572;
import net.minecraft.class_583;
import net.minecraft.class_630;
import net.minecraft.class_7833;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedBatteryUpgrade.BatteryRenderInfo;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade.TankRenderInfo;

public class BackpackModel extends class_4592<class_1309> implements IBackpackModel {
   private static final Map<class_1299<?>, class_243> entityTranslations = new HashMap<>();
   private static final class_2960 BACKPACK_ENTITY_TEXTURE = class_2960.method_60655("sophisticatedbackpacks", "textures/entity/backpack.png");
   private static final class_2960 TANK_GLASS_TEXTURE = class_2960.method_60655("sophisticatedbackpacks", "textures/entity/tank_glass.png");
   public static final float CHILD_Y_OFFSET = 0.3F;
   public static final float CHILD_Z_OFFSET = 0.1F;
   public static final float CHILD_SCALE = 0.55F;
   private static final String CLOTH_PART = "cloth";
   private static final String RIGHT_POUCHES_BORDER_PART = "rightPouchesBorder";
   private static final String LEFT_POUCHES_BORDER_PART = "leftPouchesBorder";
   private static final String FRONT_POUCH_BORDER_PART = "frontPouchBorder";
   private static final String FRONT_POUCH_PART = "frontPouch";
   private static final String RIGHT_POUCHES_PART = "rightPouches";
   private static final String LEFT_POUCHES_PART = "leftPouches";
   private static final String BORDER_PART = "border";
   private static final String FABRIC_FRONT_PART = "fabricFront";
   private static final String FABRIC_RIGHT_PART = "fabricRight";
   private static final String FABRIC_LEFT_PART = "fabricLeft";
   private static final String FABRIC_PART = "fabric";
   private static final String BATTERY_BORDER_PART = "batteryBorder";
   private static final String BATTERY_PART = "battery";
   private static final String LEFT_TANK_BORDER_PART = "leftTankBorder";
   private static final String LEFT_TANK_PART = "leftTank";
   private static final String RIGHT_TANK_BORDER_PART = "rightTankBorder";
   private static final String RIGHT_TANK_PART = "rightTank";
   private static final String BODY_CLIPS_PART = "bodyClips_";
   private static final String LEFT_POUCHES_CLIPS_PART = "leftPouchesClips_";
   private static final String RIGHT_POUCHES_CLIPS_PART = "rightPouchesClips_";
   private static final String FRONT_POUCH_CLIPS_PART = "frontPouchClips_";
   private static final String BATTERY_CLIPS_PART = "batteryClips_";
   private static final String LEFT_TANK_GLASS_PART = "leftTankGlass";
   private static final String RIGHT_TANK_GLASS_PART = "rightTankGlass";
   private static final String BATTERY_CHARGE_PART = "battery_charge_";
   private final Map<class_1792, class_630> clipsBody;
   private final Map<class_1792, class_630> clipsLeftPouches;
   private final Map<class_1792, class_630> clipsRightPouches;
   private final Map<class_1792, class_630> clipsFrontPouch;
   private final Map<class_1792, class_630> clipsBattery;
   private final Map<BackpackModel.FluidBarCacheKey, class_630> fluidLevelsLeft = new HashMap<>();
   private final Map<BackpackModel.FluidBarCacheKey, class_630> fluidLevelsRight = new HashMap<>();
   private final Map<Integer, class_630> batteryCharges;
   public final class_630 cloth;
   private final class_630 rightPouchesBorder;
   private final class_630 leftPouchesBorder;
   private final class_630 frontPouchBorder;
   private final class_630 frontPouch;
   private final class_630 rightPouches;
   private final class_630 leftPouches;
   public final class_630 border;
   private final class_630 fabricFront;
   private final class_630 fabricRight;
   private final class_630 fabricLeft;
   public final class_630 fabric;
   private final class_630 battery;
   private final class_630 batteryBorder;
   private final class_630 leftTank;
   private final class_630 leftTankBorder;
   private final class_630 rightTank;
   private final class_630 rightTankBorder;
   public final class_630 leftTankGlass;
   public final class_630 rightTankGlass;

   public BackpackModel(class_630 part) {
      this.cloth = part.method_32086("cloth");
      this.rightPouchesBorder = part.method_32086("rightPouchesBorder");
      this.leftPouchesBorder = part.method_32086("leftPouchesBorder");
      this.frontPouchBorder = part.method_32086("frontPouchBorder");
      this.frontPouch = part.method_32086("frontPouch");
      this.rightPouches = part.method_32086("rightPouches");
      this.leftPouches = part.method_32086("leftPouches");
      this.border = part.method_32086("border");
      this.fabricFront = part.method_32086("fabricFront");
      this.fabricRight = part.method_32086("fabricRight");
      this.fabricLeft = part.method_32086("fabricLeft");
      this.fabric = part.method_32086("fabric");
      this.battery = part.method_32086("battery");
      this.batteryBorder = part.method_32086("batteryBorder");
      this.leftTank = part.method_32086("leftTank");
      this.leftTankBorder = part.method_32086("leftTankBorder");
      this.rightTank = part.method_32086("rightTank");
      this.rightTankBorder = part.method_32086("rightTankBorder");
      Builder<class_1792, class_630> clipsBodyBuilder = ImmutableMap.builder();
      Builder<class_1792, class_630> clipsLeftPouchesBuilder = ImmutableMap.builder();
      Builder<class_1792, class_630> clipsRightPouchesBuilder = ImmutableMap.builder();
      Builder<class_1792, class_630> clipsFrontPouchBuilder = ImmutableMap.builder();
      Builder<class_1792, class_630> clipsBatteryBuilder = ImmutableMap.builder();
      getBackpackItems().values().forEach(backpackItem -> {
         clipsBodyBuilder.put(backpackItem, part.method_32086(getTierPartName(backpackItem, "bodyClips_")));
         clipsLeftPouchesBuilder.put(backpackItem, part.method_32086(getTierPartName(backpackItem, "leftPouchesClips_")));
         clipsRightPouchesBuilder.put(backpackItem, part.method_32086(getTierPartName(backpackItem, "rightPouchesClips_")));
         clipsFrontPouchBuilder.put(backpackItem, part.method_32086(getTierPartName(backpackItem, "frontPouchClips_")));
         clipsBatteryBuilder.put(backpackItem, part.method_32086(getTierPartName(backpackItem, "batteryClips_")));
      });
      Builder<Integer, class_630> batteryChargeBuilder = ImmutableMap.builder();

      for (int pixels = 1; pixels < 5; pixels++) {
         batteryChargeBuilder.put(pixels, part.method_32086("battery_charge_" + pixels));
      }

      this.batteryCharges = batteryChargeBuilder.build();
      this.clipsBody = clipsBodyBuilder.build();
      this.clipsLeftPouches = clipsLeftPouchesBuilder.build();
      this.clipsRightPouches = clipsRightPouchesBuilder.build();
      this.clipsFrontPouch = clipsFrontPouchBuilder.build();
      this.clipsBattery = clipsBatteryBuilder.build();
      class_630 modelPart = this.getGlassModelPart();
      this.leftTankGlass = modelPart.method_32086("leftTankGlass");
      this.rightTankGlass = modelPart.method_32086("rightTankGlass");
   }

   @Nonnull
   private class_630 getGlassModelPart() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partDefinition = meshdefinition.method_32111();
      partDefinition.method_32117(
         "leftTankGlass",
         class_5606.method_32108()
            .method_32101(18, 5)
            .method_32097(-15.0F, 3.5F, -2.5F, 4.0F, 10.0F, 0.01F)
            .method_32101(0, 0)
            .method_32097(-15.0F, 3.5F, -2.5F, 0.01F, 10.0F, 5.0F)
            .method_32101(10, 5)
            .method_32097(-15.0F, 3.5F, 2.5F, 4.0F, 10.0F, 0.01F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "rightTankGlass",
         class_5606.method_32108()
            .method_32101(18, 5)
            .method_32100(11.0F, 3.5F, -2.5F, 4.0F, 10.0F, 0.01F, true)
            .method_32101(0, 0)
            .method_32100(15.0F, 3.5F, -2.5F, 0.01F, 10.0F, 5.0F, true)
            .method_32101(10, 5)
            .method_32100(11.0F, 3.5F, 2.5F, 4.0F, 10.0F, 0.01F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      return partDefinition.method_32112(32, 32);
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partDefinition = meshdefinition.method_32111();
      partDefinition.method_32117(
         "cloth",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32097(-3.5F, -13.25F, -3.25F, 7.0F, 4.0F, 6.0F)
            .method_32101(0, 10)
            .method_32097(-5.0F, -13.0F, -3.0F, 10.0F, 13.0F, 6.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "rightPouchesBorder",
         class_5606.method_32108().method_32101(44, 0).method_32100(5.0F, -2.0F, -2.5F, 2.0F, 1.0F, 5.0F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "leftPouchesBorder",
         class_5606.method_32108().method_32101(44, 0).method_32097(-7.0F, -2.0F, -2.5F, 2.0F, 1.0F, 5.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "frontPouchBorder",
         class_5606.method_32108().method_32101(44, 0).method_32097(-4.0F, -2.0F, -5.0F, 8.0F, 1.0F, 2.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "frontPouch",
         class_5606.method_32108()
            .method_32101(25, 0)
            .method_32097(-4.0F, -1.0F, -5.0F, 8.0F, 1.0F, 2.0F)
            .method_32101(13, 2)
            .method_32097(-4.0F, -4.0F, -5.0F, 8.0F, 2.0F, 2.0F)
            .method_32101(13, 0)
            .method_32097(-4.0F, -6.0F, -5.0F, 8.0F, 1.0F, 2.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "rightPouches",
         class_5606.method_32108()
            .method_32101(32, 5)
            .method_32097(5.0F, -1.0F, -2.5F, 2.0F, 1.0F, 5.0F)
            .method_32101(32, 13)
            .method_32097(5.0F, -4.0F, -2.5F, 2.0F, 2.0F, 5.0F)
            .method_32101(32, 11)
            .method_32097(5.0F, -6.0F, -2.5F, 2.0F, 1.0F, 5.0F)
            .method_32101(32, 22)
            .method_32097(5.0F, -9.0F, -2.5F, 1.0F, 2.0F, 5.0F)
            .method_32101(32, 20)
            .method_32097(5.0F, -11.0F, -2.5F, 1.0F, 1.0F, 5.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "leftPouches",
         class_5606.method_32108()
            .method_32101(32, 5)
            .method_32100(-7.0F, -1.0F, -2.5F, 2.0F, 1.0F, 5.0F, true)
            .method_32101(32, 13)
            .method_32100(-7.0F, -4.0F, -2.5F, 2.0F, 2.0F, 5.0F, true)
            .method_32101(32, 11)
            .method_32100(-7.0F, -6.0F, -2.5F, 2.0F, 1.0F, 5.0F, true)
            .method_32101(32, 22)
            .method_32100(-6.0F, -9.0F, -2.5F, 1.0F, 2.0F, 5.0F, true)
            .method_32101(32, 20)
            .method_32100(-6.0F, -11.0F, -2.5F, 1.0F, 1.0F, 5.0F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "border",
         class_5606.method_32108()
            .method_32101(44, 7)
            .method_32097(-3.5F, -9.25F, -3.25F, 7.0F, 1.0F, 1.0F)
            .method_32101(50, 20)
            .method_32097(3.5F, -13.25F, -3.25F, 1.0F, 5.0F, 6.0F)
            .method_32101(50, 9)
            .method_32097(-4.5F, -13.25F, -3.25F, 1.0F, 5.0F, 6.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "fabricFront",
         class_5606.method_32108().method_32101(12, 58).method_32100(-0.75F, 3.0F, 1.0F, 8.0F, 1.0F, 2.0F, true),
         class_5603.method_32090(-3.25F, 16.0F, -6.0F)
      );
      partDefinition.method_32117(
         "fabricRight",
         class_5606.method_32108()
            .method_32101(32, 49)
            .method_32100(8.25F, -2.0F, 3.5F, 1.0F, 1.0F, 5.0F, true)
            .method_32101(8, 48)
            .method_32100(8.25F, 3.0F, 3.5F, 2.0F, 1.0F, 5.0F, true),
         class_5603.method_32090(-3.25F, 16.0F, -6.0F)
      );
      partDefinition.method_32117(
         "fabricLeft",
         class_5606.method_32108()
            .method_32101(32, 49)
            .method_32097(-2.75F, -2.0F, 3.5F, 1.0F, 1.0F, 5.0F)
            .method_32101(8, 48)
            .method_32097(-3.75F, 3.0F, 3.5F, 2.0F, 1.0F, 5.0F),
         class_5603.method_32090(-3.25F, 16.0F, -6.0F)
      );
      partDefinition.method_32117(
         "fabric",
         class_5606.method_32108()
            .method_32101(54, 0)
            .method_32097(1.25F, -4.75F, 5.75F, 1.0F, 1.0F, 1.0F)
            .method_32101(58, 0)
            .method_32097(4.25F, -4.75F, 5.75F, 1.0F, 1.0F, 1.0F)
            .method_32101(44, 0)
            .method_32100(1.25F, -5.75F, 5.75F, 4.0F, 1.0F, 1.0F, true)
            .method_32101(16, 47)
            .method_32097(0.0F, -5.5F, 2.5F, 1.0F, 4.0F, 7.0F)
            .method_32101(0, 47)
            .method_32097(5.5F, -5.5F, 2.5F, 1.0F, 4.0F, 7.0F),
         class_5603.method_32090(-3.25F, 16.0F, -6.0F)
      );
      partDefinition.method_32117(
         "batteryBorder",
         class_5606.method_32108()
            .method_32101(28, 38)
            .method_32097(-4.25F, -5.25F, -6.25F, 1.0F, 1.0F, 4.0F)
            .method_32101(28, 43)
            .method_32097(-3.5F, -5.25F, -6.25F, 7.0F, 1.0F, 1.0F)
            .method_32101(33, 38)
            .method_32097(-4.25F, -1.25F, -6.25F, 1.0F, 1.0F, 4.0F)
            .method_32101(33, 38)
            .method_32097(3.25F, -5.25F, -6.25F, 1.0F, 1.0F, 4.0F)
            .method_32101(27, 45)
            .method_32097(-3.5F, -1.25F, -6.25F, 7.0F, 1.0F, 1.0F)
            .method_32101(39, 37)
            .method_32097(3.25F, -1.25F, -6.25F, 1.0F, 1.0F, 4.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "battery",
         class_5606.method_32108()
            .method_32101(28, 29)
            .method_32097(-4.0F, -6.0F, -6.0F, 8.0F, 6.0F, 3.0F)
            .method_32101(28, 53)
            .method_32097(-2.0F, -6.25F, -4.5F, 1.0F, 1.0F, 1.0F)
            .method_32101(28, 53)
            .method_32097(-0.75F, -6.25F, -4.5F, 1.0F, 1.0F, 1.0F)
            .method_32101(28, 53)
            .method_32097(-2.0F, -8.0F, -3.25F, 1.0F, 1.0F, 1.0F)
            .method_32101(28, 53)
            .method_32097(-0.75F, -8.0F, -3.25F, 1.0F, 1.0F, 1.0F)
            .method_32101(0, 58)
            .method_32098(-2.0F, -7.4F, -4.5F, 1.0F, 2.0F, 1.0F, new class_5605(-0.2F))
            .method_32101(6, 58)
            .method_32098(-0.75F, -7.4F, -4.5F, 1.0F, 2.0F, 1.0F, new class_5605(-0.2F))
            .method_32101(0, 61)
            .method_32098(-2.0F, -8.0F, -4.5F, 1.0F, 1.0F, 2.0F, new class_5605(-0.2F))
            .method_32101(6, 61)
            .method_32098(-0.75F, -8.0F, -4.5F, 1.0F, 1.0F, 2.0F, new class_5605(-0.2F)),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "leftTankBorder",
         class_5606.method_32108().method_32101(50, 43).method_32097(-8.0F, -9.5F, -2.0F, 3.0F, 1.0F, 4.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "leftTank",
         class_5606.method_32108()
            .method_32101(54, 27)
            .method_32097(-5.5F, -7.5F, -2.0F, 1.0F, 6.0F, 4.0F)
            .method_32101(50, 37)
            .method_32097(-8.0F, -1.5F, -2.0F, 3.0F, 1.0F, 4.0F)
            .method_32101(50, 42)
            .method_32097(-8.0F, -8.5F, -2.0F, 3.0F, 1.0F, 4.0F)
            .method_32101(50, 37)
            .method_32097(-8.0F, -10.5F, -2.0F, 3.0F, 1.0F, 4.0F)
            .method_32101(52, 48)
            .method_32097(-7.5F, -11.5F, -1.5F, 3.0F, 1.0F, 3.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "rightTankBorder",
         class_5606.method_32108().method_32101(50, 43).method_32100(5.0F, -9.5F, -2.0F, 3.0F, 1.0F, 4.0F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
      partDefinition.method_32117(
         "rightTank",
         class_5606.method_32108()
            .method_32101(54, 27)
            .method_32100(4.5F, -7.5F, -2.0F, 1.0F, 6.0F, 4.0F, true)
            .method_32101(50, 37)
            .method_32100(5.0F, -1.5F, -2.0F, 3.0F, 1.0F, 4.0F, true)
            .method_32101(50, 42)
            .method_32100(5.0F, -8.5F, -2.0F, 3.0F, 1.0F, 4.0F, true)
            .method_32101(50, 37)
            .method_32100(5.0F, -10.5F, -2.0F, 3.0F, 1.0F, 4.0F, true)
            .method_32101(52, 48)
            .method_32100(4.5F, -11.5F, -1.5F, 3.0F, 1.0F, 3.0F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );

      for (int pixels = 1; pixels < 5; pixels++) {
         partDefinition.method_32117(
            "battery_charge_" + pixels,
            class_5606.method_32108().method_32101(30, 58).method_32097(-2.0F, 21.0F, -6.01F, pixels, 1.0F, 1.0F),
            class_5603.field_27701
         );
      }

      getBackpackItems().forEach((idx, item) -> {
         addBodyClips(partDefinition, item, 29 + idx * 3);
         addLeftPouchesClips(partDefinition, item, 29 + idx * 3);
         addRightPouchesClips(partDefinition, item, 29 + idx * 3);
         addFrontPouchClips(partDefinition, item, 29 + idx * 3);
         addBatteryClips(partDefinition, item, 30 + idx * 3);
      });
      return class_5607.method_32110(meshdefinition, 64, 64);
   }

   private static Map<Integer, class_1792> getBackpackItems() {
      return new LinkedHashMap<>(
         Map.of(
            0,
            (BackpackItem)ModItems.BACKPACK.get(),
            1,
            (BackpackItem)ModItems.COPPER_BACKPACK.get(),
            2,
            (BackpackItem)ModItems.IRON_BACKPACK.get(),
            3,
            (BackpackItem)ModItems.GOLD_BACKPACK.get(),
            4,
            (BackpackItem)ModItems.DIAMOND_BACKPACK.get(),
            5,
            (BackpackItem)ModItems.NETHERITE_BACKPACK.get()
         )
      );
   }

   @Override
   public <L extends class_1309, M extends class_583<L>> void render(
      M parentModel,
      class_1309 livingEntity,
      class_4587 poseStack,
      class_4597 buffer,
      int packedLight,
      int clothColor,
      int borderColor,
      class_1792 backpackItem,
      RenderInfo renderInfo
   ) {
      class_4588 vertexBuilder = buffer.getBuffer(class_1921.method_23578(BACKPACK_ENTITY_TEXTURE));
      Set<TankPosition> tankPositions = renderInfo.getTankRenderInfos().keySet();
      boolean showLeftTank = tankPositions.contains(TankPosition.LEFT);
      boolean showRightTank = tankPositions.contains(TankPosition.RIGHT);
      Optional<BatteryRenderInfo> batteryRenderInfo = renderInfo.getBatteryRenderInfo();
      if (showLeftTank) {
         this.leftTank.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.leftTankBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      } else {
         this.fabricLeft.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.clipsLeftPouches.get(backpackItem).method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.leftPouches.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, clothColor);
         this.leftPouchesBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      }

      if (showRightTank) {
         this.rightTank.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.rightTankBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      } else {
         this.fabricRight.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.clipsRightPouches.get(backpackItem).method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.rightPouches.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, clothColor);
         this.rightPouchesBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      }

      if (batteryRenderInfo.isPresent()) {
         this.battery.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.batteryBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
         this.clipsBattery.get(backpackItem).method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
      } else {
         this.fabricFront.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.clipsFrontPouch.get(backpackItem).method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
         this.frontPouch.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, clothColor);
         this.frontPouchBorder.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      }

      this.fabric.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
      this.clipsBody.get(backpackItem).method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
      this.cloth.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, clothColor);
      this.border.method_22699(poseStack, vertexBuilder, packedLight, class_4608.field_21444, borderColor);
      poseStack.method_22903();
      poseStack.method_22905(0.5F, 0.6F, 0.5F);
      if (showLeftTank) {
         vertexBuilder = buffer.getBuffer(class_1921.method_23578(TANK_GLASS_TEXTURE));
         this.leftTankGlass.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
      }

      if (showRightTank) {
         vertexBuilder = buffer.getBuffer(class_1921.method_23578(TANK_GLASS_TEXTURE));
         this.rightTankGlass.method_22698(poseStack, vertexBuilder, packedLight, class_4608.field_21444);
      }

      if (showLeftTank) {
         TankRenderInfo tankRenderInfo = (TankRenderInfo)renderInfo.getTankRenderInfos().get(TankPosition.LEFT);
         tankRenderInfo.getFluid().ifPresent(f -> this.renderFluid(poseStack, buffer, packedLight, f, tankRenderInfo.getFillRatio(), true));
      }

      if (showRightTank) {
         TankRenderInfo tankRenderInfo = (TankRenderInfo)renderInfo.getTankRenderInfos().get(TankPosition.RIGHT);
         tankRenderInfo.getFluid().ifPresent(f -> this.renderFluid(poseStack, buffer, packedLight, f, tankRenderInfo.getFillRatio(), false));
      }

      poseStack.method_22909();
      if (batteryRenderInfo.isPresent()) {
         batteryRenderInfo.ifPresent(info -> this.renderBatteryCharge(poseStack, buffer, packedLight, info.getChargeRatio()));
      }
   }

   @Override
   public void renderBatteryCharge(class_4587 matrixStack, class_4597 buffer, int packedLight, float chargeRatio) {
      class_630 charge = this.batteryCharges.get((int)(chargeRatio * 4.0F));
      if (charge != null) {
         class_4588 vertexBuilder = buffer.getBuffer(class_1921.method_23580(BACKPACK_ENTITY_TEXTURE));
         charge.method_22698(matrixStack, vertexBuilder, packedLight, class_4608.field_21444);
      }
   }

   @Override
   public void renderFluid(class_4587 matrixStack, class_4597 buffer, int packedLight, FluidStack fluidStack, float fill, boolean left) {
      if (!class_3532.method_15347(fill, 0.0F)) {
         FluidVariant fluidVariant = FluidVariant.of(fluidStack.getFluid());
         class_1058 still = FluidVariantRendering.getSprite(fluidVariant);
         if (still != null) {
            class_4588 vertexBuilder = buffer.getBuffer(class_1921.method_23580(class_1723.field_21668));
            class_630 fluidBox = this.getFluidBar(still, (int)(fill * 10.0F), left);
            int color = FluidVariantRendering.getColor(fluidVariant);
            fluidBox.method_22699(matrixStack, vertexBuilder, packedLight, class_4608.field_21444, color);
         }
      }
   }

   private class_630 getFluidBar(class_1058 still, int fill, boolean left) {
      int atlasWidth = (int)(still.method_45851().method_45807() / (still.method_4577() - still.method_4594()));
      int atlasHeight = (int)(still.method_45851().method_45815() / (still.method_4575() - still.method_4593()));
      int u = (int)(still.method_4594() * atlasWidth);
      int v = (int)(still.method_4593() * atlasHeight);
      BackpackModel.FluidBarCacheKey key = new BackpackModel.FluidBarCacheKey(u, v, fill);
      Map<BackpackModel.FluidBarCacheKey, class_630> fluidLevels = left ? this.fluidLevelsLeft : this.fluidLevelsRight;
      return fluidLevels.computeIfAbsent(
         key,
         k -> {
            class_5609 meshdefinition = new class_5609();
            class_5610 partDefinition = meshdefinition.method_32111();
            partDefinition.method_32117(
               "fluid_fill",
               class_5606.method_32108().method_32101(u, v).method_32100(left ? -14.5F : 11.0F, 13.5F - fill, -2.0F, 3.5F, fill, 4.0F, !left),
               class_5603.method_32090(0.0F, 24.0F, 0.0F)
            );
            class_630 root = partDefinition.method_32112(atlasWidth, atlasHeight);
            return root.method_32086("fluid_fill");
         }
      );
   }

   @Override
   public class_1304 getRenderEquipmentSlot() {
      return class_1304.field_6174;
   }

   @Override
   public <L extends class_1309, M extends class_583<L>> void translateRotateAndScale(
      M parentModel, class_1309 livingEntity, class_4587 poseStack, boolean wearsArmor
   ) {
      if (parentModel instanceof class_572<?> humanoidModel) {
         humanoidModel.field_3391.method_22703(poseStack);
      }

      poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F));
      float zOffset = wearsArmor ? -0.35F : -0.3F;
      float yOffset = -0.75F;
      if (livingEntity.method_6109()) {
         zOffset += 0.1F;
         yOffset = 0.3F;
      }

      poseStack.method_46416(0.0F, yOffset, zOffset);
      if (!(livingEntity instanceof class_1657)) {
         if (livingEntity.method_6109()) {
            poseStack.method_22905(0.55F, 0.55F, 0.55F);
         }

         if (entityTranslations.containsKey(livingEntity.method_5864())) {
            class_243 translVector = entityTranslations.get(livingEntity.method_5864());
            poseStack.method_22904(translVector.method_10216(), translVector.method_10214(), translVector.method_10215());
         }
      }
   }

   private static void addBodyClips(class_5610 partDefinition, class_1792 backpackItem, int yTextureOffset) {
      addBodyClips(partDefinition, backpackItem, 0, yTextureOffset);
   }

   private static void addBodyClips(class_5610 partDefinition, class_1792 backpackItem, int xTextureOffset, int yTextureOffset) {
      partDefinition.method_32117(
         getTierPartName(backpackItem, "bodyClips_"),
         class_5606.method_32108()
            .method_32101(22 + xTextureOffset, yTextureOffset)
            .method_32097(-3.25F, -9.5F, -3.5F, 1.0F, 2.0F, 1.0F)
            .method_32101(25 + xTextureOffset, yTextureOffset)
            .method_32097(2.25F, -9.5F, -3.5F, 1.0F, 2.0F, 1.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
   }

   private static String getTierPartName(class_1792 backpackItem, String partNamePrefix) {
      return partNamePrefix + class_7923.field_41178.method_10221(backpackItem).method_12832();
   }

   private static void addLeftPouchesClips(class_5610 partDefinition, class_1792 backpackItem, int yTextureOffset) {
      partDefinition.method_32117(
         getTierPartName(backpackItem, "leftPouchesClips_"),
         class_5606.method_32108()
            .method_32101(18, yTextureOffset)
            .method_32097(-6.25F, -10.0F, -0.5F, 1.0F, 2.0F, 1.0F)
            .method_32101(6, yTextureOffset)
            .method_32097(-7.25F, -5.0F, -0.5F, 1.0F, 2.0F, 1.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
   }

   private static void addRightPouchesClips(class_5610 partDefinition, class_1792 backpackItem, int yTextureOffset) {
      partDefinition.method_32117(
         getTierPartName(backpackItem, "rightPouchesClips_"),
         class_5606.method_32108()
            .method_32101(6, yTextureOffset)
            .method_32100(6.25F, -5.0F, -0.5F, 1.0F, 2.0F, 1.0F, true)
            .method_32101(18, yTextureOffset)
            .method_32100(5.25F, -10.0F, -0.5F, 1.0F, 2.0F, 1.0F, true),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
   }

   private static void addFrontPouchClips(class_5610 partDefinition, class_1792 backpackItem, int yTextureOffset) {
      partDefinition.method_32117(
         getTierPartName(backpackItem, "frontPouchClips_"),
         class_5606.method_32108()
            .method_32101(0, yTextureOffset)
            .method_32097(2.0F, -5.0F, -5.25F, 1.0F, 2.0F, 1.0F)
            .method_32101(3, yTextureOffset)
            .method_32097(-3.0F, -5.0F, -5.25F, 1.0F, 2.0F, 1.0F),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
   }

   private static void addBatteryClips(class_5610 partDefinition, class_1792 backpackItem, int yTextureOffset) {
      partDefinition.method_32117(
         getTierPartName(backpackItem, "batteryClips_"),
         class_5606.method_32108()
            .method_32101(24, yTextureOffset)
            .method_32098(1.0F, -5.25F, -6.15F, 1.0F, 1.0F, 1.0F, new class_5605(0.2F))
            .method_32101(21, yTextureOffset)
            .method_32098(1.0F, -1.25F, -6.15F, 1.0F, 1.0F, 1.0F, new class_5605(0.2F)),
         class_5603.method_32090(0.0F, 24.0F, 0.0F)
      );
   }

   public void method_2828(class_4587 matrixStack, class_4588 buffer, int packedLight, int packedOverlay, int color) {
   }

   protected Iterable<class_630> method_22946() {
      return Collections.emptyList();
   }

   protected Iterable<class_630> method_22948() {
      return Collections.emptyList();
   }

   public void setupAnim(class_1309 entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   static {
      entityTranslations.put(class_1299.field_6091, new class_243(0.0, -0.8, 0.0));
   }

   private record FluidBarCacheKey(int u, int v, int fill) {
      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            BackpackModel.FluidBarCacheKey that = (BackpackModel.FluidBarCacheKey)o;
            return this.u == that.u && this.v == that.v && this.fill == that.fill;
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.u, this.v, this.fill);
      }
   }
}
