package net.caffeinemc.mods.lithium.mixin.world.explosions.block_raycast;

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2394;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_5362;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_7134;
import net.minecraft.class_1927.class_4179;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1927.class)
public abstract class ExplosionMixin {
   @Shadow
   @Final
   private float field_9190;
   @Shadow
   @Final
   private double field_9195;
   @Shadow
   @Final
   private double field_9192;
   @Shadow
   @Final
   private double field_9189;
   @Shadow
   @Final
   private class_1937 field_9187;
   @Shadow
   @Final
   private class_5362 field_25400;
   @Shadow
   @Final
   private boolean field_9186;
   private final class_2339 cachedPos = new class_2339();
   private int prevChunkX = Integer.MIN_VALUE;
   private int prevChunkZ = Integer.MIN_VALUE;
   private class_2791 prevChunk;
   private boolean explodeAirBlocks;
   private int bottomY;
   private int topY;

   @Inject(
      method = "<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;Lnet/minecraft/class_1282;Lnet/minecraft/class_5362;DDDFZLnet/minecraft/class_1927$class_4179;Lnet/minecraft/class_2394;Lnet/minecraft/class_2394;Lnet/minecraft/class_6880;)V",
      at = @At("TAIL")
   )
   private void init(
      class_1937 world,
      class_1297 entity,
      class_1282 damageSource,
      class_5362 behavior,
      double x,
      double y,
      double z,
      float power,
      boolean createFire,
      class_4179 destructionType,
      class_2394 particle,
      class_2394 emitterParticle,
      class_6880<?> soundEvent,
      CallbackInfo ci
   ) {
      this.bottomY = this.field_9187.method_31607();
      this.topY = this.field_9187.method_31600();
      boolean explodeAir = this.field_9186;
      if (!explodeAir && this.field_9187.method_27983() == class_1937.field_25181 && this.field_9187.method_40134().method_40225(class_7134.field_37668)) {
         float overestimatedExplosionRange = 8 + (int)(6.0F * this.field_9190);
         int endPortalX = 0;
         int endPortalZ = 0;
         if (overestimatedExplosionRange > Math.abs(this.field_9195 - endPortalX) && overestimatedExplosionRange > Math.abs(this.field_9189 - endPortalZ)) {
            explodeAir = true;
         }
      }

      this.explodeAirBlocks = explodeAir;
   }

