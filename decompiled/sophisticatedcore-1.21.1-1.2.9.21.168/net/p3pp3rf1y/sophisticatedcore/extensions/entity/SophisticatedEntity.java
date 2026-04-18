package net.p3pp3rf1y.sophisticatedcore.extensions.entity;

import java.util.Collection;
import javax.annotation.Nullable;
import net.minecraft.class_1542;
import net.minecraft.class_2487;

public interface SophisticatedEntity {
   default class_2487 getSophisticatedCustomData() {
      throw new RuntimeException("This should have been implemented via mixin.");
   }

   @Nullable
   default Collection<class_1542> sophisticatedCaptureDrops() {
      throw new RuntimeException("This should have been implemented via mixin.");
   }

   default Collection<class_1542> sophisticatedCaptureDrops(@Nullable Collection<class_1542> value) {
      throw new RuntimeException("This should have been implemented via mixin.");
   }
}
