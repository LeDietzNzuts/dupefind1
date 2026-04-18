package net.caffeinemc.mods.sodium.api.blockentity;

import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import org.jetbrains.annotations.ApiStatus.AvailableSince;
import org.jetbrains.annotations.ApiStatus.Experimental;

@FunctionalInterface
@Experimental
@AvailableSince("0.6.0")
public interface BlockEntityRenderPredicate<T extends class_2586> {
   boolean shouldRender(class_1922 var1, class_2338 var2, T var3);
}
