package com.talhanation.smallships.mixin.container;

import net.minecraft.class_1277;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1277.class)
public interface SimpleContainerAccessor {
   @Accessor
   class_2371<class_1799> getItems();
}
