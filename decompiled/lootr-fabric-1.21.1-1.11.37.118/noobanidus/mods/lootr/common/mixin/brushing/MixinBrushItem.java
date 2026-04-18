package noobanidus.mods.lootr.common.mixin.brushing;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3965;
import net.minecraft.class_8162;
import net.minecraft.class_8174;
import noobanidus.mods.lootr.common.api.IBrushable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_8162.class)
public class MixinBrushItem {
   @ModifyExpressionValue(method = "method_7852", at = @At("MIXINEXTRAS:EXPRESSION"))
   @Definition(id = "BrushableBlockEntity", type = class_8174.class)
   @Expression("? instanceof BrushableBlockEntity")
   private boolean lootr$AllowBrushingOtherBlockEntities(
      boolean original,
      @Local(argsOnly = true) class_1937 level,
      @Local(argsOnly = true) class_1309 livingEntity,
      @Local(argsOnly = true) class_1799 itemStack,
      @Local class_3965 blockHitResult
   ) {
      class_2338 pos = blockHitResult.method_17777();
      if (level.method_8321(pos) instanceof IBrushable brushable && livingEntity instanceof class_1657 player) {
         boolean bl2 = brushable.IBrushable$brush(level.method_8510(), player, blockHitResult.method_17780());
         if (bl2) {
            class_1304 equipmentSlot = itemStack.equals(player.method_6118(class_1304.field_6171)) ? class_1304.field_6171 : class_1304.field_6173;
            itemStack.method_7970(1, player, equipmentSlot);
         }

         return false;
      } else {
         return original;
      }
   }
}
