package com.natamus.collective_common_forge.check;

import com.natamus.collective_common_forge.config.CollectiveConfigHandler;
import com.natamus.collective_common_forge.data.Constants;
import com.natamus.collective_common_forge.services.Services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class RegisterMod {
   private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();

   public static void register(String modName, String modId, String modVersion, String rawGameVersion) {
      if (CollectiveConfigHandler.enableUpdateChecker) {
         String gameVersion = rawGameVersion.replaceAll("\\[", "").replaceAll("]", "");
         String slug = modName.toLowerCase().replaceAll("[^a-z0-9 ]", "").replaceAll(" ", "-");
         String loader = Services.MODLOADER.getModLoaderName();
         new Thread(() -> checkForUpdate(slug, modName, modVersion, gameVersion, loader)).start();
      }
   }

   private static void checkForUpdate(String slug, String modName, String modVersion, String gameVersion, String loader) {
      try {
         Thread.sleep((long)(Math.random() * 1000.0));
         String url = "https://update.serilum.com/minecraft/?mc_version=" + gameVersion + "&slug=" + slug + "&mod_version=" + modVersion + "&loader=" + loader;
         HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(10L)).GET().build();
         HttpResponse<String> response = HTTP_CLIENT.send(request, BodyHandlers.ofString());
         if (response.statusCode() == 200) {
            String latestVersion = response.body().trim();
            if (!latestVersion.equals(modVersion)) {
               Constants.LOG.warn("[Update] {} has an update available: {} -> {}", new Object[]{modName, modVersion, latestVersion});
            }
         }
      } catch (Exception var9) {
      }
   }
}
