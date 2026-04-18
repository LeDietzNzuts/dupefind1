package cn.enaium.onekeyminer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.IOException
import java.util.ArrayList

public object Config {
   private final val configFile: File = new File(System.getProperty("user.dir"), "OneKeyMiner.json")

   public final var model: cn.enaium.onekeyminer.Config.Model = new Config.Model()
      private set

   public fun load() {
      if (configFile.exists()) {
         try {
            model = new Gson().fromJson(FilesKt.readText(configFile, Charsets.UTF_8), Config.Model.class) as Config.Model;
         } catch (var2: IOException) {
            var2.printStackTrace();
         }
      } else {
         this.save();
      }
   }

   public fun save() {
      try {
         val var10000: File = configFile;
         val var10001: java.lang.String = new GsonBuilder().setPrettyPrinting().create().toJson(model);
         FilesKt.writeText(var10000, var10001, Charsets.UTF_8);
      } catch (var2: IOException) {
         var2.printStackTrace();
      }
   }

   public class Model {
      public final var limit: Int = 64
      public final var interact: Boolean
      public final var axe: MutableList<String> =
         CollectionsKt.mutableListOf(
            new java.lang.String[]{
               "minecraft:warped_stem",
               "minecraft:crimson_stem",
               "minecraft:oak_log",
               "minecraft:birch_log",
               "minecraft:spruce_log",
               "minecraft:jungle_log",
               "minecraft:dark_oak_log",
               "minecraft:acacia_log",
               "minecraft:cherry_log",
               "minecraft:oak_leaves",
               "minecraft:spruce_leaves",
               "minecraft:birch_leaves",
               "minecraft:jungle_leaves",
               "minecraft:acacia_leaves",
               "minecraft:dark_oak_leaves",
               "minecraft:azalea_leaves",
               "minecraft:flowering_azalea_leaves",
               "minecraft:cherry_leaves"
            }
         )
         public final var hoe: MutableList<String> =
         CollectionsKt.mutableListOf(
            new java.lang.String[]{
               "minecraft:oak_leaves",
               "minecraft:spruce_leaves",
               "minecraft:birch_leaves",
               "minecraft:jungle_leaves",
               "minecraft:acacia_leaves",
               "minecraft:dark_oak_leaves",
               "minecraft:azalea_leaves",
               "minecraft:flowering_azalea_leaves",
               "minecraft:cherry_leaves",
               "minecraft:sponge",
               "minecraft:wet_sponge",
               "minecraft:hay_block",
               "minecraft:nether_wart_block",
               "minecraft:dried_kelp_block",
               "minecraft:warped_wart_block",
               "minecraft:shroomlight",
               "minecraft:sculk_sensor",
               "minecraft:moss_carpet",
               "minecraft:moss_block"
            }
         )
         public final var pickaxe: MutableList<String> =
         CollectionsKt.mutableListOf(
            new java.lang.String[]{
               "minecraft:gold_ore",
               "minecraft:deepslate_gold_ore",
               "minecraft:iron_ore",
               "minecraft:deepslate_iron_ore",
               "minecraft:coal_ore",
               "minecraft:deepslate_coal_ore",
               "minecraft:nether_gold_ore",
               "minecraft:lapis_ore",
               "minecraft:deepslate_lapis_ore",
               "minecraft:diamond_ore",
               "minecraft:deepslate_diamond_ore",
               "minecraft:redstone_ore",
               "minecraft:deepslate_redstone_ore",
               "minecraft:emerald_ore",
               "minecraft:deepslate_emerald_ore",
               "minecraft:nether_quartz_ore",
               "minecraft:copper_ore",
               "minecraft:deepslate_copper_ore"
            }
         )
         public final var shovel: MutableList<String> =
         CollectionsKt.mutableListOf(
            new java.lang.String[]{"minecraft:sand", "minecraft:red_sand", "minecraft:snow", "minecraft:snow_block", "minecraft:clay", "minecraft:soul_sand"}
         )
         public final var shears: MutableList<String> =
         CollectionsKt.mutableListOf(
            new java.lang.String[]{
               "minecraft:oak_leaves",
               "minecraft:spruce_leaves",
               "minecraft:birch_leaves",
               "minecraft:jungle_leaves",
               "minecraft:acacia_leaves",
               "minecraft:dark_oak_leaves",
               "minecraft:azalea_leaves",
               "minecraft:flowering_azalea_leaves",
               "minecraft:cherry_leaves"
            }
         )
         public final var any: MutableList<String> = (new ArrayList()) as java.util.List
   }
}
