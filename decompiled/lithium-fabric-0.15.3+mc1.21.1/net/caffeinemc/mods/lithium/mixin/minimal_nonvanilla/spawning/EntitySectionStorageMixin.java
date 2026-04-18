package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.spawning;

import com.google.common.collect.AbstractIterator;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Iterator;
import net.caffeinemc.mods.lithium.common.world.ChunkAwareEntityIterable;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5573;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_5573.class)
public abstract class EntitySectionStorageMixin<T extends class_5568> implements ChunkAwareEntityIterable<T> {
   @Shadow
   @Final
   private Long2ObjectMap<class_5572<T>> field_27252;

   @Override
   public Iterable<T> lithium$IterateEntitiesInTrackedSections() {
      ObjectCollection<class_5572<T>> sections = this.field_27252.values();
      return () -> {
         final ObjectIterator<class_5572<T>> sectionsIterator = sections.iterator();
         return new AbstractIterator<T>() {
            Iterator<T> entityIterator;

            @Nullable
            protected T computeNext() {
               if (this.entityIterator != null && this.entityIterator.hasNext()) {
                  return this.entityIterator.next();
               } else {
                  while (sectionsIterator.hasNext()) {
                     class_5572<T> section = (class_5572<T>)sectionsIterator.next();
                     if (section.method_31768().method_31885() && !section.method_31761()) {
                        this.entityIterator = ((EntitySectionAccessor)section).getCollection().iterator();
                        if (this.entityIterator.hasNext()) {
                           return this.entityIterator.next();
                        }
                     }
                  }

                  return (T)this.endOfData();
               }
            }
         };
      };
   }
}
