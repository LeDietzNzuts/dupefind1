package com.talhanation.smallships.world.item;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1749;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_3218;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.NotNull;

public abstract class ShipItem extends class_1749 {
   public ShipItem(class_1692 type, class_1793 properties) {
      super(false, type, properties);
   }

   @NotNull
   protected abstract Ship getShip(@NotNull class_1937 var1, double var2, double var4, double var6);

   @NotNull
   protected class_1690 method_42296(@NotNull class_1937 level, @NotNull class_239 hitResult, class_1799 stack, class_1657 player) {
      Ship ship = this.getShip(level, hitResult.method_17784().field_1352, hitResult.method_17784().field_1351, hitResult.method_17784().field_1350);
      if (level instanceof class_3218 serverLevel) {
         class_1299.method_48009(serverLevel, stack, player).accept(ship);
      }

      return ship;
   }
}
