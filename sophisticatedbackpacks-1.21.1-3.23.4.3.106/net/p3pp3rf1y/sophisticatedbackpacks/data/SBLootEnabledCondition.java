package net.p3pp3rf1y.sophisticatedbackpacks.data;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import net.minecraft.class_5342;
import net.minecraft.class_5341.class_210;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class SBLootEnabledCondition implements class_5341 {
   private static final SBLootEnabledCondition INSTANCE = new SBLootEnabledCondition();
   public static final MapCodec<SBLootEnabledCondition> CODEC = MapCodec.unit(INSTANCE).stable();

   private SBLootEnabledCondition() {
   }

   public class_5342 method_29325() {
      return ModItems.LOOT_ENABLED_CONDITION.get();
   }

   public boolean test(class_47 lootContext) {
      return Boolean.TRUE.equals(Config.COMMON.chestLootEnabled.get());
   }

   public static SBLootEnabledCondition.Builder builder() {
      return new SBLootEnabledCondition.Builder();
   }

   public static class Builder implements class_210 {
      public class_5341 build() {
         return new SBLootEnabledCondition();
      }
   }
}
