package net.p3pp3rf1y.sophisticatedbackpacks.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_3955;
import net.minecraft.class_3956;
import net.minecraft.class_768;
import net.minecraft.class_8059;
import net.minecraft.class_8786;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackSettingsScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.ClientRecipeHelper;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.CraftingContainerRecipeTransferHandlerBase;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.SettingsGhostIngredientHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.StorageGhostIngredientHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.subtypes.PropertyBasedSubtypeInterpreter;

@JeiPlugin
public class SBPPlugin implements IModPlugin {
   private static Consumer<IRecipeCatalystRegistration> additionalCatalystRegistrar = registration -> {};

   public static void setAdditionalCatalystRegistrar(Consumer<IRecipeCatalystRegistration> additionalCatalystRegistrar) {
      SBPPlugin.additionalCatalystRegistrar = additionalCatalystRegistrar;
   }

   public class_2960 getPluginUid() {
      return class_2960.method_60655("sophisticatedbackpacks", "default");
   }

   public void registerItemSubtypes(ISubtypeRegistration registration) {
      PropertyBasedSubtypeInterpreter backpackSubTypeInterpreter = new PropertyBasedSubtypeInterpreter() {
         {
            this.addProperty(s -> BackpackWrapper.fromStack(s).getMainColor(), "clothColor", String::valueOf);
            this.addProperty(s -> BackpackWrapper.fromStack(s).getAccentColor(), "borderColor", String::valueOf);
         }
      };
      ModItems.BACKPACKS
         .forEach(backpack -> registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, (class_1792)backpack.get(), backpackSubTypeInterpreter));
   }

   public void registerGuiHandlers(IGuiHandlerRegistration registration) {
      registration.addGuiContainerHandler(BackpackScreen.class, new IGuiContainerHandler<BackpackScreen>() {
         public List<class_768> getGuiExtraAreas(BackpackScreen gui) {
            List<class_768> ret = new ArrayList<>();
            gui.getUpgradeSlotsRectangle().ifPresent(ret::add);
            ret.addAll(gui.getUpgradeSettingsControl().getTabRectangles());
            gui.getSortButtonsRectangle().ifPresent(ret::add);
            return ret;
         }
      });
      registration.addGuiContainerHandler(
         BackpackSettingsScreen.class,
         new IGuiContainerHandler<BackpackSettingsScreen>() {
            public List<class_768> getGuiExtraAreas(BackpackSettingsScreen gui) {
               return (List<class_768>)(gui != null && gui.getSettingsTabControl() != null
                  ? new ArrayList<>(gui.getSettingsTabControl().getTabRectangles())
                  : List.of());
            }
         }
      );
      registration.addGhostIngredientHandler(BackpackScreen.class, new StorageGhostIngredientHandler());
      registration.addGhostIngredientHandler(SettingsScreen.class, new SettingsGhostIngredientHandler());
   }

   public void registerRecipes(IRecipeRegistration registration) {
      registration.addRecipes(RecipeTypes.CRAFTING, DyeRecipesMaker.getRecipes());
      registration.addRecipes(
         RecipeTypes.CRAFTING,
         ClientRecipeHelper.transformAllRecipesOfType(class_3956.field_17545, BackpackUpgradeRecipe.class, ClientRecipeHelper::copyShapedRecipe)
      );
   }

   public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.CRAFTING_UPGRADE.get()), new RecipeType[]{RecipeTypes.CRAFTING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.STONECUTTER_UPGRADE.get()), new RecipeType[]{RecipeTypes.STONECUTTING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.SMITHING_UPGRADE.get()), new RecipeType[]{RecipeTypes.SMITHING});
      additionalCatalystRegistrar.accept(registration);
   }

   public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
      IRecipeTransferHandlerHelper handlerHelper = registration.getTransferHelper();
      IStackHelper stackHelper = registration.getJeiHelpers().getStackHelper();
      registration.addRecipeTransferHandler(
         new CraftingContainerRecipeTransferHandlerBase<BackpackContainer, class_8786<class_3955>>(handlerHelper, stackHelper) {
            public Class<BackpackContainer> getContainerClass() {
               return BackpackContainer.class;
            }

            public RecipeType<class_8786<class_3955>> getRecipeType() {
               return RecipeTypes.CRAFTING;
            }
         }, RecipeTypes.CRAFTING
      );
      registration.addRecipeTransferHandler(
         new CraftingContainerRecipeTransferHandlerBase<BackpackContainer, class_8786<class_8059>>(handlerHelper, stackHelper) {
            public Class<BackpackContainer> getContainerClass() {
               return BackpackContainer.class;
            }

            public RecipeType<class_8786<class_8059>> getRecipeType() {
               return RecipeTypes.SMITHING;
            }
         }, RecipeTypes.SMITHING
      );
   }
}
