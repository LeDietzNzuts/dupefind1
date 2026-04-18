package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import javax.annotation.Nullable;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_1935;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2498;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_310;
import net.minecraft.class_3419;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3619;
import net.minecraft.class_3620;
import net.minecraft.class_3726;
import net.minecraft.class_3737;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_747;
import net.minecraft.class_7833;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContext;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting.EverlastingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.api.IUpgradeRenderer;
import net.p3pp3rf1y.sophisticatedcore.client.render.UpgradeRenderRegistry;
import net.p3pp3rf1y.sophisticatedcore.controller.IControllableStorage;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidActionResult;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidUtil;
import net.p3pp3rf1y.sophisticatedcore.renderdata.IUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.UpgradeRenderDataType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.infinity.InfinityUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.ServerStorageSoundHandler;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;
import org.joml.Vector3f;

public class BackpackBlock extends class_2248 implements class_2343, class_3737, BlockInterfaceHelper {
   public static final class_2746 LEFT_TANK = class_2746.method_11825("left_tank");
   public static final class_2746 RIGHT_TANK = class_2746.method_11825("right_tank");
   public static final class_2746 BATTERY = class_2746.method_11825("battery");
   public static final class_2753 FACING = class_2741.field_12481;
   private static final int BEDROCK_RESISTANCE = 3600000;

   public BackpackBlock() {
      this(0.8F);
   }

