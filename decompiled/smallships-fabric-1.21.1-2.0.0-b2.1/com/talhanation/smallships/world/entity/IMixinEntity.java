package com.talhanation.smallships.world.entity;

public interface IMixinEntity {
   float getPrevXRot();

   float getPrevYRot();

   boolean doNotTeleportOnNextPassengerSync();

   void setPreventTeleportOnNextPassengerSync(boolean var1);

   boolean doNotDismountToCoordinates();

   void setPreventDismountToCoordinates(boolean var1);
}
