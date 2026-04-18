package vectorwing.farmersdelight.common.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.class_120;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_47;
import net.minecraft.class_5339;
import net.minecraft.class_5341;
import net.minecraft.class_6328;
import net.minecraft.class_120.class_121;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.registry.ModLootFunctions;

@class_6328
public class CopySkilletFunction extends class_120 {
   public static final class_2960 ID = class_2960.method_60655("farmersdelight", "copy_skillet");
   public static final MapCodec<CopySkilletFunction> CODEC = RecordCodecBuilder.mapCodec(
      p_298131_ -> method_53344(p_298131_).apply(p_298131_, CopySkilletFunction::new)
   );

   private CopySkilletFunction(List<class_5341> conditions) {
      super(conditions);
   }

   public static class_121<?> builder() {
      return method_520(CopySkilletFunction::new);
   }

   protected class_1799 method_522(class_1799 stack, class_47 context) {
      class_2586 tile = (class_2586)context.method_296(class_181.field_1228);
      if (tile instanceof SkilletBlockEntity blockEntity) {
         stack = blockEntity.getSkilletAsItem();
      }

      return stack;
   }

   public class_5339<CopySkilletFunction> method_29321() {
      return ModLootFunctions.COPY_SKILLET.get();
   }
}
