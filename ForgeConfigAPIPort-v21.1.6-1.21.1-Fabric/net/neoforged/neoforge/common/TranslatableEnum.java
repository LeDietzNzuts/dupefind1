package net.neoforged.neoforge.common;

import net.minecraft.class_2561;

public interface TranslatableEnum {
   default class_2561 getTranslatedName() {
      return class_2561.method_43470(((Enum)this).name());
   }
}
