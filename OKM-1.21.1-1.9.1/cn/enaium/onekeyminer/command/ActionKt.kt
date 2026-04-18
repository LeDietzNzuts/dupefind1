package cn.enaium.onekeyminer.command

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.OneKeyMinerKt
import cn.enaium.onekeyminer.enums.Action
import cn.enaium.onekeyminer.enums.Tool
import cn.enaium.onekeyminer.util.BlockKt
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import kotlin.jvm.functions.Function1
import net.minecraft.class_124
import net.minecraft.class_1799
import net.minecraft.class_2168
import net.minecraft.class_2170
import net.minecraft.class_2247
import net.minecraft.class_2257
import net.minecraft.class_2561
import net.minecraft.class_2568
import net.minecraft.class_2583
import net.minecraft.class_2960
import net.minecraft.class_7157
import net.minecraft.class_2568.class_5247
import net.minecraft.class_2568.class_5249

public fun actionCommand(dispatcher: CommandDispatcher<class_2168>, registryAccess: class_7157) {
   for (Tool tool : Tool.getEntries()) {
      for (Action action : Action.getEntries()) {
         dispatcher.register(
            OneKeyMinerKt.getROOT()
               .then(
                  class_2170.method_9247(tool.name())
                     .then(
                        class_2170.method_9247(action.name())
                           .then(
                              class_2170.method_9244("block", class_2257.method_9653(registryAccess) as ArgumentType)
                                 .executes(ActionKt::actionCommand$lambda$16)
                           )
                     )
               ) as LiteralArgumentBuilder
         );
      }
   }
}

fun `actionCommand$lambda$16$lambda$1$lambda$0`(`$itemStack`: class_1799, style: class_2583): class_2583 {
   return style.method_10977(class_124.field_1075).method_10949(new class_2568(class_5247.field_24343, new class_5249(`$itemStack`)));
}

fun `actionCommand$lambda$16$lambda$1`(`$blockName`: java.lang.String, `$itemStack`: class_1799): class_2561 {
   return class_2561.method_43469(
      "command.action.add.success", new Object[]{class_2561.method_43470(`$blockName`).method_27694(ActionKt::actionCommand$lambda$16$lambda$1$lambda$0)}
   ) as class_2561;
}

fun `actionCommand$lambda$16$lambda$2`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$3`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$4`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$5`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$6`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$7`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$8`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$9`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$10`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$11`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$12`(`$blockName`: java.lang.String, s: java.lang.String): Boolean {
   return s == `$blockName`;
}

fun `actionCommand$lambda$16$lambda$13`(`$tmp0`: Function1, p0: Any): Boolean {
   return `$tmp0`.invoke(p0) as java.lang.Boolean;
}

fun `actionCommand$lambda$16$lambda$15$lambda$14`(`$itemStack`: class_1799, style: class_2583): class_2583 {
   return style.method_10977(class_124.field_1075).method_10949(new class_2568(class_5247.field_24343, new class_5249(`$itemStack`)));
}

fun `actionCommand$lambda$16$lambda$15`(`$blockName`: java.lang.String, `$itemStack`: class_1799): class_2561 {
   return class_2561.method_43469(
      "command.action.remove.success", new Object[]{class_2561.method_43470(`$blockName`).method_27694(ActionKt::actionCommand$lambda$16$lambda$15$lambda$14)}
   ) as class_2561;
}

fun `actionCommand$lambda$16`(`$action`: Action, `$tool`: Tool, context: CommandContext): Int {
   val block: class_2247 = context.getArgument("block", class_2247.class) as class_2247;
   val itemStack: class_1799 = block.method_9494().method_26204().method_8389().method_7854();
   val var10000: class_2960 = block.method_9494().method_26204().method_26162().method_29177();
   val blockName: java.lang.String = BlockKt.getName(var10000);
   switch (ActionKt.WhenMappings.$EnumSwitchMapping$1[$action.ordinal()]) {
      case 1:
         switch (ActionKt.WhenMappings.$EnumSwitchMapping$0[$tool.ordinal()]) {
            case 1:
               Config.INSTANCE.getModel().getAxe().add(blockName);
               break;
            case 2:
               Config.INSTANCE.getModel().getHoe().add(blockName);
               break;
            case 3:
               Config.INSTANCE.getModel().getPickaxe().add(blockName);
               break;
            case 4:
               Config.INSTANCE.getModel().getShovel().add(blockName);
               break;
            case 5:
               Config.INSTANCE.getModel().getShears().add(blockName);
               break;
            case 6:
               Config.INSTANCE.getModel().getAny().add(blockName);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         (context.getSource() as class_2168).method_9226(ActionKt::actionCommand$lambda$16$lambda$1, false);
         break;
      case 2:
         switch (ActionKt.WhenMappings.$EnumSwitchMapping$0[$tool.ordinal()]) {
            case 1:
               Config.INSTANCE.getModel().getAxe().removeIf(ActionKt::actionCommand$lambda$16$lambda$3);
               break;
            case 2:
               Config.INSTANCE.getModel().getHoe().removeIf(ActionKt::actionCommand$lambda$16$lambda$5);
               break;
            case 3:
               Config.INSTANCE.getModel().getPickaxe().removeIf(ActionKt::actionCommand$lambda$16$lambda$7);
               break;
            case 4:
               Config.INSTANCE.getModel().getShovel().removeIf(ActionKt::actionCommand$lambda$16$lambda$9);
               break;
            case 5:
               Config.INSTANCE.getModel().getShears().removeIf(ActionKt::actionCommand$lambda$16$lambda$11);
               break;
            case 6:
               Config.INSTANCE.getModel().getAny().removeIf(ActionKt::actionCommand$lambda$16$lambda$13);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         (context.getSource() as class_2168).method_9226(ActionKt::actionCommand$lambda$16$lambda$15, false);
         break;
      default:
         throw new NoWhenBranchMatchedException();
   }

   Config.INSTANCE.save();
   return 1;
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      var var0: IntArray = new int[Tool.values().length];

      try {
         var0[Tool.AXE.ordinal()] = 1;
      } catch (var9: NoSuchFieldError) {
      }

      try {
         var0[Tool.HOE.ordinal()] = 2;
      } catch (var8: NoSuchFieldError) {
      }

      try {
         var0[Tool.PICKAXE.ordinal()] = 3;
      } catch (var7: NoSuchFieldError) {
      }

      try {
         var0[Tool.SHOVEL.ordinal()] = 4;
      } catch (var6: NoSuchFieldError) {
      }

      try {
         var0[Tool.SHEARS.ordinal()] = 5;
      } catch (var5: NoSuchFieldError) {
      }

      try {
         var0[Tool.ANY.ordinal()] = 6;
      } catch (var4: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
      var0 = new int[Action.values().length];

      try {
         var0[Action.ADD.ordinal()] = 1;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[Action.REMOVE.ordinal()] = 2;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$1 = var0;
   }
}
