package com.magistuarmory.item;

import dev.architectury.registry.registries.RegistrySupplier;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.class_1792;
import net.minecraft.class_1792.class_1793;

public class ItemsSupply<T extends class_1792> {
   @Nullable
   public RegistrySupplier<T> wood;
   @Nullable
   public RegistrySupplier<T> stone;
   @Nullable
   public RegistrySupplier<T> iron;
   @Nullable
   public RegistrySupplier<T> gold;
   @Nullable
   public RegistrySupplier<T> diamond;
   @Nullable
   public RegistrySupplier<T> copper;
   @Nullable
   public RegistrySupplier<T> steel;
   @Nullable
   public RegistrySupplier<T> silver;
   @Nullable
   public RegistrySupplier<T> netherite;
   @Nullable
   public RegistrySupplier<T> tin;
   @Nullable
   public RegistrySupplier<T> bronze;

   public ItemsSupply(BiFunction<ModItemTier, class_1793, RegistrySupplier<T>> workshop, Supplier<class_1793> prop) {
      this.wood = workshop.apply(ModItemTier.WOOD, prop.get());
      this.stone = workshop.apply(ModItemTier.STONE, prop.get());
      this.iron = workshop.apply(ModItemTier.IRON, prop.get());
      this.gold = workshop.apply(ModItemTier.GOLD, prop.get());
      this.diamond = workshop.apply(ModItemTier.DIAMOND, prop.get());
      this.netherite = workshop.apply(ModItemTier.NETHERITE, prop.get().method_24359());
      this.copper = workshop.apply(ModItemTier.COPPER, prop.get());
      this.steel = workshop.apply(ModItemTier.STEEL, prop.get());
      this.silver = workshop.apply(ModItemTier.SILVER, prop.get());
      this.tin = workshop.apply(ModItemTier.TIN, prop.get());
      this.bronze = workshop.apply(ModItemTier.BRONZE, prop.get());
   }

   public ArrayList<RegistrySupplier<T>> get() {
      ArrayList<RegistrySupplier<T>> suppliers = new ArrayList<>();
      suppliers.add(this.iron);
      suppliers.add(this.wood);
      suppliers.add(this.stone);
      suppliers.add(this.gold);
      suppliers.add(this.diamond);
      suppliers.add(this.copper);
      suppliers.add(this.steel);
      suppliers.add(this.silver);
      suppliers.add(this.netherite);
      suppliers.add(this.tin);
      suppliers.add(this.bronze);
      return suppliers;
   }
}
