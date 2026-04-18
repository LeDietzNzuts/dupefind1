package com.talhanation.smallships.world.entity;

import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import com.talhanation.smallships.world.entity.fabric.ModEntityTypesImpl;
import com.talhanation.smallships.world.entity.projectile.CannonBallEntity;
import com.talhanation.smallships.world.entity.ship.BriggEntity;
import com.talhanation.smallships.world.entity.ship.CogEntity;
import com.talhanation.smallships.world.entity.ship.DrakkarEntity;
import com.talhanation.smallships.world.entity.ship.GalleyEntity;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1297;
import net.minecraft.class_1299;

public class ModEntityTypes {
   public static final class_1299<CannonBallEntity> CANNON_BALL = getEntityType(CannonBallEntity.class);
   public static final class_1299<CogEntity> COG = getEntityType(CogEntity.class);
   public static final class_1299<BriggEntity> BRIGG = getEntityType(BriggEntity.class);
   public static final class_1299<GalleyEntity> GALLEY = getEntityType(GalleyEntity.class);
   public static final class_1299<DrakkarEntity> DRAKKAR = getEntityType(DrakkarEntity.class);
   public static final class_1299<GroundCannonEntity> GROUND_CANNON = getEntityType(GroundCannonEntity.class);

   @ExpectPlatform
   @Transformed
   public static <T extends class_1297> class_1299<T> getEntityType(Class<T> entityClass) {
      return ModEntityTypesImpl.getEntityType(entityClass);
   }
}
