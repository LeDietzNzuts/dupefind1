package net.caffeinemc.mods.sodium.client.world;

import net.minecraft.class_7522;

public interface PalettedContainerROExtension<T> {
   static <T> PalettedContainerROExtension<T> of(class_7522<T> container) {
      return (PalettedContainerROExtension<T>)container;
   }

   static <T> class_7522<T> clone(class_7522<T> container) {
      return container == null ? null : of(container).sodium$copy();
   }

   void sodium$unpack(T[] var1);

   void sodium$unpack(T[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   class_7522<T> sodium$copy();
}
