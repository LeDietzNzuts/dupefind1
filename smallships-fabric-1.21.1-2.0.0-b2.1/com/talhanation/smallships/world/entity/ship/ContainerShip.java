package com.talhanation.smallships.world.entity.ship;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.mixin.container.SimpleContainerAccessor;
import com.talhanation.smallships.world.inventory.ContainerUtility;
import com.talhanation.smallships.world.inventory.ShipContainerMenu;
import java.util.Arrays;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1277;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1690;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3913;
import net.minecraft.class_4838;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5630;
import net.minecraft.class_5712;
import net.minecraft.class_7248;
import net.minecraft.class_7265;
import net.minecraft.class_7924;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_2945.class_9222;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ContainerShip extends Ship implements class_7248, class_7265 {
   public static final class_2940<Integer> CONTAINER_SIZE = class_2945.method_12791(ContainerShip.class, class_2943.field_13327);
   public static final class_2940<Byte> ROWS = class_2945.method_12791(ContainerShip.class, class_2943.field_13319);
   public static final class_2940<Byte> PAGES = class_2945.method_12791(ContainerShip.class, class_2943.field_13319);
   public static final class_2940<Byte> PAGE_INDEX = class_2945.method_12791(ContainerShip.class, class_2943.field_13319);
   public static final class_2940<Byte> CONTAINER_FILL_STATE = class_2945.method_12791(ContainerShip.class, class_2943.field_13319);
   private final int originalContainerSize;
   class_2371<class_1799> itemStacks;
   @Nullable
   private class_5321<class_52> lootTable;
   private long lootTableSeed;
   public final class_3913 containerData = new class_3913() {
      public int method_17390(int index) {
         return switch (index) {
            case 0 -> ContainerShip.this.<Byte>getData(ContainerShip.ROWS);
            case 1 -> ContainerShip.this.<Byte>getData(ContainerShip.PAGES);
            case 2 -> ContainerShip.this.<Byte>getData(ContainerShip.PAGE_INDEX);
            default -> 0;
         };
      }

      public void method_17391(int index, int value) {
         switch (index) {
            case 0:
               ContainerShip.this.setData(ContainerShip.ROWS, (byte)value);
               break;
            case 1:
               ContainerShip.this.setData(ContainerShip.PAGES, (byte)value);
               break;
            case 2:
               ContainerShip.this.setData(ContainerShip.PAGE_INDEX, (byte)value);
         }
      }

      public int method_17389() {
         return 3;
      }
   };

   public ContainerShip(class_1299<? extends class_1690> entityType, class_1937 level, int containerSize) {
      super(entityType, level);
      this.originalContainerSize = containerSize;
      this.updatePaging(this.originalContainerSize);
      this.setData(CONTAINER_SIZE, this.originalContainerSize);
      this.itemStacks = class_2371.method_10213(this.originalContainerSize, class_1799.field_8037);
   }

   @Override
   protected void method_5693(class_9222 builder) {
      super.method_5693(builder);
      builder.method_56912(CONTAINER_SIZE, this.originalContainerSize);
      builder.method_56912(ROWS, (byte)6);
      builder.method_56912(PAGES, (byte)1);
      builder.method_56912(PAGE_INDEX, (byte)0);
      builder.method_56912(CONTAINER_FILL_STATE, (byte)0);
   }

   @Override
   protected void method_5749(@NotNull class_2487 tag) {
      super.method_5749(tag);
      this.readContainerSizeSaveData(tag);
      this.method_42285(tag, this.method_56673());
      this.setContainerFillState(tag.method_10571("ContainerFillState"));
   }

   @Override
   protected void method_5652(@NotNull class_2487 tag) {
      super.method_5652(tag);
      this.addContainerSizeSaveData(tag);
      this.method_42288(tag, this.method_56673());
      tag.method_10567("ContainerFillState", this.getContainerFillState());
   }

   @Override
   public void method_7516(@NotNull class_1282 damageSource) {
      super.method_7516(damageSource);
      this.method_42283(damageSource, this.method_37908(), this);
   }

   public void method_5650(@NotNull class_5529 removalReason) {
      if (!this.method_5770().method_8608() && removalReason.method_31486()) {
         class_1264.method_5452(this.method_37908(), this, this);
      }

      super.method_5650(removalReason);
   }

   @NotNull
   @Override
   public class_1269 method_5688(@NotNull class_1657 player, @NotNull class_1268 interactionHand) {
      if (this.isLocked()) {
         return class_1269.field_5811;
      } else {
         return this.method_5818(player) && !player.method_21823() ? super.method_5688(player, interactionHand) : this.method_42284(player);
      }
   }

   public void method_6722(@NotNull class_1657 player) {
      ContainerUtility.openShipMenu(player, this);
      if (!player.method_37908().method_8608()) {
         this.method_32875(class_5712.field_28176, player);
         class_4838.method_24733(player, true);
      }
   }

   @Nullable
   public class_5321<class_52> method_42276() {
      return this.lootTable;
   }

   public void method_42275(@Nullable class_5321<class_52> lootTable) {
      this.lootTable = lootTable;
   }

   public long method_42277() {
      return this.lootTableSeed;
   }

   public void method_42274(long l) {
      this.lootTableSeed = l;
   }

   @NotNull
   public class_2371<class_1799> method_42278() {
      return this.itemStacks;
   }

   public void method_42273() {
      this.itemStacks.clear();
   }

   public int method_5439() {
      return this.<Integer>getData(CONTAINER_SIZE);
   }

   @NotNull
   public class_1799 method_5438(int i) {
      return this.method_42290(i);
   }

   @NotNull
   public class_1799 method_5434(int i, int j) {
      return this.method_42286(i, j);
   }

   @NotNull
   public class_1799 method_5441(int i) {
      return this.method_42289(i);
   }

   public void method_5447(int i, @NotNull class_1799 itemStack) {
      this.method_42287(i, itemStack);
      this.updateContainerFillState();
   }

   @NotNull
   public class_5630 method_32318(int n) {
      return this.method_42292(n);
   }

   public void method_5431() {
      this.updateContainerFillState();
   }

   public boolean method_5443(@NotNull class_1657 player) {
      return this.method_42294(player);
   }

   public void method_5448() {
      this.method_42293();
   }

   @Nullable
   public class_1703 createMenu(int syncId, @NotNull class_1661 inventory, @NotNull class_1657 player) {
      if (this.lootTable == null || !player.method_7325()) {
         this.method_42291(inventory.field_7546);
         this.method_6722(player);
      }

      return null;
   }

   public void method_42285(@NotNull class_2487 tag, class_7874 levelRegistry) {
      this.method_42273();
      if (tag.method_10573("LootTable", 8)) {
         this.method_42275(class_5321.method_29179(class_7924.field_50079, class_2960.method_60654(tag.method_10558("LootTable"))));
         this.method_42274(tag.method_10537("LootTableSeed"));
      } else {
         ContainerUtility.loadAllItems(tag, this.method_42278(), levelRegistry);
         this.resizeContainer(this.method_5439());
      }
   }

   public void method_42288(@NotNull class_2487 tag, class_7874 levelRegistry) {
      if (this.method_42276() != null) {
         tag.method_10582("LootTable", this.method_42276().method_29177().toString());
         if (this.method_42277() != 0L) {
            tag.method_10544("LootTableSeed", this.method_42277());
         }
      } else {
         ContainerUtility.saveAllItems(tag, this.method_42278(), levelRegistry);
      }
   }

   public void readContainerSizeSaveData(class_2487 tag) {
      if (!tag.method_10573("ContainerSize", 3)) {
         tag.method_10569("ContainerSize", this.originalContainerSize);
      }

      int containerSize = tag.method_10550("ContainerSize");
      if (containerSize == 0) {
         containerSize = this.originalContainerSize;
      }

      this.updatePaging(containerSize);
      this.setData(CONTAINER_SIZE, containerSize);
      if (!this.method_37908().method_8608()) {
         this.method_37908()
            .method_18456()
            .stream()
            .filter(player -> player.field_7512 instanceof ShipContainerMenu shipContainerMenu && shipContainerMenu.getContainerShip().equals(this))
            .map(player -> (class_3222)player)
            .forEach(class_3222::method_7346);
      }
   }

   public void addContainerSizeSaveData(class_2487 tag) {
      tag.method_10569("ContainerSize", this.<Integer>getData(CONTAINER_SIZE));
   }

   private void updatePaging(int containerSize) {
      if (containerSize % 9 != 0) {
         throw new IllegalArgumentException(
            "ShipContainerMenu can not be created with size " + containerSize + ". Make sure to use a container size that is divisible by 9."
         );
      } else {
         int allRows = containerSize / 9;
         int rowsPerPage = 6;
         int pages = 1;

         while (true) {
            while (pages * rowsPerPage < allRows) {
               if (++pages > 127) {
                  throw new IllegalArgumentException(
                     "ShipContainerMenu can not be created with size "
                        + containerSize
                        + ". Make sure to use a container size that requires less or equal to 128 pages. Current amount of required pages: "
                        + pages
                  );
               }
            }

            if (pages * rowsPerPage == allRows) {
               this.setData(ROWS, (byte)rowsPerPage);
               this.setData(PAGES, (byte)pages);
               this.setData(PAGE_INDEX, (byte)0);
               return;
            }

            rowsPerPage--;
            pages = 1;
         }
      }
   }

   protected void updateContainerFillState() {
      int percent = (int)this.getInvFillStateInPercent();
      this.setContainerFillState((byte)percent);
   }

   public byte getInvFillState() {
      return (Byte)this.field_6011.method_12789(CONTAINER_FILL_STATE);
   }

   public double getInvFillStateInPercent() {
      int size = this.method_42278().size();
      double invFillStateInPercent = this.method_42278()
            .stream()
            .map(itemStack -> !itemStack.method_7960() ? (double)itemStack.method_7947() / itemStack.method_7914() : 0.0)
            .reduce(0.0, Double::sum)
         / size;
      return invFillStateInPercent * 100.0;
   }

   public void resizeContainer(int containerSize) {
      this.itemStacks = resizeItemStacks(this, containerSize);
   }

   private static class_2371<class_1799> resizeItemStacks(class_7265 containerEntity, int containerSize) {
      class_1799[] oldItemStacks = (class_1799[])containerEntity.method_42278().toArray(class_1799[]::new);
      class_1277 newContainer;
      if (containerSize < oldItemStacks.length) {
         newContainer = new class_1277(Arrays.copyOfRange(oldItemStacks, 0, containerSize));
         oldItemStacks = Arrays.stream(Arrays.copyOfRange(oldItemStacks, containerSize, oldItemStacks.length))
            .filter(stack -> !stack.method_7960())
            .toArray(class_1799[]::new);
         class_1277 leftoverContainer = new class_1277(oldItemStacks.length);

         for (class_1799 oldItemStack : oldItemStacks) {
            leftoverContainer.method_5491(newContainer.method_5491(oldItemStack));
         }

         if (!leftoverContainer.method_5442()) {
            class_1264.method_5452(containerEntity.method_37908(), (class_1297)containerEntity, leftoverContainer);
         }
      } else {
         newContainer = new class_1277(containerSize);

         for (int i = 0; i < containerSize; i++) {
            if (i < oldItemStacks.length) {
               newContainer.method_5447(i, oldItemStacks[i]);
            }
         }
      }

      return ((SimpleContainerAccessor)newContainer).getItems();
   }

   public byte getContainerFillState() {
      return (Byte)this.field_6011.method_12789(CONTAINER_FILL_STATE);
   }

   public void setContainerFillState(byte b) {
      this.field_6011.method_12778(CONTAINER_FILL_STATE, b);
   }

   public float getContainerModifier() {
      return ((Double)SmallShipsConfig.Common.shipGeneralContainerModifier.get()).floatValue() * (this.getContainerFillState() - -128) / 255.0F;
   }
}
