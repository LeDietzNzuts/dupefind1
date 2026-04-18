package noobanidus.mods.lootr.common.mixin.elytra;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2621;
import net.minecraft.class_2680;
import net.minecraft.class_3341;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_3342.class_3343;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.entity.LootrItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3343.class)
public class MixinEndCityPieces$EndCityPiece {
   @Inject(
      method = "method_15026",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5425;method_8649(Lnet/minecraft/class_1297;)Z"),
      cancellable = true,
      require = 0
   )
   private void LootrHandleDataMarker(String marker, class_2338 position, class_5425 level, class_5819 random, class_3341 boundingBox, CallbackInfo ci) {
      if (!LootrAPI.isDisabled()) {
         if (LootrAPI.shouldConvertElytrasToItemFrames()) {
            if (marker.startsWith("Elytra")) {
               LootrItemFrame itemframe = new LootrItemFrame(
                  level.method_8410(), position, ((class_3343)this).method_41626().method_15113().method_10503(class_2350.field_11035)
               );
               itemframe.lootrSetItem(new class_1799(class_1802.field_8833));
               level.method_8649(itemframe);
               ci.cancel();
            }
         } else if (LootrAPI.shouldConvertElytrasToChests() && marker.startsWith("Elytra")) {
            class_3343 piece = (class_3343)this;
            level.method_8652(
               position.method_10074(),
               (class_2680)class_2246.field_10034.method_9564().method_11657(class_2281.field_10768, piece.method_16888().method_10503(class_2350.field_11035)),
               3
            );
            if (level.method_8321(position.method_10074()) instanceof class_2621 chest) {
               chest.method_54867(LootrAPI.ELYTRA_CHEST, random.method_43055());
            }

            ci.cancel();
         }
      }
   }
}
