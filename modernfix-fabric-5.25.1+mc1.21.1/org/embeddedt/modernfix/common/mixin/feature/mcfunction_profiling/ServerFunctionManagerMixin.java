package org.embeddedt.modernfix.common.mixin.feature.mcfunction_profiling;

import com.google.common.base.Stopwatch;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_2158;
import net.minecraft.class_2168;
import net.minecraft.class_2960;
import net.minecraft.class_2991;
import org.embeddedt.modernfix.duck.IProfilingServerFunctionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2991.class)
public class ServerFunctionManagerMixin implements IProfilingServerFunctionManager {
   @Shadow
   @Final
   private static class_2960 field_13417;
   private final Map<class_2960, Stopwatch> mfix$functionWatches = new Object2ObjectOpenHashMap();

   @Inject(method = "executeTagFunctions", at = @At("HEAD"))
   private void resetWatches(Collection<class_2158<class_2168>> functionObjects, class_2960 identifier, CallbackInfo ci) {
      this.mfix$functionWatches.values().forEach(Stopwatch::reset);
   }

   @Inject(
      method = "executeTagFunctions",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/server/ServerFunctionManager;execute(Lnet/minecraft/commands/functions/CommandFunction;Lnet/minecraft/commands/CommandSourceStack;)V"
      )
   )
   private void startWatch(
      Collection<class_2158<class_2168>> functionObjects,
      class_2960 identifier,
      CallbackInfo ci,
      @Local(ordinal = 0) class_2158<class_2168> function,
      @Share("stopwatch") LocalRef<Stopwatch> watchRef
   ) {
      watchRef.set(null);
      if (identifier == field_13417) {
         Stopwatch watch = this.mfix$functionWatches.computeIfAbsent(function.comp_1994(), i -> Stopwatch.createUnstarted());
         watch.start();
         watchRef.set(watch);
      }
   }

   @Inject(
      method = "executeTagFunctions",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/server/ServerFunctionManager;execute(Lnet/minecraft/commands/functions/CommandFunction;Lnet/minecraft/commands/CommandSourceStack;)V",
         shift = Shift.AFTER
      )
   )
   private void stopWatch(
      Collection<class_2158<class_2168>> functionObjects, class_2960 identifier, CallbackInfo ci, @Share("stopwatch") LocalRef<Stopwatch> watchRef
   ) {
      Stopwatch watch = (Stopwatch)watchRef.get();
      if (watch != null && watch.isRunning()) {
         watch.stop();
      }
   }

   @Inject(method = "executeTagFunctions", at = @At("RETURN"))
   private void pruneUnusedWatches(Collection<class_2158<class_2168>> functionObjects, class_2960 identifier, CallbackInfo ci) {
      this.mfix$functionWatches.values().removeIf(watch -> watch.elapsed().isZero());
   }

   @Override
   public String mfix$getProfilingResults() {
      ArrayList<Entry<class_2960, Stopwatch>> list = new ArrayList<>(this.mfix$functionWatches.entrySet());
      list.sort(Comparator.<Entry<class_2960, Stopwatch>, Duration>comparing(e -> e.getValue().elapsed()).reversed());
      StringBuilder sb = new StringBuilder();

      for (Entry<class_2960, Stopwatch> entry : list) {
         sb.append(entry.getKey().toString());
         sb.append(" - ");
         sb.append(entry.getValue().toString());
         sb.append('\n');
      }

      return sb.toString();
   }
}
