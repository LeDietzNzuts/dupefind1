package net.caffeinemc.mods.lithium.mixin.util.accessors;

import java.util.UUID;
import net.minecraft.class_1542;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1542.class)
public interface ItemEntityAccessor {
   @Accessor("field_41893")
   UUID lithium$getOwner();
}
