package noobanidus.mods.lootr.common.api.data;

import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.ILootrType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record BaseLootrInfo(
   @Deprecated @Nullable LootrBlockType blockType,
   @Deprecated @Nullable ILootrInfo.LootrInfoType infoType,
   @Nullable ILootrType type,
   UUID uuid,
   String cachedKey,
   class_2338 pos,
   @Nullable class_2561 name,
   class_5321<class_1937> dimension,
   int containerSize,
   class_2371<class_1799> customInventory,
   class_5321<class_52> table,
   long seed
) implements ILootrInfo {
   public static BaseLootrInfo copy(ILootrInfo info) {
      return new BaseLootrInfo(
         info.getInfoBlockType(),
         info.getInfoType(),
         info.getInfoNewType(),
         info.getInfoUUID(),
         info.getInfoKey(),
         info.getInfoPos(),
         info.getInfoDisplayName(),
         info.getInfoDimension(),
         info.getInfoContainerSize(),
         info.getInfoReferenceInventory(),
         info.getInfoLootTable(),
         info.getInfoLootSeed()
      );
   }

   @Deprecated
   @Nullable
   @Override
   public LootrBlockType getInfoBlockType() {
      return this.blockType();
   }

   @Deprecated
   @Nullable
   @Override
   public ILootrInfo.LootrInfoType getInfoType() {
      return this.infoType();
   }

   @Nullable
   @Override
   public ILootrType getInfoNewType() {
      return this.type();
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.uuid();
   }

   @Override
   public String getInfoKey() {
      return this.cachedKey();
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
      return this.pos();
   }

   @Override
   public class_2561 getInfoDisplayName() {
      return (class_2561)(this.name == null ? class_2561.method_43473() : this.name);
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.dimension();
   }

   @Override
   public int getInfoContainerSize() {
      return this.containerSize();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.customInventory();
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return this.customInventory() != null && !this.customInventory().isEmpty();
   }

   @Nullable
   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.table();
   }

   @Override
   public long getInfoLootSeed() {
      return this.seed();
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.getDefaultLevel();
   }
}
