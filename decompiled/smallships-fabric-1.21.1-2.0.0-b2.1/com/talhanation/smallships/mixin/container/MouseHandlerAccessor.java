package com.talhanation.smallships.mixin.container;

import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_312.class)
public interface MouseHandlerAccessor {
   @Accessor
   void setMouseGrabbed(boolean var1);

   @Accessor
   void setXpos(double var1);

   @Accessor
   void setYpos(double var1);
}
