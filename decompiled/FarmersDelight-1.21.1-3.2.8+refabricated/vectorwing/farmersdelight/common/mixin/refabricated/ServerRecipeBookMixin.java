package vectorwing.farmersdelight.common.mixin.refabricated;

import java.util.List;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3439;
import net.minecraft.class_3441;
import net.minecraft.class_2713.class_2714;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

@Mixin(class_3441.class)
public class ServerRecipeBookMixin extends class_3439 {
   @Inject(method = "method_14899(Lnet/minecraft/class_2713$class_2714;Lnet/minecraft/class_3222;Ljava/util/List;)V", at = @At("TAIL"))
   private void fdrf$sendCookingRecipeValues(class_2714 state, class_3222 player, List<class_2960> recipes, CallbackInfo ci) {
      ServerPlayNetworking.send(
         player,
         new ModNetworking.SendRecipeBookValuesMessage(
            this.method_30173().method_30180(FDRecipeBookTypes.COOKING), this.method_30173().method_30187(FDRecipeBookTypes.COOKING)
         )
      );
   }
}
