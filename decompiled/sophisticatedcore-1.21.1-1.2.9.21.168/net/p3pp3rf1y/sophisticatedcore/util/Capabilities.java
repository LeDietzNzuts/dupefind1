package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.List;
import net.fabricmc.fabric.api.lookup.v1.entity.EntityApiLookup;
import net.minecraft.class_1263;
import net.minecraft.class_1299;
import net.minecraft.class_2350;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryHandlerHelper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryStorageWrapper;

public class Capabilities {
   public static class ItemHandler {
      public static final EntityApiLookup<IInventoryHandlerHelper, Void> ENTITY = EntityApiLookup.get(
         SophisticatedCore.getRL("entity_item_storage"), IInventoryHandlerHelper.class, Void.class
      );
      public static final EntityApiLookup<IInventoryHandlerHelper, class_2350> ENTITY_AUTOMATION = EntityApiLookup.get(
         SophisticatedCore.getRL("entity_automation_item_storage"), IInventoryHandlerHelper.class, class_2350.class
      );

      static {
         for (class_1299 entityType : List.of(class_1299.field_38096, class_1299.field_6126, class_1299.field_6058)) {
            ENTITY.registerForType((entity, ctx) -> InventoryStorageWrapper.of((class_1263)entity), entityType);
            ENTITY_AUTOMATION.registerForType((entity, direction) -> InventoryStorageWrapper.of((class_1263)entity), entityType);
         }

         ENTITY.registerForType((player, ctx) -> InventoryStorageWrapper.of(player), class_1299.field_6097);
      }
   }
}
