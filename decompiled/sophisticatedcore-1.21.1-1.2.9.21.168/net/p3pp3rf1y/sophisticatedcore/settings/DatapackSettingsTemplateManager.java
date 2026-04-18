package net.p3pp3rf1y.sophisticatedcore.settings;

import com.google.common.collect.Maps;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_2487;
import net.minecraft.class_2522;
import net.minecraft.class_2960;
import net.minecraft.class_3300;
import net.minecraft.class_3695;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.util.SimpleIdentifiablePrepareableReloadListener;
import org.apache.commons.io.IOUtils;

public class DatapackSettingsTemplateManager {
   private static final Map<String, Map<String, class_2487>> TEMPLATES = Maps.newHashMap();

   private DatapackSettingsTemplateManager() {
   }

   public static void putTemplate(String datapackName, String templateName, class_2487 tag) {
      templateName = templateName.replace('_', ' ');
      templateName = capitalizeFirstLetterOfEachWord(templateName);
      TEMPLATES.computeIfAbsent(datapackName, n -> Maps.newTreeMap()).put(templateName, tag);
   }

   private static String capitalizeFirstLetterOfEachWord(String input) {
      String[] words = input.split("\\s+");
      StringBuilder builder = new StringBuilder();

      for (String word : words) {
         if (!word.isEmpty()) {
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            builder.append(capitalizedWord).append(" ");
         }
      }

      return builder.toString().trim();
   }

   public static Map<String, Map<String, class_2487>> getTemplates() {
      return TEMPLATES;
   }

   public static Optional<class_2487> getTemplateNbt(String datapackName, String templateName) {
      Map<String, class_2487> datapackTemplates = TEMPLATES.get(datapackName);
      return datapackTemplates == null ? Optional.empty() : Optional.ofNullable(datapackTemplates.get(templateName));
   }

   public static class Loader extends SimpleIdentifiablePrepareableReloadListener<Map<class_2960, class_2487>> {
      public static final DatapackSettingsTemplateManager.Loader INSTANCE = new DatapackSettingsTemplateManager.Loader();
      private static final String DIRECTORY = "sophisticated_settingstemplates";
      private static final String SUFFIX = ".snbt";
      private static final int PATH_SUFFIX_LENGTH = ".snbt".length();

      private Loader() {
         super(SophisticatedCore.getRL("datapack_settings_template_manager"));
      }

      protected Map<class_2960, class_2487> prepare(class_3300 resourceManager, class_3695 profiler) {
         Map<class_2960, class_2487> map = Maps.newHashMap();
         int i = "sophisticated_settingstemplates".length() + 1;
         resourceManager.method_14488("sophisticated_settingstemplates", fileName -> fileName.method_12832().endsWith(".snbt"))
            .forEach(
               (resourcelocation, resource) -> {
                  String s = resourcelocation.method_12832();
                  class_2960 resourceLocationWithoutSuffix = class_2960.method_60655(
                     resourcelocation.method_12836(), s.substring(i, s.length() - PATH_SUFFIX_LENGTH)
                  );

                  try (
                     InputStream inputstream = resource.method_14482();
                     Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
                  ) {
                     String fileContents = IOUtils.toString(reader);
                     class_2487 tag = class_2522.method_10718(fileContents);
                     if (map.put(resourceLocationWithoutSuffix, tag) != null) {
                        throw new IllegalStateException("Duplicate data file ignored with ID " + resourceLocationWithoutSuffix);
                     }
                  } catch (IOException | CommandSyntaxException | IllegalArgumentException var14) {
                     SophisticatedCore.LOGGER
                        .error("Couldn't parse data file {} from {}", new Object[]{resourceLocationWithoutSuffix, resourcelocation, var14});
                  }
               }
            );
         return map;
      }

      protected void apply(Map<class_2960, class_2487> templates, class_3300 resourceManager, class_3695 profiler) {
         templates.forEach((resourceLocation, tag) -> {
            String datapackName = resourceLocation.method_12836();
            String templateName = resourceLocation.method_12832().substring(resourceLocation.method_12832().lastIndexOf(47) + 1);
            DatapackSettingsTemplateManager.putTemplate(datapackName, templateName, tag);
         });
      }
   }
}
