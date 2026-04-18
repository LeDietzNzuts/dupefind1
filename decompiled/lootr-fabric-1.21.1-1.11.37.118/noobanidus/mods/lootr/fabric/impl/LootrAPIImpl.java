package noobanidus.mods.lootr.fabric.impl;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5251;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.client.ClientTextureType;
import noobanidus.mods.lootr.common.api.config.SaveMode;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.impl.DefaultLootrAPIImpl;
import noobanidus.mods.lootr.fabric.config.ConfigManager;
import noobanidus.mods.lootr.fabric.event.LootrEventsInit;

public class LootrAPIImpl extends DefaultLootrAPIImpl {
   @Override
   public MinecraftServer getServer() {
      return LootrEventsInit.serverInstance;
   }

   @Override
   public boolean isFakePlayer(class_1657 player) {
      return player instanceof class_3222 sPlayer && sPlayer.field_13987 == null ? true : player instanceof FakePlayer;
   }

   @Override
   public long getLootSeed(long seed) {
      return !ConfigManager.get().seed.randomize_seed && seed != -1L && seed != 0L ? seed : ThreadLocalRandom.current().nextLong();
   }

   @Override
   public float getExplosionResistance(class_2248 block, float defaultResistance) {
      if (ConfigManager.get().breaking.blast_immune) {
         return Float.MAX_VALUE;
      } else {
         return ConfigManager.get().breaking.blast_resistant ? 16.0F : defaultResistance;
      }
   }

   @Override
   public boolean isBlastResistant() {
      return ConfigManager.get().breaking.blast_resistant;
   }

   @Override
   public boolean isBlastImmune() {
      return ConfigManager.get().breaking.blast_immune;
   }

   @Override
   public float getDestroyProgress(class_2680 state, class_1657 player, class_1922 level, class_2338 position, float defaultProgress) {
      return ConfigManager.get().breaking.disable_break ? 0.0F : defaultProgress;
   }

   @Override
   public int getAnalogOutputSignal(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos, int defaultSignal) {
      return this.shouldPowerComparators() ? 1 : defaultSignal;
   }

   @Override
   public boolean shouldPowerComparators() {
      return ConfigManager.get().breaking.power_comparators;
   }

   @Override
   public boolean shouldNotify(int remaining) {
      return ConfigManager.shouldNotify(remaining);
   }

   @Override
   public int getNotificationDelay() {
      return ConfigManager.get().notifications.notification_delay;
   }

   @Override
   public boolean isNotificationsEnabled() {
      return !ConfigManager.get().notifications.disable_notifications;
   }

   @Override
   public boolean isMessageStylesEnabled() {
      return !ConfigManager.get().notifications.disable_message_styles;
   }

   @Override
   public ClientTextureType getTextureType() {
      if (ConfigManager.isVanillaTextures()) {
         return ClientTextureType.VANILLA;
      } else {
         return ConfigManager.isNewTextures() ? ClientTextureType.NEW : ClientTextureType.OLD;
      }
   }

   @Override
   public boolean isDisabled() {
      return ConfigManager.get().conversion.disable;
   }

   @Override
   public boolean isLootTableBlacklisted(class_5321<class_52> table) {
      return ConfigManager.isBlacklisted(table);
   }

   @Override
   public boolean isDimensionBlocked(class_5321<class_1937> dimension) {
      return ConfigManager.isDimensionBlocked(dimension);
   }

   @Override
   public boolean isDimensionDecaying(class_5321<class_1937> dimension) {
      return ConfigManager.isDimensionDecaying(dimension);
   }

   @Override
   public boolean isDimensionRefreshing(class_5321<class_1937> dimension) {
      return ConfigManager.isDimensionRefreshing(dimension);
   }

   @Override
   public Set<class_5321<class_1937>> getDimensionBlacklist() {
      return ConfigManager.getDimensionBlacklist();
   }

   @Override
   public Set<class_5321<class_1937>> getDimensionWhitelist() {
      return ConfigManager.getDimensionWhitelist();
   }

   @Override
   public Set<class_5321<class_52>> getLootTableBlacklist() {
      return ConfigManager.getLootBlacklist();
   }

   @Override
   public Set<String> getLootModidBlacklist() {
      return ConfigManager.getLootModidsBlacklist();
   }

   @Override
   public Set<String> getModidDimensionWhitelist() {
      return ConfigManager.getDimensionModidWhitelist();
   }

   @Override
   public Set<String> getModidDimensionBlacklist() {
      return ConfigManager.getDimensionModidBlacklist();
   }

   @Override
   public boolean isDecaying(ILootrInfoProvider provider) {
      return ConfigManager.isDecaying(provider);
   }

   @Override
   public boolean isRefreshing(ILootrInfoProvider provider) {
      return ConfigManager.isRefreshing(provider);
   }

   @Override
   public Set<String> getModidDecayWhitelist() {
      return ConfigManager.getDecayMods();
   }

   @Override
   public Set<class_5321<class_52>> getDecayWhitelist() {
      return ConfigManager.getDecayingTables();
   }

   @Override
   public Set<class_5321<class_1937>> getDecayDimensions() {
      return ConfigManager.getDecayDimensions();
   }

   @Override
   public Set<String> getRefreshModids() {
      return ConfigManager.getRefreshMods();
   }

