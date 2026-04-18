package vectorwing.farmersdelight.common.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.class_175;
import net.minecraft.class_2048;
import net.minecraft.class_3222;
import net.minecraft.class_4558;
import net.minecraft.class_5258;
import net.minecraft.class_4558.class_8788;
import vectorwing.farmersdelight.common.registry.ModAdvancements;

public class CuttingBoardTrigger extends class_4558<CuttingBoardTrigger.TriggerInstance> {
   public Codec<CuttingBoardTrigger.TriggerInstance> method_54937() {
      return CuttingBoardTrigger.TriggerInstance.CODEC;
   }

   public void trigger(class_3222 player) {
      this.method_22510(player, CuttingBoardTrigger.TriggerInstance::test);
   }

   public record TriggerInstance(Optional<class_5258> player) implements class_8788 {
      public static final Codec<CuttingBoardTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
         builder -> builder.group(class_2048.field_47250.optionalFieldOf("player").forGetter(CuttingBoardTrigger.TriggerInstance::comp_2029))
            .apply(builder, CuttingBoardTrigger.TriggerInstance::new)
      );

      public static class_175<CuttingBoardTrigger.TriggerInstance> simple() {
         return ModAdvancements.USE_CUTTING_BOARD.get().method_53699(new CuttingBoardTrigger.TriggerInstance(Optional.empty()));
      }

      public boolean test() {
         return true;
      }

      public Optional<class_5258> comp_2029() {
         return this.player;
      }
   }
}
