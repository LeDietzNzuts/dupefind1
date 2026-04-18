package net.caffeinemc.mods.lithium.mixin.alloc.chunk_ticking;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import net.minecraft.class_3193;
import net.minecraft.class_3215;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3215.class)
public class ServerChunkCacheMixin {
   private final ArrayList<class_3193> cachedChunkList = new ArrayList<>();

   @Redirect(
      method = "method_14161()V",
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayListWithCapacity(I)Ljava/util/ArrayList;", remap = false)
   )
   private ArrayList<class_3193> redirectChunksListClone(int initialArraySize) {
      ArrayList<class_3193> list = this.cachedChunkList;
      list.clear();
      list.ensureCapacity(initialArraySize);
      return list;
   }

   @Inject(method = "method_12127(Ljava/util/function/BooleanSupplier;Z)V", at = @At("HEAD"))
   private void preTick(BooleanSupplier shouldKeepTicking, boolean tickChunks, CallbackInfo ci) {
      this.cachedChunkList.clear();
   }
}
