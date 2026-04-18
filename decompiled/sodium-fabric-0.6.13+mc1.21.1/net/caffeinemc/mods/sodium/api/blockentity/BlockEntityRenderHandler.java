package net.caffeinemc.mods.sodium.api.blockentity;

import net.caffeinemc.mods.sodium.api.internal.DependencyInjection;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import org.jetbrains.annotations.ApiStatus.AvailableSince;
import org.jetbrains.annotations.ApiStatus.Experimental;

@Experimental
@AvailableSince("0.6.0")
public interface BlockEntityRenderHandler {
   BlockEntityRenderHandler INSTANCE = DependencyInjection.load(
      BlockEntityRenderHandler.class, "net.caffeinemc.mods.sodium.client.render.chunk.BlockEntityRenderHandlerImpl"
   );

   static BlockEntityRenderHandler instance() {
      return INSTANCE;
   }

   <T extends class_2586> void addRenderPredicate(class_2591<T> var1, BlockEntityRenderPredicate<T> var2);

   <T extends class_2586> boolean removeRenderPredicate(class_2591<T> var1, BlockEntityRenderPredicate<T> var2);
}
