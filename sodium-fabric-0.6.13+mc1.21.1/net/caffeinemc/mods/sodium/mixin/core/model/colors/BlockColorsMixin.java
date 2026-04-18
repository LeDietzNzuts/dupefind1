package net.caffeinemc.mods.sodium.mixin.core.model.colors;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMaps;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import it.unimi.dsi.fastutil.objects.ReferenceSets;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.model.color.interop.BlockColorsExtension;
import net.minecraft.class_2248;
import net.minecraft.class_322;
import net.minecraft.class_324;
import net.minecraft.class_7923;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_324.class)
public class BlockColorsMixin implements BlockColorsExtension {
   @Unique
   private final Reference2ReferenceMap<class_2248, class_322> blocksToColor = new Reference2ReferenceOpenHashMap();
   @Unique
   private final ReferenceSet<class_2248> overridenBlocks = new ReferenceOpenHashSet();

   @Inject(method = "method_1690(Lnet/minecraft/class_322;[Lnet/minecraft/class_2248;)V", at = @At("HEAD"))
   private void preRegisterColorProvider(class_322 provider, class_2248[] blocks, CallbackInfo ci) {
      for (class_2248 block : blocks) {
         if (this.blocksToColor.put(block, provider) != null) {
            this.overridenBlocks.add(block);
            SodiumClientMod.logger()
               .info(
                  "Block {} had its color provider replaced with {} and will not use per-vertex coloring",
                  class_7923.field_41175.method_10221(block),
                  provider.toString()
               );
         }
      }
   }

   @Override
   public Reference2ReferenceMap<class_2248, class_322> sodium$getProviders() {
      return Reference2ReferenceMaps.unmodifiable(this.blocksToColor);
   }

   @Override
   public ReferenceSet<class_2248> sodium$getOverridenVanillaBlocks() {
      return ReferenceSets.unmodifiable(this.overridenBlocks);
   }
}
