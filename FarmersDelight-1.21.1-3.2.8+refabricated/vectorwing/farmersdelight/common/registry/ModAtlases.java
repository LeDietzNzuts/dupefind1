package vectorwing.farmersdelight.common.registry;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.class_1767;
import net.minecraft.class_2960;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import org.jetbrains.annotations.Nullable;

public class ModAtlases {
   public static final class_4730 BLANK_CANVAS_SIGN_MATERIAL = new class_4730(
      class_4722.field_21708, class_2960.method_60655("farmersdelight", "entity/signs/canvas")
   );
   public static final class_4730 BLANK_HANGING_CANVAS_SIGN_MATERIAL = new class_4730(
      class_4722.field_21708, class_2960.method_60655("farmersdelight", "entity/signs/hanging/canvas")
   );
   public static final Map<class_1767, class_4730> DYED_CANVAS_SIGN_MATERIALS = Arrays.stream(class_1767.values())
      .collect(Collectors.toMap(Function.identity(), ModAtlases::createCanvasSignMaterial));
   public static final Map<class_1767, class_4730> DYED_HANGING_CANVAS_SIGN_MATERIALS = Arrays.stream(class_1767.values())
      .collect(Collectors.toMap(Function.identity(), ModAtlases::createHangingCanvasSignMaterial));

   public static class_4730 createCanvasSignMaterial(class_1767 dyeType) {
      return new class_4730(class_4722.field_21708, class_2960.method_60655("farmersdelight", "entity/signs/canvas_" + dyeType.method_7792()));
   }

   public static class_4730 createHangingCanvasSignMaterial(class_1767 dyeType) {
      return new class_4730(class_4722.field_21708, class_2960.method_60655("farmersdelight", "entity/signs/hanging/canvas_" + dyeType.method_7792()));
   }

   public static class_4730 getCanvasSignMaterial(@Nullable class_1767 dyeColor) {
      return dyeColor != null ? DYED_CANVAS_SIGN_MATERIALS.get(dyeColor) : BLANK_CANVAS_SIGN_MATERIAL;
   }

   public static class_4730 getHangingCanvasSignMaterial(@Nullable class_1767 dyeColor) {
      return dyeColor != null ? DYED_HANGING_CANVAS_SIGN_MATERIALS.get(dyeColor) : BLANK_HANGING_CANVAS_SIGN_MATERIAL;
   }
}
