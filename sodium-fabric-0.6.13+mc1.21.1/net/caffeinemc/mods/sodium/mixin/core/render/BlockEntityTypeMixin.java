package net.caffeinemc.mods.sodium.mixin.core.render;

import net.caffeinemc.mods.sodium.api.blockentity.BlockEntityRenderPredicate;
import net.caffeinemc.mods.sodium.client.render.chunk.ExtendedBlockEntityType;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_2591.class)
public class BlockEntityTypeMixin<T extends class_2586> implements ExtendedBlockEntityType<T> {
   @Unique
   private BlockEntityRenderPredicate<T>[] sodium$renderPredicates = new BlockEntityRenderPredicate[0];

   @Override
   public BlockEntityRenderPredicate<T>[] sodium$getRenderPredicates() {
      return this.sodium$renderPredicates;
   }

   @Override
   public void sodium$addRenderPredicate(BlockEntityRenderPredicate<T> predicate) {
      this.sodium$renderPredicates = (BlockEntityRenderPredicate<T>[])ArrayUtils.add(this.sodium$renderPredicates, predicate);
   }

   @Override
   public boolean sodium$removeRenderPredicate(BlockEntityRenderPredicate<T> predicate) {
      int index = ArrayUtils.indexOf(this.sodium$renderPredicates, predicate);
      if (index == -1) {
         return false;
      } else {
         this.sodium$renderPredicates = (BlockEntityRenderPredicate<T>[])ArrayUtils.remove(this.sodium$renderPredicates, index);
         return true;
      }
   }
}
