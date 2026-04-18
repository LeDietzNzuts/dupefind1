package net.p3pp3rf1y.sophisticatedbackpacks.compat.litematica.mixin;

import com.google.common.collect.Lists;
import dev.emi.trinkets.api.TrinketsApi;
import fi.dy.masa.litematica.materials.MaterialListUtils;
import java.util.List;
import net.minecraft.class_1258;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MaterialListUtils.class)
public class MaterialListUtilsMixin {
   @ModifyVariable(method = "getInventoryItemCounts", at = @At("HEAD"), argsOnly = true)
   private static class_1263 sophisticatedBackpacks$wrapContainer(class_1263 inv) {
      if (inv instanceof class_1661 playerInv) {
         class_1263 trinketInventory = sophisticatedBackpacks$getTrinketInventories(playerInv.field_7546);
         return new class_1258(inv, trinketInventory);
      } else {
         return inv;
      }
   }

   @Unique
   private static class_1263 sophisticatedBackpacks$getTrinketInventories(class_1657 player) {
      List<class_1799> stacks = Lists.newArrayList();
      TrinketsApi.getTrinketComponent(player)
         .ifPresent(trinket -> trinket.getEquipped(stack -> stack.method_7909() instanceof BackpackItem).forEach(t -> stacks.add((class_1799)t.method_15441())));
      return new class_1277(stacks.toArray(new class_1799[0]));
   }
}
