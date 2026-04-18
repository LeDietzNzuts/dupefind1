package net.p3pp3rf1y.sophisticatedbackpacks.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2960;
import net.minecraft.class_3300;
import net.minecraft.class_3518;
import net.minecraft.class_3695;
import net.minecraft.class_4309;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.tool.SwordRegistry;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.tool.ToolRegistry;

public class RegistryLoader extends class_4309 implements IdentifiableResourceReloadListener {
   private static final Map<String, IRegistryDataLoader> loaders = new HashMap<>();
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
   private final Map<class_2960, String> loadedRegistries = new HashMap<>();
   private final List<RegistryLoader.DependentFile> loadLater = new ArrayList<>();

   public static void registerParser(IRegistryDataLoader parser) {
      loaders.put(parser.getName(), parser);
   }

   public RegistryLoader() {
      super(GSON, "registry");
   }

   public class_2960 getFabricId() {
      return SophisticatedBackpacks.getRL("registry_loader");
   }

   protected void apply(Map<class_2960, JsonElement> registries, class_3300 resourceManagerIn, class_3695 profilerIn) {
      loaders.values().forEach(IRegistryDataLoader::clear);
      registries.forEach(this::loadRegistry);
      this.loadDependents(registries);
   }

   private void loadDependents(Map<class_2960, JsonElement> registries) {
      for (int lastCountLoadLater = this.loadLater.size(); !this.loadLater.isEmpty(); lastCountLoadLater = this.loadLater.size()) {
         Iterator<RegistryLoader.DependentFile> iterator = this.loadLater.iterator();

         while (iterator.hasNext()) {
            RegistryLoader.DependentFile dependentFile = iterator.next();
            if (this.areDependenciesLoaded(dependentFile.getDependencies())) {
               this.loadRegistry(dependentFile.getName(), registries.get(dependentFile.getName()));
               iterator.remove();
            }
         }

         if (lastCountLoadLater <= this.loadLater.size()) {
            this.logIncorrectDependencies();
            break;
         }
      }
   }

   private void logIncorrectDependencies() {
      for (RegistryLoader.DependentFile dependentFile : this.loadLater) {
         SophisticatedBackpacks.LOGGER
            .error("Non existent or circular load after dependencies in {} - {}", dependentFile.getName(), String.join(",", dependentFile.getDependencies()));
      }
   }

   private void loadRegistry(class_2960 name, JsonElement fullJson) {
      SophisticatedBackpacks.LOGGER.debug("Started loading registry data from {} ", name);
      String path = name.method_12832();
      String shortName = path.substring(path.lastIndexOf(47) + 1);
      if (fullJson.isJsonObject()) {
         JsonObject json = fullJson.getAsJsonObject();
         Optional<IRegistryDataLoader> loader = this.getLoader(shortName, json);
         if (loader.isEmpty()) {
            SophisticatedBackpacks.LOGGER.error("No loader defined for {}", shortName);
         } else {
            if (json.has("load_after")) {
               Set<String> dependencies = JsonHelper.setFromJson(json.get("load_after"), e -> class_3518.method_15287(e, ""));
               if (!this.areDependenciesLoaded(dependencies)) {
                  this.loadLater.add(new RegistryLoader.DependentFile(name, dependencies));
                  SophisticatedBackpacks.LOGGER.debug("Registry data at {} depend on {} which are not all loaded, skipping for now.", name, dependencies);
                  return;
               }
            }

            this.loadedRegistries.put(name, loader.get().getName());
            String modId = null;
            if (class_3518.method_15294(json, "mod")) {
               modId = class_3518.method_15265(json, "mod");
            }

            if (!this.isDisabled(json) && (modId == null || FabricLoader.getInstance().isModLoaded(modId))) {
               try {
                  loader.get().parse(json, modId);
                  SophisticatedBackpacks.LOGGER.debug("Finished loading registry data for {}", name);
               } catch (Exception var9) {
                  SophisticatedBackpacks.LOGGER.error("Caught exception while loading {} : {}", name, var9);
               }
            }
         }
      }
   }

   private boolean areDependenciesLoaded(Set<String> dependencies) {
      for (String dependency : dependencies) {
         if (!this.loadedRegistries.containsValue(dependency)) {
            return false;
         }
      }

      return true;
   }

   private boolean isDisabled(JsonObject json) {
      return json.has("disabled") && class_3518.method_15270(json, "disabled");
   }

   private Optional<IRegistryDataLoader> getLoader(String fileName, JsonObject json) {
      String parserName = fileName;
      if (json.has("type")) {
         parserName = class_3518.method_15265(json, "type");
      }

      return loaders.containsKey(parserName) ? Optional.of(loaders.get(parserName)) : Optional.empty();
   }

   static {
      registerParser(new ToolRegistry.BlockToolsLoader());
      registerParser(new ToolRegistry.EntityToolsLoader());
      registerParser(new SwordRegistry.SwordsLoader());
   }

   private static class DependentFile {
      private final class_2960 name;
      private final Set<String> dependencies;

      private DependentFile(class_2960 name, Set<String> dependencies) {
         this.name = name;
         this.dependencies = dependencies;
      }

      public Set<String> getDependencies() {
         return this.dependencies;
      }

      public class_2960 getName() {
         return this.name;
      }
   }
}
