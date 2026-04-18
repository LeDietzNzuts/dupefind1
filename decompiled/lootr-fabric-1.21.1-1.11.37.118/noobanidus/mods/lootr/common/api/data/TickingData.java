package noobanidus.mods.lootr.common.api.data;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;

@Deprecated
public class TickingData extends class_18 {
   public static final class_8645<TickingData> FACTORY = new class_8645(TickingData::new, TickingData::load, null);
   private final Object2IntMap<UUID> tickMap = new Object2IntOpenHashMap();

   public TickingData() {
      this.tickMap.defaultReturnValue(-1);
   }

   public static TickingData load(class_2487 pCompound, class_7874 provider) {
      TickingData data = new TickingData();
      data.tickMap.clear();
      data.tickMap.defaultReturnValue(-1);
      class_2499 decayList = pCompound.method_10554("result", 10);

      for (int i = 0; i < decayList.size(); i++) {
         class_2487 thisTag = decayList.method_10602(i);
         data.tickMap.put(thisTag.method_25926("id"), thisTag.method_10550("value"));
      }

      return data;
   }

   public boolean isComplete(UUID id) {
      int value = this.getValue(id);
      return value == 0 || value == 1;
   }

   public int getValue(UUID id) {
      return this.tickMap.getInt(id);
   }

   public void setValue(UUID id, int decayAmount) {
      if (this.tickMap.put(id, decayAmount) == -1) {
         this.method_80();
      }
   }

   public void remove(UUID id) {
      if (this.tickMap.removeInt(id) != -1) {
         this.method_80();
      }
   }

   public void tick() {
      if (!this.tickMap.isEmpty()) {
         Object2IntMap<UUID> newMap = new Object2IntOpenHashMap();
         newMap.defaultReturnValue(-1);
         boolean changed = false;
         ObjectIterator var3 = this.tickMap.object2IntEntrySet().iterator();

         while (var3.hasNext()) {
            Entry<UUID> entry = (Entry<UUID>)var3.next();
            int value = entry.getIntValue();
            if (value > 0) {
               value--;
               changed = true;
            }

            newMap.put((UUID)entry.getKey(), value);
         }

         if (changed) {
            this.tickMap.clear();
            this.tickMap.putAll(newMap);
            this.method_80();
         }
      }
   }

   public class_2487 method_75(class_2487 pCompound, class_7874 provider) {
      class_2499 decayList = new class_2499();
      ObjectIterator var4 = this.tickMap.object2IntEntrySet().iterator();

      while (var4.hasNext()) {
         Entry<UUID> entry = (Entry<UUID>)var4.next();
         class_2487 thisTag = new class_2487();
         thisTag.method_25927("id", (UUID)entry.getKey());
         thisTag.method_10569("value", entry.getIntValue());
         decayList.add(thisTag);
      }

      pCompound.method_10566("result", decayList);
      return pCompound;
   }

   public Object2IntMap<UUID> getTickMap() {
      return this.tickMap;
   }

   public void clear() {
      this.tickMap.clear();
      this.method_80();
   }
}
