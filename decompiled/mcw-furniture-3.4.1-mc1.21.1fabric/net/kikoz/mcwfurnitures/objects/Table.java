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
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3542;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class Table extends FurnitureObjectNonFaceable {
   public static final class_2754<Table.ConnectionStatus> CONNECTION = class_2754.method_11850("connection", Table.ConnectionStatus.class);
   protected static final class_265 BASE1 = class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
   protected static final class_265 BASE2 = class_2248.method_9541(6.0, 0.0, 6.0, 10.0, 14.0, 10.0);
   protected static final class_265 MIDDLE = class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
   protected static final class_265 BASE = class_259.method_1084(BASE1, BASE2);

   public Table(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(CONNECTION, Table.ConnectionStatus.WITH_LEG));
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      switch ((Table.ConnectionStatus)state.method_11654(CONNECTION)) {
         case CENTER:
            return MIDDLE;
         case WITH_LEG:
            return BASE;
         default:
            return BASE;
      }
   }

   private class_2680 TableState(class_2680 state, class_1936 access, class_2338 pos) {
      boolean north = access.method_8320(pos.method_10095()).method_26204() == this;
      boolean east = access.method_8320(pos.method_10078()).method_26204() == this;
      boolean south = access.method_8320(pos.method_10072()).method_26204() == this;
      boolean west = access.method_8320(pos.method_10067()).method_26204() == this;
      Table.ConnectionStatus connection = this.getConnectionStatus(north, east, south, west);
      return (class_2680)state.method_11657(CONNECTION, connection);
   }

   private Table.ConnectionStatus getConnectionStatus(boolean north, boolean east, boolean south, boolean west) {
      if (north && south && !east && !west) {
         return Table.ConnectionStatus.CENTER;
      } else if (!north && !south && east && west) {
         return Table.ConnectionStatus.CENTER;
      } else if (north && east && west && south) {
         return Table.ConnectionStatus.CENTER;
      } else if (north && east && !west && south) {
         return Table.ConnectionStatus.CENTER;
      } else if (!north && east && west && south) {
         return Table.ConnectionStatus.CENTER;
      } else if (north && !east && west && south) {
         return Table.ConnectionStatus.CENTER;
      } else {
         return north && east && west && !south ? Table.ConnectionStatus.CENTER : Table.ConnectionStatus.WITH_LEG;
      }
   }

   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack) {
      this.TableState(state, world, pos);
   }

   @Override
   public void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
      if (!newState.method_27852(state.method_26204())) {
         this.TableState(state, world, pos);
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{CONNECTION});
   }

   @Override
   public class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 world, class_2338 pos, class_2338 neighborPos) {
      return this.TableState(state, world, pos);
   }

   @Nullable
   @Override
   public class_2680 method_9605(class_1750 context) {
      return this.TableState(super.method_9605(context), context.method_8045(), context.method_8037());
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
      CENTER("center"),
      WITH_LEG("with_leg");

      private final String name;

      private ConnectionStatus(String name) {
         this.name = name;
      }

      public String method_15434() {
         return this.name;
      }
   }
}
