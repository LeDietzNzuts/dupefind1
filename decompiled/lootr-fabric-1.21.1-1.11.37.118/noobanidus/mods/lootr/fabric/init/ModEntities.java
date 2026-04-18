package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_2378;
import net.minecraft.class_7923;
import net.minecraft.class_1299.class_1300;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import noobanidus.mods.lootr.common.entity.LootrItemFrame;

public class ModEntities {
   public static class_1299<LootrChestMinecartEntity> LOOTR_MINECART_ENTITY;
   public static class_1299<LootrItemFrame> ITEM_FRAME;

   public static void registerEntities() {
      LOOTR_MINECART_ENTITY = (class_1299<LootrChestMinecartEntity>)class_2378.method_10230(
         class_7923.field_41177,
         LootrConstants.LOOTR_CART,
         class_1300.method_5903(LootrChestMinecartEntity::new, class_1311.field_17715).method_17687(0.9F, 1.4F).method_27299(8).build()
      );
      ITEM_FRAME = (class_1299<LootrItemFrame>)class_2378.method_10230(
         class_7923.field_41177,
         LootrConstants.ITEM_FRAME,
         class_1300.method_5903(LootrItemFrame::new, class_1311.field_17715)
            .method_17687(0.5F, 0.5F)
            .method_55687(0.0F)
            .method_27299(10)
            .method_27300(Integer.MAX_VALUE)
            .build()
      );
   }
}
