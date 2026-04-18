package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import net.minecraft.class_5632;
import net.minecraft.class_7923;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.NotNull;

public final class ItemInit {
   public static final Map<class_1792, class_2960> ITEMS = new LinkedHashMap<>();
   public static final class_1747 OAK_TRELLIS_ITEM = registerTrellis("oak_trellis", BlockInit.OAK_TRELLIS);
   public static final class_1747 SPRUCE_TRELLIS_ITEM = registerTrellis("spruce_trellis", BlockInit.SPRUCE_TRELLIS);
   public static final class_1747 BIRCH_TRELLIS_ITEM = registerTrellis("birch_trellis", BlockInit.BIRCH_TRELLIS);
   public static final class_1747 JUNGLE_TRELLIS_ITEM = registerTrellis("jungle_trellis", BlockInit.JUNGLE_TRELLIS);
   public static final class_1747 ACACIA_TRELLIS_ITEM = registerTrellis("acacia_trellis", BlockInit.ACACIA_TRELLIS);
   public static final class_1747 DARK_OAK_TRELLIS_ITEM = registerTrellis("dark_oak_trellis", BlockInit.DARK_OAK_TRELLIS);
   public static final class_1747 MANGROVE_TRELLIS_ITEM = registerTrellis("mangrove_trellis", BlockInit.MANGROVE_TRELLIS);
   public static final class_1747 CRIMSON_TRELLIS_ITEM = registerTrellis("crimson_trellis", BlockInit.CRIMSON_TRELLIS);
   public static final class_1747 CHERRY_TRELLIS_ITEM = registerTrellis("cherry_trellis", BlockInit.CHERRY_TRELLIS);
   public static final class_1747 WARPED_TRELLIS_ITEM = registerTrellis("warped_trellis", BlockInit.WARPED_TRELLIS);
   public static final class_1747 OAK_BLINDS_ITEM = registerBlockItem("oak_blinds", BlockInit.OAK_BLINDS);
   public static final class_1747 SPRUCE_BLINDS_ITEM = registerBlockItem("spruce_blinds", BlockInit.SPRUCE_BLINDS);
   public static final class_1747 BIRCH_BLINDS_ITEM = registerBlockItem("birch_blinds", BlockInit.BIRCH_BLINDS);
   public static final class_1747 JUNGLE_BLINDS_ITEM = registerBlockItem("jungle_blinds", BlockInit.JUNGLE_BLINDS);
   public static final class_1747 ACACIA_BLINDS_ITEM = registerBlockItem("acacia_blinds", BlockInit.ACACIA_BLINDS);
   public static final class_1747 DARK_OAK_BLINDS_ITEM = registerBlockItem("dark_oak_blinds", BlockInit.DARK_OAK_BLINDS);
   public static final class_1747 CRIMSON_BLINDS_ITEM = registerBlockItem("crimson_blinds", BlockInit.CRIMSON_BLINDS);
   public static final class_1747 CHERRY_BLINDS_ITEM = registerBlockItem("cherry_blinds", BlockInit.CHERRY_BLINDS);
   public static final class_1747 WARPED_BLINDS_ITEM = registerBlockItem("warped_blinds", BlockInit.WARPED_BLINDS);
   public static final class_1747 MANGROVE_BLINDS_ITEM = registerBlockItem("mangrove_blinds", BlockInit.MANGROVE_BLINDS);
   public static final class_1747 IRON_BLINDS_ITEM = registerBlockItem("iron_blinds", BlockInit.IRON_BLINDS);
   public static final class_1747 OAK_PICTURE_FRAME_ITEM = registerBlockItem("oak_picture_frame", BlockInit.OAK_PICTURE_FRAME);
   public static final class_1747 SPRUCE_PICTURE_FRAME_ITEM = registerBlockItem("spruce_picture_frame", BlockInit.SPRUCE_PICTURE_FRAME);
   public static final class_1747 BIRCH_PICTURE_FRAME_ITEM = registerBlockItem("birch_picture_frame", BlockInit.BIRCH_PICTURE_FRAME);
   public static final class_1747 JUNGLE_PICTURE_FRAME_ITEM = registerBlockItem("jungle_picture_frame", BlockInit.JUNGLE_PICTURE_FRAME);
   public static final class_1747 ACACIA_PICTURE_FRAME_ITEM = registerBlockItem("acacia_picture_frame", BlockInit.ACACIA_PICTURE_FRAME);
   public static final class_1747 DARK_OAK_PICTURE_FRAME_ITEM = registerBlockItem("dark_oak_picture_frame", BlockInit.DARK_OAK_PICTURE_FRAME);
   public static final class_1747 CRIMSON_PICTURE_FRAME_ITEM = registerBlockItem("crimson_picture_frame", BlockInit.CRIMSON_PICTURE_FRAME);
   public static final class_1747 CHERRY_PICTURE_FRAME_ITEM = registerBlockItem("cherry_picture_frame", BlockInit.CHERRY_PICTURE_FRAME);
   public static final class_1747 WARPED_PICTURE_FRAME_ITEM = registerBlockItem("warped_picture_frame", BlockInit.WARPED_PICTURE_FRAME);
   public static final class_1747 MANGROVE_PICTURE_FRAME_ITEM = registerBlockItem("mangrove_picture_frame", BlockInit.MANGROVE_PICTURE_FRAME);
   public static final class_1747 QUARTZ_PICTURE_FRAME_ITEM = registerBlockItem("quartz_picture_frame", BlockInit.QUARTZ_PICTURE_FRAME);
   public static class_1747 ROPE_ITEM = registerBlockItem("rope", BlockInit.ROPE);
   public static final class_1747 HANGING_POT_ITEM = register("hanging_pot", new class_1747(BlockInit.HANGING_POT, new class_1793()) {
      public Optional<class_5632> method_32346(@NotNull class_1799 stack) {
         if (class_437.method_25441()) {
            List<class_1799> plants = HangingPot.VALID_FLOWERS.stream().filter(item -> item != class_1802.field_8162).<class_1799>map(class_1799::new).toList();
            return Optional.of(new PlantableItemStackTooltip(plants));
         } else {
            return super.method_32346(stack);
         }
      }
   });
   public static final class_1747 BOOKSTACK_ITEM = registerBlockItem("bookstack", BlockInit.BOOKSTACK);
   public static final class_1747 LAMP_LIGHT_BULB_ITEM = registerBlockItem("lamp_light_bulb", BlockInit.LAMP_LIGHT_BULB);
   public static final class_1747 LAMP_BAMBOO_ITEM = registerBlockItem("lamp_bamboo", BlockInit.LAMP_BAMBOO);
   public static final class_1747 LAMP_JAR_ITEM = registerBlockItem("lamp_jar", BlockInit.LAMP_JAR);
   public static final class_1747 LAMP_CANDELABRA_ITEM = registerBlockItem("lamp_candelabra", BlockInit.LAMP_CANDELABRA);
   public static final class_1747 LAMP_CANDELABRA_LIGHT_BLUE_ITEM = registerBlockItem("lamp_candelabra_light_blue", BlockInit.LAMP_CANDELABRA_LIGHT_BLUE);
   public static final class_1747 LAMP_CANDELABRA_LIGHT_GRAY_ITEM = registerBlockItem("lamp_candelabra_light_gray", BlockInit.LAMP_CANDELABRA_LIGHT_GRAY);
   public static final class_1747 LAMP_CANDELABRA_BLACK_ITEM = registerBlockItem("lamp_candelabra_black", BlockInit.LAMP_CANDELABRA_BLACK);
   public static final class_1747 LAMP_CANDELABRA_BLUE_ITEM = registerBlockItem("lamp_candelabra_blue", BlockInit.LAMP_CANDELABRA_BLUE);
   public static final class_1747 LAMP_CANDELABRA_BROWN_ITEM = registerBlockItem("lamp_candelabra_brown", BlockInit.LAMP_CANDELABRA_BROWN);
   public static final class_1747 LAMP_CANDELABRA_CYAN_ITEM = registerBlockItem("lamp_candelabra_cyan", BlockInit.LAMP_CANDELABRA_CYAN);
   public static final class_1747 LAMP_CANDELABRA_GRAY_ITEM = registerBlockItem("lamp_candelabra_gray", BlockInit.LAMP_CANDELABRA_GRAY);
   public static final class_1747 LAMP_CANDELABRA_GREEN_ITEM = registerBlockItem("lamp_candelabra_green", BlockInit.LAMP_CANDELABRA_GREEN);
   public static final class_1747 LAMP_CANDELABRA_LIME_ITEM = registerBlockItem("lamp_candelabra_lime", BlockInit.LAMP_CANDELABRA_LIME);
   public static final class_1747 LAMP_CANDELABRA_MAGENTA_ITEM = registerBlockItem("lamp_candelabra_magenta", BlockInit.LAMP_CANDELABRA_MAGENTA);
   public static final class_1747 LAMP_CANDELABRA_ORANGE_ITEM = registerBlockItem("lamp_candelabra_orange", BlockInit.LAMP_CANDELABRA_ORANGE);
   public static final class_1747 LAMP_CANDELABRA_PINK_ITEM = registerBlockItem("lamp_candelabra_pink", BlockInit.LAMP_CANDELABRA_PINK);
   public static final class_1747 LAMP_CANDELABRA_PURPLE_ITEM = registerBlockItem("lamp_candelabra_purple", BlockInit.LAMP_CANDELABRA_PURPLE);
   public static final class_1747 LAMP_CANDELABRA_RED_ITEM = registerBlockItem("lamp_candelabra_red", BlockInit.LAMP_CANDELABRA_RED);
   public static final class_1747 LAMP_CANDELABRA_WHITE_ITEM = registerBlockItem("lamp_candelabra_white", BlockInit.LAMP_CANDELABRA_WHITE);
   public static final class_1747 LAMP_CANDELABRA_YELLOW_ITEM = registerBlockItem("lamp_candelabra_yellow", BlockInit.LAMP_CANDELABRA_YELLOW);
   public static final class_1747 BOTANIST_WORKBENCH_ITEM = registerBlockItem("botanist_workbench", BlockInit.BOTANIST_WORKBENCH);

