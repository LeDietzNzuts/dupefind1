package net.caffeinemc.mods.sodium.client.gui.options.storage;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.class_310;
import net.minecraft.class_315;

public class MinecraftOptionsStorage implements OptionStorage<class_315> {
   private final class_310 minecraft = class_310.method_1551();

   public class_315 getData() {
      return this.minecraft.field_1690;
   }

   @Override
   public void save() {
      this.getData().method_1640();
      SodiumClientMod.logger().info("Flushed changes to Minecraft configuration");
   }
}
