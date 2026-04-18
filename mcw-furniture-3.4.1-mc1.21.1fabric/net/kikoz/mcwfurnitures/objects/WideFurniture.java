package net.kikoz.mcwfurnitures.objects;

import net.kikoz.mcwfurnitures.storage.FurniturekEntity;
import net.minecraft.class_1264;
import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2753;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3542;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class WideFurniture extends FurnitureObject {
   public static final class_2754<WideFurniture.ConnectionStatus> CONNECTION = class_2754.method_11850("connection", WideFurniture.ConnectionStatus.class);
   public static final class_2753 FACING = class_2383.field_11177;
   protected static final class_265 EW = class_2248.method_9541(0.0, 0.0, 1.0, 16.0, 16.0, 15.0);
   protected static final class_265 NS = class_2248.method_9541(1.0, 0.0, 0.0, 15.0, 16.0, 16.0);

   public WideFurniture(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043))
            .method_11657(CONNECTION, WideFurniture.ConnectionStatus.SINGLE)
      );
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      switch ((class_2350)state.method_11654(FACING)) {
         case field_11043:
            return NS;
         case field_11035:
            return NS;
         case field_11039:
            return EW;
         case field_11034:
         default:
            return EW;
      }
   }

   @Override
   public class_2680 method_9598(class_2680 state, class_2470 rotation) {
      return (class_2680)state.method_11657(FACING, rotation.method_10503((class_2350)state.method_11654(FACING)));
   }

   private class_2680 TableState(class_2680 state, class_1936 level, class_2338 pos) {
      boolean north = level.method_8320(pos.method_10095()).method_26204() == this
         && state.method_11654(FACING) == level.method_8320(pos.method_10095()).method_11654(FACING);
      boolean east = level.method_8320(pos.method_10078()).method_26204() == this
         && state.method_11654(FACING) == level.method_8320(pos.method_10078()).method_11654(FACING);
      boolean south = level.method_8320(pos.method_10072()).method_26204() == this
         && state.method_11654(FACING) == level.method_8320(pos.method_10072()).method_11654(FACING);
      boolean west = level.method_8320(pos.method_10067()).method_26204() == this
         && state.method_11654(FACING) == level.method_8320(pos.method_10067()).method_11654(FACING);
      WideFurniture.ConnectionStatus connection = this.getConnectionStatus((class_2350)state.method_11654(FACING), north, east, south, west);
      return (class_2680)state.method_11657(CONNECTION, connection);
   }

   private WideFurniture.ConnectionStatus getConnectionStatus(class_2350 facing, boolean north, boolean east, boolean south, boolean west) {
      if (facing == class_2350.field_11043) {
         if (north && south && !east && !west) {
            return WideFurniture.ConnectionStatus.MIDDLE;
         }

         if (!north && south && !east && !west) {
            return WideFurniture.ConnectionStatus.RIGHT;
         }

         if (north && !south && !east && !west) {
            return WideFurniture.ConnectionStatus.LEFT;
         }
      }

      if (facing == class_2350.field_11035) {
         if (north && south && !east && !west) {
            return WideFurniture.ConnectionStatus.MIDDLE;
         }

         if (!north && south && !east && !west) {
            return WideFurniture.ConnectionStatus.LEFT;
         }

         if (north && !south && !east && !west) {
            return WideFurniture.ConnectionStatus.RIGHT;
         }
      }

      if (facing == class_2350.field_11034) {
         if (!north && !south && east && west) {
            return WideFurniture.ConnectionStatus.MIDDLE;
         }

         if (!north && !south && east && !west) {
            return WideFurniture.ConnectionStatus.LEFT;
         }

         if (!north && !south && !east && west) {
            return WideFurniture.ConnectionStatus.RIGHT;
         }
      }

      if (facing == class_2350.field_11039) {
         if (!north && !south && east && west) {
            return WideFurniture.ConnectionStatus.MIDDLE;
         }

         if (!north && !south && east && !west) {
            return WideFurniture.ConnectionStatus.RIGHT;
         }

         if (!north && !south && !east && west) {
            return WideFurniture.ConnectionStatus.LEFT;
         }
      }

      return WideFurniture.ConnectionStatus.SINGLE;
   }

   @Override
   public void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
      if (state.method_26204() != newState.method_26204()) {
         class_2586 blockEntity = world.method_8321(pos);
         if (blockEntity instanceof FurniturekEntity) {
            class_1264.method_5451(world, pos, (FurniturekEntity)blockEntity);
            world.method_8455(pos, this);
         }

         super.method_9536(state, world, pos, newState, moved);
      }
   }

   @Override
   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   @Override
   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, CONNECTION});
   }

   @Override
   public class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 level, class_2338 pos, class_2338 neighborPos) {
      return this.TableState(state, level, pos);
   }

   @Nullable
   @Override
   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.TableState(super.method_9605(context), context.method_8045(), context.method_8037())
         .method_11657(FACING, context.method_8042().method_10170());
   }

   public void placeAt(class_1936 world, class_2680 state, class_2338 pos, int flags) {
      world.method_8652(pos, this.method_9564(), flags);
   }

   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack) {
      this.TableState(state, world, pos);
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
