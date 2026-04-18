package net.caffeinemc.mods.sodium.client.model.color;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap.Entry;
import net.caffeinemc.mods.sodium.client.model.color.interop.BlockColorsExtension;
import net.caffeinemc.mods.sodium.client.services.FluidRendererFactory;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_322;
import net.minecraft.class_324;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;

public class ColorProviderRegistry {
   private final Reference2ReferenceMap<class_2248, ColorProvider<class_2680>> blocks = new Reference2ReferenceOpenHashMap();
   private final Reference2ReferenceMap<class_3611, ColorProvider<class_3610>> fluids = new Reference2ReferenceOpenHashMap();
   private final ReferenceSet<class_2248> overridenBlocks;

   public ColorProviderRegistry(class_324 blockColors) {
      Reference2ReferenceMap<class_2248, class_322> providers = BlockColorsExtension.getProviders(blockColors);
      ObjectIterator var3 = providers.reference2ReferenceEntrySet().iterator();

      while (var3.hasNext()) {
         Entry<class_2248, class_322> entry = (Entry<class_2248, class_322>)var3.next();
         this.blocks.put((class_2248)entry.getKey(), DefaultColorProviders.adapt((class_322)entry.getValue()));
      }

      this.overridenBlocks = BlockColorsExtension.getOverridenVanillaBlocks(blockColors);
      this.installOverrides();
   }

   private void installOverrides() {
      this.registerBlocks(
         DefaultColorProviders.GrassColorProvider.BLOCKS,
         class_2246.field_10219,
         class_2246.field_10112,
         class_2246.field_10479,
         class_2246.field_10128,
         class_2246.field_42750,
         class_2246.field_10424,
         class_2246.field_10313,
         class_2246.field_10214
      );
      this.registerBlocks(
         DefaultColorProviders.FoliageColorProvider.BLOCKS,
         class_2246.field_10503,
         class_2246.field_10335,
         class_2246.field_10098,
         class_2246.field_10035,
         class_2246.field_10597,
         class_2246.field_37551
      );
      this.registerBlocks(FluidRendererFactory.getInstance().getWaterBlockColorProvider(), class_2246.field_10382, class_2246.field_10422);
      this.registerFluids(FluidRendererFactory.getInstance().getWaterColorProvider(), class_3612.field_15910, class_3612.field_15909);
   }

   private void registerBlocks(ColorProvider<class_2680> provider, class_2248... blocks) {
      for (class_2248 block : blocks) {
         if (!this.overridenBlocks.contains(block)) {
            this.blocks.put(block, provider);
         }
      }
   }

   private void registerFluids(ColorProvider<class_3610> provider, class_3611... fluids) {
      for (class_3611 fluid : fluids) {
         this.fluids.put(fluid, provider);
      }
   }

   public ColorProvider<class_2680> getColorProvider(class_2248 block) {
      return (ColorProvider<class_2680>)this.blocks.get(block);
   }

   public ColorProvider<class_3610> getColorProvider(class_3611 fluid) {
      return (ColorProvider<class_3610>)this.fluids.get(fluid);
   }
}
