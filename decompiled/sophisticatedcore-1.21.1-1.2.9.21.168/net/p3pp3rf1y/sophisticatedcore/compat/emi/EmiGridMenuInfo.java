package net.p3pp3rf1y.sophisticatedcore.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.recipe.handler.EmiCraftContext;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import dev.emi.emi.platform.EmiClient;
import dev.emi.emi.registry.EmiRecipeFiller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_310;
import net.minecraft.class_3956;
import net.minecraft.class_465;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class EmiGridMenuInfo<T extends StorageContainerMenuBase<?>> implements StandardRecipeHandler<T> {
   private final class_3956<? extends class_1860<?>> recipeType;

   public static <T extends StorageContainerMenuBase<?>> EmiGridMenuInfo<T> crafting() {
      return new EmiGridMenuInfo<>(class_3956.field_17545);
   }

   public static <T extends StorageContainerMenuBase<?>> EmiGridMenuInfo<T> smithing() {
      return new EmiGridMenuInfo<>(class_3956.field_25388);
   }

   public static <T extends StorageContainerMenuBase<?>> EmiGridMenuInfo<T> stonecutting() {
      return new EmiGridMenuInfo<>(class_3956.field_17641);
   }

   private EmiGridMenuInfo(class_3956<? extends class_1860<?>> recipeType) {
      this.recipeType = recipeType;
   }

   public List<class_1735> getInputSources(T handler) {
      List<class_1735> slots = new ArrayList<>(handler.realInventorySlots);
      handler.getOpenOrFirstCraftingContainer(this.recipeType).ifPresent(c -> slots.addAll(((ICraftingContainer)c).getRecipeSlots()));
      return slots;
   }

   public List<class_1735> getCraftingSlots(T handler) {
      List<class_1735> slots = new ArrayList<>();
      handler.getOpenOrFirstCraftingContainer(this.recipeType).ifPresent(c -> slots.addAll(((ICraftingContainer)c).getRecipeSlots()));
      return slots;
   }

   @Nullable
   public class_1735 getOutputSlot(T handler) {
      return handler.getOpenOrFirstCraftingContainer(this.recipeType).map(c -> c.getSlots().get(c.getSlots().size() - 1)).orElse(null);
   }

   public boolean supportsRecipe(EmiRecipe recipe) {
      return VanillaEmiRecipeCategories.CRAFTING.equals(recipe.getCategory()) && recipe.supportsRecipeTree();
   }

   public boolean canCraft(EmiRecipe recipe, EmiCraftContext<T> context) {
      return ((StorageContainerMenuBase)context.getScreenHandler()).getOpenOrFirstCraftingContainer(this.recipeType).isPresent()
         && super.canCraft(recipe, context);
   }

   public boolean craft(EmiRecipe recipe, EmiCraftContext<T> context) {
      T container = (T)context.getScreenHandler();
      Optional<? extends UpgradeContainerBase<?, ?>> potentialCraftingContainer = container.getOpenOrFirstCraftingContainer(this.recipeType);
      List<class_1799> stacks = EmiRecipeFiller.getStacks(this, recipe, context.getScreen(), context.getAmount());
      if (stacks != null) {
         UpgradeContainerBase<?, ?> openOrFirstCraftingContainer = (UpgradeContainerBase<?, ?>)potentialCraftingContainer.get();
         if (!openOrFirstCraftingContainer.isOpen()) {
            container.getOpenContainer().ifPresent(c -> {
               c.setIsOpen(false);
               container.setOpenTabId(-1);
            });
            openOrFirstCraftingContainer.setIsOpen(true);
            container.setOpenTabId(openOrFirstCraftingContainer.getUpgradeContainerId());
         }

         class_310.method_1551().method_1507(context.getScreen());
         if (!EmiClient.onServer) {
            return EmiRecipeFiller.clientFill(this, recipe, context.getScreen(), stacks, context.getDestination());
         } else {
            sendFillRecipe(this, context.getScreen(), switch (context.getDestination()) {
               case NONE -> 0;
               case CURSOR -> 1;
               case INVENTORY -> 2;
               default -> throw new MatchException(null, null);
            }, stacks, recipe);
            return true;
         }
      } else {
         return false;
      }
   }

   public static <T extends class_1703> void sendFillRecipe(
      StandardRecipeHandler<T> handler, class_465<T> screen, int action, List<class_1799> stacks, EmiRecipe recipe
   ) {
      T screenHandler = (T)screen.method_17577();
      List<class_1735> crafting = handler.getCraftingSlots(recipe, screenHandler);
      class_1735 output = handler.getOutputSlot(screenHandler);
      PacketDistributor.sendToServer(
         new EmiFillRecipePacket(
            screenHandler.field_7763,
            action,
            handler.getInputSources(screenHandler).stream().map(s -> s == null ? -1 : s.field_7874).toList(),
            crafting.stream().map(s -> s == null ? -1 : s.field_7874).toList(),
            output == null ? -1 : output.field_7874,
            stacks
         )
      );
   }
}
