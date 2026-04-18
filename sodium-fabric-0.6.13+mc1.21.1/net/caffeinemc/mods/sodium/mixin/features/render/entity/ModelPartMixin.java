package net.caffeinemc.mods.sodium.mixin.features.render.entity;

import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.minecraft.class_4587;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_630.class)
public class ModelPartMixin {
   @Shadow
   public float field_3657;
   @Shadow
   public float field_3656;
   @Shadow
   public float field_3655;
   @Shadow
   public float field_37938;
   @Shadow
   public float field_37939;
   @Shadow
   public float field_37940;
   @Shadow
   public float field_3675;
   @Shadow
   public float field_3654;
   @Shadow
   public float field_3674;

   @Overwrite
   public void method_22703(class_4587 matrixStack) {
      if (this.field_3657 != 0.0F || this.field_3656 != 0.0F || this.field_3655 != 0.0F) {
         matrixStack.method_46416(this.field_3657 * 0.0625F, this.field_3656 * 0.0625F, this.field_3655 * 0.0625F);
      }

      if (this.field_3654 != 0.0F || this.field_3675 != 0.0F || this.field_3674 != 0.0F) {
         MatrixHelper.rotateZYX(matrixStack.method_23760(), this.field_3674, this.field_3675, this.field_3654);
      }

      if (this.field_37938 != 1.0F || this.field_37939 != 1.0F || this.field_37940 != 1.0F) {
         matrixStack.method_22905(this.field_37938, this.field_37939, this.field_37940);
      }
   }
}
