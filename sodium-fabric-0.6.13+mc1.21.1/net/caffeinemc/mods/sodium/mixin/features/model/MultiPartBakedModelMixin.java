package net.caffeinemc.mods.sodium.mixin.features.model;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Predicate;
import net.minecraft.class_1087;
import net.minecraft.class_1095;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1095.class)
public class MultiPartBakedModelMixin {
   @Unique
   private final Map<class_2680, class_1087[]> stateCacheFast = new Reference2ReferenceOpenHashMap();
   @Unique
   private final StampedLock lock = new StampedLock();
   @Shadow
   @Final
   private List<Pair<Predicate<class_2680>, class_1087>> field_5427;

   @Overwrite
   public List<class_777> method_4707(class_2680 state, class_2350 face, class_5819 random) {
      if (state == null) {
         return Collections.emptyList();
      } else {
         long readStamp = this.lock.readLock();

         class_1087[] models;
         try {
            models = this.stateCacheFast.get(state);
         } finally {
            this.lock.unlockRead(readStamp);
         }

         if (models == null) {
            long writeStamp = this.lock.writeLock();

            try {
               List<class_1087> modelList = new ArrayList<>(this.field_5427.size());

               for (Pair<Predicate<class_2680>, class_1087> pair : this.field_5427) {
                  if (((Predicate)pair.getLeft()).test(state)) {
                     modelList.add((class_1087)pair.getRight());
                  }
               }

               models = modelList.toArray(class_1087[]::new);
               this.stateCacheFast.put(state, models);
            } finally {
               this.lock.unlockWrite(writeStamp);
            }
         }

         List<class_777> quads = new ArrayList<>();
         long seed = random.method_43055();

         for (class_1087 model : models) {
            random.method_43052(seed);
            quads.addAll(model.method_4707(state, face, random));
         }

         return quads;
      }
   }
}
