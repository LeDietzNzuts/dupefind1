package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.ScrollingContainer;
import me.shedaniel.clothconfig2.api.Tooltip;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.clothconfig2.gui.entries.EmptyEntry;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.clothconfig2.gui.widget.SearchFieldEntry;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1074;
import net.minecraft.class_2561;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_3532;
import net.minecraft.class_3545;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class ClothConfigScreen extends AbstractTabbedConfigScreen {
   private final ScrollingContainer tabsScroller = new ScrollingContainer() {
      @Override
      public Rectangle getBounds() {
         return new Rectangle(0, 0, 1, ClothConfigScreen.this.field_22789 - 40);
      }

      @Override
      public int getMaxScrollHeight() {
         return (int)ClothConfigScreen.this.getTabsMaximumScrolled();
      }

      @Override
      public void updatePosition(float delta) {
         super.updatePosition(delta);
         this.scrollAmount = this.clamp(this.scrollAmount, 0.0);
      }
   };
   public ClothConfigScreen.ListWidget<AbstractConfigEntry<AbstractConfigEntry<?>>> listWidget;
   private final LinkedHashMap<class_2561, List<AbstractConfigEntry<?>>> categorizedEntries = Maps.newLinkedHashMap();
   private final List<class_3545<class_2561, Integer>> tabs;
   private SearchFieldEntry searchFieldEntry;
   private class_339 buttonLeftTab;
   private class_339 buttonRightTab;
   private Rectangle tabsBounds;
   private Rectangle tabsLeftBounds;
   private Rectangle tabsRightBounds;
   private double tabsMaximumScrolled = -1.0;
   private final List<ClothConfigTabButton> tabButtons = Lists.newArrayList();
   private final Map<String, ConfigCategory> categoryMap;

   @Internal
   public ClothConfigScreen(class_437 parent, class_2561 title, Map<String, ConfigCategory> categoryMap, class_2960 backgroundLocation) {
      super(parent, title, backgroundLocation);
      categoryMap.forEach((categoryName, category) -> {
         List<AbstractConfigEntry<?>> entries = Lists.newArrayList();

         for (Object object : category.getEntries()) {
            AbstractConfigListEntry<?> entry;
            if (object instanceof class_3545) {
               entry = (AbstractConfigListEntry<?>)((class_3545)object).method_15441();
            } else {
               entry = (AbstractConfigListEntry<?>)object;
            }

            entry.setScreen(this);
            entries.add(entry);
         }

         this.categorizedEntries.put(category.getCategoryKey(), entries);
         if (category.getBackground() != null) {
            this.registerCategoryBackground(category.getCategoryKey().getString(), category.getBackground());
            this.registerCategoryTransparency(category.getCategoryKey().getString(), false);
         }
      });
      this.tabs = this.categorizedEntries
         .keySet()
         .stream()
         .map(s -> new class_3545(s, class_310.method_1551().field_1772.method_27525(s) + 8))
         .collect(Collectors.toList());
      this.categoryMap = categoryMap;
   }

   @Override
   public class_2561 getSelectedCategory() {
      return (class_2561)this.tabs.get(this.selectedCategoryIndex).method_15442();
   }

   @Override
   public Map<class_2561, List<AbstractConfigEntry<?>>> getCategorizedEntries() {
      return this.categorizedEntries;
   }

   protected void method_25426() {
      super.method_25426();
      this.tabButtons.clear();
      this.childrenL()
         .add(
            this.listWidget = new ClothConfigScreen.ListWidget<>(
               this, this.field_22787, this.field_22789, this.field_22790, this.isShowingTabs() ? 70 : 30, this.field_22790 - 32, this.getBackgroundLocation()
            )
         );
      this.listWidget.method_25396().add(new EmptyEntry(5));
      this.listWidget.method_25396().add(this.searchFieldEntry = new SearchFieldEntry(this, this.listWidget));
      this.listWidget.method_25396().add(new EmptyEntry(5));
      if (this.categorizedEntries.size() > this.selectedCategoryIndex) {
         this.listWidget.method_25396().addAll((List)Lists.newArrayList(this.categorizedEntries.values()).get(this.selectedCategoryIndex));
      }

      int buttonWidths = Math.min(200, (this.field_22789 - 50 - 12) / 3);
      this.method_37063(
         class_4185.method_46430(
               this.isEdited() ? class_2561.method_43471("text.cloth-config.cancel_discard") : class_2561.method_43471("gui.cancel"), widget -> this.quit()
            )
            .method_46434(this.field_22789 / 2 - buttonWidths - 3, this.field_22790 - 26, buttonWidths, 20)
            .method_46431()
      );
      this.method_37063(
         new class_4185(
            this.field_22789 / 2 + 3, this.field_22790 - 26, buttonWidths, 20, class_2561.method_43473(), button -> this.saveAll(true), Supplier::get
         ) {
            public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
               boolean hasErrors = false;

               label40:
               for (List<AbstractConfigEntry<?>> entries : Lists.newArrayList(ClothConfigScreen.this.categorizedEntries.values())) {
                  Iterator var8 = entries.iterator();

                  while (true) {
                     if (var8.hasNext()) {
                        AbstractConfigEntry<?> entry = (AbstractConfigEntry<?>)var8.next();
                        if (!entry.getConfigError().isPresent()) {
                           continue;
                        }

                        hasErrors = true;
                     }

                     if (hasErrors) {
                        break label40;
                     }
                     break;
                  }
               }

               this.field_22763 = ClothConfigScreen.this.isEdited() && !hasErrors;
               this.method_25355(
                  hasErrors ? class_2561.method_43471("text.cloth-config.error_cannot_save") : class_2561.method_43471("text.cloth-config.save_and_done")
               );
               super.method_48579(graphics, mouseX, mouseY, delta);
            }
         }
      );
      if (this.isShowingTabs()) {
         this.tabsBounds = new Rectangle(0, 41, this.field_22789, 24);
         this.tabsLeftBounds = new Rectangle(0, 41, 18, 24);
         this.tabsRightBounds = new Rectangle(this.field_22789 - 18, 41, 18, 24);
         this.childrenL()
            .add(
               this.buttonLeftTab = new class_4185(4, 44, 12, 18, class_2561.method_43473(), button -> this.tabsScroller.scrollTo(0.0, true), Supplier::get) {
                  public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
                     RenderSystem.setShader(class_757::method_34542);
                     RenderSystem.setShaderTexture(0, AbstractConfigScreen.CONFIG_TEX);
                     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.field_22765);
                     RenderSystem.enableBlend();
                     RenderSystem.blendFuncSeparate(770, 771, 0, 1);
                     RenderSystem.blendFunc(770, 771);
                     graphics.method_25302(
                        AbstractConfigScreen.CONFIG_TEX,
                        this.method_46426(),
                        this.method_46427(),
                        12,
                        18 * (!this.method_37303() ? 0 : (this.method_25367() ? 2 : 1)),
                        this.field_22758,
                        this.field_22759
                     );
                  }
               }
            );
         int j = 0;

         for (class_3545<class_2561, Integer> tab : this.tabs) {
            this.tabButtons
               .add(
                  new ClothConfigTabButton(
                     this,
                     j,
                     -100,
                     43,
                     (Integer)tab.method_15441(),
                     20,
                     (class_2561)tab.method_15442(),
                     this.categoryMap.get(((class_2561)tab.method_15442()).getString()).getDescription()
                  )
               );
            j++;
         }

         this.childrenL().addAll(this.tabButtons);
         this.childrenL()
            .add(
               this.buttonRightTab = new class_4185(
                  this.field_22789 - 16,
                  44,
                  12,
                  18,
                  class_2561.method_43473(),
                  button -> this.tabsScroller.scrollTo(this.tabsScroller.getMaxScroll(), true),
                  Supplier::get
               ) {
                  public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
                     RenderSystem.setShader(class_757::method_34542);
                     RenderSystem.setShaderTexture(0, AbstractConfigScreen.CONFIG_TEX);
                     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.field_22765);
                     RenderSystem.enableBlend();
                     RenderSystem.blendFuncSeparate(770, 771, 0, 1);
                     RenderSystem.blendFunc(770, 771);
                     graphics.method_25302(
                        AbstractConfigScreen.CONFIG_TEX,
                        this.method_46426(),
                        this.method_46427(),
                        0,
                        18 * (!this.method_37303() ? 0 : (this.method_25367() ? 2 : 1)),
                        this.field_22758,
                        this.field_22759
                     );
                  }
               }
            );
      } else {
         this.tabsBounds = this.tabsLeftBounds = this.tabsRightBounds = new Rectangle();
      }

      Optional.ofNullable(this.afterInitConsumer).ifPresent(consumer -> consumer.accept(this));
   }

   @Override
   public boolean matchesSearch(Iterator<String> tags) {
      return this.searchFieldEntry.matchesSearch(tags);
   }

   public boolean method_25401(double mouseX, double mouseY, double amountX, double amountY) {
      if (this.tabsBounds.contains(mouseX, mouseY)
         && !this.tabsLeftBounds.contains(mouseX, mouseY)
         && !this.tabsRightBounds.contains(mouseX, mouseY)
         && amountY != 0.0) {
         this.tabsScroller.offset(-amountY * 16.0, true);
         return true;
      } else {
         return super.method_25401(mouseX, mouseY, amountX, amountY);
      }
   }

   public double getTabsMaximumScrolled() {
      if (this.tabsMaximumScrolled == -1.0) {
         int[] i = new int[]{0};

         for (class_3545<class_2561, Integer> pair : this.tabs) {
            i[0] += pair.method_15441() + 2;
         }

         this.tabsMaximumScrolled = i[0];
      }

      return this.tabsMaximumScrolled + 6.0;
   }

   public void resetTabsMaximumScrolled() {
      this.tabsMaximumScrolled = -1.0;
   }

   @Override
   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      if (this.isShowingTabs()) {
         this.tabsScroller.updatePosition(delta * 3.0F);
         int xx = 24 - (int)this.tabsScroller.scrollAmount;

         for (ClothConfigTabButton tabButton : this.tabButtons) {
            tabButton.method_46421(xx);
            xx += tabButton.method_25368() + 2;
         }

         this.buttonLeftTab.field_22763 = this.tabsScroller.scrollAmount > 0.0;
         this.buttonRightTab.field_22763 = this.tabsScroller.scrollAmount < this.getTabsMaximumScrolled() - this.field_22789 + 40.0;
      }

      if (!this.isTransparentBackground()) {
         this.method_57735(graphics);
      } else {
         if (this.field_22787.field_1687 == null) {
            this.method_57728(graphics, delta);
         }

         this.method_57734(delta);
         this.method_57735(graphics);
      }

      this.listWidget.method_25394(graphics, mouseX, mouseY, delta);
      ScissorsHandler.INSTANCE
         .scissor(new Rectangle(this.listWidget.left, this.listWidget.top, this.listWidget.width, this.listWidget.bottom - this.listWidget.top));

      for (AbstractConfigEntry child : this.listWidget.method_25396()) {
         child.lateRender(graphics, mouseX, mouseY, delta);
      }

      ScissorsHandler.INSTANCE.removeLastScissor();
      if (this.isShowingTabs()) {
         graphics.method_27534(this.field_22787.field_1772, this.field_22785, this.field_22789 / 2, 18, -1);
         Rectangle onlyInnerTabBounds = new Rectangle(this.tabsBounds.x + 20, this.tabsBounds.y, this.tabsBounds.width - 40, this.tabsBounds.height);
         ScissorsHandler.INSTANCE.scissor(onlyInnerTabBounds);
         if (this.isTransparentBackground()) {
            graphics.method_25296(
               onlyInnerTabBounds.x, onlyInnerTabBounds.y, onlyInnerTabBounds.getMaxX(), onlyInnerTabBounds.getMaxY(), 1744830464, 1744830464
            );
         } else {
            this.overlayBackground(graphics, onlyInnerTabBounds, 32, 32, 32, 255, 255);
         }

         this.tabButtons.forEach(widget -> widget.method_25394(graphics, mouseX, mouseY, delta));
         this.drawTabsShades(graphics, 0, this.isTransparentBackground() ? 120 : 255);
         ScissorsHandler.INSTANCE.removeLastScissor();
         this.buttonLeftTab.method_25394(graphics, mouseX, mouseY, delta);
         this.buttonRightTab.method_25394(graphics, mouseX, mouseY, delta);
      } else {
         graphics.method_27534(this.field_22787.field_1772, this.field_22785, this.field_22789 / 2, 12, -1);
      }

      if (this.isEditable()) {
         List<class_2561> errors = Lists.newArrayList();

         for (List<AbstractConfigEntry<?>> entries : Lists.newArrayList(this.categorizedEntries.values())) {
            for (AbstractConfigEntry<?> entry : entries) {
               if (entry.getConfigError().isPresent()) {
                  errors.add(entry.getConfigError().get());
               }
            }
         }

         if (errors.size() > 0) {
            RenderSystem.setShader(class_757::method_34542);
            RenderSystem.setShaderTexture(0, CONFIG_TEX);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "§c"
               + (errors.size() == 1 ? errors.get(0).method_27662().getString() : class_1074.method_4662("text.cloth-config.multi_error", new Object[0]));
            if (this.isTransparentBackground()) {
               int stringWidth = this.field_22787.field_1772.method_1727(text);
               graphics.method_25296(8, 9, 20 + stringWidth, 14 + 9, 1744830464, 1744830464);
            }

            graphics.method_25302(CONFIG_TEX, 10, 10, 0, 54, 3, 11);
            graphics.method_25303(this.field_22787.field_1772, text, 18, 12, -1);
            if (errors.size() > 1) {
               int stringWidth = this.field_22787.field_1772.method_1727(text);
               if (mouseX >= 10 && mouseY >= 10 && mouseX <= 18 + stringWidth && mouseY <= 14 + 9) {
                  this.addTooltip(Tooltip.of(new Point(mouseX, mouseY), errors.toArray(new class_2561[0])));
               }
            }
         }
      } else if (!this.isEditable()) {
         RenderSystem.setShader(class_757::method_34542);
         RenderSystem.setShaderTexture(0, CONFIG_TEX);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         String textx = "§c" + class_1074.method_4662("text.cloth-config.not_editable", new Object[0]);
         if (this.isTransparentBackground()) {
            int stringWidth = this.field_22787.field_1772.method_1727(textx);
            graphics.method_25296(8, 9, 20 + stringWidth, 14 + 9, 1744830464, 1744830464);
         }

         graphics.method_25302(CONFIG_TEX, 10, 10, 0, 54, 3, 11);
         graphics.method_25303(this.field_22787.field_1772, textx, 18, 12, -1);
      }

      super.method_25394(graphics, mouseX, mouseY, delta);
   }

   public void method_25420(class_332 guiGraphics, int i, int j, float f) {
   }

   private void drawTabsShades(class_332 graphics, int lightColor, int darkColor) {
      this.drawTabsShades(graphics.method_51448(), lightColor, darkColor);
   }

   private void drawTabsShades(class_4587 matrices, int lightColor, int darkColor) {
      this.drawTabsShades(matrices.method_23760().method_23761(), lightColor, darkColor);
   }

   private void drawTabsShades(Matrix4f matrix, int lightColor, int darkColor) {
      RenderSystem.enableBlend();
      RenderSystem.blendFuncSeparate(770, 771, 0, 1);
      RenderSystem.setShader(class_757::method_34543);
      class_289 tesselator = class_289.method_1348();
      class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix, this.tabsBounds.getMinX() + 20, this.tabsBounds.getMinY() + 4, 0.0F)
         .method_22913(0.0F, 1.0F)
         .method_1336(0, 0, 0, lightColor);
      buffer.method_22918(matrix, this.tabsBounds.getMaxX() - 20, this.tabsBounds.getMinY() + 4, 0.0F)
         .method_22913(1.0F, 1.0F)
         .method_1336(0, 0, 0, lightColor);
      buffer.method_22918(matrix, this.tabsBounds.getMaxX() - 20, this.tabsBounds.getMinY(), 0.0F).method_22913(1.0F, 0.0F).method_1336(0, 0, 0, darkColor);
      buffer.method_22918(matrix, this.tabsBounds.getMinX() + 20, this.tabsBounds.getMinY(), 0.0F).method_22913(0.0F, 0.0F).method_1336(0, 0, 0, darkColor);
      buffer.method_22918(matrix, this.tabsBounds.getMinX() + 20, this.tabsBounds.getMaxY(), 0.0F).method_22913(0.0F, 1.0F).method_1336(0, 0, 0, darkColor);
      buffer.method_22918(matrix, this.tabsBounds.getMaxX() - 20, this.tabsBounds.getMaxY(), 0.0F).method_22913(1.0F, 1.0F).method_1336(0, 0, 0, darkColor);
      buffer.method_22918(matrix, this.tabsBounds.getMaxX() - 20, this.tabsBounds.getMaxY() - 4, 0.0F)
         .method_22913(1.0F, 0.0F)
         .method_1336(0, 0, 0, lightColor);
      buffer.method_22918(matrix, this.tabsBounds.getMinX() + 20, this.tabsBounds.getMaxY() - 4, 0.0F)
         .method_22913(0.0F, 0.0F)
         .method_1336(0, 0, 0, lightColor);
      RenderSystem.disableBlend();
   }

   @Override
   public void save() {
      super.save();
   }

   @Override
   public boolean isEditable() {
      return super.isEditable();
   }

   public static class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
      private final AbstractConfigScreen screen;
      private final ValueAnimator<Rectangle> currentBounds = ValueAnimator.ofRectangle();
      public UnaryOperator<List<R>> entriesTransformer = UnaryOperator.identity();
      public Rectangle thisTimeTarget;
      public long lastTouch;

      public ListWidget(AbstractConfigScreen screen, class_310 client, int width, int height, int top, int bottom, class_2960 backgroundLocation) {
         super(client, width, height, top, bottom, backgroundLocation);
         this.setRenderSelection(false);
         this.screen = screen;
      }

      @Override
      public int getItemWidth() {
         return this.width - 80;
      }

      @Override
      protected int getScrollbarPosition() {
         return this.left + this.width - 36;
      }

      protected void renderItem(
         class_332 graphics, R item, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta
      ) {
         if (item instanceof AbstractConfigEntry) {
            ((AbstractConfigEntry)item).updateSelected(this.getFocused() == item);
         }

         super.renderItem(graphics, item, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
      }

      @Override
      protected void renderList(class_332 graphics, int startX, int startY, int mouseX, int mouseY, float delta) {
         this.thisTimeTarget = null;
         Rectangle hoverBounds = this.currentBounds.value();
         if (!hoverBounds.isEmpty()) {
            long timePast = System.currentTimeMillis() - this.lastTouch;
            int alpha = timePast <= 200L ? 255 : class_3532.method_15384(255.0 - Math.min((float)(timePast - 200L), 500.0F) / 500.0F * 255.0);
            alpha = alpha * 36 / 255 << 24;
            graphics.method_25296(
               hoverBounds.x,
               hoverBounds.y - (int)this.scroll,
               hoverBounds.getMaxX(),
               hoverBounds.getMaxY() - (int)this.scroll,
               16777215 | alpha,
               16777215 | alpha
            );
         }

         super.renderList(graphics, startX, startY, mouseX, mouseY, delta);
         if (this.thisTimeTarget != null && this.method_25405(mouseX, mouseY)) {
            this.lastTouch = System.currentTimeMillis();
         }

         if (this.thisTimeTarget != null && !this.thisTimeTarget.equals(this.currentBounds.target())) {
            this.currentBounds.setTo(this.thisTimeTarget, 100L);
         } else if (!this.currentBounds.target().isEmpty()) {
            this.currentBounds.update(delta);
         }
      }

      protected static void fillGradient(
         Matrix4f matrix4f, class_287 bufferBuilder, double xStart, double yStart, double xEnd, double yEnd, int i, int j, int k
      ) {
         float f = (j >> 24 & 0xFF) / 255.0F;
         float g = (j >> 16 & 0xFF) / 255.0F;
         float h = (j >> 8 & 0xFF) / 255.0F;
         float l = (j & 0xFF) / 255.0F;
         float m = (k >> 24 & 0xFF) / 255.0F;
         float n = (k >> 16 & 0xFF) / 255.0F;
         float o = (k >> 8 & 0xFF) / 255.0F;
         float p = (k & 0xFF) / 255.0F;
         bufferBuilder.method_22918(matrix4f, (float)xEnd, (float)yStart, i).method_22915(g, h, l, f);
         bufferBuilder.method_22918(matrix4f, (float)xStart, (float)yStart, i).method_22915(g, h, l, f);
         bufferBuilder.method_22918(matrix4f, (float)xStart, (float)yEnd, i).method_22915(n, o, p, m);
         bufferBuilder.method_22918(matrix4f, (float)xEnd, (float)yEnd, i).method_22915(n, o, p, m);
      }

      @Override
      public boolean method_25402(double mouseX, double mouseY, int button) {
         this.updateScrollingState(mouseX, mouseY, button);
         if (!this.method_25405(mouseX, mouseY)) {
            return false;
         } else {
            for (R entry : this.method_25396()) {
               if (entry.method_25402(mouseX, mouseY, button)) {
                  this.method_25395(entry);
                  this.method_25398(true);
                  return true;
               }
            }

            if (button == 0) {
               this.clickedHeader((int)(mouseX - (this.left + this.width / 2 - this.getItemWidth() / 2)), (int)(mouseY - this.top) + (int)this.getScroll() - 4);
               return true;
            } else {
               return this.scrolling;
            }
         }
      }

      @Override
      protected void renderBackBackground(class_332 graphics, class_287 buffer, class_289 tessellator) {
         if (!this.screen.isTransparentBackground()) {
            super.renderBackBackground(graphics, buffer, tessellator);
         } else {
            RenderSystem.enableBlend();
            graphics.method_25290(
               class_2960.method_60656("textures/gui/menu_list_background.png"),
               this.left,
               this.top,
               this.right,
               this.bottom,
               this.width,
               this.bottom - this.top,
               32,
               32
            );
            RenderSystem.disableBlend();
         }
      }

      @Override
      protected void renderHoleBackground(class_332 graphics, int y1, int y2, int alpha1, int alpha2) {
         if (!this.screen.isTransparentBackground()) {
            super.renderHoleBackground(graphics, y1, y2, alpha1, alpha2);
         }
      }

      @Override
      public List<R> method_25396() {
         return this.entriesTransformer.apply(super.method_25396());
      }
   }
}
