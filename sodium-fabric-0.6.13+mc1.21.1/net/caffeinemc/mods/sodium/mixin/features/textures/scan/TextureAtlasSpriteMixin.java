package net.caffeinemc.mods.sodium.mixin.features.textures.scan;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.TextureAtlasSpriteExtension;
import net.minecraft.class_1058;
import net.minecraft.class_7764;
import net.minecraft.class_7768;
import net.minecraft.class_7764.class_7765;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1058.class)
public class TextureAtlasSpriteMixin implements TextureAtlasSpriteExtension {
   @Unique
   private boolean hasUnknownImageContents;

   @WrapOperation(
      method = "method_33437()Lnet/minecraft/class_1058$class_7770;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_7764;method_45818()Lnet/minecraft/class_7768;")
   )
   private class_7768 hookTickerInstantiation(class_7764 instance, Operation<class_7768> original) {
      class_7768 ticker = (class_7768)original.call(new Object[]{instance});
      if (ticker != null && !(ticker instanceof class_7765)) {
         this.hasUnknownImageContents = true;
      }

      return ticker;
   }

   @Override
   public boolean sodium$hasUnknownImageContents() {
      return this.hasUnknownImageContents;
   }
}
