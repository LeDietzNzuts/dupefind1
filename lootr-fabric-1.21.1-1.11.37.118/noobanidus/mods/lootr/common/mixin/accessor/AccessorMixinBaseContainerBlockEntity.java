package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_1273;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2624;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_2624.class)
public interface AccessorMixinBaseContainerBlockEntity {
   @Invoker("method_11282")
   class_2371<class_1799> invokeGetItems();

   @Accessor("field_12045")
   class_1273 getLockKey();

   @Accessor("field_12045")
   void setLockKey(class_1273 var1);
}
