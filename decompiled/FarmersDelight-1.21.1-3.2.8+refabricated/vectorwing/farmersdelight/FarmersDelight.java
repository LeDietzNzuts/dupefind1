package vectorwing.farmersdelight;

import net.fabricmc.api.ModInitializer;
import net.minecraft.class_2960;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.crafting.condition.VanillaCrateEnabledCondition;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.event.CommonModBusEvents;
import vectorwing.farmersdelight.common.event.VillagerEvents;
import vectorwing.farmersdelight.common.item.DogFoodItem;
import vectorwing.farmersdelight.common.item.HorseFeedItem;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.common.registry.ModAdvancements;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.registry.ModBiomeModifiers;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModLootFunctions;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModPlacementModifiers;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.world.VillageStructures;
import vectorwing.farmersdelight.refabricated.CanItemPerformAbilityCondition;
import vectorwing.farmersdelight.refabricated.CompostableHelper;
import vectorwing.farmersdelight.refabricated.LootModificationEvents;

public class FarmersDelight implements ModInitializer {
   public static final String MODID = "farmersdelight";
   public static final Logger LOGGER = LogManager.getLogger();

   public static class_2960 res(String name) {
      return class_2960.method_60655("farmersdelight", name);
   }

   public void onInitialize() {
      Configuration.touch();
      ModSounds.touch();
      ModBlocks.touch();
      ModEffects.touch();
      ModParticleTypes.touch();
      ModItems.touch();
      ModDataComponents.touch();
      ModEntityTypes.touch();
      ModBlockEntityTypes.touch();
      ModMenuTypes.touch();
      ModRecipeTypes.touch();
      ModRecipeSerializers.touch();
      ModBiomeFeatures.touch();
      ModAdvancements.touch();
      ModPlacementModifiers.touch();
      ModLootFunctions.touch();
      ModCreativeTabs.touch();
      VillageStructures.init();
      CommonModBusEvents.init();
      VillagerEvents.init();
      CommonSetup.init();
      VanillaCrateEnabledCondition.init();
      CanItemPerformAbilityCondition.init();
      LootModificationEvents.init();
      ModBiomeModifiers.init();
      CabinetBlockEntity.init();
      CookingPotBlockEntity.init();
      CuttingBoardBlock.init();
      CuttingBoardBlockEntity.init();
      DogFoodItem.init();
      HorseFeedItem.init();
      KnifeItem.init();
      ModNetworking.init();
      RichSoilBlock.init();
      ItemAbilityIngredient.touch();
      CompostableHelper.apply();
   }
}
