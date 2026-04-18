package dev.architectury.extensions.injected;

import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;

public interface InjectedRegistryEntryExtension<T> {
   class_6880<T> arch$holder();

   @Nullable
   default class_2960 arch$registryName() {
      return this.arch$holder().method_40230().<class_2960>map(class_5321::method_29177).orElse(null);
   }
}
