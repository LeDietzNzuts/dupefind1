package net.caffeinemc.mods.lithium.mixin.chunk.palette;

import net.caffeinemc.mods.lithium.common.world.chunk.LithiumHashPalette;
import net.minecraft.class_2359;
import net.minecraft.class_3532;
import net.minecraft.class_2837.class_6559;
import net.minecraft.class_2841.class_6560;
import net.minecraft.class_2841.class_6563;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_6563.class)
public abstract class PalettedContainer$StrategyMixin {
   @Mutable
   @Shadow
   @Final
   public static class_6563 field_34569;
   @Unique
   private static final class_6560<?>[] BLOCKSTATE_DATA_PROVIDERS;
   @Unique
   private static final class_6560<?>[] BIOME_DATA_PROVIDERS = new class_6560[]{
      new class_6560(class_6563.field_34566, 0),
      new class_6560(class_6563.field_34567, 1),
      new class_6560(class_6563.field_34567, 2),
      new class_6560(PalettedContainer$StrategyMixin.HASH, 3)
   };
   @Unique
   private static final class_6559 HASH = LithiumHashPalette::create;
   @Mutable
   @Shadow
   @Final
   public static class_6563 field_34570;
   @Shadow
   @Final
   static class_6559 field_34571;

   static {
      final class_6559 idListFactory = field_34571;
      class_6560<?> arrayDataProvider4bit = new class_6560(class_6563.field_34567, 4);
      class_6560<?> hashDataProvider4bit = new class_6560(HASH, 4);
      BLOCKSTATE_DATA_PROVIDERS = new class_6560[]{
         new class_6560(class_6563.field_34566, 0),
         arrayDataProvider4bit,
         arrayDataProvider4bit,
         hashDataProvider4bit,
         hashDataProvider4bit,
         new class_6560(HASH, 5),
         new class_6560(HASH, 6),
         new class_6560(HASH, 7),
         new class_6560(HASH, 8)
      };
      field_34569 = new class_6563(4) {
         public <A> class_6560<A> method_38314(class_2359<A> idList, int bits) {
            return (class_6560<A>)(bits >= 0 && bits < PalettedContainer$StrategyMixin.BLOCKSTATE_DATA_PROVIDERS.length
               ? PalettedContainer$StrategyMixin.BLOCKSTATE_DATA_PROVIDERS[bits]
               : new class_6560(idListFactory, class_3532.method_15342(idList.method_10204())));
         }
      };
      field_34570 = new class_6563(2) {
         public <A> class_6560<A> method_38314(class_2359<A> idList, int bits) {
            return (class_6560<A>)(bits >= 0 && bits < PalettedContainer$StrategyMixin.BIOME_DATA_PROVIDERS.length
               ? PalettedContainer$StrategyMixin.BIOME_DATA_PROVIDERS[bits]
               : new class_6560(idListFactory, class_3532.method_15342(idList.method_10204())));
         }
      };
   }
}
