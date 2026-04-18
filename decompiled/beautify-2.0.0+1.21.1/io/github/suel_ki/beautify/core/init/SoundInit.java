package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.class_2378;
import net.minecraft.class_2498;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public class SoundInit {
   public static final class_3414 BOOKSTACK_NEXT = registerSoundEvent("block.bookstack_next");
   public static final class_3414 BOOKSTACK_BREAK = registerSoundEvent("block.bookstack_break");
   public static final class_3414 BOOKSTACK_STEP = registerSoundEvent("block.bookstack_step");
   public static final class_3414 BOOKSTACK_PLACE = registerSoundEvent("block.bookstack_place");
   public static final class_3414 BOOKSTACK_HIT = registerSoundEvent("block.bookstack_hit");
   public static final class_3414 BOOKSTACK_FALL = registerSoundEvent("block.bookstack_fall");
   public static final class_2498 BOOKSTACK_SOUNDS = new class_2498(1.0F, 1.0F, BOOKSTACK_BREAK, BOOKSTACK_STEP, BOOKSTACK_PLACE, BOOKSTACK_HIT, BOOKSTACK_FALL);
   public static final class_3414 BLINDS_OPEN = registerSoundEvent("block.blinds_open");
   public static final class_3414 BLINDS_CLOSE = registerSoundEvent("block.blinds_close");

   private static class_3414 registerSoundEvent(String name) {
      return (class_3414)class_2378.method_10230(class_7923.field_41172, Beautify.id(name), class_3414.method_47908(Beautify.id(name)));
   }
}
