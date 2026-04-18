package noobanidus.mods.lootr.fabric.mixin.client;

import java.util.Map;
import net.minecraft.class_1088;
import net.minecraft.class_1091;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import net.minecraft.class_324;
import net.minecraft.class_3695;
import noobanidus.mods.lootr.common.client.entity.LootrItemFrameRenderer;
import org.apache.commons.lang3.NotImplementedException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1088.class)
public class MixinModelBakery {
   @Shadow
   private void method_61074(class_1091 modelLocation, class_1100 model) {
      throw new NotImplementedException();
   }

   @Shadow
   class_1100 method_4726(class_2960 modelLocation) {
      throw new NotImplementedException();
   }

   @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Collection;forEach(Ljava/util/function/Consumer;)V"))
   private void lootr$RegisterAdditionalModels(class_324 blockColors, class_3695 profilerFiller, Map modelResources, Map blockStateResources, CallbackInfo ci) {
      class_1100 unbakedmodel = this.method_4726(LootrItemFrameRenderer.FRAME_LOCATION.comp_2875());
      this.method_61074(LootrItemFrameRenderer.FRAME_LOCATION, unbakedmodel);
      unbakedmodel = this.method_4726(LootrItemFrameRenderer.FRAME_OPEN_LOCATION.comp_2875());
      this.method_61074(LootrItemFrameRenderer.FRAME_OPEN_LOCATION, unbakedmodel);
   }
}
