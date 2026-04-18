package net.kikoz.mcwfurnitures.objects;

import com.mojang.serialization.MapCodec;
import net.kikoz.mcwfurnitures.storage.FurniturekEntity;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_4970.class_2251;

public class FurnitureObjectNonFaceable extends class_2237 {
   protected static final class_265 BASE = class_2248.method_9541(0.0, 3.0, 0.0, 16.0, 16.0, 16.0);

   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      return BASE;
   }

   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   public FurnitureObjectNonFaceable(class_2251 properties) {
      super(properties);
      this.method_9590(this.method_9564());
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return null;
   }

   public class_2680 method_9605(class_1750 context) {
      return this.method_9564();
   }

   public void onBroken(class_1937 worldIn, class_2338 pos) {
      worldIn.method_20290(1029, pos, 0);
   }

   @Deprecated
   public class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 level, class_2338 pos, class_2338 neighborPos) {
      return super.method_9559(state, direction, neighborState, level, pos, neighborPos);
   }

   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return new FurniturekEntity(pos, state);
   }

   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      class_1792 item = itemstack.method_7909();
      if (item == this.method_8389()) {
         return class_9062.field_47731;
      } else {
         if (!world.field_9236) {
            class_3908 screenHandlerFactory = state.method_26196(world, pos);
            if (screenHandlerFactory != null) {
               player.method_17355(screenHandlerFactory);
            }
         }

         return class_9062.field_47728;
      }
   }

   public void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
      if (!state.method_27852(newState.method_26204())) {
         class_2586 blockEntity = world.method_8321(pos);
         if (blockEntity instanceof FurniturekEntity) {
            class_1264.method_5451(world, pos, (FurniturekEntity)blockEntity);
            world.method_8455(pos, this);
         }

         super.method_9536(state, world, pos, newState, moved);
      }
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 state, class_1937 world, class_2338 pos) {
      return class_1703.method_7608(world.method_8321(pos));
   }
}
