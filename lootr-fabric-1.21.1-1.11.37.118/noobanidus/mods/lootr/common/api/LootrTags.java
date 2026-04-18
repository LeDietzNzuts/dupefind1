package noobanidus.mods.lootr.common.api;

import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_3195;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public class LootrTags {
   public static class BlockEntity extends LootrTags {
      public static class_6862<class_2591<?>> LOOTR_OBJECT = tag("object");
      public static class_6862<class_2591<?>> TRAPPED = tag("trapped");
      public static class_6862<class_2591<?>> CUSTOM_INELIGIBLE = tag("custom_ineligible");
      public static class_6862<class_2591<?>> CONVERT_BLACKLIST = tag("convert/blacklist");

      public static boolean isTagged(class_2586 blockEntity, class_6862<class_2591<?>> tag) {
         return blockEntity.method_11017().method_53254().method_40220(tag);
      }

      static class_6862<class_2591<?>> tag(String name) {
         return class_6862.method_40092(class_7924.field_41255, LootrAPI.rl(name));
      }
   }

   public static class Blocks extends LootrTags {
      public static final class_6862<class_2248> CONVERT_CHESTS = tag("convert/chests");
      public static final class_6862<class_2248> CONVERT_TRAPPED_CHESTS = tag("convert/trapped_chests");
      public static final class_6862<class_2248> CONVERT_SHULKERS = tag("convert/shulkers");
      public static final class_6862<class_2248> CONVERT_BARRELS = tag("convert/barrels");
      public static final class_6862<class_2248> CONVERT_GRAVELS = tag("convert/gravels");
      public static final class_6862<class_2248> CONVERT_SANDS = tag("convert/sands");
      public static final class_6862<class_2248> CONVERT_POTS = tag("convert/pots");
      public static final class_6862<class_2248> CONVERT_BLOCK = tag("convert/blocks");
      public static final class_6862<class_2248> CONVERT_BLACKLIST = tag("convert/blacklist");
      public static final class_6862<class_2248> CHESTS = tag("chests");
      public static final class_6862<class_2248> TRAPPED_CHESTS = tag("trapped_chests");
      public static final class_6862<class_2248> SHULKERS = tag("shulkers");
      public static final class_6862<class_2248> BARRELS = tag("barrels");
      public static final class_6862<class_2248> GRAVELS = tag("gravels");
      public static final class_6862<class_2248> SANDS = tag("sands");
      public static final class_6862<class_2248> POTS = tag("pots");
      public static final class_6862<class_2248> CONTAINERS = tag("containers");
      public static final class_6862<class_2248> CUSTOM_ELIGIBLE = tag("convert/custom_eligible");
      public static final class_6862<class_2248> CATS_CAN_BLOCK = tag("cats_can_block");
      public static final class_6862<class_2248> NON_BLOCKING = tag("non_blocking");
      public static final class_6862<class_2248> INTERACT_WHITELIST_BLOCKS = tag("interact_whitelist_blocks");
      public static final class_6862<class_2248> INTERACT_WHITELIST = tag("ftbchunks", "interact_whitelist");

      static class_6862<class_2248> tag(String name) {
         return class_6862.method_40092(class_7924.field_41254, LootrAPI.rl(name));
      }

      static class_6862<class_2248> tag(String namespace, String name) {
         return class_6862.method_40092(class_7924.field_41254, LootrAPI.rl(namespace, name));
      }
   }

   public static class Entity extends LootrTags {
      public static class_6862<class_1299<?>> CONVERT_CARTS = tag("convert/minecarts");
      public static class_6862<class_1299<?>> CONVERT_ITEM_FRAMES = tag("convert/item_frames");
      public static class_6862<class_1299<?>> CONVERT_ENTITIES = tag("convert/entities");
      public static class_6862<class_1299<?>> CONVERT_BLACKLIST = tag("blacklist");
      public static class_6862<class_1299<?>> MINECARTS = tag("minecarts");
      public static class_6862<class_1299<?>> ITEM_FRAMES = tag("item_frames");
      public static class_6862<class_1299<?>> CONTAINERS = tag("containers");

      static class_6862<class_1299<?>> tag(String name) {
         return class_6862.method_40092(class_7924.field_41266, LootrAPI.rl(name));
      }
   }

   public static class Items extends LootrTags {
      public static class_6862<class_1792> CHESTS = tag("chests");
      public static class_6862<class_1792> TRAPPED_CHESTS = tag("trapped_chests");
      public static class_6862<class_1792> SHULKERS = tag("shulkers");
      public static class_6862<class_1792> BARRELS = tag("barrels");
      public static class_6862<class_1792> GRAVELS = tag("gravels");
      public static class_6862<class_1792> SANDS = tag("sands");
      public static class_6862<class_1792> POTS = tag("pots");
      public static class_6862<class_1792> CONTAINERS = tag("containers");
      public static class_6862<class_1792> ITEM_FRAME_CONVERT_BLACKLIST = tag("convert/item_frame_blacklist");

      static class_6862<class_1792> tag(String name) {
         return class_6862.method_40092(class_7924.field_41197, LootrAPI.rl(name));
      }
   }

   public static class Structure extends LootrTags {
      public static class_6862<class_3195> STRUCTURE_BLACKLIST = tag("blacklist");
      public static class_6862<class_3195> STRUCTURE_WHITELIST = tag("whitelist");
      public static class_6862<class_3195> REFRESH_STRUCTURES = tag("refresh");
      public static class_6862<class_3195> DECAY_STRUCTURES = tag("decay");

      static class_6862<class_3195> tag(String name) {
         return class_6862.method_40092(class_7924.field_41246, LootrAPI.rl(name));
      }
   }
}
