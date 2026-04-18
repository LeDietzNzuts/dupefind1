package noobanidus.mods.lootr.fabric.client.block;

import com.google.common.collect.Streams;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1100;
import net.minecraft.class_1799;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3665;
import net.minecraft.class_4730;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import net.minecraft.class_7775;
import net.minecraft.class_806;
import net.minecraft.class_809;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.jetbrains.annotations.Nullable;

public class BarrelModel implements class_1100 {
   private final class_1100 opened;
   private final class_1100 unopened;
   private final class_1100 vanilla;
   private final class_1100 old_opened;
   private final class_1100 old_unopened;
   private Collection<class_2960> dependencies = null;

   public BarrelModel(class_1100 opened, class_1100 unopened, class_1100 vanilla, class_1100 old_opened, class_1100 old_unopened) {
      this.opened = opened;
      this.unopened = unopened;
      this.vanilla = vanilla;
      this.old_opened = old_opened;
      this.old_unopened = old_unopened;
   }

   public Collection<class_2960> method_4755() {
      if (this.dependencies == null) {
         this.dependencies = Streams.concat(
               new Stream[]{
                  this.opened.method_4755().stream(),
                  this.unopened.method_4755().stream(),
                  this.vanilla.method_4755().stream(),
                  this.old_opened.method_4755().stream(),
                  this.old_unopened.method_4755().stream()
               }
            )
            .collect(Collectors.toSet());
      }

      return this.dependencies;
   }

   public void method_45785(Function<class_2960, class_1100> function) {
      this.opened.method_45785(function);
      this.unopened.method_45785(function);
      this.vanilla.method_45785(function);
      this.old_opened.method_45785(function);
      this.old_unopened.method_45785(function);
   }

   @Nullable
   public class_1087 method_4753(class_7775 modelBakery, Function<class_4730, class_1058> spriteGetter, class_3665 transform) {
      return new BarrelModel.BakedBarrelModel(
         this.opened.method_4753(modelBakery, spriteGetter, transform),
         this.unopened.method_4753(modelBakery, spriteGetter, transform),
         this.vanilla.method_4753(modelBakery, spriteGetter, transform),
         this.old_opened.method_4753(modelBakery, spriteGetter, transform),
         this.old_unopened.method_4753(modelBakery, spriteGetter, transform)
      );
   }

   public static class BakedBarrelModel implements class_1087, FabricBakedModel {
      private final class_1087 opened;
      private final class_1087 unopened;
      private final class_1087 vanilla;
      private final class_1087 old_opened;
      private final class_1087 old_unopened;

      public BakedBarrelModel(class_1087 opened, class_1087 unopened, class_1087 vanilla, class_1087 old_opened, class_1087 old_unopened) {
         this.opened = opened;
         this.unopened = unopened;
         this.vanilla = vanilla;
         this.old_opened = old_opened;
         this.old_unopened = old_unopened;
      }

      public boolean isVanillaAdapter() {
         return false;
      }

      public void emitBlockQuads(class_1920 blockView, class_2680 state, class_2338 pos, Supplier<class_5819> randomSupplier, RenderContext context) {
         Object data = blockView.getBlockEntityRenderData(pos);
         class_1087 model = LootrAPI.isOldTextures() ? this.old_unopened : this.unopened;
         if (LootrAPI.isVanillaTextures()) {
            model = this.vanilla;
         } else if (data == Boolean.TRUE) {
            model = LootrAPI.isOldTextures() ? this.old_opened : this.opened;
         }

         if (model != null) {
            QuadEmitter emitter = context.getEmitter();
            Renderer renderer = RendererAccess.INSTANCE.getRenderer();
            if (renderer != null) {
               RenderMaterial material = renderer.materialById(RenderMaterial.MATERIAL_STANDARD);

               for (class_2350 dir : class_2350.values()) {
                  for (class_777 quad : model.method_4707(state, dir, randomSupplier.get())) {
                     emitter.fromVanilla(quad, material, dir);
                     emitter.emit();
                  }
               }

               for (class_777 quad : model.method_4707(state, null, randomSupplier.get())) {
                  emitter.fromVanilla(quad, material, null);
                  emitter.emit();
               }
            }
         }
      }

      public void emitItemQuads(class_1799 stack, Supplier<class_5819> randomSupplier, RenderContext context) {
      }

      public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand) {
         if (LootrAPI.isVanillaTextures()) {
            return this.vanilla.method_4707(state, side, rand);
         } else {
            return LootrAPI.isNewTextures() ? this.unopened.method_4707(state, side, rand) : this.old_unopened.method_4707(state, side, rand);
         }
      }

      public boolean method_4708() {
         return true;
      }

      public boolean method_4712() {
         return true;
      }

      public boolean method_24304() {
         return true;
      }

      public boolean method_4713() {
         return true;
      }

      public class_1058 method_4711() {
         return this.unopened.method_4711();
      }

      public class_809 method_4709() {
         return class_809.field_4301;
      }

      public class_806 method_4710() {
         return class_806.field_4292;
      }
   }
}
