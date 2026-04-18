package net.caffeinemc.mods.sodium.mixin.core.render;

import it.unimi.dsi.fastutil.ints.IntList;
import java.util.List;
import net.caffeinemc.mods.sodium.api.vertex.format.VertexFormatExtensions;
import net.caffeinemc.mods.sodium.api.vertex.format.VertexFormatRegistry;
import net.minecraft.class_293;
import net.minecraft.class_296;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_293.class)
public class VertexFormatMixin implements VertexFormatExtensions {
   private int sodium$globalId;

   @Inject(method = "<init>(Ljava/util/List;Ljava/util/List;Lit/unimi/dsi/fastutil/ints/IntList;I)V", at = @At("RETURN"))
   private void afterInit(List<class_296> elements, List<String> names, IntList offsets, int vertexSize, CallbackInfo ci) {
      this.sodium$globalId = VertexFormatRegistry.instance().allocateGlobalId((class_293)this);
   }

   @Unique
   @Override
   public int sodium$getGlobalId() {
      return this.sodium$globalId;
   }
}
