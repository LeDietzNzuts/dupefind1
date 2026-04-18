package vectorwing.farmersdelight.common.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_2392;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_3857;
import net.minecraft.class_3966;
import net.minecraft.class_6328;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;

@class_6328
public class RottenTomatoEntity extends class_3857 {
   public RottenTomatoEntity(class_1299<? extends RottenTomatoEntity> entityType, class_1937 level) {
      super(entityType, level);
   }

   public RottenTomatoEntity(class_1937 level, class_1309 entity) {
      super(ModEntityTypes.ROTTEN_TOMATO.get(), entity, level);
   }

   public RottenTomatoEntity(class_1937 level, double x, double y, double z) {
      super(ModEntityTypes.ROTTEN_TOMATO.get(), x, y, z, level);
   }

   protected class_1792 method_16942() {
      return ModItems.ROTTEN_TOMATO.get();
   }

   public void method_5711(byte id) {
      class_1799 entityStack = new class_1799(this.method_16942());
      if (id == 3) {
         class_2394 iparticledata = new class_2392(class_2398.field_11218, entityStack);

         for (int i = 0; i < 12; i++) {
            this.method_37908()
               .method_8406(
                  iparticledata,
                  this.method_23317(),
                  this.method_23318(),
                  this.method_23321(),
                  (this.field_5974.method_43057() * 2.0 - 1.0) * 0.1F,
                  (this.field_5974.method_43057() * 2.0 - 1.0) * 0.1F + 0.1F,
                  (this.field_5974.method_43057() * 2.0 - 1.0) * 0.1F
               );
         }
      }
   }

   protected void method_7454(class_3966 result) {
      super.method_7454(result);
      class_1297 entity = result.method_17782();
      entity.method_5643(this.method_48923().method_48811(this, this.method_24921()), 0.0F);
      this.method_5783(ModSounds.ENTITY_ROTTEN_TOMATO_HIT.get(), 1.0F, (this.field_5974.method_43057() - this.field_5974.method_43057()) * 0.2F + 1.0F);
   }

   protected void method_7488(class_239 result) {
      super.method_7488(result);
      if (!this.method_37908().field_9236) {
         this.method_37908().method_8421(this, (byte)3);
         this.method_5783(ModSounds.ENTITY_ROTTEN_TOMATO_HIT.get(), 1.0F, (this.field_5974.method_43057() - this.field_5974.method_43057()) * 0.2F + 1.0F);
         this.method_31472();
      }
   }
}
