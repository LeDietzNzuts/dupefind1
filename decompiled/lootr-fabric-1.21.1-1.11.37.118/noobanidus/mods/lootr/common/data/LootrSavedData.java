package noobanidus.mods.lootr.common.data;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.minecraft.class_1262;
import net.minecraft.class_1799;
import net.minecraft.class_18;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2512;
import net.minecraft.class_2520;
import net.minecraft.class_3222;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.BaseLootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import org.jetbrains.annotations.Nullable;

public class LootrSavedData extends class_18 implements ILootrSavedData {
   private boolean hasBeenOpened;
   private ILootrInfo info;
   private final Map<UUID, LootrInventory> inventories = new HashMap<>();
   private final Set<UUID> openers = new ObjectLinkedOpenHashSet();
   private final Set<UUID> actualOpeners = new ObjectLinkedOpenHashSet();

   protected LootrSavedData(ILootrInfo info) {
      this(info, false);
   }

   protected LootrSavedData(ILootrInfo info, boolean noCopy) {
      if (noCopy) {
         this.info = info;
      } else {
         this.info = BaseLootrInfo.copy(info);
      }
   }

   public static Supplier<LootrSavedData> fromInfo(ILootrInfo info) {
      return () -> new LootrSavedData(info);
   }

   public static LootrSavedData load(class_2487 compound, class_7874 provider) {
      ILootrInfo info = ILootrInfo.loadInfoFromTag(compound, provider);
      LootrSavedData data = new LootrSavedData(info, true);
      data.inventories.clear();
      data.openers.clear();
      data.actualOpeners.clear();
      class_2499 compounds = compound.method_10554("inventories", 10);

      for (int i = 0; i < compounds.size(); i++) {
         class_2487 thisTag = compounds.method_10602(i);
         class_2487 itemTag = thisTag.method_10562("chest");
         class_2371<class_1799> items = info.buildInitialInventory();
         class_1262.method_5429(itemTag, items, provider);
         UUID uuid = thisTag.method_25926("uuid");
         data.inventories.put(uuid, new LootrInventory(data, items));
      }

      if (compound.method_10545("openers")) {
         for (class_2520 opener : compound.method_10554("openers", 11)) {
            data.openers.add(class_2512.method_25930(opener));
         }
      }

      if (compound.method_10545("actualOpeners")) {
         for (class_2520 opener : compound.method_10554("actualOpeners", 11)) {
            data.actualOpeners.add(class_2512.method_25930(opener));
         }
      }

      if (compound.method_10545("hasBeenOpened")) {
         data.hasBeenOpened = compound.method_10577("hasBeenOpened");
      }

      return data;
   }

   public ILootrInfo getRedirect() {
      return this.info;
   }

   @Override
   public Set<UUID> getVisualOpeners() {
      return this.openers;
   }

   @Override
   public boolean addVisualOpener(UUID uuid) {
      boolean result = ILootrSavedData.super.addVisualOpener(uuid);
      if (result) {
         this.method_80();
      }

      return result;
   }

   @Override
   public boolean removeVisualOpener(UUID uuid) {
      boolean result = ILootrSavedData.super.removeVisualOpener(uuid);
      if (result) {
         this.method_80();
      }

      return result;
   }

   @Override
   public boolean addActualOpener(UUID uuid) {
      boolean result = ILootrSavedData.super.addActualOpener(uuid);
      if (result) {
         this.method_80();
      }

      return result;
   }

   private void removeOpener(UUID uuid) {
      Set<UUID> visualOpeners = this.getVisualOpeners();
      if (visualOpeners != null && visualOpeners.remove(uuid)) {
         this.method_80();
      }
   }

   @Override
   public Set<UUID> getActualOpeners() {
      return this.actualOpeners;
   }

   @Override
   public void markChanged() {
      this.method_80();
   }

   @Override
   public void markDataChanged() {
      this.markChanged();
   }

   @Nullable
   public LootrInventory getInventory(UUID id) {
      LootrInventory inventory = this.inventories.get(id);
      if (inventory != null) {
         inventory.setInfo(this);
      }

      return inventory;
   }

   public LootrInventory createInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler) {
      if (provider.canPlayerOpen(player)) {
         LootrInventory result = new LootrInventory(this, provider.buildInitialInventory());
         if (!LootrAPI.isFakePlayer(player)) {
            filler.unpackLootTable(provider, player, result);
         }

         this.inventories.put(player.method_5667(), result);
         this.hasBeenOpened = true;
         this.method_80();
         return result;
      } else {
         provider.informPlayerCannotOpen(player);
         return null;
      }
   }

   public class_2487 method_75(class_2487 compound, class_7874 provider) {
      this.info.saveInfoToTag(compound, provider);
      class_2499 compounds = new class_2499();

      for (Entry<UUID, LootrInventory> entry : this.inventories.entrySet()) {
         class_2487 thisTag = new class_2487();
         thisTag.method_25927("uuid", entry.getKey());
         thisTag.method_10566("chest", entry.getValue().saveToTag(provider));
         compounds.add(thisTag);
      }

      compound.method_10566("inventories", compounds);
      class_2499 openers = new class_2499();

      for (UUID opener : this.openers) {
         openers.add(class_2512.method_25929(opener));
      }

      compound.method_10566("openers", openers);
      class_2499 actualOpeners = new class_2499();

      for (UUID opener : this.actualOpeners) {
         actualOpeners.add(class_2512.method_25929(opener));
      }

      compound.method_10566("actualOpeners", actualOpeners);
      compound.method_10556("hasBeenOpened", this.hasBeenOpened);
      return compound;
   }

   @Override
   public void update(ILootrInfo info) {
      BaseLootrInfo infoCopy = BaseLootrInfo.copy(info);
      if (!infoCopy.equals(this.info)) {
         this.markChanged();
         this.info = info;
      }
   }

   @Override
   public void refresh() {
      this.inventories.clear();
      this.hasBeenOpened = false;
      this.markChanged();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.hasBeenOpened;
   }

   public boolean canBeCulled() {
      return !this.inventories.isEmpty() ? false : !this.hasBeenOpened();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return false;
   }

   @Override
   public boolean clearInventories(UUID id) {
      if (this.inventories.remove(id) != null) {
         this.removeOpener(id);
         this.method_80();
         return true;
      } else {
         return false;
      }
   }

   public void method_17919(File pFile, class_7874 provider) {
      if (this.method_79()) {
         pFile.getParentFile().mkdirs();
      }

      super.method_17919(pFile, provider);
   }
}
