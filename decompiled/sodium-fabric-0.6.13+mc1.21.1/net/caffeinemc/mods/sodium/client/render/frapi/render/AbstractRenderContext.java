package net.caffeinemc.mods.sodium.client.render.frapi.render;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext.QuadTransform;

public abstract class AbstractRenderContext implements RenderContext {
   private static final QuadTransform NO_TRANSFORM = q -> true;
   private QuadTransform activeTransform = NO_TRANSFORM;
   private final ObjectArrayList<QuadTransform> transformStack = new ObjectArrayList();
   private final QuadTransform stackTransform = q -> {
      int i = this.transformStack.size() - 1;

      while (i >= 0) {
         if (!((QuadTransform)this.transformStack.get(i--)).transform(q)) {
            return false;
         }
      }

      return true;
   };
   @Deprecated
   private final Consumer<Mesh> meshConsumer = mesh -> mesh.outputTo(this.getEmitter());

   protected final boolean transform(MutableQuadView q) {
      return this.activeTransform.transform(q);
   }

   public boolean hasTransform() {
      return this.activeTransform != NO_TRANSFORM;
   }

   public void pushTransform(QuadTransform transform) {
      if (transform == null) {
         throw new NullPointerException("Renderer received null QuadTransform.");
      } else {
         this.transformStack.push(transform);
         if (this.transformStack.size() == 1) {
            this.activeTransform = transform;
         } else if (this.transformStack.size() == 2) {
            this.activeTransform = this.stackTransform;
         }
      }
   }

   public void popTransform() {
      this.transformStack.pop();
      if (this.transformStack.isEmpty()) {
         this.activeTransform = NO_TRANSFORM;
      } else if (this.transformStack.size() == 1) {
         this.activeTransform = (QuadTransform)this.transformStack.get(0);
      }
   }

   @Deprecated
   public Consumer<Mesh> meshConsumer() {
      return this.meshConsumer;
   }
}
