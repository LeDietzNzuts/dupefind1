package net.caffeinemc.mods.sodium.mixin.features.shader.uniform;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.List;
import net.minecraft.class_284;
import net.minecraft.class_5944;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_5944.class)
public class ShaderInstanceMixin {
   @Shadow
   @Final
   private List<String> field_29488;
   @Shadow
   @Final
   private int field_29493;
   @Unique
   private Object2IntMap<String> uniformCache;

   @Unique
   private void initCache() {
      this.uniformCache = new Object2IntOpenHashMap();
      this.uniformCache.defaultReturnValue(-1);

      for (String samplerName : this.field_29488) {
         int location = class_284.method_22096(this.field_29493, samplerName);
         if (location == -1) {
            throw new IllegalStateException("Failed to find uniform '%s' during shader init".formatted(samplerName));
         }

         this.uniformCache.put(samplerName, location);
      }
   }

   @Inject(method = "method_34588()V", at = @At("RETURN"), require = 0)
   private void initCache(CallbackInfo ci) {
      this.initCache();
   }

   @Redirect(method = "method_34586()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_284;method_22096(ILjava/lang/CharSequence;)I"))
   private int redirectGetUniformLocation(int program, CharSequence name) {
      int location = this.uniformCache.getInt(name);
      if (location == -1) {
         throw new IllegalStateException("Failed to find uniform '%s' during shader bind");
      } else {
         return location;
      }
   }
}
