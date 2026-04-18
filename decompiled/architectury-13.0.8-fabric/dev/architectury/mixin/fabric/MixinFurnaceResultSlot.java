package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_1657;
import net.minecraft.class_1719;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1719.class)
public class MixinFurnaceResultSlot {
   @Shadow
   @Final
   private class_1657 field_7818;

   @Inject(method = "checkTakeAchievements", at = @At("RETURN"))
   private void checkTakeAchievements(class_1799 itemStack, CallbackInfo ci) {
      PlayerEvent.SMELT_ITEM.invoker().smelt(this.field_7818, itemStack);
   }
}
