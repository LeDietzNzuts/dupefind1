package cn.enaium.onekeyminer

import cn.enaium.onekeyminer.command.ActionKt
import cn.enaium.onekeyminer.command.InteractKt
import cn.enaium.onekeyminer.command.LimitKt
import cn.enaium.onekeyminer.command.ListKt
import cn.enaium.onekeyminer.command.ReloadKt
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.class_2168
import net.minecraft.class_7157
import net.minecraft.class_2170.class_5364
import org.slf4j.Logger

public const val ID: String = "onekeyminer"
public final val LOGGER: Logger
public final val ROOT: LiteralArgumentBuilder<class_2168>

public fun initializer() {
   LOGGER.info("Hello OneKeyMiner world!");
   CommandRegistrationCallback.EVENT.register(OneKeyMinerKt::initializer$lambda$0);
   Config.INSTANCE.load();
   Runtime.getRuntime().addShutdownHook(new Thread(Config.INSTANCE::save));
}

fun `initializer$lambda$0`(dispatcher: CommandDispatcher, registryAccess: class_7157, environment: class_5364) {
   ActionKt.actionCommand(dispatcher, registryAccess);
   InteractKt.interactCommand(dispatcher);
   LimitKt.limitCommand(dispatcher);
   ListKt.listCommand(dispatcher);
   ReloadKt.reloadCommand(dispatcher);
}

fun `ROOT$lambda$1`(source: class_2168): Boolean {
   return source.method_9259(4);
}
