package cn.enaium.onekeyminer.mixin;

import cn.enaium.onekeyminer.callback.FinishMiningCallback;
import cn.enaium.onekeyminer.callback.UseOnBlockCallback;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3225;
import net.minecraft.class_3965;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3225.class)
public abstract class ServerPlayerInteractionManagerMixin {
   @Shadow
   @Final
   protected class_3222 field_14008;
   @Shadow
   protected class_3218 field_14007;

   @Shadow
   public abstract boolean method_14266(class_2338 var1);

   @Inject(at = @At("HEAD"), method = "finishMining")
   private void finishMining(class_2338 pos, int sequence, String reason, CallbackInfo ci) {
      ((FinishMiningCallback)FinishMiningCallback.Companion.getEVENT().invoker()).interact(this.field_14007, this.field_14008, pos, tryBreak -> {
         this.method_14266(tryBreak);
         return null;
      });
   }

   @Inject(
      at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;"),
      method = "interactBlock"
   )
   public void interactBlock(
      class_3222 player, class_1937 world, class_1799 stack, class_1268 hand, class_3965 hitResult, CallbackInfoReturnable<class_1269> cir
   ) {
      ((UseOnBlockCallback)UseOnBlockCallback.Companion.getEVENT().invoker()).interact(player, world, stack, hand, hitResult);
   }
}
