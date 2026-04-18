package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.Expandable;
import me.shedaniel.clothconfig2.api.LazyResettable;
import me.shedaniel.clothconfig2.api.ReferenceBuildingConfigScreen;
import me.shedaniel.clothconfig2.api.ReferenceProvider;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.scroll.ScrollingContainer;
import me.shedaniel.clothconfig2.gui.entries.EmptyEntry;
import me.shedaniel.clothconfig2.gui.widget.DynamicEntryListWidget;
import me.shedaniel.clothconfig2.gui.widget.SearchFieldEntry;
import me.shedaniel.math.Rectangle;
import net.minecraft.class_1109;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_3545;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import net.minecraft.class_6379;
import net.minecraft.class_757;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_327.class_6415;
import net.minecraft.class_4597.class_4598;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.joml.Matrix4f;

public class GlobalizedClothConfigScreen extends AbstractConfigScreen implements ReferenceBuildingConfigScreen, Expandable {
   public ClothConfigScreen.ListWidget<AbstractConfigEntry<AbstractConfigEntry<?>>> listWidget;
   private class_339 cancelButton;
   private class_339 exitButton;
   private final LinkedHashMap<class_2561, List<AbstractConfigEntry<?>>> categorizedEntries = Maps.newLinkedHashMap();
   private final ScrollingContainer sideScroller = new ScrollingContainer() {
      @Override
      public Rectangle getBounds() {
         return new Rectangle(4, 4, GlobalizedClothConfigScreen.this.getSideSliderPosition() - 14 - 4, GlobalizedClothConfigScreen.this.field_22790 - 8);
      }

      @Override
      public int getMaxScrollHeight() {
         int i = 0;

         for (GlobalizedClothConfigScreen.Reference reference : GlobalizedClothConfigScreen.this.references) {
            if (i != 0) {
               i = (int)(i + 3.0F * reference.getScale());
            }

            i = (int)(i + 9.0F * reference.getScale());
         }

         return i;
      }
   };
   private GlobalizedClothConfigScreen.Reference lastHoveredReference = null;
   private SearchFieldEntry searchFieldEntry;
   private final ScrollingContainer sideSlider = new ScrollingContainer() {
      private final Rectangle empty = new Rectangle();

      @Override
      public Rectangle getBounds() {
         return this.empty;
      }

      @Override
      public int getMaxScrollHeight() {
         return 1;
      }
   };
   private final List<GlobalizedClothConfigScreen.Reference> references = Lists.newArrayList();
   private final LazyResettable<Integer> sideExpandLimit = new LazyResettable<>(() -> {
      int max = 0;

      for (GlobalizedClothConfigScreen.Reference reference : this.references) {
         class_2561 referenceText = reference.getText();
         int width = this.field_22793.method_27525(class_2561.method_43470(StringUtils.repeat("  ", reference.getIndent()) + "- ").method_10852(referenceText));
         if (width > max) {
            max = width;
         }
      }

      return Math.min(max + 8, this.field_22789 / 4);
   });
   private boolean requestingReferenceRebuilding = false;

