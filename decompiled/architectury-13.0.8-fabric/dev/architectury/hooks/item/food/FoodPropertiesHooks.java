package dev.architectury.hooks.item.food;

import dev.architectury.hooks.item.food.fabric.FoodPropertiesHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.function.Supplier;
import net.minecraft.class_1293;
import net.minecraft.class_4174.class_4175;

public final class FoodPropertiesHooks {
   private FoodPropertiesHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static void effect(class_4175 builder, Supplier<? extends class_1293> effectSupplier, float chance) {
      FoodPropertiesHooksImpl.effect(builder, effectSupplier, chance);
   }
}
