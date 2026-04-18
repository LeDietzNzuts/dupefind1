package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.BotanistWorkbench;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.LampJar;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Rope;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2498;
import net.minecraft.class_3619;
import net.minecraft.class_3620;
import net.minecraft.class_7923;
import net.minecraft.class_4970.class_2251;

public class BlockInit {
   public static final BookStack BOOKSTACK = register(
      "bookstack",
      new BookStack(
         class_2251.method_9637().method_31710(class_3620.field_16008).method_9629(0.2F, 0.2F).method_9626(SoundInit.BOOKSTACK_SOUNDS).method_22488()
      )
   );
   public static final Rope ROPE = register(
      "rope",
      new Rope(class_2251.method_9637().method_31710(class_3620.field_16008).method_9629(0.2F, 0.2F).method_9626(class_2498.field_11543).method_22488())
   );
   public static final HangingPot HANGING_POT = register(
      "hanging_pot",
      new HangingPot(
         class_2251.method_9637()
            .method_31710(class_3620.field_15992)
            .method_22488()
            .method_9629(0.1F, 0.1F)
            .method_9626(class_2498.field_11544)
            .method_9631(state -> {
               if ((Integer)state.method_11654(HangingPot.POTFLOWER) == 15) {
                  return 7;
               } else {
                  return state.method_11654(HangingPot.POTFLOWER) == 22 ? 14 : 0;
               }
            })
      )
   );
   public static final Trellis OAK_TRELLIS = registerTrellis("oak_trellis");
   public static final Trellis SPRUCE_TRELLIS = registerTrellis("spruce_trellis");
   public static final Trellis BIRCH_TRELLIS = registerTrellis("birch_trellis");
   public static final Trellis JUNGLE_TRELLIS = registerTrellis("jungle_trellis");
   public static final Trellis ACACIA_TRELLIS = registerTrellis("acacia_trellis");
   public static final Trellis DARK_OAK_TRELLIS = registerTrellis("dark_oak_trellis");
   public static final Trellis MANGROVE_TRELLIS = registerTrellis("mangrove_trellis");
   public static final Trellis CRIMSON_TRELLIS = registerTrellis("crimson_trellis");
   public static final Trellis CHERRY_TRELLIS = registerTrellis("cherry_trellis");
   public static final Trellis WARPED_TRELLIS = registerTrellis("warped_trellis");
   public static final LampLightBulb LAMP_LIGHT_BULB = register(
      "lamp_light_bulb",
      new LampLightBulb(
         class_2251.method_9637()
            .method_31710(class_3620.field_16005)
            .method_22488()
            .method_9629(0.2F, 0.2F)
            .method_9626(class_2498.field_17734)
            .method_9631(state -> state.method_11654(LampLightBulb.ON) ? 14 : 0)
      )
   );
   public static final LampBamboo LAMP_BAMBOO = register(
      "lamp_bamboo",
      new LampBamboo(
         class_2251.method_9637()
            .method_31710(class_3620.field_15996)
            .method_22488()
            .method_9629(0.1F, 0.1F)
            .method_9626(class_2498.field_16498)
            .method_9631(state -> state.method_11654(LampBamboo.ON) ? 14 : 0)
      )
   );
   public static final LampJar LAMP_JAR = register(
      "lamp_jar",
      new LampJar(
         class_2251.method_9637()
            .method_31710(class_3620.field_16008)
            .method_22488()
            .method_9629(0.05F, 0.05F)
            .method_9626(class_2498.field_11537)
            .method_9631(state -> {
               int fill = (Integer)state.method_11654(LampJar.FILL_LEVEL);

               return switch (fill) {
                  case 5 -> 8;
                  case 10 -> 11;
                  case 15 -> 14;
                  default -> 0;
               };
            })
      )
   );
   public static final LampCandelabra LAMP_CANDELABRA = registerLampCandelabra("lamp_candelabra");
   public static final LampCandelabra LAMP_CANDELABRA_LIGHT_BLUE = registerLampCandelabra("lamp_candelabra_light_blue");
   public static final LampCandelabra LAMP_CANDELABRA_LIGHT_GRAY = registerLampCandelabra("lamp_candelabra_light_gray");
   public static final LampCandelabra LAMP_CANDELABRA_BLACK = registerLampCandelabra("lamp_candelabra_black");
   public static final LampCandelabra LAMP_CANDELABRA_BLUE = registerLampCandelabra("lamp_candelabra_blue");
   public static final LampCandelabra LAMP_CANDELABRA_BROWN = registerLampCandelabra("lamp_candelabra_brown");
   public static final LampCandelabra LAMP_CANDELABRA_CYAN = registerLampCandelabra("lamp_candelabra_cyan");
   public static final LampCandelabra LAMP_CANDELABRA_GRAY = registerLampCandelabra("lamp_candelabra_gray");
   public static final LampCandelabra LAMP_CANDELABRA_GREEN = registerLampCandelabra("lamp_candelabra_green");
   public static final LampCandelabra LAMP_CANDELABRA_LIME = registerLampCandelabra("lamp_candelabra_lime");
   public static final LampCandelabra LAMP_CANDELABRA_MAGENTA = registerLampCandelabra("lamp_candelabra_magenta");
   public static final LampCandelabra LAMP_CANDELABRA_ORANGE = registerLampCandelabra("lamp_candelabra_orange");
   public static final LampCandelabra LAMP_CANDELABRA_PINK = registerLampCandelabra("lamp_candelabra_pink");
   public static final LampCandelabra LAMP_CANDELABRA_PURPLE = registerLampCandelabra("lamp_candelabra_purple");
   public static final LampCandelabra LAMP_CANDELABRA_RED = registerLampCandelabra("lamp_candelabra_red");
   public static final LampCandelabra LAMP_CANDELABRA_WHITE = registerLampCandelabra("lamp_candelabra_white");
   public static final LampCandelabra LAMP_CANDELABRA_YELLOW = registerLampCandelabra("lamp_candelabra_yellow");
   public static final Blinds SPRUCE_BLINDS = registerBlinds("spruce_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds DARK_OAK_BLINDS = registerBlinds("dark_oak_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds CRIMSON_BLINDS = registerBlinds("crimson_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds CHERRY_BLINDS = registerBlinds("cherry_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds ACACIA_BLINDS = registerBlinds("acacia_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds JUNGLE_BLINDS = registerBlinds("jungle_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds OAK_BLINDS = registerBlinds("oak_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds BIRCH_BLINDS = registerBlinds("birch_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds WARPED_BLINDS = registerBlinds("warped_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds MANGROVE_BLINDS = registerBlinds("mangrove_blinds", class_3620.field_15996, class_2498.field_11547);
   public static final Blinds IRON_BLINDS = registerBlinds("iron_blinds", class_3620.field_16005, class_2498.field_24119);
   public static final PictureFrame SPRUCE_PICTURE_FRAME = registerPictureFrame("spruce_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame DARK_OAK_PICTURE_FRAME = registerPictureFrame("dark_oak_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame CRIMSON_PICTURE_FRAME = registerPictureFrame("crimson_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame CHERRY_PICTURE_FRAME = registerPictureFrame("cherry_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame ACACIA_PICTURE_FRAME = registerPictureFrame("acacia_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame JUNGLE_PICTURE_FRAME = registerPictureFrame("jungle_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame OAK_PICTURE_FRAME = registerPictureFrame("oak_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame BIRCH_PICTURE_FRAME = registerPictureFrame("birch_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame WARPED_PICTURE_FRAME = registerPictureFrame("warped_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame MANGROVE_PICTURE_FRAME = registerPictureFrame("mangrove_picture_frame", class_3620.field_15996, class_2498.field_11547);
   public static final PictureFrame QUARTZ_PICTURE_FRAME = registerPictureFrame("quartz_picture_frame", class_3620.field_16025, class_2498.field_11544);
   public static final BotanistWorkbench BOTANIST_WORKBENCH = register(
      "botanist_workbench", new BotanistWorkbench(class_2251.method_9630(class_2246.field_9980))
   );

   private static <T extends class_2248> T register(String name, T block) {
      return (T)class_2378.method_10230(class_7923.field_41175, Beautify.id(name), block);
   }

   private static LampCandelabra registerLampCandelabra(String name) {
      return register(
         name,
         new LampCandelabra(
            class_2251.method_9637()
               .method_31710(class_3620.field_16005)
               .method_22488()
               .method_9629(0.2F, 0.2F)
               .method_9626(class_2498.field_17734)
               .method_9631(state -> state.method_11654(LampCandelabra.ON) ? 14 : 0)
         )
      );
   }

   private static Trellis registerTrellis(String name) {
      return register(
         name,
         new Trellis(
            class_2251.method_9637()
               .method_31710(class_3620.field_15996)
               .method_31710(class_3620.field_15996)
               .method_9629(0.3F, 0.3F)
               .method_9626(class_2498.field_11542)
               .method_22488()
         )
      );
   }

   private static PictureFrame registerPictureFrame(String name, class_3620 color, class_2498 type) {
      return register(
         name,
         new PictureFrame(
            class_2251.method_9637()
               .method_31710(color)
               .method_22488()
               .method_9629(0.1F, 0.1F)
               .method_9626(type)
               .method_22488()
               .method_50012(class_3619.field_15971)
         )
      );
   }

   private static Blinds registerBlinds(String name, class_3620 color, class_2498 type) {
      return register(name, new Blinds(class_2251.method_9637().method_31710(color).method_22488().method_9629(0.4F, 0.4F).method_9626(type)));
   }

   public static void registerFlammableBlock() {
      FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
      flammableRegistry.add(OAK_TRELLIS, 30, 20);
      flammableRegistry.add(SPRUCE_TRELLIS, 30, 20);
      flammableRegistry.add(BIRCH_TRELLIS, 30, 20);
      flammableRegistry.add(JUNGLE_TRELLIS, 30, 20);
      flammableRegistry.add(ACACIA_TRELLIS, 30, 20);
      flammableRegistry.add(DARK_OAK_TRELLIS, 30, 20);
      flammableRegistry.add(MANGROVE_TRELLIS, 30, 20);
      flammableRegistry.add(CHERRY_TRELLIS, 30, 20);
   }
}
