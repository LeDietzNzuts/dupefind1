package net.p3pp3rf1y.sophisticatedcore.compat.litematica;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;

public class LitematicaCompat implements ICompat {
   public static final ItemApiLookup<LitematicaCompat.LitematicaWrapper, class_7874> LITEMATICA_CAPABILITY = ItemApiLookup.get(
      SophisticatedCore.getRL("sophisticatedcore_requestcontents"), LitematicaCompat.LitematicaWrapper.class, class_7874.class
   );

   public static Optional<LitematicaCompat.LitematicaWrapper> getWrapper(class_1799 provider) {
      return Optional.ofNullable((LitematicaCompat.LitematicaWrapper)LITEMATICA_CAPABILITY.find(provider, null));
   }

   @Override
   public void setup() {
   }

   public record LitematicaWrapper(IStorageWrapper wrapper, Function<UUID, class_8710> packetGenerator) {
   }
}
