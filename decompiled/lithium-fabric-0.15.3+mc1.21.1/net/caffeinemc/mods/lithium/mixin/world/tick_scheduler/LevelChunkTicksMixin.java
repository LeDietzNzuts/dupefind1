package net.caffeinemc.mods.lithium.mixin.world.tick_scheduler;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.longs.Long2ReferenceAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.caffeinemc.mods.lithium.common.world.scheduler.OrderedTickQueue;
import net.minecraft.class_1953;
import net.minecraft.class_2338;
import net.minecraft.class_2499;
import net.minecraft.class_6755;
import net.minecraft.class_6759;
import net.minecraft.class_6760;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_6755.class)
public class LevelChunkTicksMixin<T> {
   private static volatile Reference2IntOpenHashMap<Object> TYPE_2_INDEX = new Reference2IntOpenHashMap();
   private final Long2ReferenceAVLTreeMap<OrderedTickQueue<T>> tickQueuesByTimeAndPriority = new Long2ReferenceAVLTreeMap();
   private OrderedTickQueue<T> nextTickQueue;
   private final IntOpenHashSet allTicks = new IntOpenHashSet();
   @Shadow
   @Nullable
   private BiConsumer<class_6755<T>, class_6760<T>> field_35530;
   @Mutable
   @Shadow
   @Final
   private Set<class_6760<?>> field_35529;
   @Shadow
   @Nullable
   private List<class_6759<T>> field_35528;
   @Mutable
   @Shadow
   @Final
   private Queue<class_6760<T>> field_35527;

   @Inject(method = {"<init>()V", "<init>(Ljava/util/List;)V"}, at = @At("RETURN"))
   private void reinit(CallbackInfo ci) {
      if (this.field_35528 != null) {
         for (class_6759<?> orderedTick : this.field_35528) {
            this.allTicks.add(tickToInt(orderedTick.comp_249(), orderedTick.comp_248()));
         }
      }

      this.field_35529 = null;
      this.field_35527 = null;
   }

   private static int tickToInt(class_2338 pos, Object type) {
      int typeIndex = TYPE_2_INDEX.getInt(type);
      if (typeIndex == -1) {
         typeIndex = fixMissingType2Index(type);
      }

      int ret = (pos.method_10263() & 15) << 16 | (pos.method_10264() & 4095) << 4 | pos.method_10260() & 15;
      return ret | typeIndex << 20;
   }

   private static synchronized int fixMissingType2Index(Object type) {
      int typeIndex = TYPE_2_INDEX.getInt(type);
      if (typeIndex == -1) {
         Reference2IntOpenHashMap<Object> clonedType2Index = TYPE_2_INDEX.clone();
         clonedType2Index.put(type, typeIndex = clonedType2Index.size());
         TYPE_2_INDEX = clonedType2Index;
         if (typeIndex >= 4096) {
            throw new IllegalStateException(
               "Lithium Tick Scheduler assumes at most 4096 different block types that receive scheduled ticks exist! Add mixin.world.tick_scheduler=false to the lithium properties/config to disable the optimization!"
            );
         }
      }

      return typeIndex;
   }

   @Overwrite
   public void method_39363(class_6760<T> orderedTick) {
      int intTick = tickToInt(orderedTick.comp_253(), orderedTick.comp_252());
      if (this.allTicks.add(intTick)) {
         this.queueTick(orderedTick);
      }
   }

   private static long getBucketKey(long time, class_1953 priority) {
      return time << 4 | priority.ordinal() & 15;
   }

   private void updateNextTickQueue(boolean checkEmpty) {
      if (checkEmpty && this.nextTickQueue != null && this.nextTickQueue.isEmpty()) {
         OrderedTickQueue<T> removed = (OrderedTickQueue<T>)this.tickQueuesByTimeAndPriority.remove(this.tickQueuesByTimeAndPriority.firstLongKey());
         if (removed != this.nextTickQueue) {
            throw new IllegalStateException("Next tick queue doesn't have the lowest key!");
         }
      }

      if (this.tickQueuesByTimeAndPriority.isEmpty()) {
         this.nextTickQueue = null;
      } else {
         long firstKey = this.tickQueuesByTimeAndPriority.firstLongKey();
         this.nextTickQueue = (OrderedTickQueue<T>)this.tickQueuesByTimeAndPriority.get(firstKey);
      }
   }

