package vectorwing.farmersdelight.refabricated.mlconfigs.integration.yacl;

import dev.isxander.yacl3.api.Binding;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.Option.Builder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.DoubleSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.LabelController;
import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.ConfigEntry;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.ConfigSubCategory;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.FabricConfigHolder;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.BoolConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ColorConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.DoubleConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.EnumConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.FloatConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.IntConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.JsonConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ListStringConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.ObjectConfigValue;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values.StringConfigValue;

public class YACLCompat {
   private static final ValueFormatter<Double> DOUBLE_FORMATTER = value -> class_2561.method_30163(String.format("%,.4f", value).replaceAll("[  ]", " "));
   private static final ValueFormatter<Float> FLOAT_FORMATTER = value -> class_2561.method_30163(String.format("%,.4f", value).replaceAll("[  ]", " "));

   public static class_437 makeScreen(class_437 parent, FabricConfigHolder spec) {
      return makeScreen(parent, spec, null);
   }

   public static class_437 makeScreen(class_437 parent, FabricConfigHolder spec, @Nullable class_2960 background) {
      spec.forceLoad();
      dev.isxander.yacl3.api.YetAnotherConfigLib.Builder builder = YetAnotherConfigLib.createBuilder();
      builder.title(spec.getReadableName());
      builder.save(spec::saveConfig);

      for (ConfigEntry en : spec.getMainEntry().getEntries()) {
         if (en instanceof ConfigSubCategory c) {
            dev.isxander.yacl3.api.ConfigCategory.Builder mainCat = ConfigCategory.createBuilder().name(class_2561.method_43471(c.getName()));

            for (ConfigEntry entry : c.getEntries()) {
               if (entry instanceof ConfigSubCategory subCat) {
                  dev.isxander.yacl3.api.OptionGroup.Builder subBuilder = OptionGroup.createBuilder()
                     .name(class_2561.method_43471(subCat.getName()))
                     .collapsed(true);
                  addEntriesRecursive(mainCat, subBuilder, subCat);
                  mainCat.group(subBuilder.build());
               } else {
                  mainCat.option(buildEntry(entry));
               }
            }

            builder.category(mainCat.build());
         }
      }

      return builder.build().generateScreen(parent);
   }

   private static void addEntriesRecursive(
      dev.isxander.yacl3.api.ConfigCategory.Builder builder, dev.isxander.yacl3.api.OptionGroup.Builder subCategoryBuilder, ConfigSubCategory c
   ) {
      for (ConfigEntry entry : c.getEntries()) {
         if (entry instanceof ConfigSubCategory cc) {
            dev.isxander.yacl3.api.OptionGroup.Builder scb = OptionGroup.createBuilder()
               .name(class_2561.method_43471(entry.getName()))
               .description(OptionDescription.of(new class_2561[]{class_2561.method_43470("Unsupported")}));
            addEntriesRecursive(builder, subCategoryBuilder, cc);
         } else {
            subCategoryBuilder.option(buildEntry(entry));
         }
      }
   }

   private static Option<?> buildEntry(ConfigEntry entry) {
      if (entry instanceof ColorConfigValue col) {
         Builder<Color> e = Option.createBuilder()
            .name(col.getTranslation())
            .binding(new Color(col.getDefaultValue()), () -> new Color(col.get()), v -> col.set(v.getRGB()))
            .controller(ColorControllerBuilder::create);
         class_2561 description = col.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof IntConfigValue ic) {
         Builder<Integer> e = Option.createBuilder()
            .name(ic.getTranslation())
            .binding(ic.getDefaultValue(), ic, ic::set)
            .controller(o -> ((IntegerSliderControllerBuilder)IntegerSliderControllerBuilder.create(o).range(ic.getMin(), ic.getMax())).step(1));
         class_2561 description = ic.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof DoubleConfigValue dc) {
         Builder<Double> e = Option.createBuilder()
            .name(dc.getTranslation())
            .binding(dc.getDefaultValue(), dc, dc::set)
            .controller(
               o -> ((DoubleSliderControllerBuilder)((DoubleSliderControllerBuilder)DoubleSliderControllerBuilder.create(o).range(dc.getMin(), dc.getMax()))
                     .step(1.0E-4))
                  .formatValue(DOUBLE_FORMATTER)
            );
         class_2561 description = dc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof FloatConfigValue fc) {
         Builder<Float> e = Option.createBuilder()
            .name(fc.getTranslation())
            .binding(fc.getDefaultValue(), fc, fc::set)
            .controller(
               o -> ((FloatSliderControllerBuilder)((FloatSliderControllerBuilder)FloatSliderControllerBuilder.create(o).range(fc.getMin(), fc.getMax()))
                     .step(1.0E-4F))
                  .formatValue(FLOAT_FORMATTER)
            );
         class_2561 description = fc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof StringConfigValue sc) {
         Builder<String> e = Option.createBuilder(String.class)
            .name(sc.getTranslation())
            .binding(sc.getDefaultValue(), sc, sc::set)
            .controller(StringControllerBuilder::create);
         class_2561 description = sc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof BoolConfigValue bc) {
         Builder<Boolean> e = Option.createBuilder()
            .name(bc.getTranslation())
            .binding(bc.getDefaultValue(), bc, bc::set)
            .controller(TickBoxControllerBuilder::create);
         class_2561 description = bc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (entry instanceof EnumConfigValue<?> ec) {
         return addEnum(ec);
      } else if (entry instanceof ListStringConfigValue<?> lc) {
         Builder<class_2561> e = Option.createBuilder()
            .name(lc.getTranslation())
            .binding(Binding.immutable(class_2561.method_43470("String Lists are not supported")))
            .customController(LabelController::new);
         class_2561 description = lc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      } else if (!(entry instanceof JsonConfigValue) && !(entry instanceof ObjectConfigValue)) {
         throw new UnsupportedOperationException("unknown entry: " + entry.getClass().getName());
      } else {
         ConfigValue lc = (ConfigValue)entry;
         Builder<class_2561> e = Option.createBuilder()
            .name(lc.getTranslation())
            .binding(Binding.immutable(class_2561.method_43470("Object fields are not supported. Edit the config manually instead")))
            .customController(LabelController::new);
         class_2561 description = lc.getDescription();
         if (description != null) {
            e.description(OptionDescription.of(new class_2561[]{description}));
         }

         return e.build();
      }
   }

   private static <T extends Enum<T>> Option<T> addEnum(EnumConfigValue<T> ec) {
      Builder<T> e = Option.createBuilder(ec.getEnumClass())
         .name(ec.getTranslation())
         .binding(ec.getDefaultValue(), ec, ec::set)
         .controller(EnumControllerBuilder::create);
      class_2561 description = ec.getDescription();
      if (description != null) {
         e.description(OptionDescription.of(new class_2561[]{description}));
      }

      return e.build();
   }
}
