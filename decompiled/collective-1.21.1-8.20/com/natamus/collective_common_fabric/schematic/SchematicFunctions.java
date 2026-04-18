package com.natamus.collective_common_fabric.schematic;

import it.unimi.dsi.fastutil.io.FastBufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import net.minecraft.class_2487;
import net.minecraft.class_2505;
import net.minecraft.class_2507;

public class SchematicFunctions {
   public static class_2487 readCompressed(InputStream inputStream) throws IOException {
      class_2487 compoundTag;
      try (DataInputStream dataInputStream = createDecompressorStream(inputStream)) {
         compoundTag = class_2507.method_10625(dataInputStream, class_2505.method_53898());
      }

      return compoundTag;
   }

   private static DataInputStream createDecompressorStream(InputStream inputStream) throws IOException {
      return new DataInputStream(new FastBufferedInputStream(new GZIPInputStream(inputStream)));
   }
}
