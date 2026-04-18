package vectorwing.farmersdelight.common.crafting;

import net.minecraft.class_1799;
import net.minecraft.class_9695;

public record CuttingBoardRecipeInput(class_1799 item, class_1799 tool) implements class_9695 {
   public class_1799 method_59984(int index) {
      return switch (index) {
         case 0 -> this.item;
         case 1 -> this.tool;
         default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
      };
   }

   public int method_59983() {
      return 2;
   }
}
