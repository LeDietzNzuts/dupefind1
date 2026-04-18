package noobanidus.mods.lootr.common.api.data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2512;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_2561.class_2562;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface ILootrInfo {
   Set<Class<?>> WARNED_CLASSES = new HashSet<>();

   @Deprecated
   @Nullable
   LootrBlockType getInfoBlockType();

   @Deprecated
   @Nullable
   ILootrInfo.LootrInfoType getInfoType();

   @Nullable
   ILootrType getInfoNewType();

   default LootFiller getDefaultFiller() {
      ILootrType type = this.getInfoNewType();
      return (LootFiller)(type == null ? DefaultLootFiller.getInstance() : type.getDefaultFiller());
   }

   default boolean canRefresh() {
      ILootrType type = this.getInfoNewType();
      return type == null ? true : type.canRefresh();
   }

   default boolean canDecay() {
      ILootrType type = this.getInfoNewType();
      return type == null ? true : type.canDecay();
   }

   default boolean canBeMarkedUnopened() {
      ILootrType type = this.getInfoNewType();
      return type == null ? true : type.canBeMarkedUnopened();
   }

   default boolean canDropContentsWhenBroken() {
      ILootrType type = this.getInfoNewType();
      return type == null ? true : type.canDropContentsWhenBroken();
   }

   @Deprecated
   @Nullable
   default class_2248 getReplacementBlock() {
      ILootrType type = this.getInfoNewType();
      return type == null ? null : type.getReplacementBlock();
   }

   @Deprecated
   @Nullable
   default class_1299<?> getReplacementEntity() {
      ILootrType type = this.getInfoNewType();
      return type == null ? null : type.getReplacementEntity();
   }

   @Deprecated
   default boolean isEntity() {
      ILootrType type = this.getInfoNewType();
      return type == null ? false : type.isEntity();
   }

   @NotNull
   default class_243 getInfoVec() {
      return class_243.method_24953(this.getInfoPos());
   }

   @NotNull
   UUID getInfoUUID();

   String getInfoKey();

   static String generateInfoKey(UUID id) {
      String idString = id.toString();
      return "lootr/" + idString.charAt(0) + "/" + idString.substring(0, 2) + "/" + idString;
   }

   boolean hasBeenOpened();

   boolean isPhysicallyOpen();

   @NotNull
   class_2338 getInfoPos();

   @Nullable
   class_2561 getInfoDisplayName();

   @NotNull
   class_5321<class_1937> getInfoDimension();

   int getInfoContainerSize();

   @Nullable
   class_2371<class_1799> getInfoReferenceInventory();

   default void setInfoReferenceInventory(class_2371<class_1799> reference) {
      throw new NotImplementedException(this + " does not declare setInfoReferenceInventory.");
   }

   default boolean canPlayerOpen(class_3222 player) {
      return true;
   }

   default void informPlayerCannotOpen(class_3222 player) {
   }

   boolean isInfoReferenceInventory();

   default boolean isInfoReferenceInventoryInternal(boolean isReferenceInventory) {
      if (isReferenceInventory && this.getInfoLootTable() != null) {
         LootrAPI.LOG
            .error(
               "Lootr container {} at {} in {} has both a loot table and a custom inventory. This is not supported and may cause issues.",
               this,
               this.getInfoPos(),
               this.getInfoDimension()
            );
      }

      return isReferenceInventory;
   }

   @Nullable
   class_5321<class_52> getInfoLootTable();

   long getInfoLootSeed();

   default class_1937 getInfoLevel() {
      if (!WARNED_CLASSES.contains(this.getClass())) {
         LootrAPI.LOG.error("Class {} does not implement `getInfoLevel`! Falling back on `getDefaultLevel`.", this.getClass().getName());
         WARNED_CLASSES.add(this.getClass());
      }

      return this.getDefaultLevel();
   }

   @Nullable
   default class_1937 getDefaultLevel() {
      MinecraftServer server = LootrAPI.getServer();
      return server == null ? null : server.method_3847(this.getInfoDimension());
   }

   @Nullable
   default class_1263 getInfoContainer() {
      if (!(this.getInfoLevel() instanceof class_3218 level && !level.method_8608())) {
         return null;
      } else if (this.getInfoNewType() != null) {
         return this.getInfoNewType().getContainer(this, level);
      } else if (level.method_8321(this.getInfoPos()) instanceof class_1263 container) {
         return container;
      } else if (level.method_14190(this.getInfoUUID()) instanceof class_1263 container) {
         return container;
      } else {
         LootrAPI.LOG.warn("Unable to guess container type for LootrInfo with key '{}'", this.getInfoKey());
         return null;
      }
   }

   default class_2371<class_1799> buildInitialInventory() {
      return class_2371.method_10213(this.getInfoContainerSize(), class_1799.field_8037);
   }

   default void saveInfoToTag(class_2487 tag, class_7874 provider) {
      if (this.getInfoType() != null) {
         tag.method_10569("type", this.getInfoType().ordinal());
      }

      if (this.getInfoNewType() != null) {
         tag.method_10582("newType", this.getInfoNewType().getName());
      }

      if (this.getInfoBlockType() != null) {
         tag.method_10569("blockType", this.getInfoBlockType().ordinal());
      }

      tag.method_10566("position", class_2512.method_10692(this.getInfoPos()));
      tag.method_10582("key", this.getInfoKey());
      tag.method_10582("dimension", this.getInfoDimension().method_29177().toString());
      tag.method_25927("uuid", this.getInfoUUID());
      tag.method_10569("size", this.getInfoContainerSize());
      if (this.getInfoLootTable() != null) {
         tag.method_10582("table", this.getInfoLootTable().method_29177().toString());
         tag.method_10544("seed", this.getInfoLootSeed());
      }

      if (this.getInfoDisplayName() != null) {
         tag.method_10582("name", class_2562.method_10867(this.getInfoDisplayName(), provider));
      }

      if (this.isInfoReferenceInventory()) {
         if (this.getInfoReferenceInventory() != null) {
            tag.method_10569("referenceSize", this.getInfoReferenceInventory().size());
            tag.method_10566("reference", class_1262.method_5427(new class_2487(), this.getInfoReferenceInventory(), true, provider));
         } else {
            LootrAPI.LOG
               .error(
                  "Info at {} with ID {} is marked as reference inventory but getInfoReferenceInventory() returns null. This may cause issues.",
                  this.getInfoPos(),
                  this.getInfoKey()
               );
         }
      }
   }

   static ILootrInfo loadInfoFromTag(class_2487 tag, class_7874 provider) {
      ILootrType type = null;
      if (tag.method_10573("newType", 8)) {
         type = LootrAPI.getType(tag.method_10558("newType"));
         if (type == null) {
            LootrAPI.LOG.error("Couldn't find LootrType '{}' when loading LootrInfo from tag: {}", tag.method_10558("newType"), tag);
            throw new IllegalStateException("Couldn't find LootrType '" + tag.method_10558("newType") + "' when loading LootrInfo from tag: " + tag);
         }
      }

      if (type == null) {
         if (tag.method_10573("blockType", 3)) {
            LootrBlockType oldType = LootrBlockType.values()[tag.method_10550("blockType")];
            type = BuiltInLootrTypes.fromLegacy(oldType);
         } else if (tag.method_10573("type", 3)) {
            type = BuiltInLootrTypes.CHEST;
         } else if (tag.method_10545("entity") && tag.method_10577("entity")) {
            type = BuiltInLootrTypes.MINECART;
         }
      }

      class_2338 pos = class_2512.method_10691(tag, "position").orElse(class_2338.field_10980);
      UUID uuid = tag.method_25926("uuid");
      class_5321<class_1937> dimension = class_5321.method_29179(class_7924.field_41223, class_2960.method_60654(tag.method_10558("dimension")));
      int size = tag.method_10550("size");
      class_2561 name = null;
      if (tag.method_10545("name")) {
         name = class_2562.method_10877(tag.method_10558("name"), provider);
      }

      class_2371<class_1799> reference = null;
      if (tag.method_10545("reference") && tag.method_10545("referenceSize")) {
         reference = class_2371.method_10213(tag.method_10550("referenceSize"), class_1799.field_8037);
         class_1262.method_5429(tag.method_10562("reference"), reference, provider);
         type = BuiltInLootrTypes.INVENTORY;
      }

      class_5321<class_52> table = null;
      long seed = -1L;
      if (tag.method_10545("table")) {
         table = class_5321.method_29179(class_7924.field_50079, class_2960.method_60654(tag.method_10558("table")));
         seed = tag.method_10537("seed");
      }

      if (type == null) {
         LootrAPI.LOG.error("Couldn't determine LootrType when loading LootrInfo from tag, guessing chest: {}", tag);
         type = BuiltInLootrTypes.CHEST;
      }

      return new BaseLootrInfo(null, null, type, uuid, generateInfoKey(uuid), pos, name, dimension, size, reference, table, seed);
   }

   @Deprecated
   public static enum LootrInfoType {
      CONTAINER_BLOCK_ENTITY,
      CONTAINER_ENTITY;
   }
}
