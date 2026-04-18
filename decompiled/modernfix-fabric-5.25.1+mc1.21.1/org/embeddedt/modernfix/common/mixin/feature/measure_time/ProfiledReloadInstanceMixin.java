package org.embeddedt.modernfix.common.mixin.feature.measure_time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_3302;
import net.minecraft.class_4010;
import net.minecraft.class_4010.class_4046;
import org.embeddedt.modernfix.util.NamedPreparableResourceListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(class_4010.class)
public class ProfiledReloadInstanceMixin {
   @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, ordinal = 0)
   private static List<class_3302> getWrappedListeners(List<class_3302> listeners) {
      List<class_3302> newList = new ArrayList<>(listeners.size());

      for (class_3302 listener : listeners) {
         String className = listener.getClass().getName();
         if (!className.startsWith("net.minecraftforge.") && !className.startsWith("net.neoforged.") && !className.startsWith("net.fabricmc.")) {
            newList.add(new NamedPreparableResourceListener(listener));
         } else {
            newList.add(listener);
         }
      }

      return newList;
   }

   @ModifyVariable(method = "finish", ordinal = 0, argsOnly = true, at = @At("HEAD"))
   private List<class_4046> sortStates(List<class_4046> datapoints) {
      List<class_4046> var2 = new ArrayList<>(datapoints);
      var2.sort(Comparator.<class_4046>comparingLong(s -> s.field_18040.get() + s.field_18041.get()).reversed());
      return var2;
   }
}
