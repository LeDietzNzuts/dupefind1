package dev.architectury.hooks.level.entity.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1297;
import net.minecraft.class_4076;
import net.minecraft.class_5569;
import net.minecraft.class_1297.class_5529;

public class EntityHooksImpl {
   public static class_5569 wrapEntityInLevelCallback(class_1297 entity, class_5569 callback) {
      if (callback == class_5569.field_27243) {
         return callback;
      } else {
         return callback == null
            ? callback
            : new class_5569() {
               private long lastSectionKey = class_4076.method_33706(entity.method_24515());

               public void method_31749() {
                  callback.method_31749();
                  long currentSectionKey = class_4076.method_33706(entity.method_24515());
                  if (currentSectionKey != this.lastSectionKey) {
                     EntityEvent.ENTER_SECTION
                        .invoker()
                        .enterSection(
                           entity,
                           class_4076.method_18686(this.lastSectionKey),
                           class_4076.method_18689(this.lastSectionKey),
                           class_4076.method_18690(this.lastSectionKey),
                           class_4076.method_18686(currentSectionKey),
                           class_4076.method_18689(currentSectionKey),
                           class_4076.method_18690(currentSectionKey)
                        );
                     this.lastSectionKey = currentSectionKey;
                  }
               }

               public void method_31750(class_5529 removalReason) {
                  callback.method_31750(removalReason);
               }
            };
      }
   }
}
