package net.p3pp3rf1y.sophisticatedcore.upgrades.crafting;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1735;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;

@Environment(EnvType.CLIENT)
public interface ICraftingUIPart {
   ICraftingUIPart NOOP = new ICraftingUIPart() {
      @Override
      public void onCraftingSlotsDisplayed(List<class_1735> slots) {
      }

      @Override
      public void onCraftingSlotsHidden() {
      }

      @Override
      public int getWidth() {
         return 0;
      }

      @Override
      public void setStorageScreen(StorageScreenBase<?> screen) {
      }
   };

   void onCraftingSlotsDisplayed(List<class_1735> var1);

   void onCraftingSlotsHidden();

   int getWidth();

   void setStorageScreen(StorageScreenBase<?> var1);
}
