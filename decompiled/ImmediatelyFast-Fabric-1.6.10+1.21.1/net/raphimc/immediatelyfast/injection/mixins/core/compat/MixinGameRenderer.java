package net.raphimc.immediatelyfast.injection.mixins.core.compat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3262;
import net.minecraft.class_3298;
import net.minecraft.class_5912;
import net.minecraft.class_5944;
import net.minecraft.class_757;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.compat.CoreShaderBlacklist;
import net.raphimc.immediatelyfast.feature.core.ImmediatelyFastResourcePackMetadata;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_757.class)
public abstract class MixinGameRenderer {
   @Shadow
   @Final
   private Map<String, class_5944> field_29350;

   @Inject(method = "method_34538(Lnet/minecraft/class_5912;)V", at = @At("RETURN"))
   private void checkForCoreShaderModifications(class_5912 factory, CallbackInfo ci) {
      if (!ImmediatelyFast.config.experimental_disable_resource_pack_conflict_handling) {
         class_3262 resourcePackWhichBreaksFontAtlasResizing = null;
         class_3262 resourcePackWhichBreaksHudBatching = null;
         class_3262 resourcePackWhichBreaksScreenBatching = null;

         try {
            Set<class_3262> breakingResourcePacks = new HashSet<>();

            for (Entry<String, class_5944> shaderProgramEntry : this.field_29350.entrySet()) {
               if (CoreShaderBlacklist.isBlacklisted(shaderProgramEntry.getKey())) {
                  class_2960 vertexShaderIdentifier = class_2960.method_60654(
                     "shaders/core/" + shaderProgramEntry.getValue().method_1274().method_1280() + ".vsh"
                  );
                  class_3262 vertexShaderResourcePack = factory.method_14486(vertexShaderIdentifier).<class_3262>map(class_3298::method_45304).orElse(null);
                  if (vertexShaderResourcePack != null && !vertexShaderResourcePack.equals(class_310.method_1551().method_45573())) {
                     breakingResourcePacks.add(vertexShaderResourcePack);
                  }

                  class_2960 fragmentShaderIdentifier = class_2960.method_60654(
                     "shaders/core/" + shaderProgramEntry.getValue().method_1278().method_1280() + ".fsh"
                  );
                  class_3262 fragmentShaderResourcePack = factory.method_14486(fragmentShaderIdentifier).<class_3262>map(class_3298::method_45304).orElse(null);
                  if (fragmentShaderResourcePack != null && !fragmentShaderResourcePack.equals(class_310.method_1551().method_45573())) {
                     breakingResourcePacks.add(fragmentShaderResourcePack);
                  }
               }
            }

            for (class_3262 resourcePack : breakingResourcePacks) {
               ImmediatelyFastResourcePackMetadata metadata = (ImmediatelyFastResourcePackMetadata)resourcePack.method_14407(
                  ImmediatelyFastResourcePackMetadata.SERIALIZER
               );
               if (metadata == null) {
                  metadata = ImmediatelyFastResourcePackMetadata.DEFAULT;
               }

               if (!metadata.compatibleFeatures().contains("font_atlas_resizing")) {
                  resourcePackWhichBreaksFontAtlasResizing = resourcePack;
               }

               if (!metadata.compatibleFeatures().contains("hud_batching")) {
                  resourcePackWhichBreaksHudBatching = resourcePack;
               }

               if (!metadata.compatibleFeatures().contains("experimental_screen_batching")) {
                  resourcePackWhichBreaksScreenBatching = resourcePack;
               }
            }
         } catch (IOException var13) {
            ImmediatelyFast.LOGGER.error("Failed to check for core shader modifications", var13);
         }

         if (ImmediatelyFast.config.font_atlas_resizing) {
            if (resourcePackWhichBreaksFontAtlasResizing != null) {
               ImmediatelyFast.LOGGER
                  .warn(
                     "Resource pack "
                        + resourcePackWhichBreaksFontAtlasResizing.method_14409()
                        + " is not compatible with font atlas resizing. Temporarily disabling font atlas resizing."
                  );
               if (ImmediatelyFast.runtimeConfig.font_atlas_resizing) {
                  ImmediatelyFast.runtimeConfig.font_atlas_resizing = false;
                  this.immediatelyFast$reloadFontStorages();
               }
            } else if (!ImmediatelyFast.runtimeConfig.font_atlas_resizing) {
               ImmediatelyFast.LOGGER.info("Re-enabling font atlas resizing because no incompatible resource packs are loaded.");
               ImmediatelyFast.runtimeConfig.font_atlas_resizing = true;
               this.immediatelyFast$reloadFontStorages();
            }
         }

         if (ImmediatelyFast.config.hud_batching) {
            if (resourcePackWhichBreaksHudBatching != null) {
               ImmediatelyFast.LOGGER
                  .warn(
                     "Resource pack "
                        + resourcePackWhichBreaksHudBatching.method_14409()
                        + " is not compatible with HUD batching. Temporarily disabling HUD batching."
                  );
               ImmediatelyFast.runtimeConfig.hud_batching = false;
            } else if (!ImmediatelyFast.runtimeConfig.hud_batching) {
               ImmediatelyFast.LOGGER.info("Re-enabling HUD batching because no incompatible resource packs are loaded.");
               ImmediatelyFast.runtimeConfig.hud_batching = true;
            }
         }

         if (ImmediatelyFast.config.experimental_screen_batching) {
            if (resourcePackWhichBreaksScreenBatching != null) {
               ImmediatelyFast.LOGGER
                  .warn(
                     "Resource pack "
                        + resourcePackWhichBreaksScreenBatching.method_14409()
                        + " is not compatible with experimental screen batching. Temporarily disabling experimental screen batching."
                  );
               ImmediatelyFast.runtimeConfig.experimental_screen_batching = false;
            } else if (!ImmediatelyFast.runtimeConfig.experimental_screen_batching) {
               ImmediatelyFast.LOGGER.info("Re-enabling experimental screen batching because no incompatible resource packs are loaded.");
               ImmediatelyFast.runtimeConfig.experimental_screen_batching = true;
            }
         }
      }
   }

   @Unique
   private void immediatelyFast$reloadFontStorages() {
      class_310.method_1551().field_1708.method_57024(class_310.method_1551().field_1690);
   }
}
