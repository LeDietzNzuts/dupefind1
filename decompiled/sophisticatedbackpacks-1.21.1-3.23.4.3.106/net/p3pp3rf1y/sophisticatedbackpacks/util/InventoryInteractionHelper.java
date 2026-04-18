package net.p3pp3rf1y.sophisticatedbackpacks.util;

import java.util.List;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IItemHandlerInteractionUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;

public class InventoryInteractionHelper {
   private InventoryInteractionHelper() {
   }

   public static boolean tryInventoryInteraction(class_1838 context) {
      class_1657 player = context.method_8036();
      return player == null
         ? false
         : tryInventoryInteraction(context.method_8037(), context.method_8045(), context.method_8041(), context.method_8038(), player);
   }

   public static boolean tryInventoryInteraction(class_2338 pos, class_1937 level, class_1799 backpack, class_2350 face, class_1657 player) {
      return Config.SERVER.noInteractionBlocks.isBlockInteractionDisallowed(level.method_8320(pos).method_26204())
         ? false
         : (Boolean)CapabilityHelper.getFromItemHandler(
            level,
            pos,
            face,
            itemHandler -> player.method_37908().field_9236 || tryRunningInteractionWrappers(itemHandler, BackpackWrapper.fromStack(backpack), player),
            false
         );
   }

   private static boolean tryRunningInteractionWrappers(Storage<ItemVariant> storage, IStorageWrapper wrapper, class_1657 player) {
      List<IItemHandlerInteractionUpgrade> wrappers = wrapper.getUpgradeHandler().getWrappersThatImplement(IItemHandlerInteractionUpgrade.class);
      if (wrappers.isEmpty()) {
         return false;
      } else {
         wrappers.forEach(upgrade -> upgrade.onHandlerInteract(storage, player));
         return true;
      }
   }
}
