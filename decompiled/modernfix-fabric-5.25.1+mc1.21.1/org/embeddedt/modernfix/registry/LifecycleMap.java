package org.embeddedt.modernfix.registry;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.minecraft.class_5321;
import net.minecraft.class_9248;

public class LifecycleMap<T> extends Reference2ReferenceOpenHashMap<class_5321<T>, class_9248> {
   public LifecycleMap() {
      this.defaultReturnValue(class_9248.field_49136);
   }

   public class_9248 put(class_5321<T> t, class_9248 lifecycle) {
      if (lifecycle != this.defRetValue) {
         return (class_9248)super.put(t, lifecycle);
      } else {
         return super.containsKey(t) ? (class_9248)super.get(t) : null;
      }
   }
}
