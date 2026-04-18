package net.caffeinemc.mods.lithium.fabric;

import net.caffeinemc.mods.lithium.common.services.PlatformModCompat;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2377;
import net.minecraft.class_2614;
import net.minecraft.class_2680;

public class FabricModCompat implements PlatformModCompat {
   private static final boolean HAS_TRANSFER_API = FabricLoader.getInstance().isModLoaded("fabric-transfer-api-v1");

   @Override
   public boolean canHopperInteractWithApiBlockInventory(class_2614 hopperBlockEntity, class_2680 hopperState, boolean extracting) {
      return !HAS_TRANSFER_API ? false : canFindApiInventory(hopperBlockEntity, hopperState, extracting);
   }

   private static boolean canFindApiInventory(class_2614 hopperBlockEntity, class_2680 hopperState, boolean extracting) {
      class_2350 direction = extracting ? class_2350.field_11036 : (class_2350)hopperState.method_11654(class_2377.field_11129);
      class_2338 targetPos = hopperBlockEntity.method_11016().method_10093(direction);
      Object target = ItemStorage.SIDED.find(hopperBlockEntity.method_10997(), targetPos, direction.method_10153());
      return target != null;
   }
}
