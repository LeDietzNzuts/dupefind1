package net.caffeinemc.mods.lithium.mixin.alloc.deep_passengers;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1297.class)
public abstract class EntityMixin {
   @Shadow
   private ImmutableList<class_1297> field_5979;

   @Overwrite
   public Iterable<class_1297> method_5736() {
      if (this.field_5979.isEmpty()) {
         return Collections.emptyList();
      } else {
         ArrayList<class_1297> passengers = new ArrayList<>();
         this.addPassengersDeep(passengers);
         return passengers;
      }
   }

   @Overwrite
   private Stream<class_1297> method_31484() {
      if (this.field_5979.isEmpty()) {
         return Stream.empty();
      } else {
         ArrayList<class_1297> passengers = new ArrayList<>();
         this.addPassengersDeep(passengers);
         return passengers.stream();
      }
   }

   @Overwrite
   public Stream<class_1297> method_24204() {
      if (this.field_5979.isEmpty()) {
         return Stream.of((class_1297)this);
      } else {
         ArrayList<class_1297> passengers = new ArrayList<>();
         passengers.add((class_1297)this);
         this.addPassengersDeep(passengers);
         return passengers.stream();
      }
   }

   @Overwrite
   public Stream<class_1297> method_31748() {
      if (this.field_5979.isEmpty()) {
         return Stream.of((class_1297)this);
      } else {
         ArrayList<class_1297> passengers = new ArrayList<>();
         this.addPassengersDeepFirst(passengers);
         passengers.add((class_1297)this);
         return passengers.stream();
      }
   }

   private void addPassengersDeep(ArrayList<class_1297> passengers) {
      ImmutableList<class_1297> list = this.field_5979;
      int i = 0;

      for (int listSize = list.size(); i < listSize; i++) {
         class_1297 passenger = (class_1297)list.get(i);
         passengers.add(passenger);
         ((EntityMixin)passenger).addPassengersDeep(passengers);
      }
   }

   private void addPassengersDeepFirst(ArrayList<class_1297> passengers) {
      ImmutableList<class_1297> list = this.field_5979;
      int i = 0;

      for (int listSize = list.size(); i < listSize; i++) {
         class_1297 passenger = (class_1297)list.get(i);
         ((EntityMixin)passenger).addPassengersDeep(passengers);
         passengers.add(passenger);
      }
   }
}
