package dev.architectury.registry.client.rendering;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.rendering.fabric.ColorHandlerRegistryImpl;
import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_322;
import net.minecraft.class_326;

@Environment(EnvType.CLIENT)
public final class ColorHandlerRegistry {
   private ColorHandlerRegistry() {
   }

   public static void registerItemColors(class_326 color, class_1935... items) {
      Supplier<class_1935>[] array = new Supplier[items.length];

      for (int i = 0; i < items.length; i++) {
         class_1935 item = Objects.requireNonNull(items[i], "items[i] is null!");
         array[i] = () -> item;
      }

      registerItemColors(color, array);
   }

   public static void registerBlockColors(class_322 color, class_2248... blocks) {
      Supplier<class_2248>[] array = new Supplier[blocks.length];

      for (int i = 0; i < blocks.length; i++) {
         class_2248 block = Objects.requireNonNull(blocks[i], "blocks[i] is null!");
         array[i] = () -> block;
      }

      registerBlockColors(color, array);
   }

   @SafeVarargs
   @ExpectPlatform
   @Transformed
   public static void registerItemColors(class_326 color, Supplier<? extends class_1935>... items) {
      ColorHandlerRegistryImpl.registerItemColors(color, items);
   }

   @SafeVarargs
   @ExpectPlatform
   @Transformed
   public static void registerBlockColors(class_322 color, Supplier<? extends class_2248>... blocks) {
      ColorHandlerRegistryImpl.registerBlockColors(color, blocks);
   }
}
