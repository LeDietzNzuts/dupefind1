package cn.enaium.onekeyminer.callback.impl

import cn.enaium.onekeyminer.Client
import net.minecraft.class_304
import net.minecraft.class_310
import net.minecraft.class_3222

public class UseOnBlockCallbackClientImpl : UseOnBlockCallbackImpl {
   public override fun condition(player: class_3222): Boolean {
      val var10000: Boolean;
      if (class_310.method_1551().field_1724 == player && Client.INSTANCE.getActive() != null) {
         val var2: class_304 = Client.INSTANCE.getActive();
         var10000 = var2.method_1434();
      } else {
         var10000 = player.method_5715();
      }

      return var10000;
   }
}
