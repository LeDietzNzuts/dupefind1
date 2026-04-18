package dev.architectury.registry.client.rendering.fabric;

import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_322;
import net.minecraft.class_326;

public class ColorHandlerRegistryImpl {
   @SafeVarargs
   public static void registerItemColors(class_326 itemColor, Supplier<? extends class_1935>... items) {
      Objects.requireNonNull(itemColor, "color is null!");
      ColorProviderRegistry.ITEM.register(itemColor, unpackItems(items));
   }

   @SafeVarargs
   public static void registerBlockColors(class_322 blockColor, Supplier<? extends class_2248>... blocks) {
      Objects.requireNonNull(blockColor, "color is null!");
      ColorProviderRegistry.BLOCK.register(blockColor, unpackBlocks(blocks));
   }

   private static class_1935[] unpackItems(Supplier<? extends class_1935>[] items) {
      class_1935[] array = new class_1935[items.length];

      for (int i = 0; i < items.length; i++) {
         array[i] = Objects.requireNonNull(items[i].get());
      }

      return array;
   }

   private static class_2248[] unpackBlocks(Supplier<? extends class_2248>[] blocks) {
      class_2248[] array = new class_2248[blocks.length];

      for (int i = 0; i < blocks.length; i++) {
         array[i] = Objects.requireNonNull(blocks[i].get());
      }

      return array;
   }
}
