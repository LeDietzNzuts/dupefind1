package net.p3pp3rf1y.sophisticatedbackpacks.data;

import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.fabricators_of_create.porting_lib.conditions.ICondition;
import io.github.fabricators_of_create.porting_lib.conditions.WithConditions;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.class_2405;
import net.minecraft.class_2960;
import net.minecraft.class_7403;
import net.minecraft.class_7784;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_7784.class_7490;

public abstract class SBGlobalLootModifierProvider implements class_2405 {
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private final class_7784 output;
   private final CompletableFuture<class_7874> registriesLookup;
   protected class_7874 registries;
   private final String modid;
   private final Map<String, WithConditions<IGlobalLootModifier>> toSerialize = new HashMap<>();
   private boolean replace = false;

   public SBGlobalLootModifierProvider(class_7784 output, CompletableFuture<class_7874> registries, String modid) {
      this.output = output;
      this.registriesLookup = registries;
      this.modid = modid;
   }

   protected void replacing() {
      this.replace = true;
   }

   protected abstract void start();

   public final CompletableFuture<?> method_10319(class_7403 cache) {
      return this.registriesLookup.thenCompose(registries -> this.run(cache, registries));
   }

   protected CompletableFuture<?> run(class_7403 cache, class_7874 registries) {
      this.registries = registries;
      this.start();
      Path forgePath = this.output.method_45972(class_7490.field_39367).resolve("neoforge").resolve("loot_modifiers").resolve("global_loot_modifiers.json");
      Path modifierFolderPath = this.output.method_45972(class_7490.field_39367).resolve(this.modid).resolve("loot_modifiers");
      List<class_2960> entries = new ArrayList<>();
      Builder<CompletableFuture<?>> futuresBuilder = new Builder();

      for (Entry<String, WithConditions<IGlobalLootModifier>> entry : this.toSerialize.entrySet()) {
         String name = entry.getKey();
         WithConditions<IGlobalLootModifier> lootModifier = entry.getValue();
         entries.add(class_2960.method_60655(this.modid, name));
         Path modifierPath = modifierFolderPath.resolve(name + ".json");
         futuresBuilder.add(class_2405.method_53496(cache, registries, IGlobalLootModifier.CONDITIONAL_CODEC, Optional.of(lootModifier), modifierPath));
      }

      JsonObject forgeJson = new JsonObject();
      forgeJson.addProperty("replace", this.replace);
      forgeJson.add("entries", GSON.toJsonTree(entries.stream().map(class_2960::toString).collect(Collectors.toList())));
      futuresBuilder.add(class_2405.method_10320(cache, forgeJson, forgePath));
      return CompletableFuture.allOf((CompletableFuture<?>[])futuresBuilder.build().toArray(CompletableFuture[]::new));
   }

   public <T extends IGlobalLootModifier> void add(String modifier, T instance, List<ICondition> conditions) {
      this.toSerialize.put(modifier, new WithConditions(instance, (ResourceCondition[])conditions.toArray(ICondition[]::new)));
   }

   public <T extends IGlobalLootModifier> void add(String modifier, T instance, ICondition... conditions) {
      this.add(modifier, instance, Arrays.asList(conditions));
   }

   public String method_10321() {
      return "Global Loot Modifiers : " + this.modid;
   }
}
