package noobanidus.mods.lootr.common.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1264;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3195;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3341;
import net.minecraft.class_3443;
import net.minecraft.class_3449;
import net.minecraft.class_3908;
import net.minecraft.class_4838;
import net.minecraft.class_6862;
import net.minecraft.class_7151;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_8172;
import net.minecraft.class_8526;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_2586.class_9473;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.ILootrAPI;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.api.MenuBuilder;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.filter.ILootrFilter;
import noobanidus.mods.lootr.common.api.processor.ILootrBlockEntityProcessor;
import noobanidus.mods.lootr.common.api.processor.ILootrEntityProcessor;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.client.ClientHooks;
import noobanidus.mods.lootr.common.data.DataStorage;
import noobanidus.mods.lootr.common.integration.sherdsapi.SherdsIntegration;
import org.jetbrains.annotations.Nullable;

public abstract class DefaultLootrAPIImpl implements ILootrAPI {
   private static final class_3341 DESERT_PYRAMID_ADDITIONAL = new class_3341(-5, -30, -5, 5, 4, 4);
   private boolean sherdsChecked = false;
   private class_9331<?> sherdsType = null;

   @Override
   public final void handleProviderSneak(@Nullable ILootrInfoProvider provider, class_3222 player) {
      if (provider != null) {
         if (provider.canBeMarkedUnopened()) {
            if (provider.removeVisualOpener(player)) {
               provider.performClose(player);
               provider.performUpdate(player);
            }
         }
      }
   }

   @Override
   public final void handleProviderOpen(@Nullable ILootrInfoProvider provider, class_3222 player) {
      this.handleProviderOpen(provider, player, null);
   }

   @Override
   public final void handleProviderOpen(@Nullable ILootrInfoProvider provider, class_3222 player, @Nullable MenuBuilder menuBuilder) {
      if (provider != null) {
         if (player.method_7325()) {
            player.method_17355(null);
         } else if (provider.getInfoLevel() != null && !provider.getInfoLevel().method_8608()) {
            if (provider.canPlayerOpen(player)) {
               if (LootrAPI.isDecayed(provider) && provider.canDecay()) {
                  provider.performDecay();
                  player.method_7353(class_2561.method_43471("lootr.message.decayed").method_10862(LootrAPI.getDecayStyle()), true);
               } else {
                  if (provider.canDecay()) {
                     int decayValue = LootrAPI.getRemainingDecayValue(provider);
                     if (decayValue > 0 && LootrAPI.shouldNotify(decayValue)) {
                        player.method_7353(
                           class_2561.method_43469("lootr.message.decay_in", new Object[]{decayValue / 20}).method_10862(LootrAPI.getDecayStyle()), true
                        );
                     } else if (decayValue == -1 && LootrAPI.isDecaying(provider)) {
                        LootrAPI.setDecaying(provider);
                        player.method_7353(
                           class_2561.method_43469("lootr.message.decay_start", new Object[]{LootrAPI.getDecayValue() / 20})
                              .method_10862(LootrAPI.getDecayStyle()),
                           true
                        );
                     }
                  }

                  provider.performTrigger(player);
                  boolean shouldUpdate = false;
                  if (LootrAPI.isRefreshed(provider) && provider.canRefresh()) {
                     provider.performRefresh();
                     provider.performClose();
                     player.method_7353(class_2561.method_43471("lootr.message.refreshed").method_10862(LootrAPI.getRefreshStyle()), true);
                     shouldUpdate = true;
                  }

                  if (provider.canRefresh()) {
                     int refreshValue = LootrAPI.getRemainingRefreshValue(provider);
                     if (refreshValue > 0 && LootrAPI.shouldNotify(refreshValue)) {
                        player.method_7353(
                           class_2561.method_43469("lootr.message.refresh_in", new Object[]{refreshValue / 20}).method_10862(LootrAPI.getRefreshStyle()), true
                        );
                     } else if (refreshValue == -1 && LootrAPI.isRefreshing(provider)) {
                        LootrAPI.setRefreshing(provider);
                        player.method_7353(
                           class_2561.method_43469("lootr.message.refresh_start", new Object[]{LootrAPI.getRefreshValue() / 20})
                              .method_10862(LootrAPI.getRefreshStyle()),
                           true
                        );
                     }
                  }

                  class_3908 menuProvider = LootrAPI.getInventory(provider, player, menuBuilder);
                  if (menuProvider != null) {
                     if (!provider.hasServerOpened(player)) {
                        player.method_7259(LootrRegistry.getLootedStat());
                        LootrRegistry.getStatTrigger().trigger(player);
                     }

                     if (provider.addOpener(player)) {
                        provider.performOpen(player);
                        shouldUpdate = true;
                     }

                     if (shouldUpdate) {
                        provider.performUpdate(player);
                     }

                     player.method_17355(menuProvider);
                     class_4838.method_24733(player, true);
                  }
               }
            }
         }
      }
   }

