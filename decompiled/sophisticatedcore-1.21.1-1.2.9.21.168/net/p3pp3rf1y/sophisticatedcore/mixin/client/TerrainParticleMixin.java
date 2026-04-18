package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_727;
import net.minecraft.class_773;
import net.p3pp3rf1y.sophisticatedcore.client.render.CustomParticleIcon;
import net.p3pp3rf1y.sophisticatedcore.extensions.client.particle.SophisticatedTerrainParticle;
import net.p3pp3rf1y.sophisticatedcore.util.model.ModelData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_727.class)
public abstract class TerrainParticleMixin extends class_4003 implements SophisticatedTerrainParticle {
   protected TerrainParticleMixin(class_638 clientLevel, double d, double e, double f) {
      super(clientLevel, d, e, f);
   }

   @Override
   public class_703 sophisticatedCore_updateSprite(class_2680 state, @Nullable class_2338 pos) {
      if (pos != null) {
         class_773 shaper = class_310.method_1551().method_1541().method_3351();
         if (shaper.method_3335(state) instanceof CustomParticleIcon model) {
            ModelData data = model.getModelData(class_310.method_1551().field_1687, pos, state, ModelData.EMPTY);
            this.method_18141(model.getParticleIcon(data));
         }
      }

      return this;
   }
}
