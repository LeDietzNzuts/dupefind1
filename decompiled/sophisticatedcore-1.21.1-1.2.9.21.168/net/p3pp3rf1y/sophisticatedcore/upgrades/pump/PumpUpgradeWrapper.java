package net.p3pp3rf1y.sophisticatedcore.upgrades.pump;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.block.BucketPickupHandlerWrapper;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2263;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidUtil;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public class PumpUpgradeWrapper extends UpgradeWrapperBase<PumpUpgradeWrapper, PumpUpgradeItem> implements ITickableUpgrade {
   private static final int DID_NOTHING_COOLDOWN_TIME = 40;
   private static final int HAND_INTERACTION_COOLDOWN_TIME = 3;
   private static final int WORLD_INTERACTION_COOLDOWN_TIME = 20;
   private static final int FLUID_HANDLER_INTERACTION_COOLDOWN_TIME = 20;
   private static final int PLAYER_SEARCH_RANGE = 3;
   private static final int PUMP_IN_WORLD_RANGE = 4;
   private static final int PUMP_IN_WORLD_RANGE_SQR = 16;
   private long lastHandActionTime = -1L;
   private final FluidFilterLogic fluidFilterLogic;
   private final PumpUpgradeConfig pumpUpgradeConfig = this.upgradeItem.getPumpUpgradeConfig();

   protected PumpUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.fluidFilterLogic = new FluidFilterLogic((Integer)this.pumpUpgradeConfig.filterSlots.get(), upgrade, upgradeSaveHandler);
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.isInCooldown(level)) {
         this.setCooldown(
            level, this.storageWrapper.getFluidHandler().map(storageFluidHandler -> this.tick(storageFluidHandler, entity, level, pos)).orElse(40)
         );
      }
   }

   private int tick(Storage<FluidVariant> storageFluidHandler, @Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (entity == null) {
         Optional<Integer> newCooldown = this.handleInWorldInteractions(storageFluidHandler, (class_1657)entity, level, pos);
         if (newCooldown.isPresent()) {
            return newCooldown.get();
         }
      } else {
         if (this.shouldInteractWithHand() && entity instanceof class_1657 player && this.handleFluidContainerInHands(player, storageFluidHandler)) {
            this.lastHandActionTime = level.method_8510();
            return 3;
         }

         Optional<Integer> newCooldown = this.handleInWorldInteractions(storageFluidHandler, (class_1657)entity, level, pos);
         if (newCooldown.isPresent()) {
            return newCooldown.get();
         }
      }

      return this.lastHandActionTime + 30L > level.method_8510() ? 3 : 40;
   }

   private Optional<Integer> handleInWorldInteractions(Storage<FluidVariant> storageFluidHandler, @Nullable class_1657 player, class_1937 level, class_2338 pos) {
      if (this.shouldInteractWithHand() && this.handleFluidContainersInHandsOfNearbyPlayers(level, pos, storageFluidHandler)) {
         this.lastHandActionTime = level.method_8510();
         return Optional.of(3);
      } else {
         if (this.shouldInteractWithWorld()) {
            Optional<Integer> newCooldown = this.interactWithWorld(level, pos, storageFluidHandler, player);
            if (newCooldown.isPresent()) {
               return newCooldown;
            }
         }

         return this.interactWithAttachedFluidHandlers(level, pos, storageFluidHandler);
      }
   }

   private Optional<Integer> interactWithAttachedFluidHandlers(class_1937 level, class_2338 pos, Storage<FluidVariant> storageFluidHandler) {
      for (class_2350 dir : class_2350.values()) {
         boolean successful = WorldHelper.getBlockEntity(level, pos.method_10081(dir.method_10163()))
            .map(
               be -> CapabilityHelper.getFromFluidHandler(
                  be,
                  dir.method_10153(),
                  fluidHandler -> this.isInput()
                     ? this.fillFromFluidHandler(fluidHandler, storageFluidHandler, this.getMaxInOut())
                     : this.fillFluidHandler(fluidHandler, storageFluidHandler, this.getMaxInOut()),
                  false
               )
            )
            .orElse(false);
         if (successful) {
            return Optional.of(20);
         }
      }

      return Optional.empty();
   }

   private long getMaxInOut() {
      return Math.max(
         81000L,
         (Integer)this.pumpUpgradeConfig.maxInputOutput.get()
               * this.storageWrapper.getNumberOfSlotRows()
               * this.getAdjustedStackMultiplier(this.storageWrapper)
            * 81L
      );
   }

   public int getAdjustedStackMultiplier(IStorageWrapper storageWrapper) {
      return 1 + (int)((Double)this.pumpUpgradeConfig.stackMultiplierRatio.get() * (storageWrapper.getInventoryHandler().getStackSizeMultiplier() - 1.0));
   }

   private Optional<Integer> interactWithWorld(class_1937 level, class_2338 pos, Storage<FluidVariant> storageFluidHandler, @Nullable class_1657 player) {
      if (this.isInput()) {
         return this.fillFromBlockInRange(level, pos, storageFluidHandler, player);
      } else {
         for (class_2350 dir : class_2350.values()) {
            class_2338 offsetPos = pos.method_10081(dir.method_10163());
            if (this.placeFluidInWorld(level, storageFluidHandler, dir, offsetPos)) {
               return Optional.of(20);
            }
         }

         return Optional.empty();
      }
   }

   private boolean placeFluidInWorld(class_1937 level, Storage<FluidVariant> storageFluidHandler, class_2350 dir, class_2338 offsetPos) {
      if (dir != class_2350.field_11036) {
         for (StorageView<FluidVariant> view : storageFluidHandler.nonEmptyViews()) {
            FluidStack tankFluid = new FluidStack(view);
            if (!tankFluid.isEmpty()
               && this.fluidFilterLogic.fluidMatches(tankFluid)
               && this.isValidForFluidPlacement(level, offsetPos)
               && FluidUtil.placeFluid(null, level, offsetPos, storageFluidHandler, (FluidVariant)view.getResource(), view.getAmount())) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isValidForFluidPlacement(class_1937 level, class_2338 offsetPos) {
      class_2680 blockState = level.method_8320(offsetPos);
      return blockState.method_26215() || !blockState.method_26227().method_15769() && !blockState.method_26227().method_15771();
   }

   private Optional<Integer> fillFromBlockInRange(class_1937 level, class_2338 basePos, Storage<FluidVariant> storageFluidHandler, @Nullable class_1657 player) {
      LinkedList<class_2338> nextPositions = new LinkedList<>();
      Set<class_2338> searchedPositions = new HashSet<>();
      nextPositions.add(basePos);

      while (!nextPositions.isEmpty()) {
         class_2338 pos = nextPositions.poll();
         if (this.fillFromBlock(level, pos, storageFluidHandler, player)) {
            return Optional.of((int)(Math.max(1.0, Math.sqrt(basePos.method_10262(pos))) * 20.0));
         }

         for (class_2350 dir : class_2350.values()) {
            class_2338 offsetPos = pos.method_10081(dir.method_10163());
            if (!searchedPositions.contains(offsetPos)) {
               searchedPositions.add(offsetPos);
               if (basePos.method_10262(offsetPos) < 16.0) {
                  nextPositions.add(offsetPos);
               }
            }
         }
      }

      return Optional.empty();
   }

   private boolean fillFromBlock(class_1937 level, class_2338 pos, Storage<FluidVariant> storageFluidHandler, @Nullable class_1657 player) {
      class_3610 fluidState = level.method_8316(pos);
      if (!fluidState.method_15769()) {
         class_2680 state = level.method_8320(pos);
         if (state.method_26204() instanceof class_2263 bucketPickup) {
            Storage<FluidVariant> targetFluidHandler = new BucketPickupHandlerWrapper(bucketPickup, level, pos);
            return this.fillFromFluidHandler(targetFluidHandler, storageFluidHandler);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean handleFluidContainersInHandsOfNearbyPlayers(class_1937 level, class_2338 pos, Storage<FluidVariant> storageFluidHandler) {
      class_238 searchBox = new class_238(pos).method_1014(3.0);

      for (class_1657 player : level.method_18456()) {
         if (searchBox.method_1008(player.method_23317(), player.method_23318(), player.method_23321())
            && this.handleFluidContainerInHands(player, storageFluidHandler)) {
            return true;
         }
      }

      return false;
   }

   private boolean handleFluidContainerInHands(class_1657 player, Storage<FluidVariant> storageFluidHandler) {
      return this.handleFluidContainerInHand(storageFluidHandler, player, class_1268.field_5808)
         || this.handleFluidContainerInHand(storageFluidHandler, player, class_1268.field_5810);
   }

   private boolean handleFluidContainerInHand(Storage<FluidVariant> storageFluidHandler, class_1657 player, class_1268 hand) {
      class_1799 itemInHand = player.method_5998(hand);
      return itemInHand.method_7947() == 1 && itemInHand != this.storageWrapper.getWrappedStorageStack()
         ? CapabilityHelper.getFromFluidHandler(
            player,
            hand,
            itemFluidHandler -> this.isInput()
               ? this.fillFromHand(player, hand, itemFluidHandler, storageFluidHandler)
               : this.fillContainerInHand(player, hand, itemFluidHandler, storageFluidHandler),
            false
         )
         : false;
   }

   private boolean fillContainerInHand(class_1657 player, class_1268 hand, Storage<FluidVariant> itemFluidHandler, Storage<FluidVariant> storageFluidHandler) {
      return this.fillFluidHandler(itemFluidHandler, storageFluidHandler);
   }

   private boolean fillFluidHandler(Storage<FluidVariant> fluidHandler, Storage<FluidVariant> storageFluidHandler) {
      return this.fillFluidHandler(fluidHandler, storageFluidHandler, 81000L);
   }

   private boolean fillFluidHandler(Storage<FluidVariant> fluidHandler, Storage<FluidVariant> storageFluidHandler, long maxFill) {
      boolean ret = false;

      for (StorageView<FluidVariant> view : storageFluidHandler.nonEmptyViews()) {
         FluidStack tankFluid = new FluidStack(view);
         if (!tankFluid.isEmpty()
            && this.fluidFilterLogic.fluidMatches(tankFluid)
            && StorageUtil.move(storageFluidHandler, fluidHandler, ((FluidVariant)view.getResource())::equals, maxFill, null) == 0L) {
            ret = true;
            break;
         }
      }

      return ret;
   }

   private boolean fillFromHand(class_1657 player, class_1268 hand, Storage<FluidVariant> itemFluidHandler, Storage<FluidVariant> storageFluidHandler) {
      return this.fillFromFluidHandler(itemFluidHandler, storageFluidHandler);
   }

   private boolean fillFromFluidHandler(Storage<FluidVariant> fluidHandler, Storage<FluidVariant> storageFluidHandler) {
      return this.fillFromFluidHandler(fluidHandler, storageFluidHandler, 81000L);
   }

   private boolean fillFromFluidHandler(Storage<FluidVariant> fluidHandler, Storage<FluidVariant> storageFluidHandler, long maxDrain) {
      FluidStack containedFluid = TransferUtil.simulateExtractAnyFluid(fluidHandler, maxDrain);
      return !containedFluid.isEmpty() && this.fluidFilterLogic.fluidMatches(containedFluid)
         ? StorageUtil.move(fluidHandler, storageFluidHandler, fluidVariant -> fluidVariant.isOf(containedFluid.getFluid()), containedFluid.getAmount(), null)
            > 0L
         : false;
   }

   public void setIsInput(boolean input) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.IS_INPUT, input);
      this.save();
   }

   public boolean isInput() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.IS_INPUT, true);
   }

   public FluidFilterLogic getFluidFilterLogic() {
      return this.fluidFilterLogic;
   }

   public void setInteractWithHand(boolean interactWithHand) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.INTERACT_WITH_HAND, interactWithHand);
      this.save();
   }

   public boolean shouldInteractWithHand() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.INTERACT_WITH_HAND, this.upgradeItem.getInteractWithHandDefault());
   }

   public void setInteractWithWorld(boolean interactWithWorld) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.INTERACT_WITH_WORLD, interactWithWorld);
      this.save();
   }

   public boolean shouldInteractWithWorld() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.INTERACT_WITH_WORLD, this.upgradeItem.getInteractWithWorldDefault());
   }
}
