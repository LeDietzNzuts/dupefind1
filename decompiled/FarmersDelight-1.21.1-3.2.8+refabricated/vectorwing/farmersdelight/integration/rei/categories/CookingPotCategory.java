package vectorwing.farmersdelight.integration.rei.categories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;

public class CookingPotCategory implements DisplayCategory<CookingPotDisplay> {
   private static final class_2960 BACKGROUND = class_2960.method_60655("farmersdelight", "textures/gui/cooking_pot.png");

   public CategoryIdentifier<? extends CookingPotDisplay> getCategoryIdentifier() {
      return REICategoryIdentifiers.COOKING;
   }

   public class_2561 getTitle() {
      return class_2561.method_43471("farmersdelight.jei.cooking");
   }

   public Renderer getIcon() {
      return EntryStacks.of((class_1935)ModBlocks.COOKING_POT.get());
   }

   public int getDisplayHeight() {
      return 88;
   }

   public int getDisplayWidth(CookingPotDisplay display) {
      return 154;
   }

   public List<Widget> setupDisplay(CookingPotDisplay display, Rectangle bounds) {
      List<EntryIngredient> recipeIngredients = display.getInputEntries();
      List<EntryIngredient> resultStack = display.getOutputEntries();
      EntryIngredient containerStack = display.getOutputContainer();
      Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 28);
      List<Widget> widgets = new ArrayList<>();
      widgets.add(Widgets.createRecipeBase(bounds));
      widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x, startPoint.y, 29.0F, 16.0F, 116, 56));
      int borderSlotSize = 18;

      for (int row = 0; row < 2; row++) {
         for (int column = 0; column < 3; column++) {
            int inputIndex = row * 3 + column;
            if (inputIndex < recipeIngredients.size()) {
               widgets.add(
                  Widgets.createSlot(new Point(startPoint.x + column * borderSlotSize + 1, startPoint.y + row * borderSlotSize + 1))
                     .entries((Collection)display.getInputEntries().get(inputIndex))
                     .disableBackground()
               );
            }
         }
      }

      widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 10)).entries((Collection)resultStack.get(0)).disableBackground());
      Slot containerSlot = Widgets.createSlot(new Point(startPoint.x + 63, startPoint.y + 39));
      if (!containerStack.isEmpty()) {
         containerSlot.entries(containerStack);
      }

      widgets.add(containerSlot);
      widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 39)).entries((Collection)resultStack.get(0)));
      int startTime = class_310.method_1551().field_1724.field_6012;
      widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
         int ticksPassed = (class_310.method_1551().field_1724.field_6012 - startTime) % 200;
         int arrowAnimationWidth = Math.floorDiv(ticksPassed * 25, 200);
         graphics.method_25302(BACKGROUND, startPoint.x + 60, startPoint.y + 9, 176, 15, arrowAnimationWidth, 17);
      }));
      widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x + 18, startPoint.y + 39, 176.0F, 0.0F, 17, 15));
      widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x + 64, startPoint.y + 2, 176.0F, 32.0F, 8, 11));
      widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x + 63, startPoint.y + 21, 176.0F, 43.0F, 9, 9));
      widgets.add(Widgets.createTooltip(() -> new Rectangle(startPoint.x + 61, startPoint.y + 2, 22, 28), this.getTooltipStrings(display)));
      return widgets;
   }

   public List<class_2561> getTooltipStrings(CookingPotDisplay display) {
      List<class_2561> tooltipStrings = new ArrayList<>();
      int cookTime = display.getCookTime();
      if (cookTime > 0) {
         int cookTimeSeconds = cookTime / 20;
         tooltipStrings.add(class_2561.method_43469("category.rei.campfire.time", new Object[]{cookTimeSeconds}));
      }

      float experience = display.getExperience();
      if (experience > 0.0F) {
         tooltipStrings.add(class_2561.method_43469("category.rei.cooking.xp", new Object[]{experience}));
      }

      return tooltipStrings;
   }
}
