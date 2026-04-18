package com.magistuarmory.item.crafting;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.class_1865;
import net.minecraft.class_7924;

public class ModRecipes {
   public static final DeferredRegister<class_1865<?>> RECIPE_SERIALIZERS = DeferredRegister.create("magistuarmory", class_7924.field_41216);
   public static final RegistrySupplier<class_1865<?>> HERALDRY_SERIALIZER = RECIPE_SERIALIZERS.register(
      "heraldry_recipes", HeraldryRecipe::getSerializerInstance
   );
   public static final RegistrySupplier<class_1865<?>> ARMOR_DECORATION_SERIALIZER = RECIPE_SERIALIZERS.register(
      "armor_decoration_recipes", ArmorDecorationRecipe::getSerializerInstance
   );
   public static final RegistrySupplier<class_1865<?>> DECORATION_REMOVE_SERIALIZER = RECIPE_SERIALIZERS.register(
      "decoration_remove_recipes", DecorationRemoveRecipe::getSerializerInstance
   );

   public static void init() {
      RECIPE_SERIALIZERS.register();
   }
}
