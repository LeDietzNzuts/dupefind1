package net.p3pp3rf1y.sophisticatedcore.init;

import com.mojang.serialization.Codec;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.SimpleFluidContent;
import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_2960;
import net.minecraft.class_4844;
import net.minecraft.class_7923;
import net.minecraft.class_9135;
import net.minecraft.class_9279;
import net.minecraft.class_9288;
import net.minecraft.class_9331;
import net.minecraft.class_9331.class_9332;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SortBy;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterAttributes;
import net.p3pp3rf1y.sophisticatedcore.upgrades.feeding.HungerLevel;
import net.p3pp3rf1y.sophisticatedcore.upgrades.filter.Direction;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.RepeatMode;
import net.p3pp3rf1y.sophisticatedcore.upgrades.xppump.AutomationDirection;
import net.p3pp3rf1y.sophisticatedcore.util.SimpleItemContent;

public class ModCoreDataComponents {
   private static final DeferredRegister<class_9331<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(class_7923.field_49658, "sophisticatedcore");
   public static final Supplier<class_9331<Integer>> NUMBER_OF_INVENTORY_SLOTS = DATA_COMPONENT_TYPES.register(
      "number_of_inventory_slots", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Integer>> NUMBER_OF_UPGRADE_SLOTS = DATA_COMPONENT_TYPES.register(
      "number_of_upgrade_slots", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Integer>> MAIN_COLOR = DATA_COMPONENT_TYPES.register(
      "main_color", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Integer>> ACCENT_COLOR = DATA_COMPONENT_TYPES.register(
      "accent_color", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<UUID>> STORAGE_UUID = DATA_COMPONENT_TYPES.register(
      "storage_uuid", () -> new class_9332().method_57881(class_4844.field_25122).method_57882(class_4844.field_48453).method_57880()
   );
   public static final Supplier<class_9331<Integer>> OPEN_TAB_ID = DATA_COMPONENT_TYPES.register(
      "open_tab_id", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<SortBy>> SORT_BY = DATA_COMPONENT_TYPES.register(
      "sort_by", () -> new class_9332().method_57881(SortBy.CODEC).method_57882(SortBy.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<class_9279>> RENDER_INFO_TAG = DATA_COMPONENT_TYPES.register(
      "render_info_tag", () -> new class_9332().method_57881(class_9279.field_49303).method_57882(class_9279.field_49305).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> SHIFT_CLICK_INTO_STORAGE = DATA_COMPONENT_TYPES.register(
      "shift_click_into_storage", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<SimpleItemContent>> INPUT_ITEM = DATA_COMPONENT_TYPES.register(
      "input_item", () -> new class_9332().method_57881(SimpleItemContent.CODEC).method_57882(SimpleItemContent.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<SimpleItemContent>> RESULT_ITEM = DATA_COMPONENT_TYPES.register(
      "result_item", () -> new class_9332().method_57881(SimpleItemContent.CODEC).method_57882(SimpleItemContent.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Long>> ENERGY_STORED = DATA_COMPONENT_TYPES.register(
      "energy_stored", () -> new class_9332().method_57881(Codec.LONG).method_57882(class_9135.field_48551).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> COMPACT_NON_UNCRAFTABLE = DATA_COMPONENT_TYPES.register(
      "compact_non_uncraftable", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> SHOULD_WORK_IN_GUI = DATA_COMPONENT_TYPES.register(
      "should_work_in_gui", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<class_9288>> COOKING_INVENTORY = DATA_COMPONENT_TYPES.register(
      "cooking_inventory", () -> new class_9332().method_57881(class_9288.field_49335).method_57882(class_9288.field_49336).method_59871().method_57880()
   );
   public static final Supplier<class_9331<Long>> BURN_TIME_FINISH = DATA_COMPONENT_TYPES.register(
      "burn_time_finish", () -> new class_9332().method_57881(Codec.LONG).method_57882(class_9135.field_48551).method_57880()
   );
   public static final Supplier<class_9331<Integer>> BURN_TIME_TOTAL = DATA_COMPONENT_TYPES.register(
      "burn_time_total", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Long>> COOK_TIME_FINISH = DATA_COMPONENT_TYPES.register(
      "cook_time_finish", () -> new class_9332().method_57881(Codec.LONG).method_57882(class_9135.field_48551).method_57880()
   );
   public static final Supplier<class_9331<Integer>> COOK_TIME_TOTAL = DATA_COMPONENT_TYPES.register(
      "cook_time_total", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> IS_COOKING = DATA_COMPONENT_TYPES.register(
      "is_cooking", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<HungerLevel>> FEED_AT_HUNGER_LEVEL = DATA_COMPONENT_TYPES.register(
      "feed_at_hunger_level", () -> new class_9332().method_57881(HungerLevel.CODEC).method_57882(HungerLevel.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> FEED_IMMEDIATELY_WHEN_HURT = DATA_COMPONENT_TYPES.register(
      "feed_immediately_when_hurt", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Direction>> DIRECTION = DATA_COMPONENT_TYPES.register(
      "direction", () -> new class_9332().method_57881(Direction.CODEC).method_57882(Direction.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> IS_PLAYING = DATA_COMPONENT_TYPES.register(
      "is_playing", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> PICKUP_ITEMS = DATA_COMPONENT_TYPES.register(
      "pickup_items", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> PICKUP_XP = DATA_COMPONENT_TYPES.register(
      "pickup_xp", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<List<SimpleFluidContent>>> FLUID_FILTERS = DATA_COMPONENT_TYPES.register(
      "fluid_filters",
      () -> new class_9332()
         .method_57881(Codec.list(SimpleFluidContent.CODEC))
         .method_57882(SimpleFluidContent.STREAM_CODEC.method_56433(class_9135.method_56363()))
         .method_57880()
   );
   public static final Supplier<class_9331<Boolean>> IS_INPUT = DATA_COMPONENT_TYPES.register(
      "is_input", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> INTERACT_WITH_HAND = DATA_COMPONENT_TYPES.register(
      "interact_with_hand", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> INTERACT_WITH_WORLD = DATA_COMPONENT_TYPES.register(
      "interact_with_world", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<class_2960>> RECIPE_ID = DATA_COMPONENT_TYPES.register(
      "recipe_id", () -> new class_9332().method_57881(class_2960.field_25139).method_57882(class_2960.field_48267).method_57880()
   );
   public static final Supplier<class_9331<SimpleFluidContent>> FLUID_CONTENTS = DATA_COMPONENT_TYPES.register(
      "fluid_contents", () -> new class_9332().method_57881(SimpleFluidContent.CODEC).method_57882(SimpleFluidContent.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> SHOULD_VOID_OVERFLOW = DATA_COMPONENT_TYPES.register(
      "should_void_overflow", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<AutomationDirection>> AUTOMATION_DIRECTION = DATA_COMPONENT_TYPES.register(
      "automation_direction", () -> new class_9332().method_57881(AutomationDirection.CODEC).method_57882(AutomationDirection.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Integer>> LEVEL = DATA_COMPONENT_TYPES.register(
      "level", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Integer>> LEVELS_TO_STORE = DATA_COMPONENT_TYPES.register(
      "levels_to_store", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Integer>> LEVELS_TO_TAKE = DATA_COMPONENT_TYPES.register(
      "levels_to_take", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> MEND_ITEMS = DATA_COMPONENT_TYPES.register(
      "mend_items", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> FILTER_ATTRIBUTES = DATA_COMPONENT_TYPES.register(
      "filter_attributes", () -> new class_9332().method_57881(FilterAttributes.CODEC).method_57882(FilterAttributes.STREAM_CODEC).method_57880()
   );
   public static final DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> INPUT_FILTER_ATTRIBUTES = DATA_COMPONENT_TYPES.register(
      "input_filter_attributes", () -> new class_9332().method_57881(FilterAttributes.CODEC).method_57882(FilterAttributes.STREAM_CODEC).method_57880()
   );
   public static final DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> FUEL_FILTER_ATTRIBUTES = DATA_COMPONENT_TYPES.register(
      "fuel_filter_attributes", () -> new class_9332().method_57881(FilterAttributes.CODEC).method_57882(FilterAttributes.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> ENABLED = DATA_COMPONENT_TYPES.register(
      "enabled", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<RepeatMode>> REPEAT_MODE = DATA_COMPONENT_TYPES.register(
      "repeat_mode", () -> new class_9332().method_57881(RepeatMode.CODEC).method_57882(RepeatMode.STREAM_CODEC).method_57880()
   );
   public static final Supplier<class_9331<Boolean>> SHUFFLE = DATA_COMPONENT_TYPES.register(
      "shuffle", () -> new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final Supplier<class_9331<Integer>> DISC_SLOT_ACTIVE = DATA_COMPONENT_TYPES.register(
      "disc_slot_active", () -> new class_9332().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final Supplier<class_9331<Long>> DISC_FINISH_TIME = DATA_COMPONENT_TYPES.register(
      "disc_finish_time", () -> new class_9332().method_57881(Codec.LONG).method_57882(class_9135.field_48551).method_57880()
   );

   public static void register() {
      DATA_COMPONENT_TYPES.register();
   }
}
