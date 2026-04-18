package noobanidus.mods.lootr.common.mixin.cat;

import net.minecraft.class_1373;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_4538;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1373.class)
public class MixinCatSitOnBlockGoal {
   @Redirect(method = "method_6296", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_27852(Lnet/minecraft/class_2248;)Z"))
   protected boolean LootrIsIn(class_2680 state, class_2248 block) {
      return !LootrRegistry.isReady() ? state.method_27852(block) : state.method_27852(block) || state.method_26164(LootrTags.Blocks.CATS_CAN_BLOCK);
   }

   @Inject(
      method = "method_6296",
      at = @At(target = "Lnet/minecraft/class_2595;method_11048(Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)I", value = "INVOKE"),
      cancellable = true
   )
   protected void LootrPlayersUsing(class_4538 reader, class_2338 pos, CallbackInfoReturnable<Boolean> info) {
      class_2586 blockEntity = reader.method_8321(pos);
      ILootrBlockEntity var6 = LootrAPI.resolveBlockEntity(blockEntity);
      if (var6 instanceof ILootrBlockEntity && var6.getPhysicalOpenerCount() < 1) {
         info.setReturnValue(true);
         info.cancel();
      }
   }
}
