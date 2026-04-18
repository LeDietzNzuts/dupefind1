package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.class_169;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import net.minecraft.class_5342;

public record CanItemPerformAbilityCondition(ItemAbility ability) implements class_5341 {
   public static final MapCodec<CanItemPerformAbilityCondition> CODEC = RecordCodecBuilder.mapCodec(
      inst -> inst.group(ItemAbility.CODEC.fieldOf("ability").forGetter(CanItemPerformAbilityCondition::ability))
         .apply(inst, CanItemPerformAbilityCondition::new)
   );
   public static final Supplier<class_5342> TYPE = RegUtils.regLootCond("can_item_perform_ability", () -> new class_5342(CODEC));

   public static void init() {
   }

   public boolean test(class_47 context) {
      class_1799 stack = (class_1799)context.method_35508(class_181.field_1229);
      return this.ability.canPerformAction(stack);
   }

   public Set<class_169<?>> method_293() {
      return Set.of(class_181.field_1229);
   }

   public class_5342 method_29325() {
      return TYPE.get();
   }
}
