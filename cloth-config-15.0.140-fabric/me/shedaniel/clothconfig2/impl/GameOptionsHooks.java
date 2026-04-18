package me.shedaniel.clothconfig2.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_304;

@Environment(EnvType.CLIENT)
public interface GameOptionsHooks {
   void cloth_setKeysAll(class_304[] var1);
}
