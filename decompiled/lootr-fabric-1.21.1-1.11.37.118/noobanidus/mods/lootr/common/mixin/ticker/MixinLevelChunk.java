package noobanidus.mods.lootr.common.mixin.ticker;

import net.minecraft.class_2586;
import net.minecraft.class_2818;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.block.entity.BlockEntityTicker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2818.class)
public class MixinLevelChunk {
   @Inject(method = "method_31723", at = @At("HEAD"))
   private void LootrUpdateBlockEntityTicker(class_2586 entity, CallbackInfo cir) {
      if (!LootrAPI.isDisabled()) {
         class_2818 level = (class_2818)this;
         BlockEntityTicker.addEntity(entity, level.method_12200(), level.method_12004());
      }
   }
}
