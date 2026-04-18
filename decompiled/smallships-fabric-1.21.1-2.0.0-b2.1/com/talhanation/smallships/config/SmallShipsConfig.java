package com.talhanation.smallships.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.talhanation.smallships.SmallShipsMod;
import com.talhanation.smallships.config.fabric.SmallShipsConfigImpl;
import com.talhanation.smallships.world.entity.ship.Ship;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.config.IConfigSpec;

public class SmallShipsConfig {
   public static final ForgeConfigSpec COMMON_SPEC;
   public static final ForgeConfigSpec CLIENT_SPEC;
   public static int CLIENT_SCHEMATIC_VERSION = 2;
   public static int COMMON_SCHEMATIC_VERSION = 5;
   private static final List<Consumer<SmallShipsConfig.ModConfigWrapper>> commonSchematicUpdater = new ArrayList<>();
   private static final List<Consumer<SmallShipsConfig.ModConfigWrapper>> clientSchematicUpdater = new ArrayList<>();

   @ExpectPlatform
   @Transformed
   public static void registerConfigs(String modId, SmallShipsConfig.ModConfigWrapper.Type type, IConfigSpec<?> spec) {
      SmallShipsConfigImpl.registerConfigs(modId, type, spec);
   }

   private static void setupCommonConfig(Builder builder) {
      ArrayList<String> MOUNT_BLACKLIST = new ArrayList<>(
         Arrays.asList(
            "minecraft:ender_dragon",
            "minecraft:wither",
            "minecraft:wither",
            "minecraft:ghast",
            "minecraft:warden",
            "minecraft:ravager",
            "alexmobs:cachalot_whale"
         )
      );
      builder.comment(" This holds the schematic version for internal purposes. DO NOT TOUCH!");
      SmallShipsConfig.Common.schematicVersion = builder.define("schematicVersion", COMMON_SCHEMATIC_VERSION);
      builder.comment(" This category holds configs that define ship behaviour.");
      builder.push("Ship");
      builder.comment("This category holds configs that define general ship behaviour.");
      builder.push("General");
      builder.comment("The cool-down for sails when increasing or decreasing sail state.");
      SmallShipsConfig.Common.shipGeneralSailCooldown = builder.defineInRange("shipGeneralSailCooldown", 30, 0, 1000);
      builder.comment("The damage that is delivered to entities on collision with a cruising ship. Set 0 to disable feature.");
      SmallShipsConfig.Common.shipGeneralCollisionDamage = builder.defineInRange("shipGeneralCollisionDamage", 7.5, 0.0, 100.0);
      builder.comment("Should entities be pushed on collision with a cruising ship?");
      SmallShipsConfig.Common.shipGeneralCollisionKnockBack = builder.define("shipGeneralCollisionKnockBack", true);
      builder.comment("Should the ship item be dropped when the ship is fully damaged?");
      SmallShipsConfig.Common.shipGeneralDoItemDrop = builder.define("shipGeneralDoItemDrop", true);
      builder.comment("General speed modifiers for ships.");
      builder.push("Modifier");
      builder.comment("Maximum speed penalty for a filled container in percent.");
      SmallShipsConfig.Common.shipGeneralContainerModifier = builder.defineInRange("shipGeneralContainerModifier", 10.0, -500.0, 500.0);
      builder.comment("Speed penalty per cannon in percent.");
      SmallShipsConfig.Common.shipGeneralCannonModifier = builder.defineInRange("shipGeneralCannonModifier", 2.5, -500.0, 500.0);
      builder.comment("Speed bonus for a paddle ship while paddling in percent.");
      SmallShipsConfig.Common.shipGeneralPaddlingModifier = builder.defineInRange("shipGeneralPaddlingModifier", 35.0, -500.0, 500.0);
      builder.comment("Maximum speed bonus and penalty depending on the ship biome type in percent.");
      SmallShipsConfig.Common.shipGeneralBiomeModifier = builder.defineInRange("shipGeneralBiomeModifier", 20.0, 0.0, 500.0);
      builder.comment("Damage reduction per shield in percent.");
      SmallShipsConfig.Common.shipGeneralShieldDamageReduction = builder.defineInRange("shipGeneralShieldDamageReduction", 3.0, -500.0, 500.0);
      builder.comment("Time in minutes in which sunken ships will despawn.");
      SmallShipsConfig.Common.shipGeneralDespawnTimeSunken = builder.defineInRange("shipGeneralDespawnTimeSunken", 15.0, 0.0, 600.0);
      builder.comment("Entities in this list won't be able to mount a ship, for example: [\"minecraft:creeper\", \"minecraft:sheep\", ...]");
      SmallShipsConfig.Common.mountBlackList = builder.define("mountBlackList", MOUNT_BLACKLIST);
      builder.comment("Amount of damage a cannonball does on hit.");
      SmallShipsConfig.Common.shipGeneralCannonDamage = builder.defineInRange("shipGeneralCannonDamage", 25.0, 0.0, 100.0);
      builder.comment("Amount of destruction a cannonball does when hit the ground.");
      SmallShipsConfig.Common.shipGeneralCannonDestruction = builder.defineInRange("shipGeneralCannonDestruction", 1.0, 0.0, 100.0);
      builder.pop();
      builder.comment("This category holds configs that define behaviour of fleeing water animals.");
      builder.push("Fleeing Water Animals");
      SmallShipsConfig.Common.waterAnimalFleeRadius = builder.defineInRange("waterAnimalFleeRadius", 15.0, 0.0, 100.0);
      SmallShipsConfig.Common.waterAnimalFleeSpeed = builder.defineInRange("waterAnimalFleeSpeed", 1.5, 0.0, 100.0);
      SmallShipsConfig.Common.waterAnimalFleeDistance = builder.defineInRange("waterAnimalFleeDistance", 10.0, 0.0, 100.0);
      builder.pop();
      builder.pop();
      builder.push("Cog");
      builder.comment("Default attributes for the Cog. Speed in km/h, Health in default mc health points");
      builder.push("Attributes");
      SmallShipsConfig.Common.shipAttributeCogMaxHealth = builder.defineInRange("shipAttributeCogMaxHealth", 300.0, 1.0, 10000.0);
      SmallShipsConfig.Common.shipAttributeCogMaxSpeed = builder.defineInRange("shipAttributeCogMaxSpeed", 30.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeCogMaxReverseSpeed = builder.defineInRange("shipAttributeCogMaxReverseSpeed", 0.1, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeCogMaxRotationSpeed = builder.defineInRange("shipAttributeCogMaxRotationSpeed", 4.5, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeCogAcceleration = builder.defineInRange("shipAttributeCogAcceleration", 0.015, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeCogRotationAcceleration = builder.defineInRange("shipAttributeCogRotationAcceleration", 0.7, 0.0, 100.0);
      builder.pop();
      builder.comment("Default configs for the container of the Cog.");
      builder.push("Container");
      builder.comment("Set container size for the Cog (value must be divisible by 9 and bigger than 0).");
      SmallShipsConfig.Common.shipContainerCogContainerSize = builder.define(
         "shipContainerCogContainerSize", 108, e -> e instanceof Integer i && i % 9 == 0 && i > 0
      );
      builder.pop();
      builder.comment("Cog specific speed modifiers.");
      builder.push("Modifier");
      builder.comment("Specify biome type for the Cog. Can be NONE, COLD, NEUTRAL, or WARM");
      SmallShipsConfig.Common.shipModifierCogBiome = builder.defineEnum("shipModifierCogBiome", Ship.BiomeModifierType.COLD);
      builder.pop();
      builder.pop();
      builder.push("Brigg");
      builder.comment("Default attributes for the Brigg. Speed in km/h, Health in default mc health points");
      builder.push("Attributes");
      SmallShipsConfig.Common.shipAttributeBriggMaxHealth = builder.defineInRange("shipAttributeBriggMaxHealth", 450.0, 0.0, 10000.0);
      SmallShipsConfig.Common.shipAttributeBriggMaxSpeed = builder.defineInRange("shipAttributeBriggMaxSpeed", 35.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeBriggMaxReverseSpeed = builder.defineInRange("shipAttributeBriggMaxReverseSpeed", 0.1, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeBriggMaxRotationSpeed = builder.defineInRange("shipAttributeBriggMaxRotationSpeed", 4.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeBriggAcceleration = builder.defineInRange("shipAttributeBriggAcceleration", 0.015, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeBriggRotationAcceleration = builder.defineInRange("shipAttributeBriggRotationAcceleration", 0.55, 0.0, 100.0);
      builder.pop();
      builder.comment("Default configs for the container of the Brigg.");
      builder.push("Container");
      builder.comment("Set container size for the Brigg (value must be divisible by 9 and bigger than 0).");
      SmallShipsConfig.Common.shipContainerBriggContainerSize = builder.define(
         "shipContainerBriggContainerSize", 162, e -> e instanceof Integer i && i % 9 == 0 && i > 0
      );
      builder.pop();
      builder.comment("Brigg specific speed modifiers.");
      builder.push("Modifier");
      builder.comment("Specify biome type for the Brigg. Can be NONE, COLD, NEUTRAL, or WARM");
      SmallShipsConfig.Common.shipModifierBriggBiome = builder.defineEnum("shipModifierBriggBiome", Ship.BiomeModifierType.COLD);
      builder.pop();
      builder.pop();
      builder.push("Galley");
      builder.comment("Default attributes for the Galley. Speed in km/h, Health in default mc health points");
      builder.push("Attributes");
      SmallShipsConfig.Common.shipAttributeGalleyMaxHealth = builder.defineInRange("shipAttributeGalleyMaxHealth", 200.0, 0.0, 10000.0);
      SmallShipsConfig.Common.shipAttributeGalleyMaxSpeed = builder.defineInRange("shipAttributeGalleyMaxSpeed", 30.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeGalleyMaxReverseSpeed = builder.defineInRange("shipAttributeGalleyMaxReverseSpeed", 0.1, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeGalleyMaxRotationSpeed = builder.defineInRange("shipAttributeGalleyMaxRotationSpeed", 5.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeGalleyAcceleration = builder.defineInRange("shipAttributeGalleyAcceleration", 0.015, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeGalleyRotationAcceleration = builder.defineInRange("shipAttributeGalleyRotationAcceleration", 1.0, 0.0, 100.0);
      builder.pop();
      builder.comment("Default configs for the container of the Galley.");
      builder.push("Container");
      builder.comment("Set container size for the Galley (value must be divisible by 9 and bigger than 0).");
      SmallShipsConfig.Common.shipContainerGalleyContainerSize = builder.define(
         "shipContainerGalleyContainerSize", 54, e -> e instanceof Integer i && i % 9 == 0 && i > 0
      );
      builder.pop();
      builder.comment("Galley specific speed modifiers.");
      builder.push("Modifier");
      builder.comment("Specify biome type for the Galley. Can be NONE, COLD, NEUTRAL, or WARM");
      SmallShipsConfig.Common.shipModifierGalleyBiome = builder.defineEnum("shipModifierGalleyBiome", Ship.BiomeModifierType.WARM);
      builder.pop();
      builder.pop();
      builder.push("Drakkar");
      builder.comment("Default attributes for the Drakkar. Speed in km/h, Health in default mc health points");
      builder.push("Attributes");
      SmallShipsConfig.Common.shipAttributeDrakkarMaxHealth = builder.defineInRange("shipAttributeDrakkarMaxHealth", 200.0, 0.0, 10000.0);
      SmallShipsConfig.Common.shipAttributeDrakkarMaxSpeed = builder.defineInRange("shipAttributeDrakkarMaxSpeed", 30.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeDrakkarMaxReverseSpeed = builder.defineInRange("shipAttributeDrakkarMaxReverseSpeed", 0.1, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeDrakkarMaxRotationSpeed = builder.defineInRange("shipAttributeDrakkarMaxRotationSpeed", 5.0, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeDrakkarAcceleration = builder.defineInRange("shipAttributeDrakkarAcceleration", 0.015, 0.0, 100.0);
      SmallShipsConfig.Common.shipAttributeDrakkarRotationAcceleration = builder.defineInRange("shipAttributeDrakkarRotationAcceleration", 1.0, 0.0, 100.0);
      builder.pop();
      builder.comment("Default configs for the container of the Drakkar.");
      builder.push("Container");
      builder.comment("Set container size for the Drakkar (value must be divisible by 9 and bigger than 0).");
      SmallShipsConfig.Common.shipContainerDrakkarContainerSize = builder.define(
         "shipContainerDrakkarContainerSize", 54, e -> e instanceof Integer i && i % 9 == 0 && i > 0
      );
      builder.pop();
      builder.comment("Drakkar specific speed modifiers.");
      builder.push("Modifier");
      builder.comment("Specify biome type for the Drakkar. Can be NONE, COLD, NEUTRAL, or WARM");
      SmallShipsConfig.Common.shipModifierDrakkarBiome = builder.defineEnum("shipModifierDrakkarBiome", Ship.BiomeModifierType.COLD);
      builder.pop();
      builder.pop();
      builder.pop();
   }

   private static void setupClientConfig(Builder builder) {
      builder.comment(" This holds the schematic version for internal purposes. DO NOT TOUCH!");
      SmallShipsConfig.Client.schematicVersion = builder.define("schematicVersion", CLIENT_SCHEMATIC_VERSION);
      builder.comment(" This category holds configs that define ship behaviour.");
      builder.push("Ship");
      builder.comment("Set the speed indication: 0 = km/h, 1 = m/s, 2 = knots, 3 = mph");
      SmallShipsConfig.Client.shipModSpeedUnit = builder.define("shipModSpeedUnit", 0);
      builder.comment("This category holds configs that define general ship behaviour.");
      builder.push("General");
      builder.comment("General camera settings for ships.");
      builder.push("Camera");
      builder.comment("Zoom camera settings for third person view in ships.");
      builder.push("Zoom");
      builder.comment("Generally enable the zooming feature.");
      SmallShipsConfig.Client.shipGeneralCameraZoomEnable = builder.define("shipGeneralCameraZoomEnable", true);
      builder.comment("Set maximum distance of zoom (value must be smaller than or equal to 50.0).");
      SmallShipsConfig.Client.shipGeneralCameraZoomMax = builder.defineInRange("shipGeneralCameraZoomMax", 20.0, 1.0, 50.0);
      builder.comment("Set minimum distance of zoom (value must be bigger than or equal to 1.0).");
      SmallShipsConfig.Client.shipGeneralCameraZoomMin = builder.defineInRange("shipGeneralCameraZoomMin", 5.0, 1.0, 50.0);
      builder.pop();
      builder.comment("Automatically enable third person camera when entering a ship.");
      SmallShipsConfig.Client.shipGeneralCameraAutoThirdPerson = builder.define("shipGeneralCameraAutoThirdPerson", true);
      builder.pop();
      builder.pop();
      builder.pop();
      builder.comment(" This category holds configs that define general mod settings.");
      builder.push("General");
      builder.comment("Enable smallships creative tab in the creative inventory (only takes effect after restart).");
      SmallShipsConfig.Common.smallshipsItemGroupEnable = builder.define("smallshipsItemGroupEnable", false);
      builder.pop();
   }

   public static boolean updateConfig(SmallShipsConfig.ModConfigWrapper config) {
      int oldSchematicVersion = getSchematicVersion(config);

      boolean hasBeenUpdated = switch (config.getType()) {
         case COMMON -> updateConfig(config, commonSchematicUpdater);
         case CLIENT -> updateConfig(config, clientSchematicUpdater);
         case SERVER -> false;
      };
      int newSchematicVersion = getSchematicVersion(config);
      if (hasBeenUpdated) {
         SmallShipsMod.LOGGER
            .warn("Updated config values of {} from schematic version {} to {}!", new Object[]{config.getFileName(), oldSchematicVersion, newSchematicVersion});
      }

      return hasBeenUpdated;
   }

   private static boolean updateConfig(SmallShipsConfig.ModConfigWrapper config, List<Consumer<SmallShipsConfig.ModConfigWrapper>> schematicUpdater) {
      if (getSchematicVersion(config) >= schematicUpdater.size() + 1) {
         return false;
      } else {
         for (int i = getSchematicVersion(config) - 1; i < schematicUpdater.size(); i++) {
            int j = 0;

            while (true) {
               try {
                  String[] fileNameExtensionPair = config.getFileName().split("\\.");
                  String backupFileName = fileNameExtensionPair[0] + "-sv" + (i + 1) + (j == 0 ? "" : "-" + j) + "." + fileNameExtensionPair[1] + ".bak";
                  Files.copy(config.getFullPath(), config.getFullPath().resolveSibling(backupFileName));
                  SmallShipsMod.LOGGER.info("Backed up previous config version: {}", backupFileName);
                  break;
               } catch (FileAlreadyExistsException var6) {
                  if (++j > 99) {
                     throw new RuntimeException("Delete the " + config.getFileName() + " config files!!!");
                  }
               } catch (IOException var7) {
                  throw new RuntimeException(
                     "Could not create backup of "
                        + config.getFileName()
                        + " during schematicVersion update process, crashing for safety! Please backup the config file if needed and remove it from the config folder. "
                        + var7
                  );
               }
            }

            setSchematicVersion(config, i + 2);
            schematicUpdater.get(i).accept(config);
         }

         return true;
      }
   }

   private static int getSchematicVersion(SmallShipsConfig.ModConfigWrapper config) {
      return config.getConfigData().getInt("schematicVersion");
   }

   private static void setSchematicVersion(SmallShipsConfig.ModConfigWrapper config, int i) {
      config.getConfigData().set("schematicVersion", i);
   }

   private static <T> void resetEntry(SmallShipsConfig.ModConfigWrapper config, ConfigValue<T> value) {
      config.getConfigData().set(value.getPath(), value.getDefault());
   }

   static {
      Builder commonConfigBuilder = new Builder();
      Builder clientConfigBuilder = new Builder();
      setupCommonConfig(commonConfigBuilder);
      setupClientConfig(clientConfigBuilder);
      COMMON_SPEC = commonConfigBuilder.build();
      CLIENT_SPEC = clientConfigBuilder.build();
      commonSchematicUpdater.add(config -> {
         resetEntry(config, SmallShipsConfig.Common.shipGeneralContainerModifier);
         resetEntry(config, SmallShipsConfig.Common.shipGeneralPaddlingModifier);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeBriggMaxSpeed);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeBriggMaxRotationSpeed);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeBriggRotationAcceleration);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeGalleyMaxSpeed);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeCogMaxSpeed);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeCogMaxRotationSpeed);
         resetEntry(config, SmallShipsConfig.Common.shipAttributeCogRotationAcceleration);
      });
   }

   public static class Client {
      public static ConfigValue<Integer> schematicVersion;
      public static BooleanValue shipGeneralCameraZoomEnable;
      public static BooleanValue shipGeneralCameraAutoThirdPerson;
      public static DoubleValue shipGeneralCameraZoomMax;
      public static DoubleValue shipGeneralCameraZoomMin;
      public static ConfigValue<Integer> shipModSpeedUnit;
   }

   public static class Common {
      public static ConfigValue<Integer> schematicVersion;
      public static IntValue shipGeneralSailCooldown;
      public static DoubleValue shipGeneralCollisionDamage;
      public static BooleanValue shipGeneralCollisionKnockBack;
      public static BooleanValue shipGeneralDoItemDrop;
      public static DoubleValue shipGeneralContainerModifier;
      public static DoubleValue shipGeneralCannonModifier;
      public static DoubleValue shipGeneralPaddlingModifier;
      public static DoubleValue shipGeneralBiomeModifier;
      public static ConfigValue<List<String>> mountBlackList;
      public static DoubleValue shipGeneralShieldDamageReduction;
      public static DoubleValue shipGeneralDespawnTimeSunken;
      public static DoubleValue shipGeneralCannonDamage;
      public static DoubleValue shipGeneralCannonDestruction;
      public static DoubleValue shipAttributeCogMaxHealth;
      public static DoubleValue shipAttributeCogMaxSpeed;
      public static DoubleValue shipAttributeCogMaxReverseSpeed;
      public static DoubleValue shipAttributeCogMaxRotationSpeed;
      public static DoubleValue shipAttributeCogAcceleration;
      public static DoubleValue shipAttributeCogRotationAcceleration;
      public static ConfigValue<Integer> shipContainerCogContainerSize;
      public static EnumValue<Ship.BiomeModifierType> shipModifierCogBiome;
      public static DoubleValue shipAttributeBriggMaxHealth;
      public static DoubleValue shipAttributeBriggMaxSpeed;
      public static DoubleValue shipAttributeBriggMaxReverseSpeed;
      public static DoubleValue shipAttributeBriggMaxRotationSpeed;
      public static DoubleValue shipAttributeBriggAcceleration;
      public static DoubleValue shipAttributeBriggRotationAcceleration;
      public static ConfigValue<Integer> shipContainerBriggContainerSize;
      public static EnumValue<Ship.BiomeModifierType> shipModifierBriggBiome;
      public static DoubleValue shipAttributeGalleyMaxHealth;
      public static DoubleValue shipAttributeGalleyMaxSpeed;
      public static DoubleValue shipAttributeGalleyMaxReverseSpeed;
      public static DoubleValue shipAttributeGalleyMaxRotationSpeed;
      public static DoubleValue shipAttributeGalleyAcceleration;
      public static DoubleValue shipAttributeGalleyRotationAcceleration;
      public static ConfigValue<Integer> shipContainerGalleyContainerSize;
      public static EnumValue<Ship.BiomeModifierType> shipModifierGalleyBiome;
      public static DoubleValue shipAttributeDrakkarMaxHealth;
      public static DoubleValue shipAttributeDrakkarMaxSpeed;
      public static DoubleValue shipAttributeDrakkarMaxReverseSpeed;
      public static DoubleValue shipAttributeDrakkarMaxRotationSpeed;
      public static DoubleValue shipAttributeDrakkarAcceleration;
      public static DoubleValue shipAttributeDrakkarRotationAcceleration;
      public static ConfigValue<Integer> shipContainerDrakkarContainerSize;
      public static EnumValue<Ship.BiomeModifierType> shipModifierDrakkarBiome;
      public static DoubleValue waterAnimalFleeRadius;
      public static DoubleValue waterAnimalFleeSpeed;
      public static DoubleValue waterAnimalFleeDistance;
      public static BooleanValue smallshipsItemGroupEnable;
   }

   public static class ModConfigWrapper {
      private final SmallShipsConfig.ModConfigWrapper.Type type;
      private final Path path;
      private final String fileName;
      private final CommentedConfig configData;

      public ModConfigWrapper(String type, Path path, String fileName, CommentedConfig configData) {
         this.path = path;
         this.fileName = fileName;
         this.type = SmallShipsConfig.ModConfigWrapper.Type.valueOf(type);
         this.configData = configData;
      }

      public Path getFullPath() {
         return this.path;
      }

      public String getFileName() {
         return this.fileName;
      }

      public SmallShipsConfig.ModConfigWrapper.Type getType() {
         return this.type;
      }

      public CommentedConfig getConfigData() {
         return this.configData;
      }

      public static enum Type {
         COMMON,
         CLIENT,
         SERVER;
      }
   }
}
