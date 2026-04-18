package net.kikoz.mcwfurnitures.storage;

import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.kikoz.mcwfurnitures.init.SoundsInit;
import net.kikoz.mcwfurnitures.objects.TallFurniture;
import net.kikoz.mcwfurnitures.objects.cabinets.Cabinet;
import net.kikoz.mcwfurnitures.objects.cabinets.CabinetHinge;
import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.minecraft.class_1262;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_3908;
import net.minecraft.class_5561;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;

public class FurniturekEntity extends class_2586 implements class_3908, ImplementedInventory {
   private final class_2371<class_1799> inventory = class_2371.method_10213(27, class_1799.field_8037);
   private final class_5561 stateManager = new class_5561() {
      protected void method_31681(class_1937 level, class_2338 pos, class_2680 state) {
         if (!(state.method_26204() instanceof TallFurniture)
            && !(state.method_26204() instanceof Cabinet)
            && !(state.method_26204() instanceof CabinetHinge)
            && !(state.method_26204() instanceof CupboardCounter)) {
            FurniturekEntity.this.playSound(null, level, pos, true, SoundsInit.DRAWER_OPEN, 0.5F);
         } else {
            FurniturekEntity.this.playSound(null, level, pos, true, SoundsInit.CABINET_OPEN, 0.5F);
         }
      }

      protected void method_31683(class_1937 level, class_2338 pos, class_2680 state) {
         if (!(state.method_26204() instanceof TallFurniture)
            && !(state.method_26204() instanceof Cabinet)
            && !(state.method_26204() instanceof CabinetHinge)
            && !(state.method_26204() instanceof CupboardCounter)) {
            FurniturekEntity.this.playSound(null, level, pos, true, SoundsInit.DRAWER_CLOSE, 0.5F);
         } else {
            FurniturekEntity.this.playSound(null, level, pos, true, SoundsInit.CABINET_CLOSE, 0.5F);
         }
      }

      protected void method_31682(class_1937 world, class_2338 pos, class_2680 state, int oldCount, int newCount) {
      }

      protected boolean method_31679(class_1657 player) {
         return player.field_7512 instanceof FurnitureScreenHandler handler ? handler.getInventory() == FurniturekEntity.this : false;
      }
   };

   public FurniturekEntity(class_2338 pos, class_2680 state) {
      super(MacawsFurniture.FURNITURE_BLOCK_ENTITY, pos, state);
   }

   public void method_5435(class_1657 player) {
      if (!this.field_11865 && !player.method_7325()) {
         this.stateManager.method_31684(player, this.field_11863, this.field_11867, this.method_11010());
      }
   }

   public void method_5432(class_1657 player) {
      if (!this.field_11865 && !player.method_7325()) {
         this.stateManager.method_31685(player, this.field_11863, this.field_11867, this.method_11010());
      }
   }

   private void playSound(@Nullable class_1297 entity, class_1937 level, class_2338 pos, boolean open, class_3414 sound, float volume) {
      level.method_45445(entity, pos, sound, class_3419.field_15245, volume, level.field_9229.method_43057() * 0.1F + 0.9F);
   }

   @Override
   public class_2371<class_1799> getItems() {
      return this.inventory;
   }

   public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
      return new FurnitureScreenHandler(syncId, playerInventory, this);
   }

   protected void method_11007(class_2487 nbt, class_7874 registryLookup) {
      super.method_11007(nbt, registryLookup);
      class_1262.method_5426(nbt, this.inventory, registryLookup);
   }

   public class_2561 method_5476() {
      return class_2561.method_43470("Furniture Inventory");
   }

   protected void method_11014(class_2487 nbt, class_7874 registryLookup) {
      super.method_11014(nbt, registryLookup);
      class_1262.method_5429(nbt, this.inventory, registryLookup);
   }
}
