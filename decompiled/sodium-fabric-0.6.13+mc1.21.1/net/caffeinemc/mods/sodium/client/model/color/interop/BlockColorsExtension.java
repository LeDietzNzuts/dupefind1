package net.caffeinemc.mods.sodium.client.model.color.interop;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import net.minecraft.class_2248;
import net.minecraft.class_322;
import net.minecraft.class_324;

public interface BlockColorsExtension {
   static Reference2ReferenceMap<class_2248, class_322> getProviders(class_324 blockColors) {
      return ((BlockColorsExtension)blockColors).sodium$getProviders();
   }

   static ReferenceSet<class_2248> getOverridenVanillaBlocks(class_324 blockColors) {
      return ((BlockColorsExtension)blockColors).sodium$getOverridenVanillaBlocks();
   }

   Reference2ReferenceMap<class_2248, class_322> sodium$getProviders();

   ReferenceSet<class_2248> sodium$getOverridenVanillaBlocks();
}
