package net.caffeinemc.mods.sodium.client.gui;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.caffeinemc.mods.sodium.client.compatibility.environment.OsUtils;
import net.caffeinemc.mods.sodium.client.compatibility.workarounds.Workarounds;
import net.caffeinemc.mods.sodium.client.gl.arena.staging.MappedStagingBuffer;
import net.caffeinemc.mods.sodium.client.gl.device.RenderDevice;
import net.caffeinemc.mods.sodium.client.gui.options.OptionFlag;
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpact;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.options.binding.compat.VanillaBooleanOptionBinding;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.SliderControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.caffeinemc.mods.sodium.client.gui.options.storage.MinecraftOptionsStorage;
import net.caffeinemc.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_313;
import net.minecraft.class_319;
import net.minecraft.class_4061;
import net.minecraft.class_4063;
import net.minecraft.class_4066;
import net.minecraft.class_5365;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class SodiumGameOptionPages {
   private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();
   private static final MinecraftOptionsStorage vanillaOpts = new MinecraftOptionsStorage();
   private static final class_1041 window = class_310.method_1551().method_22683();

   public static OptionPage general() {
      class_313 monitor = window.method_20831();
      List<OptionGroup> groups = new ArrayList<>();
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.renderDistance"))
                  .setTooltip(class_2561.method_43471("sodium.options.view_distance.tooltip"))
                  .setControl(option -> new SliderControl(option, 2, 32, 1, ControlValueFormatter.translateVariable("options.chunks")))
                  .setBinding((options, value) -> options.method_42503().method_41748(value), options -> (Integer)options.method_42503().method_41753())
                  .setImpact(OptionImpact.HIGH)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.simulationDistance"))
                  .setTooltip(class_2561.method_43471("sodium.options.simulation_distance.tooltip"))
                  .setControl(option -> new SliderControl(option, 5, 32, 1, ControlValueFormatter.translateVariable("options.chunks")))
                  .setBinding((options, value) -> options.method_42510().method_41748(value), options -> (Integer)options.method_42510().method_41753())
                  .setImpact(OptionImpact.HIGH)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.gamma"))
                  .setTooltip(class_2561.method_43471("sodium.options.brightness.tooltip"))
                  .setControl(opt -> new SliderControl(opt, 0, 100, 1, ControlValueFormatter.brightness()))
                  .setBinding(
                     (opts, value) -> opts.method_42473().method_41748(value.intValue() * 0.01),
                     opts -> (int)((Double)opts.method_42473().method_41753() / 0.01)
                  )
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.guiScale"))
                  .setTooltip(class_2561.method_43471("sodium.options.gui_scale.tooltip"))
                  .setControl(
                     option -> new SliderControl(
                        option,
                        0,
                        class_310.method_1551().method_22683().method_4476(0, class_310.method_1551().method_1573()),
                        1,
                        ControlValueFormatter.guiScale()
                     )
                  )
                  .setBinding((opts, value) -> {
                     opts.method_42474().method_41748(value);
                     class_310 client = class_310.method_1551();
                     client.method_15993();
                  }, opts -> (Integer)opts.method_42474().method_41753())
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.fullscreen"))
                  .setTooltip(class_2561.method_43471("sodium.options.fullscreen.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> {
                     opts.method_42447().method_41748(value);
                     class_310 client = class_310.method_1551();
                     class_1041 window = client.method_22683();
                     if (window != null && window.method_4498() != (Boolean)opts.method_42447().method_41753()) {
                        window.method_4500();
                        opts.method_42447().method_41748(window.method_4498());
                     }
                  }, opts -> (Boolean)opts.method_42447().method_41753())
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.fullscreen.resolution"))
                  .setTooltip(class_2561.method_43471("sodium.options.fullscreen_resolution.tooltip"))
                  .setControl(option -> new SliderControl(option, 0, null != monitor ? monitor.method_1621() : 0, 1, ControlValueFormatter.resolution()))
                  .setBinding((options, value) -> {
                     if (null != monitor) {
                        window.method_4505(0 == value ? Optional.empty() : Optional.of(monitor.method_1620(value - 1)));
                     }
                  }, options -> {
                     if (null == monitor) {
                        return 0;
                     } else {
                        Optional<class_319> optional = window.method_4511();
                        return optional.<Integer>map(videoMode -> monitor.method_1619(videoMode) + 1).orElse(0);
                     }
                  })
                  .setEnabled(() -> OsUtils.getOs() == OsUtils.OperatingSystem.WIN && class_310.method_1551().method_22683().method_20831() != null)
                  .setFlags(OptionFlag.REQUIRES_VIDEOMODE_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.vsync"))
                  .setTooltip(class_2561.method_43471("sodium.options.v_sync.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding(new VanillaBooleanOptionBinding(class_310.method_1551().field_1690.method_42433()))
                  .setImpact(OptionImpact.VARIES)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.framerateLimit"))
                  .setTooltip(class_2561.method_43471("sodium.options.fps_limit.tooltip"))
                  .setControl(option -> new SliderControl(option, 10, 260, 10, ControlValueFormatter.fpsLimit()))
                  .setBinding((opts, value) -> {
                     opts.method_42524().method_41748(value);
                     class_310.method_1551().method_22683().method_15999(value);
                  }, opts -> (Integer)opts.method_42524().method_41753())
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.viewBobbing"))
                  .setTooltip(class_2561.method_43471("sodium.options.view_bobbing.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding(new VanillaBooleanOptionBinding(class_310.method_1551().field_1690.method_42448()))
                  .build()
            )
            .add(
               OptionImpl.createBuilder(class_4061.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.attackIndicator"))
                  .setTooltip(class_2561.method_43471("sodium.options.attack_indicator.tooltip"))
                  .setControl(
                     opts -> new CyclingControl(
                        opts,
                        class_4061.class,
                        new class_2561[]{
                           class_2561.method_43471("options.off"),
                           class_2561.method_43471("options.attack.crosshair"),
                           class_2561.method_43471("options.attack.hotbar")
                        }
                     )
                  )
                  .setBinding((opts, value) -> opts.method_42565().method_41748(value), opts -> (class_4061)opts.method_42565().method_41753())
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.autosaveIndicator"))
                  .setTooltip(class_2561.method_43471("sodium.options.autosave_indicator.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> opts.method_42452().method_41748(value), opts -> (Boolean)opts.method_42452().method_41753())
                  .build()
            )
            .build()
      );
      return new OptionPage(class_2561.method_43471("stat.generalButton"), ImmutableList.copyOf(groups));
   }

   public static OptionPage quality() {
      List<OptionGroup> groups = new ArrayList<>();
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(class_5365.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.graphics"))
                  .setTooltip(class_2561.method_43471("sodium.options.graphics_quality.tooltip"))
                  .setControl(
                     option -> new CyclingControl(
                        option,
                        class_5365.class,
                        new class_2561[]{
                           class_2561.method_43471("options.graphics.fast"),
                           class_2561.method_43471("options.graphics.fancy"),
                           class_2561.method_43471("options.graphics.fabulous")
                        }
                     )
                  )
                  .setBinding((opts, value) -> opts.method_42534().method_41748(value), opts -> (class_5365)opts.method_42534().method_41753())
                  .setImpact(OptionImpact.HIGH)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(class_4063.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.renderClouds"))
                  .setTooltip(class_2561.method_43471("sodium.options.clouds_quality.tooltip"))
                  .setControl(
                     option -> new CyclingControl(
                        option,
                        class_4063.class,
                        new class_2561[]{
                           class_2561.method_43471("options.off"),
                           class_2561.method_43471("options.graphics.fast"),
                           class_2561.method_43471("options.graphics.fancy")
                        }
                     )
                  )
                  .setBinding((opts, value) -> {
                     opts.method_42528().method_41748(value);
                     if (class_310.method_29611()) {
                        class_276 framebuffer = class_310.method_1551().field_1769.method_29364();
                        if (framebuffer != null) {
                           framebuffer.method_1230(class_310.field_1703);
                        }
                     }
                  }, opts -> (class_4063)opts.method_42528().method_41753())
                  .setImpact(OptionImpact.LOW)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(SodiumGameOptions.GraphicsQuality.class, sodiumOpts)
                  .setName(class_2561.method_43471("soundCategory.weather"))
                  .setTooltip(class_2561.method_43471("sodium.options.weather_quality.tooltip"))
                  .setControl(option -> new CyclingControl<>(option, SodiumGameOptions.GraphicsQuality.class))
                  .setBinding((opts, value) -> opts.quality.weatherQuality = value, opts -> opts.quality.weatherQuality)
                  .setImpact(OptionImpact.MEDIUM)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(SodiumGameOptions.GraphicsQuality.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.leaves_quality.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.leaves_quality.tooltip"))
                  .setControl(option -> new CyclingControl<>(option, SodiumGameOptions.GraphicsQuality.class))
                  .setBinding((opts, value) -> opts.quality.leavesQuality = value, opts -> opts.quality.leavesQuality)
                  .setImpact(OptionImpact.MEDIUM)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(class_4066.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.particles"))
                  .setTooltip(class_2561.method_43471("sodium.options.particle_quality.tooltip"))
                  .setControl(
                     option -> new CyclingControl(
                        option,
                        class_4066.class,
                        new class_2561[]{
                           class_2561.method_43471("options.particles.all"),
                           class_2561.method_43471("options.particles.decreased"),
                           class_2561.method_43471("options.particles.minimal")
                        }
                     )
                  )
                  .setBinding((opts, value) -> opts.method_42475().method_41748(value), opts -> (class_4066)opts.method_42475().method_41753())
                  .setImpact(OptionImpact.MEDIUM)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.ao"))
                  .setTooltip(class_2561.method_43471("sodium.options.smooth_lighting.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> opts.method_41792().method_41748(value), opts -> (Boolean)opts.method_41792().method_41753())
                  .setImpact(OptionImpact.LOW)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.biomeBlendRadius"))
                  .setTooltip(class_2561.method_43471("sodium.options.biome_blend.tooltip"))
                  .setControl(option -> new SliderControl(option, 1, 7, 1, ControlValueFormatter.biomeBlend()))
                  .setBinding((opts, value) -> opts.method_41805().method_41748(value), opts -> (Integer)opts.method_41805().method_41753())
                  .setImpact(OptionImpact.LOW)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.entityDistanceScaling"))
                  .setTooltip(class_2561.method_43471("sodium.options.entity_distance.tooltip"))
                  .setControl(option -> new SliderControl(option, 50, 500, 25, ControlValueFormatter.percentage()))
                  .setBinding(
                     (opts, value) -> opts.method_42517().method_41748(value.intValue() / 100.0),
                     opts -> Math.round(((Double)opts.method_42517().method_41753()).floatValue() * 100.0F)
                  )
                  .setImpact(OptionImpact.HIGH)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.entityShadows"))
                  .setTooltip(class_2561.method_43471("sodium.options.entity_shadows.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> opts.method_42435().method_41748(value), opts -> (Boolean)opts.method_42435().method_41753())
                  .setImpact(OptionImpact.MEDIUM)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.vignette.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.vignette.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> opts.quality.enableVignette = value, opts -> opts.quality.enableVignette)
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(int.class, vanillaOpts)
                  .setName(class_2561.method_43471("options.mipmapLevels"))
                  .setTooltip(class_2561.method_43471("sodium.options.mipmap_levels.tooltip"))
                  .setControl(option -> new SliderControl(option, 0, 4, 1, ControlValueFormatter.multiplier()))
                  .setBinding((opts, value) -> opts.method_42563().method_41748(value), opts -> (Integer)opts.method_42563().method_41753())
                  .setImpact(OptionImpact.MEDIUM)
                  .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                  .build()
            )
            .build()
      );
      return new OptionPage(class_2561.method_43471("sodium.options.pages.quality"), ImmutableList.copyOf(groups));
   }

   public static OptionPage performance() {
      List<OptionGroup> groups = new ArrayList<>();
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(int.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.chunk_update_threads.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.chunk_update_threads.tooltip"))
                  .setControl(
                     o -> new SliderControl(o, 0, Runtime.getRuntime().availableProcessors(), 1, ControlValueFormatter.quantityOrDisabled("threads", "Default"))
                  )
                  .setImpact(OptionImpact.HIGH)
                  .setBinding((opts, value) -> opts.performance.chunkBuilderThreads = value, opts -> opts.performance.chunkBuilderThreads)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.always_defer_chunk_updates.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.always_defer_chunk_updates.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.HIGH)
                  .setBinding((opts, value) -> opts.performance.alwaysDeferChunkUpdates = value, opts -> opts.performance.alwaysDeferChunkUpdates)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_UPDATE)
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.use_block_face_culling.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.use_block_face_culling.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.MEDIUM)
                  .setBinding((opts, value) -> opts.performance.useBlockFaceCulling = value, opts -> opts.performance.useBlockFaceCulling)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.use_fog_occlusion.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.use_fog_occlusion.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setBinding((opts, value) -> opts.performance.useFogOcclusion = value, opts -> opts.performance.useFogOcclusion)
                  .setImpact(OptionImpact.MEDIUM)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_UPDATE)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.use_entity_culling.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.use_entity_culling.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.MEDIUM)
                  .setBinding((opts, value) -> opts.performance.useEntityCulling = value, opts -> opts.performance.useEntityCulling)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.animate_only_visible_textures.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.animate_only_visible_textures.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.HIGH)
                  .setBinding((opts, value) -> opts.performance.animateOnlyVisibleTextures = value, opts -> opts.performance.animateOnlyVisibleTextures)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_UPDATE)
                  .build()
            )
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.use_no_error_context.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.use_no_error_context.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.LOW)
                  .setBinding((opts, value) -> opts.performance.useNoErrorGLContext = value, opts -> opts.performance.useNoErrorGLContext)
                  .setEnabled(SodiumGameOptionPages::supportsNoErrorContext)
                  .setFlags(OptionFlag.REQUIRES_GAME_RESTART)
                  .build()
            )
            .build()
      );
      return new OptionPage(class_2561.method_43471("sodium.options.pages.performance"), ImmutableList.copyOf(groups));
   }

   private static boolean supportsNoErrorContext() {
      GLCapabilities capabilities = GL.getCapabilities();
      return (capabilities.OpenGL46 || capabilities.GL_KHR_no_error) && !Workarounds.isWorkaroundEnabled(Workarounds.Reference.NO_ERROR_CONTEXT_UNSUPPORTED);
   }

   public static OptionPage advanced() {
      List<OptionGroup> groups = new ArrayList<>();
      boolean isPersistentMappingSupported = MappedStagingBuffer.isSupported(RenderDevice.INSTANCE);
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(boolean.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.use_persistent_mapping.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.use_persistent_mapping.tooltip"))
                  .setControl(TickBoxControl::new)
                  .setImpact(OptionImpact.MEDIUM)
                  .setEnabled(() -> isPersistentMappingSupported)
                  .setBinding((opts, value) -> opts.advanced.useAdvancedStagingBuffers = value, opts -> opts.advanced.useAdvancedStagingBuffers)
                  .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                  .build()
            )
            .build()
      );
      groups.add(
         OptionGroup.createBuilder()
            .add(
               OptionImpl.createBuilder(int.class, sodiumOpts)
                  .setName(class_2561.method_43471("sodium.options.cpu_render_ahead_limit.name"))
                  .setTooltip(class_2561.method_43471("sodium.options.cpu_render_ahead_limit.tooltip"))
                  .setControl(opt -> new SliderControl(opt, 0, 9, 1, ControlValueFormatter.translateVariable("sodium.options.cpu_render_ahead_limit.value")))
                  .setBinding((opts, value) -> opts.advanced.cpuRenderAheadLimit = value, opts -> opts.advanced.cpuRenderAheadLimit)
                  .build()
            )
            .build()
      );
      return new OptionPage(class_2561.method_43471("sodium.options.pages.advanced"), ImmutableList.copyOf(groups));
   }
}
