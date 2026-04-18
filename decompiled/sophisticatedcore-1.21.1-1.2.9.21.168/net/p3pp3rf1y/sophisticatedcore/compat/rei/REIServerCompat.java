package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessorRegistry;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessorRegistry.Serializer;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;

public class REIServerCompat implements REIServerPlugin {
   public void registerSlotAccessors(SlotAccessorRegistry registry) {
      registry.register(
         class_2960.method_60655("sophisticatedcore", "storage"), slotAccessor -> slotAccessor instanceof SophisticatedSlotAccessor, new Serializer() {
            public SlotAccessor read(class_1703 menu, class_1657 player, class_2487 tag) {
               int slot = tag.method_10550("Slot");
               return new SophisticatedSlotAccessor(menu.method_7611(slot));
            }

            @Nullable
            public class_2487 save(class_1703 menu, class_1657 player, SlotAccessor accessor) {
               if (accessor instanceof SophisticatedSlotAccessor sophisticatedSlotAccessor) {
                  class_2487 tag = new class_2487();
                  tag.method_10569("Slot", sophisticatedSlotAccessor.getSlot().field_7874);
                  return tag;
               } else {
                  throw new IllegalArgumentException("Cannot save non-sophisticated slot accessor!");
               }
            }
         }
      );
   }
}
