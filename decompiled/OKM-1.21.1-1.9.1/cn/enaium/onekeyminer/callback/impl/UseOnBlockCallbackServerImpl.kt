package cn.enaium.onekeyminer.callback.impl

import net.minecraft.class_3222

public class UseOnBlockCallbackServerImpl : UseOnBlockCallbackImpl {
   public override fun condition(player: class_3222): Boolean {
      return player.method_5715();
   }
}
