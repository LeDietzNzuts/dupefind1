package ewewukek.musketmod;

import java.util.function.Consumer;
import net.minecraft.class_3414;

public class Sounds {
   public static final class_3414 MUSKET_LOAD_0 = class_3414.method_47908(MusketMod.resource("musket_load0"));
   public static final class_3414 MUSKET_LOAD_1 = class_3414.method_47908(MusketMod.resource("musket_load1"));
   public static final class_3414 MUSKET_LOAD_2 = class_3414.method_47908(MusketMod.resource("musket_load2"));
   public static final class_3414 MUSKET_READY = class_3414.method_47908(MusketMod.resource("musket_ready"));
   public static final class_3414 MUSKET_FIRE = class_3414.method_47908(MusketMod.resource("musket_fire"));
   public static final class_3414 BLUNDERBUSS_FIRE = class_3414.method_47908(MusketMod.resource("blunderbuss_fire"));
   public static final class_3414 BLUNDERBUSS_FIRE_FLAME = class_3414.method_47908(MusketMod.resource("blunderbuss_fire_flame"));
   public static final class_3414 PISTOL_FIRE = class_3414.method_47908(MusketMod.resource("pistol_fire"));
   public static final class_3414 DISPENSER_FIRE = class_3414.method_47908(MusketMod.resource("dispenser_fire"));
   public static final class_3414 BULLET_FLY_BY = class_3414.method_47908(MusketMod.resource("bullet_fly_by"));
   public static final class_3414 BULLET_WATER_HIT = class_3414.method_47908(MusketMod.resource("bullet_water_hit"));

   public static void register(Consumer<class_3414> helper) {
      helper.accept(MUSKET_LOAD_0);
      helper.accept(MUSKET_LOAD_1);
      helper.accept(MUSKET_LOAD_2);
      helper.accept(MUSKET_READY);
      helper.accept(MUSKET_FIRE);
      helper.accept(BLUNDERBUSS_FIRE);
      helper.accept(BLUNDERBUSS_FIRE_FLAME);
      helper.accept(PISTOL_FIRE);
      helper.accept(DISPENSER_FIRE);
      helper.accept(BULLET_FLY_BY);
      helper.accept(BULLET_WATER_HIT);
   }
}
