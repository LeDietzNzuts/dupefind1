package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.caffeinemc.mods.lithium.common.world.ServerWorldExtended;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1408;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1308.class)
public abstract class MobMixin extends class_1309 implements NavigatingEntity {
   @Unique
   private class_1408 registeredNavigation;

   protected MobMixin(class_1299<? extends class_1309> entityType, class_1937 level) {
      super(entityType, level);
   }

   @Shadow
   public abstract class_1408 method_5942();

   @Override
   public boolean lithium$isRegisteredToWorld() {
      return this.registeredNavigation != null;
   }

   @Override
   public void lithium$setRegisteredToWorld(class_1408 navigation) {
      this.registeredNavigation = navigation;
   }

   @Override
   public class_1408 lithium$getRegisteredNavigation() {
      return this.registeredNavigation;
   }

   @Inject(method = "method_5873(Lnet/minecraft/class_1297;Z)Z", at = @At("RETURN"))
   private void onNavigationReplacement(class_1297 entity, boolean force, CallbackInfoReturnable<Boolean> cir) {
      this.lithium$updateNavigationRegistration();
   }

   @Override
   public void lithium$updateNavigationRegistration() {
      if (this.lithium$isRegisteredToWorld()) {
         class_1408 navigation = this.method_5942();
         if (this.registeredNavigation != navigation) {
            ((ServerWorldExtended)this.method_37908()).lithium$setNavigationInactive((class_1308)this);
            this.registeredNavigation = navigation;
            if (navigation.method_6345() != null) {
               ((ServerWorldExtended)this.method_37908()).lithium$setNavigationActive((class_1308)this);
            }
         }
      }
   }
}
