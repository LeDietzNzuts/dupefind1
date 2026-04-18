package dev.architectury.hooks.item;

import dev.architectury.hooks.item.fabric.ItemStackHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import net.minecraft.class_3419;

public final class ItemStackHooks {
   private ItemStackHooks() {
   }

   public static class_1799 copyWithCount(class_1799 stack, int count) {
      class_1799 copy = stack.method_7972();
      copy.method_7939(count);
      return copy;
   }

   public static void giveItem(class_3222 player, class_1799 stack) {
      boolean bl = player.method_31548().method_7394(stack);
      if (bl && stack.method_7960()) {
         stack.method_7939(1);
         class_1542 entity = player.method_7328(stack, false);
         if (entity != null) {
            entity.method_6987();
         }

         player.method_37908()
            .method_43128(
               null,
               player.method_23317(),
               player.method_23318(),
               player.method_23321(),
               class_3417.field_15197,
               class_3419.field_15248,
               0.2F,
               ((player.method_59922().method_43057() - player.method_59922().method_43057()) * 0.7F + 1.0F) * 2.0F
            );
         player.field_7498.method_7623();
      } else {
         class_1542 entity = player.method_7328(stack, false);
         if (entity != null) {
            entity.method_6975();
            entity.method_48349(player.method_5667());
         }
      }
   }

   @ExpectPlatform
   @Transformed
   public static boolean hasCraftingRemainingItem(class_1799 stack) {
      return ItemStackHooksImpl.hasCraftingRemainingItem(stack);
   }

   @ExpectPlatform
   @Transformed
   public static class_1799 getCraftingRemainingItem(class_1799 stack) {
      return ItemStackHooksImpl.getCraftingRemainingItem(stack);
   }
}
