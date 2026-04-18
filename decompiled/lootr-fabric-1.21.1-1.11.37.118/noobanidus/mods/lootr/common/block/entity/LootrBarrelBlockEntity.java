package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2621;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3708;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5561;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrBlockEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.SimpleLootrInstance;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrBarrelBlockEntity extends class_2621 implements ILootrBlockEntity {
   protected final SimpleLootrInstance simpleLootrInstance = new SimpleLootrInstance(this::getVisualOpeners, 27);
   private final class_5561 openersCounter = new class_5561() {
      protected void method_31681(class_1937 level, class_2338 pos, class_2680 state) {
         if (!LootrBarrelBlockEntity.this.hasBeenOpened()) {
            LootrBarrelBlockEntity.this.simpleLootrInstance.setHasBeenOpened();
            LootrBarrelBlockEntity.this.markChanged();
         }

         LootrBarrelBlockEntity.this.playSound(state, class_3417.field_17604);
         LootrBarrelBlockEntity.this.updateBlockState(state, true);
         if (LootrAPI.isCustomTrapped() && LootrBarrelBlockEntity.this.isInfoReferenceInventory()) {
            class_2248 block = state.method_26204();
            level.method_8452(pos, block);
            level.method_8452(pos.method_10074(), block);
         }
      }

      protected void method_31683(class_1937 level, class_2338 pos, class_2680 state) {
         LootrBarrelBlockEntity.this.playSound(state, class_3417.field_17603);
         LootrBarrelBlockEntity.this.updateBlockState(state, false);
      }

      protected void method_31682(class_1937 level, class_2338 pos, class_2680 state, int p_155069_, int p_155070_) {
      }

      protected boolean method_31679(class_1657 player) {
         return player.field_7512 instanceof class_1707 chestMenu && chestMenu.method_7629() instanceof ILootrInventory data
            ? data.getInfo().getInfoUUID().equals(LootrBarrelBlockEntity.this.getInfoUUID())
            : false;
      }
   };

   public LootrBarrelBlockEntity(class_2338 pWorldPosition, class_2680 pBlockState) {
      super(LootrRegistry.getBarrelBlockEntity(), pWorldPosition, pBlockState);
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.BARREL;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.BARREL;
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.simpleLootrInstance.getInfoUUID();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return this.method_11010().method_28498(class_3708.field_18006) && (Boolean)this.method_11010().method_11654(class_3708.field_18006);
   }

   protected class_2371<class_1799> method_11282() {
      return this.simpleLootrInstance.getEmptyItemList();
   }

   protected void method_11281(class_2371<class_1799> pItems) {
   }

   public void method_54873(@Nullable class_1657 player) {
   }

   public void method_11014(class_2487 compound, class_7874 provider) {
      super.method_11014(compound, provider);
      this.method_54871(compound);
      this.simpleLootrInstance.loadAdditional(compound, provider);
   }

   public void method_38240(class_1799 itemstack, class_7874 provider) {
      this.simpleLootrInstance.setSavingToItem(true);
      super.method_38240(itemstack, provider);
      this.simpleLootrInstance.setSavingToItem(false);
   }

   protected void method_11007(class_2487 compound, class_7874 provider) {
      super.method_11007(compound, provider);
      this.method_54872(compound);
      this.simpleLootrInstance.saveAdditional(compound, provider, this.field_11863 != null && this.field_11863.method_8608());
   }

   protected class_2561 method_17823() {
      return class_2561.method_43471("container.barrel");
   }

   protected class_1703 method_5465(int pContainerId, class_1661 pInventory) {
      return null;
   }

   public int method_5439() {
      return this.simpleLootrInstance.getInfoContainerSize();
   }

   public void method_5435(class_1657 pPlayer) {
      if (!this.field_11865 && !pPlayer.method_7325()) {
         this.openersCounter.method_31684(pPlayer, this.method_10997(), this.method_11016(), this.method_11010());
      }
   }

   public void method_5432(class_1657 pPlayer) {
      if (!this.field_11865 && !pPlayer.method_7325()) {
         this.openersCounter.method_31685(pPlayer, this.method_10997(), this.method_11016(), this.method_11010());
      }
   }

   public void recheckOpen() {
      if (!this.field_11865) {
         this.openersCounter.method_31686(this.method_10997(), this.method_11016(), this.method_11010());
      }
   }

   protected void updateBlockState(class_2680 pState, boolean pOpen) {
      this.field_11863.method_8652(this.method_11016(), (class_2680)pState.method_11657(class_3708.field_18006, pOpen), 3);
   }

   protected void playSound(class_2680 pState, class_3414 pSound) {
      class_2382 vec3i = ((class_2350)pState.method_11654(class_3708.field_16320)).method_10163();
      double d0 = this.field_11867.method_10263() + 0.5 + vec3i.method_10263() / 2.0;
      double d1 = this.field_11867.method_10264() + 0.5 + vec3i.method_10264() / 2.0;
      double d2 = this.field_11867.method_10260() + 0.5 + vec3i.method_10260() / 2.0;
      this.field_11863.method_43128(null, d0, d1, d2, pSound, class_3419.field_15245, 0.5F, this.field_11863.field_9229.method_43057() * 0.1F + 0.9F);
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.simpleLootrInstance.hasBeenOpened();
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.simpleLootrInstance.getClientOpeners();
   }

   @Override
   public boolean isClientOpened() {
      return this.simpleLootrInstance.isClientOpened();
   }

   @Override
   public void setClientOpened(boolean opened) {
      this.simpleLootrInstance.setClientOpened(opened);
   }

   @NotNull
   public class_2487 method_16887(class_7874 provider) {
      class_2487 result = super.method_16887(provider);
      this.simpleLootrInstance.fillUpdateTag(result, provider, this.field_11863 != null && this.field_11863.method_8608());
      return result;
   }

   @Nullable
   public class_2622 getUpdatePacket() {
      return class_2622.method_39026(this, class_2586::method_16887);
   }

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return this.method_11016();
   }

   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.method_54869();
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return this.method_5476();
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.method_10997().method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return this.method_5439();
   }

   @Override
   public long getInfoLootSeed() {
      return this.method_54870();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.simpleLootrInstance.getCustomInventory();
   }

   @Override
   public void setInfoReferenceInventory(class_2371<class_1799> reference) {
      this.simpleLootrInstance.setCustomInventory(reference);
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return this.isInfoReferenceInventoryInternal(this.simpleLootrInstance.isCustomInventory());
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.method_10997();
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      return LootrRegistry.getBarrelTrigger();
   }

   @Override
   public String getInfoKey() {
      return this.simpleLootrInstance.getInfoKey();
   }

   @Override
   public int getPhysicalOpenerCount() {
      return this.openersCounter.method_31678();
   }

   @Override
   public double getParticleYOffset() {
      return 1.1;
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrBarrelBlockEntity> {
      public ILootrBlockEntity apply(LootrBarrelBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getBarrelBlockEntity();
      }
   }
}
