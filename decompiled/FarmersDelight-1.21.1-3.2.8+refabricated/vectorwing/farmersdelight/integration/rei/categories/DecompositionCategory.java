package vectorwing.farmersdelight.integration.rei.categories;

import java.util.ArrayList;
import java.util.List;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5250;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;

public class DecompositionCategory implements DisplayCategory<DecompositionDisplay> {
   private static final class_2960 BACKGROUND = class_2960.method_60655("farmersdelight", "textures/gui/jei/decomposition.png");
   public static final int OUTPUT_GRID_X = 76;
   public static final int OUTPUT_GRID_Y = 10;

   public CategoryIdentifier<? extends DecompositionDisplay> getCategoryIdentifier() {
      return REICategoryIdentifiers.DECOMPOSITION;
   }

   public class_2561 getTitle() {
      return class_2561.method_43471("farmersdelight.jei.decomposition");
   }

   public Renderer getIcon() {
      return EntryStacks.of((class_1935)ModBlocks.ORGANIC_COMPOST.get());
   }

   public int getDisplayHeight() {
      return 102;
   }

   public int getDisplayWidth(DecompositionDisplay display) {
      return 150;
   }

   public List<Widget> setupDisplay(DecompositionDisplay display, Rectangle bounds) {
      List<Widget> widgets = new ArrayList<>();
      Point startPoint = new Point(bounds.getCenterX() - 59, bounds.getCenterY() - 40);
      widgets.add(Widgets.createRecipeBase(bounds));
      widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x, startPoint.y, 0.0F, 0.0F, 118, 80));
      widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 26)).entry(EntryStacks.of((class_1935)ModBlocks.ORGANIC_COMPOST.get())));
      widgets.add(Widgets.createSlot(new Point(startPoint.x + 93, startPoint.y + 26)).entry(EntryStacks.of((class_1935)ModBlocks.RICH_SOIL.get())));
      widgets.add(Widgets.createSlot(new Point(startPoint.x + 64, startPoint.y + 54)).entries(EntryIngredients.ofItemTag(ModTags.COMPOST_ACTIVATORS)));
      widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 40, startPoint.y + 38, 11, 11), new class_2561[]{translateKey(".light")}));
      widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 53, startPoint.y + 38, 11, 11), new class_2561[]{translateKey(".fluid")}));
      widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 67, startPoint.y + 38, 11, 11), new class_2561[]{translateKey(".accelerators")}));
      return widgets;
   }

   private static class_5250 translateKey(@NotNull String suffix) {
      return class_2561.method_43471("farmersdelight.jei.decomposition" + suffix);
   }

   public int getFixedDisplaysPerPage() {
      return 1;
   }
}
