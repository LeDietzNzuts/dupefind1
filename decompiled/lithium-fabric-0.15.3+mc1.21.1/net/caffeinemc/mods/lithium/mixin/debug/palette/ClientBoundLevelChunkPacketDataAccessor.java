package net.caffeinemc.mods.lithium.mixin.debug.palette;

import net.minecraft.class_6603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_6603.class)
public interface ClientBoundLevelChunkPacketDataAccessor {
   @Accessor("field_34864")
   byte[] getBuffer();
}
