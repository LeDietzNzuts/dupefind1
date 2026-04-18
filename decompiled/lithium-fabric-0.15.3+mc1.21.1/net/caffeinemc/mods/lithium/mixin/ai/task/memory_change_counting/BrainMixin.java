package net.caffeinemc.mods.lithium.mixin.ai.task.memory_change_counting;

import java.util.Map;
import java.util.Optional;
import net.caffeinemc.mods.lithium.common.ai.MemoryModificationCounter;
import net.minecraft.class_4095;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4095.class)
public class BrainMixin implements MemoryModificationCounter {
   private long memoryModCount = 1L;

   @Redirect(
      method = "method_24535(Lnet/minecraft/class_4140;Ljava/util/Optional;)V",
      at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")
   )
   private Object increaseMemoryModificationCount(Map<Object, Object> map, Object key, Object newValue) {
      Object oldValue = map.put(key, newValue);
      if (oldValue == null || ((Optional)oldValue).isPresent() != ((Optional)newValue).isPresent()) {
         this.memoryModCount++;
      }

      return oldValue;
   }

   @Override
   public long lithium$getModCount() {
      return this.memoryModCount;
   }

   @Inject(method = "method_18911()Lnet/minecraft/class_4095;", at = @At("RETURN"))
   private void copyModCount(CallbackInfoReturnable<class_4095<?>> cir) {
      class_4095<?> newBrain = (class_4095<?>)cir.getReturnValue();
      ((BrainMixin)newBrain).memoryModCount = this.memoryModCount + 1L;
   }
}
