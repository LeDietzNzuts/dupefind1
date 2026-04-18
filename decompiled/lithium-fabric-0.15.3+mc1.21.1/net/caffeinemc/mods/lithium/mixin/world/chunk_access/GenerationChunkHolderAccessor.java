package net.caffeinemc.mods.lithium.mixin.world.chunk_access;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReferenceArray;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_9259;
import net.minecraft.class_9761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_9761.class)
public interface GenerationChunkHolderAccessor {
   @Accessor("field_51872")
   AtomicReferenceArray<CompletableFuture<class_9259<class_2791>>> lithium$getChunkFuturesByStatus();

   @Invoker("method_60467")
   boolean invokeCannotBeLoaded(class_2806 var1);
}
