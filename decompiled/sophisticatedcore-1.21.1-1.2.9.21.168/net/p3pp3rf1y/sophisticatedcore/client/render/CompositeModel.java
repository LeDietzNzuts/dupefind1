package net.p3pp3rf1y.sophisticatedcore.client.render;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import io.github.fabricators_of_create.porting_lib.models.MeshBakedModel;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryBakingContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import net.minecraft.class_806;
import net.minecraft.class_809;

public class CompositeModel {
   public static class Baked implements class_1087 {
      private final boolean isAmbientOcclusion;
      private final boolean isGui3d;
      private final boolean isSideLit;
      private final class_1058 particle;
      private final class_806 overrides;
      private final class_809 transforms;
      private final ImmutableMap<String, class_1087> children;

      public Baked(
         boolean isGui3d,
         boolean isSideLit,
         boolean isAmbientOcclusion,
         class_1058 particle,
         class_809 transforms,
         class_806 overrides,
         ImmutableMap<String, class_1087> children
      ) {
         this.children = children;
         this.isAmbientOcclusion = isAmbientOcclusion;
         this.isGui3d = isGui3d;
         this.isSideLit = isSideLit;
         this.particle = particle;
         this.overrides = overrides;
         this.transforms = transforms;
      }

      public boolean isVanillaAdapter() {
         return false;
      }

      public void emitBlockQuads(class_1920 blockView, class_2680 state, class_2338 pos, Supplier<class_5819> randomSupplier, RenderContext context) {
         UnmodifiableIterator var6 = this.children.entrySet().iterator();

         while (var6.hasNext()) {
            Entry<String, class_1087> entry = (Entry<String, class_1087>)var6.next();
            entry.getValue().emitBlockQuads(blockView, state, pos, randomSupplier, context);
         }
      }

      public void emitItemQuads(class_1799 stack, Supplier<class_5819> randomSupplier, RenderContext context) {
         UnmodifiableIterator var4 = this.children.entrySet().iterator();

         while (var4.hasNext()) {
            Entry<String, class_1087> entry = (Entry<String, class_1087>)var4.next();
            entry.getValue().emitItemQuads(stack, randomSupplier, context);
         }
      }

      public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand) {
         List<class_777> quadList = new ArrayList<>();
         UnmodifiableIterator var5 = this.children.entrySet().iterator();

         while (var5.hasNext()) {
            Entry<String, class_1087> entry = (Entry<String, class_1087>)var5.next();
            quadList.addAll(entry.getValue().method_4707(state, side, rand));
         }

         return Collections.unmodifiableList(quadList);
      }

      public boolean method_4708() {
         return this.isAmbientOcclusion;
      }

      public boolean method_4712() {
         return this.isGui3d;
      }

      public boolean method_24304() {
         return this.isSideLit;
      }

      public boolean method_4713() {
         return false;
      }

      public class_1058 method_4711() {
         return this.particle;
      }

      public class_806 method_4710() {
         return this.overrides;
      }

      public class_809 method_4709() {
         return this.transforms;
      }

      public static CompositeModel.Baked.Builder builder(IGeometryBakingContext owner, class_1058 particle, class_806 overrides, class_809 cameraTransforms) {
         return builder(owner.useAmbientOcclusion(), owner.isGui3d(), owner.useBlockLight(), particle, overrides, cameraTransforms);
      }

      public static CompositeModel.Baked.Builder builder(
         boolean isAmbientOcclusion, boolean isGui3d, boolean isSideLit, class_1058 particle, class_806 overrides, class_809 cameraTransforms
      ) {
         return new CompositeModel.Baked.Builder(isAmbientOcclusion, isGui3d, isSideLit, particle, overrides, cameraTransforms);
      }

      public static class Builder {
         private final boolean isAmbientOcclusion;
         private final boolean isGui3d;
         private final boolean isSideLit;
         private final List<class_1087> children = new ArrayList<>();
         private final List<class_777> quads = new ArrayList<>();
         private final class_806 overrides;
         private final class_809 transforms;
         private class_1058 particle;
         private class_1921 lastRenderType = null;

