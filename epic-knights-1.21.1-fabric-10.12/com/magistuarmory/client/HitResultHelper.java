package com.magistuarmory.client;

import java.util.Objects;
import net.minecraft.class_1657;
import net.minecraft.class_1675;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class HitResultHelper {
   public static class_239 getMouseOver(class_310 mc, float reach) {
      class_239 result = null;
      class_1657 player = mc.field_1724;
      if (player != null) {
         result = player.method_5745(reach, 0.0F, false);
         class_243 eyepos = player.method_33571();
         double d1 = result.method_17784().method_1025(eyepos);
         class_243 view = player.method_5828(1.0F);
         class_243 attackvec = eyepos.method_1031(view.field_1352 * reach, view.field_1351 * reach, view.field_1350 * reach);
         class_238 expBounds = player.method_5829().method_18804(view.method_1021(reach)).method_1009(1.0, 1.0, 1.0);
         class_3966 entityhitresult = class_1675.method_18075(player, eyepos, attackvec, expBounds, entity -> !entity.method_7325() && entity.method_5863(), d1);
         result = Objects.requireNonNullElseGet(
            entityhitresult,
            () -> class_3965.method_17778(
               attackvec,
               class_2350.method_10142(view.field_1352, view.field_1351, view.field_1350),
               new class_2338((int)attackvec.field_1352, (int)attackvec.field_1351, (int)attackvec.field_1350)
            )
         );
      }

      return result;
   }
}
