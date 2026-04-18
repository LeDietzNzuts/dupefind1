package com.talhanation.smallships.client;

import com.talhanation.smallships.client.gui.screens.inventory.ShipContainerScreen;
import com.talhanation.smallships.world.inventory.ModMenuTypes;
import net.minecraft.class_3929;

public class ClientInitializer {
   public static void init() {
      class_3929.method_17542(ModMenuTypes.SHIP_CONTAINER, ShipContainerScreen::new);
   }
}
