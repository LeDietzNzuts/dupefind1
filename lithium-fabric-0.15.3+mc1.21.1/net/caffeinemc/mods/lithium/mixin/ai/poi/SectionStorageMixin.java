package net.caffeinemc.mods.lithium.mixin.ai.poi;

import com.google.common.collect.AbstractIterator;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.caffeinemc.mods.lithium.common.util.collections.ListeningLong2ObjectOpenHashMap;
import net.caffeinemc.mods.lithium.common.world.interests.RegionBasedStorageSectionExtended;
import net.minecraft.class_1923;
import net.minecraft.class_4076;
import net.minecraft.class_4180;
import net.minecraft.class_5455;
import net.minecraft.class_5539;
import net.minecraft.class_9172;
import net.minecraft.class_9820;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4180.class)
public abstract class SectionStorageMixin<R> implements RegionBasedStorageSectionExtended<R> {
   @Mutable
   @Shadow
   @Final
   private Long2ObjectMap<Optional<R>> field_18692;
   @Shadow
   @Final
   protected class_5539 field_27240;
   private Long2ObjectOpenHashMap<BitSet> columns;

   @Shadow
   protected abstract Optional<R> method_19293(long var1);

   @Shadow
   protected abstract void method_19289(class_1923 var1);

   @Inject(
      method = "<init>(Lnet/minecraft/class_9172;Ljava/util/function/Function;Ljava/util/function/Function;Lnet/minecraft/class_5455;Lnet/minecraft/class_9820;Lnet/minecraft/class_5539;)V",
      at = @At("RETURN")
   )
   private void init(
      class_9172 storageAccess, Function codecFactory, Function factory, class_5455 registryManager, class_9820 errorHandler, class_5539 world, CallbackInfo ci
   ) {
      this.columns = new Long2ObjectOpenHashMap();
      this.field_18692 = new ListeningLong2ObjectOpenHashMap<Optional<R>>(this::onEntryAdded, this::onEntryRemoved);
   }

   private void onEntryRemoved(long key, Optional<R> value) {
      int y = Pos.SectionYIndex.fromSectionCoord(this.field_27240, class_4076.method_18689(key));
      if (y >= 0 && y < Pos.SectionYIndex.getNumYSections(this.field_27240)) {
         int x = class_4076.method_18686(key);
         int z = class_4076.method_18690(key);
         long pos = class_1923.method_8331(x, z);
         BitSet flags = (BitSet)this.columns.get(pos);
         if (flags != null) {
            flags.clear(y);
            if (flags.isEmpty()) {
               this.columns.remove(pos);
            }
         }
      }
   }

   private void onEntryAdded(long key, Optional<R> value) {
      int y = Pos.SectionYIndex.fromSectionCoord(this.field_27240, class_4076.method_18689(key));
      if (y >= 0 && y < Pos.SectionYIndex.getNumYSections(this.field_27240)) {
         int x = class_4076.method_18686(key);
         int z = class_4076.method_18690(key);
         long pos = class_1923.method_8331(x, z);
         BitSet flags = (BitSet)this.columns.get(pos);
         if (flags == null) {
            this.columns.put(pos, flags = new BitSet(Pos.SectionYIndex.getNumYSections(this.field_27240)));
         }

         flags.set(y, value.isPresent());
      }
   }

   @Override
   public Stream<R> lithium$getWithinChunkColumn(int chunkX, int chunkZ) {
      BitSet sectionsWithPOI = this.getNonEmptyPOISections(chunkX, chunkZ);
      if (sectionsWithPOI.isEmpty()) {
         return Stream.empty();
      } else {
         List<R> list = new ArrayList<>();
         int minYSection = Pos.SectionYCoord.getMinYSection(this.field_27240);

         for (int chunkYIndex = sectionsWithPOI.nextSetBit(0); chunkYIndex != -1; chunkYIndex = sectionsWithPOI.nextSetBit(chunkYIndex + 1)) {
            int chunkY = chunkYIndex + minYSection;
            R r = (R)((Optional)this.field_18692.get(class_4076.method_18685(chunkX, chunkY, chunkZ))).orElse(null);
            if (r != null) {
               list.add(r);
            }
         }

         return list.stream();
      }
   }

   @Override
   public Iterable<R> lithium$getInChunkColumn(int chunkX, int chunkZ) {
      BitSet sectionsWithPOI = this.getNonEmptyPOISections(chunkX, chunkZ);
      if (sectionsWithPOI.isEmpty()) {
         return Collections::emptyIterator;
      } else {
         Long2ObjectMap<Optional<R>> loadedElements = this.field_18692;
         class_5539 world = this.field_27240;
         return () -> new AbstractIterator<R>() {
            private int nextBit = sectionsWithPOI.nextSetBit(0);

            protected R computeNext() {
               while (this.nextBit >= 0) {
                  Optional<R> next = (Optional<R>)loadedElements.get(
                     class_4076.method_18685(chunkX, Pos.SectionYCoord.fromSectionIndex(world, this.nextBit), chunkZ)
                  );
                  this.nextBit = sectionsWithPOI.nextSetBit(this.nextBit + 1);
                  if (next.isPresent()) {
                     return next.get();
                  }
               }

               return (R)this.endOfData();
            }
         };
      }
   }

   private BitSet getNonEmptyPOISections(int chunkX, int chunkZ) {
      long pos = class_1923.method_8331(chunkX, chunkZ);
      BitSet flags = this.getNonEmptySections(pos, false);
      if (flags != null) {
         return flags;
      } else {
         this.method_19289(new class_1923(pos));
         return this.getNonEmptySections(pos, true);
      }
   }

   private BitSet getNonEmptySections(long pos, boolean required) {
      BitSet set = (BitSet)this.columns.get(pos);
      if (set == null && required) {
         throw new NullPointerException("No data is present for column: " + new class_1923(pos));
      } else {
         return set;
      }
   }
}
