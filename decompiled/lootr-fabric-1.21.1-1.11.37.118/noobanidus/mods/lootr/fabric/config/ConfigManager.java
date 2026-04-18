package noobanidus.mods.lootr.fabric.config;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Excluded;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.config.SaveMode;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.config.ConfigManagerBase;
import noobanidus.mods.lootr.common.impl.LootrServiceRegistry;

@Config(name = "lootr")
public class ConfigManager extends ConfigManagerBase implements ConfigData {
   @Excluded
   private static final List<class_2960> PROBLEMATIC_CHESTS = Arrays.asList(
      class_2960.method_60655("atum", "chests/pharaoh"), class_2960.method_60655("twilightforest", "structures/stronghold_boss")
   );
   @Excluded
   private static Set<String> DECAY_MODS = null;
   @Excluded
   private static Set<class_5321<class_52>> DECAY_TABLES = null;
   @Excluded
   private static Set<String> REFRESH_MODS = null;
   @Excluded
   private static Set<class_5321<class_52>> REFRESH_TABLES = null;
   @Excluded
   private static Set<class_5321<class_1937>> DIM_WHITELIST = null;
   @Excluded
   private static Set<String> MODID_DIM_WHITELIST = null;
   @Excluded
   private static Set<class_5321<class_1937>> DIM_BLACKLIST = null;
   @Excluded
   private static Set<String> MODID_DIM_BLACKLIST = null;
   @Excluded
   private static Set<class_5321<class_1937>> DECAY_DIMS = null;
   @Excluded
   private static Set<class_5321<class_1937>> REFRESH_DIMS = null;
   @Excluded
   private static Set<class_5321<class_52>> LOOT_BLACKLIST = null;
   @Excluded
   private static Set<String> LOOT_MODIDS = null;
   @CollapsibleObject
   public ConfigManager.Debug debug = new ConfigManager.Debug();
   @CollapsibleObject
   public ConfigManager.Seed seed = new ConfigManager.Seed();
   @CollapsibleObject
   public ConfigManager.Conversion conversion = new ConfigManager.Conversion();
   @CollapsibleObject
   public ConfigManager.Breaking breaking = new ConfigManager.Breaking();
   @CollapsibleObject
   public ConfigManager.Lists lists = new ConfigManager.Lists();
   @CollapsibleObject
   public ConfigManager.Decay decay = new ConfigManager.Decay();
   @CollapsibleObject
   public ConfigManager.Refresh refresh = new ConfigManager.Refresh();
   @CollapsibleObject
   public ConfigManager.Notifications notifications = new ConfigManager.Notifications();
   @CollapsibleObject
   public ConfigManager.Client client = new ConfigManager.Client();

   public static void reset() {
      LootrServiceRegistry.clearReplacements();
      MODID_DIM_WHITELIST = null;
      MODID_DIM_BLACKLIST = null;
      DIM_WHITELIST = null;
      DIM_BLACKLIST = null;
      LOOT_BLACKLIST = null;
      DECAY_MODS = null;
      DECAY_TABLES = null;
      DECAY_DIMS = null;
      LOOT_MODIDS = null;
      REFRESH_DIMS = null;
      REFRESH_MODS = null;
      REFRESH_TABLES = null;
      LootrAPI.refreshSections();
   }

   public static ConfigManager get() {
      return (ConfigManager)AutoConfig.getConfigHolder(ConfigManager.class).getConfig();
   }

   public static Set<class_5321<class_1937>> getDimensionWhitelist() {
      if (DIM_WHITELIST == null) {
         DIM_WHITELIST = validateDimensions(get().lists.dimension_whitelist, "dimension_whitelist");
      }

      return DIM_WHITELIST;
   }

   public static Set<String> getDimensionModidWhitelist() {
      if (MODID_DIM_WHITELIST == null) {
         MODID_DIM_WHITELIST = validateStringList(get().lists.modid_dimension_whitelist, "modid_dimension_whitelist");
      }

      return MODID_DIM_WHITELIST;
   }

   public static Set<class_5321<class_1937>> getDimensionBlacklist() {
      if (DIM_BLACKLIST == null) {
         DIM_BLACKLIST = validateDimensions(get().lists.dimension_blacklist, "dimension_blacklist");
      }

      return DIM_BLACKLIST;
   }

