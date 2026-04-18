package net.caffeinemc.mods.lithium.common.ai.brain;

import net.caffeinemc.mods.lithium.mixin.ai.useless_sensors.BrainAccessor;
import net.caffeinemc.mods.lithium.mixin.ai.useless_sensors.SensorAccessor;
import net.minecraft.class_1309;
import net.minecraft.class_3218;
import net.minecraft.class_4095;
import net.minecraft.class_4148;
import net.minecraft.class_4149;

public class SensorHelper {
   public static void disableSensor(class_1309 brainedEntity, class_4149<?> sensorType) {
      if (!brainedEntity.method_37908().method_8608()) {
         class_4095<?> brain = brainedEntity.method_18868();
         class_4148<?> sensor = (class_4148<?>)((BrainAccessor)brain).getSensors().get(sensorType);
         if (sensor instanceof SensorAccessor sensorAccessor) {
            long lastSenseTime = sensorAccessor.getLastSenseTime();
            int senseInterval = sensorAccessor.getSenseInterval();
            long maxMultipleOfSenseInterval = Long.MAX_VALUE - Long.MAX_VALUE % senseInterval;
            maxMultipleOfSenseInterval -= senseInterval;
            maxMultipleOfSenseInterval += lastSenseTime;
            sensorAccessor.setLastSenseTime(maxMultipleOfSenseInterval);
         }
      }
   }

   public static <T extends class_1309, U extends class_4148<T>> void enableSensor(T brainedEntity, class_4149<U> sensorType) {
      enableSensor(brainedEntity, sensorType, false);
   }

   public static <T extends class_1309, U extends class_4148<T>> void enableSensor(T brainedEntity, class_4149<U> sensorType, boolean extraTick) {
      if (!brainedEntity.method_37908().method_8608()) {
         class_4095<?> brain = brainedEntity.method_18868();
         U sensor = (U)((BrainAccessor)brain).getSensors().get(sensorType);
         if (sensor instanceof SensorAccessor sensorAccessor) {
            long lastSenseTime = sensorAccessor.getLastSenseTime();
            int senseInterval = sensorAccessor.getSenseInterval();
            if (lastSenseTime > senseInterval) {
               lastSenseTime %= senseInterval;
               if (extraTick) {
                  ((SensorAccessor)sensor).setLastSenseTime(0L);
                  sensor.method_19100((class_3218)brainedEntity.method_37908(), brainedEntity);
               }
            }

            sensorAccessor.setLastSenseTime(lastSenseTime);
         }
      }
   }
}
