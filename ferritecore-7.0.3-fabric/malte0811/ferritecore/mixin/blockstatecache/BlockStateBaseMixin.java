package malte0811.ferritecore.mixin.blockstatecache;

import malte0811.ferritecore.impl.BlockStateCacheImpl;
import net.minecraft.class_2680;
import net.minecraft.class_4970.class_4971;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4971.class)
public abstract class BlockStateBaseMixin {
   @Shadow
   protected abstract class_2680 method_26233();

   @Inject(method = "initCache", at = @At("HEAD"))
   public void cacheStateHead(CallbackInfo ci) {
      BlockStateCacheImpl.deduplicateCachePre(this.method_26233());
   }

   @Inject(method = "initCache", at = @At("TAIL"))
   public void cacheStateTail(CallbackInfo ci) {
      BlockStateCacheImpl.deduplicateCachePost(this.method_26233());
   }
}
