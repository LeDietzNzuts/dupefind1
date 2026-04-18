package ewewukek.musketmod;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.class_2561;
import net.minecraft.class_437;

public class ClothConfigScreen {
   public static class_437 build(class_437 parent) {
      ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(class_2561.method_43471("musketmod.options.title"));
      ConfigEntryBuilder entryBuilder = builder.entryBuilder();
      ConfigCategory commonCategory = builder.getOrCreateCategory(class_2561.method_43471("musketmod.options.common"));
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_travel_distance"), Config.bulletMaxDistance)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.blocks")})
            .setSaveConsumer(value -> Config.bulletMaxDistance = value)
            .setMin(0.0F)
            .setDefaultValue(256.0F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.loading_stages_number"), Config.loadingStages)
            .setSaveConsumer(value -> Config.loadingStages = value)
            .setMin(2)
            .setDefaultValue(3)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.loading_stage_duration"), Config.loadingStageDuration)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.seconds")})
            .setSaveConsumer(value -> Config.loadingStageDuration = value)
            .setMin(0.25F)
            .setDefaultValue(0.5F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.pvp_damage_multiplier"), Config.pvpDamageMultiplier)
            .setSaveConsumer(value -> Config.pvpDamageMultiplier = value)
            .setMin(0.0F)
            .setDefaultValue(1.0F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.mob_damage_multiplier"), Config.mobDamageMultiplier)
            .setSaveConsumer(value -> Config.mobDamageMultiplier = value)
            .setMin(0.0F)
            .setDefaultValue(0.5F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.pistol_pillager_chance"), Config.pistolPillagerChance)
            .setSaveConsumer(value -> Config.pistolPillagerChance = value)
            .setMin(0.0F)
            .setDefaultValue(0.2F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.musket_skeleton_chance"), Config.musketSkeletonChance)
            .setSaveConsumer(value -> Config.musketSkeletonChance = value)
            .setMin(0.0F)
            .setDefaultValue(0.05F)
            .build()
      );
      commonCategory.addEntry(
         entryBuilder.startBooleanToggle(class_2561.method_43471("musketmod.options.always_aim"), Config.alwaysAim)
            .setSaveConsumer(value -> Config.alwaysAim = value)
            .setDefaultValue(false)
            .build()
      );
      ConfigCategory musketCategory = builder.getOrCreateCategory(class_2561.method_43471("item.musketmod.musket"));
      musketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_std_dev"), Config.musketBulletStdDev)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.degrees")})
            .setSaveConsumer(value -> Config.musketBulletStdDev = value)
            .setMin(0.0F)
            .setDefaultValue(1.0F)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_speed"), Config.musketBulletSpeed)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.blocks_per_second")})
            .setSaveConsumer(value -> Config.musketBulletSpeed = value)
            .setMin(1.0F)
            .setDefaultValue(180.0F)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.damage"), Config.musketDamage)
            .setSaveConsumer(value -> Config.musketDamage = value)
            .setMin(0.5F)
            .setDefaultValue(16.0F)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.headshot_damage_multiplier"), Config.headshotDamageMultiplier)
            .setSaveConsumer(value -> Config.headshotDamageMultiplier = value)
            .setMin(1.0F)
            .setDefaultValue(1.3F)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.bayonet_damage"), Config.bayonetDamage)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.bayonetDamage = value)
            .setMin(1)
            .setDefaultValue(5)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bayonet_attack_speed"), Config.bayonetSpeed)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.bayonetSpeed = value)
            .setMin(0.0F)
            .setDefaultValue(2.0F)
            .build()
      );
      musketCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.durability"), Config.musketDurability)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.musketDurability = value)
            .setMin(1)
            .setDefaultValue(250)
            .build()
      );
      ConfigCategory scopedMusketCategory = builder.getOrCreateCategory(class_2561.method_43471("item.musketmod.musket_with_scope"));
      scopedMusketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_std_dev"), Config.scopedMusketBulletStdDev)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.degrees")})
            .setSaveConsumer(value -> Config.scopedMusketBulletStdDev = value)
            .setMin(0.0F)
            .setDefaultValue(0.2F)
            .build()
      );
      scopedMusketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.scope_zoom_factor"), Config.scopeZoom)
            .setSaveConsumer(value -> Config.scopeZoom = value)
            .setMin(1.0F)
            .setMax(10.0F)
            .setDefaultValue(3.0F)
            .build()
      );
      scopedMusketCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_gravity_multiplier"), Config.bulletGravityMultiplier)
            .setSaveConsumer(value -> Config.bulletGravityMultiplier = value)
            .setMin(0.0F)
            .setMax(1.0F)
            .setDefaultValue(0.5F)
            .build()
      );
      scopedMusketCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.durability"), Config.scopedMusketDurability)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.scopedMusketDurability = value)
            .setMin(1)
            .setDefaultValue(150)
            .build()
      );
      ConfigCategory blunderbussCategory = builder.getOrCreateCategory(class_2561.method_43471("item.musketmod.blunderbuss"));
      blunderbussCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.pellet_std_dev"), Config.blunderbussBulletStdDev)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.degrees")})
            .setSaveConsumer(value -> Config.blunderbussBulletStdDev = value)
            .setMin(0.0F)
            .setDefaultValue(2.5F)
            .build()
      );
      blunderbussCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_speed"), Config.blunderbussBulletSpeed)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.blocks_per_second")})
            .setSaveConsumer(value -> Config.blunderbussBulletSpeed = value)
            .setMin(1.0F)
            .setDefaultValue(160.0F)
            .build()
      );
      blunderbussCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.damage"), Config.blunderbussDamage)
            .setSaveConsumer(value -> Config.blunderbussDamage = value)
            .setMin(0.5F)
            .setDefaultValue(21.0F)
            .build()
      );
      blunderbussCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.pellet_count"), Config.blunderbussPelletCount)
            .setSaveConsumer(value -> Config.blunderbussPelletCount = value)
            .setMin(1)
            .setDefaultValue(9)
            .build()
      );
      blunderbussCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.durability"), Config.blunderbussDurability)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.blunderbussDurability = value)
            .setMin(1)
            .setDefaultValue(200)
            .build()
      );
      ConfigCategory pistolCategory = builder.getOrCreateCategory(class_2561.method_43471("item.musketmod.pistol"));
      pistolCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_std_dev"), Config.pistolBulletStdDev)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.degrees")})
            .setSaveConsumer(value -> Config.pistolBulletStdDev = value)
            .setMin(0.0F)
            .setDefaultValue(1.5F)
            .build()
      );
      pistolCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_speed"), Config.pistolBulletSpeed)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.blocks_per_second")})
            .setSaveConsumer(value -> Config.pistolBulletSpeed = value)
            .setMin(1.0F)
            .setDefaultValue(140.0F)
            .build()
      );
      pistolCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.damage"), Config.pistolDamage)
            .setSaveConsumer(value -> Config.pistolDamage = value)
            .setMin(0.5F)
            .setDefaultValue(12.0F)
            .build()
      );
      pistolCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.reduction_per_quick_charge_level"), Config.reductionPerQuickChargeLevel)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.seconds")})
            .setSaveConsumer(value -> Config.reductionPerQuickChargeLevel = value)
            .setMin(0.1F)
            .setDefaultValue(0.15F)
            .build()
      );
      pistolCategory.addEntry(
         entryBuilder.startIntField(class_2561.method_43471("musketmod.options.durability"), Config.pistolDurability)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.requires_restart")})
            .setSaveConsumer(value -> Config.pistolDurability = value)
            .setMin(1)
            .setDefaultValue(200)
            .build()
      );
      ConfigCategory dispenserCategory = builder.getOrCreateCategory(class_2561.method_43471("block.minecraft.dispenser"));
      dispenserCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_std_dev"), Config.dispenserBulletStdDev)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.degrees")})
            .setSaveConsumer(value -> Config.dispenserBulletStdDev = value)
            .setMin(0.0F)
            .setDefaultValue(2.0F)
            .build()
      );
      dispenserCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.bullet_speed"), Config.dispenserBulletSpeed)
            .setTooltip(new class_2561[]{class_2561.method_43471("musketmod.options.unit.blocks_per_second")})
            .setSaveConsumer(value -> Config.dispenserBulletSpeed = value)
            .setMin(1.0F)
            .setDefaultValue(120.0F)
            .build()
      );
      dispenserCategory.addEntry(
         entryBuilder.startFloatField(class_2561.method_43471("musketmod.options.damage"), Config.dispenserDamage)
            .setSaveConsumer(value -> Config.dispenserDamage = value)
            .setMin(0.5F)
            .setDefaultValue(10.0F)
            .build()
      );
      builder.setSavingRunnable(() -> Config.save());
      return builder.build();
   }
}
