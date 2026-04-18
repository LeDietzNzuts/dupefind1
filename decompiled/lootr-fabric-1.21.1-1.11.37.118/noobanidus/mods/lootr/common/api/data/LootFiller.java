package noobanidus.mods.lootr.common.api.data;

import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8567;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface LootFiller {
   void unpackLootTable(@NotNull ILootrInfoProvider var1, @NotNull class_1657 var2, class_1263 var3);

   default void fill(
      ILootrInfoProvider provider,
      class_1657 player,
      class_5321<class_52> lootTableKey,
      class_52 lootTable,
      class_1263 container,
      class_8567 parameters,
      long seed
   ) {
      DefaultLootFiller.performFill(provider, player, lootTableKey, lootTable, container, parameters, seed);
   }

   public record LootFillerState(
      ILootrInfoProvider provider,
      class_1657 player,
      class_5321<class_52> lootTableKey,
      class_52 lootTable,
      class_1263 container,
      class_8567 parameters,
      long seed
   ) {
   }
}
