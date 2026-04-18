package vectorwing.farmersdelight.common.item;

import java.util.Optional;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1282;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1657;
import net.minecraft.class_174;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3419;
import net.minecraft.class_3920;
import net.minecraft.class_3956;
import net.minecraft.class_4538;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_8786;
import net.minecraft.class_9274;
import net.minecraft.class_9285;
import net.minecraft.class_9696;
import net.minecraft.class_1322.class_1323;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_6880.class_6883;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.SkilletBlock;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class SkilletItem extends class_1747 {
   public static final float FLIP_TIME = 12.0F;
   public static final class_1834 SKILLET_TIER = class_1834.field_8923;
   protected static final class_2960 FD_ATTACK_KNOCKBACK_UUID = class_2960.method_60655("farmersdelight", "base_attack_knockback");

   public SkilletItem(class_2248 block, class_1793 properties) {
      super(block, properties.method_7895(SKILLET_TIER.method_8025()));
      float attackDamage = 5.0F + SKILLET_TIER.method_8028();
   }

   public static class_9285 createAttributes(class_1832 tier, float attackDamage, float attackSpeed) {
      return class_9285.method_57480()
         .method_57487(class_5134.field_23721, new class_1322(field_8006, attackDamage + tier.method_8028(), class_1323.field_6328), class_9274.field_49217)
         .method_57487(class_5134.field_23723, new class_1322(field_8001, attackSpeed, class_1323.field_6328), class_9274.field_49217)
         .method_57487(class_5134.field_23722, new class_1322(FD_ATTACK_KNOCKBACK_UUID, 1.0, class_1323.field_6328), class_9274.field_49217)
         .method_57486();
   }

   public boolean method_7885(class_2680 state, class_1937 level, class_2338 pos, class_1657 player) {
      return !player.method_7337();
   }

   public boolean method_7873(class_1799 stack, class_1309 target, class_1309 attacker) {
      return true;
   }

   public void method_59978(class_1799 stack, class_1309 target, class_1309 attacker) {
      stack.method_7970(1, attacker, class_1304.field_6173);
   }

   private static boolean isPlayerNearHeatSource(class_1657 player, class_4538 level) {
      if (player.method_5809()) {
         return true;
      } else {
         class_2338 pos = player.method_24515();

         for (class_2338 nearbyPos : class_2338.method_10097(pos.method_10069(-1, -1, -1), pos.method_10069(1, 1, 1))) {
            if (level.method_8320(nearbyPos).method_26164(ModTags.HEAT_SOURCES)) {
               return true;
            }
         }

         return false;
      }
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      Optional<class_6883<class_1887>> fireAspect = entity.method_37908()
         .method_30349()
         .method_46762(class_7924.field_41265)
         .method_46746(class_1893.field_9124);
      if (fireAspect.isEmpty()) {
         return 0;
      } else {
         int fireAspectLevel = fireAspect.<Integer>map(enchantmentReference -> stack.method_58657().method_57536(enchantmentReference)).orElse(0);
         int cookingTime = (Integer)stack.method_57825(ModDataComponents.COOKING_TIME_LENGTH.get(), 0);
         return SkilletBlock.getSkilletCookingTime(cookingTime, fireAspectLevel);
      }
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 skilletStack = player.method_5998(hand);
      if (isPlayerNearHeatSource(player, level)) {
         class_1268 otherHand = hand == class_1268.field_5808 ? class_1268.field_5810 : class_1268.field_5808;
         class_1799 cookingStack = player.method_5998(otherHand);
         if (!((ItemStackWrapper)skilletStack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY)).getStack().method_7960()) {
            player.method_6019(hand);
            return class_1271.method_22430(skilletStack);
         }

         Optional<class_8786<class_3920>> recipe = getCookingRecipe(cookingStack, level);
         if (recipe.isPresent()) {
            if (player.method_5869()) {
               player.method_7353(TextUtils.getTranslation("item.skillet.underwater"), true);
               return class_1271.method_22430(skilletStack);
            }

            class_1799 cookingStackCopy = cookingStack.method_7972();
            class_1799 cookingStackUnit = cookingStackCopy.method_7971(1);
            skilletStack.method_57379(ModDataComponents.SKILLET_INGREDIENT.get(), new ItemStackWrapper(cookingStackUnit));
            skilletStack.method_57379(ModDataComponents.COOKING_TIME_LENGTH.get(), ((class_3920)recipe.get().comp_1933()).method_8167());
            player.method_6019(hand);
            player.method_6122(otherHand, cookingStackCopy);
            return class_1271.method_22428(skilletStack);
         }

         player.method_7353(TextUtils.getTranslation("item.skillet.how_to_cook"), true);
      }

      return class_1271.method_22430(skilletStack);
   }

   public void method_7852(class_1937 level, class_1309 entity, class_1799 stack, int count) {
      if (entity instanceof class_1657 player) {
         class_243 pos = player.method_19538();
         double x = pos.method_10216() + 0.5;
         double y = pos.method_10214();
         double z = pos.method_10215() + 0.5;
         if (level.field_9229.method_43048(50) == 0) {
            level.method_8486(x, y, z, ModSounds.BLOCK_SKILLET_SIZZLE.get(), class_3419.field_15245, 0.4F, level.field_9229.method_43057() * 0.2F + 0.9F, false);
         }
      }
   }

   public void method_7840(class_1799 stack, class_1937 level, class_1309 entity, int timeLeft) {
      if (entity instanceof class_1657 player) {
         ItemStackWrapper storedStack = (ItemStackWrapper)stack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);
         if (!storedStack.getStack().method_7960()) {
            class_1799 cookingStack = storedStack.getStack();
            player.method_31548().method_7398(cookingStack);
            stack.method_57381(ModDataComponents.SKILLET_INGREDIENT.get());
            stack.method_57381(ModDataComponents.COOKING_TIME_LENGTH.get());
         }
      }
   }

   public class_1799 method_7861(class_1799 stack, class_1937 level, class_1309 entity) {
      if (entity instanceof class_1657 player) {
         ItemStackWrapper storedStack = (ItemStackWrapper)stack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);
         if (!storedStack.getStack().method_7960()) {
            class_1799 cookingStack = storedStack.getStack();
            Optional<class_8786<class_3920>> cookingRecipe = getCookingRecipe(cookingStack, level);
            cookingRecipe.ifPresent(recipe -> {
               class_1799 resultStack = ((class_3920)recipe.comp_1933()).method_59982(new class_9696(cookingStack), level.method_30349());
               if (!player.method_31548().method_7394(resultStack)) {
                  player.method_7328(resultStack, false);
               }

               if (player instanceof class_3222) {
                  class_174.field_1198.method_8821((class_3222)player, stack);
               }
            });
            stack.method_57381(ModDataComponents.SKILLET_INGREDIENT.get());
            stack.method_57381(ModDataComponents.COOKING_TIME_LENGTH.get());
         }
      }

      return stack;
   }

   public static Optional<class_8786<class_3920>> getCookingRecipe(class_1799 stack, class_1937 level) {
      return stack.method_7960() ? Optional.empty() : level.method_8433().method_8132(class_3956.field_17549, new class_9696(stack), level);
   }

   protected boolean method_7710(class_2338 pos, class_1937 level, @Nullable class_1657 player, class_1799 stack, class_2680 state) {
      super.method_7710(pos, level, player, stack, state);
      if (level.method_8321(pos) instanceof SkilletBlockEntity skillet) {
         skillet.setSkilletItem(stack, level.method_30349());
         return true;
      } else {
         return false;
      }
   }

   public boolean method_7878(class_1799 toRepair, class_1799 repair) {
      return SKILLET_TIER.method_8023().method_8093(repair) || super.method_7878(toRepair, repair);
   }

   public boolean method_7879(class_1799 stack, class_1937 level, class_2680 state, class_2338 pos, class_1309 entity) {
      if (!level.field_9236 && state.method_26214(level, pos) != 0.0F) {
         stack.method_7970(1, entity, class_1304.field_6173);
      }

      return true;
   }

   public class_1269 method_7712(class_1750 context) {
      class_1657 player = context.method_8036();
      return player != null && player.method_5715() ? super.method_7712(context) : class_1269.field_5811;
   }

   public boolean canBeEnchantedWith(class_1799 stack, class_6880<class_1887> enchantment, EnchantingContext context) {
      return enchantment.method_40225(class_1893.field_9115) ? false : super.canBeEnchantedWith(stack, enchantment, context);
   }

   public int method_7837() {
      return SKILLET_TIER.method_8026();
   }

   public static class SkilletEvents {
      public static float attackPower = 0.0F;

      public static void playSkilletAttackSound(class_1309 entity, class_1282 source) {
         if (source.method_5526() instanceof class_1309 livingEntity) {
            if (livingEntity.method_5998(class_1268.field_5808).method_31574(ModItems.SKILLET.get())) {
               float pitch = 0.9F + livingEntity.method_59922().method_43057() * 0.2F;
               if (livingEntity instanceof class_1657 player) {
                  if (attackPower > 0.8F) {
                     player.method_5783(ModSounds.ITEM_SKILLET_ATTACK_STRONG.get(), 1.0F, pitch);
                  } else {
                     player.method_5783(ModSounds.ITEM_SKILLET_ATTACK_WEAK.get(), 0.8F, 0.9F);
                  }
               } else {
                  livingEntity.method_5783(ModSounds.ITEM_SKILLET_ATTACK_STRONG.get(), 1.0F, pitch);
               }

               attackPower = 0.0F;
            }
         }
      }
   }
}
