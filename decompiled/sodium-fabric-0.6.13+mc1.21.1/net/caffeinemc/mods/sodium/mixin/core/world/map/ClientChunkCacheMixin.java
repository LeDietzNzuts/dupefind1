package net.caffeinemc.mods.sodium.mixin.core.world.map;

import java.util.function.Consumer;
import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTrackerHolder;
import net.minecraft.class_1923;
import net.minecraft.class_2487;
import net.minecraft.class_2540;
import net.minecraft.class_2818;
import net.minecraft.class_631;
import net.minecraft.class_638;
import net.minecraft.class_6603.class_6605;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_631.class)
public class ClientChunkCacheMixin {
   @Shadow
   @Final
   class_638 field_16525;

   @Inject(
      method = "method_2859(Lnet/minecraft/class_1923;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_631$class_3681;method_20183(ILnet/minecraft/class_2818;Lnet/minecraft/class_2818;)Lnet/minecraft/class_2818;",
         shift = Shift.AFTER
      )
   )
   private void onChunkUnloaded(class_1923 pos, CallbackInfo ci) {
      ChunkTrackerHolder.get(this.field_16525).onChunkStatusRemoved(pos.field_9181, pos.field_9180, 1);
   }

   @Inject(
      method = "method_16020(IILnet/minecraft/class_2540;Lnet/minecraft/class_2487;Ljava/util/function/Consumer;)Lnet/minecraft/class_2818;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_638;method_23782(Lnet/minecraft/class_1923;)V", shift = Shift.AFTER)
   )
   private void onChunkLoaded(
      int chunkX, int chunkZ, class_2540 buf, class_2487 nbt, Consumer<class_6605> consumer, CallbackInfoReturnable<@Nullable class_2818> cir
   ) {
      ChunkTrackerHolder.get(this.field_16525).onChunkStatusAdded(chunkX, chunkZ, 1);
   }
}
