package net.caffeinemc.mods.lithium.mixin.ai.useless_sensors;

import net.minecraft.class_4148;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_4148.class)
public interface SensorAccessor {
   @Accessor("field_18463")
   long getLastSenseTime();

   @Accessor("field_18464")
   int getSenseInterval();

   @Accessor("field_18463")
   void setLastSenseTime(long var1);
}
