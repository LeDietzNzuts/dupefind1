package noobanidus.mods.lootr.fabric.mixin.advancements;

import net.minecraft.class_2985;
import net.minecraft.class_8779;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2985.class)
public class MixinPlayerAdvancements {
   @Inject(method = "method_12878", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_170;method_748(Lnet/minecraft/class_3222;)V"))
   private void lootrAward(class_8779 advancement, String criterionKey, CallbackInfoReturnable<Boolean> cir) {
      class_2985 playerAdvancements = (class_2985)this;
      if (!playerAdvancements.field_13391.method_37908().method_8608()) {
         LootrRegistry.getAdvancementTrigger().trigger(playerAdvancements.field_13391, advancement.comp_1919());
      }
   }
}
