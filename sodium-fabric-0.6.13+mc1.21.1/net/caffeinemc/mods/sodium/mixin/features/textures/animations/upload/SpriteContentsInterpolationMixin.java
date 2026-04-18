package net.caffeinemc.mods.sodium.mixin.features.textures.animations.upload;

import java.util.List;
import net.caffeinemc.mods.sodium.api.util.ColorMixer;
import net.caffeinemc.mods.sodium.client.util.NativeImageHelper;
import net.caffeinemc.mods.sodium.mixin.features.textures.SpriteContentsInvoker;
import net.minecraft.class_1011;
import net.minecraft.class_7764;
import net.minecraft.class_7764.class_4728;
import net.minecraft.class_7764.class_5790;
import net.minecraft.class_7764.class_5791;
import net.minecraft.class_7764.class_7765;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4728.class)
public class SpriteContentsInterpolationMixin {
   @Shadow
   @Final
   private class_1011[] field_21758;
   @Unique
   private class_7764 parent;
   @Unique
   private static final int STRIDE = 4;

   @Inject(method = "<init>(Lnet/minecraft/class_7764;)V", at = @At("RETURN"))
   public void assignParent(class_7764 parent, CallbackInfo ci) {
      this.parent = parent;
   }

   @Overwrite
   void method_24128(int x, int y, class_7765 arg) {
      class_5790 animation = ((SpriteContentsTickerAccessor)arg).getField_40546();
      SpriteContentsAnimatedTextureAccessor animation2 = (SpriteContentsAnimatedTextureAccessor)((SpriteContentsTickerAccessor)arg).getField_40546();
      List<class_5791> frames = ((SpriteContentsAnimatedTextureAccessor)animation).getField_28472();
      SpriteContentsTickerAccessor accessor = (SpriteContentsTickerAccessor)arg;
      SpriteContentsFrameInfoAccessor animationFrame = (SpriteContentsFrameInfoAccessor)frames.get(accessor.getFrameIndex());
      int curIndex = animationFrame.getField_28475();
      int nextIndex = ((SpriteContentsFrameInfoAccessor)animation2.getField_28472().get((accessor.getFrameIndex() + 1) % frames.size())).getField_28475();
      if (curIndex != nextIndex) {
         float mix = 1.0F - (float)accessor.getFrameTicks() / animationFrame.getField_28476();

         for (int layer = 0; layer < this.field_21758.length; layer++) {
            int width = this.parent.method_45807() >> layer;
            int height = this.parent.method_45815() >> layer;
            int curX = curIndex % animation2.getField_28473() * width;
            int curY = curIndex / animation2.getField_28473() * height;
            int nextX = nextIndex % animation2.getField_28473() * width;
            int nextY = nextIndex / animation2.getField_28473() * height;
            class_1011 src = ((SpriteContentsAccessor)this.parent).getImages()[layer];
            class_1011 dst = this.field_21758[layer];
            long ppSrcPixel = NativeImageHelper.getPointerRGBA(src);
            long ppDstPixel = NativeImageHelper.getPointerRGBA(dst);

            for (int layerY = 0; layerY < height; layerY++) {
               long pRgba1 = ppSrcPixel + (curX + (long)(curY + layerY) * src.method_4307()) * 4L;
               long pRgba2 = ppSrcPixel + (nextX + (long)(nextY + layerY) * src.method_4307()) * 4L;

               for (int layerX = 0; layerX < width; layerX++) {
                  int rgba1 = MemoryUtil.memGetInt(pRgba1);
                  int rgba2 = MemoryUtil.memGetInt(pRgba2);
                  int mixedRgb = ColorMixer.mix(rgba1, rgba2, mix) & 16777215;
                  int alpha = rgba1 & 0xFF000000;
                  MemoryUtil.memPutInt(ppDstPixel, mixedRgb | alpha);
                  pRgba1 += 4L;
                  pRgba2 += 4L;
                  ppDstPixel += 4L;
               }
            }
         }

         ((SpriteContentsInvoker)this.parent).invokeMethod_45811(x, y, 0, 0, this.field_21758);
      }
   }
}
