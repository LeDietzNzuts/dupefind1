package vectorwing.farmersdelight.data;

import com.mojang.serialization.Lifecycle;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider.Entries;
import net.minecraft.class_2378;
import net.minecraft.class_5321;
import net.minecraft.class_7871;
import net.minecraft.class_7877;
import net.minecraft.class_7891;
import net.minecraft.class_7924;
import net.minecraft.class_8107;
import net.minecraft.class_8108;
import net.minecraft.class_8110;
import net.minecraft.class_6880.class_6883;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.data.advancement.FDAdvancementGenerator;
import vectorwing.farmersdelight.data.loot.FDBlockLoot;

public class DataGenerators implements DataGeneratorEntrypoint {
   public void onInitializeDataGenerator(FabricDataGenerator generator) {
      Pack pack = generator.createPack();
      pack.addProvider(DataGenerators.DynamicRegistryProvider::new);
      BlockTags blockTags = (BlockTags)pack.addProvider(BlockTags::new);
      pack.addProvider((output, registriesFuture) -> new ItemTags(output, registriesFuture, blockTags));
      pack.addProvider(EntityTags::new);
      pack.addProvider(DamageTypeTags::new);
      pack.addProvider(EnchantmentTags::new);
      pack.addProvider(Recipes::new);
      pack.addProvider(FDAdvancementGenerator::new);
      pack.addProvider(FDBlockLoot::new);
   }

   public void buildRegistry(class_7877 registryBuilder) {
      registryBuilder.method_46777(
         class_7924.field_42534,
         bootstrapContext -> bootstrapContext.method_46838(
            ModDamageTypes.STOVE_BURN, new class_8110("farmersdelight.stove", class_8108.field_42286, 0.1F, class_8107.field_42278)
         )
      );
      registryBuilder.method_46777(class_7924.field_41265, ModEnchantments::bootstrap);
   }

   private static class DynamicRegistryProvider extends FabricDynamicRegistryProvider {
      public DynamicRegistryProvider(FabricDataOutput output, CompletableFuture<class_7874> lookup) {
         super(output, lookup);
      }

      protected void configure(class_7874 registries, Entries entries) {
         ModEnchantments.bootstrap(createContext(registries, entries));
      }

      private static <T> class_7891<T> createContext(class_7874 registries, Entries entries) {
         return new class_7891<T>() {
            public class_6883<T> method_46800(class_5321<T> resourceKey, T object, Lifecycle lifecycle) {
               return (class_6883<T>)entries.add(resourceKey, object);
            }

            public <S> class_7871<S> method_46799(class_5321<? extends class_2378<? extends S>> resourceKey) {
               return registries.method_46762(resourceKey);
            }
         };
      }

      @NotNull
      public String method_10321() {
         return "Dynamic Registries";
      }
   }
}
