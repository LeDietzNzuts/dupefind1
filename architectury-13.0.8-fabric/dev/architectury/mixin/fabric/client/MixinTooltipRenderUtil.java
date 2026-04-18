package dev.architectury.mixin.fabric.client;

import dev.architectury.impl.TooltipEventColorContextImpl;
import net.minecraft.class_8002;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(class_8002.class)
public abstract class MixinTooltipRenderUtil {
   @ModifyConstant(method = "renderTooltipBackground", constant = @Constant(intValue = -267386864))
   private static int modifyTooltipBackgroundColor(int original) {
      return TooltipEventColorContextImpl.CONTEXT.get().getBackgroundColor();
   }

   @ModifyConstant(method = "renderTooltipBackground", constant = @Constant(intValue = 1347420415))
   private static int modifyTooltipOutlineGradientTopColor(int original) {
      return TooltipEventColorContextImpl.CONTEXT.get().getOutlineGradientTopColor();
   }

   @ModifyConstant(method = "renderTooltipBackground", constant = @Constant(intValue = 1344798847))
   private static int modifyTooltipOutlineGradientBottomColor(int original) {
      return TooltipEventColorContextImpl.CONTEXT.get().getOutlineGradientBottomColor();
   }
}
