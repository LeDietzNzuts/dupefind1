package malte0811.ferritecore.mixin.dedupbakedquad;

import malte0811.ferritecore.impl.Deduplicator;
import net.minecraft.class_2350;
import net.minecraft.class_777;
import net.minecraft.class_1093.class_1094;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1094.class)
public class SimpleModelBuilderMixin {
   @Inject(method = "addUnculledFace", at = @At("HEAD"))
   public void deduplicate(class_777 quad, CallbackInfoReturnable<class_1094> cir) {
      Deduplicator.deduplicate(quad);
   }

   @Inject(method = "addCulledFace", at = @At("HEAD"))
   public void deduplicate(class_2350 direction, class_777 quad, CallbackInfoReturnable<class_1094> cir) {
      Deduplicator.deduplicate(quad);
   }
}
