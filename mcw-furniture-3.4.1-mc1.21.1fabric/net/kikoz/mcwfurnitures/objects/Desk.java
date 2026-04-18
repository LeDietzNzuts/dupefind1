package net.kikoz.mcwfurnitures.objects;

import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
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

public class Desk extends FurnitureObjectNonFaceable {
   public static final class_2753 FACING_TWO_DIRECTIONAL = class_2753.method_11845("facing", new class_2350[]{class_2350.field_11043, class_2350.field_11034});
   public static final class_2754<Desk.ConnectionStatus> CONNECTION = class_2754.method_11850("connection", Desk.ConnectionStatus.class);
   protected static final class_265 NORTH_BASE = class_259.method_17786(
      class_2248.method_9541(0.1, 14.0, 0.1, 16.1, 16.0, 16.1),
      new class_265[]{class_2248.method_9541(0.0, 0.0, 13.0, 16.0, 14.0, 15.0), class_2248.method_9541(0.0, 0.0, 1.0, 16.0, 14.0, 3.0)}
   );
   protected static final class_265 NORTH_MIDDLE = class_259.method_17786(class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0), new class_265[0]);
   protected static final class_265 NORTH_RIGHT = class_259.method_1084(
      class_2248.method_9541(0.0, 0.0, 1.0, 16.0, 14.0, 3.0), class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0)
   );
   protected static final class_265 NORTH_LEFT = class_259.method_1084(
      class_2248.method_9541(0.0, 0.0, 13.0, 16.0, 14.0, 15.0), class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0)
   );
   protected static final class_265 EAST_BASE = class_259.method_17786(
      class_2248.method_9541(0.1, 14.0, 0.1, 16.1, 16.0, 16.1),
      new class_265[]{class_2248.method_9541(1.1, 0.0, 0.0, 3.1, 14.0, 16.1), class_2248.method_9541(13.1, 0.0, 0.0, 15.1, 14.0, 16.1)}
   );
   protected static final class_265 EAST_MIDDLE = class_259.method_17786(class_2248.method_9541(0.1, 14.0, 0.1, 16.1, 16.0, 16.1), new class_265[0]);
   protected static final class_265 EAST_RIGHT = class_259.method_1084(
      class_2248.method_9541(0.1, 14.0, 0.0, 16.1, 16.0, 16.1), class_2248.method_9541(13.1, 0.0, 0.0, 15.0, 14.0, 16.0)
   );
   protected static final class_265 EAST_LEFT = class_259.method_1084(
      class_2248.method_9541(0.1, 14.0, 0.1, 16.1, 16.0, 16.1), class_2248.method_9541(1.1, 0.0, 0.0, 3.1, 14.0, 16.1)
   );

   public Desk(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)this.method_9564().method_11657(FACING_TWO_DIRECTIONAL, class_2350.field_11043))
            .method_11657(CONNECTION, Desk.ConnectionStatus.SINGLE)
      );
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      Desk.ConnectionStatus connectionStatus = (Desk.ConnectionStatus)state.method_11654(CONNECTION);
      switch ((class_2350)state.method_11654(FACING_TWO_DIRECTIONAL)) {
         case field_11043:
            if (connectionStatus == Desk.ConnectionStatus.MIDDLE) {
               return NORTH_MIDDLE;
            } else if (connectionStatus == Desk.ConnectionStatus.LEFT) {
               return NORTH_LEFT;
            } else {
               if (connectionStatus == Desk.ConnectionStatus.RIGHT) {
                  return NORTH_RIGHT;
               }

               return NORTH_BASE;
            }
         case field_11034:
         default:
            if (connectionStatus == Desk.ConnectionStatus.MIDDLE) {
               return EAST_MIDDLE;
            } else if (connectionStatus == Desk.ConnectionStatus.LEFT) {
               return EAST_LEFT;
            } else {
               return connectionStatus == Desk.ConnectionStatus.RIGHT ? EAST_RIGHT : EAST_BASE;
            }
      }
   }

   public class_2680 method_9598(class_2680 state, class_2470 rot) {
      class_2350 originalDirection = (class_2350)state.method_11654(FACING_TWO_DIRECTIONAL);
      class_2350 rotatedDirection = rot.method_10503(originalDirection);
      if (originalDirection != class_2350.field_11043 && originalDirection != class_2350.field_11035) {
         rotatedDirection = class_2350.field_11034;
      } else {
         rotatedDirection = class_2350.field_11043;
      }

      return (class_2680)state.method_11657(FACING_TWO_DIRECTIONAL, rotatedDirection);
   }

   public class_2680 method_9569(class_2680 state, class_2415 mir) {
      class_2350 originalDirection = (class_2350)state.method_11654(FACING_TWO_DIRECTIONAL);
      if (mir != class_2415.field_11301) {
         originalDirection.method_10153();
      }

      class_2350 rotatedDirection;
      if (originalDirection != class_2350.field_11043 && originalDirection != class_2350.field_11035) {
         rotatedDirection = class_2350.field_11034;
      } else {
         rotatedDirection = class_2350.field_11043;
      }

      return (class_2680)state.method_11657(FACING_TWO_DIRECTIONAL, rotatedDirection);
   }

   private class_2680 TableState(class_2680 state, class_1936 level, class_2338 pos) {
      boolean north = level.method_8320(pos.method_10095()).method_26204() == this;
      boolean east = level.method_8320(pos.method_10078()).method_26204() == this;
      boolean south = level.method_8320(pos.method_10072()).method_26204() == this;
      boolean west = level.method_8320(pos.method_10067()).method_26204() == this;
      Desk.ConnectionStatus connection = this.getConnectionStatus((class_2350)state.method_11654(FACING_TWO_DIRECTIONAL), north, east, south, west);
      return (class_2680)state.method_11657(CONNECTION, connection);
   }

   private Desk.ConnectionStatus getConnectionStatus(class_2350 facing, boolean north, boolean east, boolean south, boolean west) {
      if (facing == class_2350.field_11043) {
         if (north && south && !east && !west) {
            return Desk.ConnectionStatus.MIDDLE;
         }

         if (!north && south && !east && !west) {
            return Desk.ConnectionStatus.RIGHT;
         }

         if (north && !south && !east && !west) {
            return Desk.ConnectionStatus.LEFT;
         }
      }

      if (facing == class_2350.field_11034) {
         if (!north && !south && east && west) {
            return Desk.ConnectionStatus.MIDDLE;
         }

         if (!north && !south && east && !west) {
            return Desk.ConnectionStatus.LEFT;
         }

         if (!north && !south && !east && west) {
            return Desk.ConnectionStatus.RIGHT;
         }
      }

      return Desk.ConnectionStatus.SINGLE;
   }

   @Override
   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean moved) {
      if (!state.method_27852(newState.method_26204())) {
         super.method_9536(state, level, pos, newState, moved);
      } else {
         if (newState.method_28498(FACING_TWO_DIRECTIONAL) && newState.method_26204() instanceof Desk) {
            class_2350 facingDirection = (class_2350)newState.method_11654(FACING_TWO_DIRECTIONAL);
            class_2680 updatedState = (class_2680)state.method_11657(FACING_TWO_DIRECTIONAL, facingDirection);
            if (!newState.method_27852(updatedState.method_26204())) {
               level.method_8652(pos, this.TableState(updatedState, level, pos), 2);
            }
         } else {
            class_2350 currentFacing = (class_2350)state.method_11654(FACING_TWO_DIRECTIONAL);
            class_2680 defaultState = (class_2680)this.method_9564().method_11657(FACING_TWO_DIRECTIONAL, currentFacing);
            level.method_8652(pos, this.TableState(defaultState, level, pos), 2);
         }
      }
   }

   @Override
   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING_TWO_DIRECTIONAL, CONNECTION});
   }

   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack) {
      this.TableState(state, world, pos);
   }

   @Override
   public class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 world, class_2338 pos, class_2338 neighborPos) {
      return (class_2680)this.TableState(state, world, pos).method_11657(FACING_TWO_DIRECTIONAL, (class_2350)state.method_11654(FACING_TWO_DIRECTIONAL));
   }

   @Override
   public class_2680 method_9605(class_1750 context) {
      class_2350 playerFacing = context.method_8042();

      return (class_2680)this.TableState(super.method_9605(context), context.method_8045(), context.method_8037())
         .method_11657(FACING_TWO_DIRECTIONAL, switch (playerFacing) {
            case field_11043 -> class_2350.field_11034;
            case field_11034 -> class_2350.field_11043;
            case field_11035 -> class_2350.field_11034;
            case field_11039 -> class_2350.field_11043;
            default -> class_2350.field_11043;
         });
   }

   public void placeAt(class_1936 world, class_2680 state, class_2338 pos, int flags) {
      world.method_8652(pos, this.method_9564(), flags);
   }

   @Override
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return null;
   }

   @Override
   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return class_9062.field_47733;
   }

   public static enum ConnectionStatus implements class_3542 {
      SINGLE("single"),
      MIDDLE("middle"),
      LEFT("left"),
      RIGHT("right");

      private final String name;

      private ConnectionStatus(String name) {
         this.name = name;
      }

      public String method_15434() {
         return this.name;
      }
   }
}
