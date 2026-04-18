package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3481;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.SoilUtils;

public class MushroomColonyBlock extends class_2261 implements class_2256 {
   public static final MapCodec<MushroomColonyBlock> CODEC = RecordCodecBuilder.mapCodec(
      builder -> builder.group(class_7923.field_41178.method_40294().fieldOf("mushroom").forGetter(block -> block.mushroomType), method_54096())
         .apply(builder, MushroomColonyBlock::new)
   );
   public static final int PLACING_LIGHT_LEVEL = 13;
   public final class_6880<class_1792> mushroomType;
   public static final class_2758 COLONY_AGE = class_2741.field_12497;
   protected static final class_265[] SHAPE_BY_AGE = new class_265[]{
      class_2248.method_9541(4.0, 0.0, 4.0, 12.0, 8.0, 12.0),
      class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 12.0, 14.0),
      class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
   };

   public MushroomColonyBlock(class_6880<class_1792> mushroomType, class_2251 properties) {
      super(properties);
      this.mushroomType = mushroomType;
      this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(COLONY_AGE, 0));
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE_BY_AGE[state.method_11654(this.getAgeProperty())];
   }

   public class_2758 getAgeProperty() {
      return COLONY_AGE;
   }

   protected MapCodec<? extends class_2261> method_53969() {
      return CODEC;
   }

   public boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return state.method_26216(level, pos);
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_2338 floorPos = pos.method_10074();
      class_2680 floorState = level.method_8320(floorPos);
      if (floorState.method_26164(class_3481.field_25739)) {
         return true;
      } else if (state.method_27852(this) && floorState.method_26204() instanceof RichSoilBlock) {
         return SoilUtils.isAbleToPlaceRichSoil(this);
      } else {
         return state.method_27852(this) && floorState.method_26204() instanceof RichSoilFarmlandBlock
            ? SoilUtils.isAbleToPlaceRichSoilFarmland(this)
            : level.method_22335(pos, 0) < 13 && this.method_9695(state, level, pos);
      }
   }

   public class_9062 method_55765(class_1799 heldStack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      int age = (Integer)state.method_11654(COLONY_AGE);
      if (age > 0 && heldStack.method_31573(ConventionalItemTags.SHEAR_TOOLS)) {
         method_9577(level, pos, this.method_9574(level, pos, state));
         level.method_8396(null, pos, class_3417.field_14705, class_3419.field_15245, 1.0F, 1.0F);
         level.method_8652(pos, (class_2680)state.method_11657(COLONY_AGE, age - 1), 2);
         if (!level.field_9236) {
            heldStack.method_7970(1, player, class_1309.method_56079(hand));
         }

         return class_9062.field_47728;
      } else {
         return class_9062.field_47731;
      }
   }

   public int getMaxAge() {
      return 3;
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      int age = (Integer)state.method_11654(COLONY_AGE);
      class_2680 groundState = level.method_8320(pos.method_10074());
      if (age < this.getMaxAge() && groundState.method_26164(ModTags.MUSHROOM_COLONY_GROWABLE_ON) && random.method_43048(4) == 0) {
         level.method_8652(pos, (class_2680)state.method_11657(COLONY_AGE, age + 1), 2);
      }
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return new class_1799((class_1935)this.mushroomType.comp_349());
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{COLONY_AGE});
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      return (Integer)state.method_11654(this.getAgeProperty()) < this.getMaxAge();
   }

   public boolean method_9650(class_1937 level, class_5819 random, class_2338 pos, class_2680 state) {
      return true;
   }

   protected int getBonemealAgeIncrease(class_1937 level) {
      return class_3532.method_15395(level.field_9229, 1, 2);
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      int age = Math.min(this.getMaxAge(), (Integer)state.method_11654(COLONY_AGE) + this.getBonemealAgeIncrease(level));
      level.method_8652(pos, (class_2680)state.method_11657(COLONY_AGE, age), 2);
   }
}
