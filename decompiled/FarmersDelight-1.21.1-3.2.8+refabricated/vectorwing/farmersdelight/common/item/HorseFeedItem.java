package vectorwing.farmersdelight.common.item;

import com.google.common.collect.Lists;
import java.util.List;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1291;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1498;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_2561;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3966;
import net.minecraft.class_5250;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class HorseFeedItem extends class_1792 {
   public static final List<class_1293> EFFECTS = Lists.newArrayList(
      new class_1293[]{new class_1293(class_1294.field_5904, 6000, 1), new class_1293(class_1294.field_5913, 6000, 0)}
   );

   public HorseFeedItem(class_1793 properties) {
      super(properties);
   }

   public static void init() {
      UseEntityCallback.EVENT.register(HorseFeedItem.HorseFeedEvent::onHorseFeedApplied);
   }

   public void method_7851(class_1799 stack, class_9635 context, List<class_2561> tooltip, class_1836 isAdvanced) {
      if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
         class_5250 textWhenFeeding = TextUtils.getTranslation("tooltip.horse_feed.when_feeding");
         tooltip.add(textWhenFeeding.method_27692(class_124.field_1080));

         for (class_1293 effectInstance : EFFECTS) {
            class_5250 effectDescription = class_2561.method_43470(" ");
            class_5250 effectName = class_2561.method_43471(effectInstance.method_5586());
            effectDescription.method_10852(effectName);
            class_1291 effect = (class_1291)effectInstance.method_5579().comp_349();
            if (effectInstance.method_5578() > 0) {
               effectDescription.method_27693(" ").method_10852(class_2561.method_43471("potion.potency." + effectInstance.method_5578()));
            }

            if (effectInstance.method_5584() > 20) {
               effectDescription.method_27693(" (").method_10852(class_1292.method_5577(effectInstance, 1.0F, context.method_59531())).method_27693(")");
            }

            tooltip.add(effectDescription.method_27692(effect.method_18792().method_18793()));
         }
      }
   }

   public class_1269 method_7847(class_1799 stack, class_1657 playerIn, class_1309 target, class_1268 hand) {
      return target instanceof class_1498 horse && horse.method_5805() && horse.method_6727() ? class_1269.field_5812 : class_1269.field_5811;
   }

   public static class HorseFeedEvent {
      public static class_1269 onHorseFeedApplied(class_1657 player, class_1937 level, class_1268 hand, class_1297 target, @Nullable class_3966 entityHitResult) {
         if (player.method_7325()) {
            return class_1269.field_5811;
         } else {
            class_1799 heldStack = player.method_5998(hand);
            if (target instanceof class_1309 entity && target.method_5864().method_20210(ModTags.HORSE_FEED_USERS)) {
               boolean isTameable = entity instanceof class_1496;
               if (entity.method_5805() && (!isTameable || ((class_1496)entity).method_6727()) && heldStack.method_7909().equals(ModItems.HORSE_FEED.get())) {
                  entity.method_6033(entity.method_6063());

                  for (class_1293 effect : HorseFeedItem.EFFECTS) {
                     entity.method_6092(new class_1293(effect));
                  }

                  entity.method_37908().method_8396(null, target.method_24515(), class_3417.field_15099, class_3419.field_15248, 0.8F, 0.8F);

                  for (int i = 0; i < 5; i++) {
                     double d0 = MathUtils.RAND.nextGaussian() * 0.02;
                     double d1 = MathUtils.RAND.nextGaussian() * 0.02;
                     double d2 = MathUtils.RAND.nextGaussian() * 0.02;
                     entity.method_37908()
                        .method_8406(
                           (class_2394)ModParticleTypes.STAR.get(), entity.method_23322(1.0), entity.method_23319() + 0.5, entity.method_23325(1.0), d0, d1, d2
                        );
                  }

                  if (!player.method_7337()) {
                     heldStack.method_7934(1);
                  }

                  return class_1269.field_5812;
               }
            }

            return class_1269.field_5811;
         }
      }
   }
}
