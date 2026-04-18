package noobanidus.mods.lootr.common.block;

import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2346;
import net.minecraft.class_2350;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_8170;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrBrushableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LootrBrushableBlock extends class_8170 {
   public static final class_2758 DUSTED = class_2741.field_42836;

   public LootrBrushableBlock(class_2248 pseudoReplacement, class_3414 soundEvent, class_3414 soundEvent2, class_2251 properties) {
      super(pseudoReplacement, soundEvent, soundEvent2, properties);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{DUSTED});
   }

   public class_2464 method_9604(class_2680 blockState) {
      return class_2464.field_11458;
   }

   public void method_9615(class_2680 blockState, class_1937 level, class_2338 blockPos, class_2680 blockState2, boolean bl) {
      level.method_39279(blockPos, this, 2);
   }

   public class_2680 method_9559(
      class_2680 blockState, class_2350 direction, class_2680 blockState2, class_1936 levelAccessor, class_2338 blockPos, class_2338 blockPos2
   ) {
      levelAccessor.method_39279(blockPos, this, 2);
      return super.method_9559(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
   }

   public void method_9588(class_2680 blockState, class_3218 serverLevel, class_2338 blockPos, class_5819 randomSource) {
      if (serverLevel.method_8321(blockPos) instanceof LootrBrushableBlockEntity brushableBlockEntity) {
         brushableBlockEntity.IBrushable$checkReset();
         if (!LootrAPI.canBrushablesSelfSupport()) {
            if (class_2346.method_10128(serverLevel.method_8320(blockPos.method_10074())) && blockPos.method_10264() >= serverLevel.method_31607()) {
               LootrBrushableBlockEntity.fall(serverLevel, blockPos, blockState, brushableBlockEntity);
            }
         }
      }
   }

   @Nullable
   public class_2586 method_10123(class_2338 blockPos, class_2680 blockState) {
      return new LootrBrushableBlockEntity(blockPos, blockState);
   }

   protected boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos) {
      return LootrAPI.getAnalogOutputSignal(pBlockState, pLevel, pPos, 0);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 pLevel, class_2680 pState, class_2591<T> pBlockEntityType) {
      return ILootrBlockEntity::ticker;
   }
}
