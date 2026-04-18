package net.kikoz.mcwfurnitures.init;

import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public class SoundsInit {
   public static class_3414 DRAWER_OPEN;
   public static class_3414 DRAWER_CLOSE;
   public static class_3414 CABINET_OPEN;
   public static class_3414 CABINET_CLOSE;

   public static void registerSoundEvents() {
      DRAWER_OPEN = registerSoundEvent("drawer_open");
      DRAWER_CLOSE = registerSoundEvent("drawer_close");
      CABINET_OPEN = registerSoundEvent("cabinet_open");
      CABINET_CLOSE = registerSoundEvent("cabinet_close");
   }

   private static class_3414 registerSoundEvent(String name) {
      class_2960 id = class_2960.method_60655("mcwfurnitures", name);
      return (class_3414)class_2378.method_10230(class_7923.field_41172, id, class_3414.method_47908(id));
   }
}
