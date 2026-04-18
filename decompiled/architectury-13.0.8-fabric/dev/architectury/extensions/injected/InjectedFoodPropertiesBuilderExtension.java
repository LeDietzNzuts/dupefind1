package dev.architectury.extensions.injected;

import dev.architectury.hooks.item.food.FoodPropertiesHooks;
import java.util.function.Supplier;
import net.minecraft.class_1293;
import net.minecraft.class_4174.class_4175;

public interface InjectedFoodPropertiesBuilderExtension {
   default class_4175 arch$effect(Supplier<? extends class_1293> effectSupplier, float chance) {
      FoodPropertiesHooks.effect((class_4175)this, effectSupplier, chance);
      return (class_4175)this;
   }
}
