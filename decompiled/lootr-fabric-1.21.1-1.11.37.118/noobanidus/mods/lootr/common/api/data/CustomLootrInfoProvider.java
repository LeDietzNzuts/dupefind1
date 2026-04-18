package noobanidus.mods.lootr.common.api.data;

import java.util.Set;
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

public record CustomLootrInfoProvider(
   UUID id,
   String cachedKey,
   class_2338 pos,
   int containerSize,
   class_5321<class_52> lootTable,
   long lootSeed,
   class_2561 displayName,
   class_5321<class_1937> dimension,
   class_2371<class_1799> customInventory,
   @Deprecated @Nullable ILootrInfo.LootrInfoType type,
   @Deprecated @Nullable LootrBlockType blockType,
   ILootrType newType
) implements ILootrInfoProvider {
   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return this.blockType();
   }

   @Deprecated
   @Override
   public ILootrInfo.LootrInfoType getInfoType() {
      return this.type();
   }

   @Override
   public ILootrType getInfoNewType() {
      return this.newType();
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.id();
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
   public class_5321<class_52> getInfoLootTable() {
      return this.lootTable();
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return this.displayName();
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

   @Override
   public long getInfoLootSeed() {
      return this.lootSeed();
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.getDefaultLevel();
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

   @Override
   public void markChanged() {
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
