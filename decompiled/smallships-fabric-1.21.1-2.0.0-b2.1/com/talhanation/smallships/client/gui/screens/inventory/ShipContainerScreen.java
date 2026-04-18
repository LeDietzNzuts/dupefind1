package com.talhanation.smallships.client.gui.screens.inventory;

import com.talhanation.smallships.SmallShipsMod;
import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.math.Kalkuel;
import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.Shieldable;
import com.talhanation.smallships.world.inventory.ShipContainerMenu;
import net.minecraft.class_1661;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4185;
import net.minecraft.class_465;
import net.minecraft.class_4185.class_7840;

public class ShipContainerScreen extends class_465<ShipContainerMenu> {
   private static final class_2960 RESOURCE_LOCATION = class_2960.method_60655("smallships", "textures/gui/ship_inventory.png");
   public static final int FONT_COLOR = 4210752;
   private final int rowCount;
   private final int pageCount;
   private final int pageIndex;
   private final ContainerShip containerShip;
   private final int offset = 40;
   private int origLeftPos;
   private int origTopPos;

   public ShipContainerScreen(ShipContainerMenu shipContainerMenu, class_1661 inventory, class_2561 component) {
      super(shipContainerMenu, inventory, component);
      this.field_2779 = 114 + ((ShipContainerMenu)this.method_17577()).getRowCount() * 18;
      this.field_2792 = 256;
      this.field_25270 = this.field_2779 - 94;
      this.containerShip = shipContainerMenu.getContainerShip();
      this.rowCount = ((ShipContainerMenu)this.method_17577()).getRowCount();
      this.pageCount = ((ShipContainerMenu)this.method_17577()).getPageCount();
      this.pageIndex = ((ShipContainerMenu)this.method_17577()).getPageIndex();
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTick) {
      this.method_25420(guiGraphics, mouseX, mouseY, partialTick);
      super.method_25394(guiGraphics, mouseX, mouseY, partialTick);
      this.method_2380(guiGraphics, mouseX, mouseY);
   }

   protected void method_2389(class_332 guiGraphics, float f, int i, int j) {
      int k = 40 + (this.field_22789 - this.field_2792) / 2;
      int l = (this.field_22790 - this.field_2779) / 2;
      guiGraphics.method_25302(RESOURCE_LOCATION, k, l, 0, 0, this.field_2792, this.rowCount * 18 + 17);
      guiGraphics.method_25302(RESOURCE_LOCATION, k, l + this.rowCount * 18 + 17, 0, 126, this.field_2792, 96);
   }

   protected void method_25426() {
      this.origLeftPos = this.field_2776;
      this.origTopPos = this.field_2800;
      this.field_2776 = 40 + (this.field_22789 - this.field_2792) / 2;
      this.field_2800 = (this.field_22790 - this.field_2779) / 2;
      if (this.field_22787 != null && this.field_22787.field_1724 != null) {
         class_4185 backward = (class_4185)this.method_37063(
            new class_7840(class_2561.method_43470("<"), button -> ((ShipContainerMenu)this.method_17577()).method_7604(this.field_22787.field_1724, -1))
               .method_46433(this.field_2776 + 115, this.field_2800 + 125)
               .method_46437(12, 12)
               .method_46431()
         );
         backward.field_22763 = this.pageCount > 1 && this.pageIndex + 1 > 1;
         class_4185 forward = (class_4185)this.method_37063(
            new class_7840(class_2561.method_43470(">"), button -> ((ShipContainerMenu)this.method_17577()).method_7604(this.field_22787.field_1724, 1))
               .method_46433(this.field_2776 + 157, this.field_2800 + 125)
               .method_46437(12, 12)
               .method_46431()
         );
         forward.field_22763 = this.pageCount > 1 && this.pageIndex + 1 < this.pageCount;
      } else {
         SmallShipsMod.LOGGER.error("Minecraft client or LocalPlayer is null?! Couldn't render page buttons.");
      }
   }

