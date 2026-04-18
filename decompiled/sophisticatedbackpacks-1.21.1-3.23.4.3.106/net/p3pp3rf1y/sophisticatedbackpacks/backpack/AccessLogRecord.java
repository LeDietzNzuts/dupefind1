package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

public class AccessLogRecord {
   private final class_2960 backpackItemRegistryName;
   private final UUID backpackUuid;
   private final String playerName;
   private final String backpackName;
   private final int clothColor;
   private final int trimColor;
   private final long accessTime;
   private final int columnsTaken;

   public AccessLogRecord(
      class_2960 backpackItemRegistryName,
      UUID backpackUuid,
      String playerName,
      String backpackName,
      int clothColor,
      int trimColor,
      long accessTime,
      int columnsTaken
   ) {
      this.backpackItemRegistryName = backpackItemRegistryName;
      this.backpackUuid = backpackUuid;
      this.playerName = playerName;
      this.backpackName = backpackName;
      this.clothColor = clothColor;
      this.trimColor = trimColor;
      this.accessTime = accessTime;
      this.columnsTaken = columnsTaken;
   }

   public UUID getBackpackUuid() {
      return this.backpackUuid;
   }

   public String getPlayerName() {
      return this.playerName;
   }

   public String getBackpackName() {
      return this.backpackName;
   }

   public int getClothColor() {
      return this.clothColor;
   }

   public int getTrimColor() {
      return this.trimColor;
   }

   public long getAccessTime() {
      return this.accessTime;
   }

   public int getColumnsTaken() {
      return this.columnsTaken;
   }

   public class_2960 getBackpackItemRegistryName() {
      return this.backpackItemRegistryName;
   }

   public class_2487 serializeToNBT() {
      class_2487 ret = new class_2487();
      ret.method_10582("backpackItemRegistryName", this.backpackItemRegistryName.toString());
      ret.method_25927("backpackUuid", this.backpackUuid);
      ret.method_10582("playerName", this.playerName);
      ret.method_10582("backpackName", this.backpackName);
      ret.method_10569("clothColor", this.clothColor);
      ret.method_10569("trimColor", this.trimColor);
      ret.method_10544("accessTime", this.accessTime);
      ret.method_10569("columnsTaken", this.columnsTaken);
      return ret;
   }

   public static AccessLogRecord deserializeFromNBT(class_2487 nbt) {
      return new AccessLogRecord(
         class_2960.method_60654(nbt.method_10558("backpackItemRegistryName")),
         nbt.method_25926("backpackUuid"),
         nbt.method_10558("playerName"),
         nbt.method_10558("backpackName"),
         nbt.method_10550("clothColor"),
         nbt.method_10550("trimColor"),
         nbt.method_10537("accessTime"),
         nbt.method_10550("columnsTaken")
      );
   }
}
