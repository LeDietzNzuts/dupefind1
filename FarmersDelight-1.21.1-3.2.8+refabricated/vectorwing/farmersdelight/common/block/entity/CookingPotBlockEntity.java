package vectorwing.farmersdelight.common.block.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntImmutableList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1275;
import net.minecraft.class_1303;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1732;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1863;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_3908;
import net.minecraft.class_3913;
import net.minecraft.class_5819;
import net.minecraft.class_8786;
import net.minecraft.class_9334;
import net.minecraft.class_1863.class_7266;
import net.minecraft.class_2561.class_2562;
import net.minecraft.class_2586.class_9473;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_9323.class_9324;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.CookingPotBlock;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.block.entity.inventory.CookingPotItemHandler;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;
import vectorwing.farmersdelight.refabricated.inventory.RecipeWrapper;

public class CookingPotBlockEntity
   extends SyncedBlockEntity
   implements ExtendedScreenHandlerFactory<class_2338>,
   class_3908,
   HeatableBlockEntity,
   class_1275,
   class_1732 {
   public static final int MEAL_DISPLAY_SLOT = 6;
   public static final int CONTAINER_SLOT = 7;
   public static final int OUTPUT_SLOT = 8;
   public static final int INVENTORY_SIZE = 9;
   public static final Map<class_1792, class_1792> INGREDIENT_REMAINDER_OVERRIDES = Map.ofEntries(
      Map.entry(class_1802.field_27876, class_1802.field_8550),
      Map.entry(class_1802.field_28354, class_1802.field_8550),
      Map.entry(class_1802.field_8666, class_1802.field_8550),
      Map.entry(class_1802.field_8108, class_1802.field_8550),
      Map.entry(class_1802.field_8714, class_1802.field_8550),
      Map.entry(class_1802.field_8478, class_1802.field_8550),
      Map.entry(class_1802.field_8766, class_1802.field_8428),
      Map.entry(class_1802.field_8208, class_1802.field_8428),
      Map.entry(class_1802.field_8308, class_1802.field_8428),
      Map.entry(class_1802.field_8515, class_1802.field_8428),
      Map.entry(class_1802.field_8574, class_1802.field_8469),
      Map.entry(class_1802.field_8436, class_1802.field_8469),
      Map.entry(class_1802.field_8150, class_1802.field_8469),
      Map.entry(class_1802.field_8287, class_1802.field_8469)
   );
   private final ItemStackHandler inventory = this.createHandler();
   private final ItemHandler inputHandler = new CookingPotItemHandler(this.inventory, class_2350.field_11036);
   private final ItemHandler outputHandler = new CookingPotItemHandler(this.inventory, class_2350.field_11033);
   private int cookTime;
   private int cookTimeTotal;
   private class_1799 mealContainerStack = class_1799.field_8037;
   private class_2561 customName;
   protected final class_3913 cookingPotData = this.createIntArray();
   private final Object2IntOpenHashMap<class_2960> usedRecipeTracker = new Object2IntOpenHashMap();
   private final class_7266<RecipeWrapper, CookingPotRecipe> quickCheck = class_1863.method_42302(ModRecipeTypes.COOKING.get());

   public CookingPotBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.COOKING_POT.get(), pos, state);
   }

   public static void init() {
      ItemStorage.SIDED.registerForBlockEntity(CookingPotBlockEntity::getStorage, ModBlockEntityTypes.COOKING_POT.get());
   }

   public static class_1799 getMealFromItem(class_1799 cookingPotStack) {
      return !cookingPotStack.method_31574(ModItems.COOKING_POT.get())
         ? class_1799.field_8037
         : ((ItemStackWrapper)cookingPotStack.method_57825(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY)).getStack();
   }

   public static void takeServingFromItem(class_1799 cookingPotStack) {
      if (cookingPotStack.method_31574(ModItems.COOKING_POT.get())) {
         class_1799 mealStack = ((ItemStackWrapper)cookingPotStack.method_57825(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY)).getStack();
         mealStack.method_7934(1);
         cookingPotStack.method_57379(ModDataComponents.MEAL.get(), new ItemStackWrapper(mealStack));
      }
   }

   public static class_1799 getContainerFromItem(class_1799 cookingPotStack) {
      return !cookingPotStack.method_31574(ModItems.COOKING_POT.get())
         ? class_1799.field_8037
         : ((ItemStackWrapper)cookingPotStack.method_57825(ModDataComponents.CONTAINER.get(), ItemStackWrapper.EMPTY)).getStack();
   }

   public void method_11014(class_2487 compound, class_7874 registries) {
      super.method_11014(compound, registries);
      this.inventory.deserializeNBT(registries, compound.method_10562("Inventory"));
      this.cookTime = compound.method_10550("CookTime");
      this.cookTimeTotal = compound.method_10550("CookTimeTotal");
      this.mealContainerStack = class_1799.method_57359(registries, compound.method_10562("Container"));
      if (compound.method_10573("CustomName", 8)) {
         this.customName = class_2562.method_10877(compound.method_10558("CustomName"), registries);
      }

      class_2487 compoundRecipes = compound.method_10562("RecipesUsed");

      for (String key : compoundRecipes.method_10541()) {
         this.usedRecipeTracker.put(class_2960.method_60654(key), compoundRecipes.method_10550(key));
      }
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      compound.method_10569("CookTime", this.cookTime);
      compound.method_10569("CookTimeTotal", this.cookTimeTotal);
      compound.method_10566("Container", this.mealContainerStack.method_57375(registries));
      if (this.customName != null) {
         compound.method_10582("CustomName", class_2562.method_10867(this.customName, registries));
      }

      compound.method_10566("Inventory", this.inventory.serializeNBT(registries));
      class_2487 compoundRecipes = new class_2487();
      this.usedRecipeTracker.forEach((recipeId, craftedAmount) -> compoundRecipes.method_10569(recipeId.toString(), craftedAmount));
      compound.method_10566("RecipesUsed", compoundRecipes);
   }

   private class_2487 writeItems(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      compound.method_10566("Container", this.mealContainerStack.method_57375(registries));
      compound.method_10566("Inventory", this.inventory.serializeNBT(registries));
      return compound;
   }

   public class_2487 writeMeal(class_2487 compound, class_7874 registries) {
      if (this.getMeal().method_7960()) {
         return compound;
      } else {
         ItemStackHandler drops = new ItemStackHandler(9);

         for (int i = 0; i < 9; i++) {
            drops.setStackInSlot(i, i == 6 ? this.inventory.getStackInSlot(i) : class_1799.field_8037);
         }

         if (this.customName != null) {
            compound.method_10582("CustomName", class_2562.method_10867(this.customName, registries));
         }

         compound.method_10566("Container", this.mealContainerStack.method_57358(registries));
         compound.method_10566("Inventory", drops.serializeNBT(registries));
         return compound;
      }
   }

   public class_1799 getAsItem() {
      class_1799 stack = new class_1799((class_1935)ModItems.COOKING_POT.get());
      stack.method_57365(this.method_57590());
      return stack;
   }

   public static void cookingTick(class_1937 level, class_2338 pos, class_2680 state, CookingPotBlockEntity cookingPot) {
      boolean isHeated = cookingPot.isHeated(level, pos);
      boolean didInventoryChange = false;
      if (isHeated && cookingPot.hasInput()) {
         Optional<class_8786<CookingPotRecipe>> recipe = cookingPot.getMatchingRecipe(new RecipeWrapper(cookingPot.inventory));
         if (recipe.isPresent() && cookingPot.canCook((CookingPotRecipe)recipe.get().comp_1933())) {
            didInventoryChange = cookingPot.processCooking(recipe.get(), cookingPot);
         } else {
            cookingPot.cookTime = class_3532.method_15340(cookingPot.cookTime - 2, 0, cookingPot.cookTimeTotal);
         }
      } else if (cookingPot.cookTime > 0) {
         cookingPot.cookTime = class_3532.method_15340(cookingPot.cookTime - 2, 0, cookingPot.cookTimeTotal);
      }

      class_1799 mealStack = cookingPot.getMeal();
      if (!mealStack.method_7960()) {
         if (!cookingPot.doesMealHaveContainer(mealStack)) {
            cookingPot.moveMealToOutput();
            didInventoryChange = true;
         } else if (!cookingPot.inventory.getStackInSlot(7).method_7960()) {
            cookingPot.useStoredContainersOnMeal();
            didInventoryChange = true;
         }
      }

      if (didInventoryChange) {
         cookingPot.inventoryChanged();
      }
   }

   public static void animationTick(class_1937 level, class_2338 pos, class_2680 state, CookingPotBlockEntity cookingPot) {
      if (cookingPot.isHeated(level, pos)) {
         class_5819 random = level.field_9229;
         if (random.method_43057() < 0.2F) {
            double x = pos.method_10263() + 0.5 + (random.method_43058() * 0.6 - 0.3);
            double y = pos.method_10264() + 0.7;
            double z = pos.method_10260() + 0.5 + (random.method_43058() * 0.6 - 0.3);
            level.method_8406(class_2398.field_11241, x, y, z, 0.0, 0.0, 0.0);
         }

         if (random.method_43057() < 0.05F) {
            double x = pos.method_10263() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double y = pos.method_10264() + 0.5;
            double z = pos.method_10260() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double motionY = random.method_43056() ? 0.015 : 0.005;
            level.method_8406((class_2394)ModParticleTypes.STEAM.get(), x, y, z, 0.0, motionY, 0.0);
         }
      }
   }

   private Optional<class_8786<CookingPotRecipe>> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
      return this.hasInput() ? this.quickCheck.method_42303(inventoryWrapper, this.field_11863) : Optional.empty();
   }

   public class_1799 getContainer() {
      class_1799 mealStack = this.getMeal();
      return !mealStack.method_7960() && !this.mealContainerStack.method_7960() ? this.mealContainerStack : mealStack.getRecipeRemainder();
   }

   private boolean hasInput() {
      for (int i = 0; i < 6; i++) {
         if (!this.inventory.getStackInSlot(i).method_7960()) {
            return true;
         }
      }

      return false;
   }

   protected boolean canCook(CookingPotRecipe recipe) {
      if (this.hasInput()) {
         class_1799 resultStack = recipe.assemble(new RecipeWrapper(this.inventory), this.field_11863.method_30349());
         if (resultStack.method_7960()) {
            return false;
         } else {
            class_1799 storedMealStack = this.inventory.getStackInSlot(6);
            if (storedMealStack.method_7960()) {
               return true;
            } else if (!class_1799.method_7984(storedMealStack, resultStack)) {
               return false;
            } else {
               return storedMealStack.method_7947() + resultStack.method_7947() <= Math.max(64, storedMealStack.method_7914())
                  ? true
                  : storedMealStack.method_7947() + resultStack.method_7947() <= resultStack.method_7914();
            }
         }
      } else {
         return false;
      }
   }

   private boolean processCooking(class_8786<CookingPotRecipe> recipe, CookingPotBlockEntity cookingPot) {
      this.cookTime++;
      this.cookTimeTotal = ((CookingPotRecipe)recipe.comp_1933()).getCookTime();
      if (this.cookTime < this.cookTimeTotal) {
         return false;
      } else {
         this.cookTime = 0;
         this.mealContainerStack = ((CookingPotRecipe)recipe.comp_1933()).getOutputContainer();
         class_1799 resultStack = ((CookingPotRecipe)recipe.comp_1933()).assemble(new RecipeWrapper(this.inventory), this.field_11863.method_30349());
         class_1799 storedMealStack = this.inventory.getStackInSlot(6);
         if (storedMealStack.method_7960()) {
            this.inventory.setStackInSlot(6, resultStack.method_7972());
         } else if (class_1799.method_7984(storedMealStack, resultStack)) {
            storedMealStack.method_7933(resultStack.method_7947());
         }

         cookingPot.method_7662(recipe);

         for (int i = 0; i < 6; i++) {
            class_1799 slotStack = this.inventory.getStackInSlot(i);
            if (!slotStack.getRecipeRemainder().method_7960()) {
               this.ejectIngredientRemainder(slotStack.getRecipeRemainder());
            } else if (INGREDIENT_REMAINDER_OVERRIDES.containsKey(slotStack.method_7909())) {
               this.ejectIngredientRemainder(INGREDIENT_REMAINDER_OVERRIDES.get(slotStack.method_7909()).method_7854());
            }

            if (!slotStack.method_7960()) {
               slotStack.method_7934(1);
            }
         }

         return true;
      }
   }

   protected void ejectIngredientRemainder(class_1799 remainderStack) {
      class_2350 direction = ((class_2350)this.method_11010().method_11654(CookingPotBlock.FACING)).method_10160();
      double x = this.field_11867.method_10263() + 0.5 + direction.method_10148() * 0.25;
      double y = this.field_11867.method_10264() + 0.7;
      double z = this.field_11867.method_10260() + 0.5 + direction.method_10165() * 0.25;
      ItemUtils.spawnItemEntity(this.field_11863, remainderStack, x, y, z, direction.method_10148() * 0.08F, 0.25, direction.method_10165() * 0.08F);
   }

   public void method_7662(@Nullable class_8786<?> recipe) {
      if (recipe != null) {
         class_2960 recipeID = recipe.comp_1932();
         this.usedRecipeTracker.addTo(recipeID, 1);
      }
   }

   @Nullable
   public class_8786<?> method_7663() {
      return null;
   }

   public void method_7664(class_1657 player, List<class_1799> items) {
      List<class_8786<?>> usedRecipes = this.getUsedRecipesAndPopExperience(player.method_37908(), player.method_19538());
      player.method_7254(usedRecipes);
      this.usedRecipeTracker.clear();
   }

   public List<class_8786<?>> getUsedRecipesAndPopExperience(class_1937 level, class_243 pos) {
      List<class_8786<?>> list = Lists.newArrayList();
      ObjectIterator var4 = this.usedRecipeTracker.object2IntEntrySet().iterator();

      while (var4.hasNext()) {
         Entry<class_2960> entry = (Entry<class_2960>)var4.next();
         level.method_8433().method_8130((class_2960)entry.getKey()).ifPresent(recipe -> {
            list.add((class_8786<?>)recipe);
            splitAndSpawnExperience((class_3218)level, pos, entry.getIntValue(), ((CookingPotRecipe)recipe.comp_1933()).getExperience());
         });
      }

      return list;
   }

   private static void splitAndSpawnExperience(class_3218 level, class_243 pos, int craftedAmount, float experience) {
      int expTotal = class_3532.method_15375(craftedAmount * experience);
      float expFraction = class_3532.method_22450(craftedAmount * experience);
      if (expFraction != 0.0F && Math.random() < expFraction) {
         expTotal++;
      }

      class_1303.method_31493(level, pos, expTotal);
   }

   public boolean isHeated() {
      return this.isHeated((class_1937)Preconditions.checkNotNull(this.field_11863), this.field_11867);
   }

   public ItemStackHandler getInventory() {
      return this.inventory;
   }

   public class_1799 getMeal() {
      return this.inventory.getStackInSlot(6);
   }

   public class_2371<class_1799> getDroppableInventory() {
      class_2371<class_1799> drops = class_2371.method_10211();

      for (int i = 0; i < 9; i++) {
         if (i != 6) {
            drops.add(this.inventory.getStackInSlot(i));
         }
      }

      return drops;
   }

   private void moveMealToOutput() {
      class_1799 mealStack = this.inventory.getStackInSlot(6);
      class_1799 outputStack = this.inventory.getStackInSlot(8);
      int mealCount = Math.min(mealStack.method_7947(), mealStack.method_7914() - outputStack.method_7947());
      if (outputStack.method_7960()) {
         this.inventory.setStackInSlot(8, mealStack.method_7971(mealCount));
      } else if (outputStack.method_7909() == mealStack.method_7909()) {
         mealStack.method_7934(mealCount);
         outputStack.method_7933(mealCount);
      }
   }

   private void useStoredContainersOnMeal() {
      class_1799 mealStack = this.inventory.getStackInSlot(6);
      class_1799 containerInputStack = this.inventory.getStackInSlot(7);
      class_1799 outputStack = this.inventory.getStackInSlot(8);
      if (this.isContainerValid(containerInputStack) && outputStack.method_7947() < outputStack.method_7914()) {
         int smallerStackCount = Math.min(mealStack.method_7947(), containerInputStack.method_7947());
         int mealCount = Math.min(smallerStackCount, mealStack.method_7914() - outputStack.method_7947());
         if (outputStack.method_7960()) {
            containerInputStack.method_7934(mealCount);
            this.inventory.setStackInSlot(8, mealStack.method_7971(mealCount));
         } else if (outputStack.method_7909() == mealStack.method_7909()) {
            mealStack.method_7934(mealCount);
            containerInputStack.method_7934(mealCount);
            outputStack.method_7933(mealCount);
         }
      }
   }

   public class_1799 useHeldItemOnMeal(class_1799 container) {
      if (this.isContainerValid(container) && !this.getMeal().method_7960()) {
         container.method_7934(1);
         this.inventoryChanged();
         return this.getMeal().method_7971(1);
      } else {
         return class_1799.field_8037;
      }
   }

   private boolean doesMealHaveContainer(class_1799 meal) {
      return !this.mealContainerStack.method_7960() || !meal.getRecipeRemainder().method_7960();
   }

   public boolean isContainerValid(class_1799 containerItem) {
      if (containerItem.method_7960()) {
         return false;
      } else {
         return !this.mealContainerStack.method_7960() ? class_1799.method_7984(this.mealContainerStack, containerItem) : false;
      }
   }

   public class_2561 method_5477() {
      return (class_2561)(this.customName != null ? this.customName : TextUtils.getTranslation("container.cooking_pot"));
   }

   public class_2561 method_5476() {
      return this.method_5477();
   }

   @Nullable
   public class_2561 method_5797() {
      return this.customName;
   }

   public class_1703 createMenu(int id, class_1661 player, class_1657 entity) {
      return new CookingPotMenu(id, player, this, this.cookingPotData);
   }

   @NotNull
   public Storage<ItemVariant> getStorage(@Nullable class_2350 side) {
      return side != null && !side.equals(class_2350.field_11036) ? this.outputHandler : this.inputHandler;
   }

   public void method_11012() {
      super.method_11012();
   }

   @Override
   public class_2487 method_16887(class_7874 registries) {
      return this.writeItems(new class_2487(), registries);
   }

   protected void method_57568(class_9473 componentInput) {
      super.method_57568(componentInput);
      this.customName = (class_2561)componentInput.method_58694(class_9334.field_49631);
      this.getInventory().setStackInSlot(6, ((ItemStackWrapper)componentInput.method_58695(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY)).getStack());
      this.mealContainerStack = ((ItemStackWrapper)componentInput.method_58695(ModDataComponents.CONTAINER.get(), ItemStackWrapper.EMPTY)).getStack();
   }

   protected void method_57567(class_9324 components) {
      super.method_57567(components);
      components.method_57840(class_9334.field_49631, this.customName);
      if (!this.getMeal().method_7960()) {
         components.method_57840(ModDataComponents.MEAL.get(), new ItemStackWrapper(this.getMeal()));
      }

      if (!this.getContainer().method_7960()) {
         components.method_57840(ModDataComponents.CONTAINER.get(), new ItemStackWrapper(this.getContainer()));
      }
   }

   public void method_57569(class_2487 tag) {
      tag.method_10551("CustomName");
      tag.method_10551("meal");
      tag.method_10551("container");
   }

   private ItemStackHandler createHandler() {
      return new ItemStackHandler(9) {
         @Override
         protected int getStackLimit(int slot, class_1799 stack) {
            return slot == 6 ? Math.max(64, stack.method_7914()) : super.getStackLimit(slot, stack);
         }

         @Override
         protected void onContentsChanged(int slot) {
            CookingPotBlockEntity.this.inventoryChanged();
         }

         @Override
         public IntList getInputSlotIndexes() {
            return IntImmutableList.of(IntStream.range(0, 6).toArray());
         }
      };
   }

   private class_3913 createIntArray() {
      return new class_3913() {
         public int method_17390(int index) {
            return switch (index) {
               case 0 -> CookingPotBlockEntity.this.cookTime;
               case 1 -> CookingPotBlockEntity.this.cookTimeTotal;
               default -> 0;
            };
         }

         public void method_17391(int index, int value) {
            switch (index) {
               case 0:
                  CookingPotBlockEntity.this.cookTime = value;
                  break;
               case 1:
                  CookingPotBlockEntity.this.cookTimeTotal = value;
            }
         }

         public int method_17389() {
            return 2;
         }
      };
   }

   public class_2338 getScreenOpeningData(class_3222 serverPlayer) {
      return this.method_11016();
   }
}
