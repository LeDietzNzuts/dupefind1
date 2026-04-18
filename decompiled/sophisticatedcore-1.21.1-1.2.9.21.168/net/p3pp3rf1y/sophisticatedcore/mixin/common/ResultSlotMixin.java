package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1657;
import net.minecraft.class_1734;
import net.minecraft.class_1799;
import net.minecraft.class_8566;
import net.p3pp3rf1y.sophisticatedcore.event.common.PlayerEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1734.class)
public class ResultSlotMixin {
   @Shadow
   @Final
   private class_8566 field_7870;
   @Shadow
   @Final
   private class_1657 field_7868;

   @Inject(method = "onTake", at = @At("HEAD"))
   public void sophisticatedCore_onTake(class_1657 player, class_1799 stack, CallbackInfo callbackInfo) {
      ((PlayerEvents.ItemCrafted)PlayerEvents.ITEM_CRAFTED.invoker()).onItemCrafted(player, stack, this.field_7870);
   }

   @Inject(method = "onQuickCraft", at = @At("HEAD"))
   public void sophisticatedCore_onQuickCraft(class_1799 stack, int amount, CallbackInfo ci) {
      ((PlayerEvents.ItemCrafted)PlayerEvents.ITEM_CRAFTED.invoker()).onItemCrafted(this.field_7868, stack, this.field_7870);
   }
}
