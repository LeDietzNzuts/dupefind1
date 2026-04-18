package net.p3pp3rf1y.sophisticatedbackpacks.client.init;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.class_322;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public class ModBlockColors {
   private ModBlockColors() {
   }

   public static void registerBlockColorHandlers() {
      ColorProviderRegistry.BLOCK
         .register(
            (class_322)(state, blockDisplayReader, pos, tintIndex) -> tintIndex >= 0 && tintIndex <= 1 && pos != null
               ? WorldHelper.getBlockEntity(blockDisplayReader, pos, BackpackBlockEntity.class)
                  .map(be -> tintIndex == 0 ? be.getBackpackWrapper().getMainColor() : be.getBackpackWrapper().getAccentColor())
                  .orElse(getDefaultColor(tintIndex))
               : -1,
            ModBlocks.BACKPACKS.stream().map(Supplier::get).toArray(BackpackBlock[]::new)
         );
   }

   private static int getDefaultColor(int tintIndex) {
      return tintIndex == 0 ? -3382982 : -10342886;
   }
}
