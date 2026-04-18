package net.p3pp3rf1y.sophisticatedcore.util.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import io.github.fabricators_of_create.porting_lib.core.PortingLib;
import io.github.fabricators_of_create.porting_lib.models.MeshBakedModel;
import io.github.fabricators_of_create.porting_lib.models.UnbakedGeometryHelper;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryBakingContext;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.geometry.IUnbakedGeometry;
import io.github.fabricators_of_create.porting_lib.models.geometry.SimpleModelState;
import io.github.fabricators_of_create.porting_lib.models.geometry.StandaloneGeometryBakingContext;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.class_1058;
import net.minecraft.class_1086;
import net.minecraft.class_1087;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_326;
import net.minecraft.class_3518;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_3665;
import net.minecraft.class_4590;
import net.minecraft.class_4730;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_777;
import net.minecraft.class_7775;
import net.minecraft.class_785;
import net.minecraft.class_7923;
import net.minecraft.class_806;
import net.minecraft.class_809;
import net.p3pp3rf1y.sophisticatedcore.client.render.CompositeModel;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class DynamicFluidContainerModel implements IUnbakedGeometry<DynamicFluidContainerModel> {
   private static final class_4590 FLUID_TRANSFORM = new class_4590(new Vector3f(), new Quaternionf(), new Vector3f(1.0F, 1.0F, 1.002F), new Quaternionf());
   private static final class_4590 COVER_TRANSFORM = new class_4590(new Vector3f(), new Quaternionf(), new Vector3f(1.0F, 1.0F, 1.004F), new Quaternionf());
   private final class_3611 fluid;
   private final boolean flipGas;
   private final boolean coverIsMask;
   private final boolean applyFluidLuminosity;

   private DynamicFluidContainerModel(class_3611 fluid, boolean flipGas, boolean coverIsMask, boolean applyFluidLuminosity) {
      this.fluid = fluid;
      this.flipGas = flipGas;
      this.coverIsMask = coverIsMask;
      this.applyFluidLuminosity = applyFluidLuminosity;
   }

   public DynamicFluidContainerModel withFluid(class_3611 newFluid) {
      return new DynamicFluidContainerModel(newFluid, this.flipGas, this.coverIsMask, this.applyFluidLuminosity);
   }

   @Nullable
   public class_1087 bake(
      IGeometryBakingContext context, class_7775 baker, Function<class_4730, class_1058> spriteGetter, class_3665 modelState, class_806 overrides
   ) {
      class_4730 particleLocation = context.hasMaterial("particle") ? context.getMaterial("particle") : null;
      class_4730 baseLocation = context.hasMaterial("base") ? context.getMaterial("base") : null;
      class_4730 fluidMaskLocation = context.hasMaterial("fluid") ? context.getMaterial("fluid") : null;
      class_4730 coverLocation = context.hasMaterial("cover") ? context.getMaterial("cover") : null;
      class_1058 baseSprite = baseLocation != null ? spriteGetter.apply(baseLocation) : null;
      class_1058 templateSprite = fluidMaskLocation != null ? spriteGetter.apply(fluidMaskLocation) : null;
      class_1058 coverSprite = coverLocation == null || this.coverIsMask && baseLocation == null ? null : spriteGetter.apply(coverLocation);
      class_1058 particleSprite = particleLocation != null ? spriteGetter.apply(particleLocation) : null;
      StandaloneGeometryBakingContext itemContext = StandaloneGeometryBakingContext.builder(context)
         .withGui3d(false)
         .withUseBlockLight(false)
         .build(PortingLib.id("dynamic_fluid_container"));
      DynamicFluidContainerModel.ContainedFluidOverrideHandler overrideHandler = new DynamicFluidContainerModel.ContainedFluidOverrideHandler(
         overrides, baker, itemContext, this
      );
      return new DynamicFluidContainerModel.LazyBakedModel(itemContext, baseSprite, templateSprite, coverSprite, particleSprite, modelState, overrideHandler);
   }

   public static class Colors implements class_326 {
      public int getColor(class_1799 stack, int tintIndex) {
         return tintIndex != 1 ? -1 : FluidUtil.getFluidContained(stack).map(f -> FluidVariantRendering.getColor((FluidVariant)f.resource())).orElse(-1);
      }
   }

   private static final class ContainedFluidOverrideHandler extends class_806 {
      private final Map<String, class_1087> cache = Maps.newHashMap();
      private final class_806 nested;
      private final class_7775 baker;
      private final IGeometryBakingContext owner;
      private final DynamicFluidContainerModel parent;

      private ContainedFluidOverrideHandler(class_806 nested, class_7775 baker, IGeometryBakingContext owner, DynamicFluidContainerModel parent) {
         this.nested = nested;
         this.baker = baker;
         this.owner = owner;
         this.parent = parent;
      }

      public class_1087 method_3495(class_1087 originalModel, class_1799 stack, @Nullable class_638 level, @Nullable class_1309 entity, int seed) {
         class_1087 overridden = this.nested.method_3495(originalModel, stack, level, entity, seed);
         return overridden != originalModel ? overridden : FluidUtil.getFluidContained(stack).map(f -> {
            class_3611 fluid = ((FluidVariant)f.resource()).getFluid();
            String name = class_7923.field_41173.method_10221(fluid).toString();
            if (!this.cache.containsKey(name)) {
               DynamicFluidContainerModel unbaked = this.parent.withFluid(fluid);
               class_1087 bakedModel = unbaked.bake(this.owner, this.baker, class_4730::method_24148, class_1086.field_5350, this);
               this.cache.put(name, bakedModel);
               return bakedModel;
            } else {
               return this.cache.get(name);
            }
         }).orElse(originalModel);
      }
   }

   public final class LazyBakedModel implements class_1087 {
      private final IGeometryBakingContext itemContext;
      private final class_1058 baseSprite;
      private final class_1058 templateSprite;
      private final class_1058 coverSprite;
      private final class_1058 particleSprite;
      private final class_3665 modelState;
      private final class_806 overrides;
      private class_1087 compositeModel;

      private LazyBakedModel(
         IGeometryBakingContext itemContext,
         class_1058 baseSprite,
         class_1058 templateSprite,
         class_1058 coverSprite,
         class_1058 particleSprite,
         class_3665 modelState,
         class_806 overrides
      ) {
         this.itemContext = itemContext;
         this.baseSprite = baseSprite;
         this.templateSprite = templateSprite;
         this.coverSprite = coverSprite;
         this.particleSprite = particleSprite;
         this.modelState = modelState;
         this.overrides = overrides;
      }

      private class_1087 wrapped() {
         if (this.compositeModel == null) {
            this.compositeModel = this.initializeWrappedModel();
         }

         return this.compositeModel;
      }

      private class_1087 initializeWrappedModel() {
         class_3665 modelState = this.modelState;
         if (DynamicFluidContainerModel.this.flipGas
            && DynamicFluidContainerModel.this.fluid != class_3612.field_15906
            && DynamicFluidContainerModel.this.fluid.getFluidType().isLighterThanAir()) {
            modelState = new SimpleModelState(
               this.modelState.method_3509().method_22933(new class_4590(null, new Quaternionf(0.0F, 0.0F, 1.0F, 0.0F), null, null))
            );
         }

         class_1058 fluidSprite;
         if (DynamicFluidContainerModel.this.fluid != class_3612.field_15906) {
            fluidSprite = FluidVariantRendering.getSprite(FluidVariant.of(DynamicFluidContainerModel.this.fluid));
         } else {
            fluidSprite = null;
         }

         CompositeModel.Baked.Builder modelBuilder = CompositeModel.Baked.builder(
            this.itemContext, this.particleSprite, this.overrides, this.itemContext.getTransforms()
         );
         class_1058 particleSprite = this.particleSprite;
         if (particleSprite == null) {
            particleSprite = fluidSprite;
         }

         if (particleSprite == null) {
            particleSprite = this.baseSprite;
         }

         if (particleSprite == null && !DynamicFluidContainerModel.this.coverIsMask) {
            particleSprite = this.coverSprite;
         }

         if (this.baseSprite != null) {
            List<class_785> unbaked = UnbakedGeometryHelper.createUnbakedItemElements(0, this.baseSprite);
            List<class_777> quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> this.baseSprite, modelState);
            modelBuilder.addQuads(class_1921.method_23583(), quads);
         }

         if (this.templateSprite != null && fluidSprite != null) {
            SimpleModelState transformedState = new SimpleModelState(
               modelState.method_3509().method_22933(DynamicFluidContainerModel.FLUID_TRANSFORM), modelState.method_3512()
            );
            List<class_785> unbaked = UnbakedGeometryHelper.createUnbakedItemMaskElements(1, this.templateSprite);
            List<class_777> quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> fluidSprite, transformedState);
            boolean emissive = DynamicFluidContainerModel.this.applyFluidLuminosity && DynamicFluidContainerModel.this.fluid.getFluidType().getLightLevel() > 0;
            RenderMaterial material = RendererAccess.INSTANCE
               .getRenderer()
               .materialFinder()
               .blendMode(BlendMode.fromRenderLayer(class_1921.method_23577()))
               .emissive(emissive)
               .find();
            MeshBuilder builder = RendererAccess.INSTANCE.getRenderer().meshBuilder();
            quads.forEach(quad -> builder.getEmitter().fromVanilla(quad, material, null).emit());
            modelBuilder.addLayer(
               new MeshBakedModel(
                  builder.build(),
                  this.itemContext.useAmbientOcclusion(),
                  this.itemContext.useBlockLight(),
                  this.itemContext.isGui3d(),
                  particleSprite,
                  this.itemContext.getTransforms(),
                  this.overrides
               )
            );
         }

         if (this.coverSprite != null) {
            class_1058 sprite = DynamicFluidContainerModel.this.coverIsMask ? this.baseSprite : this.coverSprite;
            if (sprite != null) {
               SimpleModelState transformedState = new SimpleModelState(
                  modelState.method_3509().method_22933(DynamicFluidContainerModel.COVER_TRANSFORM), modelState.method_3512()
               );
               List<class_785> unbaked = UnbakedGeometryHelper.createUnbakedItemMaskElements(2, this.coverSprite);
               List<class_777> quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> sprite, transformedState);
               modelBuilder.addQuads(class_1921.method_23583(), quads);
            }
         }

         modelBuilder.setParticle(particleSprite);
         return modelBuilder.build();
      }

      public boolean isVanillaAdapter() {
         return false;
      }

      public void emitBlockQuads(class_1920 blockView, class_2680 state, class_2338 pos, Supplier<class_5819> randomSupplier, RenderContext context) {
         this.wrapped().emitBlockQuads(blockView, state, pos, randomSupplier, context);
      }

      public void emitItemQuads(class_1799 stack, Supplier<class_5819> randomSupplier, RenderContext context) {
         this.wrapped().emitItemQuads(stack, randomSupplier, context);
      }

      public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 random) {
         return this.wrapped().method_4707(state, side, random);
      }

      public boolean method_4708() {
         return this.wrapped().method_4708();
      }

      public boolean method_4712() {
         return this.wrapped().method_4712();
      }

      public boolean method_4713() {
         return false;
      }

      public class_1058 method_4711() {
         return this.wrapped().method_4711();
      }

      public boolean method_24304() {
         return this.wrapped().method_24304();
      }

      public class_809 method_4709() {
         return this.wrapped().method_4709();
      }

      public class_806 method_4710() {
         return this.wrapped().method_4710();
      }
   }

   public static final class Loader implements IGeometryLoader<DynamicFluidContainerModel> {
      public static final DynamicFluidContainerModel.Loader INSTANCE = new DynamicFluidContainerModel.Loader();

      private Loader() {
      }

      public DynamicFluidContainerModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) {
         if (!jsonObject.has("fluid")) {
            throw new RuntimeException("Bucket model requires 'fluid' value.");
         } else {
            class_2960 fluidName = class_2960.method_60654(jsonObject.get("fluid").getAsString());
            class_3611 fluid = (class_3611)class_7923.field_41173.method_10223(fluidName);
            boolean flip = class_3518.method_15258(jsonObject, "flip_gas", false);
            boolean coverIsMask = class_3518.method_15258(jsonObject, "cover_is_mask", true);
            boolean applyFluidLuminosity = class_3518.method_15258(jsonObject, "apply_fluid_luminosity", true);
            return new DynamicFluidContainerModel(fluid, flip, coverIsMask, applyFluidLuminosity);
         }
      }
   }
}
