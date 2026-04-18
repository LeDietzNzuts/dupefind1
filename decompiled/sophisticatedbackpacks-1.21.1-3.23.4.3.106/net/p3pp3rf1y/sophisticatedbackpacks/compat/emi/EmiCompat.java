package net.p3pp3rf1y.sophisticatedbackpacks.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1792;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3955;
import net.minecraft.class_3956;
import net.minecraft.class_8786;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackSettingsScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.jei.DyeRecipesMaker;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.compat.emi.EmiGridMenuInfo;
import net.p3pp3rf1y.sophisticatedcore.compat.emi.EmiSettingsGhostDragDropHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.emi.EmiStorageGhostDragDropHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.emi.subtypes.PropertyBasedSubtypeInterpreter;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.ClientRecipeHelper;

public class EmiCompat implements EmiPlugin {
   public static Event<EmiCompat.WorkstationCallback> WORKSTATIONS = EventFactory.createArrayBacked(
      EmiCompat.WorkstationCallback.class, listeners -> consumer -> {
         for (EmiCompat.WorkstationCallback listener : listeners) {
            listener.additionalWorkstations(consumer);
         }
      }
   );

   public void register(EmiRegistry registry) {
      registry.addExclusionArea(
         BackpackScreen.class,
         (screen, consumer) -> {
            screen.getUpgradeSlotsRectangle().ifPresent(r -> consumer.accept(new Bounds(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320())));
            screen.getUpgradeSettingsControl()
               .getTabRectangles()
               .forEach(r -> consumer.accept(new Bounds(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320())));
            screen.getSortButtonsRectangle().ifPresent(r -> consumer.accept(new Bounds(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320())));
         }
      );
      registry.addExclusionArea(
         BackpackSettingsScreen.class,
         (screen, consumer) -> {
            if (screen != null && screen.getSettingsTabControl() != null) {
               screen.getSettingsTabControl()
                  .getTabRectangles()
                  .forEach(r -> consumer.accept(new Bounds(r.method_3321(), r.method_3322(), r.method_3319(), r.method_3320())));
            }
         }
      );
      registry.addDragDropHandler(BackpackScreen.class, new EmiStorageGhostDragDropHandler());
      registry.addDragDropHandler(BackpackSettingsScreen.class, new EmiSettingsGhostDragDropHandler());
      PropertyBasedSubtypeInterpreter backpackSubTypeInterpreter = new PropertyBasedSubtypeInterpreter() {
         {
            this.addProperty(s -> BackpackWrapper.fromStack(s).getMainColor(), "clothColor", String::valueOf);
            this.addProperty(s -> BackpackWrapper.fromStack(s).getAccentColor(), "borderColor", String::valueOf);
         }
      };
      registry.setDefaultComparison(EmiStack.of((class_1935)ModItems.BACKPACK.get()), backpackSubTypeInterpreter.comparator());
      registerCraftingRecipes(registry, DyeRecipesMaker.getRecipes());
      registerCraftingRecipes(
         registry, ClientRecipeHelper.transformAllRecipesOfType(class_3956.field_17545, BackpackUpgradeRecipe.class, ClientRecipeHelper::copyShapedRecipe)
      );
      registry.addRecipeHandler(ModItems.BACKPACK_CONTAINER_TYPE.get(), EmiGridMenuInfo.crafting());
      registry.addRecipeHandler(ModItems.BACKPACK_CONTAINER_TYPE.get(), EmiGridMenuInfo.stonecutting());
      registry.addRecipeHandler(ModItems.BACKPACK_CONTAINER_TYPE.get(), EmiGridMenuInfo.smithing());
      registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of((class_1935)ModItems.CRAFTING_UPGRADE.get()));
      registry.addWorkstation(VanillaEmiRecipeCategories.STONECUTTING, EmiStack.of((class_1935)ModItems.STONECUTTER_UPGRADE.get()));
      registry.addWorkstation(VanillaEmiRecipeCategories.SMITHING, EmiStack.of((class_1935)ModItems.SMITHING_UPGRADE.get()));
      List<EmiCompat.WorkstationEntry> entries = new ArrayList<>();
      ((EmiCompat.WorkstationCallback)WORKSTATIONS.invoker()).additionalWorkstations(entries::add);

      for (EmiCompat.WorkstationEntry entry : entries) {
         registry.addWorkstation(new EmiRecipeCategory(entry.id, EmiStack.of(entry.icon)), EmiStack.of(entry.workstation));
      }
   }

   private static void registerCraftingRecipes(EmiRegistry registry, Collection<class_8786<class_3955>> recipes) {
      class_310 mc = class_310.method_1551();
      recipes.forEach(
         r -> registry.addRecipe(
            new EmiCraftingRecipe(
               ((class_3955)r.comp_1933()).method_8117().stream().map(EmiIngredient::of).toList(),
               EmiStack.of(((class_3955)r.comp_1933()).method_8110(mc.field_1687.method_30349())),
               r.comp_1932().method_45138("/")
            )
         )
      );
   }

   public interface WorkstationCallback {
      void additionalWorkstations(Consumer<EmiCompat.WorkstationEntry> var1);
   }

   public record WorkstationEntry(class_2960 id, class_2248 icon, class_1792 workstation) {
   }
}
