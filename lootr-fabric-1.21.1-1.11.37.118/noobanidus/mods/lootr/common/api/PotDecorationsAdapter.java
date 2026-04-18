package noobanidus.mods.lootr.common.api;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2487;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_8526;

public record PotDecorationsAdapter(class_1799 back, class_1799 left, class_1799 right, class_1799 front) {
   public static final PotDecorationsAdapter EMPTY = new PotDecorationsAdapter(class_8526.field_44707);
   public static final Codec<PotDecorationsAdapter> CODEC = class_1799.field_24671
      .sizeLimitedListOf(4)
      .xmap(PotDecorationsAdapter::new, PotDecorationsAdapter::ordered);

   public PotDecorationsAdapter(PotDecorationsAdapter decorations) {
      this(decorations.back().method_7972(), decorations.left().method_7972(), decorations.right().method_7972(), decorations.front().method_7972());
   }

   public PotDecorationsAdapter(class_8526 decorations) {
      this(
         decorations.comp_1487().<class_1799>map(class_1799::new).orElse(class_1799.field_8037),
         decorations.comp_1488().<class_1799>map(class_1799::new).orElse(class_1799.field_8037),
         decorations.comp_1489().<class_1799>map(class_1799::new).orElse(class_1799.field_8037),
         decorations.comp_1490().<class_1799>map(class_1799::new).orElse(class_1799.field_8037)
      );
   }

   public PotDecorationsAdapter(List<class_1799> itemStacks) {
      this(itemStacks.get(0), itemStacks.get(1), itemStacks.get(2), itemStacks.get(3));
   }

   public List<class_1799> ordered() {
      return Stream.of(this.back, this.left, this.right, this.front).map(o -> o.method_7960() ? new class_1799(class_1802.field_8621) : o).toList();
   }

   public PotDecorationsAdapter load(class_2487 tag) {
      return !tag.method_10545("decorations")
         ? EMPTY
         : (PotDecorationsAdapter)((Pair)CODEC.decode(class_2509.field_11560, tag.method_10580("decorations")).getOrThrow()).getFirst();
   }

   public class_2487 save(class_2487 tag) {
      if (this != EMPTY) {
         tag.method_10566("decorations", (class_2520)CODEC.encodeStart(class_2509.field_11560, this).getOrThrow());
      }

      return tag;
   }
}
