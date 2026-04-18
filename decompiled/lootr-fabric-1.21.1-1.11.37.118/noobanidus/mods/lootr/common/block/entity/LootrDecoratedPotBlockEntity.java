package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1299;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8934;
import net.minecraft.class_9297;
import net.minecraft.class_9334;
import net.minecraft.class_2586.class_9473;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_8172.class_8837;
import net.minecraft.class_8181.class_9210;
import net.minecraft.class_9323.class_9324;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrBlockEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.SimpleLootrInstance;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrDecoratedPotBlockEntity extends class_2586 implements class_8934, class_9210, ILootrBlockEntity {
   public long wobbleStartedAtTick;
   @Nullable
   public class_8837 lastWobbleStyle;
   @Nullable
   private PotDecorationsAdapter decorations;
   @Nullable
   protected class_5321<class_52> lootTable;
   protected long lootTableSeed;
   private final SimpleLootrInstance lootrInstance = new SimpleLootrInstance(this::getVisualOpeners, 1);

   public LootrDecoratedPotBlockEntity(class_2338 blockPos, class_2680 blockState) {
      super(LootrRegistry.getDecoratedPotBlockEntity(), blockPos, blockState);
      this.decorations = PotDecorationsAdapter.EMPTY;
   }

   protected void method_11007(class_2487 compoundTag, class_7874 provider) {
      super.method_11007(compoundTag, provider);
      this.method_54872(compoundTag);
      this.getDecorations().save(compoundTag);
      this.lootrInstance.saveAdditional(compoundTag, provider, this.field_11863 == null || this.field_11863.method_8608());
   }

   protected void method_11014(class_2487 compoundTag, class_7874 provider) {
      super.method_11014(compoundTag, provider);
      this.decorations = this.getDecorations().load(compoundTag);
      this.method_54871(compoundTag);
      this.lootrInstance.loadAdditional(compoundTag, provider);
   }

   public class_2622 getUpdatePacket() {
      return class_2622.method_38585(this);
   }

   public class_2487 method_16887(class_7874 provider) {
      class_2487 compoundTag = super.method_16887(provider);
      this.method_11007(compoundTag, provider);
      this.lootrInstance.fillUpdateTag(compoundTag, provider, this.field_11863 != null && this.field_11863.method_8608());
      return compoundTag;
   }

   @Nullable
   public class_1799 popItem(class_3222 player) {
      ILootrInventory inventory = LootrAPI.getInventory(this, player);
      if (inventory == null) {
         return null;
      } else {
         class_1799 result = inventory.method_5438(0);
         if (result.method_7960()) {
            return null;
         } else {
            inventory.method_5447(0, class_1799.field_8037);
            inventory.method_5431();
            this.performTrigger(player);
            boolean shouldUpdate = false;
            if (!this.hasServerOpened(player)) {
               player.method_7259(LootrRegistry.getLootedStat());
               LootrRegistry.getStatTrigger().trigger(player);
            }

            if (this.addOpener(player)) {
               this.performOpen(player);
               shouldUpdate = true;
            }

            this.lootrInstance.setHasBeenOpened();
            if (shouldUpdate) {
               this.performUpdate(player);
            }

            if (LootrAPI.isCustomTrapped() && this.isInfoReferenceInventory()) {
               class_2248 block = this.method_11010().method_26204();
               this.field_11863.method_8452(this.method_11016(), block);
               this.field_11863.method_8452(this.method_11016().method_10074(), block);
            }

            return result;
         }
      }
   }

   @Override
   public void performOpen(class_3222 player) {
      ILootrBlockEntity.super.performOpen(player);
      PlatformAPI.performPotBreak(this, player);
   }

   public boolean dropContent(class_3222 player) {
      if (this.field_11863 != null && this.field_11863.method_8503() != null) {
         class_1799 theItem = this.popItem(player);
         if (theItem != null) {
            double d = class_1299.field_6052.method_17685();
            double e = 1.0 - d;
            double f = d / 2.0;
            class_2350 direction = class_2350.field_11036;
            class_2338 blockPos = this.field_11867.method_10079(direction, 1);
            double g = blockPos.method_10263() + 0.5 * e + f;
            double h = blockPos.method_10264() + 0.5 + class_1299.field_6052.method_17686() / 2.0F;
            double i = blockPos.method_10260() + 0.5 * e + f;
            class_1542 itemEntity = new class_1542(this.field_11863, g, h, i, theItem.method_7971(this.field_11863.field_9229.method_43048(21) + 10));
            itemEntity.method_18799(class_243.field_1353);
            this.field_11863.method_8649(itemEntity);

            for (class_1799 item : this.getDecorations().ordered()) {
               class_1799 sherdStack = item.method_7972();
               class_1542 sherdEntity = new class_1542(this.field_11863, g, h, i, sherdStack);
               sherdEntity.method_18799(class_243.field_1353);
               this.field_11863.method_8649(sherdEntity);
            }

            PlatformAPI.performPotBreak(this, player);
            return true;
         }
      }

      return false;
   }

   public class_2350 getDirection() {
      return (class_2350)this.method_11010().method_11654(class_2741.field_12481);
   }

   public PotDecorationsAdapter getDecorations() {
      if (this.decorations == null) {
         this.decorations = PotDecorationsAdapter.EMPTY;
      }

      return this.decorations;
   }

   public void setFromItem(class_1799 itemStack) {
      this.method_58683(itemStack);
   }

   public class_1799 getPotAsItem() {
      class_1799 itemStack = LootrRegistry.getDecoratedPotItem().method_7854();
      itemStack.method_57365(this.method_57590());
      return itemStack;
   }

   @Nullable
   public class_5321<class_52> method_54869() {
      return this.lootTable;
   }

   public void method_11285(@Nullable class_5321<class_52> resourceKey) {
      this.lootTable = resourceKey;
   }

   public long method_54870() {
      return this.lootTableSeed;
   }

   public void method_54866(long l) {
      this.lootTableSeed = l;
   }

   protected void method_57567(class_9324 builder) {
      super.method_57567(builder);
      if (this.lootTable != null) {
         builder.method_57840(class_9334.field_49626, new class_9297(this.lootTable, this.lootTableSeed));
      }
   }

   protected void method_57568(class_9473 dataComponentInput) {
      super.method_57568(dataComponentInput);
      this.decorations = LootrAPI.getDecorationsAdapter(dataComponentInput);
      class_9297 loot = (class_9297)dataComponentInput.method_58694(class_9334.field_49626);
      if (loot != null && loot.comp_2414() != null) {
         this.lootTable = loot.comp_2414();
         this.lootTableSeed = loot.comp_2415();
      }
   }

   public void method_57569(class_2487 compoundTag) {
      super.method_57569(compoundTag);
      compoundTag.method_10551("LootTable");
      compoundTag.method_10551("LootTableSeed");
   }

   public class_1799 method_54079() {
      return class_1799.field_8037;
   }

   public class_1799 method_54078(int i) {
      return class_1799.field_8037;
   }

   public void method_54077(class_1799 itemStack) {
   }

   public class_2586 method_54080() {
      return this;
   }

   public void wobble(class_8837 wobbleStyle) {
      if (this.field_11863 != null && !this.field_11863.method_8608()) {
         this.field_11863.method_8427(this.method_11016(), this.method_11010().method_26204(), 1, wobbleStyle.ordinal());
      }
   }

   public boolean method_11004(int i, int j) {
      if (this.field_11863 != null && i == 1 && j >= 0 && j < class_8837.values().length) {
         this.wobbleStartedAtTick = this.field_11863.method_8510();
         this.lastWobbleStyle = class_8837.values()[j];
         return true;
      } else {
         return super.method_11004(i, j);
      }
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.lootrInstance.getClientOpeners();
   }

   @Override
   public boolean isClientOpened() {
      return this.lootrInstance.isClientOpened();
   }

   @Override
   public void setClientOpened(boolean opened) {
      this.lootrInstance.setClientOpened(opened);
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.CHEST;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.POT;
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.lootrInstance.getInfoUUID();
   }

   @Override
   public String getInfoKey() {
      return this.lootrInstance.getInfoKey();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.lootrInstance.hasBeenOpened();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return false;
   }

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return this.method_11016();
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return null;
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.field_11863.method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return this.lootrInstance.getInfoContainerSize();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.lootrInstance.getCustomInventory();
   }

   @Override
   public void setInfoReferenceInventory(class_2371<class_1799> reference) {
      this.lootrInstance.setCustomInventory(reference);
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return this.isInfoReferenceInventoryInternal(this.lootrInstance.isCustomInventory());
   }

   @Nullable
   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.lootTable;
   }

   @Override
   public long getInfoLootSeed() {
      return this.lootTableSeed;
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.field_11863;
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      return LootrRegistry.getPotTrigger();
   }

   @Override
   public double getParticleYOffset() {
      return 1.3;
   }

   @Override
   public double[] getParticleXBounds() {
      return new double[]{0.4, 0.6};
   }

   @Override
   public double[] getParticleZBounds() {
      return new double[]{0.4, 0.6};
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrDecoratedPotBlockEntity> {
      public ILootrBlockEntity apply(LootrDecoratedPotBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getDecoratedPotBlockEntity();
      }
   }
}