   public static Set<String> getDimensionModidBlacklist() {
      if (MODID_DIM_BLACKLIST == null) {
         MODID_DIM_BLACKLIST = validateStringList(get().lists.modid_dimension_blacklist, "modid_dimension_blacklist");
      }

      return MODID_DIM_BLACKLIST;
   }

   public static Set<class_5321<class_1937>> getDecayDimensions() {
      if (DECAY_DIMS == null) {
         DECAY_DIMS = validateDimensions(get().decay.decay_dimensions, "decay_dimensions");
      }

      return DECAY_DIMS;
   }

   public static Set<class_5321<class_1937>> getRefreshDimensions() {
      if (REFRESH_DIMS == null) {
         REFRESH_DIMS = validateDimensions(get().refresh.refresh_dimensions, "refresh_dimensions");
      }

      return REFRESH_DIMS;
   }

   public static Set<class_5321<class_52>> getLootBlacklist() {
      if (LOOT_BLACKLIST == null) {
         LOOT_BLACKLIST = validateResourceKeyList(get().lists.loot_table_blacklist, "loot_blacklist", o -> class_5321.method_29179(class_7924.field_50079, o));
         PROBLEMATIC_CHESTS.forEach(o -> LOOT_BLACKLIST.add(class_5321.method_29179(class_7924.field_50079, o)));
      }

      return LOOT_BLACKLIST;
   }

   public static Set<String> getLootModidsBlacklist() {
      if (LOOT_MODIDS == null) {
         LOOT_MODIDS = validateStringList(get().lists.loot_modid_blacklist, "loot_modid_blacklist");
      }

      return LOOT_MODIDS;
   }

   public static boolean isBlacklisted(class_5321<class_52> table) {
      return getLootBlacklist().contains(table) ? true : getLootModidsBlacklist().contains(table.method_29177().method_12836());
   }

   public static Set<class_5321<class_52>> getDecayingTables() {
      if (DECAY_TABLES == null) {
         DECAY_TABLES = validateResourceKeyList(get().decay.decay_loot_tables, "decay_loot_tables", o -> class_5321.method_29179(class_7924.field_50079, o));
      }

      return DECAY_TABLES;
   }

   public static Set<String> getDecayMods() {
      if (DECAY_MODS == null) {
         DECAY_MODS = validateStringList(get().decay.decay_modids, "decay_mods");
      }

      return DECAY_MODS;
   }

   public static Set<class_5321<class_52>> getRefreshingTables() {
      if (REFRESH_TABLES == null) {
         REFRESH_TABLES = validateResourceKeyList(get().refresh.refresh_loot_tables, "refresh_tables", o -> class_5321.method_29179(class_7924.field_50079, o));
      }

      return REFRESH_TABLES;
   }

   public static Set<String> getRefreshMods() {
      if (REFRESH_MODS == null) {
         REFRESH_MODS = validateStringList(get().refresh.refresh_modids, "refresh_modids");
      }

      return REFRESH_MODS;
   }

   public static boolean isDimensionBlocked(class_5321<class_1937> key) {
      return (getDimensionModidWhitelist().isEmpty() || getDimensionModidWhitelist().contains(key.method_29177().method_12836()))
            && !getDimensionModidBlacklist().contains(key.method_29177().method_12836())
         ? !getDimensionWhitelist().isEmpty() && !getDimensionWhitelist().contains(key) || getDimensionBlacklist().contains(key)
         : true;
   }

   public static boolean isDimensionDecaying(class_5321<class_1937> key) {
      return getDecayDimensions().contains(key);
   }

   public static boolean isDimensionRefreshing(class_5321<class_1937> key) {
      return getRefreshDimensions().contains(key);
   }

   public static boolean isDecaying(ILootrInfoProvider tile) {
      if (get().decay.decay_all) {
         return true;
      } else {
         if (tile.getInfoLootTable() != null) {
            if (getDecayingTables().contains(tile.getInfoLootTable())) {
               return true;
            }

            if (getDecayMods().contains(tile.getInfoLootTable().method_29177().method_12836())) {
               return true;
            }
         }

         return LootrAPI.isTaggedStructurePresent(
               (class_3218)tile.getInfoLevel(), new class_1923(tile.getInfoPos()), LootrTags.Structure.DECAY_STRUCTURES, tile.getInfoPos()
            )
            ? true
            : isDimensionDecaying(tile.getInfoDimension());
      }
   }

