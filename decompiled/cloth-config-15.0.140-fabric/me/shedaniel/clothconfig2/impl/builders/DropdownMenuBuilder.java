package me.shedaniel.clothconfig2.impl.builders;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_7923;
import net.minecraft.class_918;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DropdownMenuBuilder<T> extends FieldBuilder<T, DropdownBoxEntry<T>, DropdownMenuBuilder<T>> {
   protected DropdownBoxEntry.SelectionTopCellElement<T> topCellElement;
   protected DropdownBoxEntry.SelectionCellCreator<T> cellCreator;
   protected Function<T, Optional<class_2561[]>> tooltipSupplier = str -> Optional.empty();
   protected Consumer<T> saveConsumer = null;
   protected Iterable<T> selections = Collections.emptyList();
   protected boolean suggestionMode = true;

   public DropdownMenuBuilder(
      class_2561 resetButtonKey,
      class_2561 fieldNameKey,
      DropdownBoxEntry.SelectionTopCellElement<T> topCellElement,
      DropdownBoxEntry.SelectionCellCreator<T> cellCreator
   ) {
      super(resetButtonKey, fieldNameKey);
      this.topCellElement = Objects.requireNonNull(topCellElement);
      this.cellCreator = Objects.requireNonNull(cellCreator);
   }

   public DropdownMenuBuilder<T> setSelections(Iterable<T> selections) {
      this.selections = selections;
      return this;
   }

   public DropdownMenuBuilder<T> setDefaultValue(Supplier<T> defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public DropdownMenuBuilder<T> setDefaultValue(T defaultValue) {
      this.defaultValue = () -> Objects.requireNonNull(defaultValue);
      return this;
   }

   public DropdownMenuBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
      this.saveConsumer = saveConsumer;
      return this;
   }

   public DropdownMenuBuilder<T> setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = str -> tooltipSupplier.get();
      return this;
   }

   public DropdownMenuBuilder<T> setTooltipSupplier(Function<T, Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = tooltipSupplier;
      return this;
   }

   public DropdownMenuBuilder<T> setTooltip(Optional<class_2561[]> tooltip) {
      this.tooltipSupplier = str -> tooltip;
      return this;
   }

   public DropdownMenuBuilder<T> setTooltip(class_2561... tooltip) {
      this.tooltipSupplier = str -> Optional.ofNullable(tooltip);
      return this;
   }

   public DropdownMenuBuilder<T> requireRestart() {
      this.requireRestart(true);
      return this;
   }

   public DropdownMenuBuilder<T> setErrorSupplier(Function<T, Optional<class_2561>> errorSupplier) {
      this.errorSupplier = errorSupplier;
      return this;
   }

   public DropdownMenuBuilder<T> setSuggestionMode(boolean suggestionMode) {
      this.suggestionMode = suggestionMode;
      return this;
   }

   public boolean isSuggestionMode() {
      return this.suggestionMode;
   }

   @NotNull
   public DropdownBoxEntry<T> build() {
      DropdownBoxEntry<T> entry = new DropdownBoxEntry<>(
         this.getFieldNameKey(),
         this.getResetButtonKey(),
         null,
         this.isRequireRestart(),
         this.defaultValue,
         this.saveConsumer,
         this.selections,
         this.topCellElement,
         this.cellCreator
      );
      entry.setTooltipSupplier(() -> this.tooltipSupplier.apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      entry.setSuggestionMode(this.suggestionMode);
      return this.finishBuilding(entry);
   }

   public static class CellCreatorBuilder {
      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of() {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<>();
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<>(toTextFunction);
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(int cellWidth) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
            @Override
            public int getCellWidth() {
               return cellWidth;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(int cellWidth, Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toTextFunction) {
            @Override
            public int getCellWidth() {
               return cellWidth;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(int maxItems, Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toTextFunction) {
            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellWidth, int maxItems, Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toTextFunction) {
            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems, Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toTextFunction) {
            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofItemIdentifier() {
         return ofItemIdentifier(20, 146, 7);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofItemIdentifier(int maxItems) {
         return ofItemIdentifier(20, 146, maxItems);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofItemIdentifier(int cellHeight, int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<class_2960>() {
            public DropdownBoxEntry.SelectionCellElement<class_2960> create(class_2960 selection) {
               final class_1799 s = new class_1799((class_1935)class_7923.field_41178.method_10223(selection));
               return new DropdownBoxEntry.DefaultSelectionCellElement<class_2960>(selection, this.toTextFunction) {
                  @Override
                  public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                     this.rendering = true;
                     this.x = x;
                     this.y = y;
                     this.width = width;
                     this.height = height;
                     boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                     if (b) {
                        graphics.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                     }

                     graphics.method_35720(
                        class_310.method_1551().field_1772, this.toTextFunction.apply(this.r).method_30937(), x + 6 + 18, y + 6, b ? 16777215 : 8947848
                     );
                     class_918 itemRenderer = class_310.method_1551().method_1480();
                     graphics.method_51427(s, x + 4, y + 2);
                  }
               };
            }

            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofBlockIdentifier() {
         return ofBlockIdentifier(20, 146, 7);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofBlockIdentifier(int maxItems) {
         return ofBlockIdentifier(20, 146, maxItems);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2960> ofBlockIdentifier(int cellHeight, int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<class_2960>() {
            public DropdownBoxEntry.SelectionCellElement<class_2960> create(class_2960 selection) {
               final class_1799 s = new class_1799((class_1935)class_7923.field_41175.method_10223(selection));
               return new DropdownBoxEntry.DefaultSelectionCellElement<class_2960>(selection, this.toTextFunction) {
                  @Override
                  public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                     this.rendering = true;
                     this.x = x;
                     this.y = y;
                     this.width = width;
                     this.height = height;
                     boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                     if (b) {
                        graphics.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                     }

                     graphics.method_35720(
                        class_310.method_1551().field_1772, this.toTextFunction.apply(this.r).method_30937(), x + 6 + 18, y + 6, b ? 16777215 : 8947848
                     );
                     class_918 itemRenderer = class_310.method_1551().method_1480();
                     graphics.method_51427(s, x + 4, y + 2);
                  }
               };
            }

            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_1792> ofItemObject() {
         return ofItemObject(20, 146, 7);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_1792> ofItemObject(int maxItems) {
         return ofItemObject(20, 146, maxItems);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_1792> ofItemObject(int cellHeight, int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<class_1792>(i -> class_2561.method_43470(class_7923.field_41178.method_10221(i).toString())) {
            public DropdownBoxEntry.SelectionCellElement<class_1792> create(class_1792 selection) {
               final class_1799 s = new class_1799(selection);
               return new DropdownBoxEntry.DefaultSelectionCellElement<class_1792>(selection, this.toTextFunction) {
                  @Override
                  public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                     this.rendering = true;
                     this.x = x;
                     this.y = y;
                     this.width = width;
                     this.height = height;
                     boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                     if (b) {
                        graphics.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                     }

                     graphics.method_35720(
                        class_310.method_1551().field_1772, this.toTextFunction.apply(this.r).method_30937(), x + 6 + 18, y + 6, b ? 16777215 : 8947848
                     );
                     class_918 itemRenderer = class_310.method_1551().method_1480();
                     graphics.method_51427(s, x + 4, y + 2);
                  }
               };
            }

            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2248> ofBlockObject() {
         return ofBlockObject(20, 146, 7);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2248> ofBlockObject(int maxItems) {
         return ofBlockObject(20, 146, maxItems);
      }

      public static DropdownBoxEntry.SelectionCellCreator<class_2248> ofBlockObject(int cellHeight, int cellWidth, int maxItems) {
         return new DropdownBoxEntry.DefaultSelectionCellCreator<class_2248>(i -> class_2561.method_43470(class_7923.field_41175.method_10221(i).toString())) {
            public DropdownBoxEntry.SelectionCellElement<class_2248> create(class_2248 selection) {
               final class_1799 s = new class_1799(selection);
               return new DropdownBoxEntry.DefaultSelectionCellElement<class_2248>(selection, this.toTextFunction) {
                  @Override
                  public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                     this.rendering = true;
                     this.x = x;
                     this.y = y;
                     this.width = width;
                     this.height = height;
                     boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                     if (b) {
                        graphics.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                     }

                     graphics.method_35720(
                        class_310.method_1551().field_1772, this.toTextFunction.apply(this.r).method_30937(), x + 6 + 18, y + 6, b ? 16777215 : 8947848
                     );
                     class_918 itemRenderer = class_310.method_1551().method_1480();
                     graphics.method_51427(s, x + 4, y + 2);
                  }
               };
            }

            @Override
            public int getCellHeight() {
               return cellHeight;
            }

            @Override
            public int getCellWidth() {
               return cellWidth;
            }

            @Override
            public int getDropBoxMaxHeight() {
               return this.getCellHeight() * maxItems;
            }
         };
      }
   }

   public static class TopCellElementBuilder {
      public static final Function<String, class_2960> IDENTIFIER_FUNCTION = str -> {
         try {
            return class_2960.method_60654(str);
         } catch (NumberFormatException var2) {
            return null;
         }
      };
      public static final Function<String, class_2960> ITEM_IDENTIFIER_FUNCTION = str -> {
         try {
            class_2960 identifier = class_2960.method_60654(str);
            if (class_7923.field_41178.method_17966(identifier).isPresent()) {
               return identifier;
            }
         } catch (Exception var2) {
         }

         return null;
      };
      public static final Function<String, class_2960> BLOCK_IDENTIFIER_FUNCTION = str -> {
         try {
            class_2960 identifier = class_2960.method_60654(str);
            if (class_7923.field_41175.method_17966(identifier).isPresent()) {
               return identifier;
            }
         } catch (Exception var2) {
         }

         return null;
      };
      public static final Function<String, class_1792> ITEM_FUNCTION = str -> {
         try {
            return (class_1792)class_7923.field_41178.method_17966(class_2960.method_60654(str)).orElse(null);
         } catch (Exception var2) {
            return null;
         }
      };
      public static final Function<String, class_2248> BLOCK_FUNCTION = str -> {
         try {
            return (class_2248)class_7923.field_41175.method_17966(class_2960.method_60654(str)).orElse(null);
         } catch (Exception var2) {
            return null;
         }
      };
      private static final class_1799 BARRIER = new class_1799(class_1802.field_8077);

      public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction) {
         return of(value, toObjectFunction, t -> class_2561.method_43470(t.toString()));
      }

      public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction, Function<T, class_2561> toTextFunction) {
         return new DropdownBoxEntry.DefaultSelectionTopCellElement<>(value, toObjectFunction, toTextFunction);
      }

      public static DropdownBoxEntry.SelectionTopCellElement<class_2960> ofItemIdentifier(class_1792 item) {
         return new DropdownBoxEntry.DefaultSelectionTopCellElement<class_2960>(
            class_7923.field_41178.method_10221(item), ITEM_IDENTIFIER_FUNCTION, identifier -> class_2561.method_43470(identifier.toString())
         ) {
            @Override
            public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
               this.textFieldWidget.method_46421(x + 4);
               this.textFieldWidget.method_46419(y + 6);
               this.textFieldWidget.method_25358(width - 4 - 20);
               this.textFieldWidget.method_1888(this.getParent().isEditable());
               this.textFieldWidget.method_1868(this.getPreferredTextColor());
               this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
               class_918 itemRenderer = class_310.method_1551().method_1480();
               class_1799 stack = this.hasConfigError()
                  ? DropdownMenuBuilder.TopCellElementBuilder.BARRIER
                  : new class_1799((class_1935)class_7923.field_41178.method_10223(this.getValue()));
               graphics.method_51427(stack, x + width - 18, y + 2);
            }
         };
      }

      public static DropdownBoxEntry.SelectionTopCellElement<class_2960> ofBlockIdentifier(class_2248 block) {
         return new DropdownBoxEntry.DefaultSelectionTopCellElement<class_2960>(
            class_7923.field_41175.method_10221(block), BLOCK_IDENTIFIER_FUNCTION, identifier -> class_2561.method_43470(identifier.toString())
         ) {
            @Override
            public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
               this.textFieldWidget.method_46421(x + 4);
               this.textFieldWidget.method_46419(y + 6);
               this.textFieldWidget.method_25358(width - 4 - 20);
               this.textFieldWidget.method_1888(this.getParent().isEditable());
               this.textFieldWidget.method_1868(this.getPreferredTextColor());
               this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
               class_918 itemRenderer = class_310.method_1551().method_1480();
               class_1799 stack = this.hasConfigError()
                  ? DropdownMenuBuilder.TopCellElementBuilder.BARRIER
                  : new class_1799((class_1935)class_7923.field_41175.method_10223(this.getValue()));
               graphics.method_51427(stack, x + width - 18, y + 2);
            }
         };
      }

      public static DropdownBoxEntry.SelectionTopCellElement<class_1792> ofItemObject(class_1792 item) {
         return new DropdownBoxEntry.DefaultSelectionTopCellElement<class_1792>(
            item, ITEM_FUNCTION, i -> class_2561.method_43470(class_7923.field_41178.method_10221(i).toString())
         ) {
            @Override
            public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
               this.textFieldWidget.method_46421(x + 4);
               this.textFieldWidget.method_46419(y + 6);
               this.textFieldWidget.method_25358(width - 4 - 20);
               this.textFieldWidget.method_1888(this.getParent().isEditable());
               this.textFieldWidget.method_1868(this.getPreferredTextColor());
               this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
               class_918 itemRenderer = class_310.method_1551().method_1480();
               class_1799 stack = this.hasConfigError() ? DropdownMenuBuilder.TopCellElementBuilder.BARRIER : new class_1799((class_1935)this.getValue());
               graphics.method_51427(stack, x + width - 18, y + 2);
            }
         };
      }

      public static DropdownBoxEntry.SelectionTopCellElement<class_2248> ofBlockObject(class_2248 block) {
         return new DropdownBoxEntry.DefaultSelectionTopCellElement<class_2248>(
            block, BLOCK_FUNCTION, i -> class_2561.method_43470(class_7923.field_41175.method_10221(i).toString())
         ) {
            @Override
            public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
               this.textFieldWidget.method_46421(x + 4);
               this.textFieldWidget.method_46419(y + 6);
               this.textFieldWidget.method_25358(width - 4 - 20);
               this.textFieldWidget.method_1888(this.getParent().isEditable());
               this.textFieldWidget.method_1868(this.getPreferredTextColor());
               this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
               class_918 itemRenderer = class_310.method_1551().method_1480();
               class_1799 stack = this.hasConfigError() ? DropdownMenuBuilder.TopCellElementBuilder.BARRIER : new class_1799((class_1935)this.getValue());
               graphics.method_51427(stack, x + width - 18, y + 2);
            }
         };
      }
   }
}
