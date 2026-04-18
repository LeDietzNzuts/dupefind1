package noobanidus.mods.lootr.fabric.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4208;
import net.minecraft.class_638;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.fabric.network.to_server.PacketRequestUpdate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_638.class)
public class MixinClientLevel {
   @WrapOperation(
      method = "method_41926",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_638;method_8652(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;I)Z")
   )
   private boolean lootr$onSetBlock(class_638 instance, class_2338 pos, class_2680 state, int flags, Operation<Boolean> original) {
      boolean result = (Boolean)original.call(new Object[]{instance, pos, state, flags});
      if (state.method_26164(LootrTags.Blocks.CONTAINERS) && instance.method_8321(pos) != null) {
         ClientPlayNetworking.send(new PacketRequestUpdate(new class_4208(instance.method_27983(), pos)));
      }

      return result;
   }
}
