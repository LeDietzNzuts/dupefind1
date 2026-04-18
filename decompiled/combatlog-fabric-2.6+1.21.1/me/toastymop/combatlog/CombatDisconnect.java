package me.toastymop.combatlog;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.toastymop.combatlog.util.IEntityDataSaver;
import me.toastymop.combatlog.util.TagData;
import net.minecraft.class_1928;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_1928.class_4310;
import net.minecraft.server.MinecraftServer;

public class CombatDisconnect {
   public static void OnPlayerDisconnect(class_3222 entity) {
      if (TagData.getCombat((IEntityDataSaver)entity)) {
         class_3218 world = entity.method_51469();
         MinecraftServer server = world.method_8503();
         if (CombatConfig.Config.disconnectKill) {
            class_2561 deathMessage = class_2561.method_30163(entity.method_5476().getString() + CombatConfig.Config.deathMessage);
            class_4310 gamerule = (class_4310)server.method_3767().method_20746(class_1928.field_19398);
            if (gamerule.method_20753()) {
               gamerule.method_20758(false, server);
               entity.method_5643(entity.method_48923().method_48829(), 100000.0F);
               gamerule.method_20758(true, server);
               server.method_3760().method_43514(deathMessage, false);
            } else {
               entity.method_5643(entity.method_48923().method_48829(), 100000.0F);
            }
         }

         String disconnectCommand = CombatConfig.Config.disconnectCommand.replace("{player}", entity.method_5477().getString());
         if (disconnectCommand != null && !disconnectCommand.trim().isEmpty()) {
            class_2170 manager = server.method_3734();
            CommandDispatcher<class_2168> dispatcher = manager.method_9235();
            class_2168 commandSource = server.method_3739().method_9206(4);
            ParseResults<class_2168> parseResults = dispatcher.parse(disconnectCommand, commandSource);

            try {
               dispatcher.execute(parseResults);
            } catch (CommandSyntaxException var9) {
               commandSource.method_9213(class_2561.method_30163(var9.getMessage()));
            }
         }

         TagData.endCombat((IEntityDataSaver)entity);
      }
   }
}
