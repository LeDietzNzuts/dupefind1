package com.magistuarmory.api.client.render.model;

import com.magistuarmory.api.item.ModItemsProvider;
import com.magistuarmory.client.render.ModRender;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_5607;

@Environment(EnvType.CLIENT)
public abstract class ModModelsProvider {
   public final String modId;
   public final Map<class_5601, Supplier<class_5607>> layers = new HashMap<>();

   public ModModelsProvider(String modId) {
      this.modId = modId;
   }

   public class_5601 addDecorationModel(String name, Supplier<class_5607> definition) {
      class_5601 location = this.createDecorationLocation(name);
      this.layers.put(location, definition);
      return location;
   }

   public class_5601 addArmorModel(String name, Supplier<class_5607> definition) {
      class_5601 location = this.createArmorLocation(name);
      this.layers.put(location, definition);
      return location;
   }

   public class_5601 addModel(String name, Supplier<class_5607> definition) {
      class_5601 location = this.createLocation(name);
      this.layers.put(location, definition);
      return location;
   }

   public class_5601 createDecorationLocation(String name) {
      return createDecorationLocation(class_2960.method_60655(this.modId, name));
   }

   public class_5601 createArmorLocation(String name) {
      return createArmorLocation(class_2960.method_60655(this.modId, name));
   }

   public class_5601 createLocation(String name) {
      return createLocation(class_2960.method_60655(this.modId, name));
   }

   public class_5601 createLocation(String name, String layer) {
      return createLocation(class_2960.method_60655(this.modId, name), layer);
   }

   public static class_5601 createDecorationLocation(class_2960 location) {
      return createLocation(location, "decorations");
   }

   public static class_5601 createArmorLocation(class_2960 location) {
      return createLocation(location, "armor");
   }

   public static class_5601 createLocation(class_2960 location) {
      return createLocation(location, "main");
   }

   public static class_5601 createLocation(class_2960 location, String layer) {
      return new class_5601(location, layer);
   }

   public void init(ModItemsProvider content) {
      this.layers.forEach(EntityModelLayerRegistry::register);
      ModRender.registerModelsLoadListener(content);
   }
}
