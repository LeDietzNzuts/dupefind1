package vectorwing.farmersdelight.common.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.class_1297;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class RiceBaleBlock extends class_2248 {
   public static final class_2753 FACING = class_2741.field_12525;

   public RiceBaleBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(FACING, class_2350.field_11036));
      FlammableBlockRegistry.getDefaultInstance().add(this, this.getFlammability(null, null, null, null), this.getFireSpreadSpeed(null, null, null, null));
   }

   public void method_9554(class_1937 level, class_2680 state, class_2338 pos, class_1297 entityIn, float fallDistance) {
      entityIn.method_5747(fallDistance, 0.2F, level.method_48963().method_48827());
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8038());
   }

   public int getFireSpreadSpeed(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 60;
   }

   public int getFlammability(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 20;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING});
   }

   public class_2680 method_9598(class_2680 state, class_2470 rot) {
      return (class_2680)state.method_11657(FACING, rot.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 state, class_2415 mirrorIn) {
      return state.method_26186(mirrorIn.method_10345((class_2350)state.method_11654(FACING)));
   }
}
