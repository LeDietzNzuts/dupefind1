package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumInventory;
import net.minecraft.class_1693;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2589;
import net.minecraft.class_2595;
import net.minecraft.class_2601;
import net.minecraft.class_2609;
import net.minecraft.class_2614;
import net.minecraft.class_2627;
import net.minecraft.class_3719;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

public class InventoryAccessors {
   @Mixin(class_2609.class)
   public abstract static class AbstractFurnaceBlockEntityMixin implements LithiumInventory {
      @Accessor("field_11984")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_11984")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_1693.class)
   public abstract static class AbstractMinecartContainerMixin implements LithiumInventory {
      @Accessor("field_7735")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_7735")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_3719.class)
   public abstract static class BarrelBlockEntityMixin implements LithiumInventory {
      @Accessor("field_16410")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_16410")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_2589.class)
   public abstract static class BrewingStandBlockEntityMixin implements LithiumInventory {
      @Accessor("field_11882")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_11882")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_2595.class)
   public abstract static class ChestBlockEntityMixin implements LithiumInventory {
      @Accessor("field_11927")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_11927")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_2601.class)
   public abstract static class DispenserBlockEntityMixin implements LithiumInventory {
      @Accessor("field_11945")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_11945")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_2614.class)
   public abstract static class HopperBlockEntityMixin implements LithiumInventory {
      @Accessor("field_12024")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_12024")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }

   @Mixin(class_2627.class)
   public abstract static class ShulkerBoxBlockEntityMixin implements LithiumInventory {
      @Accessor("field_12054")
      @Override
      public abstract class_2371<class_1799> getInventoryLithium();

      @Accessor("field_12054")
      @Override
      public abstract void setInventoryLithium(class_2371<class_1799> var1);
   }
}
