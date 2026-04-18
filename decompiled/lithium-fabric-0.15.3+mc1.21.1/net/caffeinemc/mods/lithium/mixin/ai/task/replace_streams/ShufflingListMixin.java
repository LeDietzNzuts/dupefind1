package net.caffeinemc.mods.lithium.mixin.ai.task.replace_streams;

import java.util.Iterator;
import java.util.List;
import net.caffeinemc.mods.lithium.common.ai.WeightedListIterable;
import net.minecraft.class_6032;
import net.minecraft.class_6032.class_6033;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_6032.class)
public class ShufflingListMixin<U> implements WeightedListIterable<U> {
   @Shadow
   @Final
   protected List<class_6033<? extends U>> field_30169;

   @Override
   public Iterator<U> iterator() {
      return new WeightedListIterable.ListIterator<>(this.field_30169.iterator());
   }
}
