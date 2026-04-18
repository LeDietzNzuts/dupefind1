package noobanidus.mods.lootr.common.api.data;

import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_1262;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2512;
import net.minecraft.class_2520;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleLootrInstance {
   protected final class_2371<class_1799> emptyItemList;
   protected class_2371<class_1799> customInventory = null;
   protected final Set<UUID> clientOpeners = new ObjectOpenHashSet();
   protected UUID infoId = null;
   protected boolean hasBeenOpened = false;
   protected String cachedId;
   protected boolean clientOpened = false;
   protected boolean savingToItem = false;
   protected boolean providesOwnUuid = false;
   protected final Supplier<Set<UUID>> visualOpenersSupplier;

   public SimpleLootrInstance(Supplier<Set<UUID>> visualOpenersSupplier, int size) {
      this.emptyItemList = class_2371.method_10213(size, class_1799.field_8037);
      this.visualOpenersSupplier = visualOpenersSupplier;
   }

   @Deprecated
   public class_2371<class_1799> getItems() {
      return this.getEmptyItemList();
   }

   public class_2371<class_1799> getEmptyItemList() {
      return this.emptyItemList;
   }

   @Nullable
   public class_2371<class_1799> getCustomInventory() {
      return this.customInventory;
   }

   public boolean isCustomInventory() {
      return this.customInventory != null && !this.customInventory.isEmpty();
   }

   public void setCustomInventory(class_2371<class_1799> customInventory) {
      class_2371<class_1799> copy = class_2371.method_10213(customInventory.size(), class_1799.field_8037);

      for (int i = 0; i < customInventory.size(); i++) {
         copy.set(i, ((class_1799)customInventory.get(i)).method_7972());
      }

      this.customInventory = copy;
   }

   public Set<UUID> getClientOpeners() {
      return this.clientOpeners;
   }

   public boolean isClientOpened() {
      return this.clientOpened;
   }

   public void setClientOpened(boolean opened) {
      this.clientOpened = opened;
   }

   @NotNull
   public UUID getInfoUUID() {
      if (this.providesOwnUuid) {
         throw new IllegalStateException("This instance provides its own UUID but hasn't overriden `getInfoUUID`: " + this);
      } else {
         if (this.infoId == null) {
            this.infoId = UUID.randomUUID();
         }

         return this.infoId;
      }
   }

   public String getInfoKey() {
      if (this.cachedId == null) {
         this.cachedId = ILootrInfo.generateInfoKey(this.getInfoUUID());
      }

      return this.cachedId;
   }

   public boolean hasBeenOpened() {
      return this.hasBeenOpened;
   }

   public int getInfoContainerSize() {
      return this.emptyItemList.size();
   }

   public void setHasBeenOpened() {
      this.hasBeenOpened = true;
   }

   public boolean isSavingToItem() {
      return this.savingToItem;
   }

   public void setSavingToItem(boolean saving) {
      this.savingToItem = saving;
   }

   public void loadAdditional(class_2487 compound, class_7874 provder) {
      if (!this.providesOwnUuid && compound.method_25928("LootrId")) {
         this.infoId = compound.method_25926("LootrId");
      }

      if (compound.method_10573("LootrHasBeenOpened", 1)) {
         this.hasBeenOpened = compound.method_10577("LootrHasBeenOpened");
      }

      if (this.infoId == null && !this.providesOwnUuid) {
         this.getInfoUUID();
      }

      this.clientOpeners.clear();
      if (compound.method_10545("LootrOpeners")) {
         for (class_2520 thisTag : compound.method_10554("LootrOpeners", 11)) {
            this.clientOpeners.add(class_2512.method_25930(thisTag));
         }
      }

      if (compound.method_10545("customInventory") && compound.method_10545("customSize")) {
         int size = Math.max(compound.method_10550("customSize"), this.getInfoContainerSize());
         this.customInventory = class_2371.method_10213(size, class_1799.field_8037);
         class_1262.method_5429(compound.method_10562("customInventory"), this.customInventory, provder);
      }
   }

   public void saveAdditional(class_2487 compound, class_7874 provider, boolean isClientSide) {
      if (!LootrAPI.shouldDiscard() && !this.isSavingToItem() && !this.providesOwnUuid) {
         compound.method_25927("LootrId", this.getInfoUUID());
      }

      compound.method_10556("LootrHasBeenOpened", this.hasBeenOpened);
      if (isClientSide && !this.clientOpeners.isEmpty()) {
         class_2499 list = new class_2499();

         for (UUID opener : this.clientOpeners) {
            list.add(class_2512.method_25929(opener));
         }

         compound.method_10566("LootrOpeners", list);
      }

      if (this.customInventory != null && !this.customInventory.isEmpty()) {
         class_2487 itemTag = new class_2487();
         class_1262.method_5426(itemTag, this.customInventory, provider);
         compound.method_10566("customInventory", itemTag);
         compound.method_10569("customSize", this.customInventory.size());
      }
   }

   public void fillUpdateTag(class_2487 result, class_7874 provider, boolean isClientSide) {
      this.saveAdditional(result, provider, isClientSide);
      if (!isClientSide) {
         Set<UUID> currentOpeners = this.visualOpenersSupplier.get();
         if (currentOpeners != null) {
            class_2499 list = new class_2499();
            UnmodifiableIterator var6 = Sets.intersection(currentOpeners, LootrAPI.getPlayerIds()).iterator();

            while (var6.hasNext()) {
               UUID opener = (UUID)var6.next();
               list.add(class_2512.method_25929(opener));
            }

            if (!list.isEmpty()) {
               result.method_10566("LootrOpeners", list);
            }
         }
      } else {
         LootrAPI.LOG.error("Tried to fillUpdateTag on the client side for SimpleLootrInstance: {}", this);
      }
   }
}
