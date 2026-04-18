package dev.architectury.core.fluid;

import dev.architectury.core.fluid.fabric.ArchitecturyFlowingFluidImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.platform.Platform;
import java.util.Optional;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2404;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3414;
import net.minecraft.class_3609;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_4538;
import net.minecraft.class_2689.class_2690;
import org.jetbrains.annotations.NotNull;

public abstract class ArchitecturyFlowingFluid extends class_3609 {
   private final ArchitecturyFluidAttributes attributes;

   ArchitecturyFlowingFluid(ArchitecturyFluidAttributes attributes) {
      checkPlatform(null);
      this.attributes = attributes;
      if (Platform.isFabric()) {
         addFabricFluidAttributes(this, attributes);
      }
   }

   private static <T> T checkPlatform(T obj) {
      if (Platform.isForgeLike()) {
         throw new IllegalStateException("This class should've been replaced on Forge!");
      } else {
         return obj;
      }
   }

   @ExpectPlatform
   @Transformed
   private static void addFabricFluidAttributes(class_3609 fluid, ArchitecturyFluidAttributes properties) {
      ArchitecturyFlowingFluidImpl.addFabricFluidAttributes(fluid, properties);
   }

   public class_3611 method_15750() {
      return this.attributes.getFlowingFluid();
   }

   public class_3611 method_15751() {
      return this.attributes.getSourceFluid();
   }

   protected boolean method_15737(class_1937 level) {
      return this.attributes.canConvertToSource();
   }

   protected void method_15730(class_1936 level, class_2338 pos, class_2680 state) {
      class_2586 blockEntity = state.method_31709() ? level.method_8321(pos) : null;
      class_2248.method_9610(state, level, pos, blockEntity);
   }

   protected int method_15733(class_4538 level) {
      return this.attributes.getSlopeFindDistance(level);
   }

   protected int method_15739(class_4538 level) {
      return this.attributes.getDropOff(level);
   }

   public class_1792 method_15774() {
      class_1792 item = this.attributes.getBucketItem();
      return item == null ? class_1802.field_8162 : item;
   }

   protected boolean method_15777(class_3610 state, class_1922 level, class_2338 pos, class_3611 fluid, class_2350 direction) {
      return direction == class_2350.field_11033 && !this.method_15780(fluid);
   }

   public int method_15789(class_4538 level) {
      return this.attributes.getTickDelay(level);
   }

   protected float method_15784() {
      return this.attributes.getExplosionResistance();
   }

   protected class_2680 method_15790(class_3610 state) {
      class_2404 block = this.attributes.getBlock();
      return block == null ? class_2246.field_10124.method_9564() : (class_2680)block.method_9564().method_11657(class_2404.field_11278, method_15741(state));
   }

   @NotNull
   public Optional<class_3414> method_32359() {
      return Optional.ofNullable(this.attributes.getFillSound());
   }

   public boolean method_15780(class_3611 fluid) {
      return fluid == this.method_15751() || fluid == this.method_15750();
   }

   public static class Flowing extends ArchitecturyFlowingFluid {
      public Flowing(ArchitecturyFluidAttributes attributes) {
         super(attributes);
         this.method_15781((class_3610)((class_3610)this.method_15783().method_11664()).method_11657(field_15900, 7));
      }

      protected void method_15775(class_2690<class_3611, class_3610> builder) {
         super.method_15775(builder);
         builder.method_11667(new class_2769[]{field_15900});
      }

      public int method_15779(class_3610 state) {
         return (Integer)state.method_11654(field_15900);
      }

      public boolean method_15793(class_3610 state) {
         return false;
      }
   }

   public static class Source extends ArchitecturyFlowingFluid {
      public Source(ArchitecturyFluidAttributes attributes) {
         super(attributes);
      }

      public int method_15779(class_3610 state) {
         return 8;
      }

      public boolean method_15793(class_3610 state) {
         return true;
      }
   }
}
