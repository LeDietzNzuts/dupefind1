package me.toastymop.combatlog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.parsers.json.JsonReader;
import org.quiltmc.parsers.json.JsonWriter;

public class CombatConfig {
   public static CombatConfig.Config CONFIG;
   static File configFolder = new File("./config");
   static File configFile = new File(configFolder + "/combatlog-common.json5");
   protected static final Logger log = LogManager.getLogger("CombatLog");

   public static CombatConfig.Config load() {
      if (!configFolder.exists()) {
         configFolder.mkdirs();
      }

      if (!configFile.getName().endsWith(".json5")) {
         throw new RuntimeException("Failed to read config");
      } else {
         CombatConfig.Config cfg = null;
         if (configFile.exists()) {
            try {
               JsonReader reader = JsonReader.json5(configFile.toPath());

               CombatConfig.Config var8;
               try {
                  cfg = new CombatConfig.Config();
                  reader.beginObject();

                  while (reader.hasNext()) {
                     String nextName = reader.nextName();
                     switch (nextName) {
                        case "combatTime":
                           CombatConfig.Config.combatTime = reader.nextInt();
                           break;
                        case "allDamage":
                           CombatConfig.Config.allDamage = reader.nextBoolean();
                           break;
                        case "mobDamage":
                           CombatConfig.Config.mobDamage = reader.nextBoolean();
                           break;
                        case "disableElytra":
                           CombatConfig.Config.disableElytra = reader.nextBoolean();
                           break;
                        case "disablePearl":
                           CombatConfig.Config.disablePearl = reader.nextBoolean();
                           break;
                        case "deathMessage":
                           CombatConfig.Config.deathMessage = reader.nextString();
                           break;
                        case "combatNotice":
                           CombatConfig.Config.combatNotice = reader.nextBoolean();
                           break;
                        case "inCombat":
                           CombatConfig.Config.inCombat = reader.nextString();
                           break;
                        case "outCombat":
                           CombatConfig.Config.outCombat = reader.nextString();
                           break;
                        case "blockedCommands":
                           CombatConfig.Config.blockedCommands = Arrays.asList(reader.nextString().split(","));
                           break;
                        case "blockedCommandMessage":
                           CombatConfig.Config.blockedCommandMessage = reader.nextString();
                           break;
                        case "disconnectKill":
                           CombatConfig.Config.disconnectKill = reader.nextBoolean();
                           break;
                        case "disconnectCommand":
                           CombatConfig.Config.disconnectCommand = reader.nextString();
                           break;
                        default:
                           reader.skipValue();
                     }
                  }

                  reader.endObject();
                  var8 = cfg;
               } catch (Throwable var6) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                     }
                  }

                  throw var6;
               }

               if (reader != null) {
                  reader.close();
               }

               return var8;
            } catch (IOException var7) {
               log.error("Failed to parse config", var7);
            }
         }

         if (cfg == null) {
            cfg = new CombatConfig.Config();
         }

         save(configFile, cfg);
         return cfg;
      }
   }

   public static void save(File file, CombatConfig.Config cfg) {
      try {
         JsonWriter writer = JsonWriter.json5(file.toPath());

         try {
            writer.beginObject();
            writer.comment("The amount of time in seconds a player should be in combat").name("combatTime").value(CombatConfig.Config.combatTime);
            writer.comment("Weather a player should be put in combat from just other players or all damage")
               .name("allDamage")
               .value(CombatConfig.Config.allDamage);
            writer.comment("Weather a player should be put in combat from mobs").name("mobDamage").value(CombatConfig.Config.mobDamage);
            writer.comment(
                  "Weather a player should be able to use their elytra while in combat, this will not make them drop from the sky it simply restricts starting elytra flight"
               )
               .name("disableElytra")
               .value(CombatConfig.Config.disableElytra);
            writer.comment("Weather a player should be able to use ender pearls while in combat").name("disablePearl").value(CombatConfig.Config.disablePearl);
            writer.comment(
                  "The death message that shows when a player disconnects while in combat, note that not having a space at the beginning will attach the message to the players name"
               )
               .name("deathMessage")
               .value(CombatConfig.Config.deathMessage);
            writer.comment("Weather a player should get a popup when they enter combat or when trying to run blocked commands")
               .name("combatNotice")
               .value(CombatConfig.Config.combatNotice);
            writer.comment("The message that shows when a player is in combat, adding {timeLeft} will display how many seconds until combat is over")
               .name("inCombat")
               .value(CombatConfig.Config.inCombat);
            writer.comment("The message that shows when a player exits combat").name("outCombat").value(CombatConfig.Config.outCombat);
            writer.comment(
                  "This is a list of commands to be blocked while in combat, do not include the slash and use commas to separate them, leave empty to disable, example \"home,spawn,rtp\""
               )
               .name("blockedCommands")
               .value("");
            writer.comment("This is the message displayed in chat when a player attempts to use a blocked command")
               .name("blockedCommandMessage")
               .value(CombatConfig.Config.blockedCommandMessage);
            writer.comment("This determines if disconnecting while tagged kills the player").name("disconnectKill").value(CombatConfig.Config.disconnectKill);
            writer.comment(
                  "This is a command to be run when a tagged player disconnects, use {player} to autofill their name, leave blank to disable, example \"warn {player} combatlogging\""
               )
               .name("disconnectCommand")
               .value(CombatConfig.Config.disconnectCommand);
            writer.endObject();
         } catch (Throwable var6) {
            if (writer != null) {
               try {
                  writer.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (writer != null) {
            writer.close();
         }
      } catch (IOException var7) {
         log.error("Failed to save config", var7);
      }
   }

   public static class Config {
      public static Integer combatTime = 30;
      public static boolean allDamage = false;
      public static boolean mobDamage = false;
      public static boolean disableElytra = false;
      public static boolean disablePearl = false;
      public static String deathMessage = " has died of cowardice";
      public static boolean combatNotice = true;
      public static String inCombat = "You are in combat do not leave! {timeLeft} seconds left";
      public static String outCombat = "You are no longer in combat";
      public static List<String> blockedCommands = new ArrayList<>();
      public static String blockedCommandMessage = "You are in combat and cannot execute this command";
      public static boolean disconnectKill = true;
      public static String disconnectCommand = "";
   }
}
