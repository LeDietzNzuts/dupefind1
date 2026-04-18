package noobanidus.mods.lootr.fabric.mixin.client;

import net.minecraft.class_2586;
import net.minecraft.class_2622;
import net.minecraft.class_634;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_634.class)
public class MixinClientPacketListener {
   @Inject(method = "method_38542", at = @At("RETURN"))
   private void LootrInjectClientBlockEntityUpdateTag(class_2622 clientboundBlockEntityDataPacket, class_2586 blockEntity, CallbackInfo ci) {
      if (LootrAPI.resolveBlockEntity(blockEntity) instanceof ILootrBlockEntity) {
         ClientHooks.clearCache(blockEntity.method_11016());
      }
   }
}
