package net.p3pp3rf1y.sophisticatedcore.util;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;
import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1264;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_3532;
import net.minecraft.class_5819;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryHandlerHelper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.inventory.SimpleItemStackHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.SlottedStackStorageContainerItemContext;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IPickupResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import org.apache.commons.lang3.mutable.MutableInt;

public class InventoryHelper {
   private static final List<Function<class_1657, SlottedStackStorage>> PLAYER_INVENTORY_PROVIDERS = new ArrayList<>();

   private InventoryHelper() {
   }

   public static void registerPlayerInventoryProvider(Function<class_1657, SlottedStackStorage> provider) {
      PLAYER_INVENTORY_PROVIDERS.add(provider);
   }

   public static Optional<class_1799> getItemFromEitherHand(class_1657 player, class_1792 item) {
      class_1799 mainHandItem = player.method_6047();
      if (mainHandItem.method_7909() == item) {
         return Optional.of(mainHandItem);
      } else {
         class_1799 offhandItem = player.method_6079();
         return offhandItem.method_7909() == item ? Optional.of(offhandItem) : Optional.empty();
      }
   }

   public static boolean hasItem(SlottedStorage<ItemVariant> inventory, Predicate<class_1799> matches) {
      AtomicBoolean result = new AtomicBoolean(false);
      iterate(inventory, (slot, stack) -> {
         if (!stack.method_7960() && matches.test(stack)) {
            result.set(true);
         }
      }, result::get);
      return result.get();
   }

   public static Set<Integer> getItemSlots(SlottedStackStorage inventory, Predicate<class_1799> matches) {
      Set<Integer> slots = new HashSet<>();
      iterate(inventory, (slot, stack) -> {
         if (!stack.method_7960() && matches.test(stack)) {
            slots.add(slot);
         }
      });
      return slots;
   }

   public static void copyTo(SlottedStackStorage handlerA, SlottedStackStorage handlerB) {
      int slotsA = handlerA.getSlotCount();
      int slotsB = handlerB.getSlotCount();

      for (int slot = 0; slot < slotsA && slot < slotsB; slot++) {
         class_1799 slotStack = handlerA.getStackInSlot(slot);
         if (!slotStack.method_7960()) {
            handlerB.setStackInSlot(slot, slotStack);
         }
      }
   }

   public static List<class_1799> insertIntoInventory(List<class_1799> stacks, Storage<ItemVariant> inventory, boolean simulate) {
      if (stacks.isEmpty()) {
         return stacks;
      } else {
         List<class_1799> remaining = new ArrayList<>();
         if (inventory instanceof IItemHandlerSimpleInserter itemHandlerSimpleInserter) {
            for (class_1799 stack : stacks) {
               class_1799 remainingStack = itemHandlerSimpleInserter.insertItem(stack.method_7972(), simulate);
               if (!remainingStack.method_7960()) {
                  remaining.add(remainingStack);
               }
            }

            return remaining;
         } else {
            Transaction ctx = Transaction.openOuter();

            try {
               for (class_1799 stackx : stacks) {
                  ItemVariant resource = ItemVariant.of(stackx);
                  long remainingCount = stackx.method_7947() - inventory.insert(resource, stackx.method_7947(), ctx);
                  if (remainingCount > 0L) {
                     remaining.add(resource.toStack((int)remainingCount));
                  }
               }

               if (!simulate) {
                  ctx.commit();
               }
            } catch (Throwable var11) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }
               }

               throw var11;
            }

            if (ctx != null) {
               ctx.close();
            }

