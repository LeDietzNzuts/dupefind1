package dev.architectury.hooks.level.entity;

import dev.architectury.hooks.level.entity.fabric.ItemEntityHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.utils.value.IntValue;
import net.minecraft.class_1542;

public final class ItemEntityHooks {
   private ItemEntityHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static IntValue lifespan(class_1542 entity) {
      return ItemEntityHooksImpl.lifespan(entity);
   }
}
