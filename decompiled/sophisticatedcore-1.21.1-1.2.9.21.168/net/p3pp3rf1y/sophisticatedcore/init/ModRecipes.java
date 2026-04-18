package net.p3pp3rf1y.sophisticatedcore.init;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.crafting.ItemEnabledCondition;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeClearRecipe;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;

public class ModRecipes {
   private static final DeferredRegister<class_1865<?>> RECIPE_SERIALIZERS = DeferredRegister.create(class_7923.field_41189, "sophisticatedcore");
   public static final Supplier<class_1865<?>> UPGRADE_NEXT_TIER_SERIALIZER = RECIPE_SERIALIZERS.register(
      "upgrade_next_tier", UpgradeNextTierRecipe.Serializer::new
   );
   public static final Supplier<class_1866<?>> UPGRADE_CLEAR_SERIALIZER = RECIPE_SERIALIZERS.register(
      "upgrade_clear", () -> new class_1866(UpgradeClearRecipe::new)
   );
   public static final ResourceConditionType<ItemEnabledCondition> ITEM_ENABLED_CONDITION = ResourceConditionType.create(
      SophisticatedCore.getRL("item_enabled"), ItemEnabledCondition.CODEC
   );

   private ModRecipes() {
   }

   public static void registerHandlers() {
      RECIPE_SERIALIZERS.register();
      ResourceConditions.register(ITEM_ENABLED_CONDITION);
   }
}
