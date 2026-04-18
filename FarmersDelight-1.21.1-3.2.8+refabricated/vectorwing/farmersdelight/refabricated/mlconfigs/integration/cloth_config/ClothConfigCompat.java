package vectorwing.farmersdelight.refabricated.mlconfigs.integration.cloth_config;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.ColorFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.DoubleFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.EnumSelectorBuilder;
import me.shedaniel.clothconfig2.impl.builders.FloatFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.StringFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.StringListBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.clothconfig2.impl.builders.TextDescriptionBuilder;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;
import vectorwing.farmersdelight.refabricated.mlconfigs.ModConfigHolder;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.ConfigEntry;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.ConfigSubCategory;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.FabricConfigHolder;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.BoolConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ColorConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.DoubleConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.EnumConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.FloatConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.IntConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.JsonConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ListStringConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ObjectConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.StringConfigValue;

public class ClothConfigCompat {
   @Internal
   public static class_437 makeScreen(class_437 parent, FabricConfigHolder spec) {
      return makeScreen(parent, spec, null);
   }

   @Internal
   public static class_437 makeScreen(class_437 parent, FabricConfigHolder spec, @Nullable class_2960 background) {
      spec.forceLoad();
      ConfigBuilder builder = ConfigBuilder.create();
      builder.setParentScreen(parent);
      builder.setTitle(spec.getReadableName());
      builder.setSavingRunnable(spec::saveConfig);
      if (background != null) {
         builder.setDefaultBackgroundTexture(background);
      }

      for (ConfigEntry en : spec.getMainEntry().getEntries()) {
         if (en instanceof ConfigSubCategory c) {
            ConfigCategory mainCat = builder.getOrCreateCategory(class_2561.method_43471(ModConfigHolder.getReadableName(c.getName())));

            for (ConfigEntry entry : c.getEntries()) {
               if (entry instanceof ConfigSubCategory subCat) {
                  SubCategoryBuilder subBuilder = builder.entryBuilder().startSubCategory(class_2561.method_43471(subCat.getName()));
                  addEntriesRecursive(builder, subBuilder, subCat);
                  mainCat.addEntry(subBuilder.build());
               } else {
                  mainCat.addEntry(buildEntry(builder, entry));
               }
            }
         }
      }

      return builder.build();
   }

   private static void addEntriesRecursive(ConfigBuilder builder, SubCategoryBuilder subCategoryBuilder, ConfigSubCategory c) {
      for (ConfigEntry entry : c.getEntries()) {
         if (entry instanceof ConfigSubCategory cc) {
            SubCategoryBuilder scb = builder.entryBuilder().startSubCategory(class_2561.method_43471(entry.getName()));
            addEntriesRecursive(builder, scb, cc);
            subCategoryBuilder.add(scb.build());
         } else {
            subCategoryBuilder.add(buildEntry(builder, entry));
         }
      }
   }

   private static AbstractConfigListEntry<?> buildEntry(ConfigBuilder builder, ConfigEntry entry) {
      if (entry instanceof ColorConfigValue col) {
         ColorFieldBuilder e = ((ColorFieldBuilder)builder.entryBuilder()
               .startAlphaColorField(col.getTranslation(), col.get())
               .setDefaultValue(col.getDefaultValue()))
            .setSaveConsumer(col::set);
         class_2561 description = col.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof IntConfigValue ic) {
         IntFieldBuilder e = ((IntFieldBuilder)((IntFieldBuilder)((IntFieldBuilder)builder.entryBuilder()
                     .startIntField(ic.getTranslation(), ic.get())
                     .setMax(ic.getMax()))
                  .setMin(ic.getMin()))
               .setDefaultValue(ic.getDefaultValue()))
            .setSaveConsumer(ic::set);
         class_2561 description = ic.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof DoubleConfigValue dc) {
         DoubleFieldBuilder e = ((DoubleFieldBuilder)((DoubleFieldBuilder)((DoubleFieldBuilder)builder.entryBuilder()
                     .startDoubleField(dc.getTranslation(), dc.get())
                     .setMax(dc.getMax()))
                  .setMin(dc.getMin()))
               .setDefaultValue(dc.getDefaultValue()))
            .setSaveConsumer(dc::set);
         class_2561 description = dc.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof FloatConfigValue fc) {
         FloatFieldBuilder e = ((FloatFieldBuilder)((FloatFieldBuilder)((FloatFieldBuilder)builder.entryBuilder()
                     .startFloatField(fc.getTranslation(), fc.get())
                     .setMax(fc.getMax()))
                  .setMin(fc.getMin()))
               .setDefaultValue(fc.getDefaultValue()))
            .setSaveConsumer(fc::set);
         class_2561 description = fc.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof StringConfigValue sc) {
         StringFieldBuilder e = builder.entryBuilder()
            .startStrField(sc.getTranslation(), sc.get())
            .setDefaultValue(sc.getDefaultValue())
            .setSaveConsumer(sc::set);
         class_2561 description = sc.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof BoolConfigValue bc) {
         BooleanToggleBuilder e = ((BooleanToggleBuilder)builder.entryBuilder()
               .startBooleanToggle(bc.getTranslation(), bc.get())
               .setDefaultValue(bc.getDefaultValue()))
            .setSaveConsumer(bc::set);
         class_2561 description = bc.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (entry instanceof EnumConfigValue<?> ec) {
         return addEnum(builder, ec);
      } else if (entry instanceof ListStringConfigValue<?> lc) {
         StringListBuilder e = builder.entryBuilder()
            .startStrList(lc.getTranslation(), lc.get())
            .setDefaultValue(lc.getDefaultValue())
            .setSaveConsumer(lc::set);
         class_2561 description = lc.getDescription();
         if (description != null) {
            e.setTooltip(new class_2561[]{description});
         }

         return e.build();
      } else if (!(entry instanceof JsonConfigValue) && !(entry instanceof ObjectConfigValue)) {
         throw new UnsupportedOperationException("unknown entry: " + entry.getClass().getName());
      } else {
         TextDescriptionBuilder e = builder.entryBuilder().startTextDescription(class_2561.method_43470("Unsupported entry. Edit config manually"));
         return e.build();
      }
   }

   @NotNull
   private static <T extends Enum<T>> EnumListEntry<T> addEnum(ConfigBuilder builder, EnumConfigValue<T> ec) {
      EnumSelectorBuilder<T> e = builder.entryBuilder()
         .startEnumSelector(ec.getTranslation(), ec.getEnumClass(), ec.get())
         .setDefaultValue(ec.getDefaultValue())
         .setSaveConsumer(ec::set);
      class_2561 description = ec.getDescription();
      if (description != null) {
         e.setTooltip(new class_2561[]{description});
      }

      return e.build();
   }
}
