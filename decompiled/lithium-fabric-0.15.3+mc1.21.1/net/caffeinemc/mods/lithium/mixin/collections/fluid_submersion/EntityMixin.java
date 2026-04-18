package net.caffeinemc.mods.lithium.mixin.collections.fluid_submersion;

import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1297.class)
public class EntityMixin {
   @Mutable
   @Shadow
   @Final
   private Set<class_6862<class_3611>> field_25599;

   @Inject(method = "<init>(Lnet/minecraft/class_1299;Lnet/minecraft/class_1937;)V", at = @At("RETURN"))
   private void useReferenceArraySet(CallbackInfo ci) {
      this.field_25599 = new ReferenceArraySet(this.field_25599);
   }

   @Redirect(method = "method_5630()V", at = @At(value = "INVOKE", target = "Ljava/util/Set;clear()V"), require = 0)
   private void clearIfNotEmpty(Set<?> set) {
      if (!set.isEmpty()) {
         set.clear();
      }
   }
}
