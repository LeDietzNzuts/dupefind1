package com.talhanation.smallships.world.item;

import com.talhanation.smallships.world.entity.ship.BriggEntity;
import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1937;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.NotNull;

public class BriggItem extends ShipItem {
   public BriggItem(class_1692 type, class_1793 properties) {
      super(type, properties);
   }

   @NotNull
   @Override
   protected Ship getShip(@NotNull class_1937 level, double x, double y, double z) {
      return BriggEntity.summon(level, x, y, z);
   }
}
