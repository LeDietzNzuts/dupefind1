package com.natamus.youritemsaresafe_common_neoforge.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.entity.EquipmentSlot;

public class Constants {
   public static final List<EquipmentSlot> slotTypes = new ArrayList<>(
      Arrays.asList(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
   );
   public static boolean inventoryTotemModIsLoaded = false;
}
