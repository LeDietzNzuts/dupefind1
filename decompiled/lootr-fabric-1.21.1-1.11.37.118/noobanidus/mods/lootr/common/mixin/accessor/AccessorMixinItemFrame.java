package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_1533;
import net.minecraft.class_1799;
import net.minecraft.class_2940;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_1533.class)
public interface AccessorMixinItemFrame {
   @Invoker("method_43271")
   void lootr$onItemChanged(class_1799 var1);

   @Accessor("field_7130")
   static class_2940<class_1799> lootr$getDataItem() {
      throw new UnsupportedOperationException();
   }

   @Accessor("field_22476")
   boolean lootr$isFixed();
}
