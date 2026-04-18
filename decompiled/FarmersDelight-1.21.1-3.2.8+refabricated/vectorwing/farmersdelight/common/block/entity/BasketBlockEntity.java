package vectorwing.farmersdelight.common.block.entity;

import java.util.function.BooleanSupplier;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.class_1262;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2621;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.block.BasketBlock;
import vectorwing.farmersdelight.common.block.entity.inventory.BasketInvWrapper;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class BasketBlockEntity extends class_2621 implements Basket {
   private class_2371<class_1799> items = class_2371.method_10213(27, class_1799.field_8037);
   private int transferCooldown = -1;

   public BasketBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.BASKET.get(), pos, state);
   }

   public static void init() {
      ItemStorage.SIDED.registerForBlockEntity((be, context) -> new BasketInvWrapper(be), ModBlockEntityTypes.BASKET.get());
   }

   protected void method_11014(class_2487 compound, class_7874 registries) {
      super.method_11014(compound, registries);
      this.items = class_2371.method_10213(this.method_5439(), class_1799.field_8037);
      if (!this.method_54871(compound)) {
         class_1262.method_5429(compound, this.items, registries);
      }

      this.transferCooldown = compound.method_10550("TransferCooldown");
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      if (!this.method_54872(compound)) {
         class_1262.method_5426(compound, this.items, registries);
      }

      compound.method_10569("TransferCooldown", this.transferCooldown);
   }

   public int method_5439() {
      return this.items.size();
   }

   public class_1799 method_5434(int index, int count) {
      this.method_54873(null);
      return class_1262.method_5430(this.method_11282(), index, count);
   }

   public void method_5447(int index, class_1799 stack) {
      this.method_54873(null);
      this.method_11282().set(index, stack);
      if (stack.method_7947() > this.method_5444()) {
         stack.method_7939(this.method_5444());
      }
   }

   protected class_2561 method_17823() {
      return TextUtils.getTranslation("container.basket");
   }

   protected class_2371<class_1799> method_11282() {
      return this.items;
   }

   protected void method_11281(class_2371<class_1799> itemsIn) {
      this.items = itemsIn;
   }

   protected class_1703 method_5465(int id, class_1661 player) {
      return class_1707.method_19245(id, player, this);
   }

   @Override
   public void setCooldown(int ticks) {
      this.transferCooldown = ticks;
   }

   @Override
   public boolean isOnCooldown() {
      return this.transferCooldown > 0;
   }

   @Override
   public boolean isOnCustomCooldown() {
      return this.transferCooldown > 8;
   }

   @Override
   public void tryTransfer(BooleanSupplier transfer) {
      if (this.field_11863 != null && !this.field_11863.field_9236 && !this.isOnCooldown() && (Boolean)this.method_11010().method_11654(class_2741.field_12515)
         )
       {
         boolean flag = false;
         if (!this.isFull()) {
            flag = transfer.getAsBoolean();
         }

         if (flag) {
            this.setCooldown(8);
            this.method_5431();
         }
      }
   }

   protected boolean isFull() {
      for (class_1799 itemstack : this.items) {
         if (itemstack.method_7960() || itemstack.method_7947() != itemstack.method_7914()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public double getLevelX() {
      return this.field_11867.method_10263() + 0.5;
   }

   @Override
   public double getLevelY() {
      return this.field_11867.method_10264() + 0.5;
   }

   @Override
   public double getLevelZ() {
      return this.field_11867.method_10260() + 0.5;
   }

   public static void pushItemsTick(class_1937 level, class_2338 pos, class_2680 state, BasketBlockEntity blockEntity) {
      blockEntity.transferCooldown--;
      if (!blockEntity.isOnCooldown()) {
         blockEntity.setCooldown(0);
         int facing = ((class_2350)state.method_11654(BasketBlock.FACING)).method_10146();
         blockEntity.tryTransfer(() -> blockEntity.collectItems(level, facing));
      }
   }
}
