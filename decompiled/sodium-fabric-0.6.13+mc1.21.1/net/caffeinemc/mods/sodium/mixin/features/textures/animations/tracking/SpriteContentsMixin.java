package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.caffeinemc.mods.sodium.client.render.texture.SpriteContentsExtension;
import net.minecraft.class_7764;
import net.minecraft.class_7764.class_5790;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_7764.class)
public abstract class SpriteContentsMixin implements SpriteContentsExtension {
   @Shadow
   @Final
   @Nullable
   private class_5790 field_40541;
   @Unique
   private boolean active;

   @Override
   public void sodium$setActive(boolean value) {
      this.active = value;
   }

   @Override
   public boolean sodium$hasAnimation() {
      return this.field_40541 != null;
   }

   @Override
   public boolean sodium$isActive() {
      return this.active;
   }
}
