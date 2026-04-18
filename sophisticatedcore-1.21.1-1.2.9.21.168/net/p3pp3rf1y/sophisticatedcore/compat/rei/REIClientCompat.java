package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import java.util.Objects;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.ClientRecipeHelper;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;

public class REIClientCompat implements REIClientPlugin {
   public void registerDisplays(DisplayRegistry registry) {
      ClientRecipeHelper.transformAllRecipesOfType(class_3956.field_17545, UpgradeNextTierRecipe.class, ClientRecipeHelper::copyShapedRecipe).forEach(r -> {
         for (Display display : registry.tryFillDisplay(r)) {
            if (Objects.equals(display.getCategoryIdentifier(), BuiltinPlugin.CRAFTING)) {
               registry.add(display, r);
            }
         }
      });
   }

   public void registerScreens(ScreenRegistry registry) {
      registry.registerDraggableStackVisitor(new REISettingsGhostIngredientHandler());
   }
}
