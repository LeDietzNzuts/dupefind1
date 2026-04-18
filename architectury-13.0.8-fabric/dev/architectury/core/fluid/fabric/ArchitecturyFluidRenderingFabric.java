package dev.architectury.core.fluid.fabric;

import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.fluid.FluidStack;
import dev.architectury.hooks.fluid.fabric.FluidStackHooksFabric;
import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3610;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
class ArchitecturyFluidRenderingFabric implements FluidVariantRenderHandler, FluidRenderHandler {
   private final ArchitecturyFluidAttributes attributes;
   private final class_1058[] sprites = new class_1058[2];
   private final class_1058[] spritesOverlaid = new class_1058[3];
   private final class_1058[] spritesOther = new class_1058[2];
   private final class_1058[] spritesOtherOverlaid = new class_1058[3];

   public ArchitecturyFluidRenderingFabric(ArchitecturyFluidAttributes attributes) {
      this.attributes = attributes;
   }

   @Nullable
   public class_1058[] getSprites(FluidVariant variant) {
      FluidStack stack = FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount());
      Function<class_2960, class_1058> atlas = class_310.method_1551().method_1549(class_1059.field_5275);
      class_2960 overlayTexture = this.attributes.getOverlayTexture(stack);
      class_1058 overlaySprite = overlayTexture == null ? null : atlas.apply(overlayTexture);
      class_1058[] sprites = overlaySprite == null ? this.sprites : this.spritesOverlaid;
      sprites[0] = atlas.apply(this.attributes.getSourceTexture(stack));
      sprites[1] = atlas.apply(this.attributes.getFlowingTexture(stack));
      if (overlaySprite != null) {
         sprites[2] = overlaySprite;
      }

      return sprites;
   }

   public int getColor(FluidVariant variant, @Nullable class_1920 view, @Nullable class_2338 pos) {
      return this.attributes.getColor(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()), view, pos);
   }

   public class_1058[] getFluidSprites(@Nullable class_1920 view, @Nullable class_2338 pos, class_3610 state) {
      Function<class_2960, class_1058> atlas = class_310.method_1551().method_1549(class_1059.field_5275);
      class_2960 overlayTexture = this.attributes.getOverlayTexture(state, view, pos);
      class_1058 overlaySprite = overlayTexture == null ? null : atlas.apply(overlayTexture);
      class_1058[] sprites = overlaySprite == null ? this.spritesOther : this.spritesOtherOverlaid;
      sprites[0] = atlas.apply(this.attributes.getSourceTexture(state, view, pos));
      sprites[1] = atlas.apply(this.attributes.getFlowingTexture(state, view, pos));
      if (overlaySprite != null) {
         sprites[2] = overlaySprite;
      }

      return sprites;
   }

   public int getFluidColor(@Nullable class_1920 view, @Nullable class_2338 pos, class_3610 state) {
      return this.attributes.getColor(state, view, pos);
   }
}
