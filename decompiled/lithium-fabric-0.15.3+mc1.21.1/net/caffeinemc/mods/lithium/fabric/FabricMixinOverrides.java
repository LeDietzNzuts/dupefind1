package net.caffeinemc.mods.lithium.fabric;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.caffeinemc.mods.lithium.common.config.Option;
import net.caffeinemc.mods.lithium.common.services.PlatformMixinOverrides;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.CustomValue.CvType;

public class FabricMixinOverrides implements PlatformMixinOverrides {
   protected static final String JSON_KEY_LITHIUM_OPTIONS = "lithium:options";

   @Override
   public void applyLithiumCompat(Map<String, Option> options) {
      if (FabricLoader.getInstance().isModLoaded("worldedit")) {
         options.get("mixin.compat.worldedit").addModOverride(true, "lithium");
      }
   }

   @Override
   public List<PlatformMixinOverrides.MixinOverride> applyModOverrides() {
      List<PlatformMixinOverrides.MixinOverride> list = new ArrayList<>();

      for (ModContainer container : FabricLoader.getInstance().getAllMods()) {
         ModMetadata meta = container.getMetadata();
         if (meta.containsCustomValue("lithium:options")) {
            CustomValue overrides = meta.getCustomValue("lithium:options");
            if (overrides.getType() != CvType.OBJECT) {
               System.out.printf("[Lithium] Mod '%s' contains invalid Lithium option overrides, ignoring", meta.getId());
            } else {
               for (Entry<String, CustomValue> entry : overrides.getAsObject()) {
                  if (entry.getValue().getType() != CvType.BOOLEAN) {
                     System.out.printf("[Lithium] Mod '%s' attempted to override option '%s' with an invalid value, ignoring", meta.getId(), entry.getKey());
                  } else {
                     list.add(new PlatformMixinOverrides.MixinOverride(meta.getId(), entry.getKey(), entry.getValue().getAsBoolean()));
                  }
               }
            }
         }
      }

      return list;
   }
}
