package dev.architectury.mixin.fabric;

import net.minecraft.class_1959;
import net.minecraft.class_1959.class_5482;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1959.class)
public interface BiomeAccessor {
   @Accessor("climateSettings")
   class_5482 getClimateSettings();
}
