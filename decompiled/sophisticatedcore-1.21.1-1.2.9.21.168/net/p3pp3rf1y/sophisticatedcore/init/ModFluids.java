package net.p3pp3rf1y.sophisticatedcore.init;

import io.github.fabricators_of_create.porting_lib.fluids.FluidType;
import io.github.fabricators_of_create.porting_lib.fluids.PortingLibFluids;
import io.github.fabricators_of_create.porting_lib.fluids.BaseFlowingFluid.Flowing;
import io.github.fabricators_of_create.porting_lib.fluids.BaseFlowingFluid.Source;
import io.github.fabricators_of_create.porting_lib.fluids.FluidType.Properties;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalFluidTags;
import net.minecraft.class_1755;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_3609;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_1792.class_1793;

public class ModFluids {
   public static final class_6862<class_3611> EXPERIENCE_TAG = ConventionalFluidTags.EXPERIENCE;
   public static final DeferredRegister<class_3611> FLUIDS = DeferredRegister.create(class_7924.field_41270, "sophisticatedcore");
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(PortingLibFluids.FLUID_TYPES, "sophisticatedcore");
   public static final Supplier<class_3609> XP_STILL = FLUIDS.register("xp_still", () -> new Source(fluidProperties()));
   public static final Supplier<class_3609> XP_FLOWING = FLUIDS.register("xp_flowing", () -> new Flowing(fluidProperties()));
   public static final Supplier<FluidType> XP_FLUID_TYPE = FLUID_TYPES.register(
      "experience", () -> new FluidType(Properties.create().lightLevel(10).density(800).viscosity(1500))
   );
   public static final DeferredRegister<class_1792> ITEMS = DeferredRegister.create(class_7924.field_41197, "sophisticatedcore");
   public static final Supplier<class_1792> XP_BUCKET = ITEMS.register(
      "xp_bucket", () -> new class_1755((class_3611)XP_STILL.get(), new class_1793().method_7889(1))
   );
   public static final DeferredRegister<class_1761> CREATIVE_MODE_TABS = DeferredRegister.create(class_7924.field_44688.method_29177(), "sophisticatedcore");
   public static final Supplier<class_1761> CREATIVE_TAB = CREATIVE_MODE_TABS.register(
      "main",
      () -> FabricItemGroup.builder()
         .method_47320(() -> new class_1799((class_1935)XP_BUCKET.get()))
         .method_47321(class_2561.method_43471("itemGroup.sophisticatedcore"))
         .method_47317((featureFlags, output) -> output.method_45420(new class_1799((class_1935)XP_BUCKET.get())))
         .method_47324()
   );

   private ModFluids() {
   }

   private static io.github.fabricators_of_create.porting_lib.fluids.BaseFlowingFluid.Properties fluidProperties() {
      return new io.github.fabricators_of_create.porting_lib.fluids.BaseFlowingFluid.Properties(XP_FLUID_TYPE, XP_STILL, XP_FLOWING).bucket(XP_BUCKET);
   }

   public static void registerHandlers() {
      FLUIDS.register();
      FLUID_TYPES.register();
      ITEMS.register();
      CREATIVE_MODE_TABS.register();
   }
}
