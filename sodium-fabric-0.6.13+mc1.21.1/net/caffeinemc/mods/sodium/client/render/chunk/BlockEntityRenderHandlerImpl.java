package net.caffeinemc.mods.sodium.client.render.chunk;

import net.caffeinemc.mods.sodium.api.blockentity.BlockEntityRenderHandler;
import net.caffeinemc.mods.sodium.api.blockentity.BlockEntityRenderPredicate;
import net.minecraft.class_2586;
import net.minecraft.class_2591;

public class BlockEntityRenderHandlerImpl implements BlockEntityRenderHandler {
   @Override
   public <T extends class_2586> void addRenderPredicate(class_2591<T> type, BlockEntityRenderPredicate<T> predicate) {
      ExtendedBlockEntityType.addRenderPredicate(type, predicate);
   }

   @Override
   public <T extends class_2586> boolean removeRenderPredicate(class_2591<T> type, BlockEntityRenderPredicate<T> predicate) {
      return ExtendedBlockEntityType.removeRenderPredicate(type, predicate);
   }
}
