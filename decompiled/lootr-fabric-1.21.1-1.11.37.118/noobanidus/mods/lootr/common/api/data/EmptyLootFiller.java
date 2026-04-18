package noobanidus.mods.lootr.common.api.data;

import net.minecraft.class_1263;
import net.minecraft.class_1657;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.jetbrains.annotations.NotNull;

public class EmptyLootFiller implements LootFiller {
   public static final EmptyLootFiller INSTANCE = new EmptyLootFiller();

   @Override
   public void unpackLootTable(@NotNull ILootrInfoProvider provider, @NotNull class_1657 player, class_1263 inventory) {
      if (provider.isInfoReferenceInventory()) {
         LootrAPI.LOG
            .error(
               "EmptyLootFiller was used to fill container {} with a reference inventory at {} in {}",
               provider.getInfoUUID(),
               provider.getInfoPos(),
               provider.getInfoDimension()
            );
      }
   }
}
