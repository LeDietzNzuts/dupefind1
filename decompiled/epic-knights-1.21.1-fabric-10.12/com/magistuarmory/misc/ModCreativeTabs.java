package com.magistuarmory.misc;

import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ModItems;
import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7706;
import net.minecraft.class_7924;
import org.jetbrains.annotations.Nullable;

public class ModCreativeTabs {
   static Supplier<class_1799> ARMET_WITH_PLUME_SUPPLIER = () -> ModItems.getDecoratedStack(ModItems.ARMET, ModItems.BIG_PLUME_DECORATION);
   static Supplier<class_1799> CEREMONIAL_ARMET_WITH_PLUME_SUPPLIER = () -> ModItems.getDecoratedStack(ModItems.CEREMONIAL_ARMET, ModItems.BIG_PLUME_DECORATION);
   static final DeferredRegister<class_1761> TABS = DeferredRegister.create("magistuarmory", class_7924.field_44688);
   static final RegistrySupplier<class_1761> ARMOR = Platform.isFabric()
      ? TABS.register("armor", () -> CreativeTabRegistry.create(class_2561.method_43471("itemGroup.magistuarmory.armor"), ARMET_WITH_PLUME_SUPPLIER))
      : createTab("armor", ModItems.ARMET);
   static final RegistrySupplier<class_1761> WEAPONS = createTab("weapons", ModItems.FLAME_BLADED_SWORDS.iron);
   static final RegistrySupplier<class_1761> PARTICULAR_WEAPONS = createTab("particular_weapons", ModItems.NOBLE_SWORD);
   static final RegistrySupplier<class_1761> SHIELDS = createTab("shields", ModItems.HEATER_SHIELDS.iron);
   static final RegistrySupplier<class_1761> RUSTED = createTab("rusted", ModItems.RUSTED_BASTARD_SWORD);
   static final RegistrySupplier<class_1761> ARMOR_DECORATIONS = createTab("armor_decorations", ModItems.CROWN_DECORATION);
   public static final class_5321<class_1761> ARMOR_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "armor")
   );
   public static final class_5321<class_1761> WEAPONS_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "weapons")
   );
   public static final class_5321<class_1761> PARTICULAR_WEAPONS_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "particular_weapons")
   );
   public static final class_5321<class_1761> SHIELDS_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "shields")
   );
   public static final class_5321<class_1761> RUSTED_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "rusted")
   );
   public static final class_5321<class_1761> ARMOR_DECORATIONS_RESOURCE_KEY = class_5321.method_29179(
      class_7924.field_44688, class_2960.method_60655("magistuarmory", "armor_decorations")
   );
   public static final class_5321<class_1761> INGRIDIENTS_RESOURCE_KEY = class_7706.field_41062;

   public static RegistrySupplier<class_1761> createTab(String name, @Nullable RegistrySupplier<? extends class_1792> supplier) {
      return TABS.register(
         name,
         () -> CreativeTabRegistry.create(
            class_2561.method_43471("itemGroup.magistuarmory." + name), () -> supplier == null ? class_1799.field_8037 : new class_1799(getIconItem(supplier))
         )
      );
   }

   public static class_1792 getIconItem(RegistrySupplier<? extends class_1792> supplier) {
      return supplier != null && supplier.get() != null ? (class_1792)supplier.get() : class_1802.field_8077;
   }

   public static void init() {
      TABS.register();
      if (Platform.isFabric()) {
         appendStack(ARMOR, ARMET_WITH_PLUME_SUPPLIER);
      } else {
         append(ARMOR, ModItems.ARMET);
      }

      append(ARMOR, ModItems.KNIGHT_CHESTPLATE);
      append(ARMOR, ModItems.KNIGHT_LEGGINGS);
      append(ARMOR, ModItems.KNIGHT_BOOTS);
      append(ARMOR, ModItems.STECHHELM);
      append(ARMOR, ModItems.JOUSTING_CHESTPLATE);
      append(ARMOR, ModItems.JOUSTING_LEGGINGS);
      append(ARMOR, ModItems.JOUSTING_BOOTS);
      append(ARMOR, ModItems.SALLET);
      append(ARMOR, ModItems.GOTHIC_CHESTPLATE);
      append(ARMOR, ModItems.GOTHIC_LEGGINGS);
      append(ARMOR, ModItems.GOTHIC_BOOTS);
      append(ARMOR, ModItems.MAXIMILIAN_HELMET);
      append(ARMOR, ModItems.MAXIMILIAN_CHESTPLATE);
      append(ARMOR, ModItems.MAXIMILIAN_LEGGINGS);
      append(ARMOR, ModItems.MAXIMILIAN_BOOTS);
      append(ARMOR, ModItems.CHAINMAIL_HELMET);
      append(ARMOR, ModItems.CHAINMAIL_CHESTPLATE);
      append(ARMOR, ModItems.CHAINMAIL_LEGGINGS);
      append(ARMOR, ModItems.CHAINMAIL_BOOTS);
      append(ARMOR, ModItems.KETTLEHAT);
      append(ARMOR, ModItems.PLATEMAIL_CHESTPLATE);
      append(ARMOR, ModItems.PLATEMAIL_LEGGINGS);
      append(ARMOR, ModItems.PLATEMAIL_BOOTS);
      append(ARMOR, ModItems.BARBUTE);
      append(ARMOR, ModItems.HALFARMOR_CHESTPLATE);
      append(ARMOR, ModItems.GREATHELM);
      append(ARMOR, ModItems.CRUSADER_CHESTPLATE);
      append(ARMOR, ModItems.CRUSADER_LEGGINGS);
      append(ARMOR, ModItems.CRUSADER_BOOTS);
      if (Platform.isFabric()) {
         appendStack(ARMOR, CEREMONIAL_ARMET_WITH_PLUME_SUPPLIER);
      } else {
         append(ARMOR, ModItems.CEREMONIAL_ARMET);
      }

      append(ARMOR, ModItems.CEREMONIAL_CHESTPLATE);
      append(ARMOR, ModItems.CEREMONIAL_BOOTS);
      append(ARMOR, ModItems.COIF);
      append(ARMOR, ModItems.GAMBESON);
      append(ARMOR, ModItems.PANTYHOSE);
      append(ARMOR, ModItems.GAMBESON_BOOTS);
      append(ARMOR, ModItems.BRIGANDINE);
      append(ARMOR, ModItems.NORMAN_HELMET);
      append(ARMOR, ModItems.SHISHAK);
      append(ARMOR, ModItems.BASCINET);
      append(ARMOR, ModItems.XIV_CENTURY_KNIGHT_CHESTPLATE);
      append(ARMOR, ModItems.XIV_CENTURY_KNIGHT_LEGGINGS);
      append(ARMOR, ModItems.XIV_CENTURY_KNIGHT_BOOTS);
      append(ARMOR, ModItems.WINGED_HUSSAR_CHESTPLATE);
      append(ARMOR, ModItems.BURGONET);
      append(ARMOR, ModItems.CUIRASSIER_CHESTPLATE);
      append(ARMOR, ModItems.CUIRASSIER_LEGGINGS);
      append(ARMOR, ModItems.CUIRASSIER_BOOTS);
      append(ARMOR, ModItems.GRAND_BASCINET);
      append(ARMOR, ModItems.KASTENBRUST_CHESTPLATE);
      append(ARMOR, ModItems.KASTENBRUST_LEGGINGS);
      append(ARMOR, ModItems.KASTENBRUST_BOOTS);
      append(ARMOR, ModItems.FACE_HELMET);
      append(ARMOR, ModItems.LAMELLAR_CHESTPLATE);
      append(ARMOR, ModItems.LAMELLAR_BOOTS);
      append(ARMOR, ModItems.BARDING);
      append(ARMOR, ModItems.CHAINMAIL_HORSE_ARMOR);
      append(PARTICULAR_WEAPONS, ModItems.BLACKSMITH_HAMMER);
      append(PARTICULAR_WEAPONS, ModItems.BARBED_CLUB);
      append(PARTICULAR_WEAPONS, ModItems.PITCHFORK);
      append(PARTICULAR_WEAPONS, ModItems.NOBLE_SWORD);
      append(PARTICULAR_WEAPONS, ModItems.CLUB);
      append(PARTICULAR_WEAPONS, ModItems.MESSER_SWORD);
      append(PARTICULAR_WEAPONS, ModItems.LONGBOW);
      append(PARTICULAR_WEAPONS, ModItems.HEAVY_CROSSBOW);
      append(RUSTED, ModItems.RUSTED_BASTARD_SWORD);
      append(RUSTED, ModItems.RUSTED_HEAVY_MACE);
      append(RUSTED, ModItems.CORRUPTED_ROUND_SHIELD);
      append(RUSTED, ModItems.RUSTED_BARBUTE);
      append(RUSTED, ModItems.RUSTED_HALFARMOR_CHESTPLATE);
      append(RUSTED, ModItems.RUSTED_GREATHELM);
      append(RUSTED, ModItems.RUSTED_CRUSADER_CHESTPLATE);
      append(RUSTED, ModItems.RUSTED_CRUSADER_BOOTS);
      append(RUSTED, ModItems.RUSTED_NORMAN_HELMET);
      append(RUSTED, ModItems.RUSTED_CHAINMAIL_HELMET);
      append(RUSTED, ModItems.RUSTED_CHAINMAIL_CHESTPLATE);
      append(RUSTED, ModItems.RUSTED_CHAINMAIL_LEGGINGS);
      append(RUSTED, ModItems.RUSTED_CHAINMAIL_BOOTS);
      append(RUSTED, ModItems.RUSTED_KETTLEHAT);

      for (RegistrySupplier<? extends class_1792> item : ModItems.INSTANCE.ingredientItems) {
         append(INGRIDIENTS_RESOURCE_KEY, item);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.STILETTOS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.SHORT_SWORDS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.KATZBALGERS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.PIKES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.RANSEURS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.AHLSPIESSES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.GIANT_LANCES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.BASTARD_SWORDS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.ESTOCS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.CLAYMORS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.ZWEIHANDERS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.FLAME_BLADED_SWORDS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.LOCHABER_AXES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.CONCAVE_EDGED_HALBERDS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.HEAVY_MACES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.HEAVY_WAR_HAMMERS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.LUCERNE_HAMMERS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.MORNINGSTARS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.FLAILS.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.GUISARMES.get()) {
         append(WEAPONS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.HEATER_SHIELDS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.TARGETS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.BUCKLERS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.RONDACHES.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.TARTSCHES.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.ELLIPTICAL_SHIELDS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.ROUND_SHIELDS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.PAVISES.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.KITE_SHIELDS.get()) {
         append(SHIELDS, supplier);
      }

      for (RegistrySupplier<? extends ArmorDecoration> supplier : ModItems.INSTANCE.armorDecorationItems) {
         if (supplier != ModItems.CAT_EARS_DECORATION) {
            append(ARMOR_DECORATIONS, () -> ((ArmorDecoration)supplier.get()).method_8389());
         }
      }
   }

   @SafeVarargs
   public static <I extends class_1799, T extends Supplier<I>> void appendStack(DeferredSupplier<class_1761> tab, T... stacks) {
      Arrays.<Supplier>stream(stacks).filter(Objects::nonNull).forEach(stack -> {
         if (!((class_1799)stack.get()).method_7960()) {
            CreativeTabRegistry.appendStack(tab, new class_1799[]{(class_1799)stack.get()});
         }
      });
   }

   @SafeVarargs
   public static <I extends class_1935, T extends Supplier<I>> void append(class_5321<class_1761> tab, T... items) {
      Arrays.<Supplier>stream(items).filter(Objects::nonNull).forEach(item -> CreativeTabRegistry.append(tab, new Supplier[]{item}));
   }

   @SafeVarargs
   public static <I extends class_1935, T extends Supplier<I>> void append(DeferredSupplier<class_1761> tab, T... items) {
      Arrays.<Supplier>stream(items).filter(Objects::nonNull).forEach(item -> CreativeTabRegistry.append(tab, new Supplier[]{item}));
   }
}
