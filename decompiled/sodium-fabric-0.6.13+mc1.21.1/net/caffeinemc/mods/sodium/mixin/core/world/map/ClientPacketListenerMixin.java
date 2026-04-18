package net.caffeinemc.mods.sodium.mixin.core.world.map;

import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTrackerHolder;
import net.minecraft.class_2666;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_6606;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_634.class)
public class ClientPacketListenerMixin {
   @Shadow
   private class_638 field_3699;

   @Inject(method = "method_38543(IILnet/minecraft/class_6606;)V", at = @At("RETURN"))
   private void onLightDataReceived(int x, int z, class_6606 data, CallbackInfo ci) {
      ChunkTrackerHolder.get(this.field_3699).onChunkStatusAdded(x, z, 2);
   }

   @Inject(method = "method_11107(Lnet/minecraft/class_2666;)V", at = @At("RETURN"))
   private void onChunkUnloadPacket(class_2666 packet, CallbackInfo ci) {
      ChunkTrackerHolder.get(this.field_3699).onChunkStatusRemoved(packet.comp_1726().field_9181, packet.comp_1726().field_9180, 3);
   }
}
