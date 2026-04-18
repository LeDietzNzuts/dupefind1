package net.p3pp3rf1y.sophisticatedbackpacks.client;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.BeforeInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1263;
import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3675.class_307;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.trinkets.TrinketsCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BlockToolSwapPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.EntityToolSwapPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.InventoryInteractionPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.UpgradeTogglePayload;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;

public class KeybindHandler {
   private static final int KEY_B = 66;
   private static final int KEY_C = 67;
   private static final int KEY_Z = 90;
   private static final int KEY_X = 88;
   private static final int KEY_UNKNOWN = -1;
   private static final int MIDDLE_BUTTON = 2;
   private static final int CHEST_SLOT_INDEX = 38;
   private static final int OFFHAND_SLOT_INDEX = 40;
   private static final String KEYBIND_SOPHISTICATEDBACKPACKS_CATEGORY = "keybind.sophisticatedbackpacks.category";
   public static final class_304 BACKPACK_TOGGLE_UPGRADE_5 = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("toggle_upgrade_5"),
      class_307.field_1668.method_1447(-1).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 BACKPACK_TOGGLE_UPGRADE_4 = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("toggle_upgrade_4"),
      class_307.field_1668.method_1447(-1).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 BACKPACK_TOGGLE_UPGRADE_3 = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("toggle_upgrade_3"),
      class_307.field_1668.method_1447(-1).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 BACKPACK_TOGGLE_UPGRADE_2 = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("toggle_upgrade_2"),
      class_307.field_1668.method_1447(88).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 BACKPACK_TOGGLE_UPGRADE_1 = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("toggle_upgrade_1"),
      class_307.field_1668.method_1447(90).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final Map<Integer, class_304> UPGRADE_SLOT_TOGGLE_KEYBINDS = Map.of(
      0, BACKPACK_TOGGLE_UPGRADE_1, 1, BACKPACK_TOGGLE_UPGRADE_2, 2, BACKPACK_TOGGLE_UPGRADE_3, 3, BACKPACK_TOGGLE_UPGRADE_4, 4, BACKPACK_TOGGLE_UPGRADE_5
   );
   public static final class_304 SORT_KEYBIND = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("sort"), class_307.field_1672, 2, "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 TOOL_SWAP_KEYBIND = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("tool_swap"), class_307.field_1668.method_1447(-1).method_1444(), "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 INVENTORY_INTERACTION_KEYBIND = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("inventory_interaction"),
      class_307.field_1668.method_1447(67).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );
   public static final class_304 BACKPACK_OPEN_KEYBIND = new class_304(
      SBPTranslationHelper.INSTANCE.translKeybind("open_backpack"),
      class_307.field_1668.method_1447(66).method_1444(),
      "keybind.sophisticatedbackpacks.category"
   );

   private KeybindHandler() {
   }

   public static void register() {
      if (!FabricLoader.getInstance().isModLoaded("mkb")) {
         ScreenEvents.BEFORE_INIT.register((BeforeInit)(client, screen, scaledWidth, scaledHeight) -> {
            ScreenKeyboardEvents.allowKeyPress(screen).register(KeybindHandler::handleGuiKeyPress);
            ScreenMouseEvents.allowMouseClick(screen).register(KeybindHandler::handleGuiMouseKeyPress);
         });
      }

      ClientTickEvents.END_CLIENT_TICK.register(KeybindHandler::handleKeyInputEvent);
   }

   public static void registerKeyMappings() {
      KeyBindingHelper.registerKeyBinding(BACKPACK_OPEN_KEYBIND);
      KeyBindingHelper.registerKeyBinding(INVENTORY_INTERACTION_KEYBIND);
      KeyBindingHelper.registerKeyBinding(TOOL_SWAP_KEYBIND);
      KeyBindingHelper.registerKeyBinding(SORT_KEYBIND);
      UPGRADE_SLOT_TOGGLE_KEYBINDS.forEach((slot, keybind) -> KeyBindingHelper.registerKeyBinding(keybind));
   }

   public static boolean handleGuiKeyPress(class_437 screen, int key, int scancode, int modifiers) {
      return SORT_KEYBIND.method_1417(key, scancode) && tryCallSort(screen)
         ? false
         : !BACKPACK_OPEN_KEYBIND.method_1417(key, scancode) || !sendBackpackOpenOrCloseMessage();
   }

   public static boolean handleGuiMouseKeyPress(class_437 screen, double mouseX, double mouseY, int button) {
      return SORT_KEYBIND.method_1433(button) && tryCallSort(screen) ? false : !BACKPACK_OPEN_KEYBIND.method_1433(button) || !sendBackpackOpenOrCloseMessage();
   }

   private static void handleKeyInputEvent(class_310 minecraft) {
      if (BACKPACK_OPEN_KEYBIND.method_1436()) {
         sendBackpackOpenOrCloseMessage();
      } else if (INVENTORY_INTERACTION_KEYBIND.method_1436()) {
         sendInteractWithInventoryMessage();
      } else if (TOOL_SWAP_KEYBIND.method_1436()) {
         sendToolSwapMessage();
      } else {
         for (Entry<Integer, class_304> slotKeybind : UPGRADE_SLOT_TOGGLE_KEYBINDS.entrySet()) {
            if (slotKeybind.getValue().method_1436()) {
               PacketDistributor.sendToServer(new UpgradeTogglePayload(slotKeybind.getKey()));
            }
         }
      }
   }

   public static boolean tryCallSort(class_437 gui) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && mc.field_1724.field_7512 instanceof BackpackContainer container && gui instanceof BackpackScreen screen) {
         class_312 mh = mc.field_1729;
         double mouseX = mh.method_1603() * mc.method_22683().method_4486() / mc.method_22683().method_4480();
         double mouseY = mh.method_1604() * mc.method_22683().method_4502() / mc.method_22683().method_4507();
         class_1735 selectedSlot = screen.method_2386(mouseX, mouseY);
         if (selectedSlot == null || container.isNotPlayersInventorySlot(selectedSlot.field_7874)) {
            container.sort();
            return true;
         }
      }

      return false;
   }

   private static void sendToolSwapMessage() {
      class_310 mc = class_310.method_1551();
      class_746 player = mc.field_1724;
      if (player != null && mc.field_1765 != null) {
         if (player.method_6047().method_7909() instanceof BackpackItem) {
            player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.unable_to_swap_tool_for_backpack"), true);
         } else {
            class_239 rayTrace = mc.field_1765;
            if (rayTrace.method_17783() == class_240.field_1332) {
               class_3965 blockRayTraceResult = (class_3965)rayTrace;
               class_2338 pos = blockRayTraceResult.method_17777();
               PacketDistributor.sendToServer(new BlockToolSwapPayload(pos));
            } else if (rayTrace.method_17783() == class_240.field_1331) {
               class_3966 entityRayTraceResult = (class_3966)rayTrace;
               PacketDistributor.sendToServer(new EntityToolSwapPayload(entityRayTraceResult.method_17782().method_5628()));
            }
         }
      }
   }

   private static void sendInteractWithInventoryMessage() {
      class_310 mc = class_310.method_1551();
      class_239 rayTrace = mc.field_1765;
      if (rayTrace != null && rayTrace.method_17783() == class_240.field_1332) {
         class_3965 blockraytraceresult = (class_3965)rayTrace;
         class_2338 pos = blockraytraceresult.method_17777();
         if (!Boolean.FALSE.equals(CapabilityHelper.getFromItemHandler(mc.field_1687, pos, itemHandler -> true, false))) {
            PacketDistributor.sendToServer(new InventoryInteractionPayload(pos, blockraytraceresult.method_17780()));
         }
      }
   }

   public static boolean sendBackpackOpenOrCloseMessage() {
      if (class_310.method_1551().field_1755 == null) {
         PacketDistributor.sendToServer(new BackpackOpenPayload());
         return false;
      } else {
         class_437 screen = class_310.method_1551().field_1755;
         if (screen instanceof class_465<?> containerScreen) {
            class_1735 slot = containerScreen.sophisticatedCore_getSlotUnderMouse();
            if (slot != null && (slot.field_7871 instanceof class_1661 || isTrinket(slot.field_7871))) {
               Optional<KeybindHandler.PlayerInventoryReturn> handler = getPlayerInventory(slot);
               if (handler.isPresent() && slot.method_7677().method_7909() instanceof BackpackItem) {
                  PacketDistributor.sendToServer(new BackpackOpenPayload(slot.method_34266(), handler.get().identifier(), handler.get().handlerName()));
                  return true;
               }
            }

            if (screen instanceof BackpackScreen
               && slot != null
               && slot.method_7677().method_7909() instanceof BackpackItem
               && slot.method_7677().method_7947() == 1) {
               PacketDistributor.sendToServer(new BackpackOpenPayload(slot.field_7874));
               return true;
            }
         }

         return false;
      }
   }

   private static boolean isTrinket(class_1263 container) {
      return FabricLoader.getInstance().isModLoaded("trinkets") && TrinketsCompat.isTrinketContainer(container);
   }

   private static Optional<KeybindHandler.PlayerInventoryReturn> getPlayerInventory(class_1735 slot) {
      int slotIndex = slot.method_34266();
      if (slotIndex == 38) {
         return Optional.of(new KeybindHandler.PlayerInventoryReturn("", "armor"));
      } else if (slotIndex == 40) {
         return Optional.of(new KeybindHandler.PlayerInventoryReturn("", "offhand"));
      } else if (isTrinket(slot.field_7871)) {
         return Optional.of(new KeybindHandler.PlayerInventoryReturn(TrinketsCompat.getIdentifierForSlot(slot.field_7871), "trinkets"));
      } else {
         return slotIndex >= 0 && slotIndex < 36 ? Optional.of(new KeybindHandler.PlayerInventoryReturn("", "main")) : Optional.empty();
      }
   }

   record PlayerInventoryReturn(String identifier, String handlerName) {
   }
}