   protected void method_2388(class_332 guiGraphics, int i, int j) {
      super.method_2388(guiGraphics, i, j);
      String name = this.containerShip.method_5476().getString();
      String smallShipTypeRaw = this.containerShip.method_5864().method_5897().getString();
      String smallShipType = smallShipTypeRaw.substring(0, 1).toUpperCase() + smallShipTypeRaw.substring(1);
      int currentPassengers = this.containerShip.method_5685().size();
      int maxPassengers = this.containerShip.method_42281();
      int maxAttachment = 0;
      int currentAttachment = 0;
      if (this.containerShip instanceof Cannonable cannonable) {
         maxAttachment = cannonable.getMaxCannonPerSide() * 2;
         currentAttachment = cannonable.getCannonCount();
      } else if (this.containerShip instanceof Shieldable shieldable) {
         maxAttachment = shieldable.getMaxShieldsPerSide() * 2;
         currentAttachment = shieldable.getShields().size();
      }

      int dmg = (int)(this.containerShip.method_54294() * 100.0F / this.containerShip.getAttributes().maxHealth);
      int currentSpeed;
      String unit;
      int maxSpeed;
      switch (SmallShipsConfig.Client.shipModSpeedUnit.get()) {
         case 1:
            unit = "m/s";
            maxSpeed = class_3532.method_15386(Kalkuel.getMeterPerSecond(this.containerShip.maxSpeed));
            currentSpeed = class_3532.method_15386(Kalkuel.getMeterPerSecond(this.containerShip.getSpeed()));
            break;
         case 2:
            unit = "knots";
            maxSpeed = class_3532.method_15386(Kalkuel.getKnots(this.containerShip.maxSpeed));
            currentSpeed = class_3532.method_15386(Kalkuel.getKnots(this.containerShip.getSpeed()));
            break;
         case 3:
            unit = "mph";
            maxSpeed = class_3532.method_15386(Kalkuel.getMilesPerHour(this.containerShip.maxSpeed));
            currentSpeed = class_3532.method_15386(Kalkuel.getMilesPerHour(this.containerShip.getSpeed()));
            break;
         default:
            unit = "km/h";
            maxSpeed = class_3532.method_15386(Kalkuel.getKilometerPerHour(this.containerShip.maxSpeed));
            currentSpeed = class_3532.method_15386(Kalkuel.getKilometerPerHour(this.containerShip.getSpeed()));
      }

      int leftPos = 260;
      int leftPos2 = 323;
      int topPos = 38;
      int gap = 14;
      guiGraphics.method_51448().method_22903();
      guiGraphics.method_51448().method_22905(0.7F, 0.7F, 1.0F);
      String attachment = this.containerShip instanceof Shieldable ? "Shields:" : "Cannons:";
      guiGraphics.method_51433(this.field_22793, "Name:", leftPos, topPos + gap * 0, 4210752, false);
      guiGraphics.method_51433(this.field_22793, "Type:", leftPos, topPos + gap * 1, 4210752, false);
      guiGraphics.method_51433(this.field_22793, "Crew:", leftPos, topPos + gap * 2, 4210752, false);
      guiGraphics.method_51433(this.field_22793, "Speed " + unit + ":", leftPos, topPos + gap * 3, 4210752, false);
      guiGraphics.method_51433(this.field_22793, "Damage:", leftPos, topPos + gap * 4, 4210752, false);
      guiGraphics.method_51433(this.field_22793, attachment, leftPos, topPos + gap * 5, 4210752, false);
      guiGraphics.method_51433(this.field_22793, name, leftPos2, topPos + gap * 0, 4210752, false);
      guiGraphics.method_51433(this.field_22793, smallShipType, leftPos2, topPos + gap * 1, 4210752, false);
      guiGraphics.method_51433(this.field_22793, currentPassengers + "/" + maxPassengers, leftPos2, topPos + gap * 2, 4210752, false);
      guiGraphics.method_51433(this.field_22793, currentSpeed + "/" + maxSpeed, leftPos2, topPos + gap * 3, 4210752, false);
      guiGraphics.method_51433(this.field_22793, dmg + "%", leftPos2, topPos + gap * 4, 4210752, false);
      guiGraphics.method_51433(this.field_22793, currentAttachment + "/" + maxAttachment, leftPos2, topPos + gap * 5, 4210752, false);
      int xOffset = this.origLeftPos + (int)(133.0F - class_3532.method_15357(Math.log10(this.pageCount)) * 6.0F);
      int yOffset = this.origTopPos + this.rowCount * 18;
      if (this.pageCount > 1) {
         guiGraphics.method_51433(this.field_22793, this.pageIndex + 1 + "/" + this.pageCount, xOffset, yOffset, 4210752, false);
      }

      guiGraphics.method_51448().method_22909();
   }
}
