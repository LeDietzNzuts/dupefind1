package net.p3pp3rf1y.sophisticatedcore.renderdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2494;
import net.minecraft.class_2497;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedBatteryUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.CookingUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public abstract class RenderInfo {
   private static final String TANKS_TAG = "tanks";
   private static final String BATTERY_TAG = "battery";
   private static final String TANK_POSITION_TAG = "position";
   private static final String TANK_INFO_TAG = "info";
   private static final String ITEM_DISPLAY_TAG = "itemDisplay";
   private static final String UPGRADES_TAG = "upgrades";
   private static final String UPGRADE_ITEMS_TAG = "upgradeItems";
   private static final Map<String, UpgradeRenderDataType<?>> RENDER_DATA_TYPES = Map.of(
      CookingUpgradeRenderData.TYPE.getName(), CookingUpgradeRenderData.TYPE, JukeboxUpgradeRenderData.TYPE.getName(), JukeboxUpgradeRenderData.TYPE
   );
   private RenderInfo.ItemDisplayRenderInfo itemDisplayRenderInfo;
   private final Supplier<Runnable> getSaveHandler;
   private final boolean showsCountsAndFillRatios;
   private final List<class_1799> upgradeItems = new ArrayList<>();
   private final Map<UpgradeRenderDataType<?>, IUpgradeRenderData> upgradeData = new HashMap<>();
   private final Map<TankPosition, IRenderedTankUpgrade.TankRenderInfo> tankRenderInfos = new LinkedHashMap<>();
   @Nullable
   private IRenderedBatteryUpgrade.BatteryRenderInfo batteryRenderInfo = null;
   private Consumer<RenderInfo> changeListener = ri -> {};

   protected RenderInfo(Supplier<Runnable> getSaveHandler) {
      this(getSaveHandler, false);
   }

   protected RenderInfo(Supplier<Runnable> getSaveHandler, boolean showsCountsAndFillRatios) {
      this.getSaveHandler = getSaveHandler;
      this.showsCountsAndFillRatios = showsCountsAndFillRatios;
      this.itemDisplayRenderInfo = new RenderInfo.ItemDisplayRenderInfo();
   }

   public RenderInfo.ItemDisplayRenderInfo getItemDisplayRenderInfo() {
      return this.itemDisplayRenderInfo;
   }

   public void setUpgradeItems(List<class_1799> upgradeItems) {
      this.upgradeItems.clear();
      this.upgradeItems.addAll(upgradeItems);
      this.serializeUpgradeItems();
      this.save();
   }

   private void serializeUpgradeItems() {
      class_2487 renderInfo = this.getRenderInfoTag().orElse(new class_2487());
      class_2499 upgradeItemsTag = new class_2499();

      for (class_1799 upgradeItem : this.upgradeItems) {
         upgradeItemsTag.add((class_2520)RegistryHelper.getRegistryAccess().<class_2487>map(upgradeItem::method_57375).orElse(new class_2487()));
      }

      renderInfo.method_10566("upgradeItems", upgradeItemsTag);
      this.serializeRenderInfo(renderInfo);
   }

   public <T extends IUpgradeRenderData> void setUpgradeRenderData(UpgradeRenderDataType<T> upgradeRenderDataType, T renderData) {
      this.upgradeData.put(upgradeRenderDataType, renderData);
      this.serializeUpgradeData(upgrades -> upgrades.method_10566(upgradeRenderDataType.getName(), renderData.serializeNBT()));
      this.save();
   }

   public <T extends IUpgradeRenderData> Optional<T> getUpgradeRenderData(UpgradeRenderDataType<T> upgradeRenderDataType) {
      return !this.upgradeData.containsKey(upgradeRenderDataType) ? Optional.empty() : upgradeRenderDataType.cast(this.upgradeData.get(upgradeRenderDataType));
   }

   private void serializeUpgradeData(Consumer<class_2487> modifyUpgradesTag) {
      class_2487 renderInfo = this.getRenderInfoTag().orElse(new class_2487());
      class_2487 upgrades = renderInfo.method_10562("upgrades");
      modifyUpgradesTag.accept(upgrades);
      renderInfo.method_10566("upgrades", upgrades);
      this.serializeRenderInfo(renderInfo);
   }

   public void refreshItemDisplayRenderInfo(
      List<RenderInfo.DisplayItem> displayItems,
      List<Integer> inaccessibleSlots,
      List<Integer> infiniteSlots,
      List<Integer> slotCounts,
      List<Float> slotFillRatios
   ) {
      this.itemDisplayRenderInfo = new RenderInfo.ItemDisplayRenderInfo(displayItems, inaccessibleSlots, infiniteSlots, slotCounts, slotFillRatios);
      class_2487 renderInfo = this.getRenderInfoTag().orElse(new class_2487());
      renderInfo.method_10566("itemDisplay", this.itemDisplayRenderInfo.serialize());
      this.serializeRenderInfo(renderInfo);
      this.save();
   }

   public void setChangeListener(Consumer<RenderInfo> changeListener) {
      this.changeListener = changeListener;
   }

   protected void save(boolean triggerChangeListener) {
      this.getSaveHandler.get().run();
      if (triggerChangeListener) {
         this.changeListener.accept(this);
      }
   }

   protected void save() {
      this.save(false);
   }

   protected abstract void serializeRenderInfo(class_2487 var1);

   protected void deserialize() {
      this.getRenderInfoTag().ifPresent(renderInfoTag -> {
         this.deserializeItemDisplay(renderInfoTag);
         this.deserializeUpgradeItems(renderInfoTag);
         this.deserializeUpgradeData(renderInfoTag);
         this.deserializeTanks(renderInfoTag);
         this.deserializeBattery(renderInfoTag);
      });
      this.changeListener.accept(this);
   }

   private void deserializeUpgradeItems(class_2487 renderInfoTag) {
      class_2499 upgradeItemsTag = renderInfoTag.method_10554("upgradeItems", 10);
      this.upgradeItems.clear();
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> {
         for (int i = 0; i < upgradeItemsTag.size(); i++) {
            this.upgradeItems.add(class_1799.method_57359(registryAccess, upgradeItemsTag.method_10602(i)));
         }
      });
   }

   private void deserializeItemDisplay(class_2487 renderInfoTag) {
      this.itemDisplayRenderInfo = RenderInfo.ItemDisplayRenderInfo.deserialize(renderInfoTag.method_10562("itemDisplay"));
   }

   protected abstract Optional<class_2487> getRenderInfoTag();

   public Map<UpgradeRenderDataType<?>, IUpgradeRenderData> getUpgradeRenderData() {
      return this.upgradeData;
   }

   public void removeUpgradeRenderData(UpgradeRenderDataType<?> type) {
      this.upgradeData.remove(type);
      this.serializeUpgradeData(upgrades -> upgrades.method_10551(type.getName()));
      this.save();
   }

   private void deserializeUpgradeData(class_2487 renderInfoTag) {
      class_2487 upgrades = renderInfoTag.method_10562("upgrades");
      upgrades.method_10541().forEach(key -> {
         if (RENDER_DATA_TYPES.containsKey(key)) {
            UpgradeRenderDataType<?> upgradeRenderDataType = RENDER_DATA_TYPES.get(key);
            this.upgradeData.put(upgradeRenderDataType, upgradeRenderDataType.deserialize(upgrades.method_10562(key)));
         }
      });
   }

   public class_2487 getNbt() {
      return this.getRenderInfoTag().orElse(new class_2487());
   }

   public void deserializeFrom(class_2487 renderInfoNbt) {
      this.resetUpgradeInfo(false);
      this.upgradeData.clear();
      this.serializeRenderInfo(renderInfoNbt);
      this.deserialize();
   }

   public void resetUpgradeInfo(boolean triggerChangeListener) {
      this.tankRenderInfos.clear();
      this.batteryRenderInfo = null;
      this.getRenderInfoTag().ifPresent(renderInfoTag -> {
         renderInfoTag.method_10551("tanks");
         renderInfoTag.method_10551("battery");
         this.serializeRenderInfo(renderInfoTag);
      });
      this.save(triggerChangeListener);
   }

   public void setTankRenderInfo(TankPosition tankPosition, IRenderedTankUpgrade.TankRenderInfo tankRenderInfo) {
      this.tankRenderInfos.put(tankPosition, tankRenderInfo);
      this.serializeTank(tankPosition, tankRenderInfo);
      this.save();
   }

   private void deserializeTanks(class_2487 renderInfoTag) {
      class_2499 tanks = renderInfoTag.method_10554("tanks", 10);

      for (int i = 0; i < tanks.size(); i++) {
         class_2487 tank = tanks.method_10602(i);
         this.tankRenderInfos
            .put(
               TankPosition.valueOf(tank.method_10558("position").toUpperCase(Locale.ENGLISH)),
               IRenderedTankUpgrade.TankRenderInfo.deserialize(tank.method_10562("info"))
            );
      }
   }

   private void deserializeBattery(class_2487 renderInfoTag) {
      this.batteryRenderInfo = NBTHelper.getCompound(renderInfoTag, "battery").map(IRenderedBatteryUpgrade.BatteryRenderInfo::deserialize).orElse(null);
   }

   private void serializeTank(TankPosition tankPosition, IRenderedTankUpgrade.TankRenderInfo tankRenderInfo) {
      class_2487 tankInfo = tankRenderInfo.serialize();
      class_2487 renderInfo = this.getRenderInfoTag().orElse(new class_2487());
      class_2499 tanks = renderInfo.method_10554("tanks", 10);
      boolean infoSet = false;

      for (int i = 0; i < tanks.size(); i++) {
         class_2487 tank = tanks.method_10602(i);
         if (tank.method_10558("position").equals(tankPosition.method_15434())) {
            tank.method_10566("info", tankInfo);
            infoSet = true;
         }
      }

      if (!infoSet) {
         class_2487 tankPositionInfo = new class_2487();
         tankPositionInfo.method_10582("position", tankPosition.method_15434());
         tankPositionInfo.method_10566("info", tankInfo);
         tanks.add(tankPositionInfo);
         renderInfo.method_10566("tanks", tanks);
      }

      this.serializeRenderInfo(renderInfo);
   }

   public Map<TankPosition, IRenderedTankUpgrade.TankRenderInfo> getTankRenderInfos() {
      return this.tankRenderInfos;
   }

   public Optional<IRenderedBatteryUpgrade.BatteryRenderInfo> getBatteryRenderInfo() {
      return Optional.ofNullable(this.batteryRenderInfo);
   }

   public void setBatteryRenderInfo(IRenderedBatteryUpgrade.BatteryRenderInfo batteryRenderInfo) {
      this.batteryRenderInfo = batteryRenderInfo;
      class_2487 batteryInfo = batteryRenderInfo.serialize();
      class_2487 renderInfo = this.getRenderInfoTag().orElse(new class_2487());
      renderInfo.method_10566("battery", batteryInfo);
      this.serializeRenderInfo(renderInfo);
      this.save();
   }

   public List<class_1799> getUpgradeItems() {
      return this.upgradeItems;
   }

   public boolean showsCountsAndFillRatios() {
      return this.showsCountsAndFillRatios;
   }

   public static class DisplayItem {
      private static final String ITEM_TAG = "item";
      private static final String ROTATION_TAG = "rotation";
      private static final String SLOT_INDEX_TAG = "slotIndex";
      private static final String DISPLAY_SIDE_TAG = "displaySide";
      private final class_1799 item;
      private final int rotation;
      private final int slotIndex;
      private final DisplaySide displaySide;

      public DisplayItem(class_1799 item, int rotation, int slotIndex, DisplaySide displaySide) {
         this.item = item;
         this.rotation = rotation;
         this.slotIndex = slotIndex;
         this.displaySide = displaySide;
      }

      private class_2487 serialize(class_2487 tag) {
         tag.method_10566("item", RegistryHelper.getRegistryAccess().<class_2520>map(this.item::method_57375).orElse(new class_2487()));
         tag.method_10569("rotation", this.rotation);
         tag.method_10569("slotIndex", this.slotIndex);
         tag.method_10582("displaySide", this.displaySide.method_15434());
         return tag;
      }

      private static RenderInfo.DisplayItem deserialize(class_2487 tag) {
         return new RenderInfo.DisplayItem(
            RegistryHelper.getRegistryAccess()
               .map(registryAccess -> class_1799.method_57359(registryAccess, tag.method_10562("item")))
               .orElse(class_1799.field_8037),
            tag.method_10550("rotation"),
            tag.method_10550("slotIndex"),
            DisplaySide.fromName(tag.method_10558("displaySide"))
         );
      }

      public class_1799 getItem() {
         return this.item;
      }

      public int getRotation() {
         return this.rotation;
      }

      public int getSlotIndex() {
         return this.slotIndex;
      }

      public DisplaySide getDisplaySide() {
         return this.displaySide;
      }
   }

   public static class ItemDisplayRenderInfo {
      private static final String ITEMS_TAG = "items";
      private static final String INACCESSIBLE_SLOTS_TAG = "inaccessibleSlots";
      private static final String INFINITE_SLOTS_TAG = "infiniteSlots";
      public static final String SLOT_COUNTS_TAG = "slotCounts";
      public static final String SLOT_FILL_RATIOS_TAG = "slotFillRatios";
      private final List<RenderInfo.DisplayItem> displayItems;
      private final List<Integer> inaccessibleSlots;
      private final List<Integer> infiniteSlots;
      private final List<Integer> slotCounts;
      private final List<Float> slotFillRatios;

      private ItemDisplayRenderInfo(
         RenderInfo.DisplayItem displayItem, List<Integer> inaccessibleSlots, List<Integer> infiniteSlots, List<Integer> slotCounts, List<Float> slotFillRatios
      ) {
         this(List.of(displayItem), inaccessibleSlots, infiniteSlots, slotCounts, slotFillRatios);
      }

      private ItemDisplayRenderInfo(
         List<RenderInfo.DisplayItem> displayItems,
         List<Integer> inaccessibleSlots,
         List<Integer> infiniteSlots,
         List<Integer> slotCounts,
         List<Float> slotFillRatios
      ) {
         this.displayItems = displayItems;
         this.inaccessibleSlots = inaccessibleSlots;
         this.infiniteSlots = infiniteSlots;
         this.slotCounts = slotCounts;
         this.slotFillRatios = slotFillRatios;
      }

      public ItemDisplayRenderInfo() {
         this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Collections.emptyList(), Collections.emptyList());
      }

      public class_2487 serialize() {
         class_2487 ret = new class_2487();
         if (this.displayItems.size() == 1) {
            this.displayItems.get(0).serialize(ret);
         } else if (this.displayItems.size() > 1) {
            NBTHelper.putList(ret, "items", this.displayItems, displayItem -> displayItem.serialize(new class_2487()));
         }

         ret.method_10539("inaccessibleSlots", this.inaccessibleSlots.stream().mapToInt(i -> i).toArray());
         ret.method_10539("infiniteSlots", this.infiniteSlots.stream().mapToInt(i -> i).toArray());
         ret.method_10539("slotCounts", this.slotCounts.stream().mapToInt(i -> i).toArray());
         NBTHelper.putList(ret, "slotFillRatios", this.slotFillRatios, class_2494::method_23244);
         return ret;
      }

      public static RenderInfo.ItemDisplayRenderInfo deserialize(class_2487 tag) {
         List<Integer> inaccessibleSlots;
         if (tag.method_10540("inaccessibleSlots") == 11) {
            inaccessibleSlots = Arrays.stream(tag.method_10561("inaccessibleSlots")).boxed().collect(Collectors.toCollection(ArrayList::new));
         } else {
            inaccessibleSlots = (List<Integer>)NBTHelper.<Integer, ArrayList<E>>getCollection(
                  tag, "inaccessibleSlots", (byte)3, t -> Optional.of(((class_2497)t).method_10701()), ArrayList::new
               )
               .orElseGet(ArrayList::new);
         }

         List<Integer> infiniteSlots = Arrays.stream(tag.method_10561("infiniteSlots")).boxed().collect(Collectors.toCollection(ArrayList::new));
         List<Integer> slotCounts = Arrays.stream(tag.method_10561("slotCounts")).boxed().collect(Collectors.toCollection(ArrayList::new));
         List<Float> slotFillRatios = (List<Float>)NBTHelper.<Float, ArrayList<E>>getCollection(
               tag, "slotFillRatios", (byte)5, t -> Optional.of(((class_2494)t).method_10700()), ArrayList::new
            )
            .orElseGet(ArrayList::new);
         if (tag.method_10545("item")) {
            return new RenderInfo.ItemDisplayRenderInfo(RenderInfo.DisplayItem.deserialize(tag), inaccessibleSlots, infiniteSlots, slotCounts, slotFillRatios);
         } else if (tag.method_10545("items")) {
            List<RenderInfo.DisplayItem> items = (List<RenderInfo.DisplayItem>)NBTHelper.<RenderInfo.DisplayItem, ArrayList<E>>getCollection(
                  tag, "items", (byte)10, stackTag -> Optional.of(RenderInfo.DisplayItem.deserialize((class_2487)stackTag)), ArrayList::new
               )
               .orElseGet(ArrayList::new);
            return new RenderInfo.ItemDisplayRenderInfo(items, inaccessibleSlots, infiniteSlots, slotCounts, slotFillRatios);
         } else {
            return new RenderInfo.ItemDisplayRenderInfo();
         }
      }

      public Optional<RenderInfo.DisplayItem> getDisplayItem() {
         return !this.displayItems.isEmpty() ? Optional.of((RenderInfo.DisplayItem)this.displayItems.getFirst()) : Optional.empty();
      }

      public List<RenderInfo.DisplayItem> getDisplayItems() {
         return this.displayItems;
      }

      public List<Integer> getInaccessibleSlots() {
         return this.inaccessibleSlots;
      }

      public List<Integer> getSlotCounts() {
         return this.slotCounts;
      }

      public List<Integer> getInfiniteSlots() {
         return this.infiniteSlots;
      }

      public List<Float> getSlotFillRatios() {
         return this.slotFillRatios;
      }
   }
}
