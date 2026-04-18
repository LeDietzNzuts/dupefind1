package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_2248.class)
public interface AccessorMixinBlock {
   @Invoker("method_33614")
   void lootr$spawnDestroyParticles(class_1937 var1, class_1657 var2, class_2338 var3, class_2680 var4);
}
