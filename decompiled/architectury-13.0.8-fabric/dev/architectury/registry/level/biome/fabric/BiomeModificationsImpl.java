package dev.architectury.registry.level.biome.fabric;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import dev.architectury.hooks.level.biome.BiomeHooks;
import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.hooks.level.biome.ClimateProperties;
import dev.architectury.hooks.level.biome.EffectsProperties;
import dev.architectury.hooks.level.biome.GenerationProperties;
import dev.architectury.hooks.level.biome.SpawnProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.EffectsContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.GenerationSettingsContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.SpawnSettingsContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.WeatherContext;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1959;
import net.minecraft.class_2922;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_4761;
import net.minecraft.class_4967;
import net.minecraft.class_4968;
import net.minecraft.class_5195;
import net.minecraft.class_5321;
import net.minecraft.class_6796;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_1959.class_5484;
import net.minecraft.class_2893.class_2894;
import net.minecraft.class_2893.class_2895;
import net.minecraft.class_4763.class_5486;
import net.minecraft.class_5483.class_1964;
import net.minecraft.class_5483.class_5265;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class BiomeModificationsImpl {
   private static final class_2960 FABRIC_MODIFICATION = class_2960.method_60655("architectury", "fabric_modification");
   private static final List<Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>>> ADDITIONS = Lists.newArrayList();
   private static final List<Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>>> POST_PROCESSING = Lists.newArrayList();
   private static final List<Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>>> REMOVALS = Lists.newArrayList();
   private static final List<Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>>> REPLACEMENTS = Lists.newArrayList();

   public static void addProperties(
      Predicate<BiomeModifications.BiomeContext> predicate, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> modifier
   ) {
      ADDITIONS.add(Pair.of(predicate, modifier));
   }

   public static void postProcessProperties(
      Predicate<BiomeModifications.BiomeContext> predicate, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> modifier
   ) {
      POST_PROCESSING.add(Pair.of(predicate, modifier));
   }

   public static void removeProperties(
      Predicate<BiomeModifications.BiomeContext> predicate, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> modifier
   ) {
      REMOVALS.add(Pair.of(predicate, modifier));
   }

   public static void replaceProperties(
      Predicate<BiomeModifications.BiomeContext> predicate, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> modifier
   ) {
      REPLACEMENTS.add(Pair.of(predicate, modifier));
   }

   private static void registerModification(
      BiomeModification modification,
      ModificationPhase phase,
      List<Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>>> list
   ) {
      modification.add(phase, Predicates.alwaysTrue(), (biomeSelectionContext, biomeModificationContext) -> {
         BiomeModifications.BiomeContext biomeContext = wrapSelectionContext(biomeSelectionContext);
         BiomeProperties.Mutable mutableBiome = wrapMutableBiome(biomeSelectionContext.getBiome(), biomeModificationContext);

         for (Pair<Predicate<BiomeModifications.BiomeContext>, BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable>> pair : list) {
            if (((Predicate)pair.getLeft()).test(biomeContext)) {
               ((BiConsumer)pair.getRight()).accept(biomeContext, mutableBiome);
            }
         }
      });
   }

   private static BiomeModifications.BiomeContext wrapSelectionContext(BiomeSelectionContext context) {
      return new BiomeModifications.BiomeContext() {
         BiomeProperties properties = BiomeHooks.getBiomeProperties(context.getBiome());

         @Override
         public Optional<class_2960> getKey() {
            return Optional.ofNullable(context.getBiomeKey().method_29177());
         }

         @Override
         public BiomeProperties getProperties() {
            return this.properties;
         }

         @Override
         public boolean hasTag(class_6862<class_1959> tag) {
            return context.hasTag(tag);
         }
      };
   }

   private static BiomeProperties.Mutable wrapMutableBiome(class_1959 biome, BiomeModificationContext context) {
      return new BiomeHooks.MutableBiomeWrapped(
         biome,
         wrapWeather(biome, context.getWeather()),
         wrapEffects(biome, context.getEffects()),
         new BiomeModificationsImpl.MutableGenerationProperties(biome, context.getGenerationSettings()),
         new BiomeModificationsImpl.MutableSpawnProperties(biome, context.getSpawnSettings())
      ) {};
   }

   private static ClimateProperties.Mutable wrapWeather(class_1959 biome, WeatherContext context) {
      return new BiomeHooks.ClimateWrapped(biome) {
         @Override
         public ClimateProperties.Mutable setHasPrecipitation(boolean hasPrecipitation) {
            context.setPrecipitation(hasPrecipitation);
            return this;
         }

         @Override
         public ClimateProperties.Mutable setTemperature(float temperature) {
            context.setTemperature(temperature);
            return this;
         }

         @Override
         public ClimateProperties.Mutable setTemperatureModifier(class_5484 temperatureModifier) {
            context.setTemperatureModifier(temperatureModifier);
            return this;
         }

         @Override
         public ClimateProperties.Mutable setDownfall(float downfall) {
            context.setDownfall(downfall);
            return this;
         }
      };
   }

   private static EffectsProperties.Mutable wrapEffects(class_1959 biome, EffectsContext context) {
      return new BiomeHooks.EffectsWrapped(biome) {
         @Override
         public EffectsProperties.Mutable setFogColor(int color) {
            context.setFogColor(color);
            return this;
         }

         @Override
         public EffectsProperties.Mutable setWaterColor(int color) {
            context.setWaterColor(color);
            return this;
         }

         @Override
         public EffectsProperties.Mutable setWaterFogColor(int color) {
            context.setWaterFogColor(color);
            return this;
         }

         @Override
         public EffectsProperties.Mutable setSkyColor(int color) {
            context.setSkyColor(color);
            return this;
         }

         @Override
         public EffectsProperties.Mutable setFoliageColorOverride(@Nullable Integer colorOverride) {
            context.setFoliageColor(Optional.ofNullable(colorOverride));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setGrassColorOverride(@Nullable Integer colorOverride) {
            context.setGrassColor(Optional.ofNullable(colorOverride));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setGrassColorModifier(class_5486 modifier) {
            context.setGrassColorModifier(modifier);
            return this;
         }

         @Override
         public EffectsProperties.Mutable setAmbientParticle(@Nullable class_4761 settings) {
            context.setParticleConfig(Optional.ofNullable(settings));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setAmbientLoopSound(@Nullable class_6880<class_3414> sound) {
            context.setAmbientSound(Optional.ofNullable(sound));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setAmbientMoodSound(@Nullable class_4968 settings) {
            context.setMoodSound(Optional.ofNullable(settings));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setAmbientAdditionsSound(@Nullable class_4967 settings) {
            context.setAdditionsSound(Optional.ofNullable(settings));
            return this;
         }

         @Override
         public EffectsProperties.Mutable setBackgroundMusic(@Nullable class_5195 music) {
            context.setMusic(Optional.ofNullable(music));
            return this;
         }
      };
   }

   static {
      BiomeModification modification = net.fabricmc.fabric.api.biome.v1.BiomeModifications.create(FABRIC_MODIFICATION);
      registerModification(modification, ModificationPhase.ADDITIONS, ADDITIONS);
      registerModification(modification, ModificationPhase.POST_PROCESSING, POST_PROCESSING);
      registerModification(modification, ModificationPhase.REMOVALS, REMOVALS);
      registerModification(modification, ModificationPhase.REPLACEMENTS, REPLACEMENTS);
   }

   private static class MutableGenerationProperties extends BiomeHooks.GenerationSettingsWrapped implements GenerationProperties.Mutable {
      protected final GenerationSettingsContext context;

      public MutableGenerationProperties(class_1959 biome, GenerationSettingsContext context) {
         super(biome);
         this.context = context;
      }

      @Override
      public GenerationProperties.Mutable addFeature(class_2895 decoration, class_6880<class_6796> feature) {
         Either<class_5321<class_6796>, class_6796> unwrap = feature.method_40229();
         if (unwrap.left().isPresent()) {
            this.context.addFeature(decoration, (class_5321)unwrap.left().get());
            return this;
         } else {
            throw new UnsupportedOperationException("Cannot add a feature that is not registered: " + unwrap.right().orElseThrow());
         }
      }

      @Override
      public GenerationProperties.Mutable addFeature(class_2895 decoration, class_5321<class_6796> feature) {
         this.context.addFeature(decoration, feature);
         return this;
      }

      @Override
      public GenerationProperties.Mutable addCarver(class_2894 carving, class_6880<class_2922<?>> feature) {
         Either<class_5321<class_2922<?>>, class_2922<?>> unwrap = feature.method_40229();
         if (unwrap.left().isPresent()) {
            this.context.addCarver(carving, (class_5321)unwrap.left().get());
            return this;
         } else {
            throw new UnsupportedOperationException("Cannot add a carver that is not registered: " + unwrap.right().orElseThrow());
         }
      }

      @Override
      public GenerationProperties.Mutable addCarver(class_2894 carving, class_5321<class_2922<?>> feature) {
         this.context.addCarver(carving, feature);
         return this;
      }

      @Override
      public GenerationProperties.Mutable removeFeature(class_2895 decoration, class_5321<class_6796> feature) {
         this.context.removeFeature(decoration, feature);
         return this;
      }

      @Override
      public GenerationProperties.Mutable removeCarver(class_2894 carving, class_5321<class_2922<?>> feature) {
         this.context.removeCarver(carving, feature);
         return this;
      }
   }

   private static class MutableSpawnProperties extends BiomeHooks.SpawnSettingsWrapped implements SpawnProperties.Mutable {
      protected final SpawnSettingsContext context;

      public MutableSpawnProperties(class_1959 biome, SpawnSettingsContext context) {
         super(biome);
         this.context = context;
      }

      @Override
      public SpawnProperties.Mutable setCreatureProbability(float probability) {
         this.context.setCreatureSpawnProbability(probability);
         return this;
      }

      @Override
      public SpawnProperties.Mutable addSpawn(class_1311 category, class_1964 data) {
         this.context.addSpawn(category, data);
         return this;
      }

      @Override
      public boolean removeSpawns(BiPredicate<class_1311, class_1964> predicate) {
         return this.context.removeSpawns(predicate);
      }

      @Override
      public SpawnProperties.Mutable setSpawnCost(class_1299<?> entityType, class_5265 cost) {
         this.context.setSpawnCost(entityType, cost.comp_1308(), cost.comp_1307());
         return this;
      }

      @Override
      public SpawnProperties.Mutable setSpawnCost(class_1299<?> entityType, double charge, double energyBudget) {
         this.context.setSpawnCost(entityType, charge, energyBudget);
         return this;
      }

      @Override
      public SpawnProperties.Mutable clearSpawnCost(class_1299<?> entityType) {
         this.context.clearSpawnCost(entityType);
         return this;
      }
   }
}
