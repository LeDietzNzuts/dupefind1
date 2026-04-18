package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.handlers.IGhostIngredientHandler;
import mezz.jei.api.gui.handlers.IGhostIngredientHandler.Target;
import mezz.jei.api.ingredients.ITypedIngredient;
import net.minecraft.class_768;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsTab;

public class SettingsGhostIngredientHandler<S extends SettingsScreen> implements IGhostIngredientHandler<S> {
   private S targetedScreen;

   public <I> List<Target<I>> getTargetsTyped(S gui, ITypedIngredient<I> ingredient, boolean doStart) {
      List<Target<I>> targets = new ArrayList<>();
      if (ingredient.getType() != VanillaTypes.ITEM_STACK) {
         return targets;
      } else {
         gui.startMouseDragHandledByOther();
         this.targetedScreen = gui;
         gui.getSettingsTabControl().getOpenTab().ifPresent(tab -> {
            if (tab instanceof MemorySettingsTab) {
               ingredient.getItemStack().ifPresent(ghostStack -> ((SettingsContainerMenu)gui.method_17577()).getStorageInventorySlots().forEach(s -> {
                  if (s.method_7677().method_7960()) {
                     targets.add(new Target<I>() {
                        public class_768 getArea() {
                           return new class_768(gui.sophisticatedCore_getGuiLeft() + s.field_7873, gui.sophisticatedCore_getGuiTop() + s.field_7872, 17, 17);
                        }

                        public void accept(I i) {
                           PacketDistributor.sendToServer(new SetMemorySlotPayload(ghostStack, s.field_7874));
                        }
                     });
                  }
               }));
            }
         });
         return targets;
      }
   }

   public void onComplete() {
      this.targetedScreen.stopMouseDragHandledByOther();
   }
}
