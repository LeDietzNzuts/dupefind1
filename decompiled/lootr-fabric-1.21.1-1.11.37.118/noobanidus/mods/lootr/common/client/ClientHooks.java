package noobanidus.mods.lootr.common.client;

import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_4076;
import net.minecraft.class_5819;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinBlock;
import org.jetbrains.annotations.Nullable;

public class ClientHooks {
   @Nullable
   public static class_1657 getPlayer() {
      class_310 mc = class_310.method_1551();
      return mc == null ? null : mc.field_1724;
   }

   public static void clearCache(class_2338 position) {
      class_4076 pos = class_4076.method_18682(position);
      class_310.method_1551().method_20493(() -> class_310.method_1551().field_1769.method_8571(pos.method_18674(), pos.method_18683(), pos.method_18687()));
   }

   public static void refreshSection() {
      class_1657 player = getPlayer();
      if (player != null) {
         clearCache(player.method_24515());
      }
   }

   public static void performBreakEffect(int entityId, class_2338 pos) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (mc.field_1687.method_8469(entityId) instanceof class_1657 player) {
            double var8 = 1.2;
            if (player == mc.field_1724) {
               class_2680 state = mc.field_1687.method_8320(pos);
               ((AccessorMixinBlock)state.method_26204()).lootr$spawnDestroyParticles(mc.field_1687, player, pos, state);
               mc.field_1687.method_8396(null, pos, class_3417.field_42577, class_3419.field_15245, 1.0F, 1.0F);
            } else {
               if (mc.field_1687.method_8321(pos) instanceof ILootrBlockEntity ibe && ibe.hasClientOpened(mc.field_1724)) {
                  var8 = 0.5;
               }

               mc.field_1687.method_8396(null, pos, class_3417.field_46650, class_3419.field_15245, 1.0F, 1.0F);

               for (int i = 0; i < 7; i++) {
                  mc.field_1687
                     .method_8466(class_2398.field_46763, false, pos.method_10263() + 0.5, pos.method_10264() + var8, pos.method_10260() + 0.5, 0.0, 0.0, 0.0);
               }
            }
         }
      }
   }

   private static double bounded(class_5819 random, double[] bounds) {
      double min = bounds[0];
      double max = bounds[1];
      return min + random.method_43058() * (max - min);
   }

   public static void performUnopenedParticles(ILootrInfoProvider provider) {
      class_1657 player = getPlayer();
      if (player != null) {
         class_1937 level = class_310.method_1551().field_1687;
         if (level != null && !provider.hasClientOpened(player)) {
            class_5819 random = class_310.method_1551().field_1687.method_8409();
            if (random.method_43048(3) == 0) {
               double xOff = bounded(random, provider.getParticleXBounds());
               double zOff = bounded(random, provider.getParticleZBounds());
               class_243 pos = provider.getParticleCenter();
               level.method_8406(
                  LootrRegistry.getUnopenedParticleType(),
                  pos.field_1352 + xOff,
                  pos.field_1351 + provider.getParticleYOffset() + random.method_43058() * 0.02,
                  pos.field_1350 + zOff,
                  0.0,
                  random.method_43058() * 0.02,
                  0.0
               );
            }
         }
      }
   }
}
