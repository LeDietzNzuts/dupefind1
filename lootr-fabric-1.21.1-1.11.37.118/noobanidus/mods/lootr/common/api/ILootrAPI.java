package noobanidus.mods.lootr.common.api;

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
import net.minecraft.class_3195;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface ILootrAPI {
   Set<UUID> getPlayerIds();

   MinecraftServer getServer();

   default int getCurrentTicks() {
      MinecraftServer server = this.getServer();
      return server == null ? -1 : server.method_3780();
   }

   boolean isFakePlayer(class_1657 var1);

   default boolean clearPlayerLoot(class_3222 entity) {
      return this.clearPlayerLoot(entity.method_5667());
   }

   boolean clearPlayerLoot(UUID var1);

   @Deprecated
   @Nullable
   ILootrInventory getInventory(ILootrInfoProvider var1, class_3222 var2, LootFiller var3);

   @Nullable
   default ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, @Nullable MenuBuilder builder) {
      return this.getInventory(provider, player, provider.getDefaultFiller(), builder);
   }

   @Nullable
   ILootrInventory getInventory(ILootrInfoProvider var1, class_3222 var2, LootFiller var3, @Nullable MenuBuilder var4);

   @Nullable
   ILootrSavedData getData(ILootrInfoProvider var1);

   long getLootSeed(long var1);

   boolean shouldDiscard();

   float getExplosionResistance(class_2248 var1, float var2);

   boolean isBlastResistant();

   boolean isBlastImmune();

   float getDestroyProgress(class_2680 var1, class_1657 var2, class_1922 var3, class_2338 var4, float var5);

   int getAnalogOutputSignal(class_2680 var1, class_1937 var2, class_2338 var3, int var4);

   boolean shouldPowerComparators();

   boolean shouldNotify(int var1);

   int getNotificationDelay();

   boolean isNotificationsEnabled();

   boolean isMessageStylesEnabled();

   ClientTextureType getTextureType();

   default boolean isNewTextures() {
      return this.getTextureType() == ClientTextureType.NEW;
   }

   default boolean isOldTextures() {
      return this.getTextureType() == ClientTextureType.OLD;
   }

   default boolean isVanillaTextures() {
      return this.getTextureType() == ClientTextureType.VANILLA;
   }

   default boolean isDefaultTextures() {
      return this.getTextureType() == ClientTextureType.NEW;
   }

   boolean isDisabled();

   boolean isLootTableBlacklisted(class_5321<class_52> var1);

   boolean isDimensionBlocked(class_5321<class_1937> var1);

   boolean isDimensionDecaying(class_5321<class_1937> var1);

   boolean isDimensionRefreshing(class_5321<class_1937> var1);

   Set<class_5321<class_1937>> getDimensionBlacklist();

   Set<class_5321<class_1937>> getDimensionWhitelist();

   Set<class_5321<class_52>> getLootTableBlacklist();

   Set<String> getLootModidBlacklist();

   Set<String> getModidDimensionWhitelist();

   Set<String> getModidDimensionBlacklist();

   boolean isDecaying(ILootrInfoProvider var1);

   boolean isRefreshing(ILootrInfoProvider var1);

   Set<String> getModidDecayWhitelist();

   Set<class_5321<class_52>> getDecayWhitelist();

   Set<class_5321<class_1937>> getDecayDimensions();

   Set<String> getRefreshModids();

   Set<class_5321<class_52>> getRefreshWhitelist();

   Set<class_5321<class_1937>> getRefreshDimensions();

   boolean reportUnresolvedTables();

   boolean isCustomTrapped();

   boolean isWorldBorderSafe(class_1937 var1, class_2338 var2);

   boolean isWorldBorderSafe(class_1937 var1, class_1923 var2);

   boolean shouldCheckWorldBorder();

   boolean shouldConvertMineshafts();

   @Deprecated
   boolean shouldConvertElytras();

   boolean shouldConvertElytrasToChests();

   boolean shouldConvertElytrasToItemFrames();

   boolean shouldConvertStructureItemFrames();

   int getDecayValue();

   boolean shouldDecayAll();

   int getRefreshValue();

   boolean shouldRefreshAll();

   class_2583 getInvalidStyle();

   class_2583 getDecayStyle();

   class_2583 getRefreshStyle();

   class_2583 getChatStyle();

   class_2561 getInvalidTableComponent(class_5321<class_52> var1);

   boolean canDestroyOrBreak(class_1657 var1);

   boolean isBreakDisabled();

   boolean isBreakEnabled();

   boolean isFakePlayerBreakEnabled();

   boolean canBrushablesSelfSupport();

   boolean canItemFramesSelfSupport();

   boolean shouldDropPlayerLoot();

   boolean shouldPerformDecayWhileTicking();

   boolean shouldPerformRefreshWhileTicking();

   boolean shouldStartDecayWhileTicking();

   boolean shouldStartRefreshWhileTicking();

   boolean shouldWarnNoLootTables();

   boolean performPiecewiseCheck();

   @Deprecated
   default boolean isAwarded(ILootrInfoProvider provider, class_3222 player) {
      return this.isAwarded(provider.getInfoUUID(), player);
   }

   @Deprecated
   boolean isAwarded(UUID var1, class_3222 var2);

   @Deprecated
   default void award(ILootrInfoProvider provider, class_3222 player) {
      this.award(provider.getInfoUUID(), player);
   }

   @Deprecated
   void award(UUID var1, class_3222 var2);

   int getRemainingDecayValue(ILootrInfoProvider var1);

   boolean isDecayed(ILootrInfoProvider var1);

   void setDecaying(ILootrInfoProvider var1);

   void removeDecayed(ILootrInfoProvider var1);

   int getRemainingRefreshValue(ILootrInfoProvider var1);

   boolean isRefreshed(ILootrInfoProvider var1);

   void setRefreshing(ILootrInfoProvider var1);

   void removeRefreshed(ILootrInfoProvider var1);

   @Nullable
   class_2680 replacementBlockState(class_2680 var1);

   void handleProviderSneak(@Nullable ILootrInfoProvider var1, class_3222 var2);

   void handleProviderOpen(@Nullable ILootrInfoProvider var1, class_3222 var2);

   void handleProviderOpen(@Nullable ILootrInfoProvider var1, class_3222 var2, MenuBuilder var3);

   void handleProviderTick(@Nullable ILootrInfoProvider var1);

   void handleProviderClientTick(@Nullable ILootrInfoProvider var1);

   @Nullable
   <T extends class_2586> ILootrBlockEntity resolveBlockEntity(T var1);

   <T extends class_1297> ILootrEntity resolveEntity(T var1);

   boolean isTaggedStructurePresent(class_3218 var1, class_1923 var2, class_6862<class_3195> var3, class_2338 var4);

   void playerDestroyed(class_1937 var1, class_1657 var2, class_2338 var3, @Nullable class_2586 var4);

   void refreshSections();

   List<ILootrFilter> getFilters();

   List<ILootrBlockEntityProcessor.Pre> getBlockEntityPreProcessors();

   List<ILootrBlockEntityProcessor.Post> getBlockEntityPostProcessors();

   List<ILootrEntityProcessor.Pre> getEntityPreProcessors();

   List<ILootrEntityProcessor.Post> getEntityPostProcessors();

   @Nullable
   <T> ILootrDataAdapter<T> getAdapter(T var1);

   @Nullable
   <T> ILootrItemFrameAdapter<T> getItemFrameAdapter(T var1);

   ILootrType getType(String var1);

   boolean shouldBypassSpawnProtection();

   boolean shouldReplaceWhenDecayed();

   PotDecorationsAdapter getDecorationsAdapter(class_2586 var1);

   PotDecorationsAdapter getDecorationsAdapter(class_1799 var1);

   PotDecorationsAdapter getDecorationsAdapter(class_9473 var1);

   SaveMode getFileSaveMode();

   boolean shouldDisplayUnopenedParticles();

   default long getGameTime() {
      MinecraftServer server = LootrAPI.getServer();
      return server == null ? -1L : server.method_27728().method_27859().method_188();
   }
}
