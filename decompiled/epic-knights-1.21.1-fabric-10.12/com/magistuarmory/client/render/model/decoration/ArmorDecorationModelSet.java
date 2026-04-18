package com.magistuarmory.client.render.model.decoration;

import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.item.ArmorDecoration;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class ArmorDecorationModelSet<T extends class_1309> {
   final Map<class_5601, ArmorDecorationModel<T>> map = new HashMap<>();

   public void registerDecorations(List<RegistrySupplier<? extends ArmorDecoration>> decorations, class_5618 context) {
      for (Supplier<? extends ArmorDecoration> supplier : decorations) {
         class_5601 location = supplier.get().createModelLocation();
         this.map.putIfAbsent(location, new ArmorDecorationModel<>(context.method_32167(location)));
      }
   }

   public ArmorDecorationModel<T> get(class_5601 location) {
      return this.map.get(location);
   }

   public ArmorDecorationModel<T> get(class_2960 location) {
      return this.get(ModModels.createDecorationLocation(location));
   }
}
