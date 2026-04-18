package net.caffeinemc.mods.lithium.common.world.chunk;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_128;
import net.minecraft.class_129;
import net.minecraft.class_148;
import net.minecraft.class_2359;
import net.minecraft.class_2540;
import net.minecraft.class_2835;
import net.minecraft.class_2837;
import net.minecraft.class_6558;
import net.minecraft.class_8703;
import org.jetbrains.annotations.NotNull;

public class LithiumHashPalette<T> implements class_2837<T> {
   private static final int ABSENT_VALUE = -1;
   private final class_2359<T> idList;
   private final class_2835<T> resizeHandler;
   private final int indexBits;
   private final Reference2IntOpenHashMap<T> table;
   private T[] entries;
   private int size = 0;

   private LithiumHashPalette(class_2359<T> idList, class_2835<T> resizeHandler, int indexBits, T[] entries, Reference2IntOpenHashMap<T> table, int size) {
      this.idList = idList;
      this.resizeHandler = resizeHandler;
      this.indexBits = indexBits;
      this.entries = entries;
      this.table = table;
      this.size = size;
   }

   public LithiumHashPalette(class_2359<T> idList, int bits, class_2835<T> resizeHandler, List<T> list) {
      this(idList, bits, resizeHandler);

      for (T t : list) {
         this.addEntry(t);
      }
   }

   public LithiumHashPalette(class_2359<T> idList, int bits, class_2835<T> resizeHandler) {
      this.idList = idList;
      this.indexBits = bits;
      this.resizeHandler = resizeHandler;
      int capacity = 1 << bits;
      this.entries = (T[])(new Object[capacity]);
      this.table = new Reference2IntOpenHashMap(capacity, 0.5F);
      this.table.defaultReturnValue(-1);
   }

   public int method_12291(@NotNull T obj) {
      int id = this.table.getInt(obj);
      if (id == -1) {
         id = this.computeEntry(obj);
      }

      return id;
   }

   public boolean method_19525(@NotNull Predicate<T> predicate) {
      for (int i = 0; i < this.size; i++) {
         if (predicate.test(this.entries[i])) {
            return true;
         }
      }

      return false;
   }

   private int computeEntry(T obj) {
      int id = this.addEntry(obj);
      if (id >= 1 << this.indexBits) {
         if (this.resizeHandler == null) {
            throw new IllegalStateException("Cannot grow");
         }

         id = this.resizeHandler.onResize(this.indexBits + 1, obj);
      }

      return id;
   }

   private int addEntry(T obj) {
      int nextId = this.size;
      if (nextId >= this.entries.length) {
         this.resize(this.size);
      }

      this.table.put(obj, nextId);
      this.entries[nextId] = obj;
      this.size++;
      return nextId;
   }

   private void resize(int neededCapacity) {
      this.entries = (T[])Arrays.copyOf(this.entries, HashCommon.nextPowerOfTwo(neededCapacity + 1));
   }

   @NotNull
   public T method_12288(int id) {
      T[] entries = this.entries;
      T entry = null;
      if (id >= 0 && id < entries.length) {
         entry = entries[id];
      }

      return entry != null ? entry : this.recoverMissingPaletteEntryOrCrash(id);
   }

   @NotNull
   private T recoverMissingPaletteEntryOrCrash(int id) {
      try {
         Thread.sleep(1L);
      } catch (InterruptedException var4) {
      }

      T[] entries = this.entries;
      T entry = null;
      if (id >= 0 && id < entries.length) {
         entry = entries[id];
      }

      if (entry != null) {
         return entry;
      } else {
         throw this.missingPaletteEntryCrash(id);
      }
   }

   private class_148 missingPaletteEntryCrash(int id) {
      try {
         throw new class_6558(id);
      } catch (class_6558 var5) {
         class_128 crashReport = class_128.method_560(var5, "[Lithium] Getting Palette Entry");
         class_129 crashReportCategory = crashReport.method_562("Chunk section");
         crashReportCategory.method_578("IndexBits", this.indexBits);
         crashReportCategory.method_578("Entries", this.entries.length + " Elements: " + Arrays.toString(this.entries));
         crashReportCategory.method_578("Table", this.table.size() + " Elements: " + this.table);
         return new class_148(crashReport);
      }
   }

   public void method_12289(class_2540 buf) {
      this.clear();
      int entryCount = buf.method_10816();

      for (int i = 0; i < entryCount; i++) {
         this.addEntry((T)this.idList.method_39974(buf.method_10816()));
      }
   }

   public void method_12287(class_2540 buf) {
      int size = this.size;
      buf.method_10804(size);

      for (int i = 0; i < size; i++) {
         buf.method_10804(this.idList.method_10206(this.method_12288(i)));
      }
   }

   public int method_12290() {
      int size = class_8703.method_53015(this.size);

      for (int i = 0; i < this.size; i++) {
         size += class_8703.method_53015(this.idList.method_10206(this.method_12288(i)));
      }

      return size;
   }

   public int method_12197() {
      return this.size;
   }

   @NotNull
   public class_2837<T> method_39956() {
      return new LithiumHashPalette<>(this.idList, this.resizeHandler, this.indexBits, (T[])((Object[])this.entries.clone()), this.table.clone(), this.size);
   }

   private void clear() {
      Arrays.fill(this.entries, null);
      this.table.clear();
      this.size = 0;
   }

   public List<T> getElements() {
      T[] copy = (T[])Arrays.copyOf(this.entries, this.size);
      return Arrays.asList(copy);
   }

   public static <A> class_2837<A> create(int bits, class_2359<A> idList, class_2835<A> listener, List<A> list) {
      return new LithiumHashPalette<>(idList, bits, listener, list);
   }
}
