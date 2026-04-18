package net.caffeinemc.mods.lithium.mixin.entity.replace_entitytype_predicates;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1530;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1530.class)
public abstract class HangingEntityMixin extends class_1297 {
   @Shadow
   @Final
   protected static Predicate<class_1297> field_7098;

   public HangingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Redirect(
      method = "method_6888()Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1297> getAbstractDecorationEntities(class_1937 world, class_1297 excluded, class_238 box, Predicate<? super class_1297> predicate) {
      return predicate == field_7098 ? world.method_8390(class_1530.class, box, entity -> entity != excluded) : world.method_8333(excluded, box, predicate);
   }
}
