package cn.enaium.onekeyminer.callback.impl

import cn.enaium.onekeyminer.Config
import cn.enaium.onekeyminer.callback.FinishMiningCallback
import cn.enaium.onekeyminer.util.BlockKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import net.minecraft.class_1269
import net.minecraft.class_1657
import net.minecraft.class_1743
import net.minecraft.class_1766
import net.minecraft.class_1792
import net.minecraft.class_1794
import net.minecraft.class_1799
import net.minecraft.class_1810
import net.minecraft.class_1820
import net.minecraft.class_1821
import net.minecraft.class_1937
import net.minecraft.class_2338
import net.minecraft.class_3218
import net.minecraft.class_3222

@SourceDebugExtension(["SMAP\nFinishMiningCallbackImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FinishMiningCallbackImpl.kt\ncn/enaium/onekeyminer/callback/impl/FinishMiningCallbackImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,69:1\n1863#2,2:70\n*S KotlinDebug\n*F\n+ 1 FinishMiningCallbackImpl.kt\ncn/enaium/onekeyminer/callback/impl/FinishMiningCallbackImpl\n*L\n56#1:70,2\n*E\n"])
public abstract class FinishMiningCallbackImpl : FinishMiningCallback {
   public override fun interact(world: class_3218, player: class_3222, pos: class_2338, tryBreak: (class_2338) -> Unit): class_1269 {
      val stack: class_1799 = player.method_31548().method_5438(player.method_31548().field_7545);
      if (stack != null
         && stack.method_7909().method_7885(world.method_8320(pos), world as class_1937, pos, player as class_1657)
         && (stack.method_7909() is class_1766 || stack.method_7909() is class_1820)
         && this.condition(player)) {
         val config: Config.Model = Config.INSTANCE.getModel();
         val list: java.util.List = new ArrayList();
         val name: class_1792 = stack.method_7909();
         if (name is class_1743) {
            list.addAll(config.getAxe());
         } else if (name is class_1794) {
            list.addAll(config.getHoe());
         } else if (name is class_1810) {
            list.addAll(config.getPickaxe());
         } else if (name is class_1821) {
            list.addAll(config.getShovel());
         } else if (name is class_1820) {
            list.addAll(config.getShears());
         } else if (name is class_1766) {
            list.addAll(config.getAny());
         }

         if (list.contains(BlockKt.getName(world as class_1937, pos))) {
            val `$this$forEach$iv`: java.lang.Iterable;
            for (Object element$iv : $this$forEach$iv) {
               val it: class_2338 = `element$iv` as class_2338;
               if (!player.method_31548().method_7391().method_7960()) {
                  tryBreak.invoke(it);
               }
            }
         }
      }

      return class_1269.field_5812;
   }

   public abstract fun condition(player: class_3222): Boolean {
   }
}
