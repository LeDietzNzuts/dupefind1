package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1750;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2498;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_3419;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_437;
import net.minecraft.class_5151;
import net.minecraft.class_5536;
import net.minecraft.class_5630;
import net.minecraft.class_5632;
import net.minecraft.class_747;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_481.class_483;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContext;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting.EverlastingBackpackItemEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting.EverlastingUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.util.InventoryInteractionHelper;
import net.p3pp3rf1y.sophisticatedcore.Config;
import net.p3pp3rf1y.sophisticatedcore.api.IStashStorageItem;
import net.p3pp3rf1y.sophisticatedcore.api.IStashStorageItem.StashResult;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.ServerStorageSoundHandler;
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ItemBase;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public class BackpackItem extends ItemBase implements IStashStorageItem, class_5151 {
   private final IntSupplier numberOfSlots;
   private final IntSupplier numberOfUpgradeSlots;
   private final Supplier<BackpackBlock> blockSupplier;

   public BackpackItem(IntSupplier numberOfSlots, IntSupplier numberOfUpgradeSlots, Supplier<BackpackBlock> blockSupplier) {
      this(numberOfSlots, numberOfUpgradeSlots, blockSupplier, p -> p);
   }

   public BackpackItem(
      IntSupplier numberOfSlots, IntSupplier numberOfUpgradeSlots, Supplier<BackpackBlock> blockSupplier, UnaryOperator<class_1793> updateProperties
   ) {
      super(updateProperties.apply(new class_1793().method_7889(1)));
      this.numberOfSlots = numberOfSlots;
      this.numberOfUpgradeSlots = numberOfUpgradeSlots;
      this.blockSupplier = blockSupplier;
   }

   public static void setColors(class_1799 backpackStack, int mainColor, int accentColor) {
      backpackStack.sophisticatedCore_set(ModCoreDataComponents.MAIN_COLOR, mainColor);
      backpackStack.sophisticatedCore_set(ModCoreDataComponents.ACCENT_COLOR, accentColor);
   }

   public void addCreativeTabItems(Consumer<class_1799> itemConsumer) {
      super.addCreativeTabItems(itemConsumer);
      if (this == ModItems.BACKPACK.get() && Config.COMMON.enabledItems.isItemEnabled(this)) {
         for (class_1767 color : class_1767.values()) {
            class_1799 stack = new class_1799(this);
            setColors(stack, color.method_7787(), color.method_7787());
            itemConsumer.accept(stack);
         }

         int mainColor = ColorHelper.calculateColor(-3382982, -3382982, List.of(class_1767.field_7947, class_1767.field_7961));
         int accentColor = ColorHelper.calculateColor(-10342886, -10342886, List.of(class_1767.field_7966, class_1767.field_7963));
         class_1799 stack = new class_1799(this);
         setColors(stack, mainColor, accentColor);
         itemConsumer.accept(stack);
      }
   }

   public void method_7851(class_1799 stack, class_9635 context, List<class_2561> tooltip, class_1836 tooltipFlag) {
      super.method_7851(stack, context, tooltip, tooltipFlag);
      if (tooltipFlag.method_8035()) {
         BackpackWrapper.fromStack(stack)
            .getContentsUuid()
            .ifPresent(uuid -> tooltip.add(class_2561.method_43470("UUID: " + uuid).method_27692(class_124.field_1063)));
      }

      if (!class_437.method_25442()) {
         tooltip.add(
            class_2561.method_43469(
                  TranslationHelper.INSTANCE.translItemTooltip("storage") + ".press_for_contents",
                  new Object[]{class_2561.method_43471(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".shift").method_27692(class_124.field_1075)}
               )
               .method_27692(class_124.field_1080)
         );
      }
   }

   public Optional<class_5632> method_32346(class_1799 stack) {
      return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT
         ? Optional.ofNullable(BackpackItemClient.getTooltipImage(stack))
         : Optional.empty();
   }

   private boolean hasEverlastingUpgrade(class_1799 stack) {
      return !BackpackWrapper.fromStack(stack).getUpgradeHandler().getTypeWrappers(EverlastingUpgradeItem.TYPE).isEmpty();
   }

   @Nullable
   public class_1297 createEntity(class_1937 level, class_1297 entity, class_1799 itemstack) {
      if (entity instanceof class_1542 itemEntity) {
         UUIDDeduplicator.dedupeBackpackItemEntityInArea(itemEntity);
         return this.hasEverlastingUpgrade(itemstack) ? this.createEverlastingBackpack(level, (class_1542)entity, itemstack) : null;
      } else {
         return null;
      }
   }

   @Nullable
   private EverlastingBackpackItemEntity createEverlastingBackpack(class_1937 level, class_1542 itemEntity, class_1799 itemstack) {
      EverlastingBackpackItemEntity backpackItemEntity = (EverlastingBackpackItemEntity)ModItems.EVERLASTING_BACKPACK_ITEM_ENTITY.get().method_5883(level);
      if (backpackItemEntity != null) {
         backpackItemEntity.method_5814(itemEntity.method_23317(), itemEntity.method_23318(), itemEntity.method_23321());
         backpackItemEntity.method_6979(itemstack);
         backpackItemEntity.method_6982(itemEntity.field_7202);
         if (itemEntity.method_24921() != null) {
            backpackItemEntity.method_6981(itemEntity.method_24921());
         }

         backpackItemEntity.method_18799(itemEntity.method_18798());
      }

      return backpackItemEntity;
   }

   public class_1269 method_7884(class_1838 context) {
      class_1657 player = context.method_8036();
      if (player == null || !player.method_5715()) {
         return class_1269.field_5811;
      } else if (InventoryInteractionHelper.tryInventoryInteraction(context)) {
         return class_1269.field_5812;
      } else {
         class_2350 direction = player.method_5735().method_10153();
         class_1750 blockItemUseContext = new class_1750(context);
         class_1269 result = this.tryPlace(player, direction, blockItemUseContext);
         return result == class_1269.field_5811 ? super.method_7884(context) : result;
      }
   }

   public class_1269 tryPlace(@Nullable class_1657 player, class_2350 direction, class_1750 blockItemUseContext) {
      if (!blockItemUseContext.method_7716()) {
         return class_1269.field_5814;
      } else {
         class_1937 level = blockItemUseContext.method_8045();
         class_2338 pos = blockItemUseContext.method_8037();
         class_3610 fluidstate = blockItemUseContext.method_8045().method_8316(pos);
         class_2680 placementState = (class_2680)((class_2680)this.blockSupplier.get().method_9564().method_11657(BackpackBlock.FACING, direction))
            .method_11657(class_2741.field_12508, fluidstate.method_15772() == class_3612.field_15910);
         if (!this.canPlace(blockItemUseContext, placementState)) {
            return class_1269.field_5814;
         } else if (level.method_8501(pos, placementState)) {
            class_1799 backpack = blockItemUseContext.method_8041();
            WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class).ifPresent(be -> {
               be.setBackpack(this.getBackpackCopy(player, backpack));
               be.refreshRenderState();
               be.tryToAddToController();
            });
            if (!level.field_9236) {
               stopBackpackSounds(backpack, level, pos);
            }

            class_2498 soundtype = placementState.method_26231();
            level.method_8396(
               player, pos, soundtype.method_10598(), class_3419.field_15245, (soundtype.method_10597() + 1.0F) / 2.0F, soundtype.method_10599() * 0.8F
            );
            if (player == null || !player.method_7337()) {
               backpack.method_7934(1);
            }

            return class_1269.field_5812;
         } else {
            return class_1269.field_5811;
         }
      }
   }

   private static void stopBackpackSounds(class_1799 backpack, class_1937 level, class_2338 pos) {
      BackpackWrapper.fromStack(backpack)
         .getContentsUuid()
         .ifPresent(uuid -> ServerStorageSoundHandler.stopPlayingDisc(level, class_243.method_24953(pos), uuid));
   }

   private class_1799 getBackpackCopy(@Nullable class_1657 player, class_1799 backpack) {
      return player != null && player.method_7337() ? BackpackWrapper.fromStack(backpack).cloneBackpack() : backpack.method_7972();
   }

   protected boolean canPlace(class_1750 context, class_2680 state) {
      class_1657 playerentity = context.method_8036();
      class_3726 iselectioncontext = playerentity == null ? class_3726.method_16194() : class_3726.method_16195(playerentity);
      return state.method_26184(context.method_8045(), context.method_8037())
         && context.method_8045().method_8628(state, context.method_8037(), iselectioncontext);
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 stack = player.method_5998(hand);
      if (!level.field_9236) {
         String handlerName = hand == class_1268.field_5808 ? "main" : "offhand";
         int slot = hand == class_1268.field_5808 ? player.method_31548().field_7545 : 0;
         BackpackContext.Item context = new BackpackContext.Item(handlerName, slot);
         player.sophisticatedCore_openMenu(new class_747((w, p, pl) -> new BackpackContainer(w, pl, context), stack.method_7964()), context::toBuffer);
      }

      return class_1271.method_22427(stack);
   }

   public void method_7888(class_1799 stack, class_1937 level, class_1297 entity, int itemSlot, boolean isSelected) {
      if (!level.field_9236
         && entity instanceof class_1657 player
         && !player.method_7325()
         && !player.method_29504()
         && (!(Boolean)net.p3pp3rf1y.sophisticatedbackpacks.Config.SERVER.nerfsConfig.onlyWornBackpackTriggersUpgrades.get() || itemSlot <= -1)) {
         BackpackWrapper.fromStack(stack)
            .getUpgradeHandler()
            .getWrappersThatImplement(ITickableUpgrade.class)
            .forEach(upgrade -> upgrade.tick(player, player.method_37908(), player.method_24515()));
         super.method_7888(stack, level, entity, itemSlot, isSelected);
      }
   }

   public int getNumberOfSlots() {
      return this.numberOfSlots.getAsInt();
   }

   public int getNumberOfUpgradeSlots() {
      return this.numberOfUpgradeSlots.getAsInt();
   }

   public boolean onDroppedByPlayer(class_1799 item, class_1657 player) {
      return !(
         player.field_7512 instanceof BackpackContainer backpackContainer
            && backpackContainer.getVisibleStorageItem().map(visibleStorageItem -> visibleStorageItem == item).orElse(false)
      );
   }

   @Nullable
   public class_1304 method_7685() {
      return class_1304.field_6174;
   }

   public boolean shouldCauseReequipAnimation(class_1799 oldStack, class_1799 newStack, boolean slotChanged) {
      return slotChanged;
   }

   public boolean makesPiglinsNeutral(class_1799 stack, class_1309 wearer) {
      return stack.method_7909() == ModItems.GOLD_BACKPACK.get();
   }

   public Optional<class_5632> getInventoryTooltip(class_1799 stack) {
      return Optional.of(new BackpackItem.BackpackContentsTooltip(stack));
   }

   public class_1799 stash(class_1799 storageStack, class_1799 stack, boolean simulate) {
      return BackpackWrapper.fromStack(storageStack).getInventoryForUpgradeProcessing().insertItem(stack, simulate);
   }

   public StashResult getItemStashable(class_7874 registries, class_1799 storageStack, class_1799 stack) {
      IBackpackWrapper wrapper = BackpackWrapper.fromStack(storageStack);
      if (wrapper.getContentsUuid().isEmpty()) {
         return StashResult.SPACE;
      } else if (wrapper.getInventoryForUpgradeProcessing().insertItem(stack, true).method_7947() == stack.method_7947()) {
         return StashResult.NO_SPACE;
      } else {
         return !wrapper.getInventoryHandler().getSlotTracker().getItems().contains(stack.method_7909())
               && !((MemorySettingsCategory)wrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class)).matchesFilter(stack)
            ? StashResult.SPACE
            : StashResult.MATCH_AND_SPACE;
      }
   }

   public boolean method_31565(class_1799 storageStack, class_1735 slot, class_5536 action, class_1657 player) {
      if (!this.hasCreativeScreenContainerOpen(player) && storageStack.method_7947() <= 1 && slot.method_7674(player) && action == class_5536.field_27014) {
         class_1799 stackToStash = slot.method_7677();
         class_1799 stashResult = this.stash(storageStack, stackToStash, true);
         if (stashResult.method_7947() >= stackToStash.method_7947()) {
            return super.method_31565(storageStack, slot, action, player);
         } else {
            int countToTake = stackToStash.method_7947() - stashResult.method_7947();

            while (countToTake > 0) {
               class_1799 takeResult = slot.method_32753(countToTake, countToTake, player);
               if (takeResult.method_7960()) {
                  break;
               }

               this.stash(storageStack, takeResult, false);
               countToTake -= takeResult.method_7947();
            }

            return true;
         }
      } else {
         return super.method_31565(storageStack, slot, action, player);
      }
   }

   public boolean method_31566(class_1799 storageStack, class_1799 otherStack, class_1735 slot, class_5536 action, class_1657 player, class_5630 carriedAccess) {
      if (!this.hasCreativeScreenContainerOpen(player) && storageStack.method_7947() <= 1 && slot.method_7680(storageStack) && action == class_5536.field_27014
         )
       {
         class_1799 result = this.stash(storageStack, otherStack, false);
         if (result.method_7947() != otherStack.method_7947()) {
            carriedAccess.method_32332(result);
            slot.method_7673(storageStack);
            return true;
         } else {
            return super.method_31566(storageStack, otherStack, slot, action, player, carriedAccess);
         }
      } else {
         return super.method_31566(storageStack, otherStack, slot, action, player, carriedAccess);
      }
   }

   private boolean hasCreativeScreenContainerOpen(class_1657 player) {
      return player.method_37908().method_8608() && player.field_7512 instanceof class_483;
   }

   public boolean method_31568() {
      return (Boolean)net.p3pp3rf1y.sophisticatedbackpacks.Config.SERVER.canBePlacedInContainerItems.get();
   }

   public record BackpackContentsTooltip(class_1799 backpack) implements class_5632 {
      public class_1799 getBackpack() {
         return this.backpack;
      }
   }
}
