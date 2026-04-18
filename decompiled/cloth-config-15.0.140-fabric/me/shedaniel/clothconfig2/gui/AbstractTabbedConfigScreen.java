package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Maps;
import java.util.Map;
import me.shedaniel.clothconfig2.api.TabbedConfigScreen;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;

public abstract class AbstractTabbedConfigScreen extends AbstractConfigScreen implements TabbedConfigScreen {
   private final Map<String, Boolean> categoryTransparentBackground = Maps.newHashMap();
   private final Map<String, class_2960> categoryBackgroundLocation = Maps.newHashMap();

   protected AbstractTabbedConfigScreen(class_437 parent, class_2561 title, class_2960 backgroundLocation) {
      super(parent, title, backgroundLocation);
   }

   @Override
   public final void registerCategoryBackground(String text, class_2960 identifier) {
      this.categoryBackgroundLocation.put(text, identifier);
   }

   @Override
   public void registerCategoryTransparency(String text, boolean transparent) {
      this.categoryTransparentBackground.put(text, transparent);
   }

   @Override
   public boolean isTransparentBackground() {
      class_2561 selectedCategory = this.getSelectedCategory();
      return this.categoryTransparentBackground.containsKey(selectedCategory.getString())
         ? this.categoryTransparentBackground.get(selectedCategory.getString())
         : super.isTransparentBackground();
   }

   @Override
   public class_2960 getBackgroundLocation() {
      class_2561 selectedCategory = this.getSelectedCategory();
      return this.categoryBackgroundLocation.containsKey(selectedCategory.getString())
         ? this.categoryBackgroundLocation.get(selectedCategory.getString())
         : super.getBackgroundLocation();
   }
}
