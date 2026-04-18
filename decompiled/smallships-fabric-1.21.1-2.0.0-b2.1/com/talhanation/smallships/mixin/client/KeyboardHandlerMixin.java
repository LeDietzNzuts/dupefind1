package com.talhanation.smallships.mixin.client;

import com.talhanation.smallships.client.option.KeyEvent;
import net.minecraft.class_309;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_309.class)
public class KeyboardHandlerMixin {
   @Shadow
   private class_310 field_1678;

   @Inject(method = "keyPress", at = @At("TAIL"))
   public void keyPress(long l, int i, int j, int k, int m, CallbackInfo ci) {
      if (l == this.field_1678.method_22683().method_4490()) {
         KeyEvent.onKeyInput(i, j, k, m);
      }
   }
}
