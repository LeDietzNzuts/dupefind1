package noobanidus.mods.lootr.common.api.data.blockentity;

import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2621;
import net.minecraft.class_2627;
import net.minecraft.class_2646;
import net.minecraft.class_3719;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record RandomizableContainerBlockEntityLootrInfoProvider(
   @NotNull class_2621 blockEntity, UUID id, String cachedId, class_2371<class_1799> customInventory
) implements ILootrBlockEntity {
   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return switch (this.blockEntity) {
         case class_3719 ignored -> LootrBlockType.BARREL;
         case class_2646 ignoredx -> LootrBlockType.TRAPPED_CHEST;
         case class_2627 ignoredxx -> LootrBlockType.SHULKER;
         default -> LootrBlockType.CHEST;
      };
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.SIMPLE;
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.id();
   }

   @Override
   public String getInfoKey() {
      return this.cachedId();
   }

   @Override
   public boolean hasBeenOpened() {
      return false;
   }

   @Override
   public boolean isPhysicallyOpen() {
      return false;
   }

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return this.blockEntity.method_11016();
   }

   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.blockEntity.method_54869();
   }

   @Override
   public class_2561 getInfoDisplayName() {
      return this.blockEntity.method_5476();
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.blockEntity.method_10997().method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return this.blockEntity.method_5439();
   }

   @Override
   public long getInfoLootSeed() {
      return this.blockEntity.method_54870();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.customInventory();
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return false;
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.blockEntity.method_10997();
   }

   @Override
   public class_1263 getInfoContainer() {
      return this.blockEntity;
   }

   @Override
   public void markChanged() {
      this.blockEntity.method_5431();
   }

   @Override
   public void markDataChanged() {
      ILootrSavedData data = LootrAPI.getData(this);
      if (data != null) {
         data.markChanged();
      }
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return null;
   }

   @Override
   public boolean isClientOpened() {
      return false;
   }

   @Override
   public void setClientOpened(boolean opened) {
   }
}
