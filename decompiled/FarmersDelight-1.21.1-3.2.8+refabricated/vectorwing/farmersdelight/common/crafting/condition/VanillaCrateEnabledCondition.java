package vectorwing.farmersdelight.common.crafting.condition;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

public class VanillaCrateEnabledCondition implements ResourceCondition {
   public static final MapCodec<VanillaCrateEnabledCondition> CODEC = MapCodec.unit(new VanillaCrateEnabledCondition());
   public static final ResourceConditionType<VanillaCrateEnabledCondition> TYPE = ResourceConditionType.create(
      FarmersDelight.res("vanilla_crates_enabled"), CODEC
   );

   public static void init() {
      ResourceConditions.register(TYPE);
   }

   public ResourceConditionType<?> getType() {
      return TYPE;
   }

   public boolean test(@Nullable class_7874 registryLookup) {
      return Configuration.ENABLE_VANILLA_CROP_CRATES.get();
   }
}
