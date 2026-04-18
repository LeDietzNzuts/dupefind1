package vectorwing.farmersdelight.common.loot.modifier;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_2248;
import net.minecraft.class_2272;
import net.minecraft.class_2680;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.refabricated.LootModifier;

public class PastrySlicingModifier extends LootModifier {
   public static final int MAX_CAKE_BITES = 7;
   public static final int MAX_PIE_BITES = 4;
   private final class_1792 pastrySlice;

   public PastrySlicingModifier(class_5341[] conditionsIn, class_1792 pastrySliceIn) {
      super(conditionsIn);
      this.pastrySlice = pastrySliceIn;
   }

   @NotNull
   @Override
   protected ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> generatedLoot, class_47 context) {
      class_2680 state = (class_2680)context.method_296(class_181.field_1224);
      if (state != null) {
         class_2248 targetBlock = state.method_26204();
         if (targetBlock instanceof class_2272) {
            int bites = (Integer)state.method_11654(class_2272.field_10739);
            generatedLoot.add(new class_1799(this.pastrySlice, 7 - bites));
         } else if (targetBlock instanceof PieBlock) {
            int bites = (Integer)state.method_11654(PieBlock.BITES);
            generatedLoot.add(new class_1799(this.pastrySlice, 4 - bites));
         }
      }

      return generatedLoot;
   }
}
