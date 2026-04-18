package dev.architectury.hooks.block;

import java.util.Objects;
import net.minecraft.class_2586;
import net.minecraft.class_3218;

public class BlockEntityHooks {
   private BlockEntityHooks() {
   }

   public static void syncData(class_2586 entity) {
      if (Objects.requireNonNull(entity.method_10997()) instanceof class_3218 level) {
         level.method_14178().method_14128(entity.method_11016());
      } else {
         throw new IllegalStateException("Cannot call syncData() on the logical client! Did you check level.isClientSide first?");
      }
   }
}
