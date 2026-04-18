package cn.enaium.onekeyminer

import cn.enaium.onekeyminer.callback.FinishMiningCallback
import cn.enaium.onekeyminer.callback.UseOnBlockCallback
import cn.enaium.onekeyminer.callback.impl.FinishMiningCallbackClientImpl
import cn.enaium.onekeyminer.callback.impl.UseOnBlockCallbackClientImpl
import cn.enaium.onekeyminer.command.ScreenKt
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.class_304
import net.minecraft.class_7157
import net.minecraft.class_2170.class_5364
import net.minecraft.class_3675.class_307

public object Client {
   public final var active: class_304?

   @JvmStatic
   public fun client() {
      active = KeyBindingHelper.registerKeyBinding(new class_304("key.onekeyminer.active", class_307.field_1668, 96, "category.onekeyminer.title"));
      FinishMiningCallback.Companion.getEVENT().register(new FinishMiningCallbackClientImpl());
      UseOnBlockCallback.Companion.getEVENT().register(new UseOnBlockCallbackClientImpl());
      CommandRegistrationCallback.EVENT.register(Client::client$lambda$0);
   }

   @JvmStatic
   fun `client$lambda$0`(dispatcher: CommandDispatcher, registryAccess: class_7157, environment: class_5364) {
      ScreenKt.screenCommand(dispatcher);
   }
}
