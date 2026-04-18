package com.talhanation.smallships.mixin.controlling;

import net.minecraft.class_1690;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1690.class)
public interface BoatAccessor {
   @Accessor
   float getDeltaRotation();

   @Accessor
   void setDeltaRotation(float var1);

   @Accessor
   boolean isInputLeft();

   @Accessor
   boolean isInputRight();

   @Accessor
   boolean isInputUp();

   @Accessor
   boolean isInputDown();
}
