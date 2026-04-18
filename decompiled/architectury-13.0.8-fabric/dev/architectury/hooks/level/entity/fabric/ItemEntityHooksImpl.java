package dev.architectury.hooks.level.entity.fabric;

import dev.architectury.utils.value.IntValue;
import net.minecraft.class_1542;

public class ItemEntityHooksImpl {
   public static IntValue lifespan(class_1542 entity) {
      return new IntValue() {
         public void accept(int value) {
         }

         @Override
         public int getAsInt() {
            return 6000;
         }
      };
   }
}
