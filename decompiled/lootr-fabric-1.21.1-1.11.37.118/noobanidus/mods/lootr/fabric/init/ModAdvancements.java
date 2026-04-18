package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.advancement.AdvancementTrigger;
import noobanidus.mods.lootr.common.advancement.ContainerTrigger;
import noobanidus.mods.lootr.common.advancement.LootedStatTrigger;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class ModAdvancements {
   public static final class_2960 CHEST_LOCATION = LootrAPI.rl("chest_opened");
   public static final class_2960 BARREL_LOCATION = LootrAPI.rl("barrel_opened");
   public static final class_2960 CART_LOCATION = LootrAPI.rl("cart_opened");
   public static final class_2960 SHULKER_LOCATION = LootrAPI.rl("shulker_opened");
   public static final class_2960 ADVANCEMENT_LOCATION = LootrAPI.rl("advancement");
   public static final class_2960 SCORE_LOCATION = LootrAPI.rl("score");
   public static final class_2960 GRAVEL_LOCATION = LootrAPI.rl("gravel_brushed");
   public static final class_2960 SAND_LOCATION = LootrAPI.rl("sand_brushed");
   public static final class_2960 POT_OPENED = LootrAPI.rl("pot_opened");
   public static final class_2960 ITEM_FRAME_LOCATION = LootrAPI.rl("item_frame_looted");
   public static ContainerTrigger CHEST = null;
   public static ContainerTrigger BARREL = null;
   public static ContainerTrigger CART = null;
   public static ContainerTrigger SHULKER = null;
   public static ContainerTrigger GRAVEL = null;
   public static ContainerTrigger SAND = null;
   public static ContainerTrigger POT = null;
   public static LootedStatTrigger SCORE = null;
   public static AdvancementTrigger ADVANCEMENT = null;
   public static ContainerTrigger ITEM_FRAME = null;

   public static void registerAdvancements() {
      ADVANCEMENT = (AdvancementTrigger)class_2378.method_10230(class_7923.field_47496, ADVANCEMENT_LOCATION, new AdvancementTrigger());
      CHEST = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, CHEST_LOCATION, new ContainerTrigger());
      BARREL = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, BARREL_LOCATION, new ContainerTrigger());
      CART = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, CART_LOCATION, new ContainerTrigger());
      SHULKER = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, SHULKER_LOCATION, new ContainerTrigger());
      SCORE = (LootedStatTrigger)class_2378.method_10230(class_7923.field_47496, SCORE_LOCATION, new LootedStatTrigger());
      GRAVEL = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, GRAVEL_LOCATION, new ContainerTrigger());
      SAND = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, SAND_LOCATION, new ContainerTrigger());
      POT = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, POT_OPENED, new ContainerTrigger());
      ITEM_FRAME = (ContainerTrigger)class_2378.method_10230(class_7923.field_47496, ITEM_FRAME_LOCATION, new ContainerTrigger());
   }
}
