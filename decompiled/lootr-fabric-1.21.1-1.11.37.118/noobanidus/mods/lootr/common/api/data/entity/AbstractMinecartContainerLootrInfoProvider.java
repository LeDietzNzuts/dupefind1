package noobanidus.mods.lootr.common.api.data.entity;

import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1693;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record AbstractMinecartContainerLootrInfoProvider(class_1693 minecart, String cachedId) implements ILootrEntity {
   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.ENTITY;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.MINECART;
   }

   @NotNull
   @Override
   public class_243 getInfoVec() {
      return this.minecart.method_19538();
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.minecart.method_5667();
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
      return this.minecart.method_24515();
   }

   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.minecart.method_42276();
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return this.minecart.method_5477();
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.minecart.method_37908().method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return this.minecart.method_5439();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return null;
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return false;
   }

   @Override
   public long getInfoLootSeed() {
      return this.minecart.method_42277();
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.minecart.method_37908();
   }

   @Override
   public class_1263 getInfoContainer() {
      return this.minecart;
   }

   @Override
   public void markChanged() {
      this.minecart.method_5431();
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
