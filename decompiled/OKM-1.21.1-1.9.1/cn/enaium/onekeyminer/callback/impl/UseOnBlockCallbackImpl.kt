package cn.enaium.onekeyminer.callback.impl

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.callback.UseOnBlockCallback
import cn.enaium.onekeyminer.util.BlockKt
import net.minecraft.class_1268
import net.minecraft.class_1657
import net.minecraft.class_1766
import net.minecraft.class_1799
import net.minecraft.class_1838
import net.minecraft.class_1937
import net.minecraft.class_2338
import net.minecraft.class_2350
import net.minecraft.class_3222
import net.minecraft.class_3965

public abstract class UseOnBlockCallbackImpl : UseOnBlockCallback {
   public override fun interact(player: class_3222, world: class_1937, stack: class_1799, hand: class_1268, hitResult: class_3965) {
      val canMine: Boolean = stack.method_7909()
         .method_7885(world.method_8320(hitResult.method_17777()), world, hitResult.method_17777(), player as class_1657);
      val config: Config.Model = Config.INSTANCE.getModel();
      if (canMine && config.getInteract() && stack.method_7909() is class_1766 && this.condition(player)) {
         val var10001: class_2338 = hitResult.method_17777();

         for (class_2338 block : BlockKt.findBlocks(world, var10001, config.getLimit())) {
            stack.method_7909()
               .method_7884(new class_1838(player as class_1657, hand, new class_3965(block.method_46558(), class_2350.field_11036, block, false)));
         }
      }
   }

   public abstract fun condition(player: class_3222): Boolean {
   }
}