   @Overwrite
   @Nullable
   public class_6760<T> method_39369() {
      return this.nextTickQueue == null ? null : this.nextTickQueue.peek();
   }

   @Overwrite
   @Nullable
   public class_6760<T> method_39371() {
      class_6760<T> orderedTick = this.nextTickQueue.poll();
      if (orderedTick != null) {
         if (this.nextTickQueue.isEmpty()) {
            this.updateNextTickQueue(true);
         }

         this.allTicks.remove(tickToInt(orderedTick.comp_253(), orderedTick.comp_252()));
         return orderedTick;
      } else {
         return null;
      }
   }

   private void queueTick(class_6760<T> orderedTick) {
      OrderedTickQueue<T> tickQueue = (OrderedTickQueue<T>)this.tickQueuesByTimeAndPriority
         .computeIfAbsent(getBucketKey(orderedTick.comp_254(), orderedTick.comp_255()), key -> new OrderedTickQueue());
      if (tickQueue.isEmpty()) {
         this.updateNextTickQueue(false);
      }

      tickQueue.offer(orderedTick);
      if (this.field_35530 != null) {
         this.field_35530.accept((class_6755<T>)this, orderedTick);
      }
   }

   @Overwrite
   public boolean method_8674(class_2338 pos, T type) {
      return this.allTicks.contains(tickToInt(pos, type));
   }

   @Overwrite
   public void method_39367(Predicate<class_6760<T>> predicate) {
      ObjectIterator<OrderedTickQueue<T>> tickQueueIterator = this.tickQueuesByTimeAndPriority.values().iterator();

      while (tickQueueIterator.hasNext()) {
         OrderedTickQueue<T> nextTickQueue = (OrderedTickQueue<T>)tickQueueIterator.next();
         nextTickQueue.sort();
         boolean removed = false;

         for (int i = 0; i < nextTickQueue.size(); i++) {
            class_6760<T> nextTick = nextTickQueue.getTickAtIndex(i);
            if (predicate.test(nextTick)) {
               nextTickQueue.setTickAtIndex(i, null);
               this.allTicks.remove(tickToInt(nextTick.comp_253(), nextTick.comp_252()));
               removed = true;
            }
         }

         if (removed) {
            nextTickQueue.removeNullsAndConsumed();
         }

         if (nextTickQueue.isEmpty()) {
            tickQueueIterator.remove();
         }
      }

      this.updateNextTickQueue(false);
   }

   @Overwrite
   public Stream<class_6760<T>> method_39372() {
      return this.tickQueuesByTimeAndPriority.values().stream().flatMap(Collection::stream);
   }

   @Overwrite
   public int method_20825() {
      return this.allTicks.size();
   }

   @Overwrite
   public class_2499 method_39365(long l, Function<T, String> function) {
      class_2499 nbtList = new class_2499();
      if (this.field_35528 != null) {
         for (class_6759<T> tick : this.field_35528) {
            nbtList.add(tick.method_39404(function));
         }
      }

      ObjectIterator var9 = this.tickQueuesByTimeAndPriority.values().iterator();

      while (var9.hasNext()) {
         OrderedTickQueue<T> nextTickQueue = (OrderedTickQueue<T>)var9.next();

         for (class_6760<T> orderedTick : nextTickQueue) {
            nbtList.add(class_6759.method_39401(orderedTick, function, l));
         }
      }

      return nbtList;
   }

   @Overwrite
   public void method_39364(long time) {
      if (this.field_35528 != null) {
         int i = -this.field_35528.size();

         for (class_6759<T> tick : this.field_35528) {
            this.queueTick(tick.method_39400(time, i++));
         }
      }

      this.field_35528 = null;
   }

   static {
      TYPE_2_INDEX.defaultReturnValue(-1);
   }
}
