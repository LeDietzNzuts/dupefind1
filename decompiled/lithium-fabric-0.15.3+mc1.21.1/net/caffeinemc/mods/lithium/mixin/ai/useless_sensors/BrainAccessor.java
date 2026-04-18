package net.caffeinemc.mods.lithium.mixin.ai.useless_sensors;

import java.util.Map;
import net.minecraft.class_1309;
import net.minecraft.class_4095;
import net.minecraft.class_4148;
import net.minecraft.class_4149;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_4095.class)
public interface BrainAccessor<E extends class_1309> {
   @Accessor("field_18323")
   Map<class_4149<? extends class_4148<? super E>>, class_4148<? super E>> getSensors();
}
