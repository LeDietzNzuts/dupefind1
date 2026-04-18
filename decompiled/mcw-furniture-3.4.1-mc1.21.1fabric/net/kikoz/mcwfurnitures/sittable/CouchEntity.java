package net.kikoz.mcwfurnitures.sittable;

import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2604;
import net.minecraft.class_3231;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5275;
import net.minecraft.class_9062;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2945.class_9222;
import net.minecraft.class_5132.class_5133;

public class CouchEntity extends class_1297 {
   private class_2338 chair;

   public CouchEntity(class_1299<?> entityType, class_1937 world) {
      super(entityType, world);
      this.method_5875(true);
   }

   protected void method_5693(class_9222 builder) {
   }

   public CouchEntity(class_1299<CouchEntity> entityType, class_1937 world, class_2338 pos, double doub) {
      this(entityType, world);
      this.chair = pos;
      this.method_23327(this.chair.method_10263() + 0.5, this.chair.method_10264() + doub, this.chair.method_10260() + 0.5);
   }

   public static class_5133 createMobAttributes() {
      return class_5132.method_26861()
         .method_26868(class_5134.field_23716, 1.0)
         .method_26868(class_5134.field_23719, 0.0)
         .method_26868(class_5134.field_23717, 0.0)
         .method_26868(class_5134.field_23721, 0.0);
   }

   protected void method_5749(class_2487 nbt) {
   }

   protected void method_5652(class_2487 nbt) {
   }

   public class_2596<class_2602> method_18002(class_3231 entityTrackerEntry) {
      return new class_2604(this, entityTrackerEntry);
   }

   public void method_5773() {
      super.method_5773();
      if (this.chair == null) {
         this.chair = this.method_24515();
      }

      if (!this.method_37908().field_9236 && (this.method_5685().isEmpty() || this.method_37908().method_8320(this.chair).method_26215())) {
         this.method_5650(class_5529.field_26999);
      }
   }

   public static class_9062 create(class_1937 world, class_2338 pos, double doub, class_1657 player) {
      if (!world.method_8608()) {
         class_1299<CouchEntity> entityType = MacawsFurniture.COUCH_ENTITY_TYPE;
         List<CouchEntity> seatsAtPos = world.method_18023(entityType, new class_238(pos), entity -> entity.chair != null && entity.chair.equals(pos));
         if (seatsAtPos.isEmpty()) {
            CouchEntity chair = new CouchEntity(entityType, world, pos, doub);
            chair.method_30634(pos.method_10263() + 0.5, pos.method_10264() + 0.1, pos.method_10260() + 0.5);
            world.method_8649(chair);
            player.method_5804(chair);
         }
      }

      return class_9062.field_47728;
   }

   protected class_243 method_52533(class_1297 passenger, class_4048 dimensions, float scaleFactor) {
      return new class_243(0.0, dimensions.comp_2186() - 0.7F * scaleFactor, 0.0);
   }

   public class_243 method_24829(class_1309 passenger) {
      class_2350 direction = this.method_5755();
      if (direction.method_10166() == class_2351.field_11052) {
         return super.method_24829(passenger);
      } else {
         int[][] is = class_5275.method_27934(direction);
         class_2338 blockPos = this.method_24515();
         class_2339 mutable = new class_2339();
         UnmodifiableIterator var6 = passenger.method_24831().iterator();

         while (var6.hasNext()) {
            class_4050 entityPose = (class_4050)var6.next();
            class_238 box = passenger.method_24833(entityPose);

            for (int[] js : is) {
               mutable.method_10103(blockPos.method_10263() + js[0], blockPos.method_10264(), blockPos.method_10260() + js[1]);
               double d = this.method_37908().method_30347(mutable);
               if (class_5275.method_27932(d)) {
                  class_243 vec3d = class_243.method_26410(mutable, d);
                  if (class_5275.method_27933(this.method_37908(), passenger, box.method_997(vec3d))) {
                     passenger.method_18380(entityPose);
                     return vec3d;
                  }
               }
            }
         }

         return super.method_24829(passenger);
      }
   }
}
