package org.embeddedt.modernfix.render;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import net.minecraft.class_806;
import net.minecraft.class_809;
import org.jetbrains.annotations.Nullable;

public class SimpleItemModelView implements class_1087 {
   private class_1087 wrappedItem;
   private FastItemRenderType type;
   private final List<class_777> nullQuadList = new ObjectArrayList();

   public void setItem(class_1087 model) {
      this.wrappedItem = model;
   }

   public void setType(FastItemRenderType type) {
      this.type = type;
   }

   private boolean isCorrectDirectionForType(class_2350 direction) {
      return this.type == FastItemRenderType.SIMPLE_ITEM
         ? direction == class_2350.field_11035
         : direction == class_2350.field_11036 || direction == class_2350.field_11034 || direction == class_2350.field_11043;
   }

   public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand) {
      boolean isWholeListValid = this.isCorrectDirectionForType(side);
      List<class_777> realList = this.wrappedItem.method_4707(state, side, rand);
      if (isWholeListValid) {
         return realList;
      } else {
         this.nullQuadList.clear();

         for (int i = 0; i < realList.size(); i++) {
            class_777 quad = realList.get(i);
            if (this.isCorrectDirectionForType(quad.method_3358())) {
               this.nullQuadList.add(quad);
            }
         }

         return this.nullQuadList;
      }
   }

   public boolean method_4708() {
      return this.wrappedItem.method_4708();
   }

   public boolean method_4712() {
      return this.wrappedItem.method_4712();
   }

   public boolean method_24304() {
      return this.wrappedItem.method_24304();
   }

   public boolean method_4713() {
      return this.wrappedItem.method_4713();
   }

   public class_1058 method_4711() {
      return this.wrappedItem.method_4711();
   }

   public class_809 method_4709() {
      return this.wrappedItem.method_4709();
   }

   public class_806 method_4710() {
      return this.wrappedItem.method_4710();
   }
}
