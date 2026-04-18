package me.shedaniel.clothconfig2.gui.entries;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_364;
import net.minecraft.class_5481;
import net.minecraft.class_6379;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class TextListEntry extends TooltipListEntry<Object> {
   public static final int LINE_HEIGHT = 12;
   public static final int DISABLED_COLOR = Objects.requireNonNull(class_124.field_1063.method_532());
   private final class_327 textRenderer = class_310.method_1551().field_1772;
   private final int color;
   private final class_2561 text;
   private int savedWidth = -1;
   private int savedX = -1;
   private int savedY = -1;
   private List<class_5481> wrappedLines;

   @Deprecated
   @Internal
   public TextListEntry(class_2561 fieldName, class_2561 text) {
      this(fieldName, text, -1);
   }

   @Deprecated
   @Internal
   public TextListEntry(class_2561 fieldName, class_2561 text, int color) {
      this(fieldName, text, color, null);
   }

   @Deprecated
   @Internal
   public TextListEntry(class_2561 fieldName, class_2561 text, int color, Supplier<Optional<class_2561[]>> tooltipSupplier) {
      super(fieldName, tooltipSupplier);
      this.text = text;
      this.color = color;
      this.wrappedLines = Collections.emptyList();
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      if (this.savedWidth != entryWidth || this.savedX != x || this.savedY != y) {
         this.wrappedLines = this.textRenderer.method_1728(this.text, entryWidth);
         this.savedWidth = entryWidth;
         this.savedX = x;
         this.savedY = y;
      }

      int yy = y + 7;
      int textColor = this.isEnabled() ? this.color : DISABLED_COLOR;

      for (class_5481 string : this.wrappedLines) {
         graphics.method_35720(class_310.method_1551().field_1772, string, x, yy, textColor);
         yy += 9 + 3;
      }

      class_2583 style = this.getTextAt(mouseX, mouseY);
      AbstractConfigScreen configScreen = this.getConfigScreen();
      if (style != null && configScreen != null) {
         graphics.method_51441(class_310.method_1551().field_1772, style, mouseX, mouseY);
      }
   }

   @Override
   public int getItemHeight() {
      if (this.savedWidth == -1) {
         return 12;
      } else {
         int lineCount = this.wrappedLines.size();
         return lineCount == 0 ? 0 : 14 + lineCount * 12;
      }
   }

   @Override
   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (button == 0) {
         class_2583 style = this.getTextAt(mouseX, mouseY);
         AbstractConfigScreen configScreen = this.getConfigScreen();
         if (configScreen != null && configScreen.method_25430(style)) {
            return true;
         }
      }

      return super.method_25402(mouseX, mouseY, button);
   }

   @Nullable
   private class_2583 getTextAt(double x, double y) {
      int lineCount = this.wrappedLines.size();
      if (lineCount > 0) {
         int textX = class_3532.method_15357(x - this.savedX);
         int textY = class_3532.method_15357(y - 7.0 - this.savedY);
         if (textX >= 0 && textY >= 0 && textX <= this.savedWidth && textY < 12 * lineCount + lineCount) {
            int line = textY / 12;
            if (line < this.wrappedLines.size()) {
               class_5481 orderedText = this.wrappedLines.get(line);
               return this.textRenderer.method_27527().method_30876(orderedText, textX);
            }
         }
      }

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

   public List<? extends class_364> method_25396() {
      return Collections.emptyList();
   }

   @Override
   public List<? extends class_6379> narratables() {
      return Collections.emptyList();
   }
}
