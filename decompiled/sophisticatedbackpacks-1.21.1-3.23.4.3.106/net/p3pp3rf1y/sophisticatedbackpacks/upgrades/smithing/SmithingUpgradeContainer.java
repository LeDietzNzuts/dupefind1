package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.smithing;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1734;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3914;
import net.minecraft.class_3956;
import net.minecraft.class_4862;
import net.minecraft.class_8047;
import net.minecraft.class_9697;
import net.minecraft.class_8047.class_8049;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class SmithingUpgradeContainer extends UpgradeContainerBase<SmithingUpgradeWrapper, SmithingUpgradeContainer> implements ICraftingContainer {
   private static final String DATA_SHIFT_CLICK_INTO_STORAGE = "shiftClickIntoStorage";
   private final class_1735 resultSlot;
   private Runnable onResultChanged = () -> {};
   private final SmithingUpgradeContainer.PersistableSmithingMenu smithingMenuDelegate;

   public SmithingUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      SmithingUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<SmithingUpgradeWrapper, SmithingUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      this.smithingMenuDelegate = new SmithingUpgradeContainer.PersistableSmithingMenu(new class_1661(player));
      this.slots.add(this.smithingMenuDelegate.method_7611(0));
      this.slots.add(this.smithingMenuDelegate.method_7611(1));
      this.slots.add(this.smithingMenuDelegate.method_7611(2));
      this.resultSlot = this.smithingMenuDelegate.method_7611(3);
      this.slots.add(this.resultSlot);
      this.smithingMenuDelegate.method_24928();
   }

   public void setOnResultChangedHandler(Runnable onResultChanged) {
      this.onResultChanged = onResultChanged;
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("shiftClickIntoStorage")) {
         this.setShiftClickIntoStorage(data.method_10577("shiftClickIntoStorage"));
      }
   }

   public void setUpgradeWrapper(IUpgradeWrapper updatedUpgradeWrapper) {
      super.setUpgradeWrapper(updatedUpgradeWrapper);
      this.smithingMenuDelegate.method_24928();
   }

   public boolean shouldShiftClickIntoStorage() {
      return ((SmithingUpgradeWrapper)this.upgradeWrapper).shouldShiftClickIntoStorage();
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      ((SmithingUpgradeWrapper)this.upgradeWrapper).setShiftClickIntoStorage(shiftClickIntoStorage);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shiftClickIntoStorage", shiftClickIntoStorage));
   }

   public boolean mergeIntoStorageFirst(class_1735 slot) {
      return !(slot instanceof class_1734) || this.shouldShiftClickIntoStorage();
   }

   public boolean allowsPickupAll(class_1735 slot) {
      return slot != this.resultSlot;
   }

   public class_1735 getTemplateSlot() {
      return this.smithingMenuDelegate.method_7611(0);
   }

   public class_1735 getBaseSlot() {
      return this.smithingMenuDelegate.method_7611(1);
   }

   public class_1735 getAdditionalSlot() {
      return this.smithingMenuDelegate.method_7611(2);
   }

   public class_1735 getResultSlot() {
      return this.smithingMenuDelegate.method_7611(3);
   }

   public List<class_1735> getRecipeSlots() {
      return List.of(this.getTemplateSlot(), this.getBaseSlot(), this.getAdditionalSlot());
   }

   public class_1263 getCraftMatrix() {
      return this.smithingMenuDelegate.getInputSlots();
   }

   public void setRecipeUsed(class_2960 recipeId) {
      this.smithingMenuDelegate.setSelectedRecipe(recipeId);
   }

   public class_3956<?> getRecipeType() {
      return class_3956.field_25388;
   }

   private class PersistableSmithingMenu extends class_4862 {
      public PersistableSmithingMenu(class_1661 playerInventory) {
         super(0, playerInventory, class_3914.method_17392(playerInventory.field_7546.method_37908(), playerInventory.field_7546.method_24515()));
      }

      protected void method_48354(class_8047 itemCombinerMenuSlotDefinition) {
         for (final class_8049 slotDefinition : itemCombinerMenuSlotDefinition.method_48368()) {
            this.method_7621(
               new SlotSuppliedHandler(((SmithingUpgradeWrapper)SmithingUpgradeContainer.this.upgradeWrapper)::getInventory, slotDefinition.comp_1204(), 0, 0) {
                  public void method_7668() {
                     super.method_7668();
                     PersistableSmithingMenu.this.method_7609(PersistableSmithingMenu.this.field_22480);
                  }

                  public boolean method_7680(class_1799 p_267156_) {
                     return slotDefinition.comp_1207().test(p_267156_);
                  }
               }
            );
         }
      }

      protected class_1277 method_48358(int size) {
         return new class_1277(size) {
            public void method_5431() {
               super.method_5431();
               PersistableSmithingMenu.this.method_7609(this);
            }

            public class_1799 method_5438(int pIndex) {
               return ((SmithingUpgradeWrapper)SmithingUpgradeContainer.this.upgradeWrapper).getInventory().getStackInSlot(pIndex);
            }

            public void method_5447(int pIndex, class_1799 pStack) {
               ((SmithingUpgradeWrapper)SmithingUpgradeContainer.this.upgradeWrapper).getInventory().setStackInSlot(pIndex, pStack);
            }
         };
      }

      protected void method_48355(class_8047 slotDefinition) {
         this.method_7621(
            new class_1735(
               this.field_22479,
               slotDefinition.method_48366().comp_1204(),
               slotDefinition.method_48366().comp_1205(),
               slotDefinition.method_48366().comp_1206()
            ) {
               public boolean method_7680(class_1799 stack) {
                  return false;
               }

               public boolean method_7674(class_1657 player) {
                  return PersistableSmithingMenu.this.method_24927(player, this.method_7681());
               }

               public void method_7667(class_1657 player, class_1799 stack) {
                  PersistableSmithingMenu.this.method_24923(player, stack);
               }

               public void method_7668() {
                  super.method_7668();
                  SmithingUpgradeContainer.this.onResultChanged.run();
               }
            }
         );
      }

      public void method_7609(class_1263 pInventory) {
         this.method_24928();
         SmithingUpgradeContainer.this.onResultChanged.run();
      }

      public class_1263 getInputSlots() {
         return this.field_22480;
      }

      public void setSelectedRecipe(class_2960 recipeId) {
         class_9697 smithingRecipeInput = new class_9697(
            SmithingUpgradeContainer.this.getTemplateSlot().method_7677(),
            SmithingUpgradeContainer.this.getBaseSlot().method_7677(),
            SmithingUpgradeContainer.this.getAdditionalSlot().method_7677()
         );
         RecipeHelper.safeGetRecipeFor(class_3956.field_25388, smithingRecipeInput, recipeId).ifPresent(recipe -> this.field_25386 = recipe);
      }
   }
}
