package dev.architectury.event;

import net.minecraft.class_1271;

public class CompoundEventResult<T> {
   private static final CompoundEventResult<?> PASS = new CompoundEventResult(EventResult.pass(), null);
   private final EventResult result;
   private final T object;

   public static <T> CompoundEventResult<T> pass() {
      return (CompoundEventResult<T>)PASS;
   }

   public static <T> CompoundEventResult<T> interrupt(Boolean value, T object) {
      return new CompoundEventResult<>(EventResult.interrupt(value), object);
   }

   public static <T> CompoundEventResult<T> interruptTrue(T object) {
      return new CompoundEventResult<>(EventResult.interruptTrue(), object);
   }

   public static <T> CompoundEventResult<T> interruptDefault(T object) {
      return new CompoundEventResult<>(EventResult.interruptDefault(), object);
   }

   public static <T> CompoundEventResult<T> interruptFalse(T object) {
      return new CompoundEventResult<>(EventResult.interruptFalse(), object);
   }

   private CompoundEventResult(EventResult result, T object) {
      this.result = result;
      this.object = object;
   }

   public boolean interruptsFurtherEvaluation() {
      return this.result.interruptsFurtherEvaluation();
   }

   public Boolean value() {
      return this.result.value();
   }

   public boolean isEmpty() {
      return this.result.isEmpty();
   }

   public boolean isPresent() {
      return this.result.isPresent();
   }

   public boolean isTrue() {
      return this.result.isTrue();
   }

   public boolean isFalse() {
      return this.result.isFalse();
   }

   public EventResult result() {
      return this.result;
   }

   public T object() {
      return this.object;
   }

   public class_1271<T> asMinecraft() {
      return new class_1271(this.result.asMinecraft(), this.object);
   }
}
