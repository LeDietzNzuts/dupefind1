package noobanidus.mods.lootr.common.api.adapter;

import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public non-sealed interface ILootrItemFrameAdapter<T> extends ILootrAdapter<T> {
   class_2350 getDirection(T var1);

   class_1799 getItem(T var1);

   int getRotation(T var1);

   class_2338 getPos(T var1);

   boolean isFixed(T var1);

   boolean isInvisible(T var1);
}
