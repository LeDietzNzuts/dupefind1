package vectorwing.farmersdelight.common.loot.modifier;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5341;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.refabricated.LootModifier;

public class FDAddTableLootModifier extends LootModifier {
   private final class_5321<class_52> lootTable;

   public FDAddTableLootModifier(class_5341[] conditionsIn, class_5321<class_52> lootTable) {
      super(conditionsIn);
      this.lootTable = lootTable;
   }

   @NotNull
   @Override
   protected ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> generatedLoot, class_47 context) {
      if (Configuration.GENERATE_FD_CHEST_LOOT.get()) {
      }

      return generatedLoot;
   }
}
