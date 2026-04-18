package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import net.minecraft.class_1297;
import net.minecraft.class_1510;
import net.minecraft.class_1528;
import net.minecraft.class_1687;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3481;

public interface BlockInterfaceHelper {
   default float getExplosionResistance(class_2680 state, class_1922 level, class_2338 pos, class_1927 explosion) {
      return ((class_2248)this).method_9520();
   }

   default boolean canEntityDestroy(class_2680 state, class_1922 level, class_2338 pos, class_1297 entity) {
      if (entity instanceof class_1510) {
         return !((class_2248)this).method_9564().method_26164(class_3481.field_17753);
      } else {
         return !(entity instanceof class_1528) && !(entity instanceof class_1687) ? true : state.method_26215() || class_1528.method_6883(state);
      }
   }
}
