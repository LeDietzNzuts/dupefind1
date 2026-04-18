package com.magistuarmory.misc;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_2960;
import net.minecraft.class_3300;
import net.minecraft.class_3695;
import net.minecraft.class_4309;

public class HeraldryReloadListener extends class_4309 {
   public HeraldryReloadListener() {
      super(new Gson(), "heraldry");
   }

   protected void apply(Map<class_2960, JsonElement> data, class_3300 manager, class_3695 profiler) {
      for (Entry<class_2960, JsonElement> entry : data.entrySet()) {
         if (entry.getKey().method_12832().equals("banner_patterns")) {
            for (Entry<String, JsonElement> entry2 : entry.getValue().getAsJsonObject().entrySet()) {
               if (entry2.getKey().equals("values")) {
                  for (JsonElement element : entry2.getValue().getAsJsonArray()) {
                     HeraldryRegistry.register(element.getAsString());
                  }
               }
            }
         }
      }
   }
}