   private static <T extends class_1792> T register(String name, T item) {
      ITEMS.put(item, Beautify.id(name));
      return (T)class_2378.method_10230(class_7923.field_41178, Beautify.id(name), item);
   }

   private static class_1747 registerBlockItem(String name, class_2248 block) {
      return register(name, new class_1747(block, new class_1793()));
   }

   private static class_1747 registerTrellis(String name, class_2248 block) {
      return register(name, new class_1747(block, new class_1793()) {
         public Optional<class_5632> method_32346(@NotNull class_1799 stack) {
            if (class_437.method_25441()) {
               List<class_1799> plants = Trellis.VALID_FLOWERS.stream().filter(item -> item != class_1802.field_8162).<class_1799>map(class_1799::new).toList();
               return Optional.of(new PlantableItemStackTooltip(plants));
            } else {
               return super.method_32346(stack);
            }
         }
      });
   }

   public static void registerFuel() {
      FuelRegistry.INSTANCE.add(OAK_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(SPRUCE_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(BIRCH_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(JUNGLE_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(ACACIA_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(DARK_OAK_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(MANGROVE_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(CHERRY_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(CRIMSON_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(WARPED_TRELLIS_ITEM, 300);
      FuelRegistry.INSTANCE.add(OAK_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(SPRUCE_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(BIRCH_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(JUNGLE_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(ACACIA_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(DARK_OAK_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(CHERRY_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(MANGROVE_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(CRIMSON_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(WARPED_BLINDS_ITEM, 300);
      FuelRegistry.INSTANCE.add(OAK_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(SPRUCE_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(BIRCH_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(JUNGLE_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(ACACIA_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(DARK_OAK_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(MANGROVE_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(CHERRY_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(CRIMSON_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(WARPED_PICTURE_FRAME_ITEM, 300);
      FuelRegistry.INSTANCE.add(ROPE_ITEM, 100);
   }
}
