package net.caffeinemc.mods.sodium.mixin.core.render.world;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.util.SortedSet;
import java.util.function.Consumer;
import net.caffeinemc.mods.sodium.client.gl.device.RenderDevice;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import net.caffeinemc.mods.sodium.client.render.viewport.Viewport;
import net.caffeinemc.mods.sodium.client.render.viewport.ViewportProvider;
import net.caffeinemc.mods.sodium.client.services.PlatformLevelRenderHooks;
import net.caffeinemc.mods.sodium.client.util.FlawlessFrames;
import net.caffeinemc.mods.sodium.client.world.LevelRendererExtension;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_3191;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4599;
import net.minecraft.class_4604;
import net.minecraft.class_638;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_765;
import net.minecraft.class_824;
import net.minecraft.class_898;
import net.minecraft.class_9779;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_761.class)
public abstract class LevelRendererMixin implements LevelRendererExtension {
   @Shadow
   @Final
   private class_4599 field_20951;
   @Shadow
   @Final
   private Long2ObjectMap<SortedSet<class_3191>> field_20950;
   @Shadow
   @Nullable
   private class_638 field_4085;
   @Shadow
   private int field_4073;
   @Shadow
   @Final
   private class_310 field_4088;
   @Shadow
   private class_4604 field_27740;
   @Unique
   private SodiumWorldRenderer renderer;

   @Override
   public SodiumWorldRenderer sodium$getWorldRenderer() {
      return this.renderer;
   }

   @Redirect(method = "method_3279()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_315;method_38521()I", ordinal = 1))
   private int nullifyBuiltChunkStorage(class_315 options) {
      return 0;
   }

   @Inject(method = "<init>(Lnet/minecraft/class_310;Lnet/minecraft/class_898;Lnet/minecraft/class_824;Lnet/minecraft/class_4599;)V", at = @At("RETURN"))
   private void init(
      class_310 client, class_898 entityRenderDispatcher, class_824 blockEntityRenderDispatcher, class_4599 bufferBuilderStorage, CallbackInfo ci
   ) {
      this.renderer = new SodiumWorldRenderer(client);
   }

   @Inject(method = "method_3244(Lnet/minecraft/class_638;)V", at = @At("RETURN"))
   private void onWorldChanged(class_638 level, CallbackInfo ci) {
      RenderDevice.enterManagedCode();

      try {
         this.renderer.setLevel(level);
      } finally {
         RenderDevice.exitManagedCode();
      }
   }

   @Overwrite
   public int method_3246() {
      return this.renderer.getVisibleChunkCount();
   }

   @Overwrite
   public boolean method_3281() {
      return this.renderer.isTerrainRenderComplete();
   }

   @Inject(method = "method_3292()V", at = @At("RETURN"))
   private void onTerrainUpdateScheduled(CallbackInfo ci) {
      this.renderer.scheduleTerrainUpdate();
   }

   @Overwrite
   private void method_3251(class_1921 renderLayer, double x, double y, double z, Matrix4f modelMatrix, Matrix4f projectionMatrix) {
      RenderDevice.enterManagedCode();

      try {
         this.renderer.drawChunkLayer(renderLayer, new ChunkRenderMatrices(projectionMatrix, modelMatrix), x, y, z);
      } finally {
         RenderDevice.exitManagedCode();
      }

      PlatformLevelRenderHooks.getInstance()
         .runChunkLayerEvents(
            renderLayer, (class_761)this, modelMatrix, projectionMatrix, this.field_4073, this.field_4088.field_1773.method_19418(), this.field_27740
         );
   }

   @Overwrite
   private void method_3273(class_4184 camera, class_4604 frustum, boolean hasForcedFrustum, boolean spectator) {
      Viewport viewport = ((ViewportProvider)frustum).sodium$createViewport();
      boolean updateChunksImmediately = FlawlessFrames.isActive();
      RenderDevice.enterManagedCode();

      try {
         this.renderer.setupTerrain(camera, viewport, spectator, updateChunksImmediately);
      } finally {
         RenderDevice.exitManagedCode();
      }
   }

   @Overwrite
   public void method_18146(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
      this.renderer.scheduleRebuildForBlockArea(minX, minY, minZ, maxX, maxY, maxZ, false);
   }

   @Overwrite
   public void method_18145(int x, int y, int z) {
      this.renderer.scheduleRebuildForChunks(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1, false);
   }

   @Overwrite
   private void method_16037(class_2338 pos, boolean important) {
      this.renderer
         .scheduleRebuildForBlockArea(
            pos.method_10263() - 1,
            pos.method_10264() - 1,
            pos.method_10260() - 1,
            pos.method_10263() + 1,
            pos.method_10264() + 1,
            pos.method_10260() + 1,
            important
         );
   }

   @Overwrite
   private void method_3295(int x, int y, int z, boolean important) {
      this.renderer.scheduleRebuildForChunk(x, y, z, important);
   }

   @Overwrite
   public boolean method_40050(class_2338 pos) {
      return this.renderer.isSectionReady(pos.method_10263() >> 4, pos.method_10264() >> 4, pos.method_10260() >> 4);
   }

   @Inject(method = "method_3279()V", at = @At("RETURN"))
   private void onReload(CallbackInfo ci) {
      RenderDevice.enterManagedCode();

      try {
         this.renderer.reload();
      } finally {
         RenderDevice.exitManagedCode();
      }
   }

   @Inject(
      method = "method_22710(Lnet/minecraft/class_9779;ZLnet/minecraft/class_4184;Lnet/minecraft/class_757;Lnet/minecraft/class_765;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_761;field_4055:Ljava/util/Set;", shift = Shift.BEFORE, ordinal = 0)
   )
   private void onRenderBlockEntities(
      class_9779 deltaTracker,
      boolean bl,
      class_4184 camera,
      class_757 gameRenderer,
      class_765 lightTexture,
      Matrix4f matrix4f,
      Matrix4f matrix4f2,
      CallbackInfo ci,
      @Local(ordinal = 3) LocalBooleanRef isGlowing
   ) {
      this.renderer.renderBlockEntities(new class_4587(), this.field_20951, this.field_20950, camera, deltaTracker.method_60637(false), isGlowing);
   }

   @Inject(method = "iterateVisibleBlockEntities", at = @At("HEAD"), cancellable = true, require = 0)
   public void replaceBlockEntityIteration(Consumer<class_2586> blockEntityConsumer, CallbackInfo ci) {
      ci.cancel();
      this.renderer.iterateVisibleBlockEntities(blockEntityConsumer);
   }

   @Overwrite
   public String method_3289() {
      return this.renderer.getChunksDebugString();
   }
}
