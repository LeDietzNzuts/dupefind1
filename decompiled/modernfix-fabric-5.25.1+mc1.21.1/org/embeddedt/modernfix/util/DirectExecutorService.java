package org.embeddedt.modernfix.util;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class DirectExecutorService extends AbstractExecutorService {
   private boolean isShutdown;

   @Override
   public void shutdown() {
      this.isShutdown = true;
   }

   @NotNull
   @Override
   public List<Runnable> shutdownNow() {
      this.isShutdown = true;
      return List.of();
   }

   @Override
   public boolean isShutdown() {
      return this.isShutdown;
   }

   @Override
   public boolean isTerminated() {
      return this.isShutdown;
   }

   @Override
   public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
      return true;
   }

   @Override
   public void execute(@NotNull Runnable command) {
      command.run();
   }
}
