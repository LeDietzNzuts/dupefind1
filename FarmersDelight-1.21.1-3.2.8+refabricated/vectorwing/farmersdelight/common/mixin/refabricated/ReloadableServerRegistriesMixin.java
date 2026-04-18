package vectorwing.farmersdelight.common.mixin.refabricated;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.class_3300;
import net.minecraft.class_6903;
import net.minecraft.class_7780;
import net.minecraft.class_8490;
import net.minecraft.class_9383;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.TagUtils;

@Mixin(class_9383.class)
public class ReloadableServerRegistriesMixin {
   @Inject(
      method = "method_58276(Lnet/minecraft/class_6903;Lnet/minecraft/class_3300;Ljava/util/concurrent/Executor;Lnet/minecraft/class_8490;)Ljava/util/concurrent/CompletableFuture;",
      at = @At("HEAD")
   )
   private static <T> void enchiridion$setLootTableAccess(
      class_6903 registryOps, class_3300 resourceManager, Executor executor, class_8490<T> lootDataType, CallbackInfoReturnable<CompletableFuture> cir
   ) {
      if (lootDataType == class_8490.field_44498) {
         TagUtils.setLootTableResourceManager(resourceManager);
      }
   }

   @Inject(method = "method_58288(Lnet/minecraft/class_7780;Ljava/util/List;)Lnet/minecraft/class_7780;", at = @At("RETURN"))
   private static void enchiridion$clearLootTableAccess(class_7780 layeredRegistryAccess, List list, CallbackInfoReturnable<class_7780> cir) {
      TagUtils.resetEarlyTagCollections();
   }
}
