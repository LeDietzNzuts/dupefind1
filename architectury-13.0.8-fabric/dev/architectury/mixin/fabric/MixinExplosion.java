package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.ExplosionEvent;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1927.class)
public class MixinExplosion {
   @Shadow
   @Final
   private class_1937 field_9187;

   @Inject(
      method = "explode",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;<init>(DDD)V", ordinal = 1),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void explodePost(CallbackInfo ci, Set<class_2338> set, int i, float q, int r, int s, int t, int u, int v, int w, List<class_1297> list) {
      ExplosionEvent.DETONATE.invoker().explode(this.field_9187, (class_1927)this, list);
   }
}
