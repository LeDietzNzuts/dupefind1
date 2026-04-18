package net.p3pp3rf1y.sophisticatedbackpacks.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1799;
import net.minecraft.class_746;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_759.class)
public class ItemInHandRendererMixin {
   @Shadow
   private class_1799 field_4047;
   @Shadow
   private class_1799 field_4048;
   @Unique
   private int slotMainHand = 0;

   @Unique
   private boolean sophisticatedBackpacks_shouldCauseReequipAnimation(class_1799 from, class_1799 to, int slot) {
      boolean fromEmpty = from.method_7960();
      boolean toEmpty = to.method_7960();
      if (fromEmpty && toEmpty) {
         return false;
      } else if (!fromEmpty && !toEmpty) {
         boolean changed = false;
         if (slot != -1) {
            changed = slot != this.slotMainHand;
            this.slotMainHand = slot;
         }

         return from.method_7909().shouldCauseReequipAnimation(from, to, changed);
      } else {
         return true;
      }
   }

   @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F"))
   private void sophisticatedbackpacks$skipRequipAnimMainHand(
      CallbackInfo ci, @Local class_746 localPlayer, @Local(ordinal = 0) class_1799 itemStack, @Local(ordinal = 1) class_1799 itemStack1
   ) {
      boolean reequipMain = this.sophisticatedBackpacks_shouldCauseReequipAnimation(this.field_4047, itemStack, localPlayer.method_31548().field_7545);
      if (!reequipMain && this.field_4047 != itemStack) {
         this.field_4047 = itemStack;
      }

      boolean reequipOff = this.sophisticatedBackpacks_shouldCauseReequipAnimation(this.field_4048, itemStack1, -1);
      if (!reequipOff && this.field_4048 != itemStack1) {
         this.field_4048 = itemStack1;
      }
   }
}
