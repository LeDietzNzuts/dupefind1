package net.p3pp3rf1y.sophisticatedbackpacks.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;

public class PlayerInventoryProvider {
   public static final String MAIN_INVENTORY = "main";
   public static final String OFFHAND_INVENTORY = "offhand";
   public static final String ARMOR_INVENTORY = "armor";
   private final Map<String, PlayerInventoryHandler> playerInventoryHandlers = new LinkedHashMap<>();
   private final List<String> renderedHandlers = new ArrayList<>();
   private static final PlayerInventoryProvider serverProvider = new PlayerInventoryProvider();
   private static final PlayerInventoryProvider clientProvider = new PlayerInventoryProvider();

   public static PlayerInventoryProvider get() {
      return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? clientProvider : serverProvider;
   }

   private PlayerInventoryProvider() {
      this.addPlayerInventoryHandler(
         "main",
         (player, gameTime) -> PlayerInventoryHandler.SINGLE_IDENTIFIER,
         (player, identifier) -> player.method_31548().field_7547.size(),
         (player, identifier, slot) -> (class_1799)player.method_31548().field_7547.get(slot),
         true,
         false,
         false,
         false
      );
      this.addPlayerInventoryHandler(
         "offhand",
         (player, gameTime) -> PlayerInventoryHandler.SINGLE_IDENTIFIER,
         (player, identifier) -> player.method_31548().field_7544.size(),
         (player, identifier, slot) -> (class_1799)player.method_31548().field_7544.get(slot),
         false,
         false,
         false,
         false
      );
      this.addPlayerInventoryHandler(
         "armor",
         (player, gameTime) -> PlayerInventoryHandler.SINGLE_IDENTIFIER,
         (player, identifier) -> 1,
         (player, identifier, slot) -> (class_1799)player.method_31548().field_7548.get(class_1304.field_6174.method_5927()),
         false,
         true,
         false,
         true
      );
   }

   public void addPlayerInventoryHandler(
      String name,
      BiFunction<class_1657, Long, Set<String>> identifiersGetter,
      PlayerInventoryHandler.SlotCountGetter slotCountGetter,
      PlayerInventoryHandler.SlotStackGetter slotStackGetter,
      boolean visibleInGui,
      boolean rendered,
      boolean ownRenderer,
      boolean accessibleByAnotherPlayer
   ) {
      Map<String, PlayerInventoryHandler> temp = new LinkedHashMap<>(this.playerInventoryHandlers);
      this.playerInventoryHandlers.clear();
      this.playerInventoryHandlers
         .put(name, new PlayerInventoryHandler(identifiersGetter, slotCountGetter, slotStackGetter, visibleInGui, ownRenderer, accessibleByAnotherPlayer));
      this.playerInventoryHandlers.putAll(temp);
      if (rendered) {
         ArrayList<String> tempRendered = new ArrayList<>(this.renderedHandlers);
         this.renderedHandlers.clear();
         this.renderedHandlers.add(name);
         this.renderedHandlers.addAll(tempRendered);
      }
   }

   public Optional<PlayerInventoryProvider.RenderInfo> getBackpackFromRendered(class_1657 player) {
      for (String handlerName : this.renderedHandlers) {
         PlayerInventoryHandler invHandler = this.playerInventoryHandlers.get(handlerName);
         if (invHandler == null) {
            return Optional.empty();
         }

         for (String identifier : new HashSet<>(invHandler.getIdentifiers(player, player.method_37908().method_8510()))) {
            for (int slot = 0; slot < invHandler.getSlotCount(player, identifier); slot++) {
               class_1799 slotStack = invHandler.getStackInSlot(player, identifier, slot);
               if (slotStack.method_7909() instanceof BackpackItem) {
                  return invHandler.hasItsOwnRenderer()
                     ? Optional.empty()
                     : Optional.of(new PlayerInventoryProvider.RenderInfo(slotStack, handlerName.equals("armor")));
               }
            }
         }
      }

      return Optional.empty();
   }

   private Map<String, PlayerInventoryHandler> getPlayerInventoryHandlers() {
      return this.playerInventoryHandlers;
   }

   public Optional<PlayerInventoryHandler> getPlayerInventoryHandler(String name) {
      return Optional.ofNullable(this.getPlayerInventoryHandlers().get(name));
   }

   public void runOnBackpacks(class_1657 player, PlayerInventoryProvider.BackpackInventorySlotConsumer backpackInventorySlotConsumer) {
      this.runOnBackpacks(player, backpackInventorySlotConsumer, false);
   }

   public void runOnBackpacks(
      class_1657 player, PlayerInventoryProvider.BackpackInventorySlotConsumer backpackInventorySlotConsumer, boolean onlyAccessibleByAnotherPlayer
   ) {
      for (Entry<String, PlayerInventoryHandler> entry : this.getPlayerInventoryHandlers().entrySet()) {
         if (this.runOnBackpacks(player, entry.getKey(), entry.getValue(), backpackInventorySlotConsumer, onlyAccessibleByAnotherPlayer)) {
            return;
         }
      }
   }

   public void runOnBackpacks(class_1657 player, String invIdentifier, PlayerInventoryProvider.BackpackInventorySlotConsumer backpackInventorySlotConsumer) {
      this.runOnBackpacks(player, invIdentifier, backpackInventorySlotConsumer, false);
   }

   public void runOnBackpacks(
      class_1657 player,
      String invIdentifier,
      PlayerInventoryProvider.BackpackInventorySlotConsumer backpackInventorySlotConsumer,
      boolean onlyAccessibleByAnotherPlayer
   ) {
      this.getPlayerInventoryHandler(invIdentifier)
         .ifPresent(invHandler -> this.runOnBackpacks(player, invIdentifier, invHandler, backpackInventorySlotConsumer, onlyAccessibleByAnotherPlayer));
   }

   private boolean runOnBackpacks(
      class_1657 player,
      String invIdentifier,
      PlayerInventoryHandler invHandler,
      PlayerInventoryProvider.BackpackInventorySlotConsumer backpackInventorySlotConsumer,
      boolean onlyAccessibleByAnotherPlayer
   ) {
      if (onlyAccessibleByAnotherPlayer && !invHandler.isAccessibleByAnotherPlayer()) {
         return false;
      } else {
         for (String identifier : new HashSet<>(invHandler.getIdentifiers(player, player.method_37908().method_8510()))) {
            for (int slot = 0; slot < invHandler.getSlotCount(player, identifier); slot++) {
               class_1799 slotStack = invHandler.getStackInSlot(player, identifier, slot);
               if (slotStack.method_7909() instanceof BackpackItem && backpackInventorySlotConsumer.accept(slotStack, invIdentifier, identifier, slot)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public interface BackpackInventorySlotConsumer {
      boolean accept(class_1799 var1, String var2, String var3, int var4);
   }

   public static class RenderInfo {
      private final class_1799 backpack;
      private final boolean isArmorSlot;

      public RenderInfo(class_1799 backpack, boolean isArmorSlot) {
         this.backpack = backpack;
         this.isArmorSlot = isArmorSlot;
      }

      public class_1799 getBackpack() {
         return this.backpack;
      }

      public boolean isArmorSlot() {
         return this.isArmorSlot;
      }
   }
}
