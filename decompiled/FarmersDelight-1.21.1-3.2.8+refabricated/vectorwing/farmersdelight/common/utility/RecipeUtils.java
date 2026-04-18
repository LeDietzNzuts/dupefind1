package vectorwing.farmersdelight.common.utility;

import com.google.common.base.Preconditions;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_310;
import net.minecraft.class_5455;
import net.minecraft.class_638;

public class RecipeUtils {
   public static class_1799 getResultItem(class_1860<?> recipe) {
      class_5455 registryAccess = ((class_638)Preconditions.checkNotNull(class_310.method_1551().field_1687, "level must not be null.")).method_30349();
      return recipe.method_8110(registryAccess);
   }
}
