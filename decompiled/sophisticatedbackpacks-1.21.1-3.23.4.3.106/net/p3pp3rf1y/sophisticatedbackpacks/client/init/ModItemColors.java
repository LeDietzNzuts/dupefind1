package net.p3pp3rf1y.sophisticatedbackpacks.client.init;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.minecraft.class_1935;
import net.minecraft.class_326;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade.TankRenderInfo;

@Environment(EnvType.CLIENT)
public class ModItemColors {
   private ModItemColors() {
   }

   public static void registerItemColorHandlers() {
      ColorProviderRegistry.ITEM
         .register(
            (class_326)(backpack, layer) -> {
               if (layer <= 3 && backpack.method_7909() instanceof BackpackItem) {
                  IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(backpack);
                  if (layer == 0) {
                     return backpackWrapper.getMainColor();
                  } else if (layer == 1) {
                     return backpackWrapper.getAccentColor();
                  } else if (layer >= 2) {
                     TankRenderInfo info = (TankRenderInfo)backpackWrapper.getRenderInfo()
                        .getTankRenderInfos()
                        .getOrDefault(layer == 2 ? TankPosition.LEFT : TankPosition.RIGHT, null);
                     return info != null && !info.getFluid().isEmpty() ? FluidVariantRendering.getColor(((FluidStack)info.getFluid().get()).getVariant()) : -1;
                  } else {
                     return -1;
                  }
               } else {
                  return -1;
               }
            },
            (class_1935[])ModItems.BACKPACKS.stream().map(Supplier::get).toArray(BackpackItem[]::new)
         );
   }
}
