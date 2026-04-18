package net.caffeinemc.mods.sodium.mixin.features.render.immediate.matrix_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_4587.class)
public abstract class PoseStackMixin {
   @Shadow
   @Final
   private Deque<class_4665> field_20898;
   @Unique
   private final Deque<class_4665> cache = new ArrayDeque<>();

   @Overwrite
   public void method_22903() {
      class_4665 prev = this.field_20898.getLast();
      class_4665 entry;
      if (!this.cache.isEmpty()) {
         entry = this.cache.removeLast();
         entry.method_23761().set(prev.method_23761());
         entry.method_23762().set(prev.method_23762());
      } else {
         entry = new class_4665(new Matrix4f(prev.method_23761()), new Matrix3f(prev.method_23762()));
      }

      entry.field_48930 = prev.field_48930;
      this.field_20898.addLast(entry);
   }

   @Overwrite
   public void method_22909() {
      this.cache.addLast(this.field_20898.removeLast());
   }
}
