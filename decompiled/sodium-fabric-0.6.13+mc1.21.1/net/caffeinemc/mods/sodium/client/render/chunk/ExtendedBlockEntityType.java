package net.caffeinemc.mods.sodium.client.render.chunk;

import net.caffeinemc.mods.sodium.api.blockentity.BlockEntityRenderPredicate;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;

public interface ExtendedBlockEntityType<T extends class_2586> {
   BlockEntityRenderPredicate<T>[] sodium$getRenderPredicates();

   void sodium$addRenderPredicate(BlockEntityRenderPredicate<T> var1);

   boolean sodium$removeRenderPredicate(BlockEntityRenderPredicate<T> var1);

   static <T extends class_2586> boolean shouldRender(class_2591<? extends T> type, class_1922 blockGetter, class_2338 blockPos, T entity) {
      BlockEntityRenderPredicate<T>[] predicates = ((ExtendedBlockEntityType)type).sodium$getRenderPredicates();

      for (int i = 0; i < predicates.length; i++) {
         if (!predicates[i].shouldRender(blockGetter, blockPos, entity)) {
            return false;
         }
      }

      return true;
   }

   static <T extends class_2586> void addRenderPredicate(class_2591<T> type, BlockEntityRenderPredicate<T> predicate) {
      ((ExtendedBlockEntityType)type).sodium$addRenderPredicate(predicate);
   }

   static <T extends class_2586> boolean removeRenderPredicate(class_2591<T> type, BlockEntityRenderPredicate<T> predicate) {
      return ((ExtendedBlockEntityType)type).sodium$removeRenderPredicate(predicate);
   }
}
