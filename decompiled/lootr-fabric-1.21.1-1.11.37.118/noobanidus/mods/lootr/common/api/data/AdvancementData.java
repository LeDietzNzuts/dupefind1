package noobanidus.mods.lootr.common.api.data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class AdvancementData extends class_18 {
   @Deprecated
   public static final class_8645<AdvancementData> FACTORY = new class_8645(AdvancementData::new, AdvancementData::load, null);
   private final Set<AdvancementData.UUIDPair> data = new HashSet<>();

   public static AdvancementData load(class_2487 compound, class_7874 provider) {
      AdvancementData data = new AdvancementData();
      data.data.clear();
      class_2499 incoming = compound.method_10554("data", 10);

      for (int i = 0; i < incoming.size(); i++) {
         data.data.add(AdvancementData.UUIDPair.fromNBT(provider, incoming.method_10602(i)));
      }

      return data;
   }

   public boolean contains(UUID first, UUID second) {
      return this.contains(new AdvancementData.UUIDPair(first, second));
   }

   public boolean contains(AdvancementData.UUIDPair pair) {
      return !this.data.isEmpty() && this.data.contains(pair);
   }

   public void add(UUID first, UUID second) {
      this.add(new AdvancementData.UUIDPair(first, second));
   }

   public void add(AdvancementData.UUIDPair pair) {
      this.data.add(pair);
      this.method_80();
   }

   public class_2487 method_75(class_2487 pCompound, class_7874 provider) {
      class_2499 result = new class_2499();

      for (AdvancementData.UUIDPair pair : this.data) {
         result.add(pair.serializeNBT(provider));
      }

      pCompound.method_10566("data", result);
      return pCompound;
   }

   public static class UUIDPair {
      @NotNull
      private UUID first;
      private UUID second;

      protected UUIDPair() {
      }

      public UUIDPair(@NotNull UUID first, @NotNull UUID second) {
         this.first = first;
         this.second = second;
      }

      public static AdvancementData.UUIDPair fromNBT(class_7874 provider, class_2487 tag) {
         AdvancementData.UUIDPair pair = new AdvancementData.UUIDPair();
         pair.deserializeNBT(provider, tag);
         return pair;
      }

      @NotNull
      public UUID getFirst() {
         return this.first;
      }

      @NotNull
      public UUID getSecond() {
         return this.second;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AdvancementData.UUIDPair uuidPair = (AdvancementData.UUIDPair)o;
            return !this.first.equals(uuidPair.first) ? false : this.second.equals(uuidPair.second);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         int result = this.first.hashCode();
         return 31 * result + this.second.hashCode();
      }

      public class_2487 serializeNBT(class_7874 provider) {
         class_2487 result = new class_2487();
         result.method_25927("first", this.getFirst());
         result.method_25927("second", this.getSecond());
         return result;
      }

      public void deserializeNBT(class_7874 provider, class_2487 nbt) {
         this.first = nbt.method_25926("first");
         this.second = nbt.method_25926("second");
      }
   }
}
