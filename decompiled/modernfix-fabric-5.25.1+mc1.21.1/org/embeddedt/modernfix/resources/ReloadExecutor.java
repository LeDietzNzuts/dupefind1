package org.embeddedt.modernfix.resources;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.class_148;
import net.minecraft.class_2966;
import net.minecraft.class_9813;
import org.embeddedt.modernfix.ModernFix;

public class ReloadExecutor {
   public static ExecutorService createCustomResourceReloadExecutor() {
      ClassLoader loader = ReloadExecutor.class.getClassLoader();
      AtomicInteger workerCount = new AtomicInteger(0);
      return new ForkJoinPool(ForkJoinPool.getCommonPoolParallelism(), forkJoinPool -> {
         ForkJoinWorkerThread forkJoinWorkerThread = new ForkJoinWorkerThread(forkJoinPool) {
            @Override
            protected void onTermination(Throwable throwOnTermination) {
               if (throwOnTermination != null) {
                  ModernFix.LOGGER.warn("{} died", this.getName(), throwOnTermination);
               } else {
                  ModernFix.LOGGER.debug("{} shutdown", this.getName());
               }

               super.onTermination(throwOnTermination);
            }
         };
         forkJoinWorkerThread.setContextClassLoader(loader);
         forkJoinWorkerThread.setName("Worker-ResourceReload-" + workerCount.getAndIncrement());
         return forkJoinWorkerThread;
      }, ReloadExecutor::handleException, true);
   }

   private static void handleException(Thread thread, Throwable throwable) {
      if (throwable instanceof CompletionException) {
         throwable = throwable.getCause();
      }

      if (throwable instanceof class_148) {
         class_2966.method_12847(((class_148)throwable).method_631().method_60920(class_9813.field_52181));
         System.exit(-1);
      }

      ModernFix.LOGGER.error(String.format("Caught exception in thread %s", thread), throwable);
   }
}
