package malte0811.ferritecore.mixin.datacomponents;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMaps;
import java.util.Optional;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_9335.class)
public class PatchedDataComponentMapMixin {
   @Shadow
   private Reference2ObjectMap<class_9331<?>, Optional<?>> field_49655;
   @Shadow
   private boolean field_49656;

   @Inject(method = {"applyPatch(Lnet/minecraft/core/component/DataComponentPatch;)V", "restorePatch"}, at = @At("RETURN"))
   private void saveMemoryIfEmpty(CallbackInfo ci) {
      if (this.field_49655.isEmpty()) {
         this.field_49655 = Reference2ObjectMaps.emptyMap();
         this.field_49656 = true;
      }
   }

   @Inject(method = {"set", "remove"}, at = @At("RETURN"))
   private void saveMemoryIfEmptyWithReturn(CallbackInfoReturnable<?> ci) {
      this.saveMemoryIfEmpty(ci);
   }
}
