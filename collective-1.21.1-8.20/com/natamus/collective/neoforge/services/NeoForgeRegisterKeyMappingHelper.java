package com.natamus.collective.neoforge.services;

import com.natamus.collective_common_neoforge.services.helpers.RegisterKeyMappingHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class NeoForgeRegisterKeyMappingHelper implements RegisterKeyMappingHelper {
   private static final List<KeyMapping> keyMappingsToRegister = new ArrayList<>();

   @Override
   public KeyMapping registerKeyMapping(String description, int key, String category) {
      KeyMapping keyMapping = new KeyMapping(description, key, category);
      keyMappingsToRegister.add(keyMapping);
      return keyMapping;
   }

   public static void registerKeyMappings(RegisterKeyMappingsEvent e) {
      for (KeyMapping keyMapping : keyMappingsToRegister) {
         e.register(keyMapping);
      }
   }
}
