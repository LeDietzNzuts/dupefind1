package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.OneKeyMinerKt
import cn.enaium.onekeyminer.screen.ToolSelectScreen
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import java.util.UUID
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_310
import net.minecraft.class_3222
import net.minecraft.class_746

public fun screenCommand(dispatcher: CommandDispatcher<class_2168>) {
   dispatcher.register(OneKeyMinerKt.getROOT().then(class_2170.method_9247("screen").executes(ScreenKt::screenCommand$lambda$1)) as LiteralArgumentBuilder);
}

fun `screenCommand$lambda$1$lambda$0`() {
   class_310.method_1551().method_1507(new ToolSelectScreen());
}

fun `screenCommand$lambda$1`(context: CommandContext): Int {
   if ((context.getSource() as class_2168).method_44023() != null && class_310.method_1551().field_1724 != null) {
      val var10000: class_3222 = (context.getSource() as class_2168).method_44023();
      val var1: UUID = var10000.method_5667();
      val var10001: class_746 = class_310.method_1551().field_1724;
      if (var1 == var10001.method_5667()) {
         class_310.method_1551().execute(ScreenKt::screenCommand$lambda$1$lambda$0);
      }
   }

   return 1;
}
