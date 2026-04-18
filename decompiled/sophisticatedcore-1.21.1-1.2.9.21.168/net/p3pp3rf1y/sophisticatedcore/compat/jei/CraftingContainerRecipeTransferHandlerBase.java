package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.common.transfer.RecipeTransferOperationsResult;
import mezz.jei.common.transfer.RecipeTransferUtil;
import mezz.jei.common.transfer.TransferOperation;
import mezz.jei.common.util.StringUtil;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3917;
import net.minecraft.class_7923;
import net.minecraft.class_8786;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public abstract class CraftingContainerRecipeTransferHandlerBase<C extends StorageContainerMenuBase<?>, R extends class_8786<? extends class_1860<?>>>
   implements IRecipeTransferHandler<C, R> {
   private final IRecipeTransferHandlerHelper handlerHelper;
   private final IStackHelper stackHelper;

   protected CraftingContainerRecipeTransferHandlerBase(IRecipeTransferHandlerHelper handlerHelper, IStackHelper stackHelper) {
      this.handlerHelper = handlerHelper;
      this.stackHelper = stackHelper;
   }

   public Optional<class_3917<C>> getMenuType() {
      return Optional.empty();
   }

   @Nullable
   public IRecipeTransferError transferRecipe(C container, R recipe, IRecipeSlotsView recipeSlots, class_1657 player, boolean maxTransfer, boolean doTransfer) {
      Optional<? extends UpgradeContainerBase<?, ?>> potentialCraftingContainer = container.getOpenOrFirstCraftingContainer(recipe.comp_1933().method_17716());
      if (potentialCraftingContainer.isEmpty()) {
         return this.handlerHelper.createInternalError();
      } else {
         UpgradeContainerBase<?, ?> openOrFirstCraftingContainer = (UpgradeContainerBase<?, ?>)potentialCraftingContainer.get();
         List<class_1735> craftingSlots = Collections.unmodifiableList(
            openOrFirstCraftingContainer instanceof ICraftingContainer cc ? cc.getRecipeSlots() : Collections.emptyList()
         );
         List<class_1735> inventorySlots = container.realInventorySlots.stream().filter(s -> s.method_7674(player)).toList();
         if (!this.validateTransferInfo(container, craftingSlots, inventorySlots)) {
            return this.handlerHelper.createInternalError();
         } else {
            List<IRecipeSlotView> inputItemSlotViews = recipeSlots.getSlotViews(RecipeIngredientRole.INPUT);
            if (!this.validateRecipeView(container, craftingSlots, inputItemSlotViews)) {
               return this.handlerHelper.createInternalError();
            } else {
               CraftingContainerRecipeTransferHandlerBase.InventoryState inventoryState = this.getInventoryState(
                  craftingSlots, inventorySlots, player, container
               );
               if (inventoryState == null) {
                  return this.handlerHelper.createInternalError();
               } else {
                  int inputCount = inputItemSlotViews.size();
                  if (!inventoryState.hasRoom(inputCount)) {
                     class_2561 message = class_2561.method_43471("jei.tooltip.error.recipe.transfer.inventory.full");
                     return this.handlerHelper.createUserErrorWithTooltip(message);
                  } else {
                     RecipeTransferOperationsResult transferOperations = RecipeTransferUtil.getRecipeTransferOperations(
                        this.stackHelper, inventoryState.availableItemStacks, inputItemSlotViews, craftingSlots
                     );
                     if (!transferOperations.missingItems.isEmpty()) {
                        class_2561 message = class_2561.method_43471("jei.tooltip.error.recipe.transfer.missing");
                        return this.handlerHelper.createUserErrorForMissingSlots(message, transferOperations.missingItems);
                     } else if (!RecipeTransferUtil.validateSlots(player, transferOperations.results, craftingSlots, inventorySlots)) {
                        return this.handlerHelper.createInternalError();
                     } else {
                        List<Integer> craftingSlotIndexes = craftingSlots.stream().map(s -> s.field_7874).sorted().toList();
                        List<Integer> inventorySlotIndexes = inventorySlots.stream().map(s -> s.field_7874).sorted().toList();
                        if (doTransfer) {
                           if (!openOrFirstCraftingContainer.isOpen()) {
                              container.getOpenContainer().ifPresent(c -> {
                                 c.setIsOpen(false);
                                 container.setOpenTabId(-1);
                              });
                              openOrFirstCraftingContainer.setIsOpen(true);
                              container.setOpenTabId(openOrFirstCraftingContainer.getUpgradeContainerId());
                           }

                           class_2960 recipeTypeId = class_7923.field_41188.method_10221(recipe.comp_1933().method_17716());
                           if (recipeTypeId != null) {
                              TransferRecipePayload packet = new TransferRecipePayload(
                                 recipe.comp_1932(),
                                 recipeTypeId,
                                 this.toMap(transferOperations.results, container),
                                 craftingSlotIndexes,
                                 inventorySlotIndexes,
                                 maxTransfer
                              );
                              PacketDistributor.sendToServer(packet);
                           }
                        }

                        return null;
                     }
                  }
               }
            }
         }
      }
   }

   private Map<Integer, Integer> toMap(List<TransferOperation> transferOperations, C container) {
      Map<Integer, Integer> ret = new HashMap<>();
      transferOperations.forEach(to -> ret.put(to.craftingSlot(container).field_7874, to.inventorySlot(container).field_7874));
      return ret;
   }

   private boolean validateTransferInfo(C container, List<class_1735> craftingSlots, List<class_1735> inventorySlots) {
      Collection<Integer> craftingSlotIndexes = this.slotIndexes(craftingSlots);
      Collection<Integer> inventorySlotIndexes = this.slotIndexes(inventorySlots);
      ArrayList<class_1735> allSlots = new ArrayList<>(container.realInventorySlots);
      allSlots.addAll(container.upgradeSlots);
      Collection<Integer> containerSlotIndexes = this.slotIndexes(allSlots);
      if (!containerSlotIndexes.containsAll(craftingSlotIndexes)) {
         SophisticatedCore.LOGGER
            .error(
               "Recipe Transfer helper {} does not work for container {}. The Recipes Transfer Helper references crafting slot indexes [{}] that are not found in the inventory container slots [{}]",
               new Object[]{this.getClass(), container.getClass(), StringUtil.intsToString(craftingSlotIndexes), StringUtil.intsToString(containerSlotIndexes)}
            );
         return false;
      } else if (!containerSlotIndexes.containsAll(inventorySlotIndexes)) {
         SophisticatedCore.LOGGER
            .error(
               "Recipe Transfer helper {} does not work for container {}. The Recipes Transfer Helper references inventory slot indexes [{}] that are not found in the inventory container slots [{}]",
               new Object[]{
                  this.getClass(), container.getClass(), StringUtil.intsToString(inventorySlotIndexes), StringUtil.intsToString(containerSlotIndexes)
               }
            );
         return false;
      } else {
         return true;
      }
   }

   private boolean validateRecipeView(C container, List<class_1735> craftingSlots, List<IRecipeSlotView> inputSlots) {
      if (inputSlots.size() > craftingSlots.size()) {
         SophisticatedCore.LOGGER
            .error(
               "Recipe View {} does not work for container {}. The Recipe View has more input slots ({}) than the number of inventory crafting slots ({})",
               new Object[]{this.getClass(), container.getClass(), inputSlots.size(), craftingSlots.size()}
            );
         return false;
      } else {
         return true;
      }
   }

   @Nullable
   private CraftingContainerRecipeTransferHandlerBase.InventoryState getInventoryState(
      Collection<class_1735> craftingSlots, Collection<class_1735> inventorySlots, class_1657 player, C container
   ) {
      Map<class_1735, class_1799> availableItemStacks = new HashMap<>();
      int filledCraftSlotCount = 0;
      int emptySlotCount = 0;

      for (class_1735 slot : craftingSlots) {
         class_1799 stack = slot.method_7677();
         if (!stack.method_7960()) {
            if (!slot.method_7674(player)) {
               SophisticatedCore.LOGGER
                  .error(
                     "Recipe Transfer helper {} does not work for container {}. The Player is not able to move items out of Crafting Slot number {}",
                     new Object[]{this.getClass(), container.getClass(), slot.field_7874}
                  );
               return null;
            }

            filledCraftSlotCount++;
            availableItemStacks.put(slot, stack.method_7972());
         }
      }

      for (class_1735 slotx : inventorySlots) {
         class_1799 stack = slotx.method_7677();
         if (!stack.method_7960()) {
            if (!slotx.method_7674(player)) {
               SophisticatedCore.LOGGER
                  .error(
                     "Recipe Transfer helper {} does not work for container {}. The Player is not able to move items out of Inventory Slot number {}",
                     new Object[]{this.getClass(), container.getClass(), slotx.field_7874}
                  );
               return null;
            }

            availableItemStacks.put(slotx, stack.method_7972());
         } else {
            emptySlotCount++;
         }
      }

      return new CraftingContainerRecipeTransferHandlerBase.InventoryState(availableItemStacks, filledCraftSlotCount, emptySlotCount);
   }

   private Set<Integer> slotIndexes(Collection<class_1735> slots) {
      return slots.stream().map(s -> s.field_7874).collect(Collectors.toSet());
   }

   public record InventoryState(Map<class_1735, class_1799> availableItemStacks, int filledCraftSlotCount, int emptySlotCount) {
      public boolean hasRoom(int inputCount) {
         return this.filledCraftSlotCount - inputCount <= this.emptySlotCount;
      }
   }
}
