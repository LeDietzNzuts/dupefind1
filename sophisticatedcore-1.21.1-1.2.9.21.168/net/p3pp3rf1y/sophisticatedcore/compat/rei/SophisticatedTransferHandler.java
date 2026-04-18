package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.List;
import me.shedaniel.rei.api.client.ClientHelper;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler.ApplicabilityResult;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler.Context;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler.Result;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler.MissingInputRenderer;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.impl.client.transfer.SimpleTransferHandlerImpl;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_2561;
import net.minecraft.class_3956;
import net.minecraft.class_465;
import net.minecraft.class_518;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class SophisticatedTransferHandler<C extends StorageContainerMenuBase<?>, D extends Display> implements SimpleTransferHandler {
   private final Class<? extends C> containerClass;
   private final CategoryIdentifier<D> categoryIdentifier;
   private final class_3956<? extends class_1860<?>> recipeType;

   public static <C extends StorageContainerMenuBase<?>> TransferHandler crafting(Class<? extends C> containerClass) {
      return new SophisticatedTransferHandler(containerClass, BuiltinPlugin.CRAFTING, class_3956.field_17545);
   }

   public SophisticatedTransferHandler(
      Class<? extends C> containerClass, CategoryIdentifier<D> categoryIdentifier, class_3956<? extends class_1860<?>> recipeType
   ) {
      this.containerClass = containerClass;
      this.categoryIdentifier = categoryIdentifier;
      this.recipeType = recipeType;
   }

   public ApplicabilityResult checkApplicable(Context context) {
      if (this.containerClass.isInstance(context.getMenu())
         && this.categoryIdentifier.equals(context.getDisplay().getCategoryIdentifier())
         && context.getContainerScreen() != null) {
         if (context.isActuallyCrafting()) {
            C storageContainerMenuBase = (C)context.getMenu();
            storageContainerMenuBase.getOpenOrFirstCraftingContainer(this.recipeType).ifPresent(openOrFirstCraftingContainer -> {
               if (!openOrFirstCraftingContainer.isOpen()) {
                  storageContainerMenuBase.getOpenContainer().ifPresent(c -> {
                     c.setIsOpen(false);
                     storageContainerMenuBase.setOpenTabId(-1);
                  });
                  openOrFirstCraftingContainer.setIsOpen(true);
                  storageContainerMenuBase.setOpenTabId(openOrFirstCraftingContainer.getUpgradeContainerId());
               }
            });
         }

         return ApplicabilityResult.createApplicable();
      } else {
         return ApplicabilityResult.createNotApplicable();
      }
   }

   public Iterable<SlotAccessor> getInputSlots(Context context) {
      StorageContainerMenuBase<?> storageContainerMenuBase = (StorageContainerMenuBase<?>)context.getMenu();
      return storageContainerMenuBase.getOpenOrFirstCraftingContainer(this.recipeType)
         .map(c -> ((ICraftingContainer)c).getRecipeSlots().stream().map(SophisticatedSlotAccessor::fromSlot).toList())
         .orElse(List.of());
   }

   public Iterable<SlotAccessor> getInventorySlots(Context context) {
      StorageContainerMenuBase<?> storageContainerMenuBase = (StorageContainerMenuBase<?>)context.getMenu();
      return storageContainerMenuBase.realInventorySlots.stream().map(SophisticatedSlotAccessor::fromSlot).toList();
   }

   public Result handleSimpleTransfer(
      Context context,
      MissingInputRenderer missingInputRenderer,
      List<InputIngredient<class_1799>> inputs,
      Iterable<SlotAccessor> inputSlots,
      Iterable<SlotAccessor> inventorySlots
   ) {
      class_465<?> containerScreen = context.getContainerScreen();
      List<InputIngredient<class_1799>> missing = SimpleTransferHandlerImpl.hasItemsIndexed(context, inventorySlots, inputs);
      if (missing.isEmpty()) {
         if (!ClientHelper.getInstance().canUseMovePackets()) {
            return Result.createFailed(class_2561.method_43471("error.rei.not.on.server"));
         } else if (!context.isActuallyCrafting()) {
            return Result.createSuccessful();
         } else {
            context.getMinecraft().method_1507(containerScreen);
            if (containerScreen instanceof class_518 listener) {
               listener.method_2659().field_3092.method_2571();
            }

            PacketDistributor.sendToServer(new REIMoveItemsPayload(context, context.isStackedCrafting(), inputs, inputSlots, inventorySlots));
            return Result.createSuccessful();
         }
      } else {
         IntSet missingIndices = new IntLinkedOpenHashSet(missing.size());

         for (InputIngredient<class_1799> ingredient : missing) {
            missingIndices.add(ingredient.getDisplayIndex());
         }

         return Result.createFailed(class_2561.method_43471("error.rei.not.enough.materials"))
            .renderer(
               (matrices, mouseX, mouseY, delta, widgets, bounds, d) -> missingInputRenderer.renderMissingInput(
                  context, inputs, missing, missingIndices, matrices, mouseX, mouseY, delta, widgets, bounds
               )
            )
            .tooltipMissing(CollectionUtils.map(missing, ingredientx -> EntryIngredients.ofItemStacks(ingredientx.get())));
      }
   }
}
