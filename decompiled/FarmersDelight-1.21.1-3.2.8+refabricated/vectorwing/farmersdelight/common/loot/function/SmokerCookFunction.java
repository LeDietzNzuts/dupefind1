package vectorwing.farmersdelight.common.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_120;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_2960;
import net.minecraft.class_3862;
import net.minecraft.class_3956;
import net.minecraft.class_47;
import net.minecraft.class_5339;
import net.minecraft.class_5341;
import net.minecraft.class_6328;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.registry.ModLootFunctions;

@class_6328
public class SmokerCookFunction extends class_120 {
   public static final class_2960 ID = class_2960.method_60655("farmersdelight", "smoker_cook");
   public static final MapCodec<SmokerCookFunction> CODEC = RecordCodecBuilder.mapCodec(
      p_298131_ -> method_53344(p_298131_).apply(p_298131_, SmokerCookFunction::new)
   );

   protected SmokerCookFunction(List<class_5341> conditionsIn) {
      super(conditionsIn);
   }

   protected class_1799 method_522(class_1799 stack, class_47 context) {
      if (stack.method_7960()) {
         return stack;
      } else {
         Optional<class_8786<class_3862>> recipe = context.method_299()
            .method_8433()
            .method_30027(class_3956.field_17548)
            .stream()
            .filter(r -> ((class_1856)((class_3862)r.comp_1933()).method_8117().get(0)).method_8093(stack))
            .findFirst();
         if (recipe.isPresent()) {
            class_1799 result = ((class_3862)recipe.get().comp_1933()).method_8110(context.method_299().method_30349()).method_7972();
            result.method_7939(result.method_7947() * stack.method_7947());
            return result;
         } else {
            return stack;
         }
      }
   }

   public class_5339<SmokerCookFunction> method_29321() {
      return ModLootFunctions.SMOKER_COOK.get();
   }
}
