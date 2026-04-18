package net.raphimc.immediatelyfast.injection.mixins.core;

import java.util.Map;
import net.minecraft.class_1044;
import net.minecraft.class_1060;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1060.class)
public abstract class MixinTextureManager {
   @Shadow
   @Final
   private Map<class_2960, class_1044> field_5286;

   @Inject(method = "method_4615(Lnet/minecraft/class_2960;)V", at = @At("RETURN"))
   private void removeDestroyedTexture(class_2960 id, CallbackInfo ci) {
      this.field_5286.remove(id);
   }
}
