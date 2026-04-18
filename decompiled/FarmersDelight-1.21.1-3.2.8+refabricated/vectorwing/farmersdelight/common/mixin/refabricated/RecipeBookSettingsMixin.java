package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.datafixers.util.Pair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_5411;
import net.minecraft.class_5421;
import net.minecraft.class_5411.class_5412;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

@Mixin(class_5411.class)
public class RecipeBookSettingsMixin {
   @Final
   @Mutable
   @Shadow
   private static Map<class_5421, Pair<String, String>> field_25735;
   @Shadow
   @Final
   private Map<class_5421, class_5412> field_25736;

   @Inject(method = "<clinit>()V", at = @At("TAIL"))
   private static void fdrf$modifyTagFields(CallbackInfo ci) {
      Map<class_5421, Pair<String, String>> newMap = new HashMap<>(field_25735);
      newMap.put(FDRecipeBookTypes.COOKING, Pair.of("isFarmersDelightCookingGuiOpen", "isFarmersDelightCookingFilteringCraftable"));
      field_25735 = Map.copyOf(newMap);
   }

   @Inject(method = "<init>(Ljava/util/Map;)V", at = @At("TAIL"))
   private void fdrf$defaultCookingRecipeBookTypeStates(CallbackInfo ci) {
      if (!this.field_25736.containsKey(FDRecipeBookTypes.COOKING)) {
         this.field_25736.put(FDRecipeBookTypes.COOKING, new class_5412(false, false));
      }
   }

   @ModifyExpressionValue(
      method = "method_30186(Lnet/minecraft/class_2540;)Lnet/minecraft/class_5411;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5421;values()[Lnet/minecraft/class_5421;")
   )
   private static class_5421[] fdrf$modifyReadFDRecipeBookSettingsToVanilla(class_5421[] original) {
      return Arrays.stream(original).filter(recipeBookType -> recipeBookType != FDRecipeBookTypes.COOKING).toArray(class_5421[]::new);
   }

   @ModifyExpressionValue(
      method = "method_30190(Lnet/minecraft/class_2540;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5421;values()[Lnet/minecraft/class_5421;")
   )
   private class_5421[] fdrf$modifyWrittenFDRecipeBookSettingsToVanilla(class_5421[] original) {
      return Arrays.stream(original).filter(recipeBookType -> recipeBookType != FDRecipeBookTypes.COOKING).toArray(class_5421[]::new);
   }
}
