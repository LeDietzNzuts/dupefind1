package net.caffeinemc.mods.lithium.mixin.block.hopper;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.caffeinemc.mods.lithium.api.inventory.LithiumCooldownReceivingInventory;
import net.caffeinemc.mods.lithium.api.inventory.LithiumInventory;
import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeListener;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeTracker;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_comparator_tracking.ComparatorTracker;
import net.caffeinemc.mods.lithium.common.hopper.BlockStateOnlyInventory;
import net.caffeinemc.mods.lithium.common.hopper.HopperCachingState;
import net.caffeinemc.mods.lithium.common.hopper.HopperHelper;
import net.caffeinemc.mods.lithium.common.hopper.InventoryHelper;
import net.caffeinemc.mods.lithium.common.hopper.LithiumStackList;
import net.caffeinemc.mods.lithium.common.hopper.UpdateReceiver;
import net.caffeinemc.mods.lithium.common.services.PlatformModCompat;
import net.caffeinemc.mods.lithium.common.tracking.entity.SectionedEntityMovementListener;
import net.caffeinemc.mods.lithium.common.tracking.entity.SectionedInventoryEntityMovementTracker;
import net.caffeinemc.mods.lithium.common.tracking.entity.SectionedItemEntityMovementTracker;
import net.minecraft.class_1258;
import net.minecraft.class_1263;
import net.minecraft.class_1278;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2377;
import net.minecraft.class_238;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2614;
import net.minecraft.class_2615;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = class_2614.class, priority = 950)
public abstract class HopperBlockEntityMixin
   extends class_2586
   implements class_2615,
   UpdateReceiver,
   LithiumInventory,
   InventoryChangeListener,
   SectionedEntityMovementListener {
   @Shadow
   private long field_12022;
   private long myModCountAtLastInsert;
   private long myModCountAtLastExtract;
   private long myModCountAtLastItemCollect;
   private HopperCachingState.BlockInventory insertionMode = HopperCachingState.BlockInventory.UNKNOWN;
   private HopperCachingState.BlockInventory extractionMode = HopperCachingState.BlockInventory.UNKNOWN;
   @Nullable
   private class_1263 insertBlockInventory;
   @Nullable
   private class_1263 extractBlockInventory;
   @Nullable
   private LithiumInventory insertInventory;
   @Nullable
   private LithiumInventory extractInventory;
   @Nullable
   private LithiumStackList insertStackList;
   @Nullable
   private LithiumStackList extractStackList;
   private long insertStackListModCount;
   private long extractStackListModCount;
   private SectionedItemEntityMovementTracker<class_1542> collectItemEntityTracker;
   private boolean collectItemEntityTrackerWasEmpty;
   private class_238 collectItemEntityBox;
   private long collectItemEntityAttemptTime;
   private SectionedInventoryEntityMovementTracker<class_1263> extractInventoryEntityTracker;
   private class_238 extractInventoryEntityBox;
   private long extractInventoryEntityFailedSearchTime;
   private SectionedInventoryEntityMovementTracker<class_1263> insertInventoryEntityTracker;
   private class_238 insertInventoryEntityBox;
   private long insertInventoryEntityFailedSearchTime;
   private boolean shouldCheckSleep;
   @Shadow
   private class_2350 field_49101;

   public HopperBlockEntityMixin(class_2591<?> type, class_2338 pos, class_2680 state) {
      super(type, pos, state);
   }

   @Shadow
   protected abstract boolean method_11242();

   @Redirect(
      method = "method_11241(Lnet/minecraft/class_1937;Lnet/minecraft/class_2615;)Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2614;method_11248(Lnet/minecraft/class_1937;Lnet/minecraft/class_2615;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Lnet/minecraft/class_1263;"
      )
   )
   private static class_1263 getExtractInventory(class_1937 world, class_2615 hopper, class_2338 extractBlockPos, class_2680 extractBlockState) {
      if (!(hopper instanceof HopperBlockEntityMixin hopperBlockEntity)) {
         return method_11248(world, hopper, extractBlockPos, extractBlockState);
      } else {
         class_1263 blockInventory = hopperBlockEntity.getExtractBlockInventory(world, extractBlockPos, extractBlockState);
         if (blockInventory != null) {
            return blockInventory;
         } else {
            if (hopperBlockEntity.extractInventoryEntityTracker == null) {
               hopperBlockEntity.initExtractInventoryTracker(world);
            }

            if (hopperBlockEntity.extractInventoryEntityTracker.isUnchangedSince(hopperBlockEntity.extractInventoryEntityFailedSearchTime)) {
               hopperBlockEntity.extractInventoryEntityFailedSearchTime = hopperBlockEntity.field_12022;
               return null;
            } else {
               hopperBlockEntity.extractInventoryEntityFailedSearchTime = Long.MIN_VALUE;
               hopperBlockEntity.shouldCheckSleep = false;
               List<class_1263> inventoryEntities = hopperBlockEntity.extractInventoryEntityTracker.getEntities(hopperBlockEntity.extractInventoryEntityBox);
               if (inventoryEntities.isEmpty()) {
                  hopperBlockEntity.extractInventoryEntityFailedSearchTime = hopperBlockEntity.field_12022;
                  return null;
               } else {
                  class_1263 inventory = inventoryEntities.get(world.field_9229.method_43048(inventoryEntities.size()));
                  if (inventory instanceof LithiumInventory optimizedInventory) {
                     LithiumStackList extractInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
                     if (inventory != hopperBlockEntity.extractInventory || hopperBlockEntity.extractStackList != extractInventoryStackList) {
                        hopperBlockEntity.cacheExtractLithiumInventory(optimizedInventory);
                     }
                  }

                  return inventory;
               }
            }
         }
      }
   }

   @Inject(
      cancellable = true,
      method = "method_11246(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2614;)Z",
      at = @At(
         value = "INVOKE",
         shift = Shift.BEFORE,
         target = "Lnet/minecraft/class_2614;method_11258(Lnet/minecraft/class_1263;Lnet/minecraft/class_2350;)Z"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private static void lithiumInsert(
      class_1937 world, class_2338 pos, class_2614 blockEntity, CallbackInfoReturnable<Boolean> cir, class_1263 insertInventory, class_2350 direction
   ) {
      if (insertInventory != null && blockEntity instanceof class_2614 && !(blockEntity instanceof class_1278)) {
         HopperBlockEntityMixin hopperBlockEntity = (HopperBlockEntityMixin)blockEntity;
         LithiumStackList hopperStackList = InventoryHelper.getLithiumStackList(hopperBlockEntity);
         if (hopperBlockEntity.insertInventory == insertInventory
            && hopperStackList.getModCount() == hopperBlockEntity.myModCountAtLastInsert
            && hopperBlockEntity.insertStackList != null
            && hopperBlockEntity.insertStackList.getModCount() == hopperBlockEntity.insertStackListModCount) {
            cir.setReturnValue(false);
         } else {
            boolean insertInventoryWasEmptyHopperNotDisabled = insertInventory instanceof HopperBlockEntityMixin
               && !((HopperBlockEntityMixin)insertInventory).method_11242()
               && hopperBlockEntity.insertStackList != null
               && hopperBlockEntity.insertStackList.getOccupiedSlots() == 0;
            boolean insertInventoryHandlesModdedCooldown = ((LithiumCooldownReceivingInventory)insertInventory).canReceiveTransferCooldown()
                  && hopperBlockEntity.insertStackList != null
               ? hopperBlockEntity.insertStackList.getOccupiedSlots() == 0
               : insertInventory.method_5442();
            if (hopperBlockEntity.insertInventory != insertInventory
               || hopperBlockEntity.insertStackList.getFullSlots() != hopperBlockEntity.insertStackList.size()) {
               class_2350 fromDirection = hopperBlockEntity.field_49101.method_10153();
               int size = hopperStackList.size();

               for (int i = 0; i < size; i++) {
                  class_1799 transferStack = (class_1799)hopperStackList.get(i);
                  if (!transferStack.method_7960()) {
                     boolean transferSuccess = HopperHelper.tryMoveSingleItem(insertInventory, transferStack, fromDirection);
                     if (transferSuccess) {
                        if (insertInventoryWasEmptyHopperNotDisabled) {
                           HopperBlockEntityMixin receivingHopper = (HopperBlockEntityMixin)insertInventory;
                           int k = 8;
                           if (receivingHopper.field_12022 >= hopperBlockEntity.field_12022) {
                              k = 7;
                           }

                           receivingHopper.method_11238(k);
                        }

                        if (insertInventoryHandlesModdedCooldown) {
                           ((LithiumCooldownReceivingInventory)insertInventory).setTransferCooldown(hopperBlockEntity.field_12022);
                        }

                        insertInventory.method_5431();
                        cir.setReturnValue(true);
                        return;
                     }
                  }
               }
            }

            hopperBlockEntity.myModCountAtLastInsert = hopperStackList.getModCount();
            if (hopperBlockEntity.insertStackList != null) {
               hopperBlockEntity.insertStackListModCount = hopperBlockEntity.insertStackList.getModCount();
            }

            cir.setReturnValue(false);
         }
      }
   }

   @Inject(
      method = "method_11241(Lnet/minecraft/class_1937;Lnet/minecraft/class_2615;)Z",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_2350;field_11033:Lnet/minecraft/class_2350;", shift = Shift.AFTER),
      cancellable = true
   )
   private static void lithiumExtract(class_1937 world, class_2615 to, CallbackInfoReturnable<Boolean> cir, @Local class_1263 from) {
      if (to instanceof HopperBlockEntityMixin hopperBlockEntity) {
         if (from == hopperBlockEntity.extractInventory && hopperBlockEntity.extractStackList != null) {
            LithiumStackList hopperStackList = InventoryHelper.getLithiumStackList(hopperBlockEntity);
            LithiumStackList fromStackList = hopperBlockEntity.extractStackList;
            if (hopperStackList.getModCount() == hopperBlockEntity.myModCountAtLastExtract
               && fromStackList.getModCount() == hopperBlockEntity.extractStackListModCount) {
               if (!(from instanceof ComparatorTracker comparatorTracker && !comparatorTracker.lithium$hasAnyComparatorNearby())) {
                  fromStackList.runComparatorUpdatePatternOnFailedExtract(fromStackList, from);
               }

               cir.setReturnValue(false);
            } else {
               int[] availableSlots = from instanceof class_1278 ? ((class_1278)from).method_5494(class_2350.field_11033) : null;
               int fromSize = availableSlots != null ? availableSlots.length : from.method_5439();

               for (int i = 0; i < fromSize; i++) {
                  int fromSlot = availableSlots != null ? availableSlots[i] : i;
                  class_1799 itemStack = (class_1799)fromStackList.get(fromSlot);
                  if (!itemStack.method_7960() && method_11252(to, from, itemStack, fromSlot, class_2350.field_11033)) {
                     class_1799 takenItem = from.method_5434(fromSlot, 1);

                     assert !takenItem.method_7960();

                     boolean transferSuccess = HopperHelper.tryMoveSingleItem(to, takenItem, null);
                     if (transferSuccess) {
                        to.method_5431();
                        from.method_5431();
                        cir.setReturnValue(true);
                        return;
                     }

                     class_1799 restoredStack = (class_1799)fromStackList.get(fromSlot);
                     if (restoredStack.method_7960()) {
                        restoredStack = takenItem;
                     } else {
                        restoredStack.method_7933(1);
                     }

                     from.method_5447(fromSlot, restoredStack);
                  }
               }

               hopperBlockEntity.myModCountAtLastExtract = hopperStackList.getModCount();
               if (fromStackList != null) {
                  hopperBlockEntity.extractStackListModCount = fromStackList.getModCount();
               }

               cir.setReturnValue(false);
            }
         }
      }
   }

   @Redirect(
      method = "method_11243(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2614;Ljava/util/function/BooleanSupplier;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2614;method_11256()Z")
   )
   private static boolean lithiumHopperIsFull(class_2614 hopperBlockEntity) {
      LithiumStackList lithiumStackList = InventoryHelper.getLithiumStackList((HopperBlockEntityMixin)hopperBlockEntity);
      return lithiumStackList.getFullSlots() == lithiumStackList.size();
   }

   @Redirect(
      method = "method_11243(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2614;Ljava/util/function/BooleanSupplier;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2614;method_5442()Z")
   )
   private static boolean lithiumHopperIsEmpty(class_2614 hopperBlockEntity) {
      LithiumStackList lithiumStackList = InventoryHelper.getLithiumStackList((HopperBlockEntityMixin)hopperBlockEntity);
      return lithiumStackList.getOccupiedSlots() == 0;
   }

   @Shadow
   protected abstract void method_11238(int var1);

   @Shadow
   protected abstract boolean method_11239();

   @Shadow
   private static native boolean method_11252(class_1263 var0, class_1263 var1, class_1799 var2, int var3, class_2350 var4);

   @Shadow
   @Nullable
   private static native class_1263 method_57010(class_1937 var0, class_2338 var1, class_2680 var2);

   @Shadow
   @Nullable
   protected static native class_1263 method_11248(class_1937 var0, class_2615 var1, class_2338 var2, class_2680 var3);

   @Override
   public void lithium$invalidateCacheOnNeighborUpdate(boolean fromAbove) {
      if (fromAbove) {
         if (this.extractionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY
            || this.extractionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
            this.invalidateBlockExtractionData();
         }
      } else if (this.insertionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY
         || this.insertionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
         this.invalidateBlockInsertionData();
      }
   }

   @Override
   public void lithium$invalidateCacheOnUndirectedNeighborUpdate() {
      if (this.extractionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY || this.extractionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
         this.invalidateBlockExtractionData();
      }

      if (this.insertionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY || this.insertionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
         this.invalidateBlockInsertionData();
      }
   }

   @Override
   public void lithium$invalidateCacheOnNeighborUpdate(class_2350 fromDirection) {
      boolean fromAbove = fromDirection == class_2350.field_11036;
      if (fromAbove || this.method_11010().method_11654(class_2377.field_11129) == fromDirection) {
         this.lithium$invalidateCacheOnNeighborUpdate(fromAbove);
      }
   }

   @Redirect(
      method = "method_11246(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2614;)Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2614;method_11255(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2614;)Lnet/minecraft/class_1263;"
      )
   )
   private static class_1263 getLithiumOutputInventory(class_1937 world, class_2338 pos, class_2614 blockEntity) {
      HopperBlockEntityMixin hopperBlockEntity = (HopperBlockEntityMixin)blockEntity;
      return hopperBlockEntity.getInsertInventory(world);
   }

   @Redirect(
      method = "method_11241(Lnet/minecraft/class_1937;Lnet/minecraft/class_2615;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2614;method_11237(Lnet/minecraft/class_1937;Lnet/minecraft/class_2615;)Ljava/util/List;")
   )
   private static List<class_1542> lithiumGetInputItemEntities(class_1937 world, class_2615 hopper) {
      if (hopper instanceof HopperBlockEntityMixin hopperBlockEntity) {
         if (hopperBlockEntity.collectItemEntityTracker == null) {
            hopperBlockEntity.initCollectItemEntityTracker();
         }

         long modCount = InventoryHelper.getLithiumStackList(hopperBlockEntity).getModCount();
         if ((hopperBlockEntity.collectItemEntityTrackerWasEmpty || hopperBlockEntity.myModCountAtLastItemCollect == modCount)
            && hopperBlockEntity.collectItemEntityTracker.isUnchangedSince(hopperBlockEntity.collectItemEntityAttemptTime)) {
            hopperBlockEntity.collectItemEntityAttemptTime = hopperBlockEntity.field_12022;
            return Collections.emptyList();
         } else {
            hopperBlockEntity.myModCountAtLastItemCollect = modCount;
            hopperBlockEntity.shouldCheckSleep = false;
            List<class_1542> itemEntities = hopperBlockEntity.collectItemEntityTracker.getEntities(hopperBlockEntity.collectItemEntityBox);
            hopperBlockEntity.collectItemEntityAttemptTime = hopperBlockEntity.field_12022;
            hopperBlockEntity.collectItemEntityTrackerWasEmpty = itemEntities.isEmpty();
            return itemEntities;
         }
      } else {
         return class_2614.method_11237(world, hopper);
      }
   }

   private void cacheInsertBlockInventory(class_1263 insertInventory) {
      assert !(insertInventory instanceof class_1297);

      if (insertInventory instanceof LithiumInventory optimizedInventory) {
         this.cacheInsertLithiumInventory(optimizedInventory);
      } else {
         this.insertInventory = null;
         this.insertStackList = null;
         this.insertStackListModCount = 0L;
      }

      if (insertInventory instanceof class_2586 || insertInventory instanceof class_1258) {
         this.insertBlockInventory = insertInventory;
         if (insertInventory instanceof InventoryChangeTracker) {
            this.insertionMode = HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY;
            ((InventoryChangeTracker)insertInventory).listenForMajorInventoryChanges(this);
         } else {
            this.insertionMode = HopperCachingState.BlockInventory.BLOCK_ENTITY;
         }
      } else if (insertInventory == null) {
         this.insertBlockInventory = null;
         this.insertionMode = HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY;
      } else {
         this.insertBlockInventory = insertInventory;
         this.insertionMode = insertInventory instanceof BlockStateOnlyInventory
            ? HopperCachingState.BlockInventory.BLOCK_STATE
            : HopperCachingState.BlockInventory.UNKNOWN;
      }
   }

   private void cacheInsertLithiumInventory(LithiumInventory optimizedInventory) {
      LithiumStackList insertInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
      this.insertInventory = optimizedInventory;
      this.insertStackList = insertInventoryStackList;
      this.insertStackListModCount = insertInventoryStackList.getModCount() - 1L;
   }

   private void cacheExtractLithiumInventory(LithiumInventory optimizedInventory) {
      LithiumStackList extractInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
      this.extractInventory = optimizedInventory;
      this.extractStackList = extractInventoryStackList;
      this.extractStackListModCount = extractInventoryStackList.getModCount() - 1L;
   }

   private void cacheExtractBlockInventory(class_1263 extractInventory) {
      assert !(extractInventory instanceof class_1297);

      if (extractInventory instanceof LithiumInventory optimizedInventory) {
         this.cacheExtractLithiumInventory(optimizedInventory);
      } else {
         this.extractInventory = null;
         this.extractStackList = null;
         this.extractStackListModCount = 0L;
      }

      if (extractInventory instanceof class_2586 || extractInventory instanceof class_1258) {
         this.extractBlockInventory = extractInventory;
         if (extractInventory instanceof InventoryChangeTracker) {
            this.extractionMode = HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY;
            ((InventoryChangeTracker)extractInventory).listenForMajorInventoryChanges(this);
         } else {
            this.extractionMode = HopperCachingState.BlockInventory.BLOCK_ENTITY;
         }
      } else if (extractInventory == null) {
         this.extractBlockInventory = null;
         this.extractionMode = HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY;
      } else {
         this.extractBlockInventory = extractInventory;
         this.extractionMode = extractInventory instanceof BlockStateOnlyInventory
            ? HopperCachingState.BlockInventory.BLOCK_STATE
            : HopperCachingState.BlockInventory.UNKNOWN;
      }
   }

   public class_1263 getExtractBlockInventory(class_1937 world, class_2338 extractBlockPos, class_2680 extractBlockState) {
      class_1263 blockInventory = this.extractBlockInventory;
      if (this.extractionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY) {
         return null;
      } else if (this.extractionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
         return blockInventory;
      } else if (this.extractionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
         return blockInventory;
      } else {
         if (this.extractionMode == HopperCachingState.BlockInventory.BLOCK_ENTITY) {
            class_2586 blockEntity = Objects.requireNonNull(blockInventory);
            class_2338 pos = blockEntity.method_11016();
            if (!blockEntity.method_11015() && pos.equals(extractBlockPos)) {
               LithiumInventory optimizedInventory = this.extractInventory;
               if (this.extractInventory == null) {
                  return blockInventory;
               }

               LithiumStackList insertInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
               if (insertInventoryStackList == this.extractStackList) {
                  return optimizedInventory;
               }

               this.invalidateBlockExtractionData();
            }
         }

         blockInventory = method_57010(world, extractBlockPos, extractBlockState);
         blockInventory = HopperHelper.replaceDoubleInventory(blockInventory);
         this.cacheExtractBlockInventory(blockInventory);
         return blockInventory;
      }
   }

   public class_1263 getInsertBlockInventory(class_1937 world) {
      class_1263 blockInventory = this.insertBlockInventory;
      if (this.insertionMode == HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY) {
         return null;
      } else if (this.insertionMode == HopperCachingState.BlockInventory.BLOCK_STATE) {
         return blockInventory;
      } else if (this.insertionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
         return blockInventory;
      } else {
         if (this.insertionMode == HopperCachingState.BlockInventory.BLOCK_ENTITY) {
            class_2586 blockEntity = Objects.requireNonNull(blockInventory);
            class_2338 pos = blockEntity.method_11016();
            class_2350 direction = this.field_49101;
            class_2338 transferPos = this.method_11016().method_10093(direction);
            if (!blockEntity.method_11015() && pos.equals(transferPos)) {
               LithiumInventory optimizedInventory = this.insertInventory;
               if (this.insertInventory == null) {
                  return blockInventory;
               }

               LithiumStackList insertInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
               if (insertInventoryStackList == this.insertStackList) {
                  return optimizedInventory;
               }

               this.invalidateBlockInsertionData();
            }
         }

         class_2350 direction = this.field_49101;
         class_2338 insertBlockPos = this.method_11016().method_10093(direction);
         class_2680 blockState = world.method_8320(insertBlockPos);
         blockInventory = method_57010(world, insertBlockPos, blockState);
         blockInventory = HopperHelper.replaceDoubleInventory(blockInventory);
         this.cacheInsertBlockInventory(blockInventory);
         return blockInventory;
      }
   }

   public class_1263 getInsertInventory(class_1937 world) {
      class_1263 blockInventory = this.getInsertBlockInventory(world);
      if (blockInventory != null) {
         return blockInventory;
      } else {
         if (this.insertInventoryEntityTracker == null) {
            this.initInsertInventoryTracker(world);
         }

         if (this.insertInventoryEntityTracker.isUnchangedSince(this.insertInventoryEntityFailedSearchTime)) {
            this.insertInventoryEntityFailedSearchTime = this.field_12022;
            return null;
         } else {
            this.insertInventoryEntityFailedSearchTime = Long.MIN_VALUE;
            this.shouldCheckSleep = false;
            List<class_1263> inventoryEntities = this.insertInventoryEntityTracker.getEntities(this.insertInventoryEntityBox);
            if (inventoryEntities.isEmpty()) {
               this.insertInventoryEntityFailedSearchTime = this.field_12022;
               return null;
            } else {
               class_1263 inventory = inventoryEntities.get(world.field_9229.method_43048(inventoryEntities.size()));
               if (inventory instanceof LithiumInventory optimizedInventory) {
                  LithiumStackList insertInventoryStackList = InventoryHelper.getLithiumStackList(optimizedInventory);
                  if (inventory != this.insertInventory || this.insertStackList != insertInventoryStackList) {
                     this.cacheInsertLithiumInventory(optimizedInventory);
                  }
               }

               return inventory;
            }
         }
      }
   }

   private void initCollectItemEntityTracker() {
      assert this.field_11863 instanceof class_3218;

      class_238 inputBox = this.method_11262().method_989(this.field_11867.method_10263(), this.field_11867.method_10264(), this.field_11867.method_10260());
      this.collectItemEntityBox = inputBox;
      this.collectItemEntityTracker = SectionedItemEntityMovementTracker.registerAt((class_3218)this.field_11863, inputBox, class_1542.class);
      this.collectItemEntityAttemptTime = Long.MIN_VALUE;
   }

   private void initExtractInventoryTracker(class_1937 world) {
      assert world instanceof class_3218;

      class_2338 pos = this.field_11867.method_10093(class_2350.field_11036);
      this.extractInventoryEntityBox = new class_238(
         pos.method_10263(), pos.method_10264(), pos.method_10260(), pos.method_10263() + 1, pos.method_10264() + 1, pos.method_10260() + 1
      );
      this.extractInventoryEntityTracker = SectionedInventoryEntityMovementTracker.registerAt(
         (class_3218)this.field_11863, this.extractInventoryEntityBox, class_1263.class
      );
      this.extractInventoryEntityFailedSearchTime = Long.MIN_VALUE;
   }

   private void initInsertInventoryTracker(class_1937 world) {
      assert world instanceof class_3218;

      class_2350 direction = this.field_49101;
      class_2338 pos = this.field_11867.method_10093(direction);
      this.insertInventoryEntityBox = new class_238(
         pos.method_10263(), pos.method_10264(), pos.method_10260(), pos.method_10263() + 1, pos.method_10264() + 1, pos.method_10260() + 1
      );
      this.insertInventoryEntityTracker = SectionedInventoryEntityMovementTracker.registerAt(
         (class_3218)this.field_11863, this.insertInventoryEntityBox, class_1263.class
      );
      this.insertInventoryEntityFailedSearchTime = Long.MIN_VALUE;
   }

   @Inject(method = "method_31664(Lnet/minecraft/class_2680;)V", at = @At("HEAD"))
   private void invalidateOnSetCachedState(class_2680 state, CallbackInfo ci) {
      if (this.field_11863 != null
         && !this.field_11863.method_8608()
         && state.method_11654(class_2377.field_11129) != this.method_11010().method_11654(class_2377.field_11129)) {
         this.invalidateCachedData();
      }
   }

   private void invalidateCachedData() {
      this.shouldCheckSleep = false;
      this.invalidateInsertionData();
      this.invalidateExtractionData();
   }

   private void invalidateInsertionData() {
      if (this.field_11863 instanceof class_3218 serverWorld && this.insertInventoryEntityTracker != null) {
         this.insertInventoryEntityTracker.unRegister(serverWorld);
         this.insertInventoryEntityTracker = null;
         this.insertInventoryEntityBox = null;
         this.insertInventoryEntityFailedSearchTime = 0L;
      }

      this.invalidateBlockInsertionData();
   }

   private void invalidateBlockInsertionData() {
      if (this.insertionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
         assert this.insertBlockInventory != null;

         ((InventoryChangeTracker)this.insertBlockInventory).stopListenForMajorInventoryChanges(this);
      }

      this.insertionMode = HopperCachingState.BlockInventory.UNKNOWN;
      this.insertBlockInventory = null;
      this.insertInventory = null;
      this.insertStackList = null;
      this.insertStackListModCount = 0L;
      if (this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
      }
   }

   private void invalidateExtractionData() {
      if (this.field_11863 instanceof class_3218 serverWorld) {
         if (this.extractInventoryEntityTracker != null) {
            this.extractInventoryEntityTracker.unRegister(serverWorld);
            this.extractInventoryEntityTracker = null;
            this.extractInventoryEntityBox = null;
            this.extractInventoryEntityFailedSearchTime = 0L;
         }

         if (this.collectItemEntityTracker != null) {
            this.collectItemEntityTracker.unRegister(serverWorld);
            this.collectItemEntityTracker = null;
            this.collectItemEntityBox = null;
            this.collectItemEntityTrackerWasEmpty = false;
         }
      }

      this.invalidateBlockExtractionData();
   }

   private void invalidateBlockExtractionData() {
      if (this.extractionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
         assert this.extractBlockInventory != null;

         ((InventoryChangeTracker)this.extractBlockInventory).stopListenForMajorInventoryChanges(this);
      }

      this.extractionMode = HopperCachingState.BlockInventory.UNKNOWN;
      this.extractBlockInventory = null;
      this.extractInventory = null;
      this.extractStackList = null;
      this.extractStackListModCount = 0L;
      if (this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
      }
   }

   @Inject(
      method = "method_31692(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2614;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2614;method_11243(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2614;Ljava/util/function/BooleanSupplier;)Z",
         shift = Shift.AFTER
      )
   )
   private static void checkSleepingConditions(class_1937 world, class_2338 pos, class_2680 state, class_2614 blockEntity, CallbackInfo ci) {
      ((HopperBlockEntityMixin)blockEntity).checkSleepingConditions();
   }

   private void checkSleepingConditions() {
      if (!this.method_11239() && this.method_10997() != null) {
         if (this instanceof SleepingBlockEntity thisSleepingBlockEntity) {
            if (thisSleepingBlockEntity.isSleeping()) {
               return;
            }

            if (!this.shouldCheckSleep) {
               this.shouldCheckSleep = true;
               return;
            }

            if (this instanceof InventoryChangeTracker thisTracker) {
               boolean listenToExtractTracker = false;
               boolean listenToInsertTracker = false;
               boolean listenToExtractEntities = false;
               boolean listenToItemEntities = false;
               boolean listenToInsertEntities = false;
               LithiumStackList thisStackList = InventoryHelper.getLithiumStackList(this);
               if (this.extractionMode != HopperCachingState.BlockInventory.BLOCK_STATE && thisStackList.getFullSlots() != thisStackList.size()) {
                  if (this.extractionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
                     class_1263 blockInventory = this.extractBlockInventory;
                     if (this.extractStackList == null || !(blockInventory instanceof InventoryChangeTracker)) {
                        return;
                     }

                     if (this.extractStackList.maybeSendsComparatorUpdatesOnFailedExtract() && this.extractStackList.getOccupiedSlots() != 0) {
                        if (!(blockInventory instanceof ComparatorTracker comparatorTracker) || comparatorTracker.lithium$hasAnyComparatorNearby()) {
                           return;
                        }

                        listenToExtractTracker = true;
                     } else {
                        listenToExtractTracker = true;
                     }
                  } else {
                     if (this.extractionMode != HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY) {
                        return;
                     }

                     class_2680 hopperState = this.method_11010();
                     if (PlatformModCompat.INSTANCE.canHopperInteractWithApiBlockInventory((class_2614)this, hopperState, true)) {
                        return;
                     }

                     listenToExtractEntities = true;
                     class_2338 blockPos = this.method_11016().method_10084();
                     class_2680 blockState = this.method_10997().method_8320(blockPos);
                     if (!blockState.method_26234(this.method_10997(), blockPos) || blockState.method_26164(class_3481.field_49147)) {
                        listenToItemEntities = true;
                     }
                  }
               }

               if (this.insertionMode != HopperCachingState.BlockInventory.BLOCK_STATE && 0 < thisStackList.getOccupiedSlots()) {
                  if (this.insertionMode == HopperCachingState.BlockInventory.REMOVAL_TRACKING_BLOCK_ENTITY) {
                     class_1263 blockInventoryx = this.insertBlockInventory;
                     if (this.insertStackList == null || !(blockInventoryx instanceof InventoryChangeTracker)) {
                        return;
                     }

                     listenToInsertTracker = true;
                  } else {
                     if (this.insertionMode != HopperCachingState.BlockInventory.NO_BLOCK_INVENTORY) {
                        return;
                     }

                     class_2680 hopperStatex = this.method_11010();
                     if (PlatformModCompat.INSTANCE.canHopperInteractWithApiBlockInventory((class_2614)this, hopperStatex, false)) {
                        return;
                     }

                     listenToInsertEntities = true;
                  }
               }

               if (listenToExtractTracker) {
                  ((InventoryChangeTracker)this.extractBlockInventory).listenForContentChangesOnce(this.extractStackList, this);
               }

               if (listenToInsertTracker) {
                  ((InventoryChangeTracker)this.insertBlockInventory).listenForContentChangesOnce(this.insertStackList, this);
               }

               if (listenToInsertEntities) {
                  if (this.insertInventoryEntityTracker == null) {
                     return;
                  }

                  this.insertInventoryEntityTracker.listenToEntityMovementOnce(this);
               }

               if (listenToExtractEntities) {
                  if (this.extractInventoryEntityTracker == null) {
                     return;
                  }

                  this.extractInventoryEntityTracker.listenToEntityMovementOnce(this);
               }

               if (listenToItemEntities) {
                  if (this.collectItemEntityTracker == null) {
                     return;
                  }

                  this.collectItemEntityTracker.listenToEntityMovementOnce(this);
               }

               thisTracker.listenForContentChangesOnce(thisStackList, this);
               thisSleepingBlockEntity.lithium$startSleeping();
            }
         }
      }
   }

   @Override
   public void lithium$handleInventoryContentModified(class_1263 inventory) {
      if (this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
      }
   }

   @Override
   public void lithium$handleInventoryRemoved(class_1263 inventory) {
      if (this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
      }

      if (inventory == this.insertBlockInventory) {
         this.invalidateBlockInsertionData();
      }

      if (inventory == this.extractBlockInventory) {
         this.invalidateBlockExtractionData();
      }

      if (inventory == this) {
         this.invalidateCachedData();
      }
   }

   @Override
   public boolean lithium$handleComparatorAdded(class_1263 inventory) {
      if (inventory == this.extractBlockInventory && this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void lithium$handleEntityMovement(Class<?> category) {
      if (this instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.wakeUpNow();
      }
   }
}
