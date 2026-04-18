package dev.architectury.registry.client.keymappings.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;

public class KeyMappingRegistryImpl {
   public static void register(class_304 mapping) {
      KeyBindingHelper.registerKeyBinding(mapping);
   }
}
