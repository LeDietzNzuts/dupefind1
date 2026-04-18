package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.OneKeyMinerKt
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_2561

public fun reloadCommand(dispatcher: CommandDispatcher<class_2168>) {
   dispatcher.register(OneKeyMinerKt.getROOT().then(class_2170.method_9247("reload").executes(ReloadKt::reloadCommand$lambda$1)) as LiteralArgumentBuilder);
}

fun `reloadCommand$lambda$1$lambda$0`(): class_2561 {
   return class_2561.method_43471("command.reload.success") as class_2561;
}

fun `reloadCommand$lambda$1`(context: CommandContext): Int {
   Config.INSTANCE.load();
   (context.getSource() as class_2168).method_9226(ReloadKt::reloadCommand$lambda$1$lambda$0, false);
   return 1;
}
