package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.class_2400;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModParticleTypes {
   public static final Supplier<class_2400> STAR = RegUtils.regParticle("star", () -> FabricParticleTypes.simple(true));
   public static final Supplier<class_2400> STEAM = RegUtils.regParticle("steam", () -> FabricParticleTypes.simple(true));

   public static void touch() {
   }
}
