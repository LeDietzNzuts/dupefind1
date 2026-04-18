package me.shedaniel.autoconfig.event;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import net.minecraft.class_1269;

public final class ConfigSerializeEvent {
   private ConfigSerializeEvent() {
   }

   @FunctionalInterface
   public interface Load<T extends ConfigData> {
      class_1269 onLoad(ConfigHolder<T> var1, T var2);
   }

   @FunctionalInterface
   public interface Save<T extends ConfigData> {
      class_1269 onSave(ConfigHolder<T> var1, T var2);
   }
}
