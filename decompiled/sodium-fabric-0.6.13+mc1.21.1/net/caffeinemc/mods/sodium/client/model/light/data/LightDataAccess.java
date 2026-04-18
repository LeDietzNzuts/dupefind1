package net.caffeinemc.mods.sodium.client.model.light.data;

import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.minecraft.class_1920;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_761;
import net.minecraft.class_765;
import net.minecraft.class_2338.class_2339;

public abstract class LightDataAccess {
   private final class_2339 pos = new class_2339();
   protected class_1920 level;

   public int get(int x, int y, int z, class_2350 d1, class_2350 d2) {
      return this.get(x + d1.method_10148() + d2.method_10148(), y + d1.method_10164() + d2.method_10164(), z + d1.method_10165() + d2.method_10165());
   }

   public int get(int x, int y, int z, class_2350 dir) {
      return this.get(x + dir.method_10148(), y + dir.method_10164(), z + dir.method_10165());
   }

   public int get(class_2338 pos, class_2350 dir) {
      return this.get(pos.method_10263(), pos.method_10264(), pos.method_10260(), dir);
   }

   public int get(class_2338 pos) {
      return this.get(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public abstract int get(int var1, int var2, int var3);

   protected int compute(int x, int y, int z) {
      class_2338 pos = this.pos.method_10103(x, y, z);
      class_1920 level = this.level;
      class_2680 state = level.method_8320(pos);
      boolean em = state.method_26208(level, pos);
      boolean op = state.method_26230(level, pos) && state.method_26193(level, pos) != 0;
      boolean fo = state.method_26216(level, pos);
      boolean fc = state.method_26234(level, pos);
      int lu = PlatformBlockAccess.getInstance().getLightEmission(state, level, pos);
      int bl;
      int sl;
      if (fo && lu == 0) {
         bl = 0;
         sl = 0;
      } else if (em) {
         bl = level.method_8314(class_1944.field_9282, pos);
         sl = level.method_8314(class_1944.field_9284, pos);
      } else {
         int light = class_761.method_23793(level, state, pos);
         bl = class_765.method_24186(light);
         sl = class_765.method_24187(light);
      }

      float ao;
      if (lu == 0) {
         ao = state.method_26210(level, pos);
      } else {
         ao = 1.0F;
      }

      return packFC(fc) | packFO(fo) | packOP(op) | packEM(em) | packAO(ao) | packLU(lu) | packSL(sl) | packBL(bl);
   }

   public static int packBL(int blockLight) {
      return blockLight & 15;
   }

   public static int unpackBL(int word) {
      return word & 15;
   }

   public static int packSL(int skyLight) {
      return (skyLight & 15) << 4;
   }

   public static int unpackSL(int word) {
      return word >>> 4 & 15;
   }

   public static int packLU(int luminance) {
      return (luminance & 15) << 8;
   }

   public static int unpackLU(int word) {
      return word >>> 8 & 15;
   }

   public static int packAO(float ao) {
      int aoi = (int)(ao * 4096.0F);
      return (aoi & 65535) << 12;
   }

   public static float unpackAO(int word) {
      int aoi = word >>> 12 & 65535;
      return aoi * 2.4414062E-4F;
   }

   public static int packEM(boolean emissive) {
      return (emissive ? 1 : 0) << 28;
   }

   public static boolean unpackEM(int word) {
      return (word >>> 28 & 1) != 0;
   }

   public static int packOP(boolean opaque) {
      return (opaque ? 1 : 0) << 29;
   }

   public static boolean unpackOP(int word) {
      return (word >>> 29 & 1) != 0;
   }

   public static int packFO(boolean opaque) {
      return (opaque ? 1 : 0) << 30;
   }

   public static boolean unpackFO(int word) {
      return (word >>> 30 & 1) != 0;
   }

   public static int packFC(boolean fullCube) {
      return (fullCube ? 1 : 0) << 31;
   }

   public static boolean unpackFC(int word) {
      return (word >>> 31 & 1) != 0;
   }

   public static int getLightmap(int word) {
      return class_765.method_23687(Math.max(unpackBL(word), unpackLU(word)), unpackSL(word));
   }

   public static int getEmissiveLightmap(int word) {
      return unpackEM(word) ? 15728880 : getLightmap(word);
   }

   public class_1920 getLevel() {
      return this.level;
   }
}
