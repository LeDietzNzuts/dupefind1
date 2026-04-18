package ewewukek.musketmod;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1282;
import net.minecraft.class_1297;

public class DeferredDamage {
   private static Map<class_1297, DeferredDamage.Entry> entries = new HashMap<>();

   public static void hurt(class_1297 target, class_1282 source, float damage, float igniteSeconds) {
      DeferredDamage.Entry entry = entries.get(target);
      if (entry == null) {
         entry = new DeferredDamage.Entry();
         entries.put(target, entry);
      }

      entry.source = source;
      entry.damage += damage;
      entry.igniteSeconds = Math.min(5.0F, entry.igniteSeconds + igniteSeconds);
   }

   public static void apply() {
      entries.forEach((target, entry) -> {
         target.field_6008 = 0;
         target.method_5643(entry.source, entry.damage);
         if (entry.igniteSeconds > 0.0F) {
            target.method_5639(entry.igniteSeconds);
         }
      });
      entries.clear();
   }

   private static class Entry {
      class_1282 source;
      float damage;
      float igniteSeconds;
   }
}
