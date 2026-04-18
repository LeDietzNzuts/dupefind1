package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3445;
import net.minecraft.class_3468;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class ModStats {
   public static class_2960 LOOTED_LOCATION = LootrAPI.rl("looted_stat");
   public static class_3445<class_2960> LOOTED_STAT;

   public static void registerStats() {
      class_2378.method_10230(class_7923.field_41183, LOOTED_LOCATION, LOOTED_LOCATION);
   }

   public static void load() {
      LOOTED_STAT = class_3468.field_15419.method_14956(LOOTED_LOCATION);
   }
}
