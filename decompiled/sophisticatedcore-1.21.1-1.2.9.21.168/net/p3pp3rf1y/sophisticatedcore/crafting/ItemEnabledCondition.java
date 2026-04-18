package net.p3pp3rf1y.sophisticatedcore.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.Config;
import net.p3pp3rf1y.sophisticatedcore.init.ModRecipes;

public record ItemEnabledCondition(class_2960 itemRegistryName) implements ResourceCondition {
   public static final MapCodec<ItemEnabledCondition> CODEC = RecordCodecBuilder.mapCodec(
      builder -> builder.group(class_2960.field_25139.fieldOf("itemRegistryName").forGetter(ItemEnabledCondition::itemRegistryName))
         .apply(builder, ItemEnabledCondition::new)
   );

   public ItemEnabledCondition(class_1792 item) {
      this(class_7923.field_41178.method_10221(item));
   }

   public boolean test(class_7874 registryLookup) {
      return Config.COMMON.enabledItems.isItemEnabled(this.itemRegistryName);
   }

   public ResourceConditionType<?> getType() {
      return ModRecipes.ITEM_ENABLED_CONDITION;
   }
}
