package net.caffeinemc.mods.lithium.mixin.entity.replace_entitytype_predicates;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1531;
import net.minecraft.class_1688;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_1688.class_1689;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1531.class)
public class ArmorStandMixin {
   @Shadow
   @Final
   private static Predicate<class_1297> field_7102;

   @Redirect(
      method = "method_6070()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1297> getMinecartsDirectly(class_1937 world, class_1297 excluded, class_238 box, Predicate<? super class_1297> predicate) {
      return predicate == field_7102
         ? world.method_8390(class_1688.class, box, e -> e != excluded && ((class_1688)e).method_7518() == class_1689.field_7674)
         : world.method_8333(excluded, box, predicate);
   }
}
