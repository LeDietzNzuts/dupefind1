package com.talhanation.smallships.mixin.controlling;

import com.talhanation.smallships.world.entity.ship.Ship;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1301;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1690.class)
public abstract class BoatMixin {
   @Shadow
   protected abstract void method_7549();

   @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat;tickLerp()V"))
   private void tickClientAndServerControlBoat(CallbackInfo ci) {
      if ((class_1690)this instanceof Ship) {
         this.method_7549();
      }
   }

   @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat;controlBoat()V"))
   private void tickCancelControlBoatHereForShip(class_1690 instance) {
      if (!((class_1690)this instanceof Ship)) {
         this.method_7549();
      }
   }

   @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat;checkInsideBlocks()V"), cancellable = true)
   private void tickCheckPassengersForShip(CallbackInfo ci) {
      class_1690 list = (class_1690)this;
      if (list instanceof Ship ship) {
         List<class_1297> listx = ((class_1690)this)
            .method_5770()
            .method_8333((class_1690)this, ((class_1690)this).method_5829().method_1009(0.2F, -0.01F, 0.2F), class_1301.method_5911((class_1690)this));
         if (!listx.isEmpty()) {
            boolean bl = !((class_1690)this).method_5770().field_9236 && !(((class_1690)this).method_5642() instanceof class_1657);

            for (int j = 0; j < listx.size(); j++) {
               class_1297 entity = listx.get(j);
               if (!entity.method_5626((class_1690)this)) {
                  if (bl && ship.method_5818(entity) && !(entity instanceof class_1657)) {
                     entity.method_5804((class_1690)this);
                  } else {
                     ((class_1690)this).method_5697(entity);
                  }
               }
            }
         }

         ci.cancel();
      }
   }
}
