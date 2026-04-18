package net.caffeinemc.mods.sodium.mixin.features.render.frapi;

import java.util.function.Supplier;
import net.caffeinemc.mods.sodium.client.render.frapi.render.AbstractBlockRenderContext;
import net.caffeinemc.mods.sodium.client.render.frapi.render.ItemRenderContext;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1087.class)
public interface BakedModelMixin extends FabricBakedModel {
   default void emitBlockQuads(class_1920 blockView, class_2680 state, class_2338 pos, Supplier<class_5819> randomSupplier, RenderContext context) {
      ((AbstractBlockRenderContext)context).bufferDefaultModel((class_1087)this, state);
   }

   default void emitItemQuads(class_1799 stack, Supplier<class_5819> randomSupplier, RenderContext context) {
      ((ItemRenderContext)context).bufferDefaultModel((class_1087)this, null);
   }
}
