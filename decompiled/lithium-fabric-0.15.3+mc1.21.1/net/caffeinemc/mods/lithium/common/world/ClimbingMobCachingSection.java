package net.caffeinemc.mods.lithium.common.world;

import java.util.ArrayList;
import net.caffeinemc.mods.lithium.common.entity.pushable.BlockCachingEntity;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_7927.class_7928;

public interface ClimbingMobCachingSection {
   class_7928 lithium$collectPushableEntities(
      class_1937 var1, class_1297 var2, class_238 var3, EntityPushablePredicate<? super class_1297> var4, ArrayList<class_1297> var5
   );

   void lithium$onEntityModifiedCachedBlock(BlockCachingEntity var1, class_2680 var2);
}
