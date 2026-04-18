package noobanidus.mods.lootr.common.data;

import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_3917;
import net.minecraft.class_7265;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.MenuBuilder;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import org.jetbrains.annotations.Nullable;

public class LootrInventory implements ILootrInventory {
   private final class_2371<class_1799> contents;
   private ILootrSavedData info;
   private MenuBuilder menuBuilder = null;

   public LootrInventory(ILootrSavedData info, class_2371<class_1799> contents) {
      this.info = info;
      if (!contents.isEmpty()) {
         this.contents = contents;
      } else {
         this.contents = info.buildInitialInventory();
      }
   }

   @Override
   public void setMenuBuilder(MenuBuilder builder) {
      this.menuBuilder = builder;
   }

   @Override
   public class_2487 saveToTag(class_7874 provider) {
      class_2487 result = new class_2487();
      class_1262.method_5426(result, this.contents, provider);
      return result;
   }

   public int method_5439() {
      return this.info.getInfoContainerSize();
   }

   public boolean method_5442() {
      for (class_1799 itemstack : this.contents) {
         if (!itemstack.method_7960()) {
            return false;
         }
      }

      return true;
   }

   public class_1799 method_5438(int index) {
      return (class_1799)this.contents.get(index);
   }

   public class_1799 method_5434(int index, int count) {
      class_1799 itemstack = class_1262.method_5430(this.contents, index, count);
      if (!itemstack.method_7960()) {
         this.method_5431();
      }

      return itemstack;
   }

   public class_1799 method_5441(int index) {
      class_1799 result = class_1262.method_5428(this.contents, index);
      if (!result.method_7960()) {
         this.method_5431();
      }

      return result;
   }

   public void method_5447(int index, class_1799 stack) {
      this.contents.set(index, stack);
      if (stack.method_7947() > this.method_5444()) {
         stack.method_7939(this.method_5444());
      }

      this.method_5431();
   }

   public void method_5431() {
      this.info.markChanged();
   }

   public boolean method_5443(class_1657 player) {
      if (!player.method_37908().method_27983().equals(this.info.getInfoDimension())) {
         return false;
      } else {
         return switch (this.info.getInfoContainer()) {
            case class_2586 blockEntity -> class_1263.method_49105(blockEntity, player);
            case class_7265 containerEntity -> containerEntity.method_42294(player);
            case null, default -> false;
         };
      }
   }

   public void method_5448() {
      this.contents.clear();
      this.method_5431();
   }

   @Override
   public ILootrInfo getInfo() {
      return this.info;
   }

   @Override
   public void setInfo(ILootrSavedData info) {
      this.info = info;
   }

   @Override
   public class_2561 method_5476() {
      class_2561 component = this.info.getInfoDisplayName();
      return (class_2561)(component == null ? class_2561.method_43473() : component);
   }

   @Nullable
   public class_1703 createMenu(int id, class_1661 inventory, class_1657 player) {
      if (this.menuBuilder != null) {
         return this.menuBuilder.build(id, inventory, this, this.method_5439() / 9);
      } else {
         return switch (this.method_5439()) {
            case 9 -> new class_1707(class_3917.field_18664, id, inventory, this, 1);
            case 18 -> new class_1707(class_3917.field_18665, id, inventory, this, 2);
            case 36 -> new class_1707(class_3917.field_18666, id, inventory, this, 4);
            case 45 -> new class_1707(class_3917.field_18667, id, inventory, this, 5);
            case 54 -> class_1707.method_19247(id, inventory, this);
            default -> class_1707.method_19245(id, inventory, this);
         };
      }
   }

   public void method_5435(class_1657 player) {
      class_1263 container = this.info.getInfoContainer();
      if (container != null) {
         container.method_5435(player);
      }
   }

   public void method_5432(class_1657 player) {
      this.method_5431();
      class_1263 container = this.info.getInfoContainer();
      if (container != null) {
         container.method_5432(player);
      }
   }

   @Override
   public class_2371<class_1799> getInventoryContents() {
      return this.contents;
   }
}
