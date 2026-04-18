package net.kikoz.mcwfurnitures.objects;

import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.kikoz.mcwfurnitures.sittable.CouchEntity;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2470;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2753;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3542;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class Couch extends class_2248 {
   public static final class_2753 FACING = class_2383.field_11177;
   public static final class_2754<Couch.CouchShape> SHAPE = class_2754.method_11850("shape", Couch.CouchShape.class);
   private static final class_265 W_SINGLE = class_259.method_17786(
      class_2248.method_9541(1.0, 0.0, 14.0, 3.0, 2.0, 16.0),
      new class_265[]{
         class_2248.method_9541(13.0, 0.0, 14.0, 15.0, 2.0, 16.0),
         class_2248.method_9541(13.0, 0.0, 1.0, 15.0, 2.0, 3.0),
         class_2248.method_9541(1.0, 7.0, 12.0, 15.0, 17.0, 16.0),
         class_2248.method_9541(0.0, 7.0, 0.0, 4.0, 11.0, 15.0),
         class_2248.method_9541(12.0, 7.0, 0.0, 16.0, 11.0, 15.0),
         class_2248.method_9541(1.0, 2.0, 0.0, 15.0, 7.0, 16.0),
         class_2248.method_9541(1.0, 0.0, 1.0, 3.0, 2.0, 3.0)
      }
   );
   private static final class_265 W_MIDDLE = class_259.method_1084(
      class_2248.method_9541(0.0, 7.0, 12.0, 16.0, 17.0, 16.0), class_2248.method_9541(0.0, 2.0, 0.0, 16.0, 7.0, 16.0)
   );
   private static final class_265 W_LEFT = class_259.method_17786(
      class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 2.0, 16.0),
      new class_265[]{
         class_2248.method_9541(1.0, 7.0, 12.0, 16.0, 17.0, 16.0),
         class_2248.method_9541(0.0, 7.0, 0.0, 4.0, 11.0, 16.0),
         class_2248.method_9541(0.0, 2.0, 0.0, 16.0, 7.0, 16.0),
         class_2248.method_9541(0.0, 0.0, 1.0, 2.0, 2.0, 3.0)
      }
   );
   private static final class_265 W_RIGHT = class_259.method_17786(
      class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 2.0, 16.0),
      new class_265[]{
         class_2248.method_9541(14.0, 0.0, 1.0, 16.0, 2.0, 3.0),
         class_2248.method_9541(0.0, 7.0, 12.0, 15.0, 17.0, 16.0),
         class_2248.method_9541(12.0, 7.0, 0.0, 16.0, 11.0, 16.0),
         class_2248.method_9541(0.0, 2.0, 0.0, 16.0, 7.0, 16.0)
      }
   );
   private static final class_265 W_CORNER = class_259.method_17786(
      class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 2.0, 16.0),
      new class_265[]{
         class_2248.method_9541(0.0, 7.0, 12.0, 16.0, 17.0, 16.0),
         class_2248.method_9541(12.0, 7.0, 0.0, 16.0, 17.0, 12.0),
         class_2248.method_9541(0.0, 2.0, 0.0, 16.0, 7.0, 16.0)
      }
   );
   private static final class_265 N_SINGLE = MacawsFurniture.rotateFurniture(W_SINGLE, 1);
   private static final class_265 N_MIDDLE = MacawsFurniture.rotateFurniture(W_MIDDLE, 1);
   private static final class_265 N_LEFT = MacawsFurniture.rotateFurniture(W_LEFT, 1);
   private static final class_265 N_RIGHT = MacawsFurniture.rotateFurniture(W_RIGHT, 1);
   private static final class_265 N_CORNER = MacawsFurniture.rotateFurniture(W_CORNER, 1);
   private static final class_265 S_SINGLE = MacawsFurniture.rotateFurniture(W_SINGLE, 3);
   private static final class_265 S_MIDDLE = MacawsFurniture.rotateFurniture(W_MIDDLE, 3);
   private static final class_265 S_LEFT = MacawsFurniture.rotateFurniture(W_LEFT, 3);
   private static final class_265 S_RIGHT = MacawsFurniture.rotateFurniture(W_RIGHT, 3);
   private static final class_265 S_CORNER = MacawsFurniture.rotateFurniture(W_CORNER, 3);
   private static final class_265 E_SINGLE = MacawsFurniture.rotateFurniture(W_SINGLE, 2);
   private static final class_265 E_MIDDLE = MacawsFurniture.rotateFurniture(W_MIDDLE, 2);
   private static final class_265 E_LEFT = MacawsFurniture.rotateFurniture(W_LEFT, 2);
   private static final class_265 E_RIGHT = MacawsFurniture.rotateFurniture(W_RIGHT, 2);
   private static final class_265 E_CORNER = MacawsFurniture.rotateFurniture(W_CORNER, 2);

   public Couch(class_2251 props) {
      super(props);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043)).method_11657(SHAPE, Couch.CouchShape.SINGLE));
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{SHAPE, FACING});
   }

   public class_2680 method_9598(class_2680 state, class_2470 rot) {
      return (class_2680)state.method_11657(FACING, rot.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return CouchEntity.create(level, pos, 0.7, player);
   }

   private class_2680 StairState(class_2680 state, class_1936 access, class_2338 pos) {
      boolean north = access.method_8320(pos.method_10095()).method_26204() == this;
      boolean east = access.method_8320(pos.method_10078()).method_26204() == this;
      boolean south = access.method_8320(pos.method_10072()).method_26204() == this;
      boolean west = access.method_8320(pos.method_10067()).method_26204() == this;
      class_2350 facingDirection = (class_2350)state.method_11654(FACING);
      Couch.CouchShape connection = this.getCouchShape(facingDirection, north, east, south, west);
      return (class_2680)((class_2680)state.method_11657(SHAPE, connection)).method_11657(FACING, facingDirection);
   }

   private Couch.CouchShape getCouchShape(class_2350 facing, boolean north, boolean east, boolean south, boolean west) {
      switch (facing) {
         case field_11043:
            if (!north && !south) {
               return Couch.CouchShape.SINGLE;
            }

            if (north && south) {
               return Couch.CouchShape.MIDDLE;
            }

            if (!north && south && !east && !west) {
               return Couch.CouchShape.LEFT;
            }

            if (north && !south && !east && !west) {
               return Couch.CouchShape.RIGHT;
            }

            if (north && !south && east) {
               return Couch.CouchShape.LEFT_CORNER;
            }

            if (!north && south && east) {
               return Couch.CouchShape.RIGHT_CORNER;
            }
            break;
         case field_11034:
            if (!east && !west) {
               return Couch.CouchShape.SINGLE;
            }

            if (east && west) {
               return Couch.CouchShape.MIDDLE;
            }

            if (!east && west && !north && !south) {
               return Couch.CouchShape.LEFT;
            }

            if (east && !west && !north && !south) {
               return Couch.CouchShape.RIGHT;
            }

            if (east && !west && south) {
               return Couch.CouchShape.LEFT_CORNER;
            }

            if (!east && west && south) {
               return Couch.CouchShape.RIGHT_CORNER;
            }
            break;
         case field_11035:
            if (!north && !south) {
               return Couch.CouchShape.SINGLE;
            }

            if (north && south) {
               return Couch.CouchShape.MIDDLE;
            }

            if (north && !south && !east && !west) {
               return Couch.CouchShape.LEFT;
            }

            if (!north && south && !east && !west) {
               return Couch.CouchShape.RIGHT;
            }

            if (north && !south && west) {
               return Couch.CouchShape.RIGHT_CORNER;
            }

            if (!north && south && west) {
               return Couch.CouchShape.LEFT_CORNER;
            }
            break;
         case field_11039:
            if (!east && !west) {
               return Couch.CouchShape.SINGLE;
            }

            if (east && west) {
               return Couch.CouchShape.MIDDLE;
            }

            if (east && !west && !north && !south) {
               return Couch.CouchShape.LEFT;
            }

            if (!east && west && !north && !south) {
               return Couch.CouchShape.RIGHT;
            }

            if (east && !west && north) {
               return Couch.CouchShape.RIGHT_CORNER;
            }

            if (!east && west && north) {
               return Couch.CouchShape.LEFT_CORNER;
            }
      }

      return Couch.CouchShape.SINGLE;
   }

   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 ctx) {
      class_2350 facing = (class_2350)state.method_11654(FACING);
      Couch.CouchShape shape = (Couch.CouchShape)state.method_11654(SHAPE);

      return switch (facing) {
         case field_11043 -> {
            switch (shape) {
               case SINGLE:
                  yield N_SINGLE;
               case LEFT:
                  yield N_LEFT;
               case MIDDLE:
                  yield N_MIDDLE;
               case RIGHT:
                  yield N_RIGHT;
               case LEFT_CORNER:
                  yield N_CORNER;
               case RIGHT_CORNER:
                  yield E_CORNER;
               default:
                  throw new MatchException(null, null);
            }
         }
         case field_11034 -> {
            switch (shape) {
               case SINGLE:
                  yield E_SINGLE;
               case LEFT:
                  yield E_LEFT;
               case MIDDLE:
                  yield E_MIDDLE;
               case RIGHT:
                  yield E_RIGHT;
               case LEFT_CORNER:
                  yield E_CORNER;
               case RIGHT_CORNER:
                  yield S_CORNER;
               default:
                  throw new MatchException(null, null);
            }
         }
         case field_11035 -> {
            switch (shape) {
               case SINGLE:
                  yield S_SINGLE;
               case LEFT:
                  yield S_LEFT;
               case MIDDLE:
                  yield S_MIDDLE;
               case RIGHT:
                  yield S_RIGHT;
               case LEFT_CORNER:
                  yield S_CORNER;
               case RIGHT_CORNER:
                  yield W_CORNER;
               default:
                  throw new MatchException(null, null);
            }
         }
         case field_11039 -> {
            switch (shape) {
               case SINGLE:
                  yield W_SINGLE;
               case LEFT:
                  yield W_LEFT;
               case MIDDLE:
                  yield W_MIDDLE;
               case RIGHT:
                  yield W_RIGHT;
               case LEFT_CORNER:
                  yield W_CORNER;
               case RIGHT_CORNER:
                  yield N_CORNER;
               default:
                  throw new MatchException(null, null);
            }
         }
         default -> N_SINGLE;
      };
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean moved) {
      if (!state.method_27852(newState.method_26204())) {
         super.method_9536(state, level, pos, newState, moved);
      } else {
         if (newState.method_28498(FACING) && newState.method_26204() instanceof Couch) {
            class_2350 facingDirection = (class_2350)newState.method_11654(FACING);
            class_2680 updatedState = (class_2680)state.method_11657(FACING, facingDirection);
            if (!newState.method_27852(updatedState.method_26204())) {
               level.method_8652(pos, this.StairState(updatedState, level, pos), 2);
            }
         } else {
            class_2350 currentFacing = (class_2350)state.method_11654(FACING);
            class_2680 defaultState = (class_2680)this.method_9564().method_11657(FACING, currentFacing);
            level.method_8652(pos, this.StairState(defaultState, level, pos), 2);
         }
      }
   }

   public class_2680 method_9559(class_2680 state, class_2350 dir, class_2680 statetwo, class_1936 access, class_2338 pos, class_2338 postwo) {
      return this.StairState(state, access, pos);
   }

   @Nullable
   public class_2680 method_9605(class_1750 contx) {
      class_2350 facingDirection = contx.method_8042().method_10170();
      return (class_2680)this.StairState(super.method_9605(contx), contx.method_8045(), contx.method_8037()).method_11657(FACING, facingDirection);
   }

   public static enum CouchShape implements class_3542 {
      SINGLE("single"),
      LEFT("left"),
      MIDDLE("middle"),
      RIGHT("right"),
      LEFT_CORNER("left_corner"),
      RIGHT_CORNER("right_corner");

      private final String name;

      private CouchShape(String name) {
         this.name = name;
      }

      public String method_15434() {
         return this.name;
      }
   }
}
