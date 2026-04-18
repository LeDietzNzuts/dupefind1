package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1540;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1540.class)
public abstract class MixinFallingBlockEntity extends class_1297 {
   @Shadow
   private class_2680 field_7188;

   public MixinFallingBlockEntity(class_1299<?> entityType, class_1937 level) {
      super(entityType, level);
   }

   @Inject(
      method = "tick",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/block/Fallable;onLand(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/item/FallingBlockEntity;)V"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void handleLand(CallbackInfo ci, class_2248 block, class_2338 blockPos2, boolean bl, boolean bl2, double d, class_2680 blockState) {
      BlockEvent.FALLING_LAND.invoker().onLand(this.method_37908(), blockPos2, this.field_7188, blockState, (class_1540)this);
   }
}
