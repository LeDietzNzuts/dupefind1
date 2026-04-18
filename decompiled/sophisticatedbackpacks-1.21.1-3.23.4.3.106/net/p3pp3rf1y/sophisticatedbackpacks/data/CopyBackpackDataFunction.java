package net.p3pp3rf1y.sophisticatedbackpacks.data;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_117;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_2586;
import net.minecraft.class_47;
import net.minecraft.class_5339;
import net.minecraft.class_117.class_118;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class CopyBackpackDataFunction implements class_117 {
   private static final CopyBackpackDataFunction INSTANCE = new CopyBackpackDataFunction();
   public static final MapCodec<CopyBackpackDataFunction> CODEC = MapCodec.unit(INSTANCE).stable();

   private CopyBackpackDataFunction() {
   }

   public class_1799 apply(class_1799 stack, class_47 lootContext) {
      class_2586 be = (class_2586)lootContext.method_296(class_181.field_1228);
      return be instanceof BackpackBlockEntity backpackBlockEntity ? backpackBlockEntity.getBackpackWrapper().getBackpack() : stack;
   }

   public class_5339<CopyBackpackDataFunction> method_29321() {
      return ModItems.COPY_BACKPACK_DATA.get();
   }

   public static CopyBackpackDataFunction.Builder builder() {
      return new CopyBackpackDataFunction.Builder();
   }

   public static class Builder implements class_118 {
      public class_117 method_515() {
         return new CopyBackpackDataFunction();
      }
   }
}
