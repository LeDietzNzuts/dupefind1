package vectorwing.farmersdelight.common.block.entity;

import java.util.Optional;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1863;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2487;
import net.minecraft.class_2498;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_7924;
import net.minecraft.class_8786;
import net.minecraft.class_1863.class_7266;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipeInput;
import vectorwing.farmersdelight.common.registry.ModAdvancements;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class CuttingBoardBlockEntity extends SyncedBlockEntity {
   private final ItemStackHandler inventory = this.createHandler();
   private final class_7266<CuttingBoardRecipeInput, CuttingBoardRecipe> quickCheck;
   private boolean isItemCarvingBoard = false;

   public CuttingBoardBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.CUTTING_BOARD.get(), pos, state);
      this.quickCheck = class_1863.method_42302(ModRecipeTypes.CUTTING.get());
   }

   public static void init() {
      ItemStorage.SIDED.registerForBlockEntity(CuttingBoardBlockEntity::getStorage, ModBlockEntityTypes.CUTTING_BOARD.get());
   }

   @NotNull
   public Storage<ItemVariant> getStorage(@Nullable class_2350 side) {
      return this.getInventory();
   }

   public void method_11014(class_2487 compound, class_7874 registries) {
      super.method_11014(compound, registries);
      this.isItemCarvingBoard = compound.method_10577("IsItemCarved");
      this.inventory.deserializeNBT(registries, compound.method_10562("Inventory"));
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      compound.method_10566("Inventory", this.inventory.serializeNBT(registries));
      compound.method_10556("IsItemCarved", this.isItemCarvingBoard);
   }

   public boolean processStoredItemUsingTool(class_1799 toolStack, @Nullable class_1657 player) {
      if (this.isItemCarvingBoard) {
         return false;
      } else {
         Optional<class_8786<CuttingBoardRecipe>> matchingRecipe = this.getMatchingRecipe(toolStack, player);
         matchingRecipe.ifPresent(
            recipe -> {
               for (class_1799 resultStack : ((CuttingBoardRecipe)recipe.comp_1933())
                  .rollResults(
                     this.field_11863.field_9229,
                     class_1890.method_8225(this.field_11863.method_45448(class_7924.field_41265).method_46747(class_1893.field_9130), toolStack)
                  )) {
                  class_2350 direction = ((class_2350)this.method_11010().method_11654(CuttingBoardBlock.FACING)).method_10160();
                  ItemUtils.spawnItemEntity(
                     this.field_11863,
                     resultStack.method_7972(),
                     this.field_11867.method_10263() + 0.5 + direction.method_10148() * 0.2,
                     this.field_11867.method_10264() + 0.2,
                     this.field_11867.method_10260() + 0.5 + direction.method_10165() * 0.2,
                     direction.method_10148() * 0.2F,
                     0.0,
                     direction.method_10165() * 0.2F
                  );
               }

               if (!this.field_11863.field_9236) {
                  toolStack.method_7956(1, (class_3218)this.field_11863, (class_3222)player, item -> {});
               }

               this.playProcessingSound(((CuttingBoardRecipe)recipe.comp_1933()).getSoundEvent().orElse(null), toolStack, this.getStoredItem());
               this.removeItem();
               if (player instanceof class_3222) {
                  ModAdvancements.USE_CUTTING_BOARD.get().trigger((class_3222)player);
               }
            }
         );
         return matchingRecipe.isPresent();
      }
   }

   private Optional<class_8786<CuttingBoardRecipe>> getMatchingRecipe(class_1799 toolStack, @Nullable class_1657 player) {
      Optional<class_8786<CuttingBoardRecipe>> recipe = this.quickCheck
         .method_42303(new CuttingBoardRecipeInput(this.getStoredItem(), toolStack), this.field_11863);
      if (recipe.isPresent()) {
         if (((CuttingBoardRecipe)recipe.get().comp_1933()).getTool().method_8093(toolStack)) {
            return recipe;
         }

         if (player != null) {
            player.method_7353(TextUtils.getTranslation("block.cutting_board.invalid_tool"), true);
         }
      } else if (player != null) {
         player.method_7353(TextUtils.getTranslation("block.cutting_board.invalid_item"), true);
      }

      return Optional.empty();
   }

   public void playProcessingSound(@Nullable class_3414 sound, class_1799 tool, class_1799 boardItem) {
      if (sound != null) {
         this.playSound(sound, 1.0F, 1.0F);
      } else if (tool.method_31573(ConventionalItemTags.SHEAR_TOOLS)) {
         this.playSound(class_3417.field_14975, 1.0F, 1.0F);
      } else if (tool.method_31573(CommonTags.TOOLS_KNIFE)) {
         this.playSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get(), 0.8F, 1.0F);
      } else if (boardItem.method_7909() instanceof class_1747 blockItem) {
         class_2248 block = blockItem.method_7711();
         class_2498 soundType = block.method_9564().method_26231();
         this.playSound(soundType.method_10595(), 1.0F, 0.8F);
      } else {
         this.playSound(class_3417.field_15215, 1.0F, 0.8F);
      }
   }

   public void playSound(class_3414 sound, float volume, float pitch) {
      if (this.field_11863 != null) {
         this.field_11863
            .method_43128(
               null,
               this.field_11867.method_10263() + 0.5F,
               this.field_11867.method_10264() + 0.5F,
               this.field_11867.method_10260() + 0.5F,
               sound,
               class_3419.field_15245,
               volume,
               pitch
            );
      }
   }

   public boolean addItem(class_1799 itemStack) {
      if (this.isEmpty() && !itemStack.method_7960()) {
         this.inventory.setStackInSlot(0, itemStack.method_7971(1));
         this.isItemCarvingBoard = false;
         this.inventoryChanged();
         return true;
      } else {
         return false;
      }
   }

   public boolean carveToolOnBoard(class_1799 tool) {
      if (this.addItem(tool)) {
         this.isItemCarvingBoard = true;
         return true;
      } else {
         return false;
      }
   }

   public class_1799 removeItem() {
      if (!this.isEmpty()) {
         this.isItemCarvingBoard = false;
         class_1799 item = this.inventory.extractItem(0, 1, false);
         this.inventoryChanged();
         return item;
      } else {
         return class_1799.field_8037;
      }
   }

   public ItemHandler getInventory() {
      return this.inventory;
   }

   public class_1799 getStoredItem() {
      return this.inventory.getStackInSlot(0);
   }

   public boolean isEmpty() {
      return this.inventory.getStackInSlot(0).method_7960();
   }

   public boolean isItemCarvingBoard() {
      return this.isItemCarvingBoard;
   }

   public void method_11012() {
      super.method_11012();
   }

   private ItemStackHandler createHandler() {
      return new ItemStackHandler() {
         @Override
         public int getSlotLimit(int slot) {
            return 1;
         }

         @Override
         protected void onContentsChanged(int slot) {
            CuttingBoardBlockEntity.this.inventoryChanged();
         }
      };
   }
}
