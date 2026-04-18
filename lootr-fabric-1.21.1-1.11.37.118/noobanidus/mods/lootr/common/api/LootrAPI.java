package noobanidus.mods.lootr.common.api;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3195;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3230;
import net.minecraft.class_3902;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_2586.class_9473;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.api.client.ClientTextureType;
import noobanidus.mods.lootr.common.api.config.SaveMode;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.filter.ILootrFilter;
import noobanidus.mods.lootr.common.api.processor.ILootrBlockEntityProcessor;
import noobanidus.mods.lootr.common.api.processor.ILootrEntityProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class LootrAPI {
   public static final Logger LOG = LogManager.getLogger();
   public static final String MODID = "lootr";
   public static final String NETWORK_VERSION = "lootr-1.21.0-1";
   public static final class_5321<class_52> ELYTRA_CHEST = class_5321.method_29179(class_7924.field_50079, rl("chests/elytra"));
   public static final class_5321<class_52> TROPHY_REWARD = class_5321.method_29179(class_7924.field_50079, rl("reward/trophy"));
   public static final class_5321<class_52> ITEM_FRAME_EMPTY = class_5321.method_29179(class_7924.field_50079, rl("entity/item_frame_empty"));
   @Deprecated
   public static final class_3230<class_3902> LOOTR_ENTITY_TICK_TICKET = class_3230.method_20628("lootr_entity_tick_ticket", (unit1, unit2) -> 0, 300);
   public static final List<class_2960> PROBLEMATIC_CHESTS = Arrays.asList(rl("twilightforest", "structures/stronghold_boss"), rl("atum", "chests/pharaoh"));
   public static ILootrAPI INSTANCE = null;
   public static boolean shouldDiscardIdAndOpeners;

   public static boolean isReady() {
      return INSTANCE != null;
   }

   public static class_2960 rl(String path) {
      return class_2960.method_60655("lootr", path);
   }

   public static class_2960 rl(String namespace, String path) {
      return class_2960.method_60655(namespace, path);
   }

   public static class_2960 mc(String path) {
      return class_2960.method_60656(path);
   }

   public static Set<UUID> getPlayerIds() {
      return INSTANCE.getPlayerIds();
   }

   public static MinecraftServer getServer() {
      return INSTANCE.getServer();
   }

   public static int getCurrentTicks() {
      return INSTANCE.getCurrentTicks();
   }

   public static boolean isFakePlayer(class_1657 player) {
      return INSTANCE.isFakePlayer(player);
   }

   public static boolean clearPlayerLoot(class_3222 entity) {
      return INSTANCE.clearPlayerLoot(entity);
   }

   public static boolean clearPlayerLoot(UUID id) {
      return INSTANCE.clearPlayerLoot(id);
   }

   public static long getLootSeed(long seed) {
      return INSTANCE.getLootSeed(seed);
   }

   public static boolean shouldDiscard() {
      return INSTANCE.shouldDiscard();
   }

   public static float getExplosionResistance(class_2248 block, float defaultResistance) {
      return INSTANCE.getExplosionResistance(block, defaultResistance);
   }

   public static boolean isBlastResistant() {
      return INSTANCE.isBlastResistant();
   }

   public static boolean isBlastImmune() {
      return INSTANCE.isBlastImmune();
   }

   public static float getDestroyProgress(class_2680 state, class_1657 player, class_1922 level, class_2338 position, float defaultProgress) {
      return INSTANCE.getDestroyProgress(state, player, level, position, defaultProgress);
   }

   public static int getAnalogOutputSignal(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos, int defaultSignal) {
      return INSTANCE.getAnalogOutputSignal(pBlockState, pLevel, pPos, defaultSignal);
   }

   public static boolean shouldPowerComparators() {
      return INSTANCE.shouldPowerComparators();
   }

   public static ClientTextureType getTextureType() {
      return INSTANCE.getTextureType();
   }

   public static boolean isOldTextures() {
      return INSTANCE.isOldTextures();
   }

   public static boolean isNewTextures() {
      return INSTANCE.isNewTextures();
   }

   public static boolean isVanillaTextures() {
      return INSTANCE.isVanillaTextures();
   }

   public static boolean isDefaultTextures() {
      return INSTANCE.isDefaultTextures();
   }

   public static boolean shouldNotify(int remaining) {
      return INSTANCE.shouldNotify(remaining);
   }

   public static int getNotificationDelay() {
      return INSTANCE.getNotificationDelay();
   }

   public static boolean isNotificationsEnabled() {
      return INSTANCE.isNotificationsEnabled();
   }

   public static boolean isDisabled() {
      return INSTANCE.isDisabled();
   }

   public static boolean isLootTableBlacklisted(class_5321<class_52> table) {
      return INSTANCE.isLootTableBlacklisted(table);
   }

   public static boolean isDimensionBlocked(class_5321<class_1937> dimension) {
      return INSTANCE.isDimensionBlocked(dimension);
   }

   public static Set<class_5321<class_1937>> getDimensionBlacklist() {
      return INSTANCE.getDimensionBlacklist();
   }

   public static Set<class_5321<class_1937>> getDimensionWhitelist() {
      return INSTANCE.getDimensionWhitelist();
   }

   public static Set<class_5321<class_52>> getLootTableBlacklist() {
      return INSTANCE.getLootTableBlacklist();
   }

   public static Set<String> getLootModidBlacklist() {
      return INSTANCE.getLootModidBlacklist();
   }

   public static Set<String> getModidDimensionWhitelist() {
      return INSTANCE.getModidDimensionWhitelist();
   }

   public static Set<String> getModidDimensionBlacklist() {
      return INSTANCE.getModidDimensionBlacklist();
   }

   public static boolean isDecaying(ILootrInfoProvider provider) {
      return INSTANCE.isDecaying(provider);
   }

   public static Set<String> getModidDecayWhitelist() {
      return INSTANCE.getModidDecayWhitelist();
   }

   public static Set<class_5321<class_52>> getDecayWhitelist() {
      return INSTANCE.getDecayWhitelist();
   }

   public static Set<class_5321<class_1937>> getDecayDimensions() {
      return INSTANCE.getDecayDimensions();
   }

   public static Set<String> getRefreshModids() {
      return INSTANCE.getRefreshModids();
   }

   public static Set<class_5321<class_52>> getRefreshWhitelist() {
      return INSTANCE.getRefreshWhitelist();
   }

   public static Set<class_5321<class_1937>> getRefreshDimensions() {
      return INSTANCE.getRefreshDimensions();
   }

   public static boolean isRefreshing(ILootrInfoProvider provider) {
      return INSTANCE.isRefreshing(provider);
   }

   public static boolean reportUnresolvedTables() {
      return INSTANCE.reportUnresolvedTables();
   }

   public static boolean isCustomTrapped() {
      return INSTANCE.isCustomTrapped();
   }

   public static boolean isWorldBorderSafe(class_1937 level, class_2338 pos) {
      return INSTANCE.isWorldBorderSafe(level, pos);
   }

   public static boolean isWorldBorderSafe(class_1937 level, class_1923 pos) {
      return INSTANCE.isWorldBorderSafe(level, pos);
   }

   @Deprecated
   public static boolean shouldCheckWorldBorder() {
      return INSTANCE.shouldCheckWorldBorder();
   }

   @Deprecated
   public static boolean shouldConvertMineshafts() {
      return INSTANCE.shouldConvertMineshafts();
   }

   @Deprecated
   public static boolean shouldConvertElytras() {
      return INSTANCE.shouldConvertElytras();
   }

   public static boolean shouldConvertElytrasToChests() {
      return INSTANCE.shouldConvertElytrasToChests();
   }

   public static boolean shouldConvertElytrasToItemFrames() {
      return INSTANCE.shouldConvertElytrasToItemFrames();
   }

   public static boolean shouldConvertStructureItemFrames() {
      return INSTANCE.shouldConvertStructureItemFrames();
   }

   public static int getDecayValue() {
      return INSTANCE.getDecayValue();
   }

   public static boolean shouldDecayAll() {
      return INSTANCE.shouldDecayAll();
   }

   public static int getRefreshValue() {
      return INSTANCE.getRefreshValue();
   }

   public static boolean shouldRefreshAll() {
      return INSTANCE.shouldRefreshAll();
   }

   public static boolean isMessageStylesEnabled() {
      return INSTANCE.isMessageStylesEnabled();
   }

   public static class_2583 getInvalidStyle() {
      return INSTANCE.getInvalidStyle();
   }

   public static class_2583 getDecayStyle() {
      return INSTANCE.getDecayStyle();
   }

   public static class_2583 getRefreshStyle() {
      return INSTANCE.getRefreshStyle();
   }

   public static class_2583 getChatStyle() {
      return INSTANCE.getChatStyle();
   }

   public static class_2561 getInvalidTableComponent(class_5321<class_52> lootTable) {
      return INSTANCE.getInvalidTableComponent(lootTable);
   }

   public static boolean canDestroyOrBreak(class_1657 player) {
      return INSTANCE.canDestroyOrBreak(player);
   }

   public static boolean canBrushablesSelfSupport() {
      return INSTANCE.canBrushablesSelfSupport();
   }

   public static boolean canItemFramesSelfSupport() {
      return INSTANCE.canItemFramesSelfSupport();
   }

   public static boolean isBreakDisabled() {
      return INSTANCE.isBreakDisabled();
   }

   public static boolean isBreakEnabled() {
      return INSTANCE.isBreakEnabled();
   }

   public static boolean isFakePlayerBreakEnabled() {
      return INSTANCE.isFakePlayerBreakEnabled();
   }

   public static boolean shouldDropPlayerLoot() {
      return INSTANCE.shouldDropPlayerLoot();
   }

   public static boolean shouldPerformDecayWhileTicking() {
      return INSTANCE.shouldPerformDecayWhileTicking();
   }

   public static boolean shouldPerformRefreshWhileTicking() {
      return INSTANCE.shouldPerformRefreshWhileTicking();
   }

   public static boolean shouldStartDecayWhileTicking() {
      return INSTANCE.shouldStartDecayWhileTicking();
   }

   public static boolean shouldStartRefreshWhileTicking() {
      return INSTANCE.shouldStartRefreshWhileTicking();
   }

   public static boolean performPiecewiseCheck() {
      return INSTANCE.performPiecewiseCheck();
   }

   @Nullable
   public static class_2680 replacementBlockState(class_2680 original) {
      return INSTANCE.replacementBlockState(original);
   }

   @Deprecated
   @Nullable
   public static ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler) {
      return INSTANCE.getInventory(provider, player, filler);
   }

   @Nullable
   public static ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, @Nullable MenuBuilder builder) {
      return INSTANCE.getInventory(provider, player, builder);
   }

   @Nullable
   public static ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player) {
      return INSTANCE.getInventory(provider, player, (MenuBuilder)null);
   }

   @Nullable
   public static ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler, @Nullable MenuBuilder builder) {
      return INSTANCE.getInventory(provider, player, filler, builder);
   }

   @Nullable
   public static ILootrSavedData getData(ILootrInfoProvider provider) {
      return INSTANCE.getData(provider);
   }

   @Deprecated
   public static boolean isAwarded(ILootrInfoProvider provider, class_3222 player) {
      return INSTANCE.isAwarded(provider, player);
   }

   @Deprecated
   public static boolean isAwarded(UUID uuid, class_3222 player) {
      return INSTANCE.isAwarded(uuid, player);
   }

   @Deprecated
   public static void award(ILootrInfoProvider provider, class_3222 player) {
      INSTANCE.award(provider, player);
   }

   @Deprecated
   public static void award(UUID id, class_3222 player) {
      INSTANCE.award(id, player);
   }

   public static int getRemainingDecayValue(ILootrInfoProvider provider) {
      return INSTANCE.getRemainingDecayValue(provider);
   }

   public static boolean isDecayed(ILootrInfoProvider provider) {
      return INSTANCE.isDecayed(provider);
   }

   public static void setDecaying(ILootrInfoProvider provider) {
      INSTANCE.setDecaying(provider);
   }

   @Deprecated
   public static void removeDecayed(ILootrInfoProvider provider) {
      INSTANCE.removeDecayed(provider);
   }

   public static int getRemainingRefreshValue(ILootrInfoProvider provider) {
      return INSTANCE.getRemainingRefreshValue(provider);
   }

   public static boolean isRefreshed(ILootrInfoProvider provider) {
      return INSTANCE.isRefreshed(provider);
   }

   public static void setRefreshing(ILootrInfoProvider provider) {
      INSTANCE.setRefreshing(provider);
   }

   @Deprecated
   public static void removeRefreshed(ILootrInfoProvider provider) {
      INSTANCE.removeRefreshed(provider);
   }

   public static void handleProviderOpen(@Nullable ILootrInfoProvider provider, class_3222 player) {
      INSTANCE.handleProviderOpen(provider, player);
   }

   public static void handleProviderSneak(@Nullable ILootrInfoProvider provider, class_3222 player) {
      INSTANCE.handleProviderSneak(provider, player);
   }

   public static void handleProviderTick(@Nullable ILootrInfoProvider provider) {
      INSTANCE.handleProviderTick(provider);
   }

   public static void handleProviderClientTick(@Nullable ILootrInfoProvider provider) {
      INSTANCE.handleProviderClientTick(provider);
   }

   @Nullable
   public static <T extends class_2586> ILootrBlockEntity resolveBlockEntity(T blockEntity) {
      return INSTANCE.resolveBlockEntity(blockEntity);
   }

   public static <T extends class_1297> ILootrEntity resolveEntity(T entity) {
      return INSTANCE.resolveEntity(entity);
   }

   public static boolean isTaggedStructurePresent(class_3218 level, class_1923 chunkPos, class_6862<class_3195> tag, class_2338 pos) {
      return INSTANCE.isTaggedStructurePresent(level, chunkPos, tag, pos);
   }

   public static void playerDestroyed(class_1937 level, class_1657 player, class_2338 blockPos, @Nullable class_2586 blockEntity) {
      INSTANCE.playerDestroyed(level, player, blockPos, blockEntity);
   }

   public static void refreshSections() {
      INSTANCE.refreshSections();
   }

   public static List<ILootrFilter> getFilters() {
      return INSTANCE.getFilters();
   }

   public static List<ILootrBlockEntityProcessor.Pre> getBlockEntityPreProcessors() {
      return INSTANCE.getBlockEntityPreProcessors();
   }

   public static List<ILootrBlockEntityProcessor.Post> getBlockEntityPostProcessors() {
      return INSTANCE.getBlockEntityPostProcessors();
   }

   public static List<ILootrEntityProcessor.Pre> getEntityPreProcessors() {
      return INSTANCE.getEntityPreProcessors();
   }

   public static List<ILootrEntityProcessor.Post> getEntityPostProcessors() {
      return INSTANCE.getEntityPostProcessors();
   }

   public static boolean shouldBypassSpawnProtection() {
      return INSTANCE.shouldBypassSpawnProtection();
   }

   public static boolean shouldReplaceWhenDecayed() {
      return INSTANCE.shouldReplaceWhenDecayed();
   }

   public static boolean shouldWarnNoLootTables() {
      return INSTANCE.shouldWarnNoLootTables();
   }

   public static void postProcess(
      class_3218 level, class_2338 position, class_2586 newBlockEntity, class_2680 newState, class_5321<class_52> lootTable, long lootTableSeed
   ) {
      for (ILootrBlockEntityProcessor.Post processor : getBlockEntityPostProcessors()) {
         processor.process(level, position, newBlockEntity, newState, lootTable, lootTableSeed);
      }
   }

   public static void preProcess(
      class_3218 level, class_2338 position, class_2586 oldBlockEntity, class_2680 newState, class_5321<class_52> lootTable, long lootTableSeed
   ) {
      for (ILootrBlockEntityProcessor.Pre processor : getBlockEntityPreProcessors()) {
         processor.process(level, position, oldBlockEntity, newState, lootTable, lootTableSeed);
      }
   }

   public static void postProcess(class_3218 level, class_1297 newBlockEntity, class_5321<class_52> lootTable, long lootTableSeed) {
      class_2338 pos = newBlockEntity.method_24515();

      for (ILootrEntityProcessor.Post processor : getEntityPostProcessors()) {
         processor.process(level, pos, newBlockEntity, null, lootTable, lootTableSeed);
      }
   }

   public static void preProcess(class_3218 level, class_1297 newBlockEntity, class_5321<class_52> lootTable, long lootTableSeed) {
      class_2338 pos = newBlockEntity.method_24515();

      for (ILootrEntityProcessor.Pre processor : getEntityPreProcessors()) {
         processor.process(level, pos, newBlockEntity, null, lootTable, lootTableSeed);
      }
   }

   @Nullable
   public static <T> ILootrDataAdapter<T> getAdapter(T type) {
      return INSTANCE.getAdapter(type);
   }

   @Nullable
   public static <T> ILootrItemFrameAdapter<T> getItemFrameAdapter(T type) {
      return INSTANCE.getItemFrameAdapter(type);
   }

   @Nullable
   public static ILootrType getType(String type) {
      return INSTANCE.getType(type);
   }

   @Nullable
   public static PotDecorationsAdapter getDecorationsAdapter(class_2586 blockEntity) {
      return INSTANCE.getDecorationsAdapter(blockEntity);
   }

   @Nullable
   public static PotDecorationsAdapter getDecorationsAdapter(class_1799 stack) {
      return INSTANCE.getDecorationsAdapter(stack);
   }

   @Nullable
   public static PotDecorationsAdapter getDecorationsAdapter(class_9473 container) {
      return INSTANCE.getDecorationsAdapter(container);
   }

   public static SaveMode getFileSaveMode() {
      return INSTANCE.getFileSaveMode();
   }

   public static boolean shouldDisplayUnopenedParticles() {
      return INSTANCE.shouldDisplayUnopenedParticles();
   }

   public static long getGameTime() {
      return INSTANCE.getGameTime();
   }
}
