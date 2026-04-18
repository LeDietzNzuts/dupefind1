package net.p3pp3rf1y.sophisticatedcore.util;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_6885;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_9322;
import net.minecraft.class_9323;

public class SimpleItemContent implements class_9322 {
   public static final SimpleItemContent EMPTY = new SimpleItemContent(class_1799.field_8037);
   public static final Codec<SimpleItemContent> CODEC = class_1799.field_49266.xmap(SimpleItemContent::new, content -> content.itemStack);
   public static final class_9139<class_9129, SimpleItemContent> STREAM_CODEC = class_1799.field_49268
      .method_56432(SimpleItemContent::new, content -> content.itemStack);
   private final class_1799 itemStack;

   private SimpleItemContent(class_1799 itemStack) {
      this.itemStack = itemStack;
   }

   public static SimpleItemContent copyOf(class_1799 itemStack) {
      return itemStack.method_7960() ? EMPTY : new SimpleItemContent(itemStack.method_7972());
   }

   public class_1799 copy() {
      return this.itemStack.method_7972();
   }

   public boolean isEmpty() {
      return this.itemStack.method_7960();
   }

   public class_1792 getItem() {
      return this.itemStack.method_7909();
   }

   public boolean is(class_6862<class_1792> tag) {
      return this.itemStack.method_31573(tag);
   }

   public boolean is(class_1792 item) {
      return this.itemStack.method_31574(item);
   }

   public boolean is(Predicate<class_6880<class_1792>> predicate) {
      return this.itemStack.method_41407(predicate);
   }

   public boolean is(class_6880<class_1792> holder) {
      return this.itemStack.method_41406(holder);
   }

   public boolean is(class_6885<class_1792> holders) {
      return this.itemStack.method_53187(holders);
   }

   public int getCount() {
      return this.itemStack.method_7947();
   }

   public boolean matches(class_1799 other) {
      return class_1799.method_31577(this.itemStack, other);
   }

   public boolean isSameItem(class_1799 other) {
      return class_1799.method_7984(this.itemStack, other);
   }

   public boolean isSameItemSameComponents(class_1799 other) {
      return class_1799.method_31577(this.itemStack, other);
   }

   public boolean isSameItemSameComponents(SimpleItemContent content) {
      return this.isSameItemSameComponents(content.itemStack);
   }

   public class_9323 method_57353() {
      return this.itemStack.method_57353();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return obj instanceof SimpleItemContent o ? class_1799.method_31577(this.itemStack, o.itemStack) : false;
      }
   }

   @Override
   public int hashCode() {
      return this.itemStack.method_7947() * 31 + class_1799.method_57355(this.itemStack);
   }
}
