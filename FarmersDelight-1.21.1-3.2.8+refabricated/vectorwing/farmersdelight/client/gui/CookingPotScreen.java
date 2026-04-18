package vectorwing.farmersdelight.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1729;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_344;
import net.minecraft.class_465;
import net.minecraft.class_507;
import net.minecraft.class_518;
import net.minecraft.class_5250;
import net.minecraft.class_8666;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class CookingPotScreen extends class_465<CookingPotMenu> implements class_518 {
   private static final class_8666 RECIPE_BUTTON = new class_8666(class_2960.method_60656("recipe_book/button"), class_2960.method_60656("recipe_book/button"));
   private static final class_2960 BACKGROUND_TEXTURE = class_2960.method_60655("farmersdelight", "textures/gui/cooking_pot.png");
   private static final Rectangle HEAT_ICON = new Rectangle(47, 55, 17, 15);
   private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);
   private final CookingPotRecipeBookComponent recipeBookComponent = new CookingPotRecipeBookComponent();
   private boolean widthTooNarrow;

   public CookingPotScreen(CookingPotMenu screenContainer, class_1661 inv, class_2561 titleIn) {
      super(screenContainer, inv, titleIn);
   }

   public void method_25426() {
      super.method_25426();
      this.widthTooNarrow = this.field_22789 < 379;
      this.field_25267 = 28;
      this.recipeBookComponent.method_2597(this.field_22789, this.field_22790, this.field_22787, this.widthTooNarrow, (class_1729)this.field_2797);
      this.field_2776 = this.recipeBookComponent.method_2595(this.field_22789, this.field_2792);
      if (Configuration.ENABLE_RECIPE_BOOK_COOKING_POT.get()) {
         this.method_37063(new class_344(this.field_2776 + 5, this.field_22790 / 2 - 49, 20, 18, RECIPE_BUTTON, button -> {
            this.recipeBookComponent.method_2591();
            this.field_2776 = this.recipeBookComponent.method_2595(this.field_22789, this.field_2792);
            button.method_48229(this.field_2776 + 5, this.field_22790 / 2 - 49);
         }));
      } else {
         this.recipeBookComponent.hide();
         this.field_2776 = this.recipeBookComponent.method_2595(this.field_22789, this.field_2792);
      }

      this.method_25429(this.recipeBookComponent);
      this.method_48265(this.recipeBookComponent);
   }

   protected void method_37432() {
      super.method_37432();
      this.recipeBookComponent.method_2590();
   }

   public void method_25394(class_332 gui, int mouseX, int mouseY, float partialTicks) {
      if (this.recipeBookComponent.method_2605() && this.widthTooNarrow) {
         this.method_25420(gui, mouseX, mouseY, partialTicks);
         this.recipeBookComponent.method_25394(gui, mouseX, mouseY, partialTicks);
      } else {
         super.method_25394(gui, mouseX, mouseY, partialTicks);
         this.recipeBookComponent.method_25394(gui, mouseX, mouseY, partialTicks);
         this.recipeBookComponent.method_2581(gui, this.field_2776, this.field_2800, false, partialTicks);
      }

      this.renderMealDisplayTooltip(gui, mouseX, mouseY);
      this.renderHeatIndicatorTooltip(gui, mouseX, mouseY);
      this.recipeBookComponent.method_2601(gui, this.field_2776, this.field_2800, mouseX, mouseY);
   }

   private void renderHeatIndicatorTooltip(class_332 gui, int mouseX, int mouseY) {
      if (this.method_2378(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
         String key = "container.cooking_pot." + (((CookingPotMenu)this.field_2797).isHeated() ? "heated" : "not_heated");
         gui.method_51438(this.field_22793, TextUtils.getTranslation(key), mouseX, mouseY);
      }
   }

   protected void renderMealDisplayTooltip(class_332 gui, int mouseX, int mouseY) {
      if (this.field_22787 != null
         && this.field_22787.field_1724 != null
         && ((CookingPotMenu)this.field_2797).method_34255().method_7960()
         && this.field_2787 != null
         && this.field_2787.method_7681()) {
         if (this.field_2787.field_7874 == 6) {
            List<class_2561> tooltip = new ArrayList<>();
            class_1799 mealStack = this.field_2787.method_7677();
            tooltip.add(((class_5250)mealStack.method_7909().method_7848()).method_27692(mealStack.method_7932().method_58413()));
            class_1799 containerStack = ((CookingPotMenu)this.field_2797).blockEntity.getContainer();
            if (!containerStack.method_7960()) {
               String container = !containerStack.method_7960() ? containerStack.method_7909().method_7848().getString() : "";
               tooltip.add(TextUtils.getTranslation("container.cooking_pot.served_on", container).method_27692(class_124.field_1080));
            }

            gui.method_51434(this.field_22793, tooltip, mouseX, mouseY);
         } else {
            gui.method_51446(this.field_22793, this.field_2787.method_7677(), mouseX, mouseY);
         }
      }
   }

   protected void method_2388(class_332 gui, int mouseX, int mouseY) {
      super.method_2388(gui, mouseX, mouseY);
      gui.method_51439(this.field_22793, this.field_29347, 8, this.field_2779 - 96 + 2, 4210752, false);
   }

   protected void method_2389(class_332 gui, float partialTicks, int mouseX, int mouseY) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.field_22787 != null) {
         gui.method_25302(BACKGROUND_TEXTURE, this.field_2776, this.field_2800, 0, 0, this.field_2792, this.field_2779);
         if (((CookingPotMenu)this.field_2797).isHeated()) {
            gui.method_25302(BACKGROUND_TEXTURE, this.field_2776 + HEAT_ICON.x, this.field_2800 + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
         }

         int l = ((CookingPotMenu)this.field_2797).getCookProgressionScaled();
         gui.method_25302(BACKGROUND_TEXTURE, this.field_2776 + PROGRESS_ARROW.x, this.field_2800 + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
      }
   }

   protected boolean method_2378(int x, int y, int width, int height, double mouseX, double mouseY) {
      return (!this.widthTooNarrow || !this.recipeBookComponent.method_2605()) && super.method_2378(x, y, width, height, mouseX, mouseY);
   }

   public boolean method_25402(double mouseX, double mouseY, int buttonId) {
      if (this.recipeBookComponent.method_25402(mouseX, mouseY, buttonId)) {
         this.method_25395(this.recipeBookComponent);
         return true;
      } else {
         return this.widthTooNarrow && this.recipeBookComponent.method_2605() || super.method_25402(mouseX, mouseY, buttonId);
      }
   }

   protected boolean method_2381(double mouseX, double mouseY, int x, int y, int buttonIdx) {
      boolean flag = mouseX < x || mouseY < y || mouseX >= x + this.field_2792 || mouseY >= y + this.field_2779;
      return flag && this.recipeBookComponent.method_2598(mouseX, mouseY, this.field_2776, this.field_2800, this.field_2792, this.field_2779, buttonIdx);
   }

   protected void method_2383(class_1735 slot, int mouseX, int mouseY, class_1713 clickType) {
      super.method_2383(slot, mouseX, mouseY, clickType);
      this.recipeBookComponent.method_2600(slot);
   }

   public void method_16891() {
      this.recipeBookComponent.method_2592();
   }

   @NotNull
   public class_507 method_2659() {
      return this.recipeBookComponent;
   }
}
