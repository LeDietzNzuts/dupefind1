package dev.architectury.extensions.injected;

import net.minecraft.class_3611;
import net.minecraft.class_6880;

public interface InjectedFluidExtension extends InjectedRegistryEntryExtension<class_3611> {
   @Override
   default class_6880<class_3611> arch$holder() {
      return ((class_3611)this).method_40178();
   }
}
