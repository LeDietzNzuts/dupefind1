package noobanidus.mods.lootr.fabric.mixin.tags;

import net.minecraft.class_5350;
import noobanidus.mods.lootr.common.debug.TagChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_5350.class)
public class MixinReloadableServerResources {
   @Inject(method = "method_40421()V", at = @At("RETURN"))
   public void LootrCheckTags(CallbackInfo ci) {
      TagChecker.checkTags();
   }
}