   public static boolean isRefreshing(ILootrInfoProvider tile) {
      if (get().refresh.refresh_all) {
         return true;
      } else {
         if (tile.getInfoLootTable() != null) {
            if (getRefreshingTables().contains(tile.getInfoLootTable())) {
               return true;
            }

            if (getRefreshMods().contains(tile.getInfoLootTable().method_29177().method_12836())) {
               return true;
            }
         }

         return LootrAPI.isTaggedStructurePresent(
               (class_3218)tile.getInfoLevel(), new class_1923(tile.getInfoPos()), LootrTags.Structure.REFRESH_STRUCTURES, tile.getInfoPos()
            )
            ? true
            : isDimensionRefreshing(tile.getInfoDimension());
      }
   }

   public static boolean shouldNotify(int remaining) {
      int delay = get().notifications.notification_delay;
      return !get().notifications.disable_notifications && (delay == -1 || remaining <= delay);
   }

   public static boolean shouldPerformPiecewiseCheck() {
      return get().conversion.perform_piecewise_check;
   }

   public static boolean isVanillaTextures() {
      return get().client.vanilla_textures;
   }

   public static boolean isNewTextures() {
      return get().client.new_textures;
   }

   public static class Breaking {
      public boolean disable_break = false;
      public boolean enable_break = false;
      public boolean enable_fake_player_break = false;
      public boolean power_comparators = true;
      public boolean blast_resistant = false;
      public boolean blast_immune = false;
      public boolean trapped_custom = false;
      public boolean should_drop_player_loot = false;
      public boolean brushables_self_support = false;
      public boolean item_frames_self_support = false;
   }

   public static class Client {
      public boolean vanilla_textures = false;
      public boolean new_textures = true;
      public boolean unopened_particles = true;
   }

   public static class Conversion {
      public boolean disable = false;
      public boolean convert_elytras_to_chests = false;
      public boolean convert_elytras_to_item_frames = true;
      @Deprecated
      public boolean convert_mineshafts = true;
      public boolean convert_structure_item_frames = true;
      public boolean world_border = false;
      public boolean perform_piecewise_check = true;
      public boolean bypass_spawn_protection = true;
      public boolean skip_logging_no_loot_table_at_generation = true;
      @EnumHandler(option = EnumDisplayOption.BUTTON)
      public SaveMode save_mode = SaveMode.SMART;
   }

   public static class Debug {
      public boolean report_unresolved_tables = true;
   }

   public static class Decay {
      public int decay_value = 6000;
      public boolean decay_all = false;
      @RequiresRestart
      public boolean perform_tick_decay = true;
      @RequiresRestart
      public boolean start_tick_decay = false;
      public List<String> decay_modids = List.of();
      public List<String> decay_loot_tables = List.of();
      public List<String> decay_dimensions = List.of();
      public boolean replace_when_decayed = false;
   }

   public static class Lists {
      public List<String> dimension_whitelist = List.of();
      public List<String> dimension_blacklist = List.of();
      public List<String> loot_table_blacklist = List.of();
      public List<String> loot_modid_blacklist = List.of();
      public List<String> modid_dimension_whitelist = List.of();
      public List<String> modid_dimension_blacklist = List.of();
   }

   public static class Notifications {
      public int notification_delay = 600;
      public boolean disable_notifications = false;
      public boolean disable_message_styles = false;
   }

   public static class Refresh {
      public int refresh_value = 24000;
      public boolean refresh_all = false;
      @RequiresRestart
      public boolean perform_tick_refresh = true;
      @RequiresRestart
      public boolean start_tick_refresh = true;
      public List<String> refresh_modids = List.of();
      public List<String> refresh_loot_tables = List.of();
      public List<String> refresh_dimensions = List.of();
   }

   public static class Seed {
      public boolean randomize_seed = true;
   }
}
