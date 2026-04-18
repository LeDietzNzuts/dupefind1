package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class BooleanListEntry extends TooltipListEntry<Boolean> {
   private final AtomicBoolean bool;
   private final boolean original;
   private final class_4185 buttonWidget;
   private final class_4185 resetButton;
   private final Supplier<Boolean> defaultValue;
   private final List<class_339> widgets;

   @Deprecated
   @Internal
   public BooleanListEntry(class_2561 fieldName, boolean bool, class_2561 resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer) {
      this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, null);
   }

   @Deprecated
   @Internal
   public BooleanListEntry(
      class_2561 fieldName,
      boolean bool,
      class_2561 resetButtonKey,
      Supplier<Boolean> defaultValue,
      Consumer<Boolean> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public BooleanListEntry(
      class_2561 fieldName,
      boolean bool,
      class_2561 resetButtonKey,
      Supplier<Boolean> defaultValue,
      Consumer<Boolean> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.defaultValue = defaultValue;
      this.original = bool;
      this.bool = new AtomicBoolean(bool);
      this.buttonWidget = class_4185.method_46430(class_2561.method_43473(), widget -> this.bool.set(!this.bool.get()))
         .method_46434(0, 0, 150, 20)
         .method_46431();
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> this.bool.set(defaultValue.get()))
         .method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20)
         .method_46431();
      this.saveCallback = saveConsumer;
      this.widgets = Lists.newArrayList(new class_339[]{this.buttonWidget, this.resetButton});
   }

   @Override
   public boolean isEdited() {
      return super.isEdited() || this.original != this.bool.get();
   }

   public Boolean getValue() {
      return this.bool.get();
   }

   @Override
   public Optional<Boolean> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && this.defaultValue.get() != this.bool.get();
      this.resetButton.method_46419(y);
      this.buttonWidget.field_22763 = this.isEditable();
      this.buttonWidget.method_46419(y);
      this.buttonWidget.method_25355(this.getYesNoText(this.bool.get()));
      class_2561 displayedFieldName = this.getDisplayedFieldName();
      if (class_310.method_1551().field_1772.method_1726()) {
         graphics.method_35720(
            class_310.method_1551().field_1772,
            displayedFieldName.method_30937(),
            window.method_4486() - x - class_310.method_1551().field_1772.method_27525(displayedFieldName),
            y + 6,
            16777215
         );
         this.resetButton.method_46421(x);
         this.buttonWidget.method_46421(x + this.resetButton.method_25368() + 2);
      } else {
         graphics.method_35720(class_310.method_1551().field_1772, displayedFieldName.method_30937(), x, y + 6, this.getPreferredTextColor());
         this.resetButton.method_46421(x + entryWidth - this.resetButton.method_25368());
         this.buttonWidget.method_46421(x + entryWidth - 150);
      }

      this.buttonWidget.method_25358(150 - this.resetButton.method_25368() - 2);
      this.resetButton.method_25394(graphics, mouseX, mouseY, delta);
      this.buttonWidget.method_25394(graphics, mouseX, mouseY, delta);
   }

   public class_2561 getYesNoText(boolean bool) {
      return class_2561.method_43471("text.cloth-config.boolean.value." + bool);
   }

   public List<? extends class_364> method_25396() {
      return this.widgets;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.widgets;
   }
}
