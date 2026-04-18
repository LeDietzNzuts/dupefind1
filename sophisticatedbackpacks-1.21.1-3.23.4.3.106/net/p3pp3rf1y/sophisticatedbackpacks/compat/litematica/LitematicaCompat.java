package net.p3pp3rf1y.sophisticatedbackpacks.compat.litematica;

import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_1935;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaCompat.LitematicaWrapper;

public class LitematicaCompat implements ICompat {
   private static class_2487 getBackpackTag(UUID backpackUuid) {
      class_2487 backpackContents = BackpackStorage.get().getOrCreateBackpackContents(backpackUuid);
      class_2487 inventoryContents = new class_2487();
      class_2520 inventoryNbt = backpackContents.method_10580("inventory");
      if (inventoryNbt != null) {
         inventoryContents.method_10566("inventory", inventoryNbt);
      }

      class_2520 upgradeNbt = backpackContents.method_10580("upgradeInventory");
      if (upgradeNbt != null) {
         inventoryContents.method_10566("upgradeInventory", upgradeNbt);
      }

      return inventoryContents;
   }

   public void setup() {
      net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaCompat.LITEMATICA_CAPABILITY
         .registerForItems(
            (stack, context) -> new LitematicaWrapper(
               BackpackWrapper.fromStack(stack), uuid -> new LitematicaBackpackContentsPayload(uuid, getBackpackTag(uuid))
            ),
            (class_1935[])ModItems.BACKPACKS.stream().map(Supplier::get).toArray(BackpackItem[]::new)
         );
   }
}
