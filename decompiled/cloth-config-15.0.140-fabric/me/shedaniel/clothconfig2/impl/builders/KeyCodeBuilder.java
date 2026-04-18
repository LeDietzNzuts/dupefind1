package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_3675.class_306;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class KeyCodeBuilder extends FieldBuilder<ModifierKeyCode, KeyCodeEntry, KeyCodeBuilder> {
   @Nullable
   private Consumer<ModifierKeyCode> saveConsumer = null;
   @NotNull
   private Function<ModifierKeyCode, Optional<class_2561[]>> tooltipSupplier = bool -> Optional.empty();
   private final ModifierKeyCode value;
   private boolean allowKey = true;
   private boolean allowMouse = true;
   private boolean allowModifiers = true;

   public KeyCodeBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, ModifierKeyCode value) {
      super(resetButtonKey, fieldNameKey);
      this.value = ModifierKeyCode.copyOf(value);
   }

   public KeyCodeBuilder setAllowModifiers(boolean allowModifiers) {
      this.allowModifiers = allowModifiers;
      if (!allowModifiers) {
         this.value.setModifier(Modifier.none());
      }

      return this;
   }

   public KeyCodeBuilder setAllowKey(boolean allowKey) {
      if (!this.allowMouse && !allowKey) {
         throw new IllegalArgumentException();
      } else {
         this.allowKey = allowKey;
         return this;
      }
   }

   public KeyCodeBuilder setAllowMouse(boolean allowMouse) {
      if (!this.allowKey && !allowMouse) {
         throw new IllegalArgumentException();
      } else {
         this.allowMouse = allowMouse;
         return this;
      }
   }

   public KeyCodeBuilder setErrorSupplier(@Nullable Function<class_306, Optional<class_2561>> errorSupplier) {
      return this.setModifierErrorSupplier(keyCode -> errorSupplier.apply(keyCode.getKeyCode()));
   }

   public KeyCodeBuilder setModifierErrorSupplier(@Nullable Function<ModifierKeyCode, Optional<class_2561>> errorSupplier) {
      this.errorSupplier = errorSupplier;
      return this;
   }

   public KeyCodeBuilder requireRestart() {
      this.requireRestart(true);
      return this;
   }

   public KeyCodeBuilder setKeySaveConsumer(Consumer<class_306> saveConsumer) {
      return this.setModifierSaveConsumer(keyCode -> saveConsumer.accept(keyCode.getKeyCode()));
   }

   public KeyCodeBuilder setDefaultValue(Supplier<class_306> defaultValue) {
      return this.setModifierDefaultValue(() -> ModifierKeyCode.of(defaultValue.get(), Modifier.none()));
   }

   public KeyCodeBuilder setModifierSaveConsumer(Consumer<ModifierKeyCode> saveConsumer) {
      this.saveConsumer = saveConsumer;
      return this;
   }

   public KeyCodeBuilder setModifierDefaultValue(Supplier<ModifierKeyCode> defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public KeyCodeBuilder setDefaultValue(class_306 defaultValue) {
      return this.setDefaultValue(ModifierKeyCode.of(defaultValue, Modifier.none()));
   }

   public KeyCodeBuilder setDefaultValue(ModifierKeyCode defaultValue) {
      this.defaultValue = () -> defaultValue;
      return this;
   }

   public KeyCodeBuilder setKeyTooltipSupplier(@NotNull Function<class_306, Optional<class_2561[]>> tooltipSupplier) {
      return this.setModifierTooltipSupplier(keyCode -> tooltipSupplier.apply(keyCode.getKeyCode()));
   }

   public KeyCodeBuilder setModifierTooltipSupplier(@NotNull Function<ModifierKeyCode, Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = tooltipSupplier;
      return this;
   }

   public KeyCodeBuilder setTooltipSupplier(@NotNull Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = bool -> tooltipSupplier.get();
      return this;
   }

   public KeyCodeBuilder setTooltip(Optional<class_2561[]> tooltip) {
      this.tooltipSupplier = bool -> tooltip;
      return this;
   }

   public KeyCodeBuilder setTooltip(@Nullable class_2561... tooltip) {
      this.tooltipSupplier = bool -> Optional.ofNullable(tooltip);
      return this;
   }

   @NotNull
   public KeyCodeEntry build() {
      KeyCodeEntry entry = new KeyCodeEntry(
         this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, null, this.isRequireRestart()
      );
      entry.setTooltipSupplier(() -> this.tooltipSupplier.apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      entry.setAllowKey(this.allowKey);
      entry.setAllowMouse(this.allowMouse);
      entry.setAllowModifiers(this.allowModifiers);
      return this.finishBuilding(entry);
   }
}
