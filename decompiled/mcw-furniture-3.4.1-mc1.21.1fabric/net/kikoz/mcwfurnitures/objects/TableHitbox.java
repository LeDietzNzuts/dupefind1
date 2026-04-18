package net.kikoz.mcwfurnitures.objects;

import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class TableHitbox extends class_2248 {
   public static final class_2746 NORTH = class_2746.method_11825("north");
   public static final class_2746 EAST = class_2746.method_11825("east");
   public static final class_2746 SOUTH = class_2746.method_11825("south");
   public static final class_2746 WEST = class_2746.method_11825("west");
   protected static final class_265 CORNER_N = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{class_2248.method_9541(12.0, 0.0, 1.0, 15.0, 11.0, 4.0), class_2248.method_9541(0.0, 11.0, 1.0, 15.0, 14.0, 16.0)}
   );
   protected static final class_265 CORNER_E = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{class_2248.method_9541(12.0, 0.0, 12.0, 15.0, 11.0, 15.0), class_2248.method_9541(0.0, 11.0, 0.0, 15.0, 14.0, 15.0)}
   );
   protected static final class_265 CORNER_S = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{class_2248.method_9541(1.0, 0.0, 12.0, 4.0, 11.0, 15.0), class_2248.method_9541(1.0, 11.0, 0.0, 16.0, 14.0, 15.0)}
   );
   protected static final class_265 CORNER_W = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{class_2248.method_9541(1.0, 0.0, 1.0, 4.0, 11.0, 4.0), class_2248.method_9541(1.0, 11.0, 1.0, 16.0, 14.0, 16.0)}
   );
   protected static final class_265 SIDE_N = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{
         class_2248.method_9541(12.0, 0.0, 1.0, 15.0, 14.0, 4.0),
         class_2248.method_9541(12.0, 0.0, 12.0, 15.0, 14.0, 15.0),
         class_2248.method_9541(12.0, 11.0, 4.0, 15.0, 14.0, 12.0),
         class_2248.method_9541(0.0, 11.0, 1.0, 12.0, 14.0, 15.0)
      }
   );
   protected static final class_265 SIDE_E = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{
         class_2248.method_9541(12.0, 0.0, 12.0, 15.0, 14.0, 15.0),
         class_2248.method_9541(1.0, 0.0, 12.0, 4.0, 14.0, 15.0),
         class_2248.method_9541(4.0, 11.0, 12.0, 12.0, 14.0, 15.0),
         class_2248.method_9541(1.0, 11.0, 0.0, 15.0, 14.0, 12.0)
      }
   );
   protected static final class_265 SIDE_S = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{
         class_2248.method_9541(1.0, 0.0, 12.0, 4.0, 14.0, 15.0),
         class_2248.method_9541(1.0, 0.0, 1.0, 4.0, 14.0, 4.0),
         class_2248.method_9541(1.0, 11.0, 4.0, 4.0, 14.0, 12.0),
         class_2248.method_9541(4.0, 11.0, 1.0, 16.0, 14.0, 15.0)
      }
   );
   protected static final class_265 SIDE_W = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{
         class_2248.method_9541(1.0, 0.0, 1.0, 4.0, 14.0, 4.0),
         class_2248.method_9541(12.0, 0.0, 1.0, 15.0, 14.0, 4.0),
         class_2248.method_9541(4.0, 11.0, 1.0, 12.0, 14.0, 4.0),
         class_2248.method_9541(1.0, 11.0, 4.0, 15.0, 14.0, 16.0)
      }
   );
   protected static final class_265 SINGLE = class_259.method_17786(
      class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      new class_265[]{
         class_2248.method_9541(12.0, 0.0, 1.0, 15.0, 11.0, 4.0),
         class_2248.method_9541(12.0, 0.0, 12.0, 15.0, 11.0, 15.0),
         class_2248.method_9541(1.0, 0.0, 1.0, 4.0, 11.0, 4.0),
         class_2248.method_9541(1.0, 0.0, 12.0, 4.0, 11.0, 15.0),
         class_2248.method_9541(1.0, 11.0, 1.0, 15.0, 14.0, 15.0)
      }
   );
   protected static final class_265 MIDDLE_NS = class_259.method_1084(
      class_2248.method_9541(0.0, 11.0, 1.0, 16.0, 14.0, 15.0), class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0)
   );
   protected static final class_265 MIDDLE_WE = class_259.method_1084(
      class_2248.method_9541(1.0, 11.0, 0.0, 15.0, 14.0, 16.0), class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0)
   );
   protected static final class_265 CENTER = class_2248.method_9541(0.0, 11.0, 0.0, 16.0, 16.0, 16.0);

   public TableHitbox(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)((class_2680)this.method_9564().method_11657(NORTH, false)).method_11657(EAST, false))
               .method_11657(SOUTH, false))
            .method_11657(WEST, false)
      );
   }

   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      boolean north = (Boolean)state.method_11654(NORTH);
      boolean east = (Boolean)state.method_11654(EAST);
      boolean south = (Boolean)state.method_11654(SOUTH);
      boolean west = (Boolean)state.method_11654(WEST);
      switch ((north ? 1 : 0) << 3 | (east ? 1 : 0) << 2 | (south ? 1 : 0) << 1 | (west ? 1 : 0)) {
         case 0:
            return SINGLE;
         case 1:
            return SIDE_N;
         case 2:
            return SIDE_W;
         case 3:
            return CORNER_N;
         case 4:
            return SIDE_S;
         case 5:
            return MIDDLE_WE;
         case 6:
            return CORNER_W;
         case 7:
            return MIDDLE_NS;
         case 8:
            return SIDE_E;
         case 9:
            return CORNER_E;
         case 10:
         case 11:
         default:
            return CENTER;
         case 12:
            return CORNER_S;
      }
   }

   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack) {
      this.TableState(state, world, pos);
   }

   private class_2680 TableState(class_2680 state, class_1936 access, class_2338 pos) {
      boolean north = access.method_8320(pos.method_10095()).method_26204() == this;
      boolean east = access.method_8320(pos.method_10078()).method_26204() == this;
      boolean south = access.method_8320(pos.method_10072()).method_26204() == this;
      boolean west = access.method_8320(pos.method_10067()).method_26204() == this;
      return (class_2680)((class_2680)((class_2680)((class_2680)state.method_11657(NORTH, north)).method_11657(EAST, east)).method_11657(SOUTH, south))
         .method_11657(WEST, west);
   }

   public void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
      if (!newState.method_27852(state.method_26204())) {
         this.TableState(state, world, pos);
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{NORTH, EAST, SOUTH, WEST});
   }

   public class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 world, class_2338 pos, class_2338 neighborPos) {
      return this.TableState(state, world, pos);
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      return this.TableState(super.method_9605(context), context.method_8045(), context.method_8037());
   }

   public void placeAt(class_1936 world, class_2680 state, class_2338 pos, int flags) {
      world.method_8652(pos, this.method_9564(), flags);
   }
}
