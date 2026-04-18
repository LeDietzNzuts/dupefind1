package org.embeddedt.modernfix.dynamicresources;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_2960;
import net.minecraft.class_4231;
import org.embeddedt.modernfix.ModernFix;
import org.jetbrains.annotations.NotNull;

public class DynamicSoundHelpers {
   private static final long SOUND_EVICTION_DELAY = TimeUnit.SECONDS.toNanos(30L);
   private static final boolean debugDynamicSoundLoading = Boolean.getBoolean("modernfix.debugDynamicSoundLoading");

   public static final class Cache extends AbstractMap<class_2960, CompletableFuture<class_4231>> {
      private final Object2ObjectLinkedOpenHashMap<class_2960, DynamicSoundHelpers.Cache.Entry> store = new Object2ObjectLinkedOpenHashMap();

      public Cache(Map<class_2960, CompletableFuture<class_4231>> otherMap) {
         this.putAll(otherMap);
      }

      private void checkExpired() {
         long ts = System.nanoTime();
         ObjectBidirectionalIterator<it.unimi.dsi.fastutil.objects.Object2ObjectMap.Entry<class_2960, DynamicSoundHelpers.Cache.Entry>> iter = this.store
            .object2ObjectEntrySet()
            .fastIterator();

         while (iter.hasNext()) {
            it.unimi.dsi.fastutil.objects.Object2ObjectMap.Entry<class_2960, DynamicSoundHelpers.Cache.Entry> entry = (it.unimi.dsi.fastutil.objects.Object2ObjectMap.Entry<class_2960, DynamicSoundHelpers.Cache.Entry>)iter.next();
            if (!((DynamicSoundHelpers.Cache.Entry)entry.getValue()).isExpired(ts)) {
               break;
            }

            if (DynamicSoundHelpers.debugDynamicSoundLoading) {
               ModernFix.LOGGER
                  .warn("Evicted sound {} with duration {} ms", entry.getKey(), ((DynamicSoundHelpers.Cache.Entry)entry.getValue()).getDuration() / 1000000L);
            }

            ((DynamicSoundHelpers.Cache.Entry)entry.getValue()).discard();
            iter.remove();
         }
      }

      public CompletableFuture<class_4231> get(Object key) {
         if (key instanceof class_2960 rl) {
            DynamicSoundHelpers.Cache.Entry entry = (DynamicSoundHelpers.Cache.Entry)this.store.getAndMoveToLast(rl);
            CompletableFuture<class_4231> result = entry != null ? entry.getBuffer() : null;
            this.checkExpired();
            return result;
         } else {
            return null;
         }
      }

      public CompletableFuture<class_4231> put(class_2960 key, CompletableFuture<class_4231> value) {
         DynamicSoundHelpers.Cache.Entry entry = new DynamicSoundHelpers.Cache.Entry(value);
         if (DynamicSoundHelpers.debugDynamicSoundLoading) {
            ModernFix.LOGGER.info("Loaded sound {}", key);
         }

         DynamicSoundHelpers.Cache.Entry previousEntry = (DynamicSoundHelpers.Cache.Entry)this.store.putAndMoveToLast(key, entry);
         return previousEntry != null ? previousEntry.getBuffer() : null;
      }

      @NotNull
      @Override
      public Set<Entry<class_2960, CompletableFuture<class_4231>>> entrySet() {
         return new DynamicSoundHelpers.Cache.EntrySet();
      }

      private static class Entry {
         private final CompletableFuture<class_4231> buffer;
         private long lastAccessTime;

         private Entry(CompletableFuture<class_4231> buffer) {
            this.buffer = buffer;
            this.lastAccessTime = System.nanoTime();
         }

         public CompletableFuture<class_4231> getBuffer() {
            this.lastAccessTime = System.nanoTime();
            return this.buffer;
         }

         public long getDuration() {
            class_4231 buf = this.buffer.getNow(null);
            return buf == null ? 0L : ((DynamicSoundHelpers.SoundBufAccess)buf).mfix$getDurationNanos();
         }

         public boolean isExpired(long currentTs) {
            long duration = this.getDuration();
            return duration > 0L && currentTs - this.lastAccessTime >= duration + DynamicSoundHelpers.SOUND_EVICTION_DELAY;
         }

         public void discard() {
            this.buffer.thenAccept(class_4231::method_19687);
         }

         @Override
         public String toString() {
            return super.toString();
         }
      }

      private class EntrySet extends AbstractSet<java.util.Map.Entry<class_2960, CompletableFuture<class_4231>>> {
         @Override
         public Iterator<java.util.Map.Entry<class_2960, CompletableFuture<class_4231>>> iterator() {
            final ObjectBidirectionalIterator<java.util.Map.Entry<class_2960, DynamicSoundHelpers.Cache.Entry>> storeIter = Cache.this.store
               .entrySet()
               .iterator();
            return new Iterator<java.util.Map.Entry<class_2960, CompletableFuture<class_4231>>>() {
               @Override
               public boolean hasNext() {
                  return storeIter.hasNext();
               }

               public java.util.Map.Entry<class_2960, CompletableFuture<class_4231>> next() {
                  java.util.Map.Entry<class_2960, DynamicSoundHelpers.Cache.Entry> entry = (java.util.Map.Entry<class_2960, DynamicSoundHelpers.Cache.Entry>)storeIter.next();
                  return new SimpleImmutableEntry<>(entry.getKey(), entry.getValue().buffer);
               }
            };
         }

         @Override
         public int size() {
            return Cache.this.store.size();
         }

         @Override
         public void clear() {
            Cache.this.store.clear();
         }
      }
   }

   public interface SoundBufAccess {
      long mfix$getDurationNanos();
   }
}
