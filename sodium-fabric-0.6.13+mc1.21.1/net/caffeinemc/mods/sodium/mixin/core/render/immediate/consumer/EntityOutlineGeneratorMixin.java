package net.caffeinemc.mods.sodium.mixin.core.render.immediate.consumer;

import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.vertex.attributes.common.ColorAttribute;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.class_293;
import net.minecraft.class_296;
import net.minecraft.class_4588;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/class_4618$class_4586")
public abstract class EntityOutlineGeneratorMixin implements VertexBufferWriter {
   @Shadow
   @Final
   private class_4588 comp_2851;
   @Shadow
   @Final
   private int comp_2852;
   @Unique
   private boolean canUseIntrinsics;

   @Inject(method = "<init>(Lnet/minecraft/class_4588;I)V", at = @At("RETURN"))
   private void onInit(CallbackInfo ci) {
      this.canUseIntrinsics = VertexBufferWriter.tryOf(this.comp_2851) != null;
   }

   @Override
   public boolean canUseIntrinsics() {
      return this.canUseIntrinsics;
   }

   @Override
   public void push(MemoryStack stack, long ptr, int count, class_293 format) {
      transform(ptr, count, format, this.comp_2852);
      VertexBufferWriter.of(this.comp_2851).push(stack, ptr, count, format);
   }

   @Unique
   private static void transform(long ptr, int count, class_293 format, int color) {
      long stride = format.method_1362();
      long offsetColor = format.method_60835(class_296.field_52108);

      for (int vertexIndex = 0; vertexIndex < count; vertexIndex++) {
         ColorAttribute.set(ptr + offsetColor, ColorARGB.toABGR(color));
         ptr += stride;
      }
   }
}
