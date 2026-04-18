package vectorwing.farmersdelight.common.block.entity;

import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1863;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_3920;
import net.minecraft.class_3956;
import net.minecraft.class_5819;
import net.minecraft.class_7924;
import net.minecraft.class_8786;
import net.minecraft.class_9696;
import net.minecraft.class_1863.class_7266;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.block.SkilletBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class SkilletBlockEntity extends SyncedBlockEntity implements HeatableBlockEntity {
   private final ItemStackHandler inventory = this.createHandler();
   private int cookingTime;
   private int cookingTimeTotal;
   private class_1799 skilletStack = new class_1799((class_1935)ModItems.SKILLET.get());
   private int fireAspectLevel;
   private final class_7266<class_9696, class_3920> quickCheck = class_1863.method_42302(class_3956.field_17549);

   public SkilletBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.SKILLET.get(), pos, state);
   }

   public static void cookingTick(class_1937 level, class_2338 pos, class_2680 state, SkilletBlockEntity skillet) {
      boolean isHeated = skillet.isHeated(level, pos);
      if ((Boolean)state.method_11654(SkilletBlock.WATERLOGGED)) {
         if (!ItemUtils.isInventoryEmpty(skillet.inventory)) {
            ItemUtils.dropItems(level, pos, skillet.inventory);
            skillet.inventoryChanged();
         }
      } else if (isHeated) {
         class_1799 cookingStack = skillet.getStoredStack();
         if (cookingStack.method_7960()) {
            skillet.cookingTime = 0;
         } else {
            skillet.cookAndOutputItems(cookingStack, level);
         }
      } else if (skillet.cookingTime > 0) {
         skillet.cookingTime = class_3532.method_15340(skillet.cookingTime - 2, 0, skillet.cookingTimeTotal);
      }
   }

   public static void animationTick(class_1937 level, class_2338 pos, class_2680 state, SkilletBlockEntity skillet) {
      if (skillet.isHeated(level, pos) && skillet.hasStoredStack()) {
         class_5819 random = level.field_9229;
         if (random.method_43057() < 0.2F) {
            double x = pos.method_10263() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double y = pos.method_10264() + 0.1;
            double z = pos.method_10260() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double motionY = random.method_43056() ? 0.015 : 0.005;
            level.method_8406((class_2394)ModParticleTypes.STEAM.get(), x, y, z, 0.0, motionY, 0.0);
         }

         if (skillet.fireAspectLevel > 0 && random.method_43057() < skillet.fireAspectLevel * 0.05F) {
            double x = pos.method_10263() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double y = pos.method_10264() + 0.1;
            double z = pos.method_10260() + 0.5 + (random.method_43058() * 0.4 - 0.2);
            double motionX = level.field_9229.method_43057() - 0.5F;
            double motionY = level.field_9229.method_43057() * 0.5F + 0.2F;
            double motionZ = level.field_9229.method_43057() - 0.5F;
            level.method_8406(class_2398.field_11208, x, y, z, motionX, motionY, motionZ);
         }
      }
   }

   private void cookAndOutputItems(class_1799 cookingStack, class_1937 level) {
      this.cookingTime++;
      if (this.cookingTime >= this.cookingTimeTotal) {
         Optional<class_8786<class_3920>> recipe = this.getMatchingRecipe(cookingStack);
         if (recipe.isPresent()) {
            class_1799 resultStack = ((class_3920)recipe.get().comp_1933()).method_59982(new class_9696(cookingStack), level.method_30349());
            class_2350 direction = ((class_2350)this.method_11010().method_11654(SkilletBlock.FACING)).method_10170();
            ItemUtils.spawnItemEntity(
               level,
               resultStack.method_7972(),
               this.field_11867.method_10263() + 0.5,
               this.field_11867.method_10264() + 0.3,
               this.field_11867.method_10260() + 0.5,
               direction.method_10148() * 0.08F,
               0.25,
               direction.method_10165() * 0.08F
            );
            this.cookingTime = 0;
            this.inventory.extractItem(0, 1, false);
         }
      }
   }

   public boolean isCooking() {
      return this.isHeated() && this.hasStoredStack();
   }

   public boolean isHeated() {
      return this.isHeated(Objects.requireNonNull(this.field_11863), this.field_11867);
   }

   private Optional<class_8786<class_3920>> getMatchingRecipe(class_1799 stack) {
      return this.quickCheck.method_42303(new class_9696(stack), (class_1937)Preconditions.checkNotNull(this.field_11863));
   }

   public void method_11014(class_2487 compound, class_7874 registries) {
      super.method_11014(compound, registries);
      this.inventory.deserializeNBT(registries, compound.method_10562("Inventory"));
      this.cookingTime = compound.method_10550("CookTime");
      this.cookingTimeTotal = compound.method_10550("CookTimeTotal");
      this.setSkilletItem(class_1799.method_57359(registries, compound.method_10562("Skillet")), registries);
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      compound.method_10566("Inventory", this.inventory.serializeNBT(registries));
      compound.method_10569("CookTime", this.cookingTime);
      compound.method_10569("CookTimeTotal", this.cookingTimeTotal);
      if (!this.skilletStack.method_7960()) {
         compound.method_10566("Skillet", this.skilletStack.method_57358(registries));
      }
   }

   public class_1799 getSkilletAsItem() {
      return this.skilletStack;
   }

   @Deprecated(forRemoval = true)
   public void setSkilletItem(class_1799 stack) {
      this.setSkilletItem(stack, this.field_11863.method_30349());
   }

   public void setSkilletItem(class_1799 stack, class_7874 registries) {
      this.skilletStack = stack.method_7972();
      this.fireAspectLevel = class_1890.method_8225(registries.method_46762(class_7924.field_41265).method_46747(class_1893.field_9124), this.skilletStack);
      if (this.field_11863 != null) {
         this.inventoryChanged();
      }
   }

   public class_1799 addItemToCook(class_1799 addedStack, class_1657 player) {
      Optional<class_8786<class_3920>> recipe = this.getMatchingRecipe(addedStack);
      if (recipe.isPresent() && this.getStoredStack().method_7960()) {
         if ((Boolean)this.method_11010().method_11654(SkilletBlock.WATERLOGGED)) {
            player.method_7353(TextUtils.getTranslation("block.skillet.underwater"), true);
            return addedStack;
         }

         boolean wasEmpty = this.getStoredStack().method_7960();
         class_1799 remainderStack = this.inventory.insertItem(0, addedStack.method_7972(), false);
         if (!class_1799.method_7973(remainderStack, addedStack)) {
            this.cookingTimeTotal = SkilletBlock.getSkilletCookingTime(((class_3920)recipe.get().comp_1933()).method_8167(), this.fireAspectLevel);
            this.cookingTime = 0;
            if (wasEmpty && this.field_11863 != null && this.isHeated(this.field_11863, this.field_11867)) {
               this.field_11863
                  .method_43128(
                     null,
                     this.field_11867.method_10263() + 0.5F,
                     this.field_11867.method_10264() + 0.5F,
                     this.field_11867.method_10260() + 0.5F,
                     ModSounds.BLOCK_SKILLET_ADD_FOOD.get(),
                     class_3419.field_15245,
                     0.8F,
                     1.0F
                  );
            }

            return remainderStack;
         }
      } else {
         player.method_7353(TextUtils.getTranslation("block.skillet.invalid_item"), true);
      }

      return addedStack;
   }

   public class_1799 removeItem() {
      return this.inventory.extractItem(0, this.getStoredStack().method_7914(), false);
   }

   public ItemStackHandler getInventory() {
      return this.inventory;
   }

   public class_1799 getStoredStack() {
      return this.inventory.getStackInSlot(0);
   }

   public boolean hasStoredStack() {
      return !this.getStoredStack().method_7960();
   }

   private ItemStackHandler createHandler() {
      return new ItemStackHandler() {
         @Override
         protected void onContentsChanged(int slot) {
            SkilletBlockEntity.this.inventoryChanged();
         }
      };
   }

   public void method_11012() {
      super.method_11012();
   }
}
