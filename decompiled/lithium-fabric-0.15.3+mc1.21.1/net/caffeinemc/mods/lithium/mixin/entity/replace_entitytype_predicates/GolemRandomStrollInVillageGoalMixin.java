package net.caffeinemc.mods.lithium.mixin.entity.replace_entitytype_predicates;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1314;
import net.minecraft.class_1379;
import net.minecraft.class_1646;
import net.minecraft.class_238;
import net.minecraft.class_3218;
import net.minecraft.class_5274;
import net.minecraft.class_5575;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_5274.class)
public abstract class GolemRandomStrollInVillageGoalMixin extends class_1379 {
   public GolemRandomStrollInVillageGoalMixin(class_1314 mob, double speed) {
      super(mob, speed);
   }

   @Shadow
   protected abstract boolean method_27922(class_1646 var1);

   @Redirect(
      method = "method_27926()Lnet/minecraft/class_243;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3218;method_18023(Lnet/minecraft/class_5575;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1646> getEntities(
      class_3218 serverWorld, class_5575<class_1297, class_1646> filter, class_238 box, Predicate<? super class_1646> predicate
   ) {
      return filter == class_1299.field_6077
         ? serverWorld.method_8390(class_1646.class, this.field_6566.method_5829().method_1014(32.0), this::method_27922)
         : serverWorld.method_18023(class_1299.field_6077, this.field_6566.method_5829().method_1014(32.0), this::method_27922);
   }
}
