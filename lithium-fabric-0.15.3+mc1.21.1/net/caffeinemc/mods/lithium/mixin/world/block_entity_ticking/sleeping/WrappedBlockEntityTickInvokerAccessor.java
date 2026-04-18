package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping;

import net.minecraft.class_5562;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net/minecraft/class_2818$class_5564")
public interface WrappedBlockEntityTickInvokerAccessor {
   @Invoker("method_31727")
   void callSetWrapped(class_5562 var1);

   @Accessor("field_27228")
   class_5562 getWrapped();
}
