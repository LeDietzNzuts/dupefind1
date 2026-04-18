package net.caffeinemc.mods.lithium.mixin.world.inline_height;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2818;
import net.minecraft.class_5539;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin implements class_5539 {
   @Shadow
   @Final
   class_1937 field_12858;

   public int method_31600() {
      return this.field_12858.method_31600();
   }

   public int method_32890() {
      return this.field_12858.method_32890();
   }

   public int method_32891() {
      return this.field_12858.method_32891();
   }

   public int method_31597() {
      return this.field_12858.method_31597();
   }

   public boolean method_31606(class_2338 pos) {
      return this.field_12858.method_31606(pos);
   }

   public boolean method_31601(int y) {
      return this.field_12858.method_31601(y);
   }

   public int method_31602(int y) {
      return this.field_12858.method_31602(y);
   }

   public int method_31603(int coord) {
      return this.field_12858.method_31603(coord);
   }

   public int method_31604(int index) {
      return this.field_12858.method_31604(index);
   }
}
