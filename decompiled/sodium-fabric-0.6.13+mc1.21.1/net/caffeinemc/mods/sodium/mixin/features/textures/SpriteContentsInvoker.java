package net.caffeinemc.mods.sodium.mixin.features.textures;

import net.minecraft.class_1011;
import net.minecraft.class_7764;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_7764.class)
public interface SpriteContentsInvoker {
   @Invoker
   void invokeMethod_45811(int var1, int var2, int var3, int var4, class_1011[] var5);
}
