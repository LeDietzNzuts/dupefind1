package net.caffeinemc.mods.lithium.common.entity.projectile;

import net.caffeinemc.mods.lithium.common.entity.EntityClassGroup;
import net.caffeinemc.mods.lithium.common.reflection.ReflectionUtil;
import net.caffeinemc.mods.lithium.common.services.PlatformMappingInformation;
import net.minecraft.class_1297;
import net.minecraft.class_1510;
import net.minecraft.class_1665;
import net.minecraft.class_1668;
import net.minecraft.class_1676;
import net.minecraft.class_1678;
import net.minecraft.class_3483;
import net.minecraft.class_8150;
import net.minecraft.class_9236;

public class ProjectileEntityClassGroup {
   public static final EntityClassGroup OPTIMIZED_PROJECTILES;
   public static final EntityClassGroup CAN_MAYBE_BE_HIT_BY_OPTIMIZED_PROJECTILE;

   static {
      String remapped_canHitEntity = PlatformMappingInformation.INSTANCE
         .mapMethodName("intermediary", "net.minecraft.class_1676", "method_26958", "(Lnet/minecraft/class_1297;)Z", "canHitEntity");
      OPTIMIZED_PROJECTILES = new EntityClassGroup((entityClass, entityType) -> {
         Class<?> parentClass = class_1676.class;
         if (class_1668.class.isAssignableFrom(entityClass)) {
            parentClass = class_1668.class;
            if (class_9236.class.isAssignableFrom(entityClass)) {
               parentClass = class_9236.class;
            }
         } else if (class_1665.class.isAssignableFrom(entityClass)) {
            parentClass = class_1665.class;
         } else if (class_1678.class.isAssignableFrom(entityClass)) {
            parentClass = class_1678.class;
         }

         return !ReflectionUtil.hasMethodOverride(entityClass, parentClass, true, remapped_canHitEntity, class_1297.class);
      });
      String remapped_canBeHitByProjectile = PlatformMappingInformation.INSTANCE
         .mapMethodName("intermediary", "net.minecraft.class_1297", "method_49108", "()Z", "canBeHitByProjectile");
      String remapped_isPickable = PlatformMappingInformation.INSTANCE
         .mapMethodName("intermediary", "net.minecraft.class_1297", "method_5863", "()Z", "isPickable");
      CAN_MAYBE_BE_HIT_BY_OPTIMIZED_PROJECTILE = new EntityClassGroup((entityClass, entityType) -> {
         Class<?> parentClass_isPickable = class_1297.class;
         if (class_8150.class == entityClass) {
            return false;
         } else if (ReflectionUtil.hasMethodOverride(entityClass, class_1297.class, true, remapped_canBeHitByProjectile)) {
            return true;
         } else if (class_1510.class == entityClass) {
            return false;
         } else {
            if (class_1676.class.isAssignableFrom(entityClass)) {
               parentClass_isPickable = class_1676.class;
               if (class_1665.class.isAssignableFrom(entityClass)) {
                  parentClass_isPickable = class_1665.class;
               }

               if (entityType.get().method_20210(class_3483.field_51503)) {
                  return true;
               }
            }

            return ReflectionUtil.hasMethodOverride(entityClass, parentClass_isPickable, true, remapped_isPickable);
         }
      });
   }
}