   @Internal
   public GlobalizedClothConfigScreen(class_437 parent, class_2561 title, Map<String, ConfigCategory> categoryMap, class_2960 backgroundLocation) {
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
      });
      this.sideSlider.scrollTo(0.0, false);
   }

   @Override
   public void requestReferenceRebuilding() {
      this.requestingReferenceRebuilding = true;
   }

   @Override
   public Map<class_2561, List<AbstractConfigEntry<?>>> getCategorizedEntries() {
      return this.categorizedEntries;
   }

   protected void method_25426() {
      super.method_25426();
      this.sideExpandLimit.reset();
      this.references.clear();
      this.buildReferences();
      this.method_25429(
         this.listWidget = new ClothConfigScreen.ListWidget<>(
            this, this.field_22787, this.field_22789 - 14, this.field_22790, 30, this.field_22790 - 32, this.getBackgroundLocation()
         )
      );
      this.listWidget.setLeftPos(14);
      this.listWidget.method_25396().add(new EmptyEntry(5));
      this.listWidget.method_25396().add(this.searchFieldEntry = new SearchFieldEntry(this, this.listWidget));
      this.listWidget.method_25396().add(new EmptyEntry(5));
      this.categorizedEntries
         .forEach(
            (category, entries) -> {
               if (!this.listWidget.method_25396().isEmpty()) {
                  this.listWidget.method_25396().add(new EmptyEntry(5));
               }

               this.listWidget.method_25396().add(new EmptyEntry(4));
               this.listWidget
                  .method_25396()
                  .add(new GlobalizedClothConfigScreen.CategoryTextEntry(category, category.method_27661().method_27692(class_124.field_1067)));
               this.listWidget.method_25396().add(new EmptyEntry(4));
               this.listWidget.method_25396().addAll(entries);
            }
         );
      int buttonWidths = Math.min(200, (this.field_22789 - 50 - 12) / 3);
      this.method_37063(
         this.cancelButton = class_4185.method_46430(
               this.isEdited() ? class_2561.method_43471("text.cloth-config.cancel_discard") : class_2561.method_43471("gui.cancel"), widget -> this.quit()
            )
            .method_46434(0, this.field_22790 - 26, buttonWidths, 20)
            .method_46431()
      );
      this.method_37063(
         this.exitButton = new class_4185(0, this.field_22790 - 26, buttonWidths, 20, class_2561.method_43473(), button -> this.saveAll(true), Supplier::get) {
            public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
               boolean hasErrors = false;

               label35:
               for (List<AbstractConfigEntry<?>> entries : GlobalizedClothConfigScreen.this.categorizedEntries.values()) {
                  for (AbstractConfigEntry<?> entry : entries) {
                     if (entry.getConfigError().isPresent()) {
                        hasErrors = true;
                        break label35;
                     }
                  }
               }

               this.field_22763 = GlobalizedClothConfigScreen.this.isEdited() && !hasErrors;
               this.method_25355(
                  hasErrors ? class_2561.method_43471("text.cloth-config.error_cannot_save") : class_2561.method_43471("text.cloth-config.save_and_done")
               );
               super.method_48579(graphics, mouseX, mouseY, delta);
            }
         }
      );
      Optional.ofNullable(this.afterInitConsumer).ifPresent(consumer -> consumer.accept(this));
   }

   @Override
   public boolean matchesSearch(Iterator<String> tags) {
      return this.searchFieldEntry.matchesSearch(tags);
   }

   private void buildReferences() {
      this.categorizedEntries.forEach((categoryText, entries) -> {
         this.references.add(new GlobalizedClothConfigScreen.CategoryReference(categoryText));

         for (AbstractConfigEntry<?> entry : entries) {
            this.buildReferenceFor(entry, 1);
         }
      });
   }

   private void buildReferenceFor(AbstractConfigEntry<?> entry, int layer) {
      List<ReferenceProvider<?>> referencableEntries = entry.getReferenceProviderEntries();
      if (referencableEntries != null) {
         this.references.add(new GlobalizedClothConfigScreen.ConfigEntryReference(entry, layer));

         for (ReferenceProvider<?> referencableEntry : referencableEntries) {
            this.buildReferenceFor(referencableEntry.provideReferenceEntry(), layer + 1);
         }
      }
   }

   @Override
   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      this.lastHoveredReference = null;
      if (this.requestingReferenceRebuilding) {
         this.references.clear();
         this.buildReferences();
         this.requestingReferenceRebuilding = false;
      }

      int sliderPosition = this.getSideSliderPosition();
      if (!this.isTransparentBackground()) {
         ScissorsHandler.INSTANCE.scissor(new Rectangle(sliderPosition, 0, this.field_22789 - sliderPosition, this.field_22790));
         this.method_57735(graphics);
         this.overlayBackground(graphics, new Rectangle(14, 0, this.field_22789, this.field_22790), 64, 64, 64, 255, 255);
      } else {
         if (this.field_22787.field_1687 == null) {
            this.method_57728(graphics, delta);
         }

         this.method_57734(delta);
         this.method_57735(graphics);
         ScissorsHandler.INSTANCE.scissor(new Rectangle(sliderPosition, 0, this.field_22789 - sliderPosition, this.field_22790));
      }

      this.listWidget.width = this.field_22789 - sliderPosition;
      this.listWidget.setLeftPos(sliderPosition);
      this.listWidget.method_25394(graphics, mouseX, mouseY, delta);
      ScissorsHandler.INSTANCE
         .scissor(new Rectangle(this.listWidget.left, this.listWidget.top, this.listWidget.width, this.listWidget.bottom - this.listWidget.top));

      for (AbstractConfigEntry<?> child : this.listWidget.method_25396()) {
         child.lateRender(graphics, mouseX, mouseY, delta);
      }

      ScissorsHandler.INSTANCE.removeLastScissor();
      graphics.method_35720(
         this.field_22793,
         this.field_22785.method_30937(),
         (int)(sliderPosition + (this.field_22789 - sliderPosition) / 2.0F - this.field_22793.method_27525(this.field_22785) / 2.0F),
         12,
         -1
      );
      ScissorsHandler.INSTANCE.removeLastScissor();
      this.cancelButton.method_46421(sliderPosition + (this.field_22789 - sliderPosition) / 2 - this.cancelButton.method_25368() - 3);
      this.exitButton.method_46421(sliderPosition + (this.field_22789 - sliderPosition) / 2 + 3);
      super.method_25394(graphics, mouseX, mouseY, delta);
      this.sideSlider.updatePosition(delta);
      this.sideScroller.updatePosition(delta);
      if (this.isTransparentBackground()) {
         RenderSystem.enableBlend();
         graphics.method_25290(
            class_2960.method_60656("textures/gui/menu_list_background.png"), 0, 0, sliderPosition, this.field_22790, sliderPosition, this.field_22790, 32, 32
         );
         graphics.method_25290(
            class_2960.method_60656("textures/gui/menu_list_background.png"),
            0,
            0,
            sliderPosition - 14,
            this.field_22790,
            sliderPosition - 14,
            this.field_22790,
            32,
            32
         );
         graphics.method_25290(DynamicEntryListWidget.VERTICAL_HEADER_SEPARATOR, sliderPosition - 1, 0, 0.0F, 0.0F, 1, this.field_22790, 2, 32);
         if (sliderPosition - 14 - 1 > 0) {
            graphics.method_25290(DynamicEntryListWidget.VERTICAL_HEADER_SEPARATOR, sliderPosition - 14 - 1, 0, 0.0F, 0.0F, 1, this.field_22790, 2, 32);
         }

         RenderSystem.disableBlend();
      } else {
         class_289 tesselator = class_289.method_1348();
         class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
         RenderSystem.setShader(class_757::method_34543);
         RenderSystem.setShaderTexture(0, this.getBackgroundLocation());
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         float f = 32.0F;
         buffer.method_22912(sliderPosition - 14, this.field_22790, 0.0F).method_22913(0.0F, this.field_22790 / 32.0F).method_1336(68, 68, 68, 255);
         buffer.method_22912(sliderPosition, this.field_22790, 0.0F).method_22913(0.4375F, this.field_22790 / 32.0F).method_1336(68, 68, 68, 255);
         buffer.method_22912(sliderPosition, 0.0F, 0.0F).method_22913(0.4375F, 0.0F).method_1336(68, 68, 68, 255);
         buffer.method_22912(sliderPosition - 14, 0.0F, 0.0F).method_22913(0.0F, 0.0F).method_1336(68, 68, 68, 255);
         buffer.method_22912(0.0F, this.field_22790, 0.0F)
            .method_22913(0.0F, (this.field_22790 + this.sideScroller.scrollAmountInt()) / 32.0F)
            .method_1336(32, 32, 32, 255);
         buffer.method_22912(sliderPosition - 14, this.field_22790, 0.0F)
            .method_22913((sliderPosition - 14) / 32.0F, (this.field_22790 + this.sideScroller.scrollAmountInt()) / 32.0F)
            .method_1336(32, 32, 32, 255);
         buffer.method_22912(sliderPosition - 14, 0.0F, 0.0F)
            .method_22913((sliderPosition - 14) / 32.0F, this.sideScroller.scrollAmountInt() / 32.0F)
            .method_1336(32, 32, 32, 255);
         buffer.method_22912(0.0F, 0.0F, 0.0F).method_22913(0.0F, this.sideScroller.scrollAmountInt() / 32.0F).method_1336(32, 32, 32, 255);
         Matrix4f matrix = graphics.method_51448().method_23760().method_23761();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_757::method_34540);
         int shadeColor = this.isTransparentBackground() ? 120 : 160;
         buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1576);
         buffer.method_22918(matrix, sliderPosition + 4, 0.0F, 100.0F).method_1336(0, 0, 0, 0);
         buffer.method_22918(matrix, sliderPosition, 0.0F, 100.0F).method_1336(0, 0, 0, shadeColor);
         buffer.method_22918(matrix, sliderPosition, this.field_22790, 100.0F).method_1336(0, 0, 0, shadeColor);
         buffer.method_22918(matrix, sliderPosition + 4, this.field_22790, 100.0F).method_1336(0, 0, 0, 0);
         shadeColor /= 2;
         buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1576);
         buffer.method_22918(matrix, sliderPosition - 14, 0.0F, 100.0F).method_1336(0, 0, 0, shadeColor);
         buffer.method_22918(matrix, sliderPosition - 14 - 4, 0.0F, 100.0F).method_1336(0, 0, 0, 0);
         buffer.method_22918(matrix, sliderPosition - 14 - 4, this.field_22790, 100.0F).method_1336(0, 0, 0, 0);
         buffer.method_22918(matrix, sliderPosition - 14, this.field_22790, 100.0F).method_1336(0, 0, 0, shadeColor);
         RenderSystem.disableBlend();
      }

      Rectangle slideArrowBounds = new Rectangle(sliderPosition - 14, 0, 14, this.field_22790);
      class_4598 immediate = graphics.method_51450();
      this.field_22793
         .method_1724(
            ">",
            sliderPosition - 7 - this.field_22793.method_1727(">") / 2.0F,
            this.field_22790 / 2,
            (slideArrowBounds.contains(mouseX, mouseY) ? 16777120 : 16777215)
               | class_3532.method_15340(class_3532.method_15384((1.0 - this.sideSlider.scrollAmount()) * 255.0), 0, 255) << 24,
            false,
            graphics.method_51448().method_23760().method_23761(),
            immediate,
            class_6415.field_33993,
            0,
            15728880
         );
      this.field_22793
         .method_1724(
            "<",
            sliderPosition - 7 - this.field_22793.method_1727("<") / 2.0F,
            this.field_22790 / 2,
            (slideArrowBounds.contains(mouseX, mouseY) ? 16777120 : 16777215)
               | class_3532.method_15340(class_3532.method_15384(this.sideSlider.scrollAmount() * 255.0), 0, 255) << 24,
            false,
            graphics.method_51448().method_23760().method_23761(),
            immediate,
            class_6415.field_33993,
            0,
            15728880
         );
      graphics.method_51452();
      Rectangle scrollerBounds = this.sideScroller.getBounds();
      if (!scrollerBounds.isEmpty()) {
         ScissorsHandler.INSTANCE.scissor(new Rectangle(0, 0, sliderPosition - 14, this.field_22790));
         int scrollOffset = scrollerBounds.y - this.sideScroller.scrollAmountInt();

         for (GlobalizedClothConfigScreen.Reference reference : this.references) {
            graphics.method_51448().method_22903();
            graphics.method_51448().method_22905(reference.getScale(), reference.getScale(), reference.getScale());
            class_5250 text = class_2561.method_43470(StringUtils.repeat("  ", reference.getIndent()) + "- ").method_10852(reference.getText());
            if (this.lastHoveredReference == null
               && new Rectangle(
                     scrollerBounds.x,
                     (int)(scrollOffset - 4.0F * reference.getScale()),
                     (int)(this.field_22793.method_27525(text) * reference.getScale()),
                     (int)((9 + 4) * reference.getScale())
                  )
                  .contains(mouseX, mouseY)) {
               this.lastHoveredReference = reference;
            }

            graphics.method_51430(
               this.field_22793, text.method_30937(), scrollerBounds.x, scrollOffset, this.lastHoveredReference == reference ? 16769544 : 16777215, false
            );
            graphics.method_51448().method_22909();
            scrollOffset = (int)(scrollOffset + (9 + 3) * reference.getScale());
         }

         ScissorsHandler.INSTANCE.removeLastScissor();
         this.sideScroller.renderScrollBar(graphics);
      }
   }

   public void method_25420(class_332 guiGraphics, int i, int j, float f) {
   }

   @Override
   public boolean method_25402(double mouseX, double mouseY, int button) {
      Rectangle slideBounds = new Rectangle(0, 0, this.getSideSliderPosition() - 14, this.field_22790);
      if (button == 0 && slideBounds.contains(mouseX, mouseY) && this.lastHoveredReference != null) {
         this.field_22787.method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
         this.lastHoveredReference.go();
         return true;
      } else {
         Rectangle slideArrowBounds = new Rectangle(this.getSideSliderPosition() - 14, 0, 14, this.field_22790);
         if (button == 0 && slideArrowBounds.contains(mouseX, mouseY)) {
            this.setExpanded(!this.isExpanded());
            this.field_22787.method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
            return true;
         } else {
            return super.method_25402(mouseX, mouseY, button);
         }
      }
   }

   @Override
   public boolean isExpanded() {
      return this.sideSlider.scrollTarget() == 1.0;
   }

   @Override
   public void setExpanded(boolean expanded) {
      this.sideSlider.scrollTo(expanded ? 1.0 : 0.0, true, 2000L);
   }

   public boolean method_25401(double mouseX, double mouseY, double amountX, double amountY) {
      Rectangle slideBounds = new Rectangle(0, 0, this.getSideSliderPosition() - 14, this.field_22790);
      if (amountY != 0.0 && slideBounds.contains(mouseX, mouseY)) {
         this.sideScroller.offset(ClothConfigInitializer.getScrollStep() * -amountY, true);
         return true;
      } else {
         return super.method_25401(mouseX, mouseY, amountX, amountY);
      }
   }

   private int getSideSliderPosition() {
      return (int)(this.sideSlider.scrollAmount() * this.sideExpandLimit.get().intValue() + 14.0);
   }

   private class CategoryReference implements GlobalizedClothConfigScreen.Reference {
      private final class_2561 category;

      public CategoryReference(class_2561 category) {
         this.category = category;
      }

      @Override
      public class_2561 getText() {
         return this.category;
      }

      @Override
      public float getScale() {
         return 1.0F;
      }

      @Override
      public void go() {
         int i = 0;

         for (AbstractConfigEntry<?> child : GlobalizedClothConfigScreen.this.listWidget.method_25396()) {
            if (child instanceof GlobalizedClothConfigScreen.CategoryTextEntry
               && ((GlobalizedClothConfigScreen.CategoryTextEntry)child).category == this.category) {
               GlobalizedClothConfigScreen.this.listWidget.scrollTo(i, true);
               return;
            }

            i += child.getItemHeight();
         }
      }
   }

   private static class CategoryTextEntry extends AbstractConfigListEntry<Object> {
      private final class_2561 category;
      private final class_2561 text;

      public CategoryTextEntry(class_2561 category, class_2561 text) {
         super(class_2561.method_43470(UUID.randomUUID().toString()), false);
         this.category = category;
         this.text = text;
      }

      @Override
      public int getItemHeight() {
         List<class_5481> strings = class_310.method_1551().field_1772.method_1728(this.text, this.getParent().getItemWidth());
         return strings.isEmpty() ? 0 : 4 + strings.size() * 10;
      }

      @Nullable
      @Override
      public class_8016 method_48205(class_8023 focusNavigationEvent) {
         return null;
      }

      @Override
      public Object getValue() {
         return null;
      }

      @Override
      public Optional<Object> getDefaultValue() {
         return Optional.empty();
      }

      @Override
      public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
         return false;
      }

      @Override
      public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
         super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
         int yy = y + 2;

         for (class_5481 text : class_310.method_1551().field_1772.method_1728(this.text, this.getParent().getItemWidth())) {
            graphics.method_35720(
               class_310.method_1551().field_1772, text, x - 4 + entryWidth / 2 - class_310.method_1551().field_1772.method_30880(text) / 2, yy, -1
            );
            yy += 10;
         }
      }

      public List<? extends class_364> method_25396() {
         return Collections.emptyList();
      }

      @Override
      public List<? extends class_6379> narratables() {
         return Collections.emptyList();
      }
   }

   private class ConfigEntryReference implements GlobalizedClothConfigScreen.Reference {
      private final AbstractConfigEntry<?> entry;
      private final int layer;

      public ConfigEntryReference(AbstractConfigEntry<?> entry, int layer) {
         this.entry = entry;
         this.layer = layer;
      }

      @Override
      public int getIndent() {
         return this.layer;
      }

      @Override
      public class_2561 getText() {
         return this.entry.getFieldName();
      }

      @Override
      public float getScale() {
         return 1.0F;
      }

      @Override
      public void go() {
         int[] i = new int[]{0};

         for (AbstractConfigEntry<?> child : GlobalizedClothConfigScreen.this.listWidget.method_25396()) {
            int i1 = i[0];
            if (this.goChild(i, null, child)) {
               return;
            }

            i[0] = i1 + child.getItemHeight();
         }
      }

      private boolean goChild(int[] i, Integer expandedParent, AbstractConfigEntry<?> root) {
         if (root == this.entry) {
            GlobalizedClothConfigScreen.this.listWidget.scrollTo(expandedParent == null ? i[0] : expandedParent.intValue(), true);
            return true;
         } else {
            int j = i[0];
            i[0] += root.getInitialReferenceOffset();
            boolean expanded = root instanceof Expandable && ((Expandable)root).isExpanded();
            if (root instanceof Expandable) {
               ((Expandable)root).setExpanded(true);
            }

            List<? extends class_364> children = root.method_25396();
            if (root instanceof Expandable) {
               ((Expandable)root).setExpanded(expanded);
            }

            for (class_364 child : children) {
               if (child instanceof ReferenceProvider) {
                  int i1 = i[0];
                  if (this.goChild(
                     i,
                     expandedParent != null ? expandedParent : (root instanceof Expandable && !expanded ? j : null),
                     ((ReferenceProvider)child).provideReferenceEntry()
                  )) {
                     return true;
                  }

                  i[0] = i1 + ((ReferenceProvider)child).provideReferenceEntry().getItemHeight();
               }
            }

            return false;
         }
      }
   }

   private interface Reference {
      default int getIndent() {
         return 0;
      }

      class_2561 getText();

      float getScale();

      void go();
   }
}
