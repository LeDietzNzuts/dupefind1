package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;

public interface IBackpackModel {
   <L extends class_1309, M extends class_583<L>> void render(
      M var1, class_1309 var2, class_4587 var3, class_4597 var4, int var5, int var6, int var7, class_1792 var8, RenderInfo var9
   );

   void renderBatteryCharge(class_4587 var1, class_4597 var2, int var3, float var4);

   void renderFluid(class_4587 var1, class_4597 var2, int var3, FluidStack var4, float var5, boolean var6);

   class_1304 getRenderEquipmentSlot();

   <L extends class_1309, M extends class_583<L>> void translateRotateAndScale(M var1, class_1309 var2, class_4587 var3, boolean var4);
}
