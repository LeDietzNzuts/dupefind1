package noobanidus.mods.lootr.common.api.data;

import net.minecraft.class_2246;
import net.minecraft.class_2248;

@Deprecated
public enum LootrBlockType {
   CHEST(class_2246.field_10034),
   TRAPPED_CHEST(class_2246.field_10380),
   BARREL(class_2246.field_16328),
   SHULKER(class_2246.field_10603),
   INVENTORY(class_2246.field_10034),
   ENTITY(class_2246.field_10124);

   private final class_2248 block;

   private LootrBlockType(class_2248 block) {
      this.block = block;
   }

   public class_2248 getBlock() {
      return this.block;
   }
}
