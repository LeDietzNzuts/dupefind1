package net.raphimc.immediatelyfast.injection.mixins.map_atlas_generation;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.class_22;
import net.minecraft.class_330;
import net.minecraft.class_9209;
import net.minecraft.class_330.class_331;
import net.raphimc.immediatelyfast.feature.map_atlas_generation.MapAtlasTexture;
import net.raphimc.immediatelyfast.injection.interfaces.IMapRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_330.class)
public abstract class MixinMapRenderer implements IMapRenderer {
   @Unique
   private final Int2ObjectMap<MapAtlasTexture> immediatelyFast$mapAtlasTextures = new Int2ObjectOpenHashMap();
   @Unique
   private final Int2IntMap immediatelyFast$mapIdToAtlasMapping = new Int2IntOpenHashMap();

   @Inject(method = "method_1771()V", at = @At("RETURN"))
   private void clearMapAtlasTextures(CallbackInfo ci) {
      ObjectIterator var2 = this.immediatelyFast$mapAtlasTextures.values().iterator();

      while (var2.hasNext()) {
         MapAtlasTexture texture = (MapAtlasTexture)var2.next();
         texture.close();
      }

      this.immediatelyFast$mapAtlasTextures.clear();
      this.immediatelyFast$mapIdToAtlasMapping.clear();
   }

   @Inject(method = "method_32601(Lnet/minecraft/class_9209;Lnet/minecraft/class_22;)Lnet/minecraft/class_330$class_331;", at = @At("HEAD"))
   private void createMapAtlasTexture(class_9209 mapId, class_22 state, CallbackInfoReturnable<class_331> cir) {
      this.immediatelyFast$mapIdToAtlasMapping.computeIfAbsent(mapId.comp_2315(), k -> {
         ObjectIterator atlasTexture = this.immediatelyFast$mapAtlasTextures.values().iterator();

         while (atlasTexture.hasNext()) {
            MapAtlasTexture atlasTexturex = (MapAtlasTexture)atlasTexture.next();
            int location = atlasTexturex.getNextMapLocation();
            if (location != -1) {
               return location;
            }
         }

         MapAtlasTexture atlasTexturex = new MapAtlasTexture(this.immediatelyFast$mapAtlasTextures.size());
         this.immediatelyFast$mapAtlasTextures.put(atlasTexturex.getId(), atlasTexturex);
         return atlasTexturex.getNextMapLocation();
      });
   }

   @Override
   public MapAtlasTexture immediatelyFast$getMapAtlasTexture(int id) {
      return (MapAtlasTexture)this.immediatelyFast$mapAtlasTextures.get(id);
   }

   @Override
   public int immediatelyFast$getAtlasMapping(int mapId) {
      return this.immediatelyFast$mapIdToAtlasMapping.getOrDefault(mapId, -1);
   }
}
