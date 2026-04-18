package org.embeddedt.modernfix.chunk;

import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_3218;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import org.jetbrains.annotations.Nullable;

public class SafeBlockGetter implements class_1922 {
   private final class_3218 wrapped;
   private final Thread mainThread;

   public SafeBlockGetter(class_3218 wrapped) {
      this.wrapped = wrapped;
      this.mainThread = Thread.currentThread();
   }

   public boolean shouldUse() {
      return Thread.currentThread() != this.mainThread;
   }

   @Nullable
   private class_1922 getChunkSafe(class_2338 pos) {
      class_1922 access = this.wrapped.method_14178().method_12246(pos.method_10263() >> 4, pos.method_10260() >> 4);
      if (!(access instanceof class_2791 chunk)) {
         return null;
      } else {
         return !chunk.method_12009().method_12165(class_2806.field_12803) ? null : chunk;
      }
   }

   public int method_31600() {
      return this.wrapped.method_31600();
   }

   public int method_8315() {
      return this.wrapped.method_8315();
   }

   public int method_31607() {
      return this.wrapped.method_31607();
   }

   public int method_31605() {
      return this.wrapped.method_31605();
   }

   @Nullable
   public class_2586 method_8321(class_2338 pos) {
      class_1922 g = this.getChunkSafe(pos);
      return g == null ? null : g.method_8321(pos);
   }

   public class_2680 method_8320(class_2338 pos) {
      class_1922 g = this.getChunkSafe(pos);
      return g == null ? class_2246.field_10124.method_9564() : g.method_8320(pos);
   }

   public class_3610 method_8316(class_2338 pos) {
      class_1922 g = this.getChunkSafe(pos);
      return g == null ? class_3612.field_15906.method_15785() : g.method_8316(pos);
   }
}
