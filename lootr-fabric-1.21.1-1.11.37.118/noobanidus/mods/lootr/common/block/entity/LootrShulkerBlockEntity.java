package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1313;
import net.minecraft.class_1606;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2480;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2621;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_3619;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5712;
import net.minecraft.class_2627.class_2628;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrBlockEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.SimpleLootrInstance;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrShulkerBlockEntity extends class_2621 implements ILootrBlockEntity {
   protected final SimpleLootrInstance simpleLootrInstance = new SimpleLootrInstance(this::getVisualOpeners, 27);
   private int openCount;
   private class_2628 animationStatus = class_2628.field_12065;
   private float progress;
   private float progressOld;

   public LootrShulkerBlockEntity(class_2338 pWorldPosition, class_2680 pBlockState) {
      super(LootrRegistry.getShulkerBlockEntity(), pWorldPosition, pBlockState);
   }

   @Override
   public void defaultTick(class_1937 level, class_2338 pos, class_2680 state) {
      ILootrBlockEntity.super.defaultTick(level, pos, state);
      this.updateAnimation(level, pos, state);
   }

   private void updateAnimation(class_1937 pLevel, class_2338 pPos, class_2680 pState) {
      this.progressOld = this.progress;
      switch (this.animationStatus) {
         case field_12065:
            this.progress = 0.0F;
            break;
         case field_12066:
            this.progress += 0.1F;
            if (this.progressOld == 0.0F) {
               doNeighborUpdates(pLevel, pPos, pState);
            }

            if (this.progress >= 1.0F) {
               this.animationStatus = class_2628.field_12063;
               this.progress = 1.0F;
               doNeighborUpdates(pLevel, pPos, pState);
            }

            this.moveCollidedEntities(pLevel, pPos, pState);
            break;
         case field_12063:
            this.progress = 1.0F;
            break;
         case field_12064:
            this.progress -= 0.1F;
            if (this.progressOld == 1.0F) {
               doNeighborUpdates(pLevel, pPos, pState);
            }

            if (this.progress <= 0.0F) {
               this.animationStatus = class_2628.field_12065;
               this.progress = 0.0F;
               doNeighborUpdates(pLevel, pPos, pState);
            }
      }
   }

   public class_2628 getAnimationStatus() {
      return this.animationStatus;
   }

   public class_238 getBoundingBox(class_2680 pState) {
      return class_1606.method_33346(1.0F, (class_2350)pState.method_11654(class_2480.field_11496), 0.5F * this.getProgress(1.0F));
   }

   private void moveCollidedEntities(class_1937 pLevel, class_2338 pPos, class_2680 pState) {
      if (pState.method_26204() instanceof class_2480) {
         class_2350 direction = (class_2350)pState.method_11654(class_2480.field_11496);
         class_238 aabb = class_1606.method_33347(1.0F, direction, this.progressOld, this.progress).method_996(pPos);

         for (class_1297 entity : pLevel.method_8335(null, aabb)) {
            if (entity.method_5657() != class_3619.field_15975) {
               entity.method_5784(
                  class_1313.field_6306,
                  new class_243(
                     (aabb.method_17939() + 0.01) * direction.method_10148(),
                     (aabb.method_17940() + 0.01) * direction.method_10164(),
                     (aabb.method_17941() + 0.01) * direction.method_10165()
                  )
               );
            }
         }
      }
   }

   public int method_5439() {
      return this.simpleLootrInstance.getInfoContainerSize();
   }

   public boolean method_11004(int pEvent, int pCount) {
      if (pEvent == 1) {
         this.openCount = pCount;
         if (pCount == 0) {
            this.animationStatus = class_2628.field_12064;
         }

         if (pCount == 1) {
            this.animationStatus = class_2628.field_12066;
         }

         return true;
      } else {
         return super.method_11004(pEvent, pCount);
      }
   }

   public void method_5435(class_1657 pPlayer) {
      if (!this.field_11865 && !pPlayer.method_7325()) {
         if (!this.simpleLootrInstance.hasBeenOpened()) {
            this.simpleLootrInstance.setHasBeenOpened();
            this.markChanged();
         }

         if (this.openCount < 0) {
            this.openCount = 0;
         }

         this.openCount++;
         this.field_11863.method_8427(this.field_11867, this.method_11010().method_26204(), 1, this.openCount);
         if (this.openCount == 1) {
            this.field_11863.method_33596(pPlayer, class_5712.field_28176, this.field_11867);
            this.field_11863
               .method_8396(
                  null, this.field_11867, class_3417.field_14825, class_3419.field_15245, 0.5F, this.field_11863.field_9229.method_43057() * 0.1F + 0.9F
               );
         }

         if (!this.field_11863.method_8608() && LootrAPI.isCustomTrapped() && this.isInfoReferenceInventory()) {
            class_2248 block = this.method_11010().method_26204();
            this.field_11863.method_8452(this.method_11016(), block);
            this.field_11863.method_8452(this.method_11016().method_10074(), block);
         }
      }
   }

   public void method_5432(class_1657 pPlayer) {
      if (!this.field_11865 && !pPlayer.method_7325()) {
         this.openCount--;
         this.field_11863.method_8427(this.field_11867, this.method_11010().method_26204(), 1, this.openCount);
         if (this.openCount <= 0) {
            this.field_11863.method_33596(pPlayer, class_5712.field_28177, this.field_11867);
            this.field_11863
               .method_8396(
                  null, this.field_11867, class_3417.field_14751, class_3419.field_15245, 0.5F, this.field_11863.field_9229.method_43057() * 0.1F + 0.9F
               );
         }
      }
   }

   protected class_2561 method_17823() {
      return class_2561.method_43471("container.shulkerBox");
   }

   protected class_1703 method_5465(int pContainerId, class_1661 pInventory) {
      return null;
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

   protected class_2371<class_1799> method_11282() {
      return this.simpleLootrInstance.getEmptyItemList();
   }

   protected void method_11281(class_2371<class_1799> pItems) {
   }

   public float getProgress(float pPartialTicks) {
      return class_3532.method_16439(pPartialTicks, this.progressOld, this.progress);
   }

   public boolean isClosed() {
      return this.animationStatus == class_2628.field_12065;
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.simpleLootrInstance.getClientOpeners();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.SHULKER;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.SHULKER;
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.simpleLootrInstance.getInfoUUID();
   }

   @Override
   public String getInfoKey() {
      return this.simpleLootrInstance.getInfoKey();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.simpleLootrInstance.hasBeenOpened();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return !this.isClosed();
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

   public void method_54873(@Nullable class_1657 player) {
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
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

   @Override
   public int getPhysicalOpenerCount() {
      return this.openCount;
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      return LootrRegistry.getShulkerTrigger();
   }

   @Override
   public double getParticleYOffset() {
      return 1.1;
   }

   private static void doNeighborUpdates(class_1937 pLevel, class_2338 pPos, class_2680 pState) {
      pState.method_30101(pLevel, pPos, 3);
      pLevel.method_8452(pPos, pState.method_26204());
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrShulkerBlockEntity> {
      public ILootrBlockEntity apply(LootrShulkerBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getShulkerBlockEntity();
      }
   }
}
