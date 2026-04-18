package me.shedaniel.autoconfig.example;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.DummyConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1269;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class ExampleInits {
   public static void exampleCommonInit() {
      ConfigHolder<ExampleConfig> holder = AutoConfig.register(ExampleConfig.class, PartitioningSerializer.wrap(DummyConfigSerializer::new));
      holder.getConfig();
      AutoConfig.getConfigHolder(ExampleConfig.class).getConfig();
      AutoConfig.getConfigHolder(ExampleConfig.class).registerSaveListener((manager, data) -> class_1269.field_5812);
      AutoConfig.getConfigHolder(ExampleConfig.class).registerLoadListener((manager, newData) -> class_1269.field_5812);
   }

   @Environment(EnvType.CLIENT)
   public static void exampleClientInit() {
      AutoConfig.getGuiRegistry(ExampleConfig.class);
   }
}
