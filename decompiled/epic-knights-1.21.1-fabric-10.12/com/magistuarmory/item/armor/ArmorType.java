package com.magistuarmory.item.armor;

import com.magistuarmory.client.render.model.ModModels;
import dev.architectury.registry.registries.DeferredRegister;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_156;
import net.minecraft.class_1741;
import net.minecraft.class_1856;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_5601;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1741.class_9196;
import org.jetbrains.annotations.NotNull;

public final class ArmorType {
   private final class_6880<class_1741> material;
   private final class_2960 location;
   private final class_2960 modellocation;
   private final EnumMap<class_8051, Integer> durability;
   private final boolean enabled;

   public ArmorType(class_2960 location, class_2960 modellocation, class_6880<class_1741> material, Integer[] durability, boolean enabled) {
      this.material = material;
      this.location = location;
      this.modellocation = modellocation;
      this.durability = (EnumMap<class_8051, Integer>)class_156.method_654(new EnumMap(class_8051.class), enumMap -> {
         enumMap.put(class_8051.field_41937, durability[0]);
         enumMap.put(class_8051.field_41936, durability[1]);
         enumMap.put(class_8051.field_41935, durability[2]);
         enumMap.put(class_8051.field_41934, durability[3]);
         enumMap.put(class_8051.field_48838, durability[2]);
      });
      this.enabled = enabled;
   }

   public ArmorType(
      DeferredRegister<class_1741> armorMaterial,
      class_2960 location,
      class_2960 modellocation,
      float toughness,
      float knockbackResistance,
      Integer[] durability,
      Integer[] defenseForSlot,
      int enchantmentValue,
      class_6880<class_3414> equipSound,
      boolean enabled,
      boolean dyeable,
      Supplier<class_1856> repairIngredient
   ) {
      List<class_9196> layers = dyeable
         ? List.of(new class_9196(location, "", true), new class_9196(location, "_overlay", false))
         : List.of(new class_9196(location, "", false));
      this.material = armorMaterial.register(location, () -> new class_1741((Map)class_156.method_654(new EnumMap(class_8051.class), enumMap -> {
         enumMap.put(class_8051.field_41937, defenseForSlot[0]);
         enumMap.put(class_8051.field_41936, defenseForSlot[1]);
         enumMap.put(class_8051.field_41935, defenseForSlot[2]);
         enumMap.put(class_8051.field_48838, defenseForSlot[2]);
         enumMap.put(class_8051.field_41934, defenseForSlot[3]);
      }), enchantmentValue, equipSound, repairIngredient, layers, toughness, knockbackResistance));
      this.location = location;
      this.modellocation = modellocation;
      this.durability = (EnumMap<class_8051, Integer>)class_156.method_654(new EnumMap(class_8051.class), enumMap -> {
         enumMap.put(class_8051.field_41937, durability[0]);
         enumMap.put(class_8051.field_41936, durability[1]);
         enumMap.put(class_8051.field_41935, durability[2]);
         enumMap.put(class_8051.field_48838, durability[2]);
         enumMap.put(class_8051.field_41934, durability[3]);
      });
      this.enabled = enabled;
   }

   public ArmorType(
      DeferredRegister<class_1741> armorMaterials,
      class_2960 location,
      class_2960 modellocation,
      float toughness,
      float knockbackResistance,
      Integer[] durability,
      Integer[] defenseForSlot,
      int enchantmentValue,
      class_6880<class_3414> equipSound,
      boolean enabled,
      boolean dyeable
   ) {
      this(
         armorMaterials,
         location,
         modellocation,
         toughness,
         knockbackResistance,
         durability,
         defenseForSlot,
         enchantmentValue,
         equipSound,
         enabled,
         dyeable,
         () -> class_1856.field_9017
      );
   }

   public ArmorType(
      DeferredRegister<class_1741> armorMaterials,
      class_2960 location,
      class_2960 modellocation,
      float toughness,
      float knockbackResistance,
      Integer[] durability,
      Integer[] defenseForSlot,
      int enchantmentValue,
      class_6880<class_3414> equipSound,
      boolean enabled,
      boolean dyeable,
      String repairitemtag
   ) {
      this(
         armorMaterials,
         location,
         modellocation,
         toughness,
         knockbackResistance,
         durability,
         defenseForSlot,
         enchantmentValue,
         equipSound,
         enabled,
         dyeable,
         () -> class_1856.method_8106(class_6862.method_40092(class_7924.field_41197, class_2960.method_60654(repairitemtag)))
      );
   }

   public String getName() {
      return this.location.toString();
   }

   public float getToughness() {
      return ((class_1741)this.material.comp_349()).comp_2303();
   }

   public float getKnockbackResistance() {
      return ((class_1741)this.material.comp_349()).comp_2304();
   }

   public int getDurabilityForType(@NotNull class_8051 type) {
      return this.durability.get(type);
   }

   public int getDefenseForType(@NotNull class_8051 type) {
      return ((class_1741)this.material.comp_349()).method_48403(type);
   }

   public int getEnchantmentValue() {
      return ((class_1741)this.material.comp_349()).comp_2299();
   }

   public class_6880<class_3414> getEquipSound() {
      return ((class_1741)this.material.comp_349()).comp_2300();
   }

   public Supplier<class_1856> getRepairIngredient() {
      return ((class_1741)this.material.comp_349()).comp_2301();
   }

   public List<class_9196> getLayers() {
      return ((class_1741)this.material.comp_349()).comp_2302();
   }

   public boolean isDisabled() {
      return !this.enabled;
   }

   @Environment(EnvType.CLIENT)
   public Optional<class_5601> getModelLocation() {
      return Objects.equals(this.modellocation.method_12832(), "default") ? Optional.empty() : Optional.of(ModModels.createArmorLocation(this.modellocation));
   }

   public class_6880<class_1741> getMaterial() {
      return this.material;
   }

   public class_2960 getLocation() {
      return this.location;
   }
}
