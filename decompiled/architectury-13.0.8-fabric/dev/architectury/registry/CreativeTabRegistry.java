package dev.architectury.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.fabric.CreativeTabRegistryImpl;
import dev.architectury.registry.registries.DeferredSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7699;
import net.minecraft.class_1761.class_7913;
import org.jetbrains.annotations.ApiStatus.Experimental;

public final class CreativeTabRegistry {
   private CreativeTabRegistry() {
   }

   public static class_1761 create(class_2561 title, Supplier<class_1799> icon) {
      return create(builder -> {
         builder.method_47321(title);
         builder.method_47320(icon);
      });
   }

   @ExpectPlatform
   @Experimental
   @Transformed
   public static class_1761 create(Consumer<class_7913> callback) {
      return CreativeTabRegistryImpl.create(callback);
   }

   @ExpectPlatform
   @Experimental
   @Transformed
   public static DeferredSupplier<class_1761> ofBuiltin(class_1761 tab) {
      return CreativeTabRegistryImpl.ofBuiltin(tab);
   }

   @ExpectPlatform
   @Experimental
   @Transformed
   public static DeferredSupplier<class_1761> defer(class_2960 name) {
      return CreativeTabRegistryImpl.defer(name);
   }

   @Experimental
   public static DeferredSupplier<class_1761> defer(class_5321<class_1761> name) {
      return defer(name.method_29177());
   }

   @Experimental
   public static void modifyBuiltin(class_1761 tab, CreativeTabRegistry.ModifyTabCallback filler) {
      modify(ofBuiltin(tab), filler);
   }

   @ExpectPlatform
   @Experimental
   @Transformed
   public static void modify(DeferredSupplier<class_1761> tab, CreativeTabRegistry.ModifyTabCallback filler) {
      CreativeTabRegistryImpl.modify(tab, filler);
   }

   @Experimental
   public static void appendBuiltin(class_1761 tab, class_1935... items) {
      append(ofBuiltin(tab), items);
   }

   @Experimental
   public static <I extends class_1935, T extends Supplier<I>> void appendBuiltin(class_1761 tab, T... items) {
      appendStack(ofBuiltin(tab), Stream.<Supplier>of(items).map(supplier -> () -> new class_1799((class_1935)supplier.get())));
   }

   @Experimental
   public static void appendBuiltinStack(class_1761 tab, class_1799... items) {
      appendStack(ofBuiltin(tab), items);
   }

   @Experimental
   public static void appendBuiltinStack(class_1761 tab, Supplier<class_1799>... items) {
      appendStack(ofBuiltin(tab), items);
   }

   @Experimental
   public static void append(DeferredSupplier<class_1761> tab, class_1935... items) {
      appendStack(tab, Stream.of(items).map(item -> () -> new class_1799(item)));
   }

   @Experimental
   public static <I extends class_1935, T extends Supplier<I>> void append(DeferredSupplier<class_1761> tab, T... items) {
      appendStack(tab, Stream.<Supplier>of(items).map(supplier -> () -> new class_1799((class_1935)supplier.get())));
   }

   @Experimental
   public static void appendStack(DeferredSupplier<class_1761> tab, class_1799... items) {
      appendStack(tab, Stream.of(items).map(supplier -> () -> supplier));
   }

   @ExpectPlatform
   @Experimental
   @Transformed
   public static void appendStack(DeferredSupplier<class_1761> tab, Supplier<class_1799> item) {
      CreativeTabRegistryImpl.appendStack(tab, item);
   }

   @Experimental
   public static void appendStack(DeferredSupplier<class_1761> tab, Supplier<class_1799>... items) {
      for (Supplier<class_1799> item : items) {
         appendStack(tab, item);
      }
   }

   @Experimental
   public static void appendStack(DeferredSupplier<class_1761> tab, Stream<Supplier<class_1799>> items) {
      items.forEach(item -> appendStack(tab, (Supplier<class_1799>)item));
   }

   @Experimental
   public static void append(class_5321<class_1761> tab, class_1935... items) {
      appendStack(defer(tab), Stream.of(items).map(item -> () -> new class_1799(item)));
   }

   @Experimental
   public static <I extends class_1935, T extends Supplier<I>> void append(class_5321<class_1761> tab, T... items) {
      appendStack(defer(tab), Stream.<Supplier>of(items).map(supplier -> () -> new class_1799((class_1935)supplier.get())));
   }

   @Experimental
   public static void appendStack(class_5321<class_1761> tab, class_1799... items) {
      appendStack(defer(tab), Stream.of(items).map(supplier -> () -> supplier));
   }

   @Experimental
   public static void appendStack(class_5321<class_1761> tab, Supplier<class_1799> item) {
      appendStack(defer(tab), item);
   }

   @Experimental
   public static void appendStack(class_5321<class_1761> tab, Supplier<class_1799>... items) {
      appendStack(defer(tab), items);
   }

   @Experimental
   public static void appendStack(class_5321<class_1761> tab, Stream<Supplier<class_1799>> items) {
      appendStack(defer(tab), items);
   }

   @FunctionalInterface
   public interface ModifyTabCallback {
      void accept(class_7699 var1, CreativeTabOutput var2, boolean var3);
   }
}
