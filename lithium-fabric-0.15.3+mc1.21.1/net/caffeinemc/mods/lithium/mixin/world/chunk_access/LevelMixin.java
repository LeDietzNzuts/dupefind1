package net.caffeinemc.mods.lithium.mixin.world.chunk_access;

import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_4076;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_1936 {
   @Overwrite
   public class_2818 method_8500(class_2338 pos) {
      return (class_2818)this.method_22350(pos);
   }

   public class_2791 method_22350(class_2338 pos) {
      return this.method_8402(class_4076.method_18675(pos.method_10263()), class_4076.method_18675(pos.method_10260()), class_2806.field_12803, true);
   }

   @Overwrite
   public class_2818 method_8497(int chunkX, int chunkZ) {
      return (class_2818)this.method_8402(chunkX, chunkZ, class_2806.field_12803, true);
   }

   public class_2791 method_22342(int chunkX, int chunkZ, class_2806 status) {
      return this.method_8402(chunkX, chunkZ, status, true);
   }

   public class_1922 method_22338(int chunkX, int chunkZ) {
      return this.method_8402(chunkX, chunkZ, class_2806.field_12803, false);
   }
}
