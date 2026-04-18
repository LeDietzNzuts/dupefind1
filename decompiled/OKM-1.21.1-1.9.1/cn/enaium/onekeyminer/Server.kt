package cn.enaium.onekeyminer

import cn.enaium.onekeyminer.callback.FinishMiningCallback
import cn.enaium.onekeyminer.callback.UseOnBlockCallback
import cn.enaium.onekeyminer.callback.impl.FinishMiningCallbackServerImpl
import cn.enaium.onekeyminer.callback.impl.UseOnBlockCallbackServerImpl

public object Server {
   @JvmStatic
   public fun server() {
      FinishMiningCallback.Companion.getEVENT().register(new FinishMiningCallbackServerImpl());
      UseOnBlockCallback.Companion.getEVENT().register(new UseOnBlockCallbackServerImpl());
   }
}
