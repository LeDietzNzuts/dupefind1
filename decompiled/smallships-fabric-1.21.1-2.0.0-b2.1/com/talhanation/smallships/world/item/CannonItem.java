package com.talhanation.smallships.world.item;

import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2241;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2768;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_5712;
import net.minecraft.class_9279;
import net.minecraft.class_9334;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_5712.class_7397;

public class CannonItem extends class_1792 {
   public CannonItem(class_1793 properties) {
      super(properties);
   }

   public class_1269 method_7884(class_1838 useOnContext) {
      if (useOnContext.method_8036() == null) {
         return class_1269.field_5811;
      } else {
         class_1657 player = useOnContext.method_8036();
         class_1937 level = useOnContext.method_8045();
         class_2338 blockPos = useOnContext.method_8037();
         class_2680 blockState = level.method_8320(blockPos);
         if ((blockState.method_51367() || blockState.method_26164(class_3481.field_15463)) && useOnContext.method_8038() == class_2350.field_11036) {
            class_1799 itemStack = useOnContext.method_8041();
            class_243 pos = new class_243(blockPos.method_10263() + 0.5, blockPos.method_10264() + 1, blockPos.method_10260() + 0.5);
            if (blockState.method_26164(class_3481.field_15463)) {
               class_2768 railShape = blockState.method_26204() instanceof class_2241
                  ? (class_2768)blockState.method_11654(((class_2241)blockState.method_26204()).method_9474())
                  : class_2768.field_12665;
               double d = 0.0;
               if (railShape.method_11897()) {
                  d = 0.5;
               }

               pos = new class_243(pos.field_1352, pos.field_1351 - 1.0 + 0.0625 + d, pos.field_1350);
            }

            if (level instanceof class_3218 serverLevel) {
               GroundCannonEntity cannon = new GroundCannonEntity(level, pos);
               cannon.method_36456(player.method_36454());
               class_9279 data = (class_9279)itemStack.method_57824(class_9334.field_49628);
               if (data != null) {
                  data.method_57445(cannon);
               }

               serverLevel.method_8649(cannon);
               serverLevel.method_43276(
                  class_5712.field_28738, blockPos, class_7397.method_43286(useOnContext.method_8036(), serverLevel.method_8320(blockPos.method_10074()))
               );
            }

            itemStack.method_57008(1, player);
            return class_1269.method_29236(level.field_9236);
         } else {
            return class_1269.field_5814;
         }
      }
   }
}
