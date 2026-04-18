package dev.architectury.extensions.injected;

import net.minecraft.class_1299;
import net.minecraft.class_6880;

public interface InjectedEntityTypeExtension extends InjectedRegistryEntryExtension<class_1299<?>> {
   @Override
   default class_6880<class_1299<?>> arch$holder() {
      return ((class_1299)this).method_40124();
   }
}
