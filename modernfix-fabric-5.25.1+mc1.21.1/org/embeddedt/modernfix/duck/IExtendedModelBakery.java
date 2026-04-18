package org.embeddedt.modernfix.duck;

import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.class_1091;
import net.minecraft.class_1100;

public interface IExtendedModelBakery {
   void mfix$tick();

   void mfix$finishLoading();

   class_1100 mfix$loadUnbakedModelDynamic(class_1091 var1);

   class_1100 mfix$getMissingModel();

   ReentrantLock mfix$getLock();
}
