package dev.architectury.hooks.item.food.fabric;

import java.util.function.Supplier;
import net.minecraft.class_1293;
import net.minecraft.class_4174.class_4175;

public class FoodPropertiesHooksImpl {
   public static void effect(class_4175 builder, Supplier<? extends class_1293> effectSupplier, float chance) {
      builder.method_19239(effectSupplier.get(), chance);
   }
}
