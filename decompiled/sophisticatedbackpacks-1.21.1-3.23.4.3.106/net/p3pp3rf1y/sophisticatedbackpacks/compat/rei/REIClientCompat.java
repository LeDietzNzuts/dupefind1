package net.p3pp3rf1y.sophisticatedbackpacks.compat.rei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import net.minecraft.class_1935;
import net.minecraft.class_3956;
import net.minecraft.class_437;
import net.minecraft.class_768;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackSettingsScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.jei.DyeRecipesMaker;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.ClientRecipeHelper;
import net.p3pp3rf1y.sophisticatedcore.compat.rei.REIStorageGhostIngredientHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.rei.SophisticatedTransferHandler;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;

public class REIClientCompat implements REIClientPlugin {
   private static Consumer<CategoryRegistry> additionalCategories = registration -> {};

   public static void setAdditionalCategories(Consumer<CategoryRegistry> additionalCategories) {
      REIClientCompat.additionalCategories = additionalCategories;
   }

   public void registerExclusionZones(ExclusionZones zones) {
      zones.register(BackpackScreen.class, screen -> {
         List<class_768> ret = new ArrayList<>();
         screen.getUpgradeSlotsRectangle().ifPresent(ret::add);
         ret.addAll(screen.getUpgradeSettingsControl().getTabRectangles());
         screen.getSortButtonsRectangle().ifPresent(ret::add);
         return ret.stream().map(r -> new Rectangle(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320())).toList();
      });
      zones.register(
         BackpackSettingsScreen.class,
         screen -> screen != null && screen.getSettingsTabControl() != null
            ? screen.getSettingsTabControl()
               .getTabRectangles()
               .stream()
               .map(r -> new Rectangle(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320()))
               .toList()
            : List.of()
      );
   }

   public void registerTransferHandlers(TransferHandlerRegistry registry) {
      registry.register(SophisticatedTransferHandler.crafting(BackpackContainer.class));
   }

   public void registerCategories(CategoryRegistry registry) {
      registry.addWorkstations(BuiltinPlugin.CRAFTING, new EntryStack[]{EntryStacks.of((class_1935)ModItems.CRAFTING_UPGRADE.get())});
      registry.addWorkstations(BuiltinPlugin.STONE_CUTTING, new EntryStack[]{EntryStacks.of((class_1935)ModItems.STONECUTTER_UPGRADE.get())});
      additionalCategories.accept(registry);
   }

   public void registerScreens(ScreenRegistry registry) {
      registry.registerDraggableStackVisitor(new REIStorageGhostIngredientHandler<StorageScreenBase<?>>() {
         public <R extends class_437> boolean isHandingScreen(R screen) {
            return screen instanceof BackpackScreen;
         }
      });
   }

   public void registerDisplays(DisplayRegistry registry) {
      registerRecipes(registry, DyeRecipesMaker.getRecipes(), BuiltinPlugin.CRAFTING);
      registerRecipes(
         registry,
         ClientRecipeHelper.transformAllRecipesOfType(class_3956.field_17545, UpgradeNextTierRecipe.class, ClientRecipeHelper::copyShapedRecipe),
         BuiltinPlugin.CRAFTING
      );
   }

   public static void registerRecipes(DisplayRegistry registry, Collection<?> recipes, CategoryIdentifier<?> identifier) {
      recipes.forEach(recipe -> {
         for (Display display : registry.tryFillDisplay(recipe)) {
            if (Objects.equals(display.getCategoryIdentifier(), identifier)) {
               registry.add(display, recipe);
            }
         }
      });
   }
}
