package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1087;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2338.class_2339;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class BlockRenderContext {
   private final LevelSlice slice;
   public final TranslucentGeometryCollector collector;
   private final class_2339 pos = new class_2339();
   private final Vector3f origin = new Vector3f();
   private class_2680 state;
   private class_1087 model;
   private long seed;

   public BlockRenderContext(LevelSlice slice, TranslucentGeometryCollector collector) {
      this.slice = slice;
      this.collector = collector;
   }

   public void update(class_2338 pos, class_2338 origin, class_2680 state, class_1087 model, long seed) {
      this.pos.method_10101(pos);
      this.origin.set(origin.method_10263(), origin.method_10264(), origin.method_10260());
      this.state = state;
      this.model = model;
      this.seed = seed;
   }

   public TranslucentGeometryCollector collector() {
      return this.collector;
   }

   public class_2338 pos() {
      return this.pos;
   }

   public LevelSlice slice() {
      return this.slice;
   }

   public class_2680 state() {
      return this.state;
   }

   public class_1087 model() {
      return this.model;
   }

   public Vector3fc origin() {
      return this.origin;
   }

   public long seed() {
      return this.seed;
   }
}
