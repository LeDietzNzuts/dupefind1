package org.embeddedt.modernfix.world.gen;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_6880;
import net.minecraft.class_2338.class_2339;

public class PositionalBiomeGetter implements Supplier<class_6880<class_1959>> {
   private final Function<class_2338, class_6880<class_1959>> biomeGetter;
   private final class_2339 pos;
   private int nextX;
   private int nextY;
   private int nextZ;
   private volatile class_6880<class_1959> curBiome;

   public PositionalBiomeGetter(Function<class_2338, class_6880<class_1959>> biomeGetter, class_2339 pos) {
      this.biomeGetter = biomeGetter;
      this.pos = pos;
   }

   public void update(int nextX, int nextY, int nextZ) {
      this.nextX = nextX;
      this.nextY = nextY;
      this.nextZ = nextZ;
      this.curBiome = null;
   }

   public class_6880<class_1959> get() {
      class_6880<class_1959> biome = this.curBiome;
      if (biome == null) {
         this.curBiome = biome = this.biomeGetter.apply(this.pos.method_10103(this.nextX, this.nextY, this.nextZ));
      }

      return biome;
   }
}
