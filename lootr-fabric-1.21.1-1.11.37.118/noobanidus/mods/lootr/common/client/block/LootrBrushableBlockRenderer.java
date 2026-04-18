package noobanidus.mods.lootr.common.client.block;

import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2741;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_761;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_918;
import net.minecraft.class_5614.class_5615;
import noobanidus.mods.lootr.common.block.entity.LootrBrushableBlockEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;

public class LootrBrushableBlockRenderer implements class_827<LootrBrushableBlockEntity> {
   private final class_918 itemRenderer;

   public LootrBrushableBlockRenderer(class_5615 arg) {
      this.itemRenderer = arg.method_43335();
   }

   public void render(LootrBrushableBlockEntity arg, float f, class_4587 arg2, class_4597 arg3, int k, int l) {
      if (arg.method_10997() != null) {
         class_1657 player = ClientHooks.getPlayer();
         if (player == null) {
            return;
         }

         if (arg.hasClientOpened(player)) {
            return;
         }

         if (!arg.isBrushingPlayer(player)) {
            return;
         }

         int i = (Integer)arg.method_11010().method_11654(class_2741.field_42836);
         if (i > 0) {
            class_2350 direction = arg.getHitDirection();
            if (direction != null) {
               class_1799 itemstack = arg.getItem();
               if (!itemstack.method_7960()) {
                  arg2.method_22903();
                  arg2.method_46416(0.0F, 0.5F, 0.0F);
                  float[] afloat = this.translations(direction, i);
                  arg2.method_46416(afloat[0], afloat[1], afloat[2]);
                  arg2.method_22907(class_7833.field_40716.rotationDegrees(75.0F));
                  boolean flag = direction == class_2350.field_11034 || direction == class_2350.field_11039;
                  arg2.method_22907(class_7833.field_40716.rotationDegrees((flag ? 90 : 0) + 11));
                  arg2.method_22905(0.5F, 0.5F, 0.5F);
                  int j = class_761.method_23793(arg.method_10997(), arg.method_11010(), arg.method_11016().method_10093(direction));
                  this.itemRenderer.method_23178(itemstack, class_811.field_4319, j, class_4608.field_21444, arg2, arg3, arg.method_10997(), 0);
                  arg2.method_22909();
               }
            }
         }
      }
   }

   private float[] translations(class_2350 arg, int i) {
      float[] afloat = new float[]{0.5F, 0.0F, 0.5F};
      float f = i / 10.0F * 0.75F;
      switch (arg) {
         case field_11034:
            afloat[0] = 0.73F + f;
            break;
         case field_11039:
            afloat[0] = 0.25F - f;
            break;
         case field_11036:
            afloat[1] = 0.25F + f;
            break;
         case field_11033:
            afloat[1] = -0.23F - f;
            break;
         case field_11043:
            afloat[2] = 0.25F - f;
            break;
         case field_11035:
            afloat[2] = 0.73F + f;
      }

      return afloat;
   }

   public class_238 getRenderBoundingBox(LootrBrushableBlockEntity blockEntity) {
      class_2338 pos = blockEntity.method_11016();
      return new class_238(
         pos.method_10263() - 0.25,
         pos.method_10264() - 0.25,
         pos.method_10260() - 0.25,
         pos.method_10263() + 1.25,
         pos.method_10264() + 1.25,
         pos.method_10260() + 1.25
      );
   }
}
