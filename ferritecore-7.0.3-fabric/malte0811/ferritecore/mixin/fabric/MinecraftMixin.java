package malte0811.ferritecore.mixin.fabric;

import malte0811.ferritecore.impl.Deduplicator;
import net.minecraft.class_310;
import net.minecraft.class_542;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
public class MinecraftMixin {
   @Inject(
      method = "<init>",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/resources/model/ModelManager;Lnet/minecraft/client/color/item/ItemColors;Lnet/minecraft/client/renderer/BlockEntityWithoutLevelRenderer;)V"
      )
   )
   private void injectAfterModels(class_542 gameConfig, CallbackInfo ci) {
      Deduplicator.registerReloadListener();
   }
}
