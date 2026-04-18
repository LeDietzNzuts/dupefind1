package com.magistuarmory.item;

import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.BiFunction;
import net.minecraft.class_1792.class_1793;

public class WeaponsSupply extends ItemsSupply<MedievalWeaponItem> {
   public WeaponsSupply(BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> workshop) {
      super(workshop, class_1793::new);
   }
}
