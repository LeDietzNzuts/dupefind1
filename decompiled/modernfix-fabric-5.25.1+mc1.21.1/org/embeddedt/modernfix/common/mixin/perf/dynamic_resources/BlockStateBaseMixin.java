package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import java.lang.ref.SoftReference;
import net.minecraft.class_1087;
import net.minecraft.class_4970.class_4971;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IModelHoldingBlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_4971.class)
@ClientOnlyMixin
public class BlockStateBaseMixin implements IModelHoldingBlockState {
   private volatile SoftReference<class_1087> mfix$model;

   @Override
   public class_1087 mfix$getModel() {
      SoftReference<class_1087> ref = this.mfix$model;
      return ref != null ? ref.get() : null;
   }

   @Override
   public void mfix$setModel(class_1087 model) {
      this.mfix$model = model != null ? new SoftReference<>(model) : null;
   }
}
