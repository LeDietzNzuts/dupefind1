package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.OneKeyMinerKt
import cn.enaium.onekeyminer.enums.Tool
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import kotlin.jvm.internal.Ref.ObjectRef
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_2248
import net.minecraft.class_2561
import net.minecraft.class_2568
import net.minecraft.class_2583
import net.minecraft.class_2960
import net.minecraft.class_5250
import net.minecraft.class_7923
import net.minecraft.class_2568.class_5247
import net.minecraft.class_2568.class_5249

public fun listCommand(dispatcher: CommandDispatcher<class_2168>) {
   for (Tool tool : Tool.getEntries()) {
      dispatcher.register(
         OneKeyMinerKt.getROOT().then(class_2170.method_9247(tool.name()).then(class_2170.method_9247("list").executes(ListKt::listCommand$lambda$2))) as LiteralArgumentBuilder
      );
   }
}

fun `listCommand$lambda$2$lambda$0`(`$list`: java.util.List, `$i`: Int, style: class_2583): class_2583 {
   return style.method_10949(
      new class_2568(
         class_5247.field_24343,
         new class_5249(
            (class_7923.field_41175.method_10223(class_2960.method_12829(`$list`.get(`$i`) as java.lang.String)) as class_2248).method_8389().method_7854()
         )
      )
   );
}

fun `listCommand$lambda$2$lambda$1`(`$previous`: ObjectRef): class_2561 {
   return `$previous`.element as class_2561;
}

fun `listCommand$lambda$2`(`$tool`: Tool, context: CommandContext): Int {
   var var10000: java.util.List;
   switch (ListKt.WhenMappings.$EnumSwitchMapping$0[$tool.ordinal()]) {
      case 1:
         var10000 = Config.INSTANCE.getModel().getAxe();
         break;
      case 2:
         var10000 = Config.INSTANCE.getModel().getHoe();
         break;
      case 3:
         var10000 = Config.INSTANCE.getModel().getPickaxe();
         break;
      case 4:
         var10000 = Config.INSTANCE.getModel().getShovel();
         break;
      case 5:
         var10000 = Config.INSTANCE.getModel().getShears();
         break;
      case 6:
         var10000 = Config.INSTANCE.getModel().getAny();
         break;
      default:
         throw new NoWhenBranchMatchedException();
   }

   val list: java.util.List = var10000;
   val previous: ObjectRef = new ObjectRef();
   var i: Int = 0;

   for (int var5 = var10000.size(); i < var5; i++) {
      val item: class_5250 = class_2561.method_43470(list.get(i) as java.lang.String).method_27694(ListKt::listCommand$lambda$2$lambda$0);
      if (previous.element == null) {
         previous.element = item;
      } else {
         (previous.element as class_5250).method_10852(class_2561.method_43470(", ") as class_2561);
         (previous.element as class_5250).method_10852(item as class_2561);
      }
   }

   if (previous.element != null) {
      (context.getSource() as class_2168).method_9226(ListKt::listCommand$lambda$2$lambda$1, false);
   }

   return 1;
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[Tool.values().length];

      try {
         var0[Tool.AXE.ordinal()] = 1;
      } catch (var7: NoSuchFieldError) {
      }

      try {
         var0[Tool.HOE.ordinal()] = 2;
      } catch (var6: NoSuchFieldError) {
      }

      try {
         var0[Tool.PICKAXE.ordinal()] = 3;
      } catch (var5: NoSuchFieldError) {
      }

      try {
         var0[Tool.SHOVEL.ordinal()] = 4;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[Tool.SHEARS.ordinal()] = 5;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[Tool.ANY.ordinal()] = 6;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