   @Override
   public final void handleProviderTick(@Nullable ILootrInfoProvider provider) {
      if (provider != null) {
         if (provider.getInfoLevel() != null && !provider.getInfoLevel().method_8608()) {
            if (LootrAPI.shouldPerformDecayWhileTicking() && LootrAPI.isDecayed(provider) && provider.hasBeenOpened() && provider.canDecay()) {
               provider.performDecay();
            } else {
               if (LootrAPI.shouldStartDecayWhileTicking() && !LootrAPI.isDecayed(provider) && provider.hasBeenOpened() && provider.canDecay()) {
                  int decayValue = LootrAPI.getRemainingDecayValue(provider);
                  if (decayValue == -1 && LootrAPI.isDecaying(provider)) {
                     LootrAPI.setDecaying(provider);
                  }
               }

               if (LootrAPI.shouldPerformRefreshWhileTicking() && LootrAPI.isRefreshed(provider) && provider.hasBeenOpened() && provider.canRefresh()) {
                  provider.performRefresh();
                  provider.performClose();
                  provider.performUpdate();
               }

               if (LootrAPI.shouldStartRefreshWhileTicking() && !LootrAPI.isRefreshed(provider) && provider.hasBeenOpened() && provider.canRefresh()) {
                  int refreshValue = LootrAPI.getRemainingRefreshValue(provider);
                  if (refreshValue == -1 && LootrAPI.isRefreshing(provider)) {
                     LootrAPI.setRefreshing(provider);
                  }
               }
            }
         }
      }
   }

   @Override
   public final void handleProviderClientTick(@Nullable ILootrInfoProvider provider) {
      if (provider != null) {
         if (provider.getInfoLevel() != null && provider.getInfoLevel().method_8608()) {
            if (LootrAPI.shouldDisplayUnopenedParticles()) {
               ILootrType type = provider.getInfoNewType();
               if (type != null && type.displaysUnopenedParticle()) {
                  ClientHooks.performUnopenedParticles(provider);
               }
            }
         }
      }
   }

   @Override
   public final Set<UUID> getPlayerIds() {
      MinecraftServer server = this.getServer();
      if (server == null) {
         return Set.of();
      } else {
         Set<UUID> result = new HashSet<>();

         for (class_3222 player : server.method_3760().method_14571()) {
            if (!this.isFakePlayer(player)) {
               UUID thisUuid = player.method_5667();
               if (thisUuid != null) {
                  result.add(thisUuid);
               }
            }
         }

         return result;
      }
   }

   @Override
   public final boolean clearPlayerLoot(UUID id) {
      return DataStorage.clearInventories(id);
   }

