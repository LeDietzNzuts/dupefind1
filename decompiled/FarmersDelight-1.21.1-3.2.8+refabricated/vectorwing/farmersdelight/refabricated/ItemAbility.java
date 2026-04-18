package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.Codec;
import java.util.Locale;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1799;
import net.minecraft.class_3489;
import net.minecraft.class_3542;
import org.jetbrains.annotations.NotNull;

public enum ItemAbility implements class_3542 {
   SWORD_DIG,
   SHOVEL_DIG,
   PICKAXE_DIG,
   SHEARS_CARVE,
   SHEARS_DIG,
   AXE_DIG,
   AXE_STRIP;

   public static final Codec<ItemAbility> CODEC = class_3542.method_28140(ItemAbility::values);

   public String method_15434() {
      return this.name().toLowerCase(Locale.ROOT);
   }

   public boolean canPerformAction(@NotNull class_1799 stack) {
      return switch (this) {
         case SWORD_DIG -> stack.method_31573(class_3489.field_42611);
         case SHOVEL_DIG -> stack.method_31573(class_3489.field_42615);
         case PICKAXE_DIG -> stack.method_31573(class_3489.field_42614);
         case SHEARS_CARVE, SHEARS_DIG -> stack.method_31573(ConventionalItemTags.SHEAR_TOOLS);
         case AXE_DIG, AXE_STRIP -> stack.method_31573(class_3489.field_42612);
      };
   }
}
