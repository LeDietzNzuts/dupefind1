package vectorwing.farmersdelight.integration.rei;

import java.util.List;
import java.util.Optional;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler.IntRange;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultShapelessDisplay;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1863;
import net.minecraft.class_1867;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_7710;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.rei.categories.CookingPotCategory;
import vectorwing.farmersdelight.integration.rei.categories.CuttingCategory;
import vectorwing.farmersdelight.integration.rei.categories.DecompositionCategory;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;

public class ClientREIPlugin implements REIClientPlugin {
   public void registerDisplays(DisplayRegistry registry) {
      registry.registerRecipeFiller(CookingPotRecipe.class, ModRecipeTypes.COOKING.get(), CookingPotDisplay::new);
      registry.registerRecipeFiller(CuttingBoardRecipe.class, ModRecipeTypes.CUTTING.get(), CuttingDisplay::new);
      registry.add(new DecompositionDisplay());
      class_8786<class_1867> recipeHolder = this.getSpecialWheatDoughRecipe(registry.getRecipeManager());
      if (recipeHolder != null) {
         registry.add(new DefaultShapelessDisplay(recipeHolder));
      }

      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.of((class_1935)ModItems.WHEAT_DOUGH.get()), class_2561.method_43471("item.farmersdelight.wheat_dough")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.dough")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.of((class_1935)ModItems.STRAW.get()), class_2561.method_43471("item.farmersdelight.straw")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.straw")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(EntryIngredients.of((class_1935)ModItems.HAM.get()), class_2561.method_43471("item.farmersdelight.ham"))
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.ham")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(
                  List.of(
                     (class_1935)ModItems.FLINT_KNIFE.get(),
                     (class_1935)ModItems.IRON_KNIFE.get(),
                     (class_1935)ModItems.IRON_KNIFE.get(),
                     (class_1935)ModItems.DIAMOND_KNIFE.get(),
                     (class_1935)ModItems.NETHERITE_KNIFE.get(),
                     (class_1935)ModItems.GOLDEN_KNIFE.get()
                  )
               ),
               class_2561.method_43471("tag.item.farmersdelight.tools.knives")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.knife")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(
                  List.of((class_1935)ModItems.WILD_CABBAGES.get(), (class_1935)ModItems.CABBAGE.get(), (class_1935)ModItems.CABBAGE_LEAF.get())
               ),
               class_2561.method_43471("item.farmersdelight.cabbage")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_cabbages")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_BEETROOTS.get(), class_1802.field_8186)),
               class_2561.method_43471("item.minecraft.beetroot")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_beetroots")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_CARROTS.get(), class_1802.field_8179)),
               class_2561.method_43471("item.minecraft.carrot")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_carrots")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_ONIONS.get(), (class_1935)ModItems.ONION.get())),
               class_2561.method_43471("item.farmersdelight.onion")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_onions")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_POTATOES.get(), class_1802.field_8567)),
               class_2561.method_43471("item.minecraft.potato")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_potatoes")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_TOMATOES.get(), (class_1935)ModItems.TOMATO.get())),
               class_2561.method_43471("item.farmersdelight.tomato")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_tomatoes")})
      );
      registry.add(
         DefaultInformationDisplay.createFromEntries(
               EntryIngredients.ofItems(List.of((class_1935)ModItems.WILD_RICE.get(), (class_1935)ModItems.RICE_PANICLE.get())),
               class_2561.method_43471("item.farmersdelight.rice")
            )
            .lines(new class_2561[]{TextUtils.getTranslation("jei.info.wild_rice")})
      );
   }

   public void registerScreens(ScreenRegistry registry) {
      registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), CookingPotScreen.class, new CategoryIdentifier[]{REICategoryIdentifiers.COOKING});
   }

   public void registerCategories(CategoryRegistry registry) {
      registry.add(new DisplayCategory[]{new CookingPotCategory(), new CuttingCategory(), new DecompositionCategory()});
      registry.addWorkstations(REICategoryIdentifiers.COOKING, new EntryStack[]{EntryStacks.of((class_1935)ModItems.COOKING_POT.get())});
      registry.addWorkstations(REICategoryIdentifiers.CUTTING, new EntryStack[]{EntryStacks.of((class_1935)ModItems.CUTTING_BOARD.get())});
   }

   public void registerTransferHandlers(TransferHandlerRegistry registry) {
      registry.register(SimpleTransferHandler.create(CookingPotMenu.class, REICategoryIdentifiers.COOKING, new IntRange(0, 6)));
   }

   public class_8786<class_1867> getSpecialWheatDoughRecipe(class_1863 recipeManager) {
      Optional<class_8786<?>> specialRecipe = recipeManager.method_8130(class_2960.method_60655("farmersdelight", "wheat_dough_from_water"));
      return specialRecipe.<class_8786<class_1867>>map(
            recipe -> {
               class_2371<class_1856> inputs = class_2371.method_10212(
                  class_1856.field_9017,
                  new class_1856[]{
                     class_1856.method_8091(new class_1935[]{class_1802.field_8861}), class_1856.method_8091(new class_1935[]{class_1802.field_8705})
                  }
               );
               class_1799 output = new class_1799((class_1935)ModItems.WHEAT_DOUGH.get());
               class_2960 id = recipe.comp_1932();
               class_1867 newRecipe = new class_1867("fd_dough", class_7710.field_40251, output, inputs);
               return new class_8786(id, newRecipe);
            }
         )
         .orElse(null);
   }
}
