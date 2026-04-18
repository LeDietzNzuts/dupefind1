package org.embeddedt.modernfix.common.mixin.perf.compact_bit_storage;

import net.minecraft.class_2540;
import net.minecraft.class_2841;
import net.minecraft.class_2841.class_6561;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2841.class)
public abstract class PalettedContainerMixin<T> {
   @Shadow
   private volatile class_6561<T> field_34560;

   @Shadow
   protected abstract class_6561<T> method_38297(@Nullable class_6561<T> var1, int var2);

   @Inject(
      method = "read(Lnet/minecraft/network/FriendlyByteBuf;)V",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/world/level/chunk/PalettedContainer;data:Lnet/minecraft/world/level/chunk/PalettedContainer$Data;",
         opcode = 181,
         shift = Shift.AFTER
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void validateData(class_2540 buffer, CallbackInfo ci, int i) {
      if (i > 1) {
         long[] storArray = this.field_34560.comp_118().method_15212();
         boolean empty = true;

         for (long l : storArray) {
            if (l != 0L) {
               empty = false;
               break;
            }
         }

         if (empty && storArray.length > 0) {
            T value;
            try {
               value = (T)this.field_34560.comp_119().method_12288(0);
            } catch (RuntimeException var11) {
               return;
            }

            this.field_34560 = this.method_38297(null, 0);
            this.field_34560.comp_119().method_12291(value);
         }
      }
   }
}
