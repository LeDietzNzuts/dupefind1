package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_572;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

@Mixin(class_572.class)
public class HumanoidModelMixin {
   @Shadow
   @Final
   public class_630 field_27433;
   @Shadow
   @Final
   public class_630 field_3401;

   @Inject(
      method = "method_17087(Lnet/minecraft/class_1309;FFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1309;method_6058()Lnet/minecraft/class_1268;")
   )
   private <T extends class_1309> void farmersdelightrefabricated$setupSkilletThirdPersonAnim(
      T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci
   ) {
      class_1799 stack = entity.method_6030();
      if (stack.method_7909() instanceof SkilletItem && stack.method_57826(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
         long time = (Long)stack.method_57824(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get());
         float partialTicks = class_310.method_1551().method_60646().method_60637(false);
         float animation = ((float)(entity.method_37908().method_8510() - time) + partialTicks) / 12.0F;
         animation = class_3532.method_15363(animation, 0.0F, 1.0F);
         if (entity.method_6068() == class_1306.field_6182) {
            this.field_27433.field_3654 = (-class_3532.method_15374(animation * (float) (Math.PI * 2)) * 15.0F - 20.0F) * (float) (Math.PI / 180.0);
         } else {
            this.field_3401.field_3654 = (-class_3532.method_15374(animation * (float) (Math.PI * 2)) * 15.0F - 20.0F) * (float) (Math.PI / 180.0);
         }
      }
   }
}
