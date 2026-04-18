package net.p3pp3rf1y.sophisticatedcore.util;

import net.minecraft.class_1657;

public class XpHelper {
   private static final long RATIO = 1620L;

   private XpHelper() {
   }

   public static float liquidToExperience(long liquid) {
      return (float)liquid / 1620.0F;
   }

   public static long experienceToLiquid(float xp) {
      return (long)(xp * 1620.0F);
   }

   public static int getExperienceForLevel(int level) {
      if (level == 0) {
         return 0;
      } else if (level > 0 && level < 16) {
         return level * (12 + level * 2) / 2;
      } else {
         return level > 15 && level < 31 ? (level - 15) * (69 + (level - 15) * 5) / 2 + 315 : (level - 30) * (215 + (level - 30) * 9) / 2 + 1395;
      }
   }

   public static int getExperienceLimitOnLevel(int level) {
      if (level >= 30) {
         return 112 + (level - 30) * 9;
      } else {
         return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
      }
   }

   public static int getLevelForExperience(int experience) {
      int i = 0;

      while (getExperienceForLevel(i) <= experience) {
         i++;
      }

      return i - 1;
   }

   public static double getLevelsForExperience(int experience) {
      int baseLevel = getLevelForExperience(experience);
      int remainingExperience = experience - getExperienceForLevel(baseLevel);
      return baseLevel + (double)remainingExperience / getExperienceLimitOnLevel(baseLevel);
   }

   public static int getPlayerTotalExperience(class_1657 player) {
      int currentLevelPoints = getExperienceForLevel(player.field_7520);
      int partialLevelPoints = (int)(player.field_7510 * player.method_7349());
      return currentLevelPoints + partialLevelPoints;
   }
}
