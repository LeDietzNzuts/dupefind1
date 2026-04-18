package net.caffeinemc.mods.sodium.client.model.color;

import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_2338;
import net.minecraft.class_2338.class_2339;

public interface ColorProvider<T> {
   void getColors(LevelSlice var1, class_2338 var2, class_2339 var3, T var4, ModelQuadView var5, int[] var6);
}
