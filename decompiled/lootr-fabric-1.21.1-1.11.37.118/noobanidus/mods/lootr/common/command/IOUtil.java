package noobanidus.mods.lootr.common.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_5218;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class IOUtil {
   private static CompletableFuture<Void> deleteFileTasks = CompletableFuture.completedFuture(null);

   public static void withIOWorker(Runnable task) {
      deleteFileTasks = deleteFileTasks.thenRunAsync(task);
   }

   public static void waitUntilIOWorkerComplete() {
      deleteFileTasks.join();
      deleteFileTasks = CompletableFuture.completedFuture(null);
   }

   private static Path getSavedDataPath(MinecraftServer server, String fileName) {
      Path dataRoot = server.method_27050(new class_5218("data"));
      return dataRoot.resolve(fileName + ".dat");
   }

   public static void cullSavedDataAsync(MinecraftServer server, Set<String> savedDataFiles) {
      if (savedDataFiles.isEmpty()) {
         LootrAPI.LOG.info("No saved data files to cull.");
      } else {
         for (String name : savedDataFiles) {
            Path file = getSavedDataPath(server, name);
            withIOWorker(() -> {
               try {
                  Files.deleteIfExists(file);
               } catch (IOException var2) {
                  LootrAPI.LOG.error("Failed to delete saved data file: {}", file, var2);
               }
            });
         }

         withIOWorker(() -> LootrAPI.LOG.info("Culled {} saved data files.", savedDataFiles.size()));
      }
   }
}
