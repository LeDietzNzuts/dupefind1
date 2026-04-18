package dev.architectury.registry.fabric;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredSupplier;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntriesAll;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_1761.class_7705;
import net.minecraft.class_1761.class_7913;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Experimental;

public class CreativeTabRegistryImpl {
   private static final Multimap<class_2960, Supplier<class_1799>> APPENDS = MultimapBuilder.hashKeys().arrayListValues().build();

   @Experimental
   public static class_1761 create(Consumer<class_7913> callback) {
      class_7913 builder = FabricItemGroup.builder();
      callback.accept(builder);
      return builder.method_47324();
   }

   @Experimental
   public static DeferredSupplier<class_1761> ofBuiltin(class_1761 tab) {
      class_2960 key = class_7923.field_44687.method_10221(tab);
      if (key == null) {
         throw new IllegalArgumentException("Builtin tab %s is not registered!".formatted(tab));
      } else {
         return new DeferredSupplier<class_1761>() {
            @Override
            public class_2960 getRegistryId() {
               return class_7924.field_44688.method_29177();
            }

            @Override
            public class_2960 getId() {
               return class_7923.field_44687.method_10221(tab);
            }

            @Override
            public boolean isPresent() {
               return true;
            }

            public class_1761 get() {
               return tab;
            }
         };
      }
   }

   @Experimental
   public static DeferredSupplier<class_1761> defer(class_2960 name) {
      return new DeferredSupplier<class_1761>() {
         @Nullable
         private class_1761 tab;

         @Override
         public class_2960 getRegistryId() {
            return class_7924.field_44688.method_29177();
         }

         @Override
         public class_2960 getId() {
            return name;
         }

         public class_1761 get() {
            this.resolve();
            if (this.tab == null) {
               throw new IllegalStateException("Creative tab %s was not registered yet!".formatted(name));
            } else {
               return this.tab;
            }
         }

         @Override
         public boolean isPresent() {
            this.resolve();
            return this.tab != null;
         }

         private void resolve() {
            if (this.tab == null) {
               this.tab = (class_1761)class_7923.field_44687.method_10223(name);
            }
         }
      };
   }

   public static void modify(DeferredSupplier<class_1761> tab, CreativeTabRegistry.ModifyTabCallback filler) {
      ItemGroupEvents.modifyEntriesEvent(tab.getKey()).register((ModifyEntries)entries -> filler.accept(entries.getEnabledFeatures(), new CreativeTabOutput() {
         @Override
         public void acceptAfter(class_1799 after, class_1799 stack, class_7705 visibility) {
            if (after.method_7960()) {
               entries.method_45417(stack, visibility);
            } else {
               entries.addAfter(after, List.of(stack), visibility);
            }
         }

         @Override
         public void acceptBefore(class_1799 before, class_1799 stack, class_7705 visibility) {
            if (before.method_7960()) {
               entries.method_45417(stack, visibility);
            } else {
               entries.addBefore(before, List.of(stack), visibility);
            }
         }
      }, entries.shouldShowOpRestrictedItems()));
   }

   @Experimental
   public static void appendStack(DeferredSupplier<class_1761> tab, Supplier<class_1799> item) {
      APPENDS.put(tab.getId(), item);
   }

   static {
      ItemGroupEvents.MODIFY_ENTRIES_ALL
         .register(
            (ModifyEntriesAll)(tab, output) -> APPENDS.get(class_7923.field_44687.method_10221(tab)).forEach(s -> output.method_45420((class_1799)s.get()))
         );
   }
}
