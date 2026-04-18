package dev.architectury.hooks.item.tool;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import net.minecraft.class_1821;
import net.minecraft.class_2248;
import net.minecraft.class_2680;

public final class ShovelItemHooks {
   private ShovelItemHooks() {
   }

   public static void addFlattenable(class_2248 input, class_2680 result) {
      if (class_1821.field_8912 instanceof ImmutableMap) {
         class_1821.field_8912 = new HashMap(class_1821.field_8912);
      }

      class_1821.field_8912.put(input, result);
   }
}
