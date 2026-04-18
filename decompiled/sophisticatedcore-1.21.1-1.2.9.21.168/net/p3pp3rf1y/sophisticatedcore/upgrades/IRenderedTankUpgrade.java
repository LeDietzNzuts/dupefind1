package net.p3pp3rf1y.sophisticatedcore.upgrades;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public interface IRenderedTankUpgrade {
   void setTankRenderInfoUpdateCallback(Consumer<IRenderedTankUpgrade.TankRenderInfo> var1);

   void forceUpdateTankRenderInfo();

   public static class TankRenderInfo {
      private static final String FLUID_TAG = "fluid";
      private static final String FILL_RATIO_TAG = "fillRatio";
      @Nullable
      private FluidStack fluidStack;
      private float fillRatio;

      public TankRenderInfo() {
         this(null, 0.0F);
      }

      public TankRenderInfo(@Nullable FluidStack fluidStack, float fillRatio) {
         this.fluidStack = fluidStack;
         this.fillRatio = fillRatio;
      }

      public class_2487 serialize() {
         class_2487 ret = new class_2487();
         if (this.fluidStack != null) {
            ret.method_10566(
               "fluid", RegistryHelper.getRegistryAccess().map(registryAccess -> this.fluidStack.saveOptional(registryAccess)).orElse(new class_2487())
            );
            ret.method_10548("fillRatio", this.fillRatio);
         }

         return ret;
      }

      public static IRenderedTankUpgrade.TankRenderInfo deserialize(class_2487 tag) {
         if (tag.method_10545("fluid")) {
            FluidStack fluidStack = RegistryHelper.getRegistryAccess()
               .map(registryAccess -> FluidStack.parseOptional(registryAccess, tag.method_10562("fluid")))
               .orElse(FluidStack.EMPTY);
            if (!fluidStack.isEmpty()) {
               return new IRenderedTankUpgrade.TankRenderInfo(fluidStack, tag.method_10583("fillRatio"));
            }
         }

         return new IRenderedTankUpgrade.TankRenderInfo();
      }

      public void setFluid(FluidStack fluidStack) {
         this.fluidStack = fluidStack.copy();
      }

      public Optional<FluidStack> getFluid() {
         return Optional.ofNullable(this.fluidStack);
      }

      public void setFillRatio(float fillRatio) {
         this.fillRatio = fillRatio;
      }

      public float getFillRatio() {
         return this.fillRatio;
      }
   }
}
