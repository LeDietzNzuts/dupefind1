package org.embeddedt.modernfix.fabric.datagen;

import java.lang.reflect.Method;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AfterInit;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.class_2561;
import net.minecraft.class_4185;
import net.minecraft.class_442;
import org.embeddedt.modernfix.ModernFix;

public class RuntimeDatagen {
   private static final boolean SHOULD_RUNTIME_DATAGEN = System.getProperty("fabric-api.datagen.output-dir") != null;

   private static void runRuntimeDatagen() {
      try {
         System.setProperty("fabric-api.datagen", "true");
         Method method = FabricDataGenHelper.class.getDeclaredMethod("runInternal");
         method.setAccessible(true);
         method.invoke(null);
      } catch (Throwable var4) {
         ModernFix.LOGGER.error("Error running datagen", var4);
      } finally {
         System.clearProperty("fabric-api.datagen");
      }
   }

   public static void init() {
      if (SHOULD_RUNTIME_DATAGEN) {
         ScreenEvents.AFTER_INIT
            .register(
               (AfterInit)(client, s, scaledWidth, scaledHeight) -> {
                  if (s instanceof class_442 screen) {
                     screen.method_37063(
                        class_4185.method_46430(class_2561.method_43470("DG"), arg -> runRuntimeDatagen())
                           .method_46433(screen.field_22789 / 2 - 100 - 50, screen.field_22790 / 4 + 48)
                           .method_46437(50, 20)
                           .method_46431()
                     );
                  }
               }
            );
      }
   }
}
