package dev.architectury.extensions.injected;

import net.minecraft.class_2248;
import net.minecraft.class_6880;

public interface InjectedBlockExtension extends InjectedRegistryEntryExtension<class_2248> {
   @Override
   default class_6880<class_2248> arch$holder() {
      return ((class_2248)this).method_40142();
   }
}
