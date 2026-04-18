package net.caffeinemc.mods.lithium.mixin.entity.replace_entitytype_predicates;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1362;
import net.minecraft.class_1501;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1362.class)
public class LlamaFollowCaravanGoalMixin {
   @Redirect(
      method = "method_6264()Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1501> getLlamasForCaravan(class_1937 world, class_1297 excluded, class_238 box, Predicate<? super class_1297> predicate) {
      return world.method_8390(class_1501.class, box, entity -> entity != excluded);
   }
}
