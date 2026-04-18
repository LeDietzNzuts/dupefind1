package vectorwing.farmersdelight.common.block.entity;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2621;
import net.minecraft.class_2680;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_5561;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.inventory.InvWrapper;

public class CabinetBlockEntity extends class_2621 {
   private class_2371<class_1799> contents = class_2371.method_10213(27, class_1799.field_8037);
   private class_5561 openersCounter = new class_5561() {
      protected void method_31681(class_1937 level, class_2338 pos, class_2680 state) {
         CabinetBlockEntity.this.playSound(state, ModSounds.BLOCK_CABINET_OPEN.get());
         CabinetBlockEntity.this.updateBlockState(state, true);
      }

      protected void method_31683(class_1937 level, class_2338 pos, class_2680 state) {
         CabinetBlockEntity.this.playSound(state, ModSounds.BLOCK_CABINET_CLOSE.get());
         CabinetBlockEntity.this.updateBlockState(state, false);
      }

      protected void method_31682(class_1937 level, class_2338 pos, class_2680 sta, int arg1, int arg2) {
      }

      protected boolean method_31679(class_1657 p_155060_) {
         if (p_155060_.field_7512 instanceof class_1707) {
            class_1263 container = ((class_1707)p_155060_.field_7512).method_7629();
            return container == CabinetBlockEntity.this;
         } else {
            return false;
         }
      }
   };

   public CabinetBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.CABINET.get(), pos, state);
   }

   public static void init() {
      ItemStorage.SIDED.registerForBlockEntity((be, context) -> new InvWrapper(be), ModBlockEntityTypes.CABINET.get());
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      if (!this.method_54872(compound)) {
         class_1262.method_5426(compound, this.contents, registries);
      }
   }

   public void method_11014(class_2487 compound, class_7874 registries) {
      super.method_11014(compound, registries);
      this.contents = class_2371.method_10213(this.method_5439(), class_1799.field_8037);
      if (!this.method_54871(compound)) {
         class_1262.method_5429(compound, this.contents, registries);
      }
   }

   public int method_5439() {
      return 27;
   }

   protected class_2371<class_1799> method_11282() {
      return this.contents;
   }

   protected void method_11281(class_2371<class_1799> itemsIn) {
      this.contents = itemsIn;
   }

   protected class_2561 method_17823() {
      return TextUtils.getTranslation("container.cabinet");
   }

   protected class_1703 method_5465(int id, class_1661 player) {
      return class_1707.method_19245(id, player, this);
   }

   public void method_5435(class_1657 pPlayer) {
      if (this.field_11863 != null && !this.field_11865 && !pPlayer.method_7325()) {
         this.openersCounter.method_31684(pPlayer, this.field_11863, this.method_11016(), this.method_11010());
      }
   }

   public void method_5432(class_1657 pPlayer) {
      if (this.field_11863 != null && !this.field_11865 && !pPlayer.method_7325()) {
         this.openersCounter.method_31685(pPlayer, this.field_11863, this.method_11016(), this.method_11010());
      }
   }

   public void recheckOpen() {
      if (this.field_11863 != null && !this.field_11865) {
         this.openersCounter.method_31686(this.field_11863, this.method_11016(), this.method_11010());
      }
   }

   void updateBlockState(class_2680 state, boolean open) {
      if (this.field_11863 != null) {
         this.field_11863.method_8652(this.method_11016(), (class_2680)state.method_11657(CabinetBlock.OPEN, open), 3);
      }
   }

   private void playSound(class_2680 state, class_3414 sound) {
      class_2382 cabinetFacingVector = ((class_2350)state.method_11654(CabinetBlock.FACING)).method_10163();
      double x = this.field_11867.method_10263() + 0.5 + cabinetFacingVector.method_10263() / 2.0;
      double y = this.field_11867.method_10264() + 0.5 + cabinetFacingVector.method_10264() / 2.0;
      double z = this.field_11867.method_10260() + 0.5 + cabinetFacingVector.method_10260() / 2.0;
      this.field_11863.method_43128(null, x, y, z, sound, class_3419.field_15245, 0.5F, this.field_11863.field_9229.method_43057() * 0.1F + 0.9F);
   }
}
