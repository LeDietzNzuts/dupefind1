package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class KeyCodeEntry extends TooltipListEntry<ModifierKeyCode> {
   private ModifierKeyCode value;
   private final ModifierKeyCode original;
   private final class_4185 buttonWidget;
   private final class_4185 resetButton;
   private final Supplier<ModifierKeyCode> defaultValue;
   private final List<class_339> widgets;
   private boolean allowMouse = true;
   private boolean allowKey = true;
   private boolean allowModifiers = true;

   @Deprecated
   @Internal
   public KeyCodeEntry(
      class_2561 fieldName,
      ModifierKeyCode value,
      class_2561 resetButtonKey,
      Supplier<ModifierKeyCode> defaultValue,
      Consumer<ModifierKeyCode> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.defaultValue = defaultValue;
      this.value = value.copy();
      this.original = value.copy();
      this.buttonWidget = class_4185.method_46430(class_2561.method_43473(), widget -> this.getConfigScreen().setFocusedBinding(this))
         .method_46434(0, 0, 150, 20)
         .method_46431();
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> {
         this.value = this.getDefaultValue().orElse(null).copy();
         this.getConfigScreen().setFocusedBinding(null);
      }).method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20).method_46431();
      this.saveCallback = saveConsumer;
      this.widgets = Lists.newArrayList(new class_339[]{this.buttonWidget, this.resetButton});
   }

   @Override
   public boolean isEdited() {
      return super.isEdited() || !this.original.equals(this.getValue());
   }

   public boolean isAllowModifiers() {
      return this.allowModifiers;
   }

   public void setAllowModifiers(boolean allowModifiers) {
      this.allowModifiers = allowModifiers;
   }

   public boolean isAllowKey() {
      return this.allowKey;
   }

   public void setAllowKey(boolean allowKey) {
      this.allowKey = allowKey;
   }

   public boolean isAllowMouse() {
      return this.allowMouse;
   }

   public void setAllowMouse(boolean allowMouse) {
      this.allowMouse = allowMouse;
   }

   public ModifierKeyCode getValue() {
      return this.value;
   }

   public void setValue(ModifierKeyCode value) {
      this.value = value;
   }

   @Override
   public Optional<ModifierKeyCode> getDefaultValue() {
      return Optional.ofNullable(this.defaultValue).map(Supplier::get).map(ModifierKeyCode::copy);
   }

   private class_2561 getLocalizedName() {
      return this.value.getLocalizedName();
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && !this.getDefaultValue().get().equals(this.getValue());
      this.resetButton.method_46419(y);
      this.buttonWidget.field_22763 = this.isEditable();
      this.buttonWidget.method_46419(y);
      this.buttonWidget.method_25355(this.getLocalizedName());
      if (this.getConfigScreen().getFocusedBinding() == this) {
         this.buttonWidget
            .method_25355(
               class_2561.method_43470("> ")
                  .method_27692(class_124.field_1068)
                  .method_10852(this.buttonWidget.method_25369().method_27662().method_27692(class_124.field_1054))
                  .method_10852(class_2561.method_43470(" <").method_27692(class_124.field_1068))
            );
      }

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

   public List<? extends class_364> method_25396() {
      return this.widgets;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.widgets;
   }
}
