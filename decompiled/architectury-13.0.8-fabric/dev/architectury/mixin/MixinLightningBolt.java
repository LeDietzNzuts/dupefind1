package dev.architectury.mixin;

import dev.architectury.event.events.common.LightningEvent;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1538;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1538.class)
public abstract class MixinLightningBolt extends class_1297 {
   public MixinLightningBolt(class_1299<?> type, class_1937 level) {
      super(type, level);
      throw new IllegalStateException();
   }

   @Inject(
      method = "tick",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/world/level/Level;getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;",
         ordinal = 1,
         shift = Shift.BY,
         by = 1
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void handleLightning(CallbackInfo ci, List<class_1297> list) {
      if (!this.method_31481() && !this.method_37908().field_9236) {
         LightningEvent.STRIKE.invoker().onStrike((class_1538)this, this.method_37908(), this.method_19538(), list);
      }
   }
}
