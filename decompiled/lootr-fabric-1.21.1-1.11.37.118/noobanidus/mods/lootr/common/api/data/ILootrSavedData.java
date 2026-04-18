package noobanidus.mods.lootr.common.api.data;

import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.IMarkChanged;
import noobanidus.mods.lootr.common.api.IOpeners;
import noobanidus.mods.lootr.common.api.IRedirect;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ILootrSavedData extends IRedirect<ILootrInfo>, ILootrInfo, IOpeners, IMarkChanged {
   void update(ILootrInfo var1);

   void refresh();

   default boolean clearInventories(class_3222 player) {
      return this.clearInventories(player.method_5667());
   }

   boolean clearInventories(UUID var1);

   default ILootrInventory getInventory(class_3222 player) {
      return this.getInventory(player.method_5667());
   }

   default ILootrInventory getOrCreateInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler) {
      if (provider.canPlayerOpen(player)) {
         ILootrInventory result = this.getInventory(player);
         return result != null ? result : this.createInventory(provider, player, filler);
      } else {
         provider.informPlayerCannotOpen(player);
         return null;
      }
   }

   ILootrInventory getInventory(UUID var1);

   ILootrInventory createInventory(ILootrInfoProvider var1, class_3222 var2, LootFiller var3);

   @Deprecated
   @Nullable
   @Override
   default ILootrInfo.LootrInfoType getInfoType() {
      return this.getRedirect().getInfoType();
   }

   @Deprecated
   @Nullable
   @Override
   default LootrBlockType getInfoBlockType() {
      return this.getRedirect().getInfoBlockType();
   }

   @Nullable
   @Override
   default ILootrType getInfoNewType() {
      return this.getRedirect().getInfoNewType();
   }

   @NotNull
   @Override
   default class_243 getInfoVec() {
      return this.getRedirect().getInfoVec();
   }

   @NotNull
   @Override
   default UUID getInfoUUID() {
      return this.getRedirect().getInfoUUID();
   }

   @Override
   default String getInfoKey() {
      return this.getRedirect().getInfoKey();
   }

   @NotNull
   @Override
   default class_2338 getInfoPos() {
      return this.getRedirect().getInfoPos();
   }

   @Nullable
   @Override
   default class_2561 getInfoDisplayName() {
      return this.getRedirect().getInfoDisplayName();
   }

   @NotNull
   @Override
   default class_5321<class_1937> getInfoDimension() {
      return this.getRedirect().getInfoDimension();
   }

   @Override
   default int getInfoContainerSize() {
      return this.getRedirect().getInfoContainerSize();
   }

   @Nullable
   @Override
   default class_1937 getInfoLevel() {
      return this.getRedirect().getInfoLevel();
   }

   @Nullable
   @Override
   default class_1263 getInfoContainer() {
      return this.getRedirect().getInfoContainer();
   }

   @Nullable
   @Override
   default class_2371<class_1799> getInfoReferenceInventory() {
      return this.getRedirect().getInfoReferenceInventory();
   }

   @Override
   default boolean isInfoReferenceInventory() {
      return this.getRedirect().isInfoReferenceInventory();
   }

   @Override
   default class_5321<class_52> getInfoLootTable() {
      return this.getRedirect().getInfoLootTable();
   }

   @Override
   default long getInfoLootSeed() {
      return this.getRedirect().getInfoLootSeed();
   }
}
