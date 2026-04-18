package net.caffeinemc.mods.lithium.mixin.entity.collisions.unpushable_cramming;

import java.util.ArrayList;
import java.util.Iterator;
import net.caffeinemc.mods.lithium.common.entity.pushable.BlockCachingEntity;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.caffeinemc.mods.lithium.common.entity.pushable.PushableEntityClassGroup;
import net.caffeinemc.mods.lithium.common.util.collections.ReferenceMaskedList;
import net.caffeinemc.mods.lithium.common.world.ClimbingMobCachingSection;
import net.minecraft.class_1297;
import net.minecraft.class_1510;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3509;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5584;
import net.minecraft.class_7927.class_7928;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5572.class)
public abstract class EntitySectionMixin<T extends class_5568> implements ClimbingMobCachingSection {
   @Shadow
   @Final
   private class_3509<T> field_27248;
   @Shadow
   private class_5584 field_27249;
   @Unique
   private ReferenceMaskedList<class_1297> pushableEntities;

   @Override
   public class_7928 lithium$collectPushableEntities(
      class_1937 world, class_1297 except, class_238 box, EntityPushablePredicate<? super class_1297> entityPushablePredicate, ArrayList<class_1297> entities
   ) {
      Iterator<?> entityIterator;
      if (this.pushableEntities != null) {
         entityIterator = this.pushableEntities.iterator();
      } else {
         entityIterator = this.field_27248.iterator();
      }

      int i = 0;
      int j = 0;

      while (entityIterator.hasNext()) {
         class_1297 entity = (class_1297)entityIterator.next();
         if (entity.method_5829().method_994(box) && !entity.method_7325() && entity != except && !(entity instanceof class_1510)) {
            i++;
            if (entityPushablePredicate.test(entity)) {
               j++;
               entities.add(entity);
            }
         }
      }

      if (this.pushableEntities == null && i >= 25 && i >= j * 2) {
         this.startFilteringPushableEntities();
      }

      return class_7928.field_41283;
   }

   @Unique
   private void startFilteringPushableEntities() {
      this.pushableEntities = new ReferenceMaskedList<>();

      for (T entity : this.field_27248) {
         this.onStartClimbingCachingEntity((class_1297)entity);
      }
   }

   @Unique
   private void stopFilteringPushableEntities() {
      this.pushableEntities = null;
   }

   @Override
   public void lithium$onEntityModifiedCachedBlock(BlockCachingEntity entity, class_2680 newBlockState) {
      if (this.pushableEntities == null) {
         entity.lithium$SetClimbingMobCachingSectionUpdateBehavior(false);
      } else {
         this.updatePushabilityOnCachedStateChange(entity, newBlockState);
      }
   }

   @Unique
   private void updatePushabilityOnCachedStateChange(BlockCachingEntity entity, class_2680 newBlockState) {
      boolean visible = entityPushableHeuristic(newBlockState);
      this.pushableEntities.setVisible((class_1297)entity, visible);
   }

   @Unique
   private void onStartClimbingCachingEntity(class_1297 entity) {
      if (PushableEntityClassGroup.MAYBE_PUSHABLE.contains(entity)) {
         this.pushableEntities.add(entity);
         boolean shouldTrackBlockChanges = PushableEntityClassGroup.CACHABLE_UNPUSHABILITY.contains(entity);
         if (shouldTrackBlockChanges) {
            BlockCachingEntity blockCachingEntity = (BlockCachingEntity)entity;
            this.updatePushabilityOnCachedStateChange(blockCachingEntity, blockCachingEntity.lithium$getCachedFeetBlockState());
            blockCachingEntity.lithium$SetClimbingMobCachingSectionUpdateBehavior(true);
         }
      }
   }

   @Inject(method = "method_31764(Lnet/minecraft/class_5568;)V", at = @At("RETURN"))
   private void onEntityAdded(T entityLike, CallbackInfo ci) {
      if (this.pushableEntities != null) {
         if (!this.field_27249.method_31885()) {
            this.stopFilteringPushableEntities();
         } else {
            this.onStartClimbingCachingEntity((class_1297)entityLike);
            if (this.pushableEntities.totalSize() > this.field_27248.size()) {
               this.stopFilteringPushableEntities();
            }
         }
      }
   }

   @Inject(method = "method_31767(Lnet/minecraft/class_5568;)Z", at = @At("RETURN"))
   private void onEntityRemoved(T entityLike, CallbackInfoReturnable<Boolean> cir) {
      if (this.pushableEntities != null) {
         if (!this.field_27249.method_31885()) {
            this.stopFilteringPushableEntities();
         } else {
            this.pushableEntities.remove((class_1297)entityLike);
         }
      }
   }

   @Unique
   private static boolean entityPushableHeuristic(class_2680 cachedFeetBlockState) {
      return cachedFeetBlockState == null || !cachedFeetBlockState.method_26164(class_3481.field_22414);
   }
}
