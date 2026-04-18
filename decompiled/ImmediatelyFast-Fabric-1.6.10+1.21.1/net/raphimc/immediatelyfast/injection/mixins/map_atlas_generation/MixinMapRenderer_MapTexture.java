package net.raphimc.immediatelyfast.injection.mixins.map_atlas_generation;

import com.llamalad7.mixinextras.sugar.Local;
import net.lenni0451.reflect.Objects;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1060;
import net.minecraft.class_22;
import net.minecraft.class_2960;
import net.minecraft.class_330;
import net.minecraft.class_3620;
import net.minecraft.class_4588;
import net.minecraft.class_330.class_331;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.map_atlas_generation.MapAtlasTexture;
import net.raphimc.immediatelyfast.injection.interfaces.IMapRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_331.class, priority = 1100)
public abstract class MixinMapRenderer_MapTexture {
   @Shadow
   private class_22 field_2046;
   @Mutable
   @Shadow
   @Final
   private class_1043 field_2048;
   @Shadow
   @Final
   class_330 field_2047;
   @Unique
   private static final class_1043 DUMMY_TEXTURE = (class_1043)Objects.allocate(class_1043.class);
   @Unique
   private int immediatelyFast$atlasX;
   @Unique
   private int immediatelyFast$atlasY;
   @Unique
   private MapAtlasTexture immediatelyFast$atlasTexture;

   @Redirect(method = "<init>(Lnet/minecraft/class_330;ILnet/minecraft/class_22;)V", at = @At(value = "NEW", target = "(IIZ)Lnet/minecraft/class_1043;"))
   private class_1043 initAtlasParametersAndDontAllocateTexture(int width, int height, boolean useMipmaps, @Local int id) {
      int packedLocation = ((IMapRenderer)this.field_2047).immediatelyFast$getAtlasMapping(id);
      if (packedLocation == -1) {
         ImmediatelyFast.LOGGER.warn("Map " + id + " is not in an atlas");
         return new class_1043(width, height, useMipmaps);
      } else {
         this.immediatelyFast$atlasX = (packedLocation >> 8 & 0xFF) * 128;
         this.immediatelyFast$atlasY = (packedLocation & 0xFF) * 128;
         this.immediatelyFast$atlasTexture = ((IMapRenderer)this.field_2047).immediatelyFast$getMapAtlasTexture(packedLocation >> 16);
         if (this.immediatelyFast$atlasTexture == null) {
            throw new IllegalStateException("getMapAtlasTexture returned null for packedLocation " + packedLocation + " (map " + id + ")");
         } else {
            return DUMMY_TEXTURE;
         }
      }
   }

   @Redirect(
      method = "<init>(Lnet/minecraft/class_330;ILnet/minecraft/class_22;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1060;method_4617(Ljava/lang/String;Lnet/minecraft/class_1043;)Lnet/minecraft/class_2960;")
   )
   private class_2960 getAtlasTextureIdentifier(class_1060 textureManager, String id, class_1043 texture) {
      if (this.immediatelyFast$atlasTexture != null) {
         this.field_2048 = null;
         return this.immediatelyFast$atlasTexture.getIdentifier();
      } else {
         return textureManager.method_4617(id, texture);
      }
   }

   @Inject(method = "method_1776()V", at = @At("HEAD"), cancellable = true)
   private void updateAtlasTexture(CallbackInfo ci) {
      if (this.immediatelyFast$atlasTexture != null) {
         ci.cancel();
         class_1043 atlasTexture = this.immediatelyFast$atlasTexture.getTexture();
         class_1011 atlasImage = atlasTexture.method_4525();
         if (atlasImage == null) {
            throw new IllegalStateException("Atlas texture has already been closed");
         }

         for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
               int i = x + y * 128;
               atlasImage.method_4305(this.immediatelyFast$atlasX + x, this.immediatelyFast$atlasY + y, class_3620.method_38480(this.field_2046.field_122[i]));
            }
         }

         atlasTexture.method_23207();
         atlasImage.method_4312(
            0, this.immediatelyFast$atlasX, this.immediatelyFast$atlasY, this.immediatelyFast$atlasX, this.immediatelyFast$atlasY, 128, 128, false, false
         );
      }
   }

   @Redirect(
      method = "method_1777(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ZI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4588;method_22913(FF)Lnet/minecraft/class_4588;"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/class_4588;method_22918(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;", ordinal = 0),
         to = @At(value = "INVOKE", target = "Lnet/minecraft/class_4588;method_60803(I)Lnet/minecraft/class_4588;", ordinal = 3)
      )
   )
   private class_4588 drawAtlasTexture(class_4588 instance, float u, float v) {
      if (this.immediatelyFast$atlasTexture != null) {
         if (u == 0.0F && v == 1.0F) {
            u = this.immediatelyFast$atlasX / 4096.0F;
            v = (this.immediatelyFast$atlasY + 128) / 4096.0F;
         } else if (u == 1.0F && v == 1.0F) {
            u = (this.immediatelyFast$atlasX + 128) / 4096.0F;
            v = (this.immediatelyFast$atlasY + 128) / 4096.0F;
         } else if (u == 1.0F && v == 0.0F) {
            u = (this.immediatelyFast$atlasX + 128) / 4096.0F;
            v = this.immediatelyFast$atlasY / 4096.0F;
         } else if (u == 0.0F && v == 0.0F) {
            u = this.immediatelyFast$atlasX / 4096.0F;
            v = this.immediatelyFast$atlasY / 4096.0F;
         }
      }

      return instance.method_22913(u, v);
   }

   @Inject(method = "close()V", at = @At("HEAD"), cancellable = true)
   private void dontCloseDummyTexture(CallbackInfo ci) {
      if (this.immediatelyFast$atlasTexture != null) {
         ci.cancel();
      }
   }
}
