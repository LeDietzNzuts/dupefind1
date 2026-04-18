package com.natamus.collective.fabric.services;

import com.natamus.collective_common_fabric.services.helpers.RegisterKeyMappingHelper;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;

public class FabricRegisterKeyMappingHelper implements RegisterKeyMappingHelper {
   @Override
   public class_304 registerKeyMapping(String description, int key, String category) {
      return KeyBindingHelper.registerKeyBinding(new class_304(description, key, category));
   }
}
