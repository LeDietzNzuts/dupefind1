package org.embeddedt.modernfix.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.class_3300;
import net.minecraft.class_3302;
import net.minecraft.class_3695;
import net.minecraft.class_3302.class_4045;

public class NamedPreparableResourceListener implements class_3302 {
   private final class_3302 delegate;

   public NamedPreparableResourceListener(class_3302 delegate) {
      this.delegate = delegate;
   }

   public CompletableFuture<Void> method_25931(
      class_4045 stage,
      class_3300 resourceManager,
      class_3695 preparationsProfiler,
      class_3695 reloadProfiler,
      Executor backgroundExecutor,
      Executor gameExecutor
   ) {
      return this.delegate.method_25931(stage, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor);
   }

   public String method_22322() {
      return this.delegate.method_22322() + " [" + this.delegate.getClass().getName() + "]";
   }
}
