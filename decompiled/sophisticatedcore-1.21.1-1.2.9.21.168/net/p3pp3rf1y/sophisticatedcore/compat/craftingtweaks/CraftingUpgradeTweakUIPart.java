package net.p3pp3rf1y.sophisticatedcore.compat.craftingtweaks;

import java.util.ArrayList;
import java.util.List;
import net.blay09.mods.craftingtweaks.CraftingTweaksProviderManager;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksClientAPI;
import net.blay09.mods.craftingtweaks.api.TweakType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1735;
import net.minecraft.class_339;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.ICraftingUIPart;

public class CraftingUpgradeTweakUIPart implements ICraftingUIPart {
   @Environment(EnvType.CLIENT)
   private StorageScreenBase<?> storageScreen;
   private final List<class_339> buttons = new ArrayList<>();

   public static void register() {
      StorageScreenBase.setCraftingUIPart(new CraftingUpgradeTweakUIPart());
   }

   @Environment(EnvType.CLIENT)
   private void addButton(class_339 button) {
      this.buttons.add(button);
      this.storageScreen.method_37060(button);
      this.storageScreen.method_25429(button);
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void onCraftingSlotsHidden() {
      if (!this.buttons.isEmpty() && this.storageScreen != null) {
         this.buttons.forEach(this.storageScreen.method_25396()::remove);
         this.buttons.forEach(this.storageScreen.field_33816::remove);
         this.buttons.clear();
      }
   }

   @Override
   public int getWidth() {
      return 18;
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void setStorageScreen(StorageScreenBase<?> screen) {
      this.storageScreen = screen;
   }

   @Override
   public void onCraftingSlotsDisplayed(List<class_1735> slots) {
      if (!slots.isEmpty() && this.storageScreen != null) {
         class_1735 firstSlot = (class_1735)slots.getFirst();
         CraftingTweaksProviderManager.getDefaultCraftingGrid(this.storageScreen.method_17577())
            .ifPresent(
               craftingGrid -> {
                  this.addButton(
                     CraftingTweaksClientAPI.createTweakButtonRelative(
                        craftingGrid, this.storageScreen, this.getButtonX(firstSlot), this.getButtonY(firstSlot, 0), TweakType.Rotate
                     )
                  );
                  this.addButton(
                     CraftingTweaksClientAPI.createTweakButtonRelative(
                        craftingGrid, this.storageScreen, this.getButtonX(firstSlot), this.getButtonY(firstSlot, 1), TweakType.Balance
                     )
                  );
                  this.addButton(
                     CraftingTweaksClientAPI.createTweakButtonRelative(
                        craftingGrid, this.storageScreen, this.getButtonX(firstSlot), this.getButtonY(firstSlot, 2), TweakType.Clear
                     )
                  );
               }
            );
      }
   }

   @Environment(EnvType.CLIENT)
   private int getButtonX(class_1735 firstSlot) {
      return firstSlot.field_7873 - 19;
   }

   @Environment(EnvType.CLIENT)
   private int getButtonY(class_1735 firstSlot, int index) {
      return firstSlot.field_7872 + 18 * index;
   }
}
