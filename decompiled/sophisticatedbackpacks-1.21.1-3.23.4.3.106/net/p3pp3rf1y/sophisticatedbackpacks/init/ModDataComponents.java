package net.p3pp3rf1y.sophisticatedbackpacks.init;

import com.mojang.serialization.Codec;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9135;
import net.minecraft.class_9331;
import net.minecraft.class_9331.class_9332;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception.InventoryOrder;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill.RefillUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper.ToolSwapMode;

public class ModDataComponents {
   private static final DeferredRegister<class_9331<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(class_7923.field_49658, "sophisticatedbackpacks");
   public static final Supplier<class_9331<class_2960>> LOOT_TABLE = DATA_COMPONENT_TYPES.register(
      "loot_table", () -> new class_9332().method_57881(class_2960.field_25139).method_57882(class_2960.field_48267).method_57880()
   );
   public static final Supplier<class_9331<Float>> LOOT_FACTOR = DATA_COMPONENT_TYPES.register(
      "loot_factor", () -> new class_9332().method_57881(Codec.FLOAT).method_57882(class_9135.field_48552).method_57880()
   );
   public static final Supplier<class_9331<Integer>> COLUMNS_TAKEN = DATA_COMPONENT_TYPES.register(
      "columns_taken", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<String>> ITEM_NAME = DATA_COMPONENT_TYPES.register(
      "item_name", () -> new class_9332().method_57881(Codec.STRING).method_57882(class_9135.field_48554).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> FILTER_BY_INVENTORY = DATA_COMPONENT_TYPES.register(
      "filter_by_inventory", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<InventoryOrder>> INVENTORY_ORDER = DATA_COMPONENT_TYPES.register(
      "inventory_order", () -> new class_9332().method_57881(InventoryOrder.CODEC).method_57882(InventoryOrder.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Map<Integer, RefillUpgradeWrapper.TargetSlot>>> TARGET_SLOTS = DATA_COMPONENT_TYPES.register(
      "target_slots",
      () -> new class_9332().method_57881(RefillUpgradeWrapper.TARGET_SLOTS_CODEC).method_57882(RefillUpgradeWrapper.TARGET_SLOTS_STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> SHOULD_SWAP_WEAPON = DATA_COMPONENT_TYPES.register(
      "should_swap_weapon", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<ToolSwapMode>> TOOL_SWAP_MODE = DATA_COMPONENT_TYPES.register(
      "tool_swap_mode", () -> new class_9332().method_57881(ToolSwapMode.CODEC).method_57882(ToolSwapMode.STREAM_CODEC).method_57880()
   );

   public static void register() {
      DATA_COMPONENT_TYPES.register();
   }
}
