package net.caffeinemc.mods.lithium.mixin.util.block_entity_retrieval;

import net.caffeinemc.mods.lithium.common.world.blockentity.BlockEntityGetter;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_4076;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1937.class)
public abstract class LevelMixin implements BlockEntityGetter, class_1936 {
   @Shadow
   @Final
   public boolean field_9236;
   @Shadow
   @Final
   private Thread field_17086;

   @Shadow
   public abstract class_2818 method_8497(int var1, int var2);

   @Shadow
   @Nullable
   public abstract class_2791 method_8402(int var1, int var2, class_2806 var3, boolean var4);

   @Override
   public class_2586 lithium$getLoadedExistingBlockEntity(class_2338 pos) {
      if (!this.method_31606(pos) && (this.field_9236 || Thread.currentThread() == this.field_17086)) {
         class_2791 chunk = this.method_8402(
            class_4076.method_18675(pos.method_10263()), class_4076.method_18675(pos.method_10260()), class_2806.field_12803, false
         );
         if (chunk != null) {
            return chunk.method_8321(pos);
         }
      }

      return null;
   }
}
