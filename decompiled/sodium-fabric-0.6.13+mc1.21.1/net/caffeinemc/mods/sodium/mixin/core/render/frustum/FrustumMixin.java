package net.caffeinemc.mods.sodium.mixin.core.render.frustum;

import net.caffeinemc.mods.sodium.client.render.viewport.Viewport;
import net.caffeinemc.mods.sodium.client.render.viewport.ViewportProvider;
import net.caffeinemc.mods.sodium.client.render.viewport.frustum.SimpleFrustum;
import net.minecraft.class_4604;
import org.joml.FrustumIntersection;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4604.class)
public class FrustumMixin implements ViewportProvider {
   @Shadow
   private double field_20995;
   @Shadow
   private double field_20996;
   @Shadow
   private double field_20997;
   @Shadow
   @Final
   private FrustumIntersection field_40823;

   @Override
   public Viewport sodium$createViewport() {
      return new Viewport(new SimpleFrustum(this.field_40823), new Vector3d(this.field_20995, this.field_20996, this.field_20997));
   }
}
