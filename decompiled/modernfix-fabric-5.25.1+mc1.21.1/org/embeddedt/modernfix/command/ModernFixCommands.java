package org.embeddedt.modernfix.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import org.embeddedt.modernfix.duck.IProfilingServerFunctionManager;

public class ModernFixCommands {
   public static void register(CommandDispatcher<class_2168> dispatcher) {
      dispatcher.register(
         (LiteralArgumentBuilder)class_2170.method_9247("modernfix")
            .then(((LiteralArgumentBuilder)class_2170.method_9247("mcfunctions").requires(source -> source.method_9259(3))).executes(context -> {
               class_3218 level = ((class_2168)context.getSource()).method_9225();
               if (level == null) {
                  ((class_2168)context.getSource()).method_9213(class_2561.method_43470("Couldn't find server level"));
                  return 0;
               } else if (!(level.method_8503().method_3740() instanceof IProfilingServerFunctionManager profiler)) {
                  ((class_2168)context.getSource()).method_9213(class_2561.method_43470("ModernFix mcfunction profiling is not enabled on this server."));
                  return 0;
               } else {
                  ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470("mcfunction runtime breakdown:"), false);

                  for (String line : profiler.mfix$getProfilingResults().split("\n")) {
                     ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470(line), false);
                  }

                  return 1;
               }
            }))
      );
   }
}