            return remaining;
         }
      }
   }

   public static ItemStackHandler cloneInventory(SlottedStackStorage inventory) {
      ItemStackHandler cloned = new SimpleItemStackHandler(inventory.getSlotCount());

      for (int slot = 0; slot < inventory.getSlotCount(); slot++) {
         cloned.setStackInSlot(slot, inventory.getStackInSlot(slot).method_7972());
      }

      return cloned;
   }

   public static class_1799 insertIntoInventory(class_1799 stack, SlottedStorage<ItemVariant> inventory, boolean simulate) {
      if (inventory instanceof IItemHandlerSimpleInserter itemHandlerSimpleInserter) {
         return itemHandlerSimpleInserter.insertItem(stack, simulate);
      } else {
         Transaction ctx = Transaction.openOuter();

         class_1799 remainingStack;
         try {
            remainingStack = stack.method_46651((int)(stack.method_7947() - inventory.insert(ItemVariant.of(stack), stack.method_7947(), ctx)));
            if (!simulate) {
               ctx.commit();
            }
         } catch (Throwable var8) {
            if (ctx != null) {
               try {
                  ctx.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (ctx != null) {
            ctx.close();
         }

         return remainingStack;
      }
   }

   public static class_1799 extractFromInventory(class_1792 item, int count, IItemHandlerSimpleInserter inventory, boolean simulate) {
      return extractFromInventory((Predicate<class_1799>)(stack -> stack.method_7909() == item), count, inventory, simulate);
   }

   public static class_1799 extractFromInventory(Predicate<class_1799> stackMatcher, int count, IItemHandlerSimpleInserter inventory, boolean simulate) {
      class_1799 ret = class_1799.field_8037;
      int slots = inventory.getSlotCount();

      for (int slot = 0; slot < slots && ret.method_7947() < count; slot++) {
         class_1799 slotStack = inventory.getStackInSlot(slot);
         if (stackMatcher.test(slotStack) && (ret.method_7960() || class_1799.method_31577(ret, slotStack))) {
            int toExtract = Math.min(slotStack.method_7947(), count - ret.method_7947());
            class_1799 extractedStack = inventory.extractItem(slot, toExtract, simulate);
            if (ret.method_7960()) {
               ret = extractedStack;
            } else {
               ret.method_7939(ret.method_7947() + extractedStack.method_7947());
            }
         }
      }

      return ret;
   }

   public static class_1799 extractFromInventory(class_1799 stack, IItemHandlerSimpleInserter inventory, boolean simulate) {
      int extractedCount = 0;
      int slots = inventory.getSlotCount();

      for (int slot = 0; slot < slots && extractedCount < stack.method_7947(); slot++) {
         class_1799 slotStack = inventory.getStackInSlot(slot);
         if (class_1799.method_31577(stack, slotStack)) {
            int toExtract = Math.min(slotStack.method_7947(), stack.method_7947() - extractedCount);
            extractedCount += inventory.extractItem(slot, toExtract, simulate).method_7947();
         }
      }

      if (extractedCount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 result = stack.method_7972();
         result.method_7939(extractedCount);
         return result;
      }
   }

   public static class_1799 runPickupOnPickupResponseUpgrades(class_1937 level, UpgradeHandler upgradeHandler, class_1799 remainingStack, boolean simulate) {
      return runPickupOnPickupResponseUpgrades(level, null, upgradeHandler, remainingStack, simulate);
   }

   public static class_1799 runPickupOnPickupResponseUpgrades(
      class_1937 level, @Nullable class_1657 player, UpgradeHandler upgradeHandler, class_1799 remainingStack, boolean simulate
   ) {
      for (IPickupResponseUpgrade pickupUpgrade : upgradeHandler.getWrappersThatImplement(IPickupResponseUpgrade.class)) {
         int countBeforePickup = remainingStack.method_7947();
         class_1792 item = remainingStack.method_7909();
         remainingStack = pickupUpgrade.pickup(level, remainingStack, simulate);
         if (!simulate && player != null && remainingStack.method_7947() != countBeforePickup) {
            playPickupSound(level, player);
            player.method_7342(class_3468.field_15392.method_14956(item), countBeforePickup - remainingStack.method_7947());
         }

         if (remainingStack.method_7960()) {
            return class_1799.field_8037;
         }
      }

      return remainingStack;
   }

   private static void playPickupSound(class_1937 level, @Nonnull class_1657 player) {
      level.method_43128(
         null,
         player.method_23317(),
         player.method_23318(),
         player.method_23321(),
         class_3417.field_15197,
         class_3419.field_15248,
         0.2F,
         RandHelper.getRandomMinusOneToOne(level.field_9229) * 1.4F + 2.0F
      );
   }

   public static void iterate(Storage<ItemVariant> handler, Consumer<class_1799> actOn) {
      iterate(handler, actOn, () -> false);
   }

   public static void iterate(Storage<ItemVariant> handler, Consumer<class_1799> actOn, BooleanSupplier shouldExit) {
      for (StorageView<ItemVariant> view : handler.nonEmptyViews()) {
         actOn.accept(view.isResourceBlank() ? class_1799.field_8037 : ((ItemVariant)view.getResource()).toStack((int)view.getAmount()));
         if (shouldExit.getAsBoolean()) {
            break;
         }
      }
   }

   public static void iterate(SlottedStorage<ItemVariant> handler, BiConsumer<Integer, class_1799> actOn) {
      iterate(handler, actOn, () -> false);
   }

   public static void iterate(SlottedStorage<ItemVariant> handler, BiConsumer<Integer, class_1799> actOn, BooleanSupplier shouldExit) {
      iterate(handler, actOn, shouldExit, true);
   }

   public static void iterate(SlottedStorage<ItemVariant> handler, BiConsumer<Integer, class_1799> actOn, BooleanSupplier shouldExit, boolean getVirtualCounts) {
      Function<Integer, class_1799> getStackHandler = handler instanceof SlottedStackStorage slottedHandler ? slottedHandler::getStackInSlot : slotx -> {
         SingleSlotStorage<ItemVariant> slotStorage = handler.getSlot(slotx);
         return slotStorage.isResourceBlank() ? class_1799.field_8037 : ((ItemVariant)slotStorage.getResource()).toStack((int)slotStorage.getAmount());
      };
      int slots = handler.getSlotCount();

      for (int slot = 0; slot < slots; slot++) {
         class_1799 stack = !getVirtualCounts && handler instanceof InventoryHandler inventoryHandler
            ? inventoryHandler.getSlotStack(slot)
            : getStackHandler.apply(slot);
         actOn.accept(slot, stack);
         if (shouldExit.getAsBoolean()) {
            break;
         }
      }
   }

   public static int getCountMissingInHandler(IInventoryHandlerHelper itemHandler, class_1799 filter, int expectedCount) {
      MutableInt missingCount = new MutableInt(expectedCount);
      iterate(itemHandler, (slot, stack) -> {
         if (class_1799.method_31577(stack, filter)) {
            missingCount.subtract(Math.min(stack.method_7947(), missingCount.getValue()));
         }
      }, () -> missingCount.getValue() == 0);
      return missingCount.getValue();
   }

   public static <T> T iterate(
      SlottedStackStorage handler, BiFunction<Integer, class_1799, T> getFromSlotStack, Supplier<T> supplyDefault, Predicate<T> shouldExit
   ) {
      T ret = supplyDefault.get();
      int slots = handler.getSlotCount();

      for (int slot = 0; slot < slots; slot++) {
         class_1799 stack = handler.getStackInSlot(slot);
         ret = getFromSlotStack.apply(slot, stack);
         if (shouldExit.test(ret)) {
            break;
         }
      }

      return ret;
   }

   public static void transfer(IItemHandlerSimpleInserter handlerA, IItemHandlerSimpleInserter handlerB, Consumer<Supplier<class_1799>> onInserted) {
      int slotsA = handlerA.getSlotCount();

      for (int slot = 0; slot < slotsA; slot++) {
         class_1799 slotStack = handlerA.getStackInSlot(slot);
         if (!slotStack.method_7960()) {
            int countToTransfer = slotStack.method_7947();

            while (countToTransfer > 0) {
               class_1799 toInsert = slotStack.method_7972();
               toInsert.method_7939(Math.min(slotStack.method_7914(), countToTransfer));
               class_1799 remainingAfterInsert = insertIntoInventory(toInsert, handlerB, true);
               if (remainingAfterInsert.method_7947() == toInsert.method_7947()) {
                  break;
               }

               int toExtract = toInsert.method_7947() - remainingAfterInsert.method_7947();
               class_1799 extractedStack = handlerA.extractItem(slot, toExtract, true);
               if (extractedStack.method_7960()) {
                  break;
               }

               insertIntoInventory(handlerA.extractItem(slot, extractedStack.method_7947(), false), handlerB, false);
               onInserted.accept(() -> {
                  class_1799 copiedStack = slotStack.method_7972();
                  copiedStack.method_7939(extractedStack.method_7947());
                  return copiedStack;
               });
               countToTransfer -= extractedStack.method_7947();
            }
         }
      }
   }

   public static void transfer(
      Storage<ItemVariant> handlerA, Storage<ItemVariant> handlerB, Consumer<Supplier<class_1799>> onInserted, @Nullable TransactionContext ctx
   ) {
      for (StorageView<ItemVariant> view : handlerA.nonEmptyViews()) {
         ItemVariant resource = (ItemVariant)view.getResource();
         long countToTransfer = view.getAmount();

         while (countToTransfer > 0L) {
            long inserted = StorageUtil.simulateInsert(handlerB, resource, Math.min((long)resource.toStack().method_7914(), countToTransfer), ctx);
            if (inserted == 0L) {
               break;
            }

            long extracted = StorageUtil.simulateExtract(handlerA, resource, inserted, ctx);
            if (extracted == 0L) {
               break;
            }

            Transaction transferTransaction = Transaction.openNested(ctx);

            try {
               extracted = view.extract(resource, extracted, transferTransaction);
               long accepted = handlerB.insert(resource, inserted, transferTransaction);
               TransactionCallback.onSuccess(transferTransaction, () -> onInserted.accept(() -> resource.toStack((int)accepted).method_7972()));
               transferTransaction.commit();
            } catch (Throwable var17) {
               if (transferTransaction != null) {
                  try {
                     transferTransaction.close();
                  } catch (Throwable var16) {
                     var17.addSuppressed(var16);
                  }
               }

               throw var17;
            }

            if (transferTransaction != null) {
               transferTransaction.close();
            }

            countToTransfer -= extracted;
         }
      }
   }

   public static boolean isEmpty(SlottedStackStorage itemHandler) {
      int slots = itemHandler.getSlotCount();

      for (int slot = 0; slot < slots; slot++) {
         if (!itemHandler.getStackInSlot(slot).method_7960()) {
            return false;
         }
      }

      return true;
   }

   public static class_1799 getAndRemove(SlottedStorage<ItemVariant> itemHandler, int slotIndex) {
      if (slotIndex >= itemHandler.getSlotCount()) {
         return class_1799.field_8037;
      } else {
         SingleSlotStorage<ItemVariant> slot = itemHandler.getSlot(slotIndex);
         ItemVariant resource = (ItemVariant)slot.getResource();
         return resource.toStack((int)slot.extract(resource, Long.MAX_VALUE, null));
      }
   }

   @SafeVarargs
   public static void insertOrDropItem(class_1657 player, class_1799 stack, Storage<ItemVariant>... inventories) {
      ItemVariant resource = ItemVariant.of(stack);
      long toInsert = stack.method_7947();

      for (Storage<ItemVariant> inventory : inventories) {
         Transaction ctx = Transaction.openOuter();

         try {
            toInsert -= inventory.insert(resource, toInsert, ctx);
            ctx.commit();
         } catch (Throwable var14) {
            if (ctx != null) {
               try {
                  ctx.close();
               } catch (Throwable var13) {
                  var14.addSuppressed(var13);
               }
            }

            throw var14;
         }

         if (ctx != null) {
            ctx.close();
         }

         if (toInsert == 0L) {
            return;
         }
      }

      if (toInsert > 0L) {
         player.method_7328(resource.toStack((int)toInsert), true);
      }
   }

   public static class_1799 mergeIntoPlayerInventory(class_1657 player, class_1799 stack, int startSlot) {
      class_1799 result = stack.method_7972();
      List<Integer> emptySlots = new ArrayList<>();

      for (int slot = startSlot; slot < player.method_31548().field_7547.size(); slot++) {
         class_1799 slotStack = player.method_31548().method_5438(slot);
         if (slotStack.method_7960()) {
            emptySlots.add(slot);
         }

         if (class_1799.method_31577(slotStack, result)) {
            int count = Math.min(slotStack.method_7914() - slotStack.method_7947(), result.method_7947());
            slotStack.method_7933(count);
            result.method_7934(count);
            if (result.method_7960()) {
               return class_1799.field_8037;
            }
         }
      }

      for (int slot : emptySlots) {
         class_1799 slotStackx = result.method_7972();
         slotStackx.method_7939(Math.min(slotStackx.method_7914(), result.method_7947()));
         player.method_31548().method_5447(slot, slotStackx);
         result.method_7934(slotStackx.method_7947());
         if (result.method_7960()) {
            return class_1799.field_8037;
         }
      }

      return result;
   }

   static Map<ItemStackKey, Integer> getCompactedStacks(SlottedStackStorage handler) {
      return getCompactedStacks(handler, new HashSet<>());
   }

   static Map<ItemStackKey, Integer> getCompactedStacks(SlottedStackStorage handler, Set<Integer> ignoreSlots) {
      return getCompactedStacks(handler, ignoreSlots, true);
   }

   static Map<ItemStackKey, Integer> getCompactedStacks(SlottedStackStorage handler, Set<Integer> ignoreSlots, boolean getVirtualCounts) {
      Map<ItemStackKey, Integer> ret = new HashMap<>();
      iterate(handler, (slot, stack) -> {
         if (!stack.method_7960() && !ignoreSlots.contains(slot)) {
            ItemStackKey itemStackKey = ItemStackKey.of(stack);
            ret.put(itemStackKey, ret.computeIfAbsent(itemStackKey, fs -> 0) + stack.method_7947());
         }
      }, () -> false, getVirtualCounts);
      return ret;
   }

   public static List<class_1799> getCompactedStacksSortedByCount(SlottedStackStorage handler) {
      Map<ItemStackKey, Integer> compactedStacks = getCompactedStacks(handler);
      List<Entry<ItemStackKey, Integer>> sortedList = new ArrayList<>(compactedStacks.entrySet());
      sortedList.sort(InventorySorter.BY_COUNT);
      List<class_1799> ret = new ArrayList<>();
      sortedList.forEach(e -> {
         class_1799 stackCopy = e.getKey().getStack().method_7972();
         stackCopy.method_7939(e.getValue());
         ret.add(stackCopy);
      });
      return ret;
   }

   public static Set<ItemStackKey> getUniqueStacks(Storage<ItemVariant> handler) {
      Set<ItemStackKey> uniqueStacks = new HashSet<>();
      iterate(handler, stack -> {
         if (!stack.method_7960()) {
            ItemStackKey itemStackKey = ItemStackKey.of(stack);
            uniqueStacks.add(itemStackKey);
         }
      });
      return uniqueStacks;
   }

   public static List<Integer> getEmptySlotsRandomized(SlottedStorage<ItemVariant> inventory) {
      List<Integer> list = Lists.newArrayList();

      for (int i = 0; i < inventory.getSlotCount(); i++) {
         if (inventory.getSlot(i).isResourceBlank()) {
            list.add(i);
         }
      }

      Collections.shuffle(list, new Random());
      return list;
   }

   public static void shuffleItems(List<class_1799> stacks, int emptySlotsCount, class_5819 rand) {
      List<class_1799> list = Lists.newArrayList();
      Iterator<class_1799> iterator = stacks.iterator();

      while (iterator.hasNext()) {
         class_1799 itemstack = iterator.next();
         if (itemstack.method_7960()) {
            iterator.remove();
         } else if (itemstack.method_7947() > 1) {
            list.add(itemstack);
            iterator.remove();
         }
      }

      while (emptySlotsCount - stacks.size() - list.size() > 0 && !list.isEmpty()) {
         class_1799 itemstack2 = list.remove(class_3532.method_15395(rand, 0, list.size() - 1));
         int i = class_3532.method_15395(rand, 1, itemstack2.method_7947() / 2);
         class_1799 itemstack1 = itemstack2.method_7971(i);
         if (itemstack2.method_7947() > 1 && rand.method_43056()) {
            list.add(itemstack2);
         } else {
            stacks.add(itemstack2);
         }

         if (itemstack1.method_7947() > 1 && rand.method_43056()) {
            list.add(itemstack1);
         } else {
            stacks.add(itemstack1);
         }
      }

      stacks.addAll(list);
      Collections.shuffle(stacks, new Random());
   }

   public static void dropItems(SlottedStackStorage inventoryHandler, class_1937 level, class_2338 pos) {
      dropItems(inventoryHandler, level, pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public static void dropItems(SlottedStackStorage inventoryHandler, class_1937 level, double x, double y, double z) {
      iterate(inventoryHandler, (slot, stack) -> dropItem(inventoryHandler, level, x, y, z, slot, stack), () -> false, false);
   }

   public static void dropItem(SlottedStackStorage handler, class_1937 level, double x, double y, double z, Integer slot, class_1799 stack) {
      if (!stack.method_7960()) {
         if (handler instanceof InventoryHandler inventoryHandler) {
            int countToExtract = stack.method_7947();

            while (countToExtract > 0) {
               int countToDrop = Math.min(stack.method_7914(), countToExtract);
               class_1264.method_5449(level, x, y, z, stack.method_46651(countToDrop));
               countToExtract -= countToDrop;
            }

            inventoryHandler.setSlotStack(slot, class_1799.field_8037);
         } else {
            ItemVariant resource = ItemVariant.of(stack);
            Transaction ctx = Transaction.openOuter();

            long extracted;
            try {
               extracted = handler.extractSlot(slot, resource, stack.method_7914(), ctx);
               ctx.commit();
            } catch (Throwable var20) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var18) {
                     var20.addSuppressed(var18);
                  }
               }

               throw var20;
            }

            if (ctx != null) {
               ctx.close();
            }

            while (extracted > 0L) {
               class_1264.method_5449(level, x, y, z, resource.toStack((int)extracted));
               ctx = Transaction.openOuter();

               try {
                  extracted = handler.extractSlot(slot, resource, stack.method_7914(), ctx);
                  ctx.commit();
               } catch (Throwable var19) {
                  if (ctx != null) {
                     try {
                        ctx.close();
                     } catch (Throwable var17) {
                        var19.addSuppressed(var17);
                     }
                  }

                  throw var19;
               }

               if (ctx != null) {
                  ctx.close();
               }
            }

            handler.setStackInSlot(slot, class_1799.field_8037);
         }
      }
   }

   public static int getAnalogOutputSignal(ITrackedContentsItemHandler handler) {
      AtomicDouble totalFilled = new AtomicDouble(0.0);
      AtomicBoolean isEmpty = new AtomicBoolean(true);
      iterate(handler, (slot, stack) -> {
         if (!stack.method_7960()) {
            int slotLimit = handler.getInternalSlotLimit(slot);
            totalFilled.addAndGet(stack.method_7947() / (slotLimit / (64.0F / stack.method_7914())));
            isEmpty.set(false);
         }
      });
      double percentFilled = totalFilled.get() / handler.getSlotCount();
      return class_3532.method_15357(percentFilled * 14.0) + (isEmpty.get() ? 0 : 1);
   }

   public static List<Storage<ItemVariant>> getItemHandlersFromPlayerIncludingContainers(class_1657 player) {
      List<Storage<ItemVariant>> itemHandlers = new ArrayList<>();
      PLAYER_INVENTORY_PROVIDERS.forEach(provider -> {
         SlottedStackStorage itemHandler = provider.apply(player);
         itemHandlers.add(itemHandler);

         for (int i = 0; i < itemHandler.getSlotCount(); i++) {
            SingleSlotStorage<ItemVariant> slot = itemHandler.getSlot(i);
            if (!slot.isResourceBlank()) {
               Storage<ItemVariant> containerHandler = SlottedStackStorageContainerItemContext.of(itemHandler, i).find(ItemStorage.ITEM);
               if (containerHandler != null) {
                  itemHandlers.add(containerHandler);
               }
            }
         }
      });
      return itemHandlers;
   }

   static {
      registerPlayerInventoryProvider(InventoryStorageWrapper::of);
   }
}
