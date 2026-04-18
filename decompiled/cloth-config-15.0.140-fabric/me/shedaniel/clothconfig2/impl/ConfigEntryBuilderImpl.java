package me.shedaniel.clothconfig2.impl;

import java.util.List;
import java.util.UUID;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.ColorFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.DoubleFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.DoubleListBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import me.shedaniel.clothconfig2.impl.builders.EnumSelectorBuilder;
import me.shedaniel.clothconfig2.impl.builders.FloatFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.FloatListBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntListBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntSliderBuilder;
import me.shedaniel.clothconfig2.impl.builders.KeyCodeBuilder;
import me.shedaniel.clothconfig2.impl.builders.LongFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.LongListBuilder;
import me.shedaniel.clothconfig2.impl.builders.LongSliderBuilder;
import me.shedaniel.clothconfig2.impl.builders.SelectorBuilder;
import me.shedaniel.clothconfig2.impl.builders.StringFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.StringListBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.clothconfig2.impl.builders.TextDescriptionBuilder;
import me.shedaniel.clothconfig2.impl.builders.TextFieldBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;

@Environment(EnvType.CLIENT)
public class ConfigEntryBuilderImpl implements ConfigEntryBuilder {
   private class_2561 resetButtonKey = class_2561.method_43471("text.cloth-config.reset_value");

   private ConfigEntryBuilderImpl() {
   }

   public static ConfigEntryBuilderImpl create() {
      return new ConfigEntryBuilderImpl();
   }

   public static ConfigEntryBuilderImpl createImmutable() {
      return new ConfigEntryBuilderImpl() {
         @Override
         public ConfigEntryBuilder setResetButtonKey(class_2561 resetButtonKey) {
            throw new UnsupportedOperationException("This is an immutable entry builder!");
         }
      };
   }

   @Override
   public class_2561 getResetButtonKey() {
      return this.resetButtonKey;
   }

   @Override
   public ConfigEntryBuilder setResetButtonKey(class_2561 resetButtonKey) {
      this.resetButtonKey = resetButtonKey;
      return this;
   }

   @Override
   public IntListBuilder startIntList(class_2561 fieldNameKey, List<Integer> value) {
      return new IntListBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public LongListBuilder startLongList(class_2561 fieldNameKey, List<Long> value) {
      return new LongListBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public FloatListBuilder startFloatList(class_2561 fieldNameKey, List<Float> value) {
      return new FloatListBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public DoubleListBuilder startDoubleList(class_2561 fieldNameKey, List<Double> value) {
      return new DoubleListBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public StringListBuilder startStrList(class_2561 fieldNameKey, List<String> value) {
      return new StringListBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public SubCategoryBuilder startSubCategory(class_2561 fieldNameKey) {
      return new SubCategoryBuilder(this.resetButtonKey, fieldNameKey);
   }

   @Override
   public SubCategoryBuilder startSubCategory(class_2561 fieldNameKey, List<AbstractConfigListEntry> entries) {
      SubCategoryBuilder builder = new SubCategoryBuilder(this.resetButtonKey, fieldNameKey);
      builder.addAll(entries);
      return builder;
   }

   @Override
   public BooleanToggleBuilder startBooleanToggle(class_2561 fieldNameKey, boolean value) {
      return new BooleanToggleBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public StringFieldBuilder startStrField(class_2561 fieldNameKey, String value) {
      return new StringFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public ColorFieldBuilder startColorField(class_2561 fieldNameKey, int value) {
      return new ColorFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public TextFieldBuilder startTextField(class_2561 fieldNameKey, String value) {
      return new TextFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public TextDescriptionBuilder startTextDescription(class_2561 value) {
      return new TextDescriptionBuilder(this.resetButtonKey, class_2561.method_43470(UUID.randomUUID().toString()), value);
   }

   @Override
   public <T extends Enum<?>> EnumSelectorBuilder<T> startEnumSelector(class_2561 fieldNameKey, Class<T> clazz, T value) {
      return new EnumSelectorBuilder<>(this.resetButtonKey, fieldNameKey, clazz, value);
   }

   @Override
   public <T> SelectorBuilder<T> startSelector(class_2561 fieldNameKey, T[] valuesArray, T value) {
      return new SelectorBuilder<>(this.resetButtonKey, fieldNameKey, valuesArray, value);
   }

   @Override
   public IntFieldBuilder startIntField(class_2561 fieldNameKey, int value) {
      return new IntFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public LongFieldBuilder startLongField(class_2561 fieldNameKey, long value) {
      return new LongFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public FloatFieldBuilder startFloatField(class_2561 fieldNameKey, float value) {
      return new FloatFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public DoubleFieldBuilder startDoubleField(class_2561 fieldNameKey, double value) {
      return new DoubleFieldBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public IntSliderBuilder startIntSlider(class_2561 fieldNameKey, int value, int min, int max) {
      return new IntSliderBuilder(this.resetButtonKey, fieldNameKey, value, min, max);
   }

   @Override
   public LongSliderBuilder startLongSlider(class_2561 fieldNameKey, long value, long min, long max) {
      return new LongSliderBuilder(this.resetButtonKey, fieldNameKey, value, min, max);
   }

   @Override
   public KeyCodeBuilder startModifierKeyCodeField(class_2561 fieldNameKey, ModifierKeyCode value) {
      return new KeyCodeBuilder(this.resetButtonKey, fieldNameKey, value);
   }

   @Override
   public <T> DropdownMenuBuilder<T> startDropdownMenu(
      class_2561 fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement, DropdownBoxEntry.SelectionCellCreator<T> cellCreator
   ) {
      return new DropdownMenuBuilder<>(this.resetButtonKey, fieldNameKey, topCellElement, cellCreator);
   }
}
