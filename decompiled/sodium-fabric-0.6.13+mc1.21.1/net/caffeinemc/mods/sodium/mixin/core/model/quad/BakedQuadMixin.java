package net.caffeinemc.mods.sodium.mixin.core.model.quad;

import net.caffeinemc.mods.sodium.client.model.quad.BakedQuadView;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFlags;
import net.caffeinemc.mods.sodium.client.util.ModelQuadUtil;
import net.minecraft.class_1058;
import net.minecraft.class_2350;
import net.minecraft.class_777;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_777.class)
public class BakedQuadMixin implements BakedQuadView {
   @Shadow
   @Final
   protected int[] field_4175;
   @Shadow
   @Final
   protected class_1058 field_4176;
   @Shadow
   @Final
   protected int field_4174;
   @Shadow
   @Final
   protected class_2350 field_4173;
   @Shadow
   @Final
   private boolean field_22441;
   @Unique
   private int flags;
   @Unique
   private int normal;
   @Unique
   private ModelQuadFacing normalFace = null;

   @Inject(method = "<init>([IILnet/minecraft/class_2350;Lnet/minecraft/class_1058;Z)V", at = @At("RETURN"))
   private void init(int[] vertexData, int colorIndex, class_2350 face, class_1058 sprite, boolean shade, CallbackInfo ci) {
      this.normal = this.calculateNormal();
      this.normalFace = ModelQuadFacing.fromPackedNormal(this.normal);
      this.flags = ModelQuadFlags.getQuadFlags(this, face);
   }

   @Override
   public float getX(int idx) {
      return Float.intBitsToFloat(this.field_4175[ModelQuadUtil.vertexOffset(idx) + 0]);
   }

   @Override
   public float getY(int idx) {
      return Float.intBitsToFloat(this.field_4175[ModelQuadUtil.vertexOffset(idx) + 0 + 1]);
   }

   @Override
   public float getZ(int idx) {
      return Float.intBitsToFloat(this.field_4175[ModelQuadUtil.vertexOffset(idx) + 0 + 2]);
   }

   @Override
   public int getColor(int idx) {
      return this.field_4175[ModelQuadUtil.vertexOffset(idx) + 3];
   }

   @Override
   public int getVertexNormal(int idx) {
      return this.field_4175[ModelQuadUtil.vertexOffset(idx) + 7];
   }

   @Override
   public int getLight(int idx) {
      return this.field_4175[ModelQuadUtil.vertexOffset(idx) + 6];
   }

   @Override
   public class_1058 getSprite() {
      return this.field_4176;
   }

   @Override
   public float getTexU(int idx) {
      return Float.intBitsToFloat(this.field_4175[ModelQuadUtil.vertexOffset(idx) + 4]);
   }

   @Override
   public float getTexV(int idx) {
      return Float.intBitsToFloat(this.field_4175[ModelQuadUtil.vertexOffset(idx) + 4 + 1]);
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public int getColorIndex() {
      return this.field_4174;
   }

   @Override
   public ModelQuadFacing getNormalFace() {
      return this.normalFace;
   }

   @Override
   public int getFaceNormal() {
      return this.normal;
   }

   @Override
   public class_2350 getLightFace() {
      return this.field_4173;
   }

   @Unique(silent = true)
   @Override
   public boolean hasShade() {
      return this.field_22441;
   }

   @Override
   public boolean hasAO() {
      return true;
   }
}
