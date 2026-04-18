package org.embeddedt.modernfix.common.mixin.bugfix.world_leaks;

import java.util.concurrent.atomic.AtomicReferenceArray;
import net.minecraft.class_2818;
import net.minecraft.class_310;
import net.minecraft.class_3568;
import net.minecraft.class_638;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
@ClientOnlyMixin
public class MinecraftMixin {
   @Shadow
   @Nullable
   public class_638 field_1687;

   @Inject(
      method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;Z)V",
      at = @At(value = "FIELD", opcode = 181, target = "Lnet/minecraft/client/Minecraft;level:Lnet/minecraft/client/multiplayer/ClientLevel;")
   )
   private void clearLevelDataForLeaks(CallbackInfo ci) {
      if (this.field_1687 != null) {
         try {
            AtomicReferenceArray<class_2818> chunks = this.field_1687.method_2935().field_16246.field_16251;

            for (int i = 0; i < chunks.length(); i++) {
               chunks.set(i, null);
            }

            this.field_1687.method_2935().field_3677 = new class_3568(this.field_1687.method_2935(), false, false);
            this.field_1687.field_27082.clear();
         } catch (RuntimeException var4) {
            ModernFix.LOGGER.error("Exception clearing level data", var4);
         }
      }
   }
}
