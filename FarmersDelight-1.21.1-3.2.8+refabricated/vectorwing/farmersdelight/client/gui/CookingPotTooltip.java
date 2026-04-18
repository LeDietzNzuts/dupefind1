package vectorwing.farmersdelight.client.gui;

import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import net.minecraft.class_327.class_6415;
import net.minecraft.class_4597.class_4598;
import org.joml.Matrix4f;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class CookingPotTooltip implements class_5684 {
   private static final int ITEM_SIZE = 16;
   private static final int MARGIN = 4;
   private final int textSpacing = 9 + 1;
   private final class_1799 mealStack;

   public CookingPotTooltip(CookingPotTooltip.CookingPotTooltipComponent tooltip) {
      this.mealStack = tooltip.mealStack;
   }

   public int method_32661() {
      return this.mealStack.method_7960() ? this.textSpacing : this.textSpacing + 16;
   }

   public int method_32664(class_327 font) {
      if (!this.mealStack.method_7960()) {
         class_5250 textServingsOf = this.mealStack.method_7947() == 1
            ? TextUtils.getTranslation("tooltip.cooking_pot.single_serving")
            : TextUtils.getTranslation("tooltip.cooking_pot.many_servings", this.mealStack.method_7947());
         return Math.max(font.method_27525(textServingsOf), font.method_27525(this.mealStack.method_7964()) + 20);
      } else {
         return font.method_27525(TextUtils.getTranslation("tooltip.cooking_pot.empty"));
      }
   }

   public void method_32666(class_327 font, int mouseX, int mouseY, class_332 gui) {
      if (!this.mealStack.method_7960()) {
         gui.method_51428(this.mealStack, mouseX, mouseY + this.textSpacing, 0);
      }
   }

   public void method_32665(class_327 font, int x, int y, Matrix4f matrix4f, class_4598 bufferSource) {
      Integer color = class_124.field_1080.method_532();
      int gray = color == null ? -1 : color;
      if (!this.mealStack.method_7960()) {
         class_5250 textServingsOf = this.mealStack.method_7947() == 1
            ? TextUtils.getTranslation("tooltip.cooking_pot.single_serving")
            : TextUtils.getTranslation("tooltip.cooking_pot.many_servings", this.mealStack.method_7947());
         font.method_30882(textServingsOf, x, y, gray, true, matrix4f, bufferSource, class_6415.field_33993, 0, 15728880);
         font.method_30882(
            this.mealStack.method_7964(), x + 16 + 4, y + this.textSpacing + 4, -1, true, matrix4f, bufferSource, class_6415.field_33993, 0, 15728880
         );
      } else {
         class_5250 textEmpty = TextUtils.getTranslation("tooltip.cooking_pot.empty");
         font.method_30882(textEmpty, x, y, gray, true, matrix4f, bufferSource, class_6415.field_33993, 0, 15728880);
      }
   }

   public record CookingPotTooltipComponent(class_1799 mealStack) implements class_5632 {
   }
}
