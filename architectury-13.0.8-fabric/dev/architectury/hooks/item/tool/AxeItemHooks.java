package dev.architectury.hooks.item.tool;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import net.minecraft.class_1743;
import net.minecraft.class_2248;
import net.minecraft.class_2465;

public final class AxeItemHooks {
   private AxeItemHooks() {
   }

   public static void addStrippable(class_2248 input, class_2248 result) {
      if (!input.method_9564().method_28498(class_2465.field_11459)) {
         throw new IllegalArgumentException("Input block is missing required 'AXIS' property!");
      } else if (!result.method_9564().method_28498(class_2465.field_11459)) {
         throw new IllegalArgumentException("Result block is missing required 'AXIS' property!");
      } else {
         if (class_1743.field_7898 instanceof ImmutableMap) {
            class_1743.field_7898 = new HashMap(class_1743.field_7898);
         }

         class_1743.field_7898.put(input, result);
      }
   }
}
