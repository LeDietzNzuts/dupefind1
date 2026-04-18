package vectorwing.farmersdelight.common.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;

public class SyncedBlockEntity extends class_2586 {
   public SyncedBlockEntity(class_2591<?> tileEntityTypeIn, class_2338 pos, class_2680 state) {
      super(tileEntityTypeIn, pos, state);
   }

   @Nullable
   public class_2622 getUpdatePacket() {
      return class_2622.method_38585(this);
   }

   public class_2487 method_16887(class_7874 registries) {
      return this.method_38244(registries);
   }

   protected void inventoryChanged() {
      super.method_5431();
      if (this.field_11863 != null) {
         this.field_11863.method_8413(this.method_11016(), this.method_11010(), this.method_11010(), 2);
      }
   }
}
