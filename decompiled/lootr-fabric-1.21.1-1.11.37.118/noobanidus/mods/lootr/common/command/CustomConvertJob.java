package noobanidus.mods.lootr.common.command;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import net.minecraft.class_1923;
import net.minecraft.class_2168;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2818;
import net.minecraft.class_3218;
import net.minecraft.class_3230;
import net.minecraft.class_2818.class_2819;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;

public final class CustomConvertJob {
   private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("lootr-convert-%d").build();
   private static final class_3230<class_1923> CONVERT_TICKET = class_3230.method_14291("lootr_convert", Comparator.comparingLong(class_1923::method_8324));
   private static Thread convertThread;
   private static final int TICKET_LEVEL = 2;
   private static final int batchSize = 2;

   public static void start(MinecraftServer server, class_3218 level, List<class_1923> positions, class_2168 src) {
      if (convertThread != null && convertThread.isAlive()) {
         src.method_9213(class_2561.method_43470("A conversion job is already running."));
      } else {
         AtomicBoolean running = new AtomicBoolean(true);
         AtomicInteger processed = new AtomicInteger();
         AtomicInteger converted = new AtomicInteger();
         AtomicInteger skipped = new AtomicInteger();
         AtomicInteger convertedBlockEntities = new AtomicInteger();
         convertThread = THREAD_FACTORY.newThread(
            () -> {
               try {
                  for (int i = 0; i < positions.size() && running.get(); i += 2) {
                     int from = i;
                     int to = Math.min(i + 2, positions.size());
                     CompletableFuture<Void> batchDone = new CompletableFuture<>();
                     server.execute(
                        () -> {
                           try {
                              for (int j = from; j < to; j++) {
                                 class_1923 cp = positions.get(j);
                                 int convertedCount = processOneChunkOnServerThread(level, cp, src);
                                 processed.incrementAndGet();
                                 if (convertedCount > 0) {
                                    converted.incrementAndGet();
                                    convertedBlockEntities.addAndGet(convertedCount);
                                 } else {
                                    skipped.incrementAndGet();
                                 }
                              }

                              if (processed.get() % 50 == 0) {
                                 src.method_9226(
                                    () -> class_2561.method_43470(
                                       "Progress: "
                                          + processed.get()
                                          + "/"
                                          + positions.size()
                                          + " converted="
                                          + converted.get()
                                          + " chunks, skipped="
                                          + skipped.get()
                                          + " empty chunks, converted a total of "
                                          + convertedBlockEntities.get()
                                          + " block entities to custom inventories."
                                    ),
                                    true
                                 );
                              }

                              batchDone.complete(null);
                           } catch (Throwable var13x) {
                              batchDone.completeExceptionally(var13x);
                           }
                        }
                     );
                     batchDone.join();
                  }

                  server.execute(
                     () -> src.method_9226(
                        () -> class_2561.method_43470(
                           "Conversion complete. processed=" + processed.get() + " converted=" + converted.get() + " skipped=" + skipped.get()
                        ),
                        true
                     )
                  );
               } catch (Throwable var13) {
                  server.execute(() -> src.method_9213(class_2561.method_43470("Conversion failed: " + var13)));
               }
            }
         );
         convertThread.start();
      }
   }

   private static int processOneChunkOnServerThread(class_3218 level, class_1923 pos, class_2168 src) {
      if (level == null) {
         src.method_9213(class_2561.method_43470("Level not found."));
         return 0;
      } else {
         level.method_14178().method_17297(CONVERT_TICKET, pos, 2, pos);
         Consumer<String> reporter = msg -> src.method_9226(() -> class_2561.method_43470(msg), true);

         int var12;
         try {
            class_2818 chunk = level.method_8497(pos.field_9181, pos.field_9180);
            int changed = 0;

            for (class_2338 bePos : chunk.method_12021()) {
               class_2586 be = chunk.method_12201(bePos, class_2819.field_12860);
               if (be != null) {
                  changed += convertAt(bePos, level, reporter, src.method_30497());
               }
            }

            var12 = changed;
         } finally {
            level.method_14178().method_17300(CONVERT_TICKET, pos, 2, pos);
         }

         return var12;
      }
   }

   private static int convertAt(class_2338 pos, class_3218 level, Consumer<String> reporter, class_7874 registries) {
      return CommandLootr.convertToCustom(pos, level, reporter, registries) ? 1 : 0;
   }
}
