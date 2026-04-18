package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_1657;
import net.minecraft.class_1734;
import net.minecraft.class_1799;
import net.minecraft.class_8566;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1734.class)
public class MixinResultSlot {
   @Shadow
   @Final
   private class_1657 field_7868;
   @Shadow
   @Final
   private class_8566 field_7870;

   @Inject(
      method = "checkTakeAchievements",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/item/ItemStack;onCraftedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;I)V",
         shift = Shift.AFTER
      )
   )
   private void craft(class_1799 itemStack, CallbackInfo ci) {
      PlayerEvent.CRAFT_ITEM.invoker().craft(this.field_7868, itemStack, this.field_7870);
   }
}
