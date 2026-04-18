package net.p3pp3rf1y.sophisticatedbackpacks.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3966;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IAttackEntityResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockClickResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModPayloads;
import net.p3pp3rf1y.sophisticatedbackpacks.network.AnotherPlayerBackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatRegistry;
import net.p3pp3rf1y.sophisticatedcore.event.common.EntityEvents;
import net.p3pp3rf1y.sophisticatedcore.event.common.ItemEntityEvents;
import net.p3pp3rf1y.sophisticatedcore.event.common.LivingEntityEvents;
import net.p3pp3rf1y.sophisticatedcore.event.common.MobSpawnEvents;
import net.p3pp3rf1y.sophisticatedcore.event.common.EntityEvents.JoinWorld;
import net.p3pp3rf1y.sophisticatedcore.event.common.MobSpawnEvents.FinalizeSpawn;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncPlayerSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.upgrades.infinity.InfinityUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public class CommonEventHandler {
   private static final int BACKPACK_CHECK_COOLDOWN = 40;
   private final Map<class_2960, Long> nextBackpackCheckTime = new HashMap<>();

   public void registerHandlers() {
      ModCompat.register();
      CompatRegistry.getRegistry("sophisticatedbackpacks").initCompats();
      ModItems.registerHandlers();
      ModBlocks.registerHandlers();
      ModPayloads.registerPayloads();
      ItemEntityEvents.CAN_PICKUP.register(this::onItemPickup);
      MobSpawnEvents.AFTER_FINALIZE_SPAWN.register(this::onLivingSpecialSpawn);
      LivingEntityEvents.DROPS.register(this::onLivingDrops);
      EntityTrackingEvents.STOP_TRACKING.register(this::onEntityLeaveWorld);
      AttackBlockCallback.EVENT.register(this::onBlockClick);
      AttackEntityCallback.EVENT.register(this::onAttackEntity);
      LivingEntityEvents.TICK.register(EntityBackpackAdditionHandler::onLivingUpdate);
      ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(this::onPlayerChangedDimension);
      ServerPlayerEvents.AFTER_RESPAWN.register(this::onPlayerRespawn);
      ServerTickEvents.END_WORLD_TICK.register(this::onWorldTick);
      UseEntityCallback.EVENT.register(this::interactWithEntity);
      PlayerBlockBreakEvents.BEFORE.register(this::handleBreakBackpackWithInfinityUpgrade);
      EntityEvents.ON_JOIN_WORLD.register((JoinWorld)(entity, world, loadedFromDisk) -> {
         if (entity.getClass().equals(class_1542.class) && ((class_1542)entity).method_6983().method_7909() instanceof BackpackItem backpack) {
            class_1297 newEntity = backpack.createEntity(world, entity, ((class_1542)entity).method_6983());
            if (newEntity != null) {
               entity.method_31472();
               world.method_8649(newEntity);
               return false;
            }
         }

         return true;
      });
   }

   private class_1269 interactWithEntity(class_1657 player, class_1937 world, class_1268 hand, class_1297 entity, @Nullable class_3966 hitResult) {
      if (entity instanceof class_1657 targetPlayer && hitResult != null && !Boolean.FALSE.equals(Config.SERVER.allowOpeningOtherPlayerBackpacks.get())) {
         class_243 targetPlayerViewVector = class_243.method_1034(new class_241(targetPlayer.method_36455(), targetPlayer.field_6283));
         class_243 hitVector = hitResult.method_17784();
         class_243 vec31 = player.method_19538().method_1035(targetPlayer.method_19538()).method_1029();
         vec31 = new class_243(vec31.field_1352, 0.0, vec31.field_1350);
         boolean isPointingAtBody = hitVector.field_1351 >= 0.9 && hitVector.field_1351 < 1.6;
         boolean isPointingAtBack = vec31.method_1026(targetPlayerViewVector) > 0.0;
         if (!isPointingAtBody || !isPointingAtBack) {
            return class_1269.field_5811;
         } else if (targetPlayer.method_37908().field_9236) {
            PacketDistributor.sendToServer(new AnotherPlayerBackpackOpenPayload(targetPlayer.method_5628()));
            return class_1269.field_5812;
         } else {
            return class_1269.field_5811;
         }
      } else {
         return class_1269.field_5811;
      }
   }

   private void onWorldTick(class_3218 level) {
      class_2960 dimensionKey = level.method_27983().method_29177();
      boolean runSlownessLogic = Boolean.TRUE.equals(Config.SERVER.nerfsConfig.tooManyBackpacksSlowness.get());
      boolean runDedupeLogic = Boolean.FALSE.equals(Config.SERVER.tickDedupeLogicDisabled.get());
      if ((runSlownessLogic || runDedupeLogic) && this.nextBackpackCheckTime.getOrDefault(dimensionKey, 0L) <= level.method_8510()) {
         this.nextBackpackCheckTime.put(dimensionKey, level.method_8510() + 40L);
         Set<UUID> backpackIds = new HashSet<>();
         level.method_18456()
            .forEach(
               player -> {
                  AtomicInteger numberOfBackpacks = new AtomicInteger(0);
                  PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, handlerName, identifier, slot) -> {
                     if (runSlownessLogic) {
                        numberOfBackpacks.incrementAndGet();
                     }

                     if (runDedupeLogic) {
                        addBackpackIdIfUniqueOrDedupe(backpackIds, BackpackWrapper.fromStack(backpack));
                     }

                     return false;
                  });
                  if (runSlownessLogic) {
                     int maxNumberOfBackpacks = (Integer)Config.SERVER.nerfsConfig.maxNumberOfBackpacks.get();
                     if (numberOfBackpacks.get() > maxNumberOfBackpacks) {
                        int numberOfSlownessLevels = Math.min(
                           10,
                           (int)Math.ceil(
                              (numberOfBackpacks.get() - maxNumberOfBackpacks) * (Double)Config.SERVER.nerfsConfig.slownessLevelsPerAdditionalBackpack.get()
                           )
                        );
                        player.method_6092(new class_1293(class_1294.field_5909, 80, numberOfSlownessLevels - 1, false, false));
                     }
                  }
               }
            );
      }
   }

   private static void addBackpackIdIfUniqueOrDedupe(Set<UUID> backpackIds, IBackpackWrapper backpackWrapper) {
      backpackWrapper.getContentsUuid().ifPresent(backpackId -> {
         if (backpackIds.contains(backpackId)) {
            backpackWrapper.removeContentsUUIDTag();
            backpackWrapper.onContentsNbtUpdated();
         } else {
            backpackIds.add(backpackId);
         }
      });
   }

   private void onPlayerChangedDimension(class_3222 player, class_3218 origin, class_3218 destination) {
      this.sendPlayerSettingsToClient(player);
   }

   private void sendPlayerSettingsToClient(class_1657 player) {
      if (player instanceof class_3222 serverPlayer) {
         String playerTagName = "sophisticatedBackpackSettings";
         PacketDistributor.sendToPlayer(serverPlayer, new SyncPlayerSettingsPayload(playerTagName, SettingsManager.getPlayerSettingsTag(player, playerTagName)));
      }
   }

   private void onPlayerRespawn(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
      this.sendPlayerSettingsToClient(newPlayer);
   }

   private class_1269 onBlockClick(class_1657 player, class_1937 world, class_1268 hand, class_2338 pos, class_2350 direction) {
      if (world.field_9236) {
         return class_1269.field_5811;
      } else {
         PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryHandlerName, identifier, slot) -> {
            IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);

            for (IBlockClickResponseUpgrade upgrade : wrapper.getUpgradeHandler().getWrappersThatImplement(IBlockClickResponseUpgrade.class)) {
               if (upgrade.onBlockClick(player, pos)) {
                  return true;
               }
            }

            return false;
         });
         return class_1269.field_5811;
      }
   }

   private class_1269 onAttackEntity(class_1657 player, class_1937 level, class_1268 hand, class_1297 entity, @Nullable class_3966 hitResult) {
      if (level.field_9236) {
         return class_1269.field_5811;
      } else {
         PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryHandlerName, identifier, slot) -> {
            IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);

            for (IAttackEntityResponseUpgrade upgrade : wrapper.getUpgradeHandler().getWrappersThatImplement(IAttackEntityResponseUpgrade.class)) {
               if (upgrade.onAttackEntity(player)) {
                  return true;
               }
            }

            return false;
         });
         return class_1269.field_5811;
      }
   }

   private void onLivingSpecialSpawn(FinalizeSpawn event) {
      if (event.getEntity() instanceof class_1588 monster && monster.method_6118(class_1304.field_6174).method_7960()) {
         EntityBackpackAdditionHandler.addBackpack(monster, event.getLevel(), event.getDifficulty());
      }
   }

   private boolean onLivingDrops(class_1309 target, class_1282 damageSource, Collection<class_1542> drops, boolean recentlyHit) {
      EntityBackpackAdditionHandler.handleBackpackDrop(target, damageSource, drops);
      return false;
   }

   private void onEntityLeaveWorld(class_1297 trackedEntity, class_3222 player) {
      if (trackedEntity instanceof class_1588 monster) {
         EntityBackpackAdditionHandler.removeBackpackUuid(monster, player.method_37908());
      }
   }

   private class_1269 onItemPickup(class_1657 player, class_1542 itemEntity, class_1799 stack) {
      if (!itemEntity.method_6983().method_7960() && itemEntity.field_7202 <= 0) {
         AtomicReference<class_1799> remainingStackSimulated = new AtomicReference<>(itemEntity.method_6983().method_7972());
         class_1937 level = player.method_5770();
         PlayerInventoryProvider.get()
            .runOnBackpacks(
               player,
               (backpack, inventoryHandlerName, identifier, slot) -> {
                  IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
                  remainingStackSimulated.set(
                     InventoryHelper.runPickupOnPickupResponseUpgrades(level, wrapper.getUpgradeHandler(), remainingStackSimulated.get(), true)
                  );
                  return remainingStackSimulated.get().method_7960();
               },
               (Boolean)Config.SERVER.nerfsConfig.onlyWornBackpackTriggersUpgrades.get()
            );
         if (remainingStackSimulated.get().method_7947() != itemEntity.method_6983().method_7947()) {
            AtomicReference<class_1799> remainingStack = new AtomicReference<>(itemEntity.method_6983().method_7972());
            PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryHandlerName, identifier, slot) -> {
               IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
               remainingStack.set(InventoryHelper.runPickupOnPickupResponseUpgrades(level, player, wrapper.getUpgradeHandler(), remainingStack.get(), false));
               return remainingStack.get().method_7960();
            }, (Boolean)Config.SERVER.nerfsConfig.onlyWornBackpackTriggersUpgrades.get());
            itemEntity.method_6979(remainingStack.get());
            return class_1269.field_5812;
         } else {
            return class_1269.field_5811;
         }
      } else {
         return class_1269.field_5811;
      }
   }

   private boolean handleBreakBackpackWithInfinityUpgrade(
      class_1937 world, class_1657 player, class_2338 pos, class_2680 state, @org.jetbrains.annotations.Nullable class_2586 blockEntity
   ) {
      if (!(state.method_26204() instanceof BackpackBlock)) {
         return true;
      } else if (WorldHelper.getBlockEntity(world, pos, BackpackBlockEntity.class)
         .map(
            backpackBlockEntity -> backpackBlockEntity.getStorageWrapper()
               .getUpgradeHandler()
               .getTypeWrappers(InfinityUpgradeItem.TYPE)
               .stream()
               .anyMatch(w -> !player.method_5687(w.getPermissionLevel()))
         )
         .orElse(false)) {
         player.method_7353(
            SBPTranslationHelper.INSTANCE.translStatusMessage("infinity_upgrade_only_admin_break", new Object[0]).method_27692(class_124.field_1061), true
         );
         return false;
      } else {
         return true;
      }
   }
}
