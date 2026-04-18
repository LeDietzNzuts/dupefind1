package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.core.init.SoundInit;
import java.util.List;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2561;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_9062;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class Blinds extends class_2383 {
   private static final Map<class_2350, class_265> CLOSED_SHAPES = ImmutableMap.of(
      class_2350.field_11043,
      class_2248.method_9541(0.0, 13.0, 13.0, 16.0, 16.0, 16.0),
      class_2350.field_11034,
      class_2248.method_9541(0.0, 13.0, 0.0, 3.0, 16.0, 16.0),
      class_2350.field_11035,
      class_2248.method_9541(0.0, 13.0, 0.0, 16.0, 16.0, 3.0),
      class_2350.field_11039,
      class_2248.method_9541(13.0, 13.0, 0.0, 16.0, 16.0, 16.0)
   );
   private static final Map<class_2350, class_265> OPEN_SHAPES = ImmutableMap.of(
      class_2350.field_11043,
      class_2248.method_9541(0.0, 0.0, 13.0, 16.0, 16.0, 16.0),
      class_2350.field_11034,
      class_2248.method_9541(0.0, 0.0, 0.0, 3.0, 16.0, 16.0),
      class_2350.field_11035,
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
      class_2350.field_11039,
      class_2248.method_9541(13.0, 0.0, 0.0, 16.0, 16.0, 16.0)
   );
   private static final class_265 SHAPE_HIDDEN = class_259.method_1073();
   public static final class_2746 OPEN = class_2746.method_11825("open");
   public static final class_2746 HIDDEN = class_2746.method_11825("hidden");
   public static final MapCodec<Blinds> CODEC = method_54094(Blinds::new);

   public Blinds(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.method_9564().method_11657(OPEN, false)).method_11657(field_11177, class_2350.field_11043))
            .method_11657(HIDDEN, false)
      );
   }

   protected MapCodec<Blinds> method_53969() {
      return CODEC;
   }

   public boolean method_37403(class_2680 state, class_1922 level, class_2338 pos) {
      return false;
   }

   public class_265 method_25959(class_2680 state, class_1922 level, class_2338 pos) {
      return class_259.method_1073();
   }

   public boolean method_9579(class_2680 state, class_1922 level, class_2338 pos) {
      return true;
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)((class_2680)((class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153()))
            .method_11657(OPEN, false))
         .method_11657(HIDDEN, false);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{OPEN, field_11177, HIDDEN});
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 result) {
      return this.toggle(state, level, pos);
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      this.toggle(state, level, pos);
      return class_9062.field_47729;
   }

   private class_1269 toggle(class_2680 state, class_1937 level, class_2338 pos) {
      if (level.method_8608()) {
         return class_1269.field_5812;
      } else {
         boolean currentlyOpen = (Boolean)state.method_11654(OPEN);
         if (Beautify.CONFIG.blinds.opensFromRoot) {
            int step = 1;

            while (this.sameBlindType(level, pos.method_10086(step), state)) {
               step++;
            }

            pos = pos.method_10086(step - 1);
         }

         level.method_8652(pos, (class_2680)state.method_11657(OPEN, !currentlyOpen), 3);
         if (Beautify.CONFIG.blinds.searchRadius > 0) {
            for (int offsetDown = 1;
               offsetDown <= Beautify.CONFIG.blinds.searchRadius && this.sameBlindType(level, pos.method_10087(offsetDown), state);
               offsetDown++
            ) {
               this.switchOpenUpdateHidden(level, pos.method_10087(offsetDown), state, false);
            }
         }

         if (Beautify.CONFIG.blinds.searchRadius > 0) {
            if (state.method_11654(field_11177) == class_2350.field_11043 || state.method_11654(field_11177) == class_2350.field_11035) {
               for (int offsetEast = 1;
                  offsetEast <= Beautify.CONFIG.blinds.searchRadius / 2 && this.sameBlindType(level, pos.method_10089(offsetEast), state);
                  offsetEast++
               ) {
                  level.method_8652(pos.method_10089(offsetEast), (class_2680)state.method_11657(OPEN, !currentlyOpen), 3);

                  for (int offsetDown = 1;
                     offsetDown <= Beautify.CONFIG.blinds.searchRadius
                        && this.sameBlindType(level, pos.method_10087(offsetDown).method_10089(offsetEast), state);
                     offsetDown++
                  ) {
                     this.switchOpenUpdateHidden(level, pos.method_10087(offsetDown).method_10089(offsetEast), state, false);
                  }
               }

               for (int offsetWest = 1;
                  offsetWest <= Beautify.CONFIG.blinds.searchRadius / 2 && this.sameBlindType(level, pos.method_10088(offsetWest), state);
                  offsetWest++
               ) {
                  level.method_8652(pos.method_10088(offsetWest), (class_2680)state.method_11657(OPEN, !currentlyOpen), 3);

                  for (int offsetDown = 1;
                     offsetDown <= Beautify.CONFIG.blinds.searchRadius
                        && this.sameBlindType(level, pos.method_10087(offsetDown).method_10088(offsetWest), state);
                     offsetDown++
                  ) {
                     this.switchOpenUpdateHidden(level, pos.method_10087(offsetDown).method_10088(offsetWest), state, false);
                  }
               }
            }

            if (state.method_11654(field_11177) == class_2350.field_11034 || state.method_11654(field_11177) == class_2350.field_11039) {
               for (int offsetNorth = 1;
                  offsetNorth <= Beautify.CONFIG.blinds.searchRadius / 2 && this.sameBlindType(level, pos.method_10076(offsetNorth), state);
                  offsetNorth++
               ) {
                  level.method_8652(pos.method_10076(offsetNorth), (class_2680)state.method_11657(OPEN, !currentlyOpen), 3);

                  for (int offsetDown = 1;
                     offsetDown <= Beautify.CONFIG.blinds.searchRadius
                        && this.sameBlindType(level, pos.method_10087(offsetDown).method_10076(offsetNorth), state);
                     offsetDown++
                  ) {
                     this.switchOpenUpdateHidden(level, pos.method_10087(offsetDown).method_10076(offsetNorth), state, false);
                  }
               }

               for (int offsetSouth = 1;
                  offsetSouth <= Beautify.CONFIG.blinds.searchRadius / 2 && this.sameBlindType(level, pos.method_10077(offsetSouth), state);
                  offsetSouth++
               ) {
                  level.method_8652(pos.method_10077(offsetSouth), (class_2680)state.method_11657(OPEN, !currentlyOpen), 3);

                  for (int offsetDown = 1;
                     offsetDown <= Beautify.CONFIG.blinds.searchRadius
                        && this.sameBlindType(level, pos.method_10087(offsetDown).method_10077(offsetSouth), state);
                     offsetDown++
                  ) {
                     this.switchOpenUpdateHidden(level, pos.method_10087(offsetDown).method_10077(offsetSouth), state, false);
                  }
               }
            }
         }

         level.method_8396(null, pos, currentlyOpen ? SoundInit.BLINDS_CLOSE : SoundInit.BLINDS_OPEN, class_3419.field_15245, 1.0F, 1.0F);
         return class_1269.field_21466;
      }
   }

   private boolean sameBlindType(class_1936 level, class_2338 pos, class_2680 state) {
      class_2680 blindsState = level.method_8320(pos);
      class_2248 block = blindsState.method_26204();
      return block == this && state.method_28498(field_11177) ? blindsState.method_11654(field_11177) == state.method_11654(field_11177) : false;
   }

   private void switchOpenUpdateHidden(class_1936 level, class_2338 pos, class_2680 state, boolean updateOnly) {
      if (updateOnly) {
         level.method_8652(pos, (class_2680)state.method_11657(HIDDEN, false), 3);
      } else {
         if (!(Boolean)state.method_11654(OPEN)) {
            level.method_8652(pos, (class_2680)((class_2680)state.method_11657(OPEN, true)).method_11657(HIDDEN, false), 3);
         } else {
            level.method_8652(pos, (class_2680)((class_2680)state.method_11657(OPEN, false)).method_11657(HIDDEN, true), 3);
         }
      }
   }

   public void method_9585(class_1936 level, class_2338 pos, class_2680 state) {
      if (this.sameBlindType(level, pos.method_10074(), state)) {
         this.switchOpenUpdateHidden(level, pos.method_10074(), state, true);
      }
   }

   public void method_9586(class_1937 level, class_2338 pos, class_1927 explosion) {
      class_2680 state = level.method_8320(pos);
      if (this.sameBlindType(level, pos.method_10074(), state)) {
         this.switchOpenUpdateHidden(level, pos.method_10074(), state, true);
      }

      super.method_9586(level, pos, explosion);
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      if ((Boolean)state.method_11654(HIDDEN)) {
         return SHAPE_HIDDEN;
      } else {
         class_2350 facing = (class_2350)state.method_11654(field_11177);
         boolean isOpen = (Boolean)state.method_11654(OPEN);
         Map<class_2350, class_265> shapes = isOpen ? OPEN_SHAPES : CLOSED_SHAPES;
         return shapes.get(facing);
      }
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.blinds.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.blinds.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
