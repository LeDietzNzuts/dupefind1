package vectorwing.farmersdelight.integration.jei.resource;

import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1867;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_3955;
import net.minecraft.class_7710;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.registry.ModItems;

public class DoughRecipeMaker {
   public static List<class_8786<class_3955>> createRecipe() {
      class_2371<class_1856> inputs = class_2371.method_10212(
         class_1856.field_9017,
         new class_1856[]{class_1856.method_8091(new class_1935[]{class_1802.field_8861}), class_1856.method_8091(new class_1935[]{class_1802.field_8705})}
      );
      class_1799 output = new class_1799((class_1935)ModItems.WHEAT_DOUGH.get());
      String path = "farmersdelight.dough";
      class_2960 id = class_2960.method_60654(path);
      return List.of(new class_8786(id, new class_1867(path, class_7710.field_40251, output, inputs)));
   }
}
