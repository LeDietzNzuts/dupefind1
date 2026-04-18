package net.caffeinemc.mods.lithium.common.world.blockview;

import java.util.List;
import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_1941;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2784;
import net.minecraft.class_3610;
import net.minecraft.class_3726;
import org.jetbrains.annotations.Nullable;

public record SingleBlockBlockView(class_2680 state, class_2338 blockPos) implements class_1922, class_1941 {
   public static SingleBlockBlockView of(class_2680 blockState, class_2338 blockPos) {
      return new SingleBlockBlockView(blockState, blockPos.method_10062());
   }

   public class_2680 method_8320(class_2338 pos) {
      if (pos.equals(this.blockPos())) {
         return this.state();
      } else {
         throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
      }
   }

   public class_3610 method_8316(class_2338 pos) {
      if (pos.equals(this.blockPos())) {
         return this.state().method_26227();
      } else {
         throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
      }
   }

   @Nullable
   public class_2586 method_8321(class_2338 pos) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public int method_31605() {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public int method_31607() {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public class_2784 method_8621() {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   @Nullable
   public class_1922 method_22338(int chunkX, int chunkZ) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public boolean method_8611(@Nullable class_1297 except, class_265 shape) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public boolean method_8628(class_2680 state, class_2338 pos, class_3726 context) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public boolean method_8606(class_1297 entity) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public boolean method_8587(@Nullable class_1297 entity, class_238 box) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public List<class_265> method_20743(@Nullable class_1297 entity, class_238 box) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public Iterable<class_265> method_8600(@Nullable class_1297 entity, class_238 box) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public Iterable<class_265> method_20812(@Nullable class_1297 entity, class_238 box) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public boolean method_39454(@Nullable class_1297 entity, class_238 box) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public Optional<class_243> method_33594(@Nullable class_1297 entity, class_265 shape, class_243 target, double x, double y, double z) {
      throw SingleBlockBlockView.SingleBlockViewException.INSTANCE;
   }

   public static class SingleBlockViewException extends RuntimeException {
      public static final SingleBlockBlockView.SingleBlockViewException INSTANCE = new SingleBlockBlockView.SingleBlockViewException();

      private SingleBlockViewException() {
         this.setStackTrace(new StackTraceElement[0]);
      }

      @Override
      public synchronized Throwable fillInStackTrace() {
         this.setStackTrace(new StackTraceElement[0]);
         return this;
      }
   }
}