   @Deprecated
   @Override
   public final ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler) {
      return DataStorage.getInventory(provider, player, filler);
   }

   @Override
   public final ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler, @Nullable MenuBuilder menuBuilder) {
      ILootrInventory inventory = DataStorage.getInventory(provider, player, filler);
      if (inventory != null && menuBuilder != null) {
         inventory.setMenuBuilder(menuBuilder);
      }

      return inventory;
   }

   @Nullable
   @Override
   public final ILootrSavedData getData(ILootrInfoProvider provider) {
      return DataStorage.getData(provider);
   }

   @Override
   public final boolean shouldDiscard() {
      return LootrAPI.shouldDiscardIdAndOpeners;
   }

   @Deprecated
   @Override
   public final boolean isAwarded(UUID uuid, class_3222 player) {
      return DataStorage.isAwarded(uuid, player);
   }

   @Deprecated
   @Override
   public final void award(UUID id, class_3222 player) {
      DataStorage.award(id, player);
   }

   @Override
   public final int getRemainingDecayValue(ILootrInfoProvider provider) {
      return DataStorage.getDecayValue(provider);
   }

   @Override
   public final boolean isDecayed(ILootrInfoProvider provider) {
      return DataStorage.isDecayed(provider);
   }

   @Override
   public final void setDecaying(ILootrInfoProvider provider) {
      DataStorage.setDecaying(provider);
   }

   @Deprecated
   @Override
   public final void removeDecayed(ILootrInfoProvider provider) {
   }

   @Override
   public final int getRemainingRefreshValue(ILootrInfoProvider provider) {
      return DataStorage.getRefreshValue(provider);
   }

   @Override
   public final boolean isRefreshed(ILootrInfoProvider provider) {
      return DataStorage.isRefreshed(provider);
   }

   @Override
   public final void setRefreshing(ILootrInfoProvider provider) {
      DataStorage.setRefreshing(provider);
   }

   @Deprecated
   @Override
   public final void removeRefreshed(ILootrInfoProvider provider) {
   }

   @Nullable
   @Override
   public final <T extends class_2586> ILootrBlockEntity resolveBlockEntity(T blockEntity) {
      return LootrServiceRegistry.convertBlockEntity(blockEntity);
   }

   @Override
   public final <T extends class_1297> ILootrEntity resolveEntity(T entity) {
      return LootrServiceRegistry.convertEntity(entity);
   }

   @Override
   public boolean isTaggedStructurePresent(class_3218 level, class_1923 chunkPos, class_6862<class_3195> tag, class_2338 pos) {
      class_2378<class_3195> registry = level.method_30349().method_30530(class_7924.field_41246);
      List<class_3449> starts = level.method_27056()
         .method_41035(chunkPos, o -> registry.method_40265(registry.method_10206(o)).map(b -> b.method_40220(tag)).orElse(false));

      for (class_3449 start : starts) {
         class_3341 extended = start.method_14969().method_35410(8);
         if (extended.method_14662(pos)) {
            return true;
         }

         if (start.method_16656().method_41618().equals(class_7151.field_37753)) {
            class_2338 center = start.method_14969().method_22874();
            if (DESERT_PYRAMID_ADDITIONAL.method_19311(center.method_10263(), center.method_10264(), center.method_10260()).method_14662(pos)) {
               return true;
            }
         }
      }

      if (LootrAPI.performPiecewiseCheck()) {
         for (class_3449 start : starts) {
            for (class_3443 piece : start.method_14963()) {
               if (piece.method_14935().method_35410(8).method_14662(pos)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   @Override
   public boolean isWorldBorderSafe(class_1937 level, class_2338 pos) {
      return !this.shouldCheckWorldBorder() ? true : level.method_8621().method_11952(pos);
   }

   @Override
   public boolean isWorldBorderSafe(class_1937 level, class_1923 pos) {
      return !this.shouldCheckWorldBorder() ? true : level.method_8621().method_11951(pos);
   }

   @Override
   public void playerDestroyed(class_1937 level, class_1657 player, class_2338 pos, @Nullable class_2586 blockEntity) {
      if (this.shouldDropPlayerLoot() && !level.method_8608() && blockEntity != null) {
         ILootrBlockEntity inventory = LootrAPI.resolveBlockEntity(blockEntity);
         if (inventory instanceof ILootrInfoProvider && player instanceof class_3222 serverPlayer && inventory.canDropContentsWhenBroken()) {
            ILootrInventory inventoryx = this.getInventory(inventory, serverPlayer, inventory.getDefaultFiller(), null);
            if (inventoryx != null) {
               class_1264.method_5451(level, pos, inventoryx);
            }
         }
      }
   }

   @Override
   public void refreshSections() {
      MinecraftServer server = this.getServer();
      if (server == null || server.method_3724() || !server.method_3816()) {
         ClientHooks.refreshSection();
      }
   }

   @Nullable
   @Override
   public class_2680 replacementBlockState(class_2680 original) {
      return LootrServiceRegistry.getReplacementBlockState(original);
   }

   @Override
   public List<ILootrFilter> getFilters() {
      return LootrServiceRegistry.getFilters();
   }

   @Override
   public List<ILootrBlockEntityProcessor.Pre> getBlockEntityPreProcessors() {
      return LootrServiceRegistry.getBlockEntityPreProcessors();
   }

   @Override
   public List<ILootrBlockEntityProcessor.Post> getBlockEntityPostProcessors() {
      return LootrServiceRegistry.getBlockEntityPostProcessors();
   }

   @Override
   public List<ILootrEntityProcessor.Pre> getEntityPreProcessors() {
      return LootrServiceRegistry.getEntityPreProcessors();
   }

   @Override
   public List<ILootrEntityProcessor.Post> getEntityPostProcessors() {
      return LootrServiceRegistry.getEntityPostProcessors();
   }

   @Nullable
   @Override
   public <T> ILootrDataAdapter<T> getAdapter(T type) {
      return LootrServiceRegistry.getAdapter(type);
   }

   @Nullable
   @Override
   public <T> ILootrItemFrameAdapter<T> getItemFrameAdapter(T type) {
      return LootrServiceRegistry.getItemFrameAdapter(type);
   }

   @Override
   public ILootrType getType(String type) {
      return LootrServiceRegistry.getType(type);
   }

   @Nullable
   private class_9331<?> getSherdsComponent() {
      if (!this.sherdsChecked) {
         this.sherdsChecked = true;
         this.sherdsType = (class_9331<?>)class_7923.field_49658.method_10223(LootrConstants.SHERDSAPI_POT_DECORATIONS);
      }

      return this.sherdsType;
   }

   @Nullable
   @Override
   public PotDecorationsAdapter getDecorationsAdapter(class_1799 stack) {
      class_9331<?> sherdsType = this.getSherdsComponent();
      if (sherdsType != null && stack.method_57826(sherdsType)) {
         return SherdsIntegration.getAdapterFrom(stack);
      } else {
         class_8526 output = (class_8526)stack.method_57824(class_9334.field_49621);
         return output == null ? null : new PotDecorationsAdapter(output);
      }
   }

   @Nullable
   @Override
   public PotDecorationsAdapter getDecorationsAdapter(class_9473 stack) {
      class_9331<?> sherdsType = this.getSherdsComponent();
      if (sherdsType != null) {
         Object comp = stack.method_58694(sherdsType);
         if (comp != null) {
            return SherdsIntegration.getAdapterFrom(stack);
         }
      }

      class_8526 output = (class_8526)stack.method_58694(class_9334.field_49621);
      return output == null ? null : new PotDecorationsAdapter(output);
   }

   @Nullable
   @Override
   public PotDecorationsAdapter getDecorationsAdapter(class_2586 blockEntity) {
      if (blockEntity instanceof class_8172 decoratedPotBlockEntity) {
         class_9331<?> sherdsType = this.getSherdsComponent();
         if (sherdsType != null) {
            return SherdsIntegration.getAdapterFrom(blockEntity);
         } else {
            return decoratedPotBlockEntity.method_51511() != class_8526.field_44707 ? new PotDecorationsAdapter(decoratedPotBlockEntity.method_51511()) : null;
         }
      } else {
         return null;
      }
   }
}
