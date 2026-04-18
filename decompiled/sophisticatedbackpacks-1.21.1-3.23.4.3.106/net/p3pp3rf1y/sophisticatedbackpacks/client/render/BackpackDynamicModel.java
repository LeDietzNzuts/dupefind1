package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryBakingContext;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.geometry.IUnbakedGeometry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.impl.client.indigo.renderer.mesh.EncodingFormat;
import net.fabricmc.fabric.impl.client.indigo.renderer.mesh.MutableQuadViewImpl;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1100;
import net.minecraft.class_1309;
import net.minecraft.class_1723;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3665;
import net.minecraft.class_4730;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_777;
import net.minecraft.class_7775;
import net.minecraft.class_793;
import net.minecraft.class_804;
import net.minecraft.class_806;
import net.minecraft.class_809;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedBatteryUpgrade.BatteryRenderInfo;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade.TankRenderInfo;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class BackpackDynamicModel implements IUnbakedGeometry<BackpackDynamicModel> {
   private final Map<BackpackDynamicModel.ModelPart, class_1100> modelParts;

   private BackpackDynamicModel(Map<BackpackDynamicModel.ModelPart, class_1100> modelParts) {
      this.modelParts = modelParts;
   }

   public class_1087 bake(
      IGeometryBakingContext context, class_7775 baker, Function<class_4730, class_1058> spriteGetter, class_3665 modelTransform, class_806 overrides
   ) {
      Builder<BackpackDynamicModel.ModelPart, class_1087> builder = ImmutableMap.builder();
      this.modelParts.forEach((part, model) -> {
         class_1087 bakedModel = model.method_4753(baker, spriteGetter, modelTransform);
         if (bakedModel != null) {
            builder.put(part, bakedModel);
         }
      });
      return new BackpackDynamicModel.BackpackBakedModel(builder.build(), modelTransform);
   }

   public void resolveParents(Function<class_2960, class_1100> modelGetter, IGeometryBakingContext context) {
      this.modelParts.values().forEach(model -> model.method_45785(modelGetter));
   }

   private static final class BackpackBakedModel implements class_1087 {
      private static final class_809 ITEM_TRANSFORMS = createItemTransforms();
      private static final class_2960 BACKPACK_MODULES_TEXTURE = class_2960.method_60655("sophisticatedbackpacks", "block/backpack_modules");
      private final BackpackDynamicModel.BackpackItemOverrideList overrideList = new BackpackDynamicModel.BackpackItemOverrideList(this);
      private final Map<BackpackDynamicModel.ModelPart, class_1087> models;
      private final class_3665 modelTransform;
      private boolean tankLeft;
      @Nullable
      private TankRenderInfo leftTankRenderInfo = null;
      private boolean tankRight;
      @Nullable
      private TankRenderInfo rightTankRenderInfo = null;
      private boolean battery;
      @Nullable
      private BatteryRenderInfo batteryRenderInfo = null;

      private static class_809 createItemTransforms() {
         return new class_809(
            new class_804(new Vector3f(85.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.125F, -0.28125F), new Vector3f(0.75F, 0.75F, 0.75F)),
            new class_804(new Vector3f(85.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.125F, -0.28125F), new Vector3f(0.75F, 0.75F, 0.75F)),
            new class_804(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.5F, 0.5F, 0.5F)),
            new class_804(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.5F, 0.5F, 0.5F)),
            new class_804(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.890625F, 0.0F), new Vector3f(1.0F, 1.0F, 1.0F)),
            new class_804(new Vector3f(30.0F, 225.0F, 0.0F), new Vector3f(0.0F, 0.078125F, 0.0F), new Vector3f(0.9F, 0.9F, 0.9F)),
            new class_804(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.1875F, 0.0F), new Vector3f(0.5F, 0.5F, 0.5F)),
            new class_804(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, -0.140625F), new Vector3f(0.75F, 0.75F, 0.75F))
         );
      }

      public BackpackBakedModel(Map<BackpackDynamicModel.ModelPart, class_1087> models, class_3665 modelTransform) {
         this.models = models;
         this.modelTransform = modelTransform;
      }

      @Nonnull
      public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand) {
         List<class_777> ret = new ArrayList<>(this.models.get(BackpackDynamicModel.ModelPart.BASE).method_4707(state, side, rand));
         if (state == null) {
            this.addLeftSide(state, side, rand, ret, this.tankLeft);
            this.addRightSide(state, side, rand, ret, this.tankRight);
            this.addFront(state, side, rand, ret, this.battery);
         } else {
            this.addLeftSide(state, side, rand, ret, (Boolean)state.method_11654(BackpackBlock.LEFT_TANK));
            this.addRightSide(state, side, rand, ret, (Boolean)state.method_11654(BackpackBlock.RIGHT_TANK));
            this.addFront(state, side, rand, ret, (Boolean)state.method_11654(BackpackBlock.BATTERY));
         }

         return ret;
      }

      private void addFront(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand, List<class_777> ret, boolean battery) {
         if (battery) {
            if (this.batteryRenderInfo != null) {
               this.addCharge(ret, this.batteryRenderInfo.getChargeRatio());
            }

            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.BATTERY).method_4707(state, side, rand));
         } else {
            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.FRONT_POUCH).method_4707(state, side, rand));
         }
      }

      private void addCharge(List<class_777> ret, float chargeRatio) {
         if (!class_3532.method_15347(chargeRatio, 0.0F)) {
            int pixels = (int)(chargeRatio * 4.0F);
            float minX = (10 - pixels) / 16.0F;
            float minY = 0.125F;
            float minZ = 0.121875F;
            float maxX = minX + pixels / 16.0F;
            float maxY = minY + 0.0625F;
            float[] cols = new float[]{1.0F, 1.0F, 1.0F, 1.0F, -1.0F};
            class_1058 sprite = (class_1058)class_310.method_1551().method_1549(class_1723.field_21668).apply(BACKPACK_MODULES_TEXTURE);
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(maxX, maxY, minZ), this.getVector(maxX, minY, minZ), this.getVector(minX, minY, minZ), this.getVector(minX, maxY, minZ)
                  ),
                  cols,
                  sprite,
                  class_2350.field_11043,
                  14.0F,
                  14.0F + pixels / 2.0F,
                  6.0F,
                  6.5F
               )
            );
         }
      }

      private void addRightSide(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand, List<class_777> ret, boolean tankRight) {
         if (tankRight) {
            if (this.rightTankRenderInfo != null) {
               this.rightTankRenderInfo.getFluid().ifPresent(fluid -> this.addFluid(ret, fluid, this.rightTankRenderInfo.getFillRatio(), 0.0375, 3));
            }

            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.RIGHT_TANK).method_4707(state, side, rand));
         } else {
            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.RIGHT_POUCH).method_4707(state, side, rand));
         }
      }

      private void addLeftSide(@Nullable class_2680 state, @Nullable class_2350 side, class_5819 rand, List<class_777> ret, boolean tankLeft) {
         if (tankLeft) {
            if (this.leftTankRenderInfo != null) {
               this.leftTankRenderInfo.getFluid().ifPresent(fluid -> this.addFluid(ret, fluid, this.leftTankRenderInfo.getFillRatio(), 0.803125, 2));
            }

            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.LEFT_TANK).method_4707(state, side, rand));
         } else {
            ret.addAll(this.models.get(BackpackDynamicModel.ModelPart.LEFT_POUCH).method_4707(state, side, rand));
         }
      }

      private void addFluid(List<class_777> ret, FluidStack fluidStack, float ratio, double xMin, int tintIndex) {
         if (fluidStack != FluidStack.EMPTY && !class_3532.method_15347(ratio, 0.0F)) {
            double yMin = 0.09375;
            double yMax = yMin + ratio * 6.0F / 16.0;
            class_238 bounds = new class_238(xMin, yMin, 0.421875, xMin + 0.15625, yMax, 0.578125);
            FluidVariant fluidVariant = fluidStack.getVariant();
            int color = FluidVariantRendering.getColor(fluidVariant);
            float[] cols = new float[]{
               (color >> 24 & 0xFF) / 255.0F, (color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, tintIndex
            };
            class_1058 still = FluidVariantRendering.getSprite(fluidVariant);
            float bx1 = 0.0F;
            float bx2 = 5.0F;
            float by1 = 0.0F;
            float by2 = ratio * 10.0F;
            float bz1 = 0.0F;
            float bz2 = 5.0F;
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1321),
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1321)
                  ),
                  cols,
                  still,
                  class_2350.field_11036,
                  bx1,
                  bx2,
                  bz1,
                  bz2
               )
            );
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1321),
                     this.getVector(bounds.field_1320, bounds.field_1322, bounds.field_1321),
                     this.getVector(bounds.field_1323, bounds.field_1322, bounds.field_1321),
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1321)
                  ),
                  cols,
                  still,
                  class_2350.field_11043,
                  bx1,
                  bx2,
                  by1,
                  by2
               )
            );
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1324),
                     this.getVector(bounds.field_1323, bounds.field_1322, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1322, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1324)
                  ),
                  cols,
                  still,
                  class_2350.field_11035,
                  bx1,
                  bx2,
                  by1,
                  by2
               )
            );
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1321),
                     this.getVector(bounds.field_1323, bounds.field_1322, bounds.field_1321),
                     this.getVector(bounds.field_1323, bounds.field_1322, bounds.field_1324),
                     this.getVector(bounds.field_1323, bounds.field_1325, bounds.field_1324)
                  ),
                  cols,
                  still,
                  class_2350.field_11039,
                  bz1,
                  bz2,
                  by1,
                  by2
               )
            );
            ret.add(
               this.createQuad(
                  List.of(
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1322, bounds.field_1324),
                     this.getVector(bounds.field_1320, bounds.field_1322, bounds.field_1321),
                     this.getVector(bounds.field_1320, bounds.field_1325, bounds.field_1321)
                  ),
                  cols,
                  still,
                  class_2350.field_11034,
                  bz1,
                  bz2,
                  by1,
                  by2
               )
            );
         }
      }

      private Vector3f getVector(double x, double y, double z) {
         Vector3f ret = new Vector3f((float)x, (float)y, (float)z);
         this.rotate(ret, this.modelTransform.method_3509().method_22936());
         return ret;
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
         return this.models.get(BackpackDynamicModel.ModelPart.BASE).method_4711();
      }

      public class_806 method_4710() {
         return this.overrideList;
      }

      public class_809 method_4709() {
         return ITEM_TRANSFORMS;
      }

      private class_777 createQuad(List<Vector3f> vecs, float[] colors, class_1058 sprite, class_2350 face, float u1, float u2, float v1, float v2) {
         BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer quadBaker = new BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer();
         quadBaker.setSprite(sprite);
         class_2382 dirVec = face.method_10163();
         quadBaker.setDirection(face);
         quadBaker.setTintIndex((int)colors[4]);
         u1 = sprite.method_4594() + u1 / 4.0F * sprite.method_23842();
         u2 = sprite.method_4594() + u2 / 4.0F * sprite.method_23842();
         v1 = sprite.method_4593() + v1 / 4.0F * sprite.method_23842();
         v2 = sprite.method_4593() + v2 / 4.0F * sprite.method_23842();
         quadBaker.addVertex(vecs.get(0).x(), vecs.get(0).y(), vecs.get(0).z())
            .setColor(colors[1], colors[2], colors[3], colors[0])
            .setUv(u1, v1)
            .setNormal(dirVec.method_10263(), dirVec.method_10264(), dirVec.method_10260());
         quadBaker.addVertex(vecs.get(1).x(), vecs.get(1).y(), vecs.get(1).z())
            .setColor(colors[1], colors[2], colors[3], colors[0])
            .setUv(u1, v2)
            .setNormal(dirVec.method_10263(), dirVec.method_10264(), dirVec.method_10260());
         quadBaker.addVertex(vecs.get(2).x(), vecs.get(2).y(), vecs.get(2).z())
            .setColor(colors[1], colors[2], colors[3], colors[0])
            .setUv(u2, v2)
            .setNormal(dirVec.method_10263(), dirVec.method_10264(), dirVec.method_10260());
         quadBaker.addVertex(vecs.get(3).x(), vecs.get(3).y(), vecs.get(3).z())
            .setColor(colors[1], colors[2], colors[3], colors[0])
            .setUv(u2, v1)
            .setNormal(dirVec.method_10263(), dirVec.method_10264(), dirVec.method_10260());
         return quadBaker.bakeQuad();
      }

      private void rotate(Vector3f posIn, Matrix4f transform) {
         Vector3f originIn = new Vector3f(0.5F, 0.5F, 0.5F);
         Vector4f vector4f = transform.transform(new Vector4f(posIn.x() - originIn.x(), posIn.y() - originIn.y(), posIn.z() - originIn.z(), 1.0F));
         posIn.set(vector4f.x() + originIn.x(), vector4f.y() + originIn.y(), vector4f.z() + originIn.z());
      }

      private static class QuadBakingVertexConsumer extends MutableQuadViewImpl {
         private class_1058 sprite;
         private int vertexIndex;

         public QuadBakingVertexConsumer() {
            this.data = new int[EncodingFormat.TOTAL_STRIDE];
            this.vertexIndex = -1;
            this.clear();
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setDirection(class_2350 face) {
            this.nominalFace(face);
            return this;
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setTintIndex(int tintIndex) {
            this.colorIndex(tintIndex);
            return this;
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer addVertex(float x, float y, float z) {
            if (++this.vertexIndex > 4) {
               throw new IllegalStateException("Expected quad export after fourth vertex");
            } else {
               this.pos(this.vertexIndex, x, y, z);
               return this;
            }
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setUv(float u, float v) {
            this.uv(this.vertexIndex, u, v);
            return this;
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setNormal(float x, float y, float z) {
            this.normal(this.vertexIndex, x, y, z);
            return this;
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setColor(float red, float green, float blue, float alpha) {
            return this.setColor((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), (int)(alpha * 255.0F));
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setColor(int r, int g, int b, int a) {
            this.color(this.vertexIndex, (a & 0xFF) << 24 | (b & 0xFF) << 16 | (g & 0xFF) << 8 | r & 0xFF);
            return this;
         }

         public BackpackDynamicModel.BackpackBakedModel.QuadBakingVertexConsumer setSprite(class_1058 sprite) {
            this.sprite = sprite;
            return this;
         }

         public class_777 bakeQuad() {
            return this.toBakedQuad(this.sprite);
         }

         public void emitDirectly() {
         }
      }
   }

   private static class BackpackItemOverrideList extends class_806 {
      private final BackpackDynamicModel.BackpackBakedModel backpackModel;

      public BackpackItemOverrideList(BackpackDynamicModel.BackpackBakedModel backpackModel) {
         this.backpackModel = backpackModel;
      }

      @Nullable
      public class_1087 method_3495(class_1087 model, class_1799 stack, @Nullable class_638 world, @Nullable class_1309 livingEntity, int seed) {
         this.backpackModel.tankRight = false;
         this.backpackModel.tankLeft = false;
         this.backpackModel.battery = false;
         IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(stack);
         RenderInfo renderInfo = backpackWrapper.getRenderInfo();
         Map<TankPosition, TankRenderInfo> tankRenderInfos = renderInfo.getTankRenderInfos();
         tankRenderInfos.forEach((pos, info) -> {
            if (pos == TankPosition.LEFT) {
               this.backpackModel.tankLeft = true;
               this.backpackModel.leftTankRenderInfo = info;
            } else {
               this.backpackModel.tankRight = true;
               this.backpackModel.rightTankRenderInfo = info;
            }
         });
         renderInfo.getBatteryRenderInfo().ifPresent(batteryRenderInfo -> {
            this.backpackModel.battery = true;
            this.backpackModel.batteryRenderInfo = batteryRenderInfo;
         });
         return this.backpackModel;
      }
   }

   public static final class Loader implements IGeometryLoader<BackpackDynamicModel> {
      public static final BackpackDynamicModel.Loader INSTANCE = new BackpackDynamicModel.Loader();

      public BackpackDynamicModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext) {
         Builder<BackpackDynamicModel.ModelPart, class_1100> builder = ImmutableMap.builder();
         Builder<String, Either<class_4730, String>> texturesBuilder = ImmutableMap.builder();
         if (modelContents.has("clipsTexture")) {
            class_2960 clipsTexture = class_2960.method_12829(modelContents.get("clipsTexture").getAsString());
            if (clipsTexture != null) {
               texturesBuilder.put("clips", Either.left(new class_4730(class_1723.field_21668, clipsTexture)));
            }
         }

         ImmutableMap<String, Either<class_4730, String>> textures = texturesBuilder.build();

         for (BackpackDynamicModel.ModelPart part : BackpackDynamicModel.ModelPart.values()) {
            this.addPartModel(builder, part, textures);
         }

         return new BackpackDynamicModel(builder.build());
      }

      private void addPartModel(
         Builder<BackpackDynamicModel.ModelPart, class_1100> builder,
         BackpackDynamicModel.ModelPart modelPart,
         ImmutableMap<String, Either<class_4730, String>> textures
      ) {
         builder.put(
            modelPart,
            new class_793(
               SophisticatedBackpacks.getRL("block/backpack_" + modelPart.name().toLowerCase(Locale.ENGLISH)),
               Collections.emptyList(),
               textures,
               true,
               null,
               class_809.field_4301,
               Collections.emptyList()
            )
         );
      }
   }

   private static enum ModelPart {
      BASE,
      BATTERY,
      FRONT_POUCH,
      LEFT_POUCH,
      LEFT_TANK,
      RIGHT_POUCH,
      RIGHT_TANK;
   }
}