   public BackpackBlock(float explosionResistance) {
      super(
         class_2251.method_9637()
            .method_31710(class_3620.field_15979)
            .method_22488()
            .method_9629(0.8F, explosionResistance)
            .method_9626(class_2498.field_11543)
            .method_50012(class_3619.field_15971)
      );
      this.method_9590(
         (class_2680)((class_2680)((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043))
                  .method_11657(class_2741.field_12508, false))
               .method_11657(LEFT_TANK, false))
            .method_11657(RIGHT_TANK, false)
      );
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      return WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class)
         .map(t -> InventoryHelper.getAnalogOutputSignal(t.getBackpackWrapper().getInventoryForInputOutput()))
         .orElse(0);
   }

   public class_3610 method_9545(class_2680 state) {
      return Boolean.TRUE.equals(state.method_11654(class_2741.field_12508)) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if (Boolean.TRUE.equals(stateIn.method_11654(class_2741.field_12508))) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, class_2741.field_12508, LEFT_TANK, RIGHT_TANK, BATTERY});
   }

   @Override
   public float getExplosionResistance(class_2680 state, class_1922 world, class_2338 pos, class_1927 explosion) {
      return this.hasEverlastingUpgrade(world, pos) ? 3600000.0F : super.method_9520();
   }

   private boolean hasEverlastingUpgrade(class_1922 world, class_2338 pos) {
      return WorldHelper.getBlockEntity(world, pos, BackpackBlockEntity.class)
         .map(be -> !be.getBackpackWrapper().getUpgradeHandler().getTypeWrappers(EverlastingUpgradeItem.TYPE).isEmpty())
         .orElse(false);
   }

   public class_265 method_9530(class_2680 state, class_1922 worldIn, class_2338 pos, class_3726 context) {
      return BackpackShapes.getShape(
         this,
         (class_2350)state.method_11654(FACING),
         (Boolean)state.method_11654(LEFT_TANK),
         (Boolean)state.method_11654(RIGHT_TANK),
         (Boolean)state.method_11654(BATTERY)
      );
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return new BackpackBlockEntity(pos, state);
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit) {
      if (level.field_9236) {
         return class_1269.field_5812;
      } else {
         class_1799 heldItem = player.method_6047();
         if (!player.method_5715() || !heldItem.method_7960()) {
            BackpackContext.Block backpackContext = new BackpackContext.Block(pos);
            player.sophisticatedCore_openMenu(
               new class_747((w, p, pl) -> new BackpackContainer(w, pl, backpackContext), this.getBackpackDisplayName(level, pos)), backpackContext::toBuffer
            );
            return class_1269.field_5812;
         } else if (hasPermissionsToPickup(player, pos)) {
            putInPlayersHandAndRemove(state, level, pos, player, class_1268.field_5808);
            return class_1269.field_5812;
         } else {
            return class_1269.field_5814;
         }
      }
   }

   private static boolean hasPermissionsToPickup(class_1657 player, class_2338 pos) {
      return WorldHelper.getBlockEntity(player.method_37908(), pos, BackpackBlockEntity.class)
         .map(
            be -> {
               if (be.getStorageWrapper()
                  .getUpgradeHandler()
                  .getTypeWrappers(InfinityUpgradeItem.TYPE)
                  .stream()
                  .anyMatch(w -> !player.method_5687(w.getPermissionLevel()))) {
                  player.method_7353(
                     SBPTranslationHelper.INSTANCE.translStatusMessage("infinity_upgrade_only_admin_pickup", new Object[0]).method_27692(class_124.field_1061),
                     true
                  );
                  return false;
               } else {
                  return true;
               }
            }
         )
         .orElse(true);
   }

   protected class_9062 method_55765(
      class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hitResult
   ) {
      if (!stack.method_7960() && FluidUtil.isFluidStorage(stack)) {
         WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class)
            .flatMap(be -> be.getBackpackWrapper().getFluidHandler())
            .ifPresent(backpackFluidHandler -> CapabilityHelper.runOnItemHandler(player, inventoryHandler -> {
               FluidActionResult resultOfEmptying = FluidUtil.tryEmptyContainerAndStow(stack, backpackFluidHandler, inventoryHandler, 81000L, player, true);
               if (resultOfEmptying.isSuccess()) {
                  player.method_6122(class_1268.field_5808, resultOfEmptying.getResult());
               } else {
                  FluidActionResult resultOfFilling = FluidUtil.tryFillContainerAndStow(stack, backpackFluidHandler, inventoryHandler, 81000L, player, true);
                  if (resultOfFilling.isSuccess()) {
                     player.method_6122(class_1268.field_5808, resultOfFilling.getResult());
                  }
               }
            }));
         return class_9062.field_47728;
      } else {
         return super.method_55765(stack, state, level, pos, player, hand, hitResult);
      }
   }

   private class_2561 getBackpackDisplayName(class_1937 level, class_2338 pos) {
      class_2561 defaultDisplayName = new class_1799((class_1935)ModItems.BACKPACK.get()).method_7964();
      return WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class)
         .map(be -> be.getBackpackWrapper().getBackpack().method_7964())
         .orElse(defaultDisplayName);
   }

   private static void putInPlayersHandAndRemove(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand) {
      class_1799 backpack = WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class)
         .map(be -> be.getBackpackWrapper().getBackpack())
         .orElse(class_1799.field_8037);
      stopBackpackSounds(backpack, level, pos);
      player.method_6122(hand, backpack.method_7972());
      player.method_7357().method_7906(backpack.method_7909(), 5);
      level.method_8650(pos, false);
      class_2498 soundType = state.method_26231();
      level.method_8396(null, pos, soundType.method_10595(), class_3419.field_15245, (soundType.method_10597() + 1.0F) / 2.0F, soundType.method_10599() * 0.8F);
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (!state.method_27852(newState.method_26204())) {
         WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class).ifPresent(IControllableStorage::removeFromController);
      }

      super.method_9536(state, level, pos, newState, isMoving);
   }

   public class_2680 method_9576(class_1937 level, class_2338 pos, class_2680 state, class_1657 player) {
      class_2680 result = super.method_9576(level, pos, state, player);
      WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class).ifPresent(IControllableStorage::removeFromController);
      return result;
   }

   private static void stopBackpackSounds(class_1799 backpack, class_1937 level, class_2338 pos) {
      BackpackWrapper.fromStack(backpack)
         .getContentsUuid()
         .ifPresent(uuid -> ServerStorageSoundHandler.stopPlayingDisc(level, class_243.method_24953(pos), uuid));
   }

   public static class_1269 playerInteract(class_1657 player, class_1937 level, class_1268 hand, class_3965 hitResult) {
      class_2338 pos = hitResult.method_17777();
      if (!player.method_5715() || !hasEmptyMainHandAndSomethingInOffhand(player) || didntInteractWithBackpack(player, level, hand, pos)) {
         return class_1269.field_5811;
      } else if (level.field_9236) {
         return class_1269.field_5812;
      } else {
         class_2680 state = level.method_8320(pos);
         if (!(state.method_26204() instanceof BackpackBlock)) {
            return class_1269.field_5811;
         } else if (!hasPermissionsToPickup(player, pos)) {
            return class_1269.field_5814;
         } else {
            putInPlayersHandAndRemove(state, level, pos, player, player.method_6047().method_7960() ? class_1268.field_5808 : class_1268.field_5810);
            return class_1269.field_5812;
         }
      }
   }

   private static boolean didntInteractWithBackpack(class_1657 player, class_1937 world, class_1268 hand, class_2338 pos) {
      return !(world.method_8320(pos).method_26204() instanceof BackpackBlock);
   }

   private static boolean hasEmptyMainHandAndSomethingInOffhand(class_1657 player) {
      return player.method_6047().method_7960() && !player.method_6079().method_7960();
   }

   public void method_9548(class_2680 state, class_1937 level, class_2338 pos, class_1297 entity) {
      super.method_9548(state, level, pos, entity);
      if (!level.field_9236 && entity instanceof class_1542 itemEntity) {
         WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class).ifPresent(be -> this.tryToPickup(level, itemEntity, be.getBackpackWrapper()));
      }
   }

   @Override
   public boolean canEntityDestroy(class_2680 state, class_1922 world, class_2338 pos, class_1297 entity) {
      return this.hasEverlastingUpgrade(world, pos) ? false : BlockInterfaceHelper.super.canEntityDestroy(state, world, pos, entity);
   }

   private void tryToPickup(class_1937 level, class_1542 itemEntity, IStorageWrapper w) {
      class_1799 remainingStack = itemEntity.method_6983().method_7972();
      remainingStack = InventoryHelper.runPickupOnPickupResponseUpgrades(level, w.getUpgradeHandler(), remainingStack, false);
      if (remainingStack.method_7947() < itemEntity.method_6983().method_7947()) {
         itemEntity.method_6979(remainingStack);
      }
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 level, class_2680 state, class_2591<T> blockEntityType) {
      return !level.field_9236
         ? createTickerHelper(
            blockEntityType,
            ModBlocks.BACKPACK_TILE_TYPE.get(),
            (l, blockPos, blockState, backpackBlockEntity) -> BackpackBlockEntity.serverTick(l, blockPos, backpackBlockEntity)
         )
         : null;
   }

   @Nullable
   protected static <E extends class_2586, A extends class_2586> class_5558<A> createTickerHelper(
      class_2591<A> typePassedIn, class_2591<E> typeExpected, class_5558<? super E> blockEntityTicker
   ) {
      return typeExpected == typePassedIn ? blockEntityTicker : null;
   }

   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 rand) {
      WorldHelper.getBlockEntity(level, pos, BackpackBlockEntity.class).ifPresent(be -> {
         RenderInfo renderInfo = be.getBackpackWrapper().getRenderInfo();
         renderUpgrades(level, rand, pos, (class_2350)state.method_11654(FACING), renderInfo);
      });
   }

   private static void renderUpgrades(class_1937 level, class_5819 rand, class_2338 pos, class_2350 facing, RenderInfo renderInfo) {
      if (!class_310.method_1551().method_1493()) {
         renderInfo.getUpgradeRenderData()
            .forEach(
               (type, data) -> UpgradeRenderRegistry.getUpgradeRenderer(type)
                  .ifPresent(renderer -> renderUpgrade(renderer, level, rand, pos, facing, (UpgradeRenderDataType<?>)type, data))
            );
      }
   }

   private static Vector3f getBackpackMiddleFacePoint(class_2338 pos, class_2350 facing, Vector3f vector) {
      Vector3f point = new Vector3f(vector);
      point.add(0.0F, 0.0F, 0.41F);
      point.rotate(class_7833.field_40715.rotationDegrees(facing.method_10144()));
      point.add(pos.method_10263() + 0.5F, pos.method_10264(), pos.method_10260() + 0.5F);
      return point;
   }

   private static <T extends IUpgradeRenderData> void renderUpgrade(
      IUpgradeRenderer<T> renderer,
      class_1937 level,
      class_5819 rand,
      class_2338 pos,
      class_2350 facing,
      UpgradeRenderDataType<?> type,
      IUpgradeRenderData data
   ) {
      type.cast(data).ifPresent(renderData -> renderer.render(level, rand, vector -> getBackpackMiddleFacePoint(pos, facing, vector), renderData));
   }
}
