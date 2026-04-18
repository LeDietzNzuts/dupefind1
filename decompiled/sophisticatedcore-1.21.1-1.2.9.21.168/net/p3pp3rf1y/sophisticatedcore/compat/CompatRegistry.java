package net.p3pp3rf1y.sophisticatedcore.compat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public class CompatRegistry {
   private static final Map<String, CompatRegistry> compatRegistries = new ConcurrentHashMap<>();
   private final Map<CompatInfo, List<Supplier<ICompat>>> compatFactories = new ConcurrentHashMap<>();
   private final Map<CompatInfo, List<ICompat>> loadedCompats = new ConcurrentHashMap<>();

   public static CompatRegistry getRegistry(String identifier) {
      return compatRegistries.computeIfAbsent(identifier, key -> new CompatRegistry());
   }

   protected CompatRegistry() {
   }

   public void registerCompat(CompatInfo info, Supplier<ICompat> factory) {
      this.compatFactories.computeIfAbsent(info, k -> new ArrayList<>()).add(factory);
   }

   public void setupCompats() {
      this.loadedCompats.values().forEach(compats -> compats.forEach(ICompat::setup));
   }

   public void initCompats() {
      this.compatFactories.forEach((compatInfo, factories) -> {
         if (compatInfo.isLoaded()) {
            factories.forEach(factory -> {
               try {
                  this.loadedCompats.computeIfAbsent(compatInfo, k -> new ArrayList<>()).add(factory.get());
               } catch (Exception var4) {
                  SophisticatedCore.LOGGER.error("Error instantiating compatibility ", var4);
               }
            });
         }
      });
      this.loadedCompats.values().forEach(compats -> compats.forEach(ICompat::init));
   }

   @Nullable
   public static VersionPredicate fromSpec(String spec) {
      try {
         return VersionPredicate.parse(spec);
      } catch (VersionParsingException var2) {
         return null;
      }
   }
}
