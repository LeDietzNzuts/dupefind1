package com.talhanation.smallships.world.entity.ship;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.entity.ModEntityTypes;
import com.talhanation.smallships.world.entity.ship.abilities.Ability;
import com.talhanation.smallships.world.entity.ship.abilities.Bannerable;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.Paddleable;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import com.talhanation.smallships.world.item.ModItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1690;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import org.jetbrains.annotations.NotNull;

public class GalleyEntity extends ContainerShip implements Bannerable, Sailable, Cannonable, Paddleable, Ability {
   public static final String ID = "galley";
   private static final int ORIGINAL_CONTAINER_SIZE = (Integer)SmallShipsConfig.Common.shipContainerGalleyContainerSize.get();

   public GalleyEntity(class_1299<? extends class_1690> entityType, class_1937 level) {
      super(entityType, level, ORIGINAL_CONTAINER_SIZE);
   }

   private GalleyEntity(class_1937 level, double d, double e, double f) {
      this(ModEntityTypes.GALLEY, level);
      this.method_5814(d, e, f);
      this.field_6014 = d;
      this.field_6036 = e;
      this.field_5969 = f;
   }

   public static GalleyEntity summon(class_1937 level, double d, double e, double f) {
      return new GalleyEntity(level, d, e, f);
   }

   @Override
   public class_2487 createDefaultAttributes() {
      Attributes attributes = new Attributes();
      attributes.maxHealth = ((Double)SmallShipsConfig.Common.shipAttributeGalleyMaxHealth.get()).floatValue();
      attributes.maxSpeed = ((Double)SmallShipsConfig.Common.shipAttributeGalleyMaxSpeed.get()).floatValue();
      attributes.maxReverseSpeed = ((Double)SmallShipsConfig.Common.shipAttributeGalleyMaxReverseSpeed.get()).floatValue();
      attributes.maxRotationSpeed = ((Double)SmallShipsConfig.Common.shipAttributeGalleyMaxRotationSpeed.get()).floatValue();
      attributes.acceleration = ((Double)SmallShipsConfig.Common.shipAttributeGalleyAcceleration.get()).floatValue();
      attributes.rotationAcceleration = ((Double)SmallShipsConfig.Common.shipAttributeGalleyRotationAcceleration.get()).floatValue();
      class_2487 tag = new class_2487();
      attributes.addSaveData(tag);
      return tag;
   }

   @Override
   public int method_42281() {
      return 9;
   }

   @NotNull
   @Override
   public class_1792 method_7557() {
      return !SmallShipsConfig.Common.shipGeneralDoItemDrop.get() ? class_1799.field_8037.method_7909() : ModItems.GALLEY_ITEMS.get(this.method_47885());
   }

   @Override
   public Ship.BiomeModifierType getBiomeModifierType() {
      return (Ship.BiomeModifierType)SmallShipsConfig.Common.shipModifierGalleyBiome.get();
   }

   @NotNull
   public class_243 method_52533(class_1297 entity, class_4048 dimensions, float partialTick) {
      float v = 1.0F;
      float h = 0.0F;
      if (!this.method_5685().isEmpty()) {
         int i = this.method_5685().indexOf(entity);
         switch (i) {
            case 0:
               v += -4.0F;
               h = 0.0F;
               break;
            case 1:
               v += -2.5F;
               h = 0.75F;
               break;
            case 2:
               v += -2.5F;
               h = -0.75F;
               break;
            case 3:
               v += -1.5F;
               h = -0.75F;
               break;
            case 4:
               v += -1.5F;
               h = 0.75F;
               break;
            case 5:
               v += -0.5F;
               h = -0.75F;
               break;
            case 6:
               v += -0.5F;
               h = 0.75F;
               break;
            case 7:
               v += 0.5F;
               h = -0.75F;
               break;
            case 8:
               v += 0.5F;
               h = 0.75F;
               break;
            default:
               v++;
               h = 0.0F;
         }
      }

      return new class_243(v, dimensions.comp_2186() - 0.1, h).method_1024(-this.method_36454() * (float) (Math.PI / 180.0) - (float) (Math.PI / 2));
   }

   @Override
   public Bannerable.BannerPosition getBannerPosition() {
      return new Bannerable.BannerPosition(-180.0F, 90.0F, -4.0, 0.15, 0.05);
   }

   @Override
   public void waterSplash() {
      class_243 vector3d = this.method_5828(0.0F);
      float f0 = class_3532.method_15362(this.method_36454() * (float) (Math.PI / 180.0)) * 1.2F;
      float f1 = class_3532.method_15374(this.method_36454() * (float) (Math.PI / 180.0)) * 1.2F;
      float f2 = 4.0F - this.field_5974.method_43057() * 0.7F;
      float f2_ = -2.3F - this.field_5974.method_43057() * 0.7F;
      float x = 0.0F;

      for (int i = 0; i < 2; i++) {
         this.method_37908()
            .method_8406(
               class_2398.field_11222,
               this.method_23317() - vector3d.field_1352 * f2 + f0,
               this.method_23318() - vector3d.field_1351 + 0.5,
               this.method_23321() - vector3d.field_1350 * f2 + f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11222,
               this.method_23317() - vector3d.field_1352 * f2 - f0,
               this.method_23318() - vector3d.field_1351 + 0.5,
               this.method_23321() - vector3d.field_1350 * f2 - f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11222,
               this.method_23317() - vector3d.field_1352 * f2 + f0,
               this.method_23318() - vector3d.field_1351 + 0.5,
               this.method_23321() - vector3d.field_1350 * f2 + f1 * 5.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11222,
               this.method_23317() - vector3d.field_1352 * f2 - f0,
               this.method_23318() - vector3d.field_1351 + 0.5,
               this.method_23321() - vector3d.field_1350 * f2 - f1 * 5.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2 + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * f2 + f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2 - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * f2 - f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2 + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * f2 + f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2 - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * f2 - f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2_ + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) + f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2_ - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) - f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2_ + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) + f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11202,
               this.method_23317() - vector3d.field_1352 * f2_ - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) - f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11247,
               this.method_23317() - vector3d.field_1352 * f2_ + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) + f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11247,
               this.method_23317() - vector3d.field_1352 * f2_ - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) - f1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11247,
               this.method_23317() - vector3d.field_1352 * f2_ + f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) + f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
         this.method_37908()
            .method_8406(
               class_2398.field_11247,
               this.method_23317() - vector3d.field_1352 * f2_ - f0,
               this.method_23318() - vector3d.field_1351 + 0.8,
               this.method_23321() - vector3d.field_1350 * (f2_ - x) - f1 * 1.1,
               0.0,
               0.0,
               0.0
            );
      }
   }

   @Override
   public Cannonable.CannonPosition getCannonPosition(int index) {
      List<Cannonable.CannonPosition> positionList = new ArrayList<>();
      Cannonable.CannonPosition pos1 = new Cannonable.CannonPosition(-1.2, 0.0, 0.6, true);
      Cannonable.CannonPosition pos2 = new Cannonable.CannonPosition(-1.2, 0.0, 0.6, false);
      positionList.add(pos1);
      positionList.add(pos2);
      return positionList.get(index);
   }

   @Override
   public byte getMaxCannonPerSide() {
      return 1;
   }
}
