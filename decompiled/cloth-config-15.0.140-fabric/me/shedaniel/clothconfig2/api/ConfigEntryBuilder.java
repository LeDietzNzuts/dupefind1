package me.shedaniel.clothconfig2.api;

import java.util.List;
import java.util.function.Function;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
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
import me.shedaniel.math.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_5251;
import net.minecraft.class_3675.class_306;

@Environment(EnvType.CLIENT)
public interface ConfigEntryBuilder {
   static ConfigEntryBuilder create() {
      return ConfigEntryBuilderImpl.create();
   }

   class_2561 getResetButtonKey();

   ConfigEntryBuilder setResetButtonKey(class_2561 var1);

   IntListBuilder startIntList(class_2561 var1, List<Integer> var2);

   LongListBuilder startLongList(class_2561 var1, List<Long> var2);

   FloatListBuilder startFloatList(class_2561 var1, List<Float> var2);

   DoubleListBuilder startDoubleList(class_2561 var1, List<Double> var2);

   StringListBuilder startStrList(class_2561 var1, List<String> var2);

   SubCategoryBuilder startSubCategory(class_2561 var1);

   SubCategoryBuilder startSubCategory(class_2561 var1, List<AbstractConfigListEntry> var2);

   BooleanToggleBuilder startBooleanToggle(class_2561 var1, boolean var2);

   StringFieldBuilder startStrField(class_2561 var1, String var2);

   ColorFieldBuilder startColorField(class_2561 var1, int var2);

   default ColorFieldBuilder startColorField(class_2561 fieldNameKey, class_5251 color) {
      return this.startColorField(fieldNameKey, color.method_27716());
   }

   default ColorFieldBuilder startColorField(class_2561 fieldNameKey, Color color) {
      return this.startColorField(fieldNameKey, color.getColor() & 16777215);
   }

   default ColorFieldBuilder startAlphaColorField(class_2561 fieldNameKey, int value) {
      return this.startColorField(fieldNameKey, value).setAlphaMode(true);
   }

   default ColorFieldBuilder startAlphaColorField(class_2561 fieldNameKey, Color color) {
      return this.startColorField(fieldNameKey, color.getColor());
   }

   TextFieldBuilder startTextField(class_2561 var1, String var2);

   TextDescriptionBuilder startTextDescription(class_2561 var1);

   <T extends Enum<?>> EnumSelectorBuilder<T> startEnumSelector(class_2561 var1, Class<T> var2, T var3);

   <T> SelectorBuilder<T> startSelector(class_2561 var1, T[] var2, T var3);

   IntFieldBuilder startIntField(class_2561 var1, int var2);

   LongFieldBuilder startLongField(class_2561 var1, long var2);

   FloatFieldBuilder startFloatField(class_2561 var1, float var2);

   DoubleFieldBuilder startDoubleField(class_2561 var1, double var2);

   IntSliderBuilder startIntSlider(class_2561 var1, int var2, int var3, int var4);

   LongSliderBuilder startLongSlider(class_2561 var1, long var2, long var4, long var6);

   KeyCodeBuilder startModifierKeyCodeField(class_2561 var1, ModifierKeyCode var2);

   default KeyCodeBuilder startKeyCodeField(class_2561 fieldNameKey, class_306 value) {
      return this.startModifierKeyCodeField(fieldNameKey, ModifierKeyCode.of(value, Modifier.none())).setAllowModifiers(false);
   }

   default KeyCodeBuilder fillKeybindingField(class_2561 fieldNameKey, class_304 value) {
      return this.startKeyCodeField(fieldNameKey, value.field_1655).setDefaultValue(value.method_1429()).setKeySaveConsumer(code -> {
         value.method_1422(code);
         class_304.method_1426();
         class_310.method_1551().field_1690.method_1640();
      });
   }

   <T> DropdownMenuBuilder<T> startDropdownMenu(
      class_2561 var1, DropdownBoxEntry.SelectionTopCellElement<T> var2, DropdownBoxEntry.SelectionCellCreator<T> var3
   );

   default <T> DropdownMenuBuilder<T> startDropdownMenu(class_2561 fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement) {
      return this.startDropdownMenu(fieldNameKey, topCellElement, new DropdownBoxEntry.DefaultSelectionCellCreator<>());
   }

   default <T> DropdownMenuBuilder<T> startDropdownMenu(
      class_2561 fieldNameKey, T value, Function<String, T> toObjectFunction, DropdownBoxEntry.SelectionCellCreator<T> cellCreator
   ) {
      return this.startDropdownMenu(fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, toObjectFunction), cellCreator);
   }

   default <T> DropdownMenuBuilder<T> startDropdownMenu(
      class_2561 fieldNameKey,
      T value,
      Function<String, T> toObjectFunction,
      Function<T, class_2561> toTextFunction,
      DropdownBoxEntry.SelectionCellCreator<T> cellCreator
   ) {
      return this.startDropdownMenu(fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, toObjectFunction, toTextFunction), cellCreator);
   }

   default <T> DropdownMenuBuilder<T> startDropdownMenu(class_2561 fieldNameKey, T value, Function<String, T> toObjectFunction) {
      return this.startDropdownMenu(
         fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, toObjectFunction), new DropdownBoxEntry.DefaultSelectionCellCreator<>()
      );
   }

   default <T> DropdownMenuBuilder<T> startDropdownMenu(
      class_2561 fieldNameKey, T value, Function<String, T> toObjectFunction, Function<T, class_2561> toTextFunction
   ) {
      return this.startDropdownMenu(
         fieldNameKey,
         DropdownMenuBuilder.TopCellElementBuilder.of(value, toObjectFunction, toTextFunction),
         new DropdownBoxEntry.DefaultSelectionCellCreator<>()
      );
   }

   default DropdownMenuBuilder<String> startStringDropdownMenu(class_2561 fieldNameKey, String value, DropdownBoxEntry.SelectionCellCreator<String> cellCreator) {
      return this.startDropdownMenu(fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, s -> s, class_2561::method_43470), cellCreator);
   }

   default DropdownMenuBuilder<String> startStringDropdownMenu(
      class_2561 fieldNameKey, String value, Function<String, class_2561> toTextFunction, DropdownBoxEntry.SelectionCellCreator<String> cellCreator
   ) {
      return this.startDropdownMenu(fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, s -> s, toTextFunction), cellCreator);
   }

   default DropdownMenuBuilder<String> startStringDropdownMenu(class_2561 fieldNameKey, String value) {
      return this.startDropdownMenu(
         fieldNameKey,
         DropdownMenuBuilder.TopCellElementBuilder.of(value, s -> s, class_2561::method_43470),
         new DropdownBoxEntry.DefaultSelectionCellCreator<>()
      );
   }

   default DropdownMenuBuilder<String> startStringDropdownMenu(class_2561 fieldNameKey, String value, Function<String, class_2561> toTextFunction) {
      return this.startDropdownMenu(
         fieldNameKey, DropdownMenuBuilder.TopCellElementBuilder.of(value, s -> s, toTextFunction), new DropdownBoxEntry.DefaultSelectionCellCreator<>()
      );
   }
}
