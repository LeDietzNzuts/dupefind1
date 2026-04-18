package com.magistuarmory.api.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.magistuarmory.item.armor.DyeableMedievalArmorItem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2405;
import net.minecraft.class_2960;
import net.minecraft.class_7403;
import net.minecraft.class_7784;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;

public class ArmorModelProvider implements class_2405 {
   private final class_7784 output;
   private final String modid;
   private final List<DyeableMedievalArmorItem> armors;

   public ArmorModelProvider(class_7784 output, String modid, List<DyeableMedievalArmorItem> armors) {
      this.output = output;
      this.modid = modid;
      this.armors = armors;
   }

   @NotNull
   public CompletableFuture<?> method_10319(class_7403 cache) {
      List<CompletableFuture<?>> futures = new ArrayList<>();

      for (DyeableMedievalArmorItem armor : this.armors) {
         class_2960 id = class_7923.field_41178.method_10221(armor);
         Path path = this.output.method_45971().resolve("assets").resolve(id.method_12836()).resolve("equipment").resolve(id.method_12832() + ".json");
         JsonObject root = this.createEquipmentModelJson(id.method_12836(), id.method_12832(), armor.getDefaultColor());
         futures.add(class_2405.method_10320(cache, root, path));
      }

      return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
   }

   private JsonObject createEquipmentModelJson(String namespace, String name, int defaultColor) {
      JsonObject root = new JsonObject();
      JsonObject layers = new JsonObject();
      layers.add("humanoid", this.humanoidLayer(namespace, name, defaultColor));
      layers.add("humanoid_leggings", this.leggingsLayer(namespace, name, defaultColor));
      root.add("layers", layers);
      return root;
   }

   private JsonArray humanoidLayer(String ns, String name, int color) {
      JsonArray arr = new JsonArray();
      arr.add(this.texture(ns, "armor/" + name + "_outer"));
      arr.add(this.dyeableTexture(ns, "armor/" + name + "_outer_overlay", color));
      return arr;
   }

   private JsonArray leggingsLayer(String ns, String name, int color) {
      JsonArray arr = new JsonArray();
      arr.add(this.texture(ns, "armor/" + name + "_inner"));
      arr.add(this.dyeableTexture(ns, "armor/" + name + "_inner_overlay", color));
      return arr;
   }

   private JsonObject texture(String ns, String path) {
      JsonObject obj = new JsonObject();
      obj.addProperty("texture", ns + ":" + path);
      return obj;
   }

   private JsonObject dyeableTexture(String ns, String path, int color) {
      JsonObject obj = this.texture(ns, path);
      JsonObject dyeable = new JsonObject();
      dyeable.addProperty("color_when_undyed", color);
      obj.add("dyeable", dyeable);
      return obj;
   }

   @NotNull
   public String method_10321() {
      return "Equipment Models";
   }
}
