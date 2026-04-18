package vectorwing.farmersdelight.common.block.entity.dispenser;

import java.util.HashMap;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2315;
import net.minecraft.class_2338;
import net.minecraft.class_2342;
import net.minecraft.class_2350;
import net.minecraft.class_2357;
import net.minecraft.class_2680;
import net.minecraft.class_2969;
import net.minecraft.class_6328;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@class_6328
public class CuttingBoardDispenseBehavior extends class_2969 {
   private static final HashMap<class_1792, class_2357> DISPENSE_ITEM_BEHAVIOR_HASH_MAP = new HashMap<>();
   public static final CuttingBoardDispenseBehavior INSTANCE = new CuttingBoardDispenseBehavior();

   public static void registerBehaviour(class_1792 item, CuttingBoardDispenseBehavior behavior) {
      DISPENSE_ITEM_BEHAVIOR_HASH_MAP.put(item, (class_2357)class_2315.field_10919.get(item));
      class_2315.method_10009(item, behavior);
   }

   public final class_1799 dispense(class_2342 source, class_1799 stack) {
      if (this.tryDispenseStackOnCuttingBoard(source, stack)) {
         this.method_10136(source);
         this.method_10133(source, (class_2350)source.comp_1969().method_11654(class_2315.field_10918));
         return stack;
      } else {
         return DISPENSE_ITEM_BEHAVIOR_HASH_MAP.get(stack.method_7909()).dispense(source, stack);
      }
   }

   public boolean tryDispenseStackOnCuttingBoard(class_2342 source, class_1799 stack) {
      this.method_27955(false);
      class_1937 level = source.comp_1967();
      class_2338 pos = source.comp_1968().method_10093((class_2350)source.comp_1969().method_11654(class_2315.field_10918));
      class_2680 state = level.method_8320(pos);
      class_2248 block = state.method_26204();
      if (block instanceof CuttingBoardBlock && level.method_8321(pos) instanceof CuttingBoardBlockEntity cuttingBoard) {
         class_1799 boardItem = cuttingBoard.getStoredItem().method_7972();
         if (!boardItem.method_7960() && cuttingBoard.processStoredItemUsingTool(stack, null)) {
            CuttingBoardBlock.spawnCuttingParticles(level, pos, boardItem, 5);
            this.method_27955(true);
         }

         return true;
      } else {
         return false;
      }
   }
}
