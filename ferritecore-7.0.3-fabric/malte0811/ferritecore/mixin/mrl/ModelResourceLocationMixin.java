package malte0811.ferritecore.mixin.mrl;

import malte0811.ferritecore.impl.Deduplicator;
import net.minecraft.class_1091;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1091.class)
public class ModelResourceLocationMixin {
   @Shadow
   @Final
   @Mutable
   private String comp_2876;

   @Inject(method = "<init>(Lnet/minecraft/resources/ResourceLocation;Ljava/lang/String;)V", at = @At("TAIL"))
   private void constructTail(class_2960 location, String variantIn, CallbackInfo ci) {
      this.comp_2876 = Deduplicator.deduplicateVariant(this.comp_2876);
   }
}