   @Override
   public Set<class_5321<class_52>> getRefreshWhitelist() {
      return ConfigManager.getRefreshingTables();
   }

   @Override
   public Set<class_5321<class_1937>> getRefreshDimensions() {
      return ConfigManager.getRefreshDimensions();
   }

   @Override
   public boolean reportUnresolvedTables() {
      return ConfigManager.get().debug.report_unresolved_tables;
   }

   @Override
   public boolean isCustomTrapped() {
      return ConfigManager.get().breaking.trapped_custom;
   }

   @Override
   public boolean shouldCheckWorldBorder() {
      return ConfigManager.get().conversion.world_border;
   }

   @Deprecated
   @Override
   public boolean shouldConvertMineshafts() {
      return ConfigManager.get().conversion.convert_mineshafts;
   }

   @Deprecated
   @Override
   public boolean shouldConvertElytras() {
      return false;
   }

   @Override
   public boolean shouldConvertElytrasToChests() {
      return ConfigManager.get().conversion.convert_elytras_to_chests;
   }

   @Override
   public boolean shouldConvertElytrasToItemFrames() {
      return ConfigManager.get().conversion.convert_elytras_to_item_frames;
   }

   @Override
   public boolean shouldConvertStructureItemFrames() {
      return ConfigManager.get().conversion.convert_structure_item_frames;
   }

   @Override
   public int getDecayValue() {
      return ConfigManager.get().decay.decay_value;
   }

   @Override
   public boolean shouldDecayAll() {
      return ConfigManager.get().decay.decay_all;
   }

   @Override
   public int getRefreshValue() {
      return ConfigManager.get().refresh.refresh_value;
   }

   @Override
   public boolean shouldRefreshAll() {
      return ConfigManager.get().refresh.refresh_all;
   }

   @Override
   public class_2583 getInvalidStyle() {
      return !this.isMessageStylesEnabled()
         ? class_2583.field_24360
         : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1061)).method_10982(true);
   }

   @Override
   public class_2583 getDecayStyle() {
      return !this.isMessageStylesEnabled()
         ? class_2583.field_24360
         : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1061)).method_10982(true);
   }

   @Override
   public class_2583 getRefreshStyle() {
      return !this.isMessageStylesEnabled()
         ? class_2583.field_24360
         : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1078)).method_10982(true);
   }

   @Override
   public class_2583 getChatStyle() {
      return !this.isMessageStylesEnabled() ? class_2583.field_24360 : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1075));
   }

   @Override
   public boolean canDestroyOrBreak(class_1657 player) {
      return this.isFakePlayer(player) && this.isFakePlayerBreakEnabled() || this.isBreakEnabled();
   }

   @Override
   public boolean isBreakDisabled() {
      return ConfigManager.get().breaking.disable_break;
   }

   @Override
   public boolean isBreakEnabled() {
      return ConfigManager.get().breaking.enable_break;
   }

   @Override
   public boolean isFakePlayerBreakEnabled() {
      return ConfigManager.get().breaking.enable_fake_player_break;
   }

   @Override
   public boolean canBrushablesSelfSupport() {
      return ConfigManager.get().breaking.brushables_self_support;
   }

   @Override
   public boolean canItemFramesSelfSupport() {
      return ConfigManager.get().breaking.item_frames_self_support;
   }

   @Override
   public boolean shouldDropPlayerLoot() {
      return ConfigManager.get().breaking.should_drop_player_loot;
   }

   @Override
   public boolean shouldPerformDecayWhileTicking() {
      return ConfigManager.get().decay.perform_tick_decay;
   }

   @Override
   public boolean shouldPerformRefreshWhileTicking() {
      return ConfigManager.get().refresh.perform_tick_refresh;
   }

   @Override
   public boolean shouldStartDecayWhileTicking() {
      return ConfigManager.get().decay.start_tick_decay;
   }

   @Override
   public boolean shouldStartRefreshWhileTicking() {
      return ConfigManager.get().refresh.start_tick_refresh;
   }

   @Override
   public boolean shouldWarnNoLootTables() {
      return !ConfigManager.get().conversion.skip_logging_no_loot_table_at_generation;
   }

   @Override
   public boolean performPiecewiseCheck() {
      return ConfigManager.shouldPerformPiecewiseCheck();
   }

   @Override
   public boolean shouldBypassSpawnProtection() {
      return ConfigManager.get().conversion.bypass_spawn_protection;
   }

   @Override
   public boolean shouldReplaceWhenDecayed() {
      return ConfigManager.get().decay.replace_when_decayed;
   }

   @Override
   public SaveMode getFileSaveMode() {
      return ConfigManager.get().conversion.save_mode;
   }

   @Override
   public boolean shouldDisplayUnopenedParticles() {
      return ConfigManager.get().client.unopened_particles;
   }

   @Override
   public class_2561 getInvalidTableComponent(class_5321<class_52> lootTable) {
      return class_2561.method_43469("lootr.message.invalid_table", new Object[]{lootTable.method_29177().method_12836(), lootTable.toString()})
         .method_10862(
            !this.isMessageStylesEnabled()
               ? class_2583.field_24360
               : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1079)).method_10982(true)
         );
   }
}
