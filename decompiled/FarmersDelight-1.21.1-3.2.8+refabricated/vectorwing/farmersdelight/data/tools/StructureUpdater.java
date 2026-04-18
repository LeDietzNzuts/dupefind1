package vectorwing.farmersdelight.data.tools;

import com.google.common.hash.Hashing;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerUpper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.class_155;
import net.minecraft.class_2405;
import net.minecraft.class_2487;
import net.minecraft.class_2505;
import net.minecraft.class_2507;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3259;
import net.minecraft.class_3262;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3499;
import net.minecraft.class_3551;
import net.minecraft.class_4284;
import net.minecraft.class_5352;
import net.minecraft.class_7403;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9224;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class StructureUpdater implements class_2405 {
   private final class_2960 id;
   private final FabricDataOutput output;
   private final class_3262 resources;

   public StructureUpdater(FabricDataOutput output, String basePath, String modid) {
      this.id = class_2960.method_60655(modid, basePath);
      this.output = output;
      this.resources = new class_3259(
         new class_9224("fdrf/temp", class_2561.method_43470("temp"), class_5352.field_25348, Optional.empty()),
         output.method_60917(class_7924.field_41246).method_44108(this.id, ".json")
      );
   }

   public CompletableFuture<?> method_10319(@NotNull class_7403 cache) {
      try {
         class_3298 resource = new class_3298(this.resources, this.resources.method_14405(class_3264.field_14190, this.id));
         this.process(this.id, resource, cache);
         return CompletableFuture.completedFuture(null);
      } catch (IOException var3) {
         return CompletableFuture.failedFuture(var3);
      }
   }

   private void process(class_2960 loc, class_3298 resource, class_7403 cache) throws IOException {
      class_2487 inputNBT = class_2507.method_10629(resource.method_14482(), class_2505.method_53898());
      class_2487 converted = updateNBT(inputNBT);
      if (!converted.equals(inputNBT)) {
         Class<? extends DataFixer> fixerClass = class_3551.method_15450().getClass();
         if (!fixerClass.equals(DataFixerUpper.class)) {
            throw new RuntimeException("Structures are not up to date, but unknown data fixer is in use: " + fixerClass.getName());
         }

         this.writeNBTTo(loc, converted, cache);
      }
   }

   private void writeNBTTo(class_2960 loc, class_2487 data, class_7403 cache) throws IOException {
      ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
      class_2507.method_10634(data, bytearrayoutputstream);
      byte[] bytes = bytearrayoutputstream.toByteArray();
      Path outputPath = this.output.method_45971().resolve("data/" + loc.method_12836() + "/" + loc.method_12832());
      cache.method_43346(outputPath, bytes, Hashing.sha256().hashBytes(bytes));
   }

   private static class_2487 updateNBT(class_2487 nbt) {
      class_2487 updatedNBT = class_4284.field_19217
         .method_48130(
            class_3551.method_15450(),
            nbt,
            nbt.method_10545("DataVersion") ? nbt.method_10550("DataVersion") : class_155.method_16673().method_37912().method_38494()
         );
      class_3499 template = new class_3499();
      template.method_15183(class_7923.field_41175.method_46771(), updatedNBT);
      return template.method_15175(new class_2487());
   }

   @NotNull
   public String method_10321() {
      return "Update structure files in " + this.id.toString();
   }
}
