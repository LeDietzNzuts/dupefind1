package me.shedaniel.autoconfig.gui;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;

@Environment(EnvType.CLIENT)
public class ConfigScreenProvider<T extends ConfigData> implements Supplier<class_437> {
   private static final class_2960 TRANSPARENT_BACKGROUND = class_2960.method_60654("cloth-config2:transparent");
   private final ConfigManager<T> manager;
   private final GuiRegistryAccess registry;
   private final class_437 parent;
   private Function<ConfigManager<T>, String> i18nFunction = managerx -> String.format("text.autoconfig.%s", managerx.getDefinition().name());
   private Function<ConfigBuilder, class_437> buildFunction = ConfigBuilder::build;
   private BiFunction<String, Field, String> optionFunction = (baseI13n, field) -> String.format("%s.option.%s", baseI13n, field.getName());
   private BiFunction<String, String, String> categoryFunction = (baseI13n, categoryName) -> String.format("%s.category.%s", baseI13n, categoryName);

   public ConfigScreenProvider(ConfigManager<T> manager, GuiRegistryAccess registry, class_437 parent) {
      this.manager = manager;
      this.registry = registry;
      this.parent = parent;
   }

   @Deprecated
   public void setI13nFunction(Function<ConfigManager<T>, String> i18nFunction) {
      this.i18nFunction = i18nFunction;
   }

   @Deprecated
   public void setBuildFunction(Function<ConfigBuilder, class_437> buildFunction) {
      this.buildFunction = buildFunction;
   }

   @Deprecated
   public void setCategoryFunction(BiFunction<String, String, String> categoryFunction) {
      this.categoryFunction = categoryFunction;
   }

   @Deprecated
   public void setOptionFunction(BiFunction<String, Field, String> optionFunction) {
      this.optionFunction = optionFunction;
   }

   public class_437 get() {
      T config = this.manager.getConfig();
      T defaults = this.manager.getSerializer().createDefault();
      String i18n = this.i18nFunction.apply(this.manager);
      ConfigBuilder builder = ConfigBuilder.create()
         .setParentScreen(this.parent)
         .setTitle(class_2561.method_43471(String.format("%s.title", i18n)))
         .setSavingRunnable(this.manager::save);
      Class<T> configClass = this.manager.getConfigClass();
      if (configClass.isAnnotationPresent(Config.Gui.Background.class)) {
         String bg = configClass.getAnnotation(Config.Gui.Background.class).value();
         class_2960 bgId = class_2960.method_12829(bg);
         if (TRANSPARENT_BACKGROUND.equals(bgId)) {
            builder.transparentBackground().setDefaultBackgroundTexture(null);
         } else {
            builder.solidBackground().setDefaultBackgroundTexture(bgId);
         }
      }

      Map<String, class_2960> categoryBackgrounds = Arrays.stream(configClass.getAnnotationsByType(Config.Gui.CategoryBackground.class))
         .collect(Collectors.toMap(Config.Gui.CategoryBackground::category, ann -> class_2960.method_60654(ann.background())));
      Arrays.stream(configClass.getDeclaredFields())
         .collect(
            Collectors.groupingBy(field -> this.getOrCreateCategoryForField(field, builder, categoryBackgrounds, i18n), LinkedHashMap::new, Collectors.toList())
         )
         .forEach((key, value) -> value.forEach(field -> {
            String optionI13n = this.optionFunction.apply(i18n, field);
            this.registry.getAndTransform(optionI13n, field, config, defaults, this.registry).forEach(key::addEntry);
         }));
      return this.buildFunction.apply(builder);
   }

   private ConfigCategory getOrCreateCategoryForField(Field field, ConfigBuilder screenBuilder, Map<String, class_2960> backgroundMap, String baseI13n) {
      String categoryName = "default";
      if (field.isAnnotationPresent(ConfigEntry.Category.class)) {
         categoryName = field.getAnnotation(ConfigEntry.Category.class).value();
      }

      class_2561 categoryKey = class_2561.method_43471(this.categoryFunction.apply(baseI13n, categoryName));
      if (!screenBuilder.hasCategory(categoryKey)) {
         ConfigCategory category = screenBuilder.getOrCreateCategory(categoryKey);
         if (backgroundMap.containsKey(categoryName)) {
            category.setCategoryBackground(backgroundMap.get(categoryName));
         }

         return category;
      } else {
         return screenBuilder.getOrCreateCategory(categoryKey);
      }
   }
}
