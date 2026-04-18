package noobanidus.mods.lootr.common.api.data.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3222;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;

public interface ILootrEntity extends ILootrInfoProvider {
   @Deprecated
   @Override
   default ILootrInfo.LootrInfoType getInfoType() {
      return ILootrInfo.LootrInfoType.CONTAINER_ENTITY;
   }

   default class_1297 asEntity() {
      if (this instanceof class_1297 entity) {
         return entity;
      } else {
         throw new NullPointerException("ILootrEntity implementation is not an Entity and doesn't provide asEntity()!");
      }
   }

   @Override
   default void performOpen(class_3222 player) {
      PlatformAPI.performEntityOpen(this, player);
   }

   @Override
   default void performOpen() {
      PlatformAPI.performEntityOpen(this);
   }

   @Override
   default void performClose(class_3222 player) {
      PlatformAPI.performEntityClose(this, player);
   }

   @Override
   default void performClose() {
      PlatformAPI.performEntityClose(this);
   }

   @Override
   default void performDecay() {
      class_1937 level = this.getInfoLevel();
      if (level != null && !level.method_8608()) {
         boolean replaceWhenDecayed = LootrAPI.shouldReplaceWhenDecayed();
         class_1297 entity = this.asEntity();
         if (replaceWhenDecayed) {
            class_1299<?> type = this.getReplacementEntity();
            if (type != null) {
               class_1297 newCart = type.method_5883(level);
               if (newCart != null) {
                  newCart.method_33574(entity.method_19538());
                  newCart.method_36457(entity.method_36455());
                  newCart.method_36456(entity.method_36454());
                  level.method_8649(newCart);
               }
            }
         }

         entity.method_31472();
      }
   }

   @Override
   default class_243 getParticleCenter() {
      return this.asEntity().method_19538().method_1023(0.5, 0.0, 0.5);
   }
}
