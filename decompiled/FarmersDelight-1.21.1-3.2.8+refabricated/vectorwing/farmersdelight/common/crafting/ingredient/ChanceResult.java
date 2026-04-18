package vectorwing.farmersdelight.common.crafting.ingredient;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1799;
import net.minecraft.class_5819;
import net.minecraft.class_9129;
import vectorwing.farmersdelight.common.Configuration;

public record ChanceResult(class_1799 stack, float chance) {
   public static final ChanceResult EMPTY = new ChanceResult(class_1799.field_8037, 1.0F);
   public static final Codec<ChanceResult> CODEC = RecordCodecBuilder.create(
      inst -> inst.group(
            class_1799.field_24671.fieldOf("item").forGetter(ChanceResult::stack), Codec.FLOAT.optionalFieldOf("chance", 1.0F).forGetter(ChanceResult::chance)
         )
         .apply(inst, ChanceResult::new)
   );

   public class_1799 rollOutput(class_5819 rand, int fortuneLevel) {
      int outputAmount = this.stack.method_7947();
      double fortuneBonus = Configuration.CUTTING_BOARD_FORTUNE_BONUS.get() * fortuneLevel;

      for (int roll = 0; roll < this.stack.method_7947(); roll++) {
         if (rand.method_43057() > this.chance + fortuneBonus) {
            outputAmount--;
         }
      }

      if (outputAmount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 out = this.stack.method_7972();
         out.method_7939(outputAmount);
         return out;
      }
   }

   public void write(class_9129 buffer) {
      class_1799.field_48349.encode(buffer, this.stack());
      buffer.method_52941(this.chance());
   }

   public static ChanceResult read(class_9129 buffer) {
      return new ChanceResult((class_1799)class_1799.field_48349.decode(buffer), buffer.readFloat());
   }
}