         private Builder(boolean isAmbientOcclusion, boolean isGui3d, boolean isSideLit, class_1058 particle, class_806 overrides, class_809 transforms) {
            this.isAmbientOcclusion = isAmbientOcclusion;
            this.isGui3d = isGui3d;
            this.isSideLit = isSideLit;
            this.particle = particle;
            this.overrides = overrides;
            this.transforms = transforms;
         }

         public void addLayer(class_1087 model) {
            this.flushQuads(null);
            this.children.add(model);
         }

         private void addLayer(class_1921 renderType, List<class_777> quads) {
            CompositeModel.Baked.SimpleModelBuilder modelBuilder = new CompositeModel.Baked.SimpleModelBuilder(
               this.isAmbientOcclusion, this.isSideLit, this.isGui3d, this.transforms, this.overrides, this.particle, renderType
            );
            quads.forEach(modelBuilder::addUnculledFace);
            this.children.add(modelBuilder.build());
         }

         private void flushQuads(class_1921 renderType) {
            if (!Objects.equals(renderType, this.lastRenderType)) {
               if (this.quads.size() > 0) {
                  this.addLayer(this.lastRenderType, this.quads);
                  this.quads.clear();
               }

               this.lastRenderType = renderType;
            }
         }

         public CompositeModel.Baked.Builder setParticle(class_1058 particleSprite) {
            this.particle = particleSprite;
            return this;
         }

         public CompositeModel.Baked.Builder addQuads(class_1921 renderType, Collection<class_777> quadsToAdd) {
            this.flushQuads(renderType);
            this.quads.addAll(quadsToAdd);
            return this;
         }

         public class_1087 build() {
            if (this.quads.size() > 0) {
               this.addLayer(this.lastRenderType, this.quads);
            }

            com.google.common.collect.ImmutableMap.Builder<String, class_1087> childrenBuilder = ImmutableMap.builder();
            com.google.common.collect.ImmutableList.Builder<class_1087> itemPassesBuilder = ImmutableList.builder();
            int i = 0;

            for (class_1087 model : this.children) {
               childrenBuilder.put("model_" + i++, model);
               itemPassesBuilder.add(model);
            }

            return new CompositeModel.Baked(
               this.isGui3d, this.isSideLit, this.isAmbientOcclusion, this.particle, this.transforms, this.overrides, childrenBuilder.build()
            );
         }
      }

      protected static class SimpleModelBuilder {
         private final MeshBuilder builder = RendererAccess.INSTANCE.getRenderer().meshBuilder();
         private final boolean hasAmbientOcclusion;
         private final boolean usesBlockLight;
         private final boolean isGui3d;
         private final class_809 transforms;
         private final class_806 overrides;
         private final class_1058 particle;
         private final RenderMaterial material;

         private SimpleModelBuilder(
            boolean hasAmbientOcclusion1,
            boolean usesBlockLight,
            boolean isGui3d,
            class_809 transforms,
            class_806 overrides,
            class_1058 particle,
            class_1921 renderType
         ) {
            this.hasAmbientOcclusion = hasAmbientOcclusion1;
            this.usesBlockLight = usesBlockLight;
            this.isGui3d = isGui3d;
            this.transforms = transforms;
            this.overrides = overrides;
            this.particle = particle;
            this.material = RendererAccess.INSTANCE.getRenderer().materialFinder().blendMode(BlendMode.fromRenderLayer(renderType)).find();
         }

         public CompositeModel.Baked.SimpleModelBuilder addUnculledFace(class_777 quad) {
            this.builder.getEmitter().fromVanilla(quad, this.material, null).emit();
            return this;
         }

         public class_1087 build() {
            return new MeshBakedModel(
               this.builder.build(), this.hasAmbientOcclusion, this.usesBlockLight, this.isGui3d, this.particle, this.transforms, this.overrides
            );
         }
      }
   }
}
