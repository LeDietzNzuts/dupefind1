package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;

public class UUIDDeduplicator {
   private UUIDDeduplicator() {
   }

   public static void checkForDuplicateBackpacksAndRemoveTheirUUID(class_1657 player, UUID backpackUuid, class_1799 backpack) {
      PlayerInventoryProvider.get().runOnBackpacks(player, (otherBackpack, inventoryHandlerName, identifier, slot) -> {
         if (otherBackpack != backpack) {
            IBackpackWrapper wrapper = BackpackWrapper.fromStack(otherBackpack);
            wrapper.getContentsUuid().ifPresent(uuid -> {
               if (uuid.equals(backpackUuid)) {
                  wrapper.removeContentsUUIDTag();
                  wrapper.onContentsNbtUpdated();
               }
            });
         }

         return false;
      });
   }

   public static void dedupeBackpackItemEntityInArea(class_1542 newBackpackItemEntity) {
      IBackpackWrapper newBackpackWrapper = BackpackWrapper.fromStack(newBackpackItemEntity.method_6983());
      newBackpackWrapper.getContentsUuid().ifPresent(backpackId -> dedupeBackpackItemEntityInArea(newBackpackWrapper, newBackpackItemEntity, backpackId));
   }

   private static void dedupeBackpackItemEntityInArea(IBackpackWrapper newBackpackWrapper, class_1542 newBackpackItemEntity, UUID backpackId) {
      for (class_1542 entity : newBackpackItemEntity.method_37908()
         .method_8390(class_1542.class, newBackpackItemEntity.method_5829().method_1014(10.0), class_1297::method_5805)) {
         if (checkEntityBackpackIdMatchAndRemoveIfItDoes(newBackpackWrapper, backpackId, entity)) {
            break;
         }
      }
   }

   private static boolean checkEntityBackpackIdMatchAndRemoveIfItDoes(IBackpackWrapper newBackpackWrapper, UUID newBackpackId, class_1542 entity) {
      return BackpackWrapper.fromStack(entity.method_6983()).getContentsUuid().map(backpackId -> {
         if (backpackId.equals(newBackpackId)) {
            newBackpackWrapper.removeContentsUUIDTag();
            newBackpackWrapper.onContentsNbtUpdated();
            return true;
         } else {
            return false;
         }
      }).orElse(false);
   }
}
