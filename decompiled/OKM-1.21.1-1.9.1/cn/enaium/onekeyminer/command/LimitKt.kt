package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.OneKeyMinerKt
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_2561

public fun limitCommand(dispatcher: CommandDispatcher<class_2168>) {
   dispatcher.register(
      OneKeyMinerKt.getROOT()
         .then(
            (class_2170.method_9247("limit").executes(LimitKt::limitCommand$lambda$1) as LiteralArgumentBuilder)
               .then(class_2170.method_9244("limit", IntegerArgumentType.integer() as ArgumentType).executes(LimitKt::limitCommand$lambda$3))
         ) as LiteralArgumentBuilder
   );
}

fun `limitCommand$lambda$1$lambda$0`(): class_2561 {
   return class_2561.method_43469("command.limit.get", new Object[]{Config.INSTANCE.getModel().getLimit()}) as class_2561;
}

fun `limitCommand$lambda$1`(context: CommandContext): Int {
   (context.getSource() as class_2168).method_9226(LimitKt::limitCommand$lambda$1$lambda$0, false);
   return 1;
}

fun `limitCommand$lambda$3$lambda$2`(`$limit`: Int): class_2561 {
   return class_2561.method_43469("command.limit.success", new Object[]{`$limit`}) as class_2561;
}

fun `limitCommand$lambda$3`(context: CommandContext): Int {
   val limit: Int = IntegerArgumentType.getInteger(context, "limit");
   if (limit <= 0) {
      (context.getSource() as class_2168).method_9213(class_2561.method_43471("command.limit.error") as class_2561);
      return 1;
   } else {
      Config.INSTANCE.getModel().setLimit(limit);
      (context.getSource() as class_2168).method_9226(LimitKt::limitCommand$lambda$3$lambda$2, false);
      return 1;
   }
}
