package dev.architectury.mixin.fabric.client;

import net.minecraft.class_280;
import net.minecraft.class_2960;
import net.minecraft.class_5912;
import net.minecraft.class_281.class_282;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Unique
@Mixin(value = class_280.class, priority = 950)
public class MixinEffectInstance {
   @Redirect(
      method = "<init>",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/resources/ResourceLocation;withDefaultNamespace(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;",
         ordinal = 0
      )
   )
   private class_2960 mojangPls(String _0, class_5912 rm, String str) {
      return mojangPls(class_2960.method_60654(str), ".json");
   }

   @Redirect(
      method = "getOrCreate",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/resources/ResourceLocation;withDefaultNamespace(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;",
         ordinal = 0
      )
   )
   private static class_2960 mojangPls(String _0, class_5912 rm, class_282 type, String str) {
      return mojangPls(class_2960.method_60654(str), type.method_1284());
   }

   private static class_2960 mojangPls(class_2960 rl, String ext) {
      return class_2960.method_60655(rl.method_12836(), "shaders/program/" + rl.method_12832() + ext);
   }
}
