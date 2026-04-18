package net.caffeinemc.mods.lithium.mixin.ai.task.launch;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.caffeinemc.mods.lithium.common.util.collections.MaskedList;
import net.minecraft.class_1309;
import net.minecraft.class_3218;
import net.minecraft.class_4095;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_4168;
import net.minecraft.class_5996;
import net.minecraft.class_7893;
import net.minecraft.class_4097.class_4098;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_4095.class)
public class BrainMixin<E extends class_1309> {
   @Shadow
   @Final
   private Map<Integer, Map<class_4168, Set<class_7893<? super E>>>> field_18324;
   @Shadow
   @Final
   private Set<class_4168> field_18328;
   private ArrayList<class_7893<? super E>> possibleTasks;
   private MaskedList<class_7893<? super E>> runningTasks;

   private void onTasksChanged() {
      this.runningTasks = null;
      this.onPossibleActivitiesChanged();
   }

   private void onPossibleActivitiesChanged() {
      this.possibleTasks = null;
   }

   private void initPossibleTasks() {
      this.possibleTasks = new ArrayList<>();

      for (Map<class_4168, Set<class_7893<? super E>>> map : this.field_18324.values()) {
         for (Entry<class_4168, Set<class_7893<? super E>>> entry : map.entrySet()) {
            class_4168 activity = entry.getKey();
            if (this.field_18328.contains(activity)) {
               for (class_7893<? super E> task : entry.getValue()) {
                  this.possibleTasks.add(task);
               }
            }
         }
      }
   }

   private ArrayList<class_7893<? super E>> getPossibleTasks() {
      if (this.possibleTasks == null) {
         this.initPossibleTasks();
      }

      return this.possibleTasks;
   }

   private MaskedList<class_7893<? super E>> getCurrentlyRunningTasks() {
      if (this.runningTasks == null) {
         this.initCurrentlyRunningTasks();
      }

      return this.runningTasks;
   }

   private void initCurrentlyRunningTasks() {
      MaskedList<class_7893<? super E>> list = new MaskedList<>(new ObjectArrayList(), false);

      for (Map<class_4168, Set<class_7893<? super E>>> map : this.field_18324.values()) {
         for (Set<class_7893<? super E>> set : map.values()) {
            for (class_7893<? super E> task : set) {
               list.addOrSet(task, task.method_18921() == class_4098.field_18338);
            }
         }
      }

      this.runningTasks = list;
   }

   @Overwrite
   private void method_18891(class_3218 world, E entity) {
      long startTime = world.method_8510();

      for (class_7893<? super E> task : this.getPossibleTasks()) {
         if (task.method_18921() == class_4098.field_18337) {
            task.method_18922(world, entity, startTime);
         }
      }
   }

   @Overwrite
   @Deprecated
   @class_5996
   public List<class_7893<? super E>> method_27074() {
      return this.getCurrentlyRunningTasks();
   }

   @Inject(
      method = "<init>(Ljava/util/Collection;Ljava/util/Collection;Lcom/google/common/collect/ImmutableList;Ljava/util/function/Supplier;)V",
      at = @At("RETURN")
   )
   private void reinitializeBrainCollections(
      Collection<?> memories, Collection<?> sensors, ImmutableList<?> memoryEntries, Supplier<?> codecSupplier, CallbackInfo ci
   ) {
      this.onTasksChanged();
   }

   @Inject(method = "method_24530(Lnet/minecraft/class_4168;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;Ljava/util/Set;)V", at = @At("RETURN"))
   private void reinitializeTasksSorted(
      class_4168 activity,
      ImmutableList<? extends Pair<Integer, ? extends class_7893<?>>> indexedTasks,
      Set<Pair<class_4140<?>, class_4141>> requiredMemories,
      Set<class_4140<?>> forgettingMemories,
      CallbackInfo ci
   ) {
      this.onTasksChanged();
   }

   @Inject(method = "method_35060()V", at = @At("RETURN"))
   private void reinitializeTasksSorted(CallbackInfo ci) {
      this.onTasksChanged();
   }

   @Inject(
      method = "method_18880(Lnet/minecraft/class_4168;)V",
      at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z", shift = Shift.AFTER)
   )
   private void onPossibleActivitiesChanged(class_4168 except, CallbackInfo ci) {
      this.onPossibleActivitiesChanged();
   }

   @Inject(
      method = "method_18900(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_7893;method_18925(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;J)V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void removeStoppedTask(class_3218 world, E entity, CallbackInfo ci, long l, Iterator<?> it, class_7893<? super E> task) {
      if (this.runningTasks != null) {
         this.runningTasks.setVisible(task, false);
      }
   }

   @Inject(
      method = "method_19545(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_7893;method_18923(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;J)V",
         shift = Shift.AFTER
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void removeTaskIfStopped(class_3218 world, E entity, CallbackInfo ci, long l, Iterator<?> it, class_7893<? super E> task) {
      if (this.runningTasks != null && task.method_18921() != class_4098.field_18338) {
         this.runningTasks.setVisible(task, false);
      }
   }

   @ModifyVariable(
      method = "method_18891(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_7893;method_18922(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;J)Z",
         shift = Shift.AFTER
      )
   )
   private class_7893<? super E> addStartedTasks(class_7893<? super E> task) {
      if (this.runningTasks != null && task.method_18921() == class_4098.field_18338) {
         this.runningTasks.setVisible(task, true);
      }

      return task;
   }
}
