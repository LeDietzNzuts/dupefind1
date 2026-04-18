package dev.architectury.extensions.injected;

import net.minecraft.class_1792;
import net.minecraft.class_6880;

public interface InjectedItemExtension extends InjectedRegistryEntryExtension<class_1792> {
   @Override
   default class_6880<class_1792> arch$holder() {
      return ((class_1792)this).method_40131();
   }
}
