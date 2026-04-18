package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.OneKeyMinerKt
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_2561

public fun interactCommand(dispatcher: CommandDispatcher<class_2168>) {
   dispatcher.register(
      OneKeyMinerKt.getROOT()
         .then(
            (class_2170.method_9247("interact").executes(InteractKt::interactCommand$lambda$1) as LiteralArgumentBuilder)
               .then(class_2170.method_9244("interact", BoolArgumentType.bool() as ArgumentType).executes(InteractKt::interactCommand$lambda$3))
         ) as LiteralArgumentBuilder
   );
}

fun `interactCommand$lambda$1$lambda$0`(): class_2561 {
   return class_2561.method_43469("command.interact.get", new Object[]{Config.INSTANCE.getModel().getInteract()}) as class_2561;
}

fun `interactCommand$lambda$1`(context: CommandContext): Int {
   (context.getSource() as class_2168).method_9226(InteractKt::interactCommand$lambda$1$lambda$0, false);
   return 1;
}

fun `interactCommand$lambda$3$lambda$2`(): class_2561 {
   return class_2561.method_43469("command.interact.success", new Object[]{Config.INSTANCE.getModel().getInteract()}) as class_2561;
}

fun `interactCommand$lambda$3`(context: CommandContext): Int {
   Config.INSTANCE.getModel().setInteract(BoolArgumentType.getBool(context, "interact"));
   (context.getSource() as class_2168).method_9226(InteractKt::interactCommand$lambda$3$lambda$2, false);
   return 1;
}
