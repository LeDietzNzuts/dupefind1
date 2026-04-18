package com.talhanation.smallships.world.entity.fabric;

import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import com.talhanation.smallships.world.entity.projectile.CannonBallEntity;
import com.talhanation.smallships.world.entity.ship.BriggEntity;
import com.talhanation.smallships.world.entity.ship.CogEntity;
import com.talhanation.smallships.world.entity.ship.DrakkarEntity;
import com.talhanation.smallships.world.entity.ship.GalleyEntity;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_1299.class_1300;

public class ModEntityTypesImpl {
   private static final Map<Class<? extends class_1297>, class_1299<? extends class_1297>> entries = new HashMap<>();

   public static <T extends class_1297> class_1299<T> getEntityType(Class<T> entityClass) {
      return (class_1299<T>)entries.get(entityClass);
   }

   private static <T extends class_1297> class_1299<T> register(String id, class_1299<T> type) {
      return (class_1299<T>)class_2378.method_10230(class_7923.field_41177, class_2960.method_60655("smallships", id), type);
   }

   static {
      entries.put(
         CannonBallEntity.class,
         register(
            "cannon_ball",
            class_1300.method_5903(CannonBallEntity::factory, class_1311.field_17715).method_17687(0.25F, 0.25F).method_27299(20).method_27300(10).build()
         )
      );
      entries.put(
         GroundCannonEntity.class,
         register(
            "ground_cannon", class_1300.method_5903(GroundCannonEntity::factory, class_1311.field_17715).method_17687(0.85F, 0.75F).method_27299(20).build()
         )
      );
      entries.put(
         CogEntity.class,
         register("cog", class_1300.method_5903(CogEntity::new, class_1311.field_17715).method_17687(3.5F, 1.25F).method_27299(20).method_27300(10).build())
      );
      entries.put(
         BriggEntity.class,
         register("brigg", class_1300.method_5903(BriggEntity::new, class_1311.field_17715).method_17687(3.5F, 1.25F).method_27299(20).method_27300(10).build())
      );
      entries.put(
         GalleyEntity.class,
         register(
            "galley", class_1300.method_5903(GalleyEntity::new, class_1311.field_17715).method_17687(3.5F, 1.25F).method_27299(20).method_27300(10).build()
         )
      );
      entries.put(
         DrakkarEntity.class,
         register(
            "drakkar", class_1300.method_5903(DrakkarEntity::new, class_1311.field_17715).method_17687(3.5F, 1.25F).method_27299(20).method_27300(10).build()
         )
      );
   }
}
