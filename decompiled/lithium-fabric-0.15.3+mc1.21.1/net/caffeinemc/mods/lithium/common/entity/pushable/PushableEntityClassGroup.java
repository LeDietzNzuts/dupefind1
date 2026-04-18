package net.caffeinemc.mods.lithium.common.entity.pushable;

import net.caffeinemc.mods.lithium.common.entity.EntityClassGroup;
import net.caffeinemc.mods.lithium.common.reflection.ReflectionUtil;
import net.caffeinemc.mods.lithium.common.services.PlatformMappingInformation;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1420;
import net.minecraft.class_1510;
import net.minecraft.class_1531;
import net.minecraft.class_1657;

public class PushableEntityClassGroup {
   public static final EntityClassGroup CACHABLE_UNPUSHABILITY;
   public static final EntityClassGroup MAYBE_PUSHABLE;

   static {
      String remapped_isClimbing = PlatformMappingInformation.INSTANCE
         .mapMethodName("intermediary", "net.minecraft.class_1309", "method_6101", "()Z", "onClimbable");
      String remapped_isPushable = PlatformMappingInformation.INSTANCE
         .mapMethodName("intermediary", "net.minecraft.class_1297", "method_5810", "()Z", "isPushable");
      CACHABLE_UNPUSHABILITY = new EntityClassGroup(
         (entityClass, entityType) -> class_1309.class.isAssignableFrom(entityClass)
            && !class_1657.class.isAssignableFrom(entityClass)
            && !ReflectionUtil.hasMethodOverride(entityClass, class_1309.class, true, remapped_isPushable)
            && !ReflectionUtil.hasMethodOverride(entityClass, class_1309.class, true, remapped_isClimbing)
      );
      MAYBE_PUSHABLE = new EntityClassGroup(
         (entityClass, entityType) -> {
            if (ReflectionUtil.hasMethodOverride(entityClass, class_1297.class, true, remapped_isPushable)) {
               if (class_1510.class.isAssignableFrom(entityClass)) {
                  return false;
               } else if (class_1531.class.isAssignableFrom(entityClass)) {
                  return ReflectionUtil.hasMethodOverride(entityClass, class_1531.class, true, remapped_isPushable);
               } else {
                  return class_1420.class.isAssignableFrom(entityClass)
                     ? ReflectionUtil.hasMethodOverride(entityClass, class_1420.class, true, remapped_isPushable)
                     : true;
               }
            } else {
               return class_1657.class.isAssignableFrom(entityClass);
            }
         }
      );
   }
}
