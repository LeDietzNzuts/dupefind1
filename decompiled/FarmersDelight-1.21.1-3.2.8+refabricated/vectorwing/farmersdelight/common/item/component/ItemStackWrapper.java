package vectorwing.farmersdelight.common.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.class_1799;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public class ItemStackWrapper {
   public static final ItemStackWrapper EMPTY = new ItemStackWrapper(class_1799.field_8037);
   public static final Codec<ItemStackWrapper> CODEC = class_1799.field_49266.xmap(ItemStackWrapper::new, ItemStackWrapper::getStack);
   public static final class_9139<class_9129, ItemStackWrapper> STREAM_CODEC = class_1799.field_49268
      .method_56432(ItemStackWrapper::new, itemStackWrapper -> itemStackWrapper.itemStack);
   private final class_1799 itemStack;
   private final int hashCode;

   public ItemStackWrapper(class_1799 stack) {
      this.itemStack = stack;
      this.hashCode = class_1799.method_57355(stack);
   }

   public class_1799 getStack() {
      return this.itemStack;
   }

   @Override
   public int hashCode() {
      return this.hashCode;
   }

   @Override
   public boolean equals(Object object) {
      return object instanceof ItemStackWrapper itemStackWrapper ? class_1799.method_7973(this.itemStack, itemStackWrapper.getStack()) : false;
   }
}
