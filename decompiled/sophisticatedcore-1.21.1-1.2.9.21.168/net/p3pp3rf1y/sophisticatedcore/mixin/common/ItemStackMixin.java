package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1799;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import net.p3pp3rf1y.sophisticatedcore.extensions.component.SophisticatedMutableDataComponentHolder;
import net.p3pp3rf1y.sophisticatedcore.extensions.item.SophisticatedItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1799.class)
public abstract class ItemStackMixin implements SophisticatedItemStack, SophisticatedMutableDataComponentHolder {
   @Shadow
   @Final
   public class_9335 field_49270;

   @Nullable
   @Override
   public <T> T sophisticatedCore_set(class_9331<? super T> type, @Nullable T value) {
      return (T)this.field_49270.method_57938(type, value);
   }

   @Nullable
   public <T> T method_57824(class_9331<? extends T> type) {
      return (T)this.field_49270.method_57829(type);
   }

   @Nullable
   @Override
   public <T> T sophisticatedCore_remove(class_9331<? extends T> type) {
      return (T)this.field_49270.method_57939(type);
   }
}
