package net.caffeinemc.mods.lithium.mixin.entity.collisions.unpushable_cramming;

import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.entity.pushable.BlockCachingEntity;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.caffeinemc.mods.lithium.common.world.ClimbingMobCachingSection;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_4076;
import net.minecraft.class_5572;
import net.minecraft.class_5573;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 implements BlockCachingEntity {
   boolean updateClimbingMobCachingSectionOnChange;

   public LivingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Redirect(
      method = "method_6070()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1297> getOtherPushableEntities(class_1937 world, @Nullable class_1297 except, class_238 box, Predicate<? super class_1297> predicate) {
      if (predicate == Predicates.alwaysFalse()) {
         return Collections.emptyList();
      } else {
         if (predicate instanceof EntityPushablePredicate<?> entityPushablePredicate) {
            class_5573<class_1297> cache = WorldHelper.getEntityCacheOrNull(world);
            if (cache != null) {
               return WorldHelper.getPushableEntities(world, cache, except, box, (EntityPushablePredicate<? super class_1297>)entityPushablePredicate);
            }
         }

         return world.method_8333(except, box, predicate);
      }
   }

   @Override
   public void lithium$SetClimbingMobCachingSectionUpdateBehavior(boolean listenForCachedBlockChanges) {
      this.updateClimbingMobCachingSectionOnChange = listenForCachedBlockChanges;
   }

   @Override
   public void lithium$OnBlockCacheDeleted() {
      if (this.updateClimbingMobCachingSectionOnChange) {
         this.updateClimbingMobCachingSection(null);
      }
   }

   @Override
   public void lithium$OnBlockCacheSet(class_2680 newState) {
      if (this.updateClimbingMobCachingSectionOnChange) {
         this.updateClimbingMobCachingSection(newState);
      }
   }

   private void updateClimbingMobCachingSection(class_2680 newState) {
      class_5573<class_1297> entityCacheOrNull = WorldHelper.getEntityCacheOrNull(this.method_37908());
      if (entityCacheOrNull != null) {
         class_5572<class_1297> trackingSection = entityCacheOrNull.method_31785(class_4076.method_33706(this.method_24515()));
         if (trackingSection != null) {
            ((ClimbingMobCachingSection)trackingSection).lithium$onEntityModifiedCachedBlock(this, newState);
         } else {
            this.updateClimbingMobCachingSectionOnChange = false;
         }
      }
   }
}
