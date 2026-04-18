package dev.architectury.mixin.fabric;

import net.minecraft.class_1755;
import net.minecraft.class_3611;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1755.class)
public interface BucketItemAccessor {
   @Accessor("content")
   class_3611 getContent();
}
