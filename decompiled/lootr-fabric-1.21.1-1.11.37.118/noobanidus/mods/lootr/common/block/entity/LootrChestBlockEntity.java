package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2595;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5560;
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
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.data.LootrInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrChestBlockEntity extends class_2595 implements ILootrBlockEntity {
   protected final SimpleLootrInstance simpleLootrInstance = new SimpleLootrInstance(this::getVisualOpeners, 27);
   private final class_5560 chestLidController = new class_5560();
   private final class_5561 openersCounter = new class_5561() {
      protected void method_31681(class_1937 level, class_2338 pos, class_2680 state) {
         if (!LootrChestBlockEntity.this.hasBeenOpened()) {
            LootrChestBlockEntity.this.simpleLootrInstance.setHasBeenOpened();
            LootrChestBlockEntity.this.markChanged();
         }

         LootrChestBlockEntity.playSound(level, pos, state, class_3417.field_14982);
      }

      protected void method_31683(class_1937 level, class_2338 pos, class_2680 state) {
         LootrChestBlockEntity.playSound(level, pos, state, class_3417.field_14823);
      }

      protected void method_31682(class_1937 level, class_2338 pos, class_2680 state, int p_155364_, int p_155365_) {
         LootrChestBlockEntity.this.method_11049(level, pos, state, p_155364_, p_155365_);
      }

      protected boolean method_31679(class_1657 player) {
         return player.field_7512 instanceof class_1707 menu && menu.method_7629() instanceof LootrInventory data
            ? LootrChestBlockEntity.this.getInfoUUID().equals(data.getInfo().getInfoUUID())
            : false;
      }
   };

   protected LootrChestBlockEntity(class_2591<?> p_155327_, class_2338 p_155328_, class_2680 p_155329_) {
      super(p_155327_, p_155328_, p_155329_);
   }

   public LootrChestBlockEntity(class_2338 pWorldPosition, class_2680 pBlockState) {
      this(LootrRegistry.getChestBlockEntity(), pWorldPosition, pBlockState);
   }

   @Override
   public void defaultTick(class_1937 level, class_2338 pos, class_2680 state) {
      ILootrBlockEntity.super.defaultTick(level, pos, state);
      this.chestLidController.method_31672();
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

   public boolean method_11004(int pId, int pType) {
      if (pId == 1) {
         this.chestLidController.method_31674(pType > 0);
         return true;
      } else {
         return super.method_11004(pId, pType);
      }
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

   public void method_31671() {
      if (!this.field_11865) {
         this.openersCounter.method_31686(this.method_10997(), this.method_11016(), this.method_11010());
      }
   }

   public float method_11274(float pPartialTicks) {
      return this.chestLidController.method_31673(pPartialTicks);
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

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.simpleLootrInstance.getClientOpeners();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.CHEST;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.CHEST;
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
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
      return this.method_11274(1.0F) > 0.0F;
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
      return this.simpleLootrInstance.getInfoContainerSize();
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
      return this.openersCounter.method_31678();
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      return LootrRegistry.getChestTrigger();
   }

   protected void method_11049(class_1937 level, class_2338 pos, class_2680 state, int p_155868_, int p_155869_) {
      super.method_11049(level, pos, state, p_155868_, p_155869_);
      if (LootrAPI.isCustomTrapped() && p_155868_ != p_155869_ && this.isInfoReferenceInventory()) {
         class_2248 block = state.method_26204();
         level.method_8452(pos, block);
         level.method_8452(pos.method_10074(), block);
      }
   }

   public static int getOpenCount(class_1922 pLevel, class_2338 pPos) {
      class_2680 blockstate = pLevel.method_8320(pPos);
      return blockstate.method_31709() && pLevel.method_8321(pPos) instanceof LootrChestBlockEntity chest ? chest.openersCounter.method_31678() : 0;
   }

   protected static void playSound(class_1937 pLevel, class_2338 pPos, class_2680 pState, class_3414 pSound) {
      double d0 = pPos.method_10263() + 0.5;
      double d1 = pPos.method_10264() + 0.5;
      double d2 = pPos.method_10260() + 0.5;
      pLevel.method_43128(null, d0, d1, d2, pSound, class_3419.field_15245, 0.5F, pLevel.field_9229.method_43057() * 0.1F + 0.9F);
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrChestBlockEntity> {
      public ILootrBlockEntity apply(LootrChestBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getChestBlockEntity();
      }
   }
}
