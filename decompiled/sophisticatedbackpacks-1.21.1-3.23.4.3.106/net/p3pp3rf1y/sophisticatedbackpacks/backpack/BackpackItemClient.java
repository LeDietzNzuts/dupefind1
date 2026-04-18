package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_5632;

public class BackpackItemClient {
   @Nullable
   public static class_5632 getTooltipImage(class_1799 stack) {
      class_310 mc = class_310.method_1551();
      return !class_437.method_25442() && (mc.field_1724 == null || mc.field_1724.field_7512.method_34255().method_7960())
         ? null
         : new BackpackItem.BackpackContentsTooltip(stack);
   }
}