   @Redirect(method = "method_8348()V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Sets;newHashSet()Ljava/util/HashSet;", remap = false))
   public HashSet<class_2338> skipNewHashSet() {
      return null;
   }

   @ModifyConstant(method = "method_8348()V", constant = @Constant(intValue = 16, ordinal = 1))
   public int skipLoop(int prevValue) {
      return 0;
   }

   @Redirect(
      method = "method_8348()V",
      at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;addAll(Ljava/util/Collection;)Z", remap = false)
   )
   public boolean collectBlocks(ObjectArrayList<class_2338> affectedBlocks, Collection<class_2338> collection) {
      LongOpenHashSet touched = new LongOpenHashSet(0);
      class_5819 random = this.field_9187.field_9229;

      for (int rayX = 0; rayX < 16; rayX++) {
         boolean xPlane = rayX == 0 || rayX == 15;
         double vecX = rayX / 15.0F * 2.0F - 1.0F;

         for (int rayY = 0; rayY < 16; rayY++) {
            boolean yPlane = rayY == 0 || rayY == 15;
            double vecY = rayY / 15.0F * 2.0F - 1.0F;

            for (int rayZ = 0; rayZ < 16; rayZ++) {
               boolean zPlane = rayZ == 0 || rayZ == 15;
               if (xPlane || yPlane || zPlane) {
                  double vecZ = rayZ / 15.0F * 2.0F - 1.0F;
                  this.performRayCast(random, vecX, vecY, vecZ, touched);
               }
            }
         }
      }

      LongIterator it = touched.iterator();
      boolean added = false;

      while (it.hasNext()) {
         added |= affectedBlocks.add(class_2338.method_10092(it.nextLong()));
      }

      return added;
   }

   @Unique
   private void performRayCast(class_5819 random, double vecX, double vecY, double vecZ, LongOpenHashSet touched) {
      double dist = Math.sqrt(vecX * vecX + vecY * vecY + vecZ * vecZ);
      double normX = vecX / dist * 0.3;
      double normY = vecY / dist * 0.3;
      double normZ = vecZ / dist * 0.3;
      float strength = this.field_9190 * (0.7F + random.method_43057() * 0.6F);
      double stepX = this.field_9195;
      double stepY = this.field_9192;
      double stepZ = this.field_9189;
      int prevX = Integer.MIN_VALUE;
      int prevY = Integer.MIN_VALUE;
      int prevZ = Integer.MIN_VALUE;
      float prevResistance = 0.0F;
      int boundMinY = this.bottomY;

      for (int boundMaxY = this.topY; strength > 0.0F; stepZ += normZ) {
         int blockX = class_3532.method_15357(stepX);
         int blockY = class_3532.method_15357(stepY);
         int blockZ = class_3532.method_15357(stepZ);
         float resistance;
         if (prevX == blockX && prevY == blockY && prevZ == blockZ) {
            resistance = prevResistance;
         } else {
            if (blockY < boundMinY || blockY >= boundMaxY || blockX < -30000000 || blockZ < -30000000 || blockX >= 30000000 || blockZ >= 30000000) {
               return;
            }

            resistance = this.traverseBlock(strength, blockX, blockY, blockZ, touched);
            prevX = blockX;
            prevY = blockY;
            prevZ = blockZ;
            prevResistance = resistance;
         }

         strength -= resistance;
         strength -= 0.22500001F;
         stepX += normX;
         stepY += normY;
      }
   }

   @Unique
   private float traverseBlock(float strength, int blockX, int blockY, int blockZ, LongOpenHashSet touched) {
      class_2338 pos = this.cachedPos.method_10103(blockX, blockY, blockZ);
      int chunkX = Pos.ChunkCoord.fromBlockCoord(blockX);
      int chunkZ = Pos.ChunkCoord.fromBlockCoord(blockZ);
      if (this.prevChunkX != chunkX || this.prevChunkZ != chunkZ) {
         this.prevChunk = this.field_9187.method_8497(chunkX, chunkZ);
         this.prevChunkX = chunkX;
         this.prevChunkZ = chunkZ;
      }

      class_2680 blockState;
      float totalResistance;
      Optional<Float> blastResistance;
      label35: {
         class_2791 chunk = this.prevChunk;
         blockState = class_2246.field_10124.method_9564();
         totalResistance = 0.0F;
         if (chunk != null) {
            class_2826 section = chunk.method_12006()[Pos.SectionYIndex.fromBlockCoord(chunk, blockY)];
            if (section != null && !section.method_38292()) {
               blockState = section.method_12254(blockX & 15, blockY & 15, blockZ & 15);
               if (blockState.method_26204() != class_2246.field_10124) {
                  class_3610 fluidState = blockState.method_26227();
                  blastResistance = this.field_25400.method_29555((class_1927)this, this.field_9187, pos, blockState, fluidState);
                  break label35;
               }
            }
         }

         blastResistance = this.field_25400
            .method_29555((class_1927)this, this.field_9187, pos, class_2246.field_10124.method_9564(), class_3612.field_15906.method_15785());
      }

      if (blastResistance.isPresent()) {
         totalResistance = (blastResistance.get() + 0.3F) * 0.3F;
      }

      float reducedStrength = strength - totalResistance;
      if (reducedStrength > 0.0F
         && (this.explodeAirBlocks || !blockState.method_26215())
         && this.field_25400.method_29554((class_1927)this, this.field_9187, pos, blockState, reducedStrength)) {
         touched.add(pos.method_10063());
      }

      return totalResistance;
   }
}
