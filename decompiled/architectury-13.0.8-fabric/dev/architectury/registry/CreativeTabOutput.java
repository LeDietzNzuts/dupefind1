package dev.architectury.registry;

import java.util.Collection;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1761.class_7704;
import net.minecraft.class_1761.class_7705;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@NonExtendable
@Experimental
public interface CreativeTabOutput extends class_7704 {
   void acceptAfter(class_1799 var1, class_1799 var2, class_7705 var3);

   void acceptBefore(class_1799 var1, class_1799 var2, class_7705 var3);

   default void method_45417(class_1799 stack, class_7705 visibility) {
      this.acceptAfter(class_1799.field_8037, stack, visibility);
   }

   default void acceptAfter(class_1799 after, class_1799 stack) {
      this.acceptAfter(after, stack, class_7705.field_40191);
   }

   default void acceptAfter(class_1799 after, class_1935 item, class_7705 visibility) {
      this.acceptAfter(after, new class_1799(item), visibility);
   }

   default void acceptAfter(class_1799 after, class_1935 item) {
      this.acceptAfter(after, new class_1799(item), class_7705.field_40191);
   }

   default void acceptAllAfter(class_1799 after, Collection<class_1799> stacks, class_7705 visibility) {
      stacks.forEach(stack -> this.acceptAfter(after, stack, visibility));
   }

   default void acceptAllAfter(class_1799 after, Collection<class_1799> stacks) {
      this.acceptAllAfter(after, stacks, class_7705.field_40191);
   }

   default void acceptAfter(class_1935 after, class_1799 stack) {
      this.acceptAfter(new class_1799(after), stack);
   }

   default void acceptAfter(class_1935 after, class_1935 item, class_7705 visibility) {
      this.acceptAfter(new class_1799(after), item, visibility);
   }

   default void acceptAfter(class_1935 after, class_1935 item) {
      this.acceptAfter(new class_1799(after), item);
   }

   default void acceptAllAfter(class_1935 after, Collection<class_1799> stacks, class_7705 visibility) {
      this.acceptAllAfter(new class_1799(after), stacks, visibility);
   }

   default void acceptAllAfter(class_1935 after, Collection<class_1799> stacks) {
      this.acceptAllAfter(new class_1799(after), stacks);
   }

   default void acceptBefore(class_1799 before, class_1799 stack) {
      this.acceptBefore(before, stack, class_7705.field_40191);
   }

   default void acceptBefore(class_1799 before, class_1935 item, class_7705 visibility) {
      this.acceptBefore(before, new class_1799(item), visibility);
   }

   default void acceptBefore(class_1799 before, class_1935 item) {
      this.acceptBefore(before, new class_1799(item), class_7705.field_40191);
   }

   default void acceptAllBefore(class_1799 before, Collection<class_1799> stacks, class_7705 visibility) {
      stacks.forEach(stack -> this.acceptBefore(before, stack, visibility));
   }

   default void acceptAllBefore(class_1799 before, Collection<class_1799> stacks) {
      this.acceptAllBefore(before, stacks, class_7705.field_40191);
   }

   default void acceptBefore(class_1935 before, class_1799 stack) {
      this.acceptBefore(new class_1799(before), stack);
   }

   default void acceptBefore(class_1935 before, class_1935 item, class_7705 visibility) {
      this.acceptBefore(new class_1799(before), item, visibility);
   }

   default void acceptBefore(class_1935 before, class_1935 item) {
      this.acceptBefore(new class_1799(before), item);
   }

   default void acceptAllBefore(class_1935 before, Collection<class_1799> stacks, class_7705 visibility) {
      this.acceptAllBefore(new class_1799(before), stacks, visibility);
   }

   default void acceptAllBefore(class_1935 before, Collection<class_1799> stacks) {
      this.acceptAllBefore(new class_1799(before), stacks);
   }
}
