package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_2625;
import net.minecraft.class_310;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.gui.CanvasSignEditScreen;
import vectorwing.farmersdelight.client.gui.HangingCanvasSignEditScreen;
import vectorwing.farmersdelight.common.block.entity.CanvasSignBlockEntity;
import vectorwing.farmersdelight.common.block.entity.HangingCanvasSignBlockEntity;

@Mixin(class_746.class)
public class CanvasSignEditScreenMixin {
   @Shadow
   @Final
   protected class_310 field_3937;

   @Inject(at = @At("HEAD"), method = "method_7311(Lnet/minecraft/class_2625;Z)V", cancellable = true)
   private void openCanvasSignEditScreen(class_2625 signBlockEntity, boolean isFront, CallbackInfo ci) {
      if (signBlockEntity instanceof CanvasSignBlockEntity) {
         this.field_3937.method_1507(new CanvasSignEditScreen(signBlockEntity, isFront, this.field_3937.method_33883()));
         ci.cancel();
      }

      if (signBlockEntity instanceof HangingCanvasSignBlockEntity) {
         this.field_3937.method_1507(new HangingCanvasSignEditScreen(signBlockEntity, isFront, this.field_3937.method_33883()));
         ci.cancel();
      }
   }
}
