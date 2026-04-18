package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2975;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_7924;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.world.WildCropGeneration;

public class SandyShrubBlock extends class_2261 implements class_2256 {
   public static final MapCodec<SandyShrubBlock> CODEC = method_54094(SandyShrubBlock::new);
   protected static final class_265 SHAPE = class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

   public SandyShrubBlock(class_2251 properties) {
      super(properties);
   }

   protected MapCodec<? extends class_2261> method_53969() {
      return CODEC;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return state.method_26164(class_3481.field_15466);
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      return true;
   }

   public boolean method_9650(class_1937 level, class_5819 random, class_2338 pos, class_2680 state) {
      return true;
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      level.method_30349()
         .method_33310(class_7924.field_41239)
         .flatMap(value -> value.method_40264(WildCropGeneration.FEATURE_PATCH_SANDY_SHRUB))
         .ifPresent(value -> ((class_2975)value.comp_349()).method_12862(level, level.method_14178().method_12129(), random, pos.method_10084()));
   }
}
