package net.p3pp3rf1y.sophisticatedbackpacks.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2319;
import net.minecraft.class_7157;
import net.minecraft.class_2170.class_5364;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;

public class SBPCommand {
   private static final int OP_LEVEL = 2;

   private SBPCommand() {
   }

   public static void init() {
      ArgumentTypeRegistry.registerArgumentType(
         SophisticatedBackpacks.getRL("backpack_uuid"), BackpackUUIDArgumentType.class, class_2319.method_41999(BackpackUUIDArgumentType::backpackUuid)
      );
      ArgumentTypeRegistry.registerArgumentType(
         SophisticatedBackpacks.getRL("player_name"), BackpackPlayerArgumentType.class, class_2319.method_41999(BackpackPlayerArgumentType::playerName)
      );
      CommandRegistrationCallback.EVENT.register(SBPCommand::registerCommands);
   }

   private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 buildContext, class_5364 selection) {
      LiteralCommandNode<class_2168> mainNode = dispatcher.register(
         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("sbp")
                     .requires(cs -> cs.method_9259(2)))
                  .then(ListCommand.register()))
               .then(GiveCommand.register()))
            .then(RemoveNonPlayerCommand.register())
      );
      dispatcher.register(
         (LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247("sophisticatedbackpacks").requires(cs -> cs.method_9259(2)))
            .redirect(mainNode)
      );
   }
}
